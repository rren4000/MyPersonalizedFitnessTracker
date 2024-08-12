import java.util.*;

public class MyFitnessTracker{
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

    // method to help clear the screen (terminal) --- needs more work when implementing within code
    public static void clear_screen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
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
        boolean user_already_set = false; // INITALLY FALSE AS NO USER HAS BEEN SET 
        User curr_user = new User(); // CREATES AN EMPTY USER OBJECT
        
        // Opening Message
        print_opening_msg();
        scanner.nextLine();

        //CLEAR THE CURRENT SCREEN TO SHOW MENU OF ACTION OPTIONS
        clear_screen();

        // LOOP RUNS UNTIL USER CHOOSES TO EXIT
        while (user_choice != 7) {
            //PRINT ALL OPTIONS TO THE USER AND USE INPUT TO DETERMINE NEXT ACTION
            print_and_get_choices();

            try{
                user_choice = scanner.nextInt();
                scanner.nextLine();

                switch (user_choice) {
                    // ADD USER PROFILE
                    case 1:
                        if (!user_already_set) {
                            ClearConsole.clear();
                            curr_user = new User(); // CREATE A NEW USER OBJECT
                            User_Input_Handler userInputHandler = new User_Input_Handler(); // CREATE USER INPUT HANDLER
                            userInputHandler.set_user_profile(curr_user); // SET USER PROFILE DETAILS
                            
                            User_Data_Saver dataSaver = new File_User_Data_Saver(); // CREATE FILE DATA SAVER
                            dataSaver.saveData(curr_user); // SAVE USER PROFILE TO FILE

                            ClearConsole.clear();
                            user_already_set = true; // MARK THAT USER PROFILE HAS BEEN SET
                        } else {
                            ClearConsole.clear();
                            System.out.println("----------------------------------------\n ");
                            System.out.println("Sorry, a user has already been set.  \n\nIf you would like to set a new user, please restart the program.\n");
                            System.out.println("If you would like to continue as " + curr_user.get_name() + ", please press \"Enter\".");
                            System.out.println("\n----------------------------------------");
                            scanner.nextLine();
                            ClearConsole.clear();
                        }
                    break;

                    // TRACK ACTIVITIES
                    case 2:
                        ClearConsole.clear();
                        ActivityTracker tracker = new ActivityTracker();
                        tracker.activityClassRunnerCode();
                    break;

                    // CALORIE MANAGER
                    case 3:
                        ClearConsole.clear();
                        Cal_Manager_UI calManagerUI = new Cal_Manager_UI(scanner);
                        Cal_Manager calManager = new Cal_Manager(calManagerUI, scanner);
                        calManager.display_cal_manager();
                        ClearConsole.clear();
                    break;

                    //SET GOALS
                    case 4:
                        ClearConsole.clear();
                        //GOAL OBJECT
                        Goals curr_goals = new Goals();
                        //USER INTERFACE TO SET GOALS 
                        Goal_Input_Handler inputHandler = new Goal_Input_Handler();
                        inputHandler.set_goals(curr_goals);
                        //SAVES GOALS TO FILE 
                        Goal_Data_Saver dataSaver = new File_Goal_Data_Saver();
                        dataSaver.saveData(curr_goals);

                        ClearConsole.clear();
                    break;  

                    //BMI CALCULATOR
                    case 5:
                        ClearConsole.clear();
                        BMIInputHandler bmiInputHandler = new BMIInputHandler(scanner);
                        BMIFileHandler bmiFileHandler = new BMIFileHandler("user_profile.txt");
                        BMIDisplay bmiDisplay = new BMIDisplay();

                        BMI_Calc bmiCalc = new BMI_Calc(bmiInputHandler, bmiFileHandler, bmiDisplay, scanner);
                        bmiCalc.start();
                        ClearConsole.clear();
                    break;

                    //ACTIVITY GENERATOR
                    case 6:
                        ClearConsole.clear();
                        ActivityGenerator.generateActivity(scanner);
                        ClearConsole.clear();
                    break;
                }
            } catch(InputMismatchException e) {
                ClearConsole.clear();
                System.out.println("\n\nInvalid input. Please enter a valid choice."); // ****THIS IS NEVER PRINTED!!!
                scanner.nextLine(); // Clear the invalid input
            }
            }

            //QUIT... clear screen and print closing message
            if (user_choice == 7){
                ClearConsole.clear();
                print_closing_msg();
            }
            //THE PROGRAM IS FINISHED... close the scanner
            scanner.close();
    }
}
