import java.util.InputMismatchException;
import java.util.Scanner;

public class Cal_Manager {
    private Cal_Tracker calTracker;
    private BMR_Calc bmrCalc;

    private int user_choice;

    // Constructor
    public Cal_Manager() {
        display_cal_manager();
        cal_manager_choice();
    }

    //CLEAR THE CURRENT SCREEN
    public static void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    //Displays Calorie Manager Menu
    public void display_cal_manager() {
        System.out.println("----------------------------------------\n");
        System.out.println("Welcome to Calorie Manager!");
        System.out.println("\n----------------------------------------\n");
        System.out.println("What would you like to do today?\n\n\t(1) Track Calories\n\t(2) Calculate BMR\n\t(3) Go Back");
    }

    //Handles user choice for Calorie Manager
    public void cal_manager_choice() {
        Scanner scanner = new Scanner(System.in);
        //SET AGE
        while(true){
            try{
                System.out.print("\nPlease enter your choice: "); 
                int user_choice = scanner.nextInt();
                scanner.nextLine();
                this.user_choice = user_choice;
                break;
            } catch(InputMismatchException e){
                System.out.println("\nInvalid input. Please enter a valid integer for your desired action.");
                scanner.nextLine();
            }
        }
        switch (user_choice) {
            //TRACK CALORIES 
            case 1:
                this.calTracker = new Cal_Tracker();
                break;
            //CALCULATE BMR
            case 2:
                this.bmrCalc = new BMR_Calc();
                break;
            //RETURN TO MAIN MENU
            case 3:
                System.out.println("Going back to main menu...");
                break;
            default:
                clear();
                System.out.println("----------------------------------------\n");
                System.out.println("Invalid choice. Please try again.\n");
                display_cal_manager();
                cal_manager_choice();
                break;
        }
    }

}
