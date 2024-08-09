import java.util.Scanner;
public class Goals {
    private int weight;
    private int steps;  
    private int activity_duration; // MOVE, EXERCISE & STAND
    public Scanner scanner;

    public Goals(){
        this.weight = 0;
        this.steps = 0;
        this.activity_duration = 0;
        this.scanner = new Scanner(System.in);
    }

    public Goals(int weight, int steps, int activity_duration){
        this.weight = weight;
        this.steps = steps;
        this.activity_duration = activity_duration;
        this.scanner = new Scanner(System.in);
    }

    //MAIN GOAL SETTING MENU
    public void set_goals(){
        Scanner scanner = new Scanner(System.in);
        int continue_loop = 1; 

        while(continue_loop == 1){

            System.out.println("----------------------------------------");
            System.out.println("Welcome to Goal Settings!\n");
            System.out.println("\t(1) Body Weight \n\t(2) Steps \n\t(3) Activity Duration\n");
            System.out.print("Enter the goal you would like to set: ");
            
            int curr_goal = scanner.nextInt();

            System.out.println("\n----------------------------------------\n");

        
            switch(curr_goal){
                //BODY WEIGHT
                case 1:
                System.out.print("Goal Body Weight (in numerical pounds): ");
                int weight_goal = scanner.nextInt();
                this.set_goal_weight(weight_goal);
                break;

                //STEPS
                case 2:
                System.out.print("Goal Step Count (in numerical form): ");
                int step_goal = scanner.nextInt();
                this.set_goal_weight(step_goal);
                break;

                //DURATION
                case 3:
                ActivityDuration act_duration = new ActivityDuration(); 
                System.out.print("Move: ");
                int move = scanner.nextInt();
                act_duration.set_move(move);

                System.out.print("Exercise: ");
                int exercise = scanner.nextInt();
                act_duration.set_exercise(exercise);

                System.out.print("Stand: ");
                int stand = scanner.nextInt(); 
                act_duration.set_stand(stand);
                break;
            }
            System.out.println("\n----------------------------------------");
            scanner.nextLine();
            System.out.println("\nYour goal has been successfuly set. \n\nPress 1 if you would like to set another goal or any other number to go back to the main menu.");
            System.out.println("\n----------------------------------------");
            continue_loop = scanner.nextInt();
            //scanner.close();
    }
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
