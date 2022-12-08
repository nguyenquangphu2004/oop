package Final;

public class Activity extends AccountFinal{
    private boolean activity;
    private boolean online;


    public Activity(String user, String passWord, boolean activity, boolean online) {
        super(user, passWord);
        this.activity = activity;
        this.online = online;
    }

    public Activity() {

    }

    public Activity(boolean activity, boolean online) {
        this.activity = activity;
        this.online = online;
    }

    public boolean isActivity() {
        return activity;
    }

    public void setActivity(boolean activity) {
        this.activity = activity;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
