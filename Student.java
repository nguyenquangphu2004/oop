package Final;

public class Student extends Person{
    public static int next = 1001;
    String idStudent;
    String major;
    public Student() {
    }

    public Student(String idStudent, String major) {
        this.idStudent = idStudent;
        this.major = major;
    }

    public Student(String idStudent, String fullName, String birth,
             String gender, String major) {
        super(fullName,birth,gender);
        this.idStudent = idStudent;
        this.major = major;

    }

    public Student(Person person, Student student) {
        super(person.getIdCard(), person.getFullName(), person.getAddress(),
                person.getGender(), person.getBirth(),
                person.getEmail(), person.getPhoneNumber());
        this.idStudent = student.getIdStudent();
        this.major = student.getMajor();
    }
    public void setNext(int next) {
        Student.next = next;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent() {
        this.idStudent = "STU" + next;
        next ++;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    protected void doWork() {
    }

    @Override
    public String toString() {
        return super.toString() + ";" + idStudent + ";" + major;
    }
}
