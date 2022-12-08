package Final;

public class Subject {
    public static int next = 1001;
    private String idSubject;
    private String nameSubject;
    private int numberCredit;
    private int numberLesson;

    public Subject() {

    }

    public Subject(String idSubject) {
        this.idSubject = idSubject;
    }

    public Subject(String idSubject, String nameSubject,
                   int numberCredit, int numberLesson) {
        this.idSubject = idSubject;
        this.nameSubject = nameSubject;
        this.numberCredit = numberCredit;
        this.numberLesson = numberLesson;
    }

    public static void setNext(int next) {
        Subject.next = next;
    }

    public String getIdSubject() {
        return idSubject;
    }

    public void setIdSubject() {
        this.idSubject = "SUB" + next;
        next ++;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public int getNumberCredit() {
        return numberCredit;
    }

    public void setNumberCredit(int numberCredit) {
        this.numberCredit = numberCredit;
    }

    public int getNumberLesson() {
        return numberLesson;
    }

    public void setNumberLesson(int numberLesson) {
        this.numberLesson = numberLesson;
    }

    @Override
    public String toString() {
        return idSubject + ";" + nameSubject + ";" + numberCredit + ";" + numberLesson;
    }
}
