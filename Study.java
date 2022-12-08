package Final;

public enum Study {
    A_PLUS("Xuất sắc"),
    A("Giỏi"),
    B("Khá"),
    C("Trung bình"),
    D("Yếu"),
    F("Trượt môn");

    private String value;
    Study(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
