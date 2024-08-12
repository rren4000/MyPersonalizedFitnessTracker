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

    // Activity Generator Helper Method
    public static void generateActivity(Scanner scanner) {
        ActivityGenerator generator = new ActivityGenerator();
        String suggestion;
        String userResponse;
        do {
            clear_screen();
            suggestion = generator.getRandomActivity();
            System.out.println("----------------------------------------");
            System.out.println(suggestion);
            System.out.println("Press \"Enter\" to return to the main menu, or type 'r' to generate another suggestion.");
            
            //ENSURE VALID INPUT
            do{
                userResponse = scanner.nextLine();
                //IF USER INPUT IS VALID
                if(userResponse.equalsIgnoreCase("r") || userResponse.equals("")){break;}
                //ELSE USER INPUT IS INVALID... REPEAT INSTRUCTIONS
                else{System.out.println("\nInvalid Input.  Please press \"Enter\" to return to the main menu, or type 'r' to generate another suggestion.");}
            } while(true);
        } while (userResponse.equalsIgnoreCase("r"));
        clear_screen();
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
                            clear_screen();
                            curr_user = new User(); // CREATE A NEW USER OBJECT
                            User_Input_Handler userInputHandler = new User_Input_Handler(); // CREATE USER INPUT HANDLER
                            userInputHandler.set_user_profile(curr_user); // SET USER PROFILE DETAILS

                            User_Data_Saver dataSaver = new File_User_Data_Saver(); // CREATE FILE DATA SAVER
                            dataSaver.saveData(curr_user); // SAVE USER PROFILE TO FILE

                            clear_screen();
                            user_already_set = true; // MARK THAT USER PROFILE HAS BEEN SET
                        } else {
                            clear_screen();
                            System.out.println("----------------------------------------\n ");
                            System.out.println("Sorry, a user has already been set.  \n\nIf you would like to set a new user, please restart the program.\n");
                            System.out.println("If you would like to continue as " + curr_user.get_name() + ", please press \"Enter\".");
                            System.out.println("\n----------------------------------------");
                            scanner.nextLine();
                            clear_screen();
                        }
                    break;

                    // TRACK ACTIVITIES
                    case 2:
                        clear_screen();
                        ActivityTracker tracker = new ActivityTracker();
                        tracker.activityClassRunnerCode();
                    break;

                    // CALORIE MANAGER
                    case 3:
                        clear_screen();
                        Cal_Manager_UI calManagerUI = new Cal_Manager_UI(scanner);
                        Cal_Manager calManager = new Cal_Manager(calManagerUI, scanner);
                        calManager.display_cal_manager();
                        clear_screen();
                    break;

                    //SET GOALS
                    case 4:
                        clear_screen();
                        //GOAL OBJECT
                        Goals curr_goals = new Goals();
                        //USER INTERFACE TO SET GOALS 
                        Goal_Input_Handler inputHandler = new Goal_Input_Handler();
                        inputHandler.set_goals(curr_goals);
                        //SAVES GOALS TO FILE 
                        Goal_Data_Saver dataSaver = new File_Goal_Data_Saver();
                        dataSaver.saveData(curr_goals);

                        clear_screen();
                    break;  

                    //BMI CALCULATOR
                    case 5:
                        clear_screen();
                        BMIInputHandler bmiInputHandler = new BMIInputHandler(scanner);
                        BMIFileHandler bmiFileHandler = new BMIFileHandler("user_profile.txt");
                        BMIDisplay bmiDisplay = new BMIDisplay();

                        BMI_Calc bmiCalc = new BMI_Calc(bmiInputHandler, bmiFileHandler, bmiDisplay, scanner);
                        bmiCalc.start();
                        clear_screen();
                    break;

                    //ACTIVITY GENERATOR
                    case 6:
                        clear_screen();
                        clear_screen();
                        clear_screen();
                        generateActivity(scanner);
                        clear_screen();
                    break;
                }
            } catch(InputMismatchException e) {
                clear_screen();
                System.out.println("\n\nInvalid input. Please enter a valid choice."); // ****THIS IS NEVER PRINTED!!!
                scanner.nextLine(); // Clear the invalid input
            }
            }

            //QUIT... clear screen and print closing message
            if (user_choice == 7){
                clear_screen();
                print_closing_msg();
            }
            //THE PROGRAM IS FINISHED... close the scanner
            scanner.close();
    }
}
