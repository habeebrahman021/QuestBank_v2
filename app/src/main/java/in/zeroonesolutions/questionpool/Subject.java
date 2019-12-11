package in.zeroonesolutions.questionpool;

public class Subject {
    private int id;
    private String name;
    private int icon;

    public Subject(int id, String name, int icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getIcon() {
        return icon;
    }

}
