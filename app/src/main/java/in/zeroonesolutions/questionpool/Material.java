package in.zeroonesolutions.questionpool;

public class Material {
    String name;
    String url;
    String subject;
    String medium;
    String class_type;
    String board;

    public Material() {
    }

    public Material(String name, String url, String subject, String medium, String class_type, String board) {
        this.name = name;
        this.url = url;
        this.subject = subject;
        this.medium = medium;
        this.class_type = class_type;
        this.board = board;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getSubject() {
        return subject;
    }

    public String getMedium() {
        return medium;
    }

    public String getClass_type() {
        return class_type;
    }

    public String getBoard() {
        return board;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public void setClass_type(String class_type) {
        this.class_type = class_type;
    }

    public void setBoard(String board) {
        this.board = board;
    }
}
