package Final;
import Bai8.GraduatedStudent;

import java.awt.geom.Area;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class Test {
    public static boolean check = true;
    public static String fileStudent = "STUDENT.PHU";
    public static String fileTeacher = "TEACHER.PHU";
    public static String fileCourse = "COURSE.PHU";
    public static String fileSubject = "SUBJECT";
    public static String fileGrade = "GRADE.PHU";
    public static String fileStudentInCourse = "STUDENT-COURSE.PHU";

    public static void main(String[] args) {


        ArrayList<Person> people = new ArrayList<>(readFilePerson());
        ArrayList<Subject> subjects = new ArrayList<>(readFileSujbect());
        ArrayList<Course> courses = new ArrayList<>(readFileCourse());
        setNextIdStudent(people);
        setNextTeacher(people);
        setNextSubject(subjects);
        setNextCourse(courses);


        ManagerAccountFinal account = new ManagerAccountFinal();


        var input = new Scanner(System.in);
        while (check) {
            menuAccount();
            int choice = Integer.parseInt(input.nextLine());
            switch (choice) {
                case 1:
                    var check1 = account.signup(accountFinal(input));
                    var result = (check1) ? "Tạo tài khoản thành công" : "Tạo tài khoản thất bại";
                    System.out.println(result);
                    break;
                case 2:
                    boolean check2 = true;
                    var acc = accountFinal(input);
                    var login = account.login(acc);
                    for (int i = 0; i < account.getAccountFinals().size(); i++) {
                        if (acc.equals(account.getAccountFinals().get(i)) &&
                                acc.getPassWord().equals(account.getAccountFinals().get(i).getPassWord())
                                && account.getAccountFinals().get(i) instanceof Activity) {
                            var activity = (Activity) account.getAccountFinals().get(i);
                            check2 = activity.isActivity();
                        }
                    }
                    if (check2 && login) {
                        boolean bolean = true;
                        while (bolean) {
                            menu();
                            int choice1 = Integer.parseInt(input.nextLine());
                            switch (choice1) {
                                case 1:
                                    people.add(new Student(person(input), (Student) student(input)));
                                    saveFileStudent(people);
                                    break;
                                case 2:
                                    var peopl = person(input);
                                    Teacher tech = (Teacher) teacher(input, subjects);
                                    if (tech != null) {
                                        people.add(new Teacher(peopl, tech));
                                        saveFileTeacher(people);
                                        System.out.println("====>Thêm giáo viên thành công<====");
                                    } else {
                                        System.out.println("===>Môn học chưa tồn tại Or mã môn không hợp lệ<====");
                                        System.out.println("===>Thêm giáo viên thất bại <======");
                                    }
                                    break;
                                case 3:
                                    subjects.add(subject(input));
                                    saveFileSubject(subjects);
                                    break;
                                case 4:
                                    var course = course(input, subjects, people);
                                    if (course != null) {
                                        courses.add(course);
                                        saveFileCourse(courses);
                                        System.out.println("====>Thêm lớp thành công<===");
                                    } else {
                                        System.out.println("Không tồn tại môn học Hoặc Không tồn tại giáo viên");
                                        System.out.println("=====> Thêm lớp thất bại <======");
                                    }

                                    break;
                                case 5:
                                    addStudentToCourse(courses, people, input);
                                    saveFileStudentInCourse(courses);
                                    break;
                                case 6:
                                    showListStudentAll(people);
                                    break;
                                case 7:
                                    showListStudentInCourse(courses);
                                    break;
                                case 8:
                                    showListSubject(subjects);
                                    break;
                                case 9:
                                    showListCourse(courses);
                                    break;
                                case 10:
                                    showListTeacherAll(people);
                                    break;


                            }
                        }

                    } else {
                        System.out.println("=====>Đăng nhập thất bại<=====");
                    }
                    break;
                case 3:
                    var check3 = account.lockAccount(accountFinal(input));
                    System.out.println((check3) ? "====>Khóa thành công<====" : "====>Khóa thất bại<====");
                    break;
                case 4:
                    System.out.println((account.openAccount(accountFinal(input))) ?
                            "====>Mở khóa thành công<=====" : "====>Mở khóa thất bại<=====");
                    break;
                case 5:
                    var sign = accountFinal(input);
                    var checkPassWordandUser = findOfPassWord(sign, account.getAccountFinals());
                    if (checkPassWordandUser != null) {
                        System.out.println("New PassWord: ");
                        String newPass = input.nextLine();
                        System.out.println("Enter a new password: ");
                        String newPass1 = input.nextLine();
                        if (newPass.compareTo(newPass1) == 0) {
                            var concac = account.changePassWord(new AccountFinal(sign.getUser(), newPass));
                            if (concac) {
                                System.out.println("===>ĐỔI MẬT KHÂU THÀNH CÔNG <===");
                            } else {
                                System.out.println("===>Đổi thất bại<====");
                            }
                        } else {
                            System.out.println("===>Vui lòng nhập lại mất khẩu mới<====");
                        }
                    } else {
                        System.out.println("====>Tên đăng nhập hoặc mật khẩu của tài khoản không hợp lệ<====");
                    }
                    break;

                case 6:
                    account.showlistAccount();
                    break;
                default:
                    check = false;
                    System.out.println("======>Cảm ơn bạn đã sử dụng chức năng<======");
            }

        }
    }

    /**
     * Phương thức kiểm tra xem tài khoản hiên thời có trong danh sách các tài khoản hay không
     * @param sign     : tài khoản hiện thời
     * @param accounts : danh sách các tài khoản
     * @return : khác null nếu tìm thấy, bằng null thì không tìm thấy
     */
    private static AccountFinal findOfPassWord(AccountFinal sign, ArrayList<AccountFinal> accounts) {
        for (int i = 0; i < accounts.size(); i++) {
            if (sign.equals(accounts.get(i)) &&
                    sign.getPassWord().equals(accounts.get(i).getPassWord())) {
                return accounts.get(i);
            }
        }
        return null;
    }

    /**
     * Phương thức Nhập tài khoản
     *
     * @param input : dùng để nhập dữ liệu từ bàn phìm
     * @return trả về một đối tượng kiểu AccoutFinal
     */

    public static AccountFinal accountFinal(Scanner input) {
        System.out.println("User: ");
        String user = input.nextLine();
        System.out.println("PassWord: ");
        String passWord = input.nextLine();
        return new Activity(user, passWord, true, false);
    }

    /**
     * Phương thức hiển thị menu chức năng của tài khoản
     */
    public static void menuAccount() {
        System.out.println("1.Đăng kí tài khoản.");
        System.out.println("2.Đăng nhập tài khoản.");
        System.out.println("3.Khóa tài khoản.");
        System.out.println("4.Mở tài khoản.");
        System.out.println("5.Thay đổi mật khẩu.");
        System.out.println("6.Hiển thị danh sách tài khoản.");
    }

    public static void menu() {
        System.out.println("=====================================================");
        System.out.println("==  1.Thêm danh sách học sinh vào ArrayList         =");
        System.out.println("==  2.Thêm giáo viên vào ArrayList                  =");
        System.out.println("==  3.Thêm môn học vào Arralist                     =");
        System.out.println("==  4.Thêm lớp học vào ArrayList                    =");
        System.out.println("==  5.Thêm sinh viên vào lớp học                    =");
        System.out.println("==  6.Hiển thị danh sách tất cả học sinh            =");
        System.out.println("==  7.Hiển thị danh sách học sinh có trong một lớp  =");
        System.out.println("==  8.Hiển thị danh sách tất cả môn học             =");
        System.out.println("==  9.Hiển thị danh sách tất cả lớp học             =");
        System.out.println("==  10.Hiển thị danh sách toàn bộ giảng viên        =");
        System.out.println("=====================================================");


        System.out.printf("            Mời bạn chọn: ");
    }

    /**
     * Phương thức nhập thông tin chung cho con ngươời
     *
     * @param input: dùng để nhập thông tin từ bàn phím
     * @return trả về đối tượng chung của con người.
     */

    public static Person person(Scanner input) {
        System.out.println("Căn cước: ");
        String idCard = input.nextLine();
        System.out.println("Họ và tên: ");
        String fullName = input.nextLine();
        System.out.println("Địa chỉ: ");
        String address = input.nextLine();
        System.out.println("Giới tính: ");
        String gender = input.nextLine();
        System.out.println("Sinh nhật: ");
        String birth = input.nextLine();
        System.out.println("Email: ");
        String email = input.nextLine();
        System.out.println("Số điện thoại: ");
        String phoneNumber = input.nextLine();

        return new Person(idCard, fullName, address, gender, birth, email, phoneNumber) {
            @Override
            protected void doWork() {

            }
        };
    }

    /**
     * Phương thức nhập các thông tin của sinh viên.
     *
     * @param input : Nhập dữ liệu từ bàn phím
     * @return một đối tượng kiểu Student
     */

    public static Person student(Scanner input) {
        System.out.println("Mã sinh viên: ");
        Student student  = new Student();
        student.setIdStudent();
        System.out.println(student.getIdStudent());
        System.out.println("Chuyên ngành: ");
        String major = input.nextLine();
        System.out.println("===>Thêm sinh viên thành công<=====");
        return new Student(student.getIdStudent(), major);
    }

    public static Person teacher(Scanner input, ArrayList<Subject> subjects) {
        if (subjects.size() > 0) {
            System.out.println("Mã giáo viên: ");
            Teacher teacher = new Teacher();
            teacher.setIdTeacher();
            System.out.println(teacher.getIdTeacher());
            System.out.println("Mã môn học: ");
            String idSubject = input.nextLine();
            var checkIdSubject = findOfSubjectById(idSubject, subjects);
            if (checkIdSubject != null) {
                return new Teacher(teacher.getIdTeacher(), checkIdSubject);
            }
        }
        return null;
    }

    /**
     * phương thức thêm môn học
     * @param input : nhập thông tin môn học từ bàn phím
     * @return : 1 dđối tượng kiêu subject
     */
    public static Subject subject(Scanner input) {
        System.out.println("Mã môn học: ");
        Subject subject = new Subject();
        subject.setIdSubject();
        System.out.println(subject.getIdSubject());
        System.out.println("Tên môn học: ");
        String nameSubject = input.nextLine();
        System.out.println("Số tín chỉ: ");
        int numberCredit = Integer.parseInt(input.nextLine());
        System.out.println("Số tiết học: ");
        int numberLesson = Integer.parseInt(input.nextLine());
        System.out.println("=====>Thêm môn học thành công<====");
        return new Subject(subject.getIdSubject(), nameSubject, numberCredit, numberLesson);
    }

    /**
     * Phương thuwsvc thêm lớp học
     * @param input  : nhập thông tin lớp học từ bàn phím
     * @param subjects : danh sách các môn học và kiểm tra xem môn học đã tồn tại hay không
     *                 nếu không tồn tại trả về null;
     * @param teachers : danh sách các giảng viên và kiểm tra xem giảng viên có tồn tại để xét vào lớp
     * @return: đối tương Course
     */
    public static Course course(Scanner input, ArrayList<Subject> subjects, ArrayList<Person> teachers) {
        if (subjects.size() > 0) {
            System.out.println("Mã khóa học: ");
            Course course = new Course();
            course.setIdCourse();
            System.out.println(course.getIdCourse());
            System.out.println("Tên khóa học: ");
            String nameCourse = input.nextLine();
            System.out.println("Mã giáo viên: ");
            String idTeacher = input.nextLine();
            var teacher = findOfTeacher(idTeacher, teachers);
            if (teacher != null) {
                System.out.println("Mã môn học: ");
                System.out.println(teacher.getSubject().getIdSubject());
                System.out.println("Phòng học: ");
                String classRoom = input.nextLine();
                System.out.println("Thời gian học: ");
                String time = input.nextLine();
                return new Course(course.getIdCourse(), nameCourse, classRoom, time, teacher);
            }

        }
        return null;

    }

    /**
     * Pương thức Nhập điểm cho từng học sinh trong lớp
     * @param input :nhập giá trị từ bàn phím
     * @return 1 đối tượng Grade
     */
    public static Grade grade(Scanner input,ArrayList<Person> people) {
        System.out.println("Nhập mã học sinh: ");
        String idStuden = input.nextLine();
        var checkIdStudent = findOfStudentById(idStuden,people);
        if(checkIdStudent != null) {
            System.out.println("Mã bảng điểm: ");
            Grade grade = new Grade();
            grade.setIdGrade();
            System.out.println(grade.getIdGrade());
            System.out.println("Điểm chuyên cần: ");
            System.out.println("Điểm giữa kì 1: ");
            System.out.println("Điểm cuối kì 1: ");
            System.out.println("Điểm trung bình kì 1: ");
            System.out.println("Điểm giữa kì 2: ");
            System.out.println("Điểm cuối kì 2: ");
            System.out.println("Điểm trung bình kì 2: ");
            System.out.println("Điểm trung bình cả năm: ");
        }
        return null;
    }

    /**
     * Phương thức dùng để tìm giáo viên theo mã giáo viên
     *
     * @param idTeacher : Mã giáo viên
     * @param people    : Danh sách các con người bao gôm học sinh và giáo viên.
     * @return trả về null nếu không tìm thấy giáo viên
     * @return trả về khác null nếu đã tìm thấy được giáo viên
     */
    private static Teacher findOfTeacher(String idTeacher, ArrayList<Person> people) {
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i) instanceof Teacher) {
                var teacher = (Teacher) people.get(i);
                if (teacher.getIdTeacher().compareTo(idTeacher) == 0) {
                    return teacher;
                }
            }
        }
        return null;
    }

    /**
     * PHƯƠNG THỨC LƯU DANH SÁCH CỦA SINH VIÊN VÀO FILE STUDENT.PHU;
     *
     * @param people :danh sách các people tham chiếu đến đối tượng lớp con là Student
     */
    public static void saveFileStudent(ArrayList<Person> people) {
        try {
            FileWriter fileWriter = new FileWriter(fileStudent);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (var item : people) {
                if (item instanceof Student) {
                    var student = (Student) item;
                    printWriter.println(student);
                }
            }
            printWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Phương thức để lưu danh sách các giáo viên vào file TEACHER.PHU
     *
     * @param people: là danh sách các people và các thành phần nào của peole mà tham chiếu đến
     *                Teacher
     */
    public static void saveFileTeacher(ArrayList<Person> people) {
        try {
            FileWriter fileWriter = new FileWriter(fileTeacher);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (var item : people) {
                if (item instanceof Teacher) {
                    var teacher = (Teacher) item;
                    printWriter.println(teacher);
                }
            }
            printWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Phương thức để lưu danh sách các môn học vào file SUBJECT.PHU
     *
     * @param subjects:là một danh sách các môn học
     */

    public static void saveFileSubject(ArrayList<Subject> subjects) {
        try {
            FileWriter fileWriter = new FileWriter(fileSubject);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (var item : subjects) {
                printWriter.println(item);
            }
            printWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Đây là phương thức ghi vào dữ liệu cần thiết của khóa học vào file
     *
     * @param courses : là tham số để lấy thông tin từ lớp để ghi dữ liệu vào file COURSE.PHU
     */
    public static void saveFileCourse(ArrayList<Course> courses) {
        try {
            FileWriter fileWriter = new FileWriter(fileCourse);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (var item : courses) {
                printWriter.println(item);
            }
            printWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveFileStudentInCourse(ArrayList<Course> courses) {
        try {
            FileWriter fileWriter = new FileWriter(fileStudentInCourse);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for(int i = 0; i < courses.size(); i++) {
                if(courses.get(i).getGradeOfStudents() != null) {
                    for (int j = 0; j < courses.get(i).getGradeOfStudents().size(); j++) {
                        printWriter.println(courses.get(i).getGradeOfStudents().get(i));
                    }
                }
            }

            printWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * PHƯƠNG THỨC TÌM KIẾM MN HỌC THEO MÃ MÔN
     * @param idSubject: MÁ MÔN HỌC
     * @param subjects:DANH SÁCH CACS MOON HỌC
     * @return: TRAVE NULL NẾU KHÔNG TIM THẤY, != NULL NẾU TÌM THẤY
     */
    private static Subject findOfSubjectById(String idSubject, ArrayList<Subject> subjects) {
        for (int i = 0; i < subjects.size(); i++) {
            if (idSubject.compareTo(subjects.get(i).getIdSubject()) == 0) {
                return subjects.get(i);
            }
        }
        return null;
    }

    /**
     * Phương thwusc tìm kiếm lớp học theo mã lớp
     * @param idCourse: MÃ LỚP
     * @param courses:DANH SÁCH CÁC LỚP HỌC
     * @return: NULL nếu không tìm thấy,!= Null NẾU ĐÃ TÌM THẤY
     */
    private static Course findOfCourseById(String idCourse, ArrayList<Course> courses) {
        for (int i = 0; i < courses.size(); i++) {
            if (idCourse.compareTo(courses.get(i).getIdCourse()) == 0) {
                return courses.get(i);
            }
        }
        return null;
    }

    /**
     * Phương thức thêm học sinh vào lớp học
     * @param courses : dah sách các lớp học
     * @param people: Đối tượng tham chiếu đến các học sinh
     * @param input  : giati nhập từ bàn phím
     */
    public static void addStudentToCourse(ArrayList<Course> courses, ArrayList<Person> people,
                                          Scanner input) {
        System.out.println("Mã lớp: ");
        String idCourse = input.nextLine();
        var checkIdCourse = findOfCourseById(idCourse, courses);
        if (checkIdCourse != null) {
            System.out.println("Mã sinh viên: ");
            String idStudent = input.nextLine();
            var checkIdStudent = findOfStudentById(idStudent, people);
            if (checkIdStudent != null) {
                checkIdCourse.addStudentToCourse(checkIdStudent, null);
                System.out.println("=====>Thêm học sinh thành công<=====");
            } else {
                System.out.println("====>Thêm học sinh thất bại<=====");
            }
        } else {

        }
    }

    /**
     * Phương thức tìm kiếm học sinh theo mã sinh viên
     * @param idStudent: MÃ SINH VIÊN
     * @param people : ĐỐI TƯỢNG THAM CHẾU ĐẾN STUDENT
     * @return : NULL NẾU KHÔNG TÌM THẤY, KHÁC NULL KHI ĐÃ TÌM THẤY
     */
    private static Student findOfStudentById(String idStudent, ArrayList<Person> people) {
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i) instanceof Student) {
                var student = (Student) people.get(i);
                if (student.getIdStudent().compareTo(idStudent) == 0) {
                    return student;
                }
            }
        }
        return null;
    }

    /**
     * Hiển thị danh sách tất cả học sinh
     *
     * @param people: 1 danh sách các con người gồm học sinh và giáo viên
     */
    public static void showListStudentAll(ArrayList<Person> people) {
        System.out.printf("%-12s%-25s%-15s%-13s%-14s%-15s%-18s%-15s%-15s\n", "Căn cước", "Họ và tên",
                "Địa chỉ", "Giới tính", "Sinh nhật", "Email", "Số điện thoại",
                "Mã sinh viên", "Chuyên ngành");
        for (var item1 :
                people) {
            if (item1 instanceof Student) {
                var item = (Student) item1;
                System.out.printf("%-12s%-25s%-15s%-13s%-14s%-15s%-18s%-15s\n",
                        item.getIdCard(), item.getFullName(), item.getAddress(), item.getGender(),
                        item.getBirth(), item.getPhoneNumber(), item.getIdStudent(), item.getMajor());
            }

        }
    }

    /**
     * Hiển thị danh sách Giaos viên
     * @param people : 1 danh sách các con người gồm học sinh và giáo viên
     */

    public static void showListTeacherAll(ArrayList<Person> people) {
        System.out.printf("%-12s%-25s%-15s%-13s%-14s%-15s%-18s%-15s%-15s\n", "Căn cước", "Họ và tên",
                "Địa chỉ", "Giới tính", "Sinh nhật", "Email", "Số điện thoại",
                "Mã giáo viên", "Mã môn học");
        for (var item1 :
                people) {
            if (item1 instanceof Teacher) {
                var item = (Teacher) item1;
                var teacher = item.getSubject();
                System.out.printf("%-12s%-25s%-15s%-13s%-14s%-15s%-18s%-15s%-15s\n",
                        item.getIdCard(), item.getFullName(), item.getAddress(), item.getGender(),
                        item.getBirth(),item.getEmail(),item.getPhoneNumber(), item.getIdTeacher(), teacher.getIdSubject());
            }
        }
    }

    /**
     * Phương thức hiển thị danh sách các khóa học
     * @param courses: 1 danh sách các khóa học
     */
    public static void showListCourse(ArrayList<Course> courses) {
        System.out.printf("%-15s%-16s%-16s%-20s%-15s%-15s%-16s\n","Mã Khóa học","Tên khóa học","Mã Giảng viên","Họ và tên",
                "Mã môn học","Phòng học","Thời gian học");
        for (var item :
                courses) {
            System.out.printf("%-15s%-16s%-16s%-20s%-15s%-15s%-16s\n",item.getIdCourse(),
                    item.getNameCourse(),item.getTeacher().getIdTeacher(),item.getTeacher().getFullName(),
                    item.getTeacher().getSubject().getIdSubject(),item.getClassRoom(),item.getTime());

        }
    }

    /**
     * Hiển thị danh sách các môn học
     * @param subjects : danh sách các môn học
     */
    public static void showListSubject(ArrayList<Subject> subjects) {
        System.out.printf("%-15s%-16s%-15s%-15s\n","Mã môn học",""+
                "Tên môn học","Số tín chỉ","Số bài học");
        for (var item :
                subjects) {
            System.out.printf("%-15s%-16s%-15s%-15s\n", item.getIdSubject(), item.getNameSubject(),
                    item.getNumberCredit(),item.getNumberLesson());
        }
    }

    /**
     * Hiển thị danh sách các học sinh có trong từng lớp học
     * @param courses:danh sách các lớp học
     */
    public static void showListStudentInCourse(ArrayList<Course> courses) {
        for (int i = 0; i < courses.size(); i++) {
            System.out.println("Mã khóa học: " + courses.get(i).getIdCourse());
            System.out.printf("%-15s%-19s%-14s%-14s%-16s%-16s\n","Mã sinh viên","Họ và tên",
                    "Sinh nhật","Giới tính","Mã lớp học","Chuyên ngành");
            if(courses.get(i).getGradeOfStudents() != null) {
                for (int j = 0; j < courses.get(i).getGradeOfStudents().size(); j++) {
                    var item = courses.get(i).getGradeOfStudents().get(j).getStudent();
                    System.out.printf("%-15s%-19s%-14s%-14s%-16s%-16s\n", item.getIdStudent(),
                            item.getFullName(), item.getBirth(), item.getGender(),
                            courses.get(i).getIdCourse(),item.getMajor());
                }
            }
             if(i < courses.size() - 1){
                System.out.println("================================================================");
            }

        }

    }

    /**
     * Đọc file student và teacher rồi cho vào danh sách con người
     * @return
     */
    public static ArrayList<Person> readFilePerson() {
        ArrayList<Person> people = new ArrayList<>();
        File file = new File(fileStudent);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Scanner readFile = new Scanner(file);
            while(readFile.hasNextLine()) {
                var words = readFile.nextLine().split(";");
                String idCard = words[0];
                String fullName = words[1];

                String address = words[2];
                String gender = words[3];
                String birth = words[4];
                String email = words[5];
                String phoneNumber = words[6];
                String idStudent = words[7];
                String major = words[8];
                Person person = new Person(idCard,fullName,address,gender,birth,
                            email,phoneNumber) {
                    @Override
                    protected void doWork() {

                    }
                };
                Person student = new Student(idStudent,major);
                people.add(new Student(person,(Student) student));
            }
            readFile.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        File file1 = new File(fileTeacher);
        try {
            file1.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Scanner readFile1 = new Scanner(file1);
            while (readFile1.hasNextLine()) {
                var words = readFile1.nextLine().split(";");
                String idCard = words[0];
                String fullName = words[1];
                String address = words[2];
                String gender = words[3];
                String birth = words[4];
                String email = words[5];
                String phoneNumber = words[6];
                String idTeacger = words[7];
                String idSubject = words[8];
                Subject subject = new Subject(idSubject);
                Person person = new Person(idCard, fullName, address, gender, birth,
                        email, phoneNumber) {
                    @Override
                    protected void doWork() {

                    }
                };
                Person teacher = new Teacher(idTeacger, subject);
                people.add(new Teacher(person, (Teacher) teacher));
            }
            readFile1.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return people;
    }

    /**
     * đọc file subject
     * @return danh sách các môn học
     */
    public static ArrayList<Subject> readFileSujbect() {
        ArrayList<Subject> subjects = new ArrayList<>();
        File file = new File(fileSubject);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Scanner readFile = new Scanner(file);
            while(readFile.hasNextLine()) {
                var words = readFile.nextLine().split(";");
                String idSubject = words[0];
                String nameSubject = words[1];
                int credit = Integer.parseInt(words[2]);
                int lesson = Integer.parseInt(words[3]);
                Subject subject = new Subject(idSubject,nameSubject,credit,lesson);
                subjects.add(subject);
            }
            readFile.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return subjects;
    }

    /**
     * Đọc dữ liệu từ file COURSE.DAT AND STUDENT-COURSE.DAT
     * @return : trả về mảng danh sách các lớp học
     */
    public static ArrayList<Course> readFileCourse() {
        ArrayList<Course> courses = new ArrayList<>();
        File file = new File(fileCourse);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Scanner readFile = new Scanner(file);
            while(readFile.hasNextLine()) {
                var words = readFile.nextLine().split(";");
                String idCourse = words[0];
                String nameCourse = words[1];
                String idTeacher  = words[2];
                String fullName = words[3];
                String idSubject = words[4];
                String roomClass = words[5];
                String time = words[6];
                Course course = new Course(idCourse,nameCourse,roomClass,time,
                            new Teacher(idTeacher,fullName, new Subject(idSubject)));
                courses.add(course);
            }
            readFile.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        File file1 = new File(fileStudentInCourse);
        try {
            file1.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Scanner readFile = new Scanner(file1);
            while(readFile.hasNextLine()) {
                var words = readFile.nextLine().split(";");
                String idStudent = words[0];
                String fullName = words[1];
                String birth = words[2];
                String gender = words[3];
                String idCourse = words[4];
                String major = words[5];
                var checkIdCourse = findOfCourseById(idCourse,courses);
                if(checkIdCourse != null) {
                    checkIdCourse.addStudentToCourse(new Student(idStudent,fullName,birth,gender,major),null);
                }
            }
            readFile.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return courses;
    }


    /**
     * Phương thức tự động mã sinh viên
     * @param people : bảng danh sách student và teacher
     */
    public static void setNextIdStudent(ArrayList<Person> people) {
        for(int i = 0 ;i  < people.size(); i++) {
            if(people.get(i) instanceof Student ) {
                var item = (Student)people.get(i);
                String nextIdStudent = item.getIdStudent().substring(3);
                int next = Integer.parseInt(nextIdStudent);
                Student student = new Student();
                student.setNext(next + 1);
            }
        }

    }

    /**
     * Phương thức tự động mã giáo viên
     * @param people : bảng danh sách student và teacher
     */
    public static void setNextTeacher(ArrayList<Person> people) {
        for(int i = 0 ;i  < people.size(); i++) {
            if(people.get(i) instanceof Teacher ) {
                var item = (Teacher)people.get(i);
                String nextIdStudent = item.getIdTeacher().substring(3);
                int next = Integer.parseInt(nextIdStudent);
                Student student = new Student();
                student.setNext(next + 1);
            }
        }
    }


    /**
     * Phương thức tự động mã môn học
     * @param subjects : bảng danh sách môn học
     */
    public static void setNextSubject(ArrayList<Subject> subjects) {
        for(int i = 0 ;i  < subjects.size(); i++) {
                String nextIdStudent = subjects.get(i).getIdSubject().substring(3);
                int next = Integer.parseInt(nextIdStudent);
                Student student = new Student();
                student.setNext(next + 1);
        }

    }


    /**
     * Phương thức tự động mã lớp học
     * @param courses : bảng danh sách lớp học
     */
    public static void setNextCourse(ArrayList<Course> courses) {
        for(int i = 0 ;i  < courses.size(); i++) {
            String nextIdStudent = courses.get(i).getIdCourse().substring(3);
            int next = Integer.parseInt(nextIdStudent);
            Student student = new Student();
            student.setNext(next + 1);
        }
    }

}