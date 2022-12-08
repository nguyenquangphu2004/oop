package Final;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Course {
    public static int next = 1001;
    private String idCourse;
    private String nameCourse;
    private String classRoom;
    private Date time;
    private Teacher teacher;
    private ArrayList<GradeOfStudent> gradeOfStudents;

    public Course() {

    }

    public Course(String idCourse, String nameCourse, String classRoom, String time,
                  Teacher teacher,  ArrayList<GradeOfStudent> gradeOfStudents) {
        this.idCourse = idCourse;
        this.nameCourse = nameCourse;
        this.classRoom = classRoom;
        setTime(time);
        this.teacher = teacher;
        this.gradeOfStudents = gradeOfStudents;
    }

    public Course(String idCourse, String nameCourse, String classRoom,
                  String time, Teacher teacher) {
        this.idCourse = idCourse;
        this.nameCourse = nameCourse;
        this.classRoom = classRoom;
        setTime(time);
        this.teacher = teacher;
    }

    public Course(String idCourse) {
        this.idCourse = idCourse;
    }


    public void setNext(int next) {
        Course.next = next;
    }

    public String getIdCourse() {
        return idCourse;
    }

    public void setIdCourse() {
        this.idCourse = "COU" + next;
        next ++;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getTime() {
        var format = "hh:mm:ss";
        SimpleDateFormat dateFomat = new SimpleDateFormat(format);
        return dateFomat.format(time);
    }

    public void setTime(String time) {
        var format = "hh:mm:ss";
        SimpleDateFormat dateFomat = new SimpleDateFormat(format);
        try {
            this.time = dateFomat.parse(time);
        } catch (ParseException e) {
            this.time = new Date();
        }
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public ArrayList<GradeOfStudent> getGradeOfStudents() {
        return gradeOfStudents;
    }

    public void setGradeOfStudents(ArrayList<GradeOfStudent> gradeOfStudents) {
        this.gradeOfStudents = gradeOfStudents;
    }


    public void addStudentToCourse(Student student, Grade grade) {
        gradeOfStudents = new ArrayList<>();
        gradeOfStudents.add(new GradeOfStudent(student,grade));
    }




    public class GradeOfStudent {
        private Student student;
        private Grade grade;


        public GradeOfStudent(Student student, Grade grade) {
            this.student = student;
            this.grade = grade;
        }

        public Student getStudent() {
            return student;
        }

        public void setStudent(Student student) {
            this.student = student;
        }

        public Grade getGrade() {
            return grade;
        }

        public void setGrade(Grade grade) {
            this.grade = grade;
        }

        @Override
        public String toString() {
            return student.getIdStudent() + ";" +student.getFullName() + ";" +
                    student.getBirth() + ";" + student.getGender() + ";" +
                    getIdCourse() + ";" + student.getMajor();
        }
    }

    @Override
    public String toString() {
        return idCourse + ";" + nameCourse + ";" +  teacher.getIdTeacher() +
                teacher.getSubject().getIdSubject() + ";" + teacher.getFullName() +
                ";" + teacher.getSubject().getIdSubject()   + ";" + classRoom + ";" + getTime();
    }
}
