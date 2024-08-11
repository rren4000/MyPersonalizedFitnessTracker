import java.util.InputMismatchException;
import java.util.Scanner;

public class Cal_Manager {
    private Cal_Tracker calTracker;
    private BMR_Calc bmrCalc;

    private int user_choice;

    // Constructor
    public Cal_Manager() {
        this.user_choice = 0;
    }

    //Displays Calorie Manager Menu
    public void display_cal_manager() {
        System.out.println("----------------------------------------\n");
        System.out.println("Welcome to Calorie Manager!");
        System.out.println("\n----------------------------------------\n");
        System.out.println("What would you like to do today?\n\n\t(1) Track Calories\n\t(2) Calculate BMR\n\t(3) Go Back");
    }

    public void close_Cal_manager(){

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
            case 1:
                this.calTracker = new Cal_Tracker();
                this.calTracker.display_cal_tracker();
                this.calTracker.close_Cal_tracker();
                break;
            case 2:
                this.bmrCalc = new BMR_Calc();
                this.bmrCalc.display_bmr_calc();
                this.bmrCalc.close_BMR_calc();
                break;
            case 3:
                System.out.println("Going back to main menu...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                display_cal_manager();
                cal_manager_choice();
                break;
        }
    }

}
