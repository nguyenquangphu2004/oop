package Final;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Person {
    private String idCard;
    private FullNanme fullName;
    private String address;
    private String gender;
    private Date birth;
    private String email;
    private String phoneNumber;

    public Person() {

    }

    public Person(String fullName, String birth, String gender) {
        this.fullName = new FullNanme();
        setFullName(fullName);
        setBirth(birth);
        this.gender = gender;
    }
    public Person(String fullName) {
        this.fullName = new FullNanme();
        setFullName(fullName);
    }

    public Person(String idCard, String fullName,
                  String address, String gender, String birth,
                  String email, String phoneNumber) {
        this.idCard = idCard;
        this.fullName = new FullNanme();
        setFullName(fullName);
        this.address = address;
        this.gender = gender;
        setBirth(birth);
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getFullName() {
        return fullName.last + " " + fullName.mid + fullName.first;
    }

    public void setFullName(String fullName) {
        var words = fullName.split(" ");
        this.fullName.last = words[0];
        this.fullName.first = words[words.length - 1];
        this.fullName.mid = "";
        for (int i = 1; i < words.length - 1; i++) {
            this.fullName.mid += words[i] + " ";
        }

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
        var format = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        return dateFormat.format(birth);
    }

    public void setBirth(String birth) {
        var format = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            this.birth = dateFormat.parse(birth);
        } catch (ParseException e) {
            this.birth = new Date();
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    protected abstract void doWork();

    private class FullNanme {
        private String first;
        private String mid;
        private String last;

        public FullNanme() {

        }

        public FullNanme(String first, String mid, String last) {
            this.first = first;
            this.mid = mid;
            this.last = last;
        }

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getMid() {
            return mid;
        }

        public void setMid(String mid) {
            this.mid = mid;
        }

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }
    }

    @Override
    public String toString() {
        return idCard + ";" + getFullName() + ";" + address + ";" +
                gender + ";" + getBirth() + ";" + email + ";" + phoneNumber;

    }
}
