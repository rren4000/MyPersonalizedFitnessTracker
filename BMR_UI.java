import java.util.InputMismatchException;
import java.util.Scanner;

public class BMR_UI {
    private Scanner scanner;

    public BMR_UI(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayWelcomeMessage() {
        System.out.println("----------------------------------------\n");
        System.out.println("Welcome to the BMR Calculator!");
        System.out.println("\n----------------------------------------\n");
    }

    public int getIntInput(String prompt) {
        int input = 0;
        while (true) {
            try {
                System.out.print(prompt);
                input = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("\n\tInvalid input. Please enter a valid integer.");
                scanner.nextLine();
            }
        }
        return input;
    }

    public int getOptionInput(String prompt, int min, int max) {
        int input = 0;
        while (true) {
            input = getIntInput(prompt);
            if (input >= min && input <= max) {
                break;
            } else {
                System.out.println("\n\tInvalid input. Please enter a valid option between " + min + " and " + max + ".");
                scanner.nextLine();
            }
        }
        return input;
    }

    public void displayBMRResults(int bmr, int goalCalories) {
        System.out.println("----------------------------------------\n");
        System.out.println("You have a Basal Metabolic Rate (BMR) of " + bmr + " calories per day.");
        System.out.println("You should aim to consume " + goalCalories + " calories per day to reach your goal weight.");
        closeBMRCalc();
    }

    public void closeBMRCalc() {
        System.out.print("Thank you for using the BMR Calculator! \n\nPress \"Enter\" to return to the main menu.");
        scanner.nextLine(); // Wait for user input
        scanner.nextLine(); // Wait for Enter
        System.out.print("\033[H\033[2J");
    }
}
