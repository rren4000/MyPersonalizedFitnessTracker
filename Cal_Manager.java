
public class Cal_Manager {
    private final Cal_Manager_UI ui;

    public Cal_Manager(Cal_Manager_UI ui) {
        this.ui = ui;
    }

    // Display the Calorie Manager Menu
    public void display_cal_manager() {
        ui.displayWelcomeMessage(); // WELCOME BANNER
        handle_choice(); // DECIDE WHICH TYPE OF CALORIE MANAGING 
    }

    // Handles user choice for Calorie Manager
    private void handle_choice() {
        int userChoice = ui.getUserChoice();

        switch (userChoice) {
            //CALORIE TRACKER
            case 1:
                System.out.print("\033[H\033[2J");
                Cal_Tracker calTracker = new Cal_Tracker();
                calTracker.display_cal_tracker();
                break;
            //CALCULATE BMR
            case 2:
                System.out.print("\033[H\033[2J");
                BMR_Calc_Handler bmrCalc = new BMR_Calc_Handler();
                bmrCalc.display_bmr_calc();
                break;
            //GO BACK TO MAIN MENU
            case 3:
                System.out.print("\033[H\033[2J");
                System.out.println("Going back to the main menu...");
                break;
            default:
                System.out.print("\033[H\033[2J");
                System.out.println("Invalid choice. Please try again.");
                display_cal_manager(); // Recursively call the menu again for a valid choice
                break;
        }
    }
}
