import java.util.*;

public class MyFitnessTracker{
    public static void print_and_get_choices(){
        System.out.println("\nMain Window:");
        System.out.println("\t(1) Add User Profile \n\t(2) Track Activities \n\t(3) Calorie Manager \n\t(4) Set Goals \n\t(5) BMI Calculator \n\t(6) Activity Generator \n\t(7) Quit");
        System.out.print("Please Enter Your Choice: ");
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
            System.out.println("Press Enter to return to the main menu, or type 'r' to generate another suggestion.");
            userResponse = scanner.nextLine();
        } while (userResponse.equalsIgnoreCase("r"));
        clear_screen();
    }


    public static void main (String args[]){
        Scanner scanner = new Scanner(System.in);
        int user_choice = 0;
        
        // Opening Message
        //System.out.println("\n\nWelcome to.... \n\n\n\t\tMY PERSONALIZED FITNESS TRACKER\n\n\n\n(Please press enter to proceed....)");
        System.out.println("************************************************");
        System.out.println("* Welcome to....                               *");
        System.out.println("*                                              *");
        System.out.println("*                                              *");
        System.out.println("*        MY PERSONALIZED FITNESS TRACKER       *");
        System.out.println("*                                              *");
        System.out.println("*                                              *");
        System.out.println("*                                              *");
        System.out.println("* (Please press enter to proceed....)          *");
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
                    case 1:
                    // ADD USER PROFILE
                    // CREATE USER OBJECT
                    User curr_user = new User(); 
                    //SET USER OBJECT WITH USER'S INPUT
                    curr_user.set_profile();  // **** NEEDED EDIT ---> SAVE TO FILE ******
                                            // *** I ALSO DON'T CLOSE THE SCANNER BUT PROBS SHOULD ***
                    break;

                    case 2:
                    // TRACK ACTIVITIES
                    clear_screen();
                    ActivityTracker tracker = new ActivityTracker();
                    tracker.activityClassRunnerCode();
                    clear_screen();
                    break;

                    case 3:
                    // CALORIE MANAGER
                    break;

                    //SET GOALS
                    case 4: 
                    break;

                    //BMI CALCULATOR
                    case 5:
                    break;

                    //ACTIVITY GENERATOR
                    case 6:
                        clear_screen();
                        generateActivity(scanner);
                        clear_screen();
                    break;
                }
            } catch(InputMismatchException e) {
                System.out.println("\n\nInvalid input. Please enter a valid choice.");
                scanner.nextLine(); // Clear the invalid input
            }
            }
            if (user_choice == 7){
                clear_screen();
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
