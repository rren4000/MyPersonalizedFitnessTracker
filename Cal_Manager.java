import java.util.Scanner;

public class Cal_Manager {
    private final Scanner scanner;
    private final Cal_Manager_UI ui;

    public Cal_Manager(Cal_Manager_UI ui, Scanner scanner) {
        this.ui = ui;
        this.scanner = scanner;
    }

    // Display the Calorie Manager Menu
    public void display_cal_manager() {
        ui.displayWelcomeMessage();
        handle_choice();
    }

    // Handles user choice for Calorie Manager
    private void handle_choice() {
        int userChoice = ui.getUserChoice();

        switch (userChoice) {
            case 1:
                ui.clear();
                Cal_Tracker calTracker = new Cal_Tracker();
                calTracker.display_cal_tracker();
                break;

            case 2:
                ui.clear();
                BMR_Calc bmrCalc = new BMR_Calc();
                bmrCalc.display_bmr_calc();
                break;

            case 3:
                ui.clear();
                ui.displayExitMessage();
                break;

            default:
                ui.clear();
                ui.displayInvalidChoiceMessage();
                display_cal_manager(); // Recursively call the menu again for a valid choice
                break;
        }
    }
}
