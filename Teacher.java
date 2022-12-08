package Final;

public class Teacher extends Person{

    public static int next = 1001;
    private String idTeacher;
    private Subject subject;

    public Teacher() {
        subject = null;

    }

    public Teacher(String idTeacher, Subject subject) {
        this.idTeacher = idTeacher;
        this.subject = subject;
    }


    public Teacher(String idTeacher, String fullName, Subject subject) {
        super(fullName);
        this.idTeacher = idTeacher;
        this.subject = subject;
    }

    public Teacher(String idCard, String fullName, String address, String gender, String birth, String email, String phoneNumber, String idTeacher, Subject subject) {
        super(idCard, fullName, address, gender, birth, email, phoneNumber);
        this.idTeacher = idTeacher;
        this.subject = subject;
    }

    public Teacher(Person person, Teacher teacher) {
        super(person.getIdCard(), person.getFullName(), person.getAddress(),
                person.getGender(), person.getBirth(),
                person.getEmail(), person.getPhoneNumber());

        this.idTeacher = teacher.getIdTeacher();
        this.subject = teacher.getSubject();
    }



    public void setNext(int next) {
        Teacher.next = next;

    }

    public String getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher() {
        this.idTeacher = "TEA" + next;
        next ++;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    protected void doWork() {
    }

    @Override
    public String toString() {
        return super.toString() + ";" + getIdTeacher() + ";" +
                getSubject().getIdSubject();
    }
}
