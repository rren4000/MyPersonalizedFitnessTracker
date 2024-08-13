import java.util.*;

public class MyFitnessTracker{
    private static boolean user_already_set = false;

    //PRINTS OPENING MESSAGE OF THE PROGRAM
    public static void print_opening_msg(){
        System.out.println("\n\n");
        System.out.println("************************************************");
        System.out.println("* Welcome to....                               *");
        System.out.println("*                                              *");
        System.out.println("*                                              *");
        System.out.println("*        MY PERSONALIZED FITNESS TRACKER       *");
        System.out.println("*                                              *");
        System.out.println("*                                              *");
        System.out.println("*                                              *");
        System.out.println("* (Please press \"Enter\" to proceed....)        *");
        System.out.println("************************************************");
    }

    //PRINTS ALL OF THE ACTIONS THE USER CAN TAKE
    public static void print_and_get_choices(){
        System.out.println("----------------------------------------\n");
        System.out.println("Main Window:");
        System.out.println("\n---------------------------------------- ");
        System.out.println("\n\t(1) Add User Profile \n\t(2) Track Activities \n\t(3) Calorie Manager \n\t(4) Set Goals \n\t(5) BMI Calculator \n\t(6) Activity Generator \n\t(7) Quit");
        System.out.print("\nPlease Enter Your Choice: ");
    }

    //PRINTS THE CLOSING MESSAGE OF THE PROGRAM
    public static void print_closing_msg(){
        System.out.println("\n\n");
        System.out.println("************************************************");
        System.out.println("* Thank you for using....                      *");
        System.out.println("*                                              *");
        System.out.println("*                                              *");
        System.out.println("*        MY PERSONALIZED FITNESS TRACKER       *");
        System.out.println("*                                              *");
        System.out.println("*                                              *");
        System.out.println("*                                              *");
        System.out.println("************************************************");
    }

    // ADD USER PROFILE ... CASE 1
    public static void add_user(Scanner scanner, User curr_user) {
        if (!user_already_set) {
            // CLEAR SCREEN
            System.out.print("\033[H\033[2J");
            curr_user = new User(); // CREATE A NEW USER OBJECT
            User_Input_Handler userInputHandler = new User_Input_Handler(); // CREATE USER INPUT HANDLER
            userInputHandler.set_user_profile(curr_user); // SET USER PROFILE DETAILS

            User_Data_Saver dataSaver = new File_User_Data_Saver(); // CREATE FILE DATA SAVER
            dataSaver.saveData(curr_user); // SAVE USER PROFILE TO FILE

            System.out.print("\n(Press \"Enter\" to continue...)");
            scanner.nextLine();

            // CLEAR SCREEN
            System.out.print("\033[H\033[2J");
            user_already_set = true; // MARK THAT USER PROFILE HAS BEEN SET
        } else {
            // CLEAR SCREEN
            System.out.print("\033[H\033[2J");
            System.out.println("----------------------------------------\n ");
            System.out.println("Sorry, a user has already been set.  \n\nIf you would like to set a new user, please restart the program.\n");
            System.out.println("If you would like to continue as the current user, please press \"Enter\".");
            System.out.println("\n----------------------------------------");
            scanner.nextLine();
            // CLEAR SCREEN
            System.out.print("\033[H\033[2J");
        }
    }

    // TRACK ACTIVITIES ... CASE 2
    public static void track_activities() {
        // CLEAR SCREEN
        System.out.print("\033[H\033[2J");
        ActivityTracker tracker = new ActivityTracker();
        tracker.activityClassRunnerCode();
    }

    // CALORIE MANAGER FUNCTION ... CASE 3
    public static void manage_calories() {
        // CLEAR SCREEN
        System.out.print("\033[H\033[2J");
        Cal_Manager_UI calManagerUI = new Cal_Manager_UI();
        Cal_Manager calManager = new Cal_Manager(calManagerUI);
        calManager.display_cal_manager();
        // CLEAR SCREEN
        System.out.print("\033[H\033[2J");
    }

    // SET GOALS ... CASE 4
    public static void set_goals() {
        // CLEAR SCREEN
        System.out.print("\033[H\033[2J");
        Goals curr_goals = new Goals();
        Goal_Input_Handler inputHandler = new Goal_Input_Handler();
        inputHandler.set_goals(curr_goals);
        Goal_Data_Saver dataSaver = new File_Goal_Data_Saver();
        dataSaver.saveData(curr_goals);
        // CLEAR SCREEN
        System.out.print("\033[H\033[2J");
    }

    // BMI CALCULATOR ... CASE 5
    public static void calculate_bmi() {
        // CLEAR SCREEN
        System.out.print("\033[H\033[2J");
        //BMI_Input_Handler bmiInputHandler = new BMI_Input_Handler(scanner);
        BMI_File_Handler bmiFileHandler = new BMI_File_Handler("user_profile.txt");
        BMI_Display bmiDisplay = new BMI_Display();
        BMI_Calc_Handler bmiCalc = new BMI_Calc_Handler(bmiFileHandler, bmiDisplay);
        bmiCalc.start();
        // CLEAR SCREEN
        System.out.print("\033[H\033[2J");
    }

    // ACTIVITY GENERATOR ... CASE 6
    public static void generate_activity(Scanner scanner) {
        // CLEAR SCREEN
        System.out.print("\033[H\033[2J");
        ActivityGenerator.generateActivity(scanner);
        // CLEAR SCREEN
        System.out.print("\033[H\033[2J");
    }


    public static void main (String args[]){
        Scanner scanner = new Scanner(System.in);
        int user_choice = 0;//INITALLY 0 AS THE USER HAS YET TO SET THEIR DESIRED ACTION
                            // 1: ADD USER PROFILE
                            //2: TRACK ACTIVITES
                            //3: CALORIE MANAGER
                            //4: SET GOALS
                            //5: BMI CALCULATOR
                            //6: ACTIVITY GENERATOR
                            //7: QUIT
        User curr_user = new User(); // CREATES AN EMPTY USER OBJECT
        
        // Opening Message
        print_opening_msg();
        scanner.nextLine();

        //CLEAR THE CURRENT SCREEN TO SHOW MENU OF ACTION OPTIONS
        System.out.print("\033[H\033[2J");

        // LOOP RUNS UNTIL USER CHOOSES TO EXIT
        while (user_choice != 7) {
            //PRINT ALL OPTIONS TO THE USER AND USE INPUT TO DETERMINE NEXT ACTION
            print_and_get_choices();

            try{
                user_choice = scanner.nextInt();
                scanner.nextLine();

                switch (user_choice) {
                    case 1:
                        add_user(scanner, curr_user);
                        break;

                    case 2:
                        track_activities();
                        break;

                    case 3:
                        manage_calories();
                        break;

                    case 4:
                        set_goals();
                        break;

                    case 5:
                        calculate_bmi();
                        break;

                    case 6:
                        generate_activity(scanner);
                        break;
                }
            } catch(InputMismatchException e) {
                // CLEAR SCREEN
                System.out.print("\033[H\033[2J");
                System.out.println("\n\nInvalid input. Please enter a valid choice."); // ****THIS IS NEVER PRINTED!!!
                scanner.nextLine(); // Clear the invalid input
            }
            }

            //QUIT... clear screen and print closing message
            if (user_choice == 7){
                // CLEAR SCREEN
                System.out.print("\033[H\033[2J");
                print_closing_msg();
            }
            //THE PROGRAM IS FINISHED... close the scanner
            scanner.close();
    }
}
