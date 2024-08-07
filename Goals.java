public class Goals {
    private int weight;
    private int steps;  
    private int activity_duration;

    public Goals(int weight, int steps, int activity_duration){
        this.weight = weight;
        this.steps = steps;
        this.activity_duration = activity_duration;
    }

    //GETTERS
    public int get_goal_weight(){return weight;}
    public int get_goal_steps(){return steps;}
    public int get_goal_activity_duration(){return activity_duration;}

    //SETTERS
    public void set_goal_weight(int weight){this.weight = weight;}
    public void set_goal_steps(int steps){this.steps = steps;}
    public void set_goal_activity_duration(int activity_duration){this.activity_duration = activity_duration;}
  
}
