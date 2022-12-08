package Final;

import java.util.Objects;

public class AccountFinal {
    private String user;
    private String passWord;

    public AccountFinal(String user, String passWord) {
        this.user = user;
        this.passWord = passWord;
    }

    public AccountFinal() {

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountFinal that = (AccountFinal) o;
        return Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }
}
