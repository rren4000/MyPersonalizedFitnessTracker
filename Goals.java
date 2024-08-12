import java.time.LocalDate;

public class Goals {
    private int weight;
    private int steps;
    private ActivityDuration act_duration;
    private LocalDate date;

    // DEFAULT CONSTRUCTOR
    public Goals() {
        this.weight = 0;
        this.steps = 0;
        this.act_duration = new ActivityDuration(0, 0, 0);
        this.date = LocalDate.now();
    }

    // PARAMETERIZED CONSTRUCTOR 
    public Goals(int weight, int steps, int move, int exercise, int stand) {
        this.weight = weight;
        this.steps = steps;
        this.act_duration = new ActivityDuration(move, exercise, stand);
        this.date = LocalDate.now();
    }

    // GETTERS
    public int get_goal_weight() {return weight;}
    public int get_goal_steps() {return steps;}
    public ActivityDuration get_goal_activity_duration() {return act_duration;}
    public LocalDate get_date() {return date;}

    // SETTERS
    public void set_goal_weight(int weight) {this.weight = weight;}
    public void set_goal_steps(int steps) {this.steps = steps;}
    public void set_goal_activity_duration(int move, int exercise, int stand) {
        this.act_duration.set_move(move);
        this.act_duration.set_exercise(exercise);
        this.act_duration.set_stand(stand);
    }
}
