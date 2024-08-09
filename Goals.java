import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Goals {
    private static final String FILE_NAME = "goals.txt";
    private int weight;
    private int steps;  
    private ActivityDuration act_duration;
    public Scanner scanner;

    public Goals(){
        this.weight = 0;
        this.steps = 0;
        this.act_duration = new ActivityDuration(0, 0, 0);
        this.scanner = new Scanner(System.in);
    }

    public Goals(int weight, int steps, int move, int exercise, int stand){
        this.weight = weight;
        this.steps = steps;
        this.act_duration = new ActivityDuration(move, exercise, stand);
        this.scanner = new Scanner(System.in);
    }

    //MAIN GOAL SETTING MENU
    public void set_goals(){
        MyFitnessTracker tracker = new MyFitnessTracker();
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
                this.set_goal_steps(step_goal);
                break;

                //DURATION
                case 3:
                System.out.print("Move (in numerical hours): ");
                int move = scanner.nextInt();
                act_duration.set_move(move);

                System.out.print("Exercise (in numerical hours): ");
                int exercise = scanner.nextInt();
                act_duration.set_exercise(exercise);

                System.out.print("Stand (in numerical hours): ");
                int stand = scanner.nextInt(); 
                act_duration.set_stand(stand);
                break;
            }

            System.out.println("\n----------------------------------------");
            scanner.nextLine();
            System.out.println("\nYour goal has been successfuly set. \n\nPress 1 if you would like to set another goal or any other number to go back to the main menu.");
            System.out.println("\n----------------------------------------");
            continue_loop = scanner.nextInt();
            //tracker.clear_screen();
            //scanner.close();
        }
        saveData();
    }

    //GETTERS
    public int get_goal_weight(){return weight;}
    public int get_goal_steps(){return steps;}
    public String get_goal_activity_duration(){
        String activity_duration = "Move: " + act_duration.get_move() + "\nExercise: " + act_duration.get_move() + "\nStand: " + act_duration.get_stand();
        return activity_duration;
    }

    //SETTERS
    public void set_goal_weight(int weight){this.weight = weight;}
    public void set_goal_steps(int steps){this.steps = steps;}
    public void set_goal_activity_duration(int move, int exercise, int stand){
        this.act_duration.set_move(move);
        this.act_duration.set_exercise(exercise);
        this.act_duration.set_stand(stand);
    }

    // Save data to a file
    private void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            if(this.get_goal_weight() != 0){
                writer.write("Goal Weight: " + this.get_goal_weight());
            }
            writer.newLine();

            if(this.get_goal_steps() != 0){
                writer.write("Goal Steps: " + this.get_goal_steps());
            }
            writer.newLine();

            if(this.act_duration.get_move() != 0 || this.act_duration.get_exercise() != 0 || this.act_duration.get_stand() != 0){
                writer.write("Goal Activity Duration: " + "\n\tMove: " + this.act_duration.get_move() + "\n\tExercise: " + this.act_duration.get_exercise() + "\n\tStand: " + this.act_duration.get_stand());
            }
            writer.newLine();
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
  
}
