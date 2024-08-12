import java.util.*;

public class Cal_Manager {
    private Cal_Tracker calTracker;
    private BMR_Calc bmrCalc;

    private int user_choice;

    private Scanner scanner;

    // Constructor
    public Cal_Manager() {
        this.user_choice = 0;
        this.scanner = new Scanner(System.in); 
    }

    // GETTER
    public int get_user_choice(){ return user_choice; }

    // SETTER
    public void set_user_choice(int user_choice){ this.user_choice = user_choice; }

    // CLEAR THE CURRENT SCREEN
    public static void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Displays Calorie Manager Menu
    public void display_cal_manager() {
        System.out.println("----------------------------------------\n");
        System.out.println("Welcome to Calorie Manager!");
        System.out.println("\n----------------------------------------\n");
        System.out.println("What would you like to do today?\n\n\t(1) Track Calories\n\t(2) Calculate BMR\n\t(3) Go Back");

        // HANDLE USER CHOICE
        handle_choice();
    }

    // Handles user choice for Calorie Manager
    private void handle_choice() {
        this.user_choice = get_valid_choice();

        switch (user_choice) {
            // TRACK CALORIES 
            case 1:
                clear();
                this.calTracker = new Cal_Tracker();
                this.calTracker.display_cal_tracker();
                break;
            // CALCULATE BMR
            case 2:
                clear();
                this.bmrCalc = new BMR_Calc();
                this.bmrCalc.display_bmr_calc();
                clear();
                break;
            // RETURN TO MAIN MENU
            case 3:
                clear();
                System.out.println("Going back to main menu...");
                break;
            default:
                clear();
                System.out.println("----------------------------------------\n");
                System.out.println("Invalid choice. Please try again.\n");
                display_cal_manager();
                break;
        }
    }

    // Get a valid choice from the user
    private int get_valid_choice() {
        int choice = 0;
        while (true) {
            try {
                System.out.print("\nPlease enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();

                if (choice >= 1 && choice <= 3) {
                    break;
                } else {
                    System.out.println("\nInvalid input. Please enter 1, 2, or 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter a valid integer for your desired action.");
                scanner.nextLine();
            }
        }
        return choice;
    }
}