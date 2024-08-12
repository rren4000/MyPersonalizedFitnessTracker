

public class ActivityObj implements Activity {
    private int id;
    private String name;
    private int duration;

    public ActivityObj(int id, String name, int duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return id + ". " + name + " - " + duration + " minutes";
    }
}
