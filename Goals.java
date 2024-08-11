import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Goals {
    //FILE ATTRIBUTES
    private static final String FILE_NAME = "goals.txt";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    //OBJECT ATTRIBUTES
    private int weight;
    private int steps;  
    private ActivityDuration act_duration;

    //GENERAL ATTRIBUTES
    public Scanner scanner;
    private LocalDate date;

    //DEFAULT GOALS CONSTRUCTOR
    public Goals(){
        this.weight = 0;
        this.steps = 0;
        this.act_duration = new ActivityDuration(0, 0, 0);
        this.scanner = new Scanner(System.in);
        this.date = LocalDate.now();
    }

    //PARAMETER GOALS CONSTRUCTOR 
    public Goals(int weight, int steps, int move, int exercise, int stand){
        this.weight = weight;
        this.steps = steps;
        this.act_duration = new ActivityDuration(move, exercise, stand);
        this.scanner = new Scanner(System.in);
        this.date = LocalDate.now();
    }

    //CLEAR THE CURRENT SCREEN 
    public static void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    //MAIN GOAL SETTING MENU
    public void set_goals(){
        Scanner scanner = new Scanner(System.in);
        int continue_loop = 1;  // 1: LOOP OF CREATING GOALS SHOULD CONTINUE... 
                                //2: LOOP SHOULD CEASE AND USER SHOULD GO BACK TO MAIN MENU
        int curr_goal;  //1: USER WANTS TO SET BODY WEIGHT GOAL
                        //2: USER WANTS TO SET STEPS GOAL
                        //3: USER WANTS TO SET ACTIVITY DURATION GOAL 

        while(continue_loop == 1){
            //TITLE & INTRODCUCTION
            System.out.println("----------------------------------------\n");
            System.out.println("Welcome to Goal Settings!\n");
            System.out.println("----------------------------------------\n");
            System.out.println("\t(1) Body Weight \n\t(2) Steps \n\t(3) Activity Duration\n");

            while(true){
                try{
                    System.out.print("Enter the goal you would like to set: ");
                    curr_goal = scanner.nextInt();

                    // CHECK IF INPUT IS VALID
                    if (curr_goal >= 1 && curr_goal <= 3) {break;} 
                    else {System.out.println("\nInvalid input. Please enter 1, 2, or 3.\n");}
                } catch(InputMismatchException e){
                    System.out.println("\nInvalid input. Please enter a valid integer for the goal you would like to set.\n");
                    scanner.nextLine();
                }
            }

            clear();

            System.out.println("----------------------------------------\n");

            switch(curr_goal){
                //BODY WEIGHT
                case 1:
                    while(true){
                        try{
                            System.out.print("Goal Body Weight (in numerical pounds): ");
                            int weight_goal = scanner.nextInt();
                            this.set_goal_weight(weight_goal);
                            break;
                        } catch(InputMismatchException e){
                            System.out.println("\nInvalid input. Please enter a valid integer for weight.\n");
                            scanner.nextLine();
                        }
                    }
                break;

                //STEPS
                case 2:
                    while(true){
                        try{
                            System.out.print("Goal Step Count (in numerical form): ");
                            int step_goal = scanner.nextInt();
                            this.set_goal_steps(step_goal);
                            break;
                        } catch(InputMismatchException e){
                            System.out.println("\nInvalid input. Please enter a valid integer for step count.\n");
                            scanner.nextLine();
                        }
                    }
                break;

                //DURATION
                case 3:
                    //MOVE
                    while(true){
                        try{
                            System.out.print("Move (in numerical hours): ");
                            int move = scanner.nextInt();
                            act_duration.set_move(move);
                            break;
                        } catch(InputMismatchException e){
                            System.out.println("\nInvalid input. Please enter a valid integer for moving hours.\n");
                            scanner.nextLine();
                        }
                    }

                    //EXERCISE
                    while(true){
                        try{
                            System.out.print("Exercise (in numerical hours): ");
                            int exercise = scanner.nextInt();
                            act_duration.set_exercise(exercise);
                            break;
                        } catch(InputMismatchException e){
                            System.out.println("\nInvalid input. Please enter a valid integer for exercising hours.\n");
                            scanner.nextLine();
                        }
                    }

                    //STAND
                    while(true){
                        try{
                            System.out.print("Stand (in numerical hours): ");
                            int stand = scanner.nextInt(); 
                            act_duration.set_stand(stand);
                            break;
                        } catch(InputMismatchException e){
                            System.out.println("\nInvalid input. Please enter a valid integer for standing hours.\n");
                            scanner.nextLine();
                        }
                    }
                break;
            }

            clear();
            scanner.nextLine();

            //PRINT SUCCESS MESSAGE TO USER 
            System.out.println("----------------------------------------\n");
            String result = "Your "; 
            if(curr_goal == 1){result += "weight";}
            else if(curr_goal == 2){result += "step";}
            else if(curr_goal == 3){result += "activity duration";}
            result += " goal has been successfuly set! ";
            System.out.println(result);

            
           
            

            while(true){
                //ADD ANOTHER GOAL OR BRING USER TO BACK TO MAIN MENU
                System.out.println("\nPress 1 if you would like to set another goal or press \"Enter\" to go back to the main menu.\n");
                String input = scanner.nextLine().trim();
                //IF USER JUST PRESSES ENTER... EXIT LOOP & GO BACK TO MAIN MENU
                if(input.isEmpty()){
                    continue_loop = 0;
                    break;
                }
                //IF USER ENTERS 1... CONTINUE LOOP
                else if(input.equals("1")){
                    continue_loop = 1;
                    break;
                }
                //IF USER GIVES INVALID INPUT... ASK THEM AGAIN
                else{System.out.println("\nInvalid Input.  Please press 1 to set another goal or press \"Enter\" to go back to the main menu.");}
            }
            
            clear();
            //scanner.close();
        }
        //SAVE AL OF THE GOALS SET TO THE TEXT FILE "GOALS.TXT"
        saveData();
    }

    //GETTERS
    public int get_goal_weight(){return weight;}
    public int get_goal_steps(){return steps;}
    public String get_goal_activity_duration(){
        String activity_duration = "Move: " + act_duration.get_move() + "\nExercise: " + act_duration.get_move() + "\nStand: " + act_duration.get_stand();
        return activity_duration;
    }
    public LocalDate getDate() {return date;}

    //SETTERS
    public void set_goal_weight(int weight){
        this.weight = weight;
    }
    public void set_goal_steps(int steps){
        this.steps = steps;
        saveData();
    }
    public void set_goal_activity_duration(int move, int exercise, int stand){
        this.act_duration.set_move(move);
        this.act_duration.set_exercise(exercise);
        this.act_duration.set_stand(stand);
    }
    public void setDate(LocalDate date) {this.date = date;}

    // SAVE DATA TO FILE
    private void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(this.date.format(DATE_FORMAT) + ":");
            writer.newLine();
            writer.newLine();

            //IF WEIGHT GOAL IS SET... add to file 
            if(this.get_goal_weight() != 0){writer.write("Goal Weight (in pounds): " + this.get_goal_weight());}
            writer.newLine();

            //IF STEP GOAL IS SET... add to file
            if(this.get_goal_steps() != 0){writer.write("Goal Step Count: " + this.get_goal_steps());}
            writer.newLine();

            //IF ACTIVITY DURATION IS SET... add to file 
            if(this.act_duration.get_move() != 0 || this.act_duration.get_exercise() != 0 || this.act_duration.get_stand() != 0){writer.write("Goal Activity Duration (in hours): " + "\n\tMove: " + this.act_duration.get_move() + "\n\tExercise: " + this.act_duration.get_exercise() + "\n\tStand: " + this.act_duration.get_stand());}
            writer.newLine();

            writer.write("----------------------------------------");
            writer.newLine();
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
  
}
