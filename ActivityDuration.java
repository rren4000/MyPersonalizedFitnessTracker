public class ActivityDuration {
    private int move;
    private int exercise;
    private int stand;

    public ActivityDuration(){
        this.move = 0;
        this.exercise = 0;
        this.stand = 0;
    }

    public ActivityDuration(int move, int exercise, int stand){
        this.move = move;
        this.exercise = exercise;
        this.stand = stand;
    }

    //GETTERS
    public int get_move(){return move;}
    public int get_exercise(){return exercise;}
    public int get_stand(){return stand;}

    //SETTERS
    public void set_move(int move){this.move = move;}
    public void set_exercise(int exercise){this.exercise = exercise;}
    public void set_stand(int stand){this.stand = stand;}
  

}
