import java.util.Scanner;

public class Cal_Manager {
    private Cal_Tracker calTracker;
    private BMR_Calc bmrCalc;

    public String cal_manager_display = "This is the Calorie Manager, what would you like to do today?\n\t(1) Track Calories\n\t(2) Calculate BMR\n\t(3) Go Back\nPlease enter your choice: ";
    private int user_choice;

    // Constructor
    public Cal_Manager() {
        display_cal_manager();
        cal_manager_choice();
    }

    //Displays Calorie Manager Menu
    public void display_cal_manager() {
        System.out.print(cal_manager_display);
    }

    //Handles user choice for Calorie Manager
    public void cal_manager_choice() {
        Scanner scanner = new Scanner(System.in);
        int user_choice = scanner.nextInt();
        scanner.nextLine();
        this.user_choice = user_choice;
        switch (user_choice) {
            case 1:
                this.calTracker = new Cal_Tracker();
                break;
            case 2:
                this.bmrCalc = new BMR_Calc();
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