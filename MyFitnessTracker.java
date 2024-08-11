import java.util.*;

public class MyFitnessTracker{
    public static void print_and_get_choices(){
        System.out.println("----------------------------------------\n");
        System.out.println("Main Window:");
        System.out.println("\n---------------------------------------- ");
        System.out.println("\n\t(1) Add User Profile \n\t(2) Track Activities \n\t(3) Calorie Manager \n\t(4) Set Goals \n\t(5) BMI Calculator \n\t(6) Activity Generator \n\t(7) Quit");
        System.out.print("\nPlease Enter Your Choice: ");
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
            System.out.println(suggestion);
            System.out.println("Press \"Enter\" to return to the main menu, or type 'r' to generate another suggestion.");
            userResponse = scanner.nextLine();
        } while (userResponse.equalsIgnoreCase("r"));
        clear_screen();
    }


    public static void main (String args[]){
        Scanner scanner = new Scanner(System.in);
        int user_choice = 0;
        int user_already_set = 0; // INITALLY 0 AS NO USER HAS BEEN SET 
        User curr_user = new User(); // CREATES AN EMPTY USER OBJECT
        
        // Opening Message
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
        scanner.nextLine();
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
                        //IF USER NOT SET, CREATE A NEW ONE
                        if(user_already_set == 0){
                            clear_screen();
                            clear_screen();
                            curr_user.set_profile(); 
                            clear_screen();
                            user_already_set = 1; 
                        }
                        //ELSE... present user with other options
                        else if(user_already_set == 1){
                            clear_screen();
                            clear_screen();
                            System.out.println("----------------------------------------\n ");
                            System.out.println("Sorry, a user has already been set.  \nIf you would like to set a new user, please restart the program.");
                            System.out.println("If you would like to continue as " + curr_user.get_name() + " press \"Enter\"");
                            System.out.println("\n----------------------------------------");
                            scanner.nextLine();
                            clear_screen();
                        }
                    break;

                    // TRACK ACTIVITIES
                    case 2:
                        clear_screen();
                        clear_screen();
                        ActivityTracker tracker = new ActivityTracker();
                        tracker.activityClassRunnerCode();
                    break;

                    // CALORIE MANAGER
                    case 3:
                        clear_screen();
                        clear_screen();
                        Cal_Manager cal_Manager = new Cal_Manager();
                        clear_screen();
                    break;

                    //SET GOALS
                    case 4: 
                        clear_screen();
                        clear_screen();
                        Goals curr_goals = new Goals();
                        curr_goals.set_goals();
                        clear_screen();
                    break;  

                    //BMI CALCULATOR
                    case 5:
                        clear_screen();
                        clear_screen();
                        BMI_Calc bmi_calc = new BMI_Calc();
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
                System.out.println("\n\nInvalid input. Please enter a valid choice.");
                scanner.nextLine(); // Clear the invalid input
            }
            }
            if (user_choice == 7){
                clear_screen();
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
            scanner.close();
    }
}
