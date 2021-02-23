package in.zeroonesolutions.questionpool;

public class Subject {
    private int id;
    private String name;
    private int icon;

    private int files_count;

    public Subject(){

    }

    public Subject(int id, String name, int icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    public Subject(int id, String name, int icon, int files_count) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.files_count = files_count;
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

    public int getFiles_count() {
        return files_count;
    }

    public void setFiles_count(int files_count) {
        this.files_count = files_count;
    }
}
