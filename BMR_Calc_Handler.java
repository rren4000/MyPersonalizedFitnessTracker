import java.util.InputMismatchException;
import java.util.Scanner;

public class BMR_Calc_Handler {
    private Scanner scanner = new Scanner(System.in);
    private BMR_File_Handler fileHandler;
    private BMR_Calculator calculator;

    public BMR_Calc_Handler() {
        this.fileHandler = new BMR_File_Handler();
    }

    // WELCOME BANNER
    private void displayWelcomeMessage() {
        System.out.println("----------------------------------------\n");
        System.out.println("Welcome to the BMR Calculator!");
        System.out.println("\n----------------------------------------\n");
    }

    //VALIDATE DATA TYPE OF INPUT
    private int getIntInput(String prompt) {
        int input = 0;
        while (true) {
            try {
                System.out.print(prompt);
                input = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("\n\tInvalid input. Please enter a valid integer.");
                scanner.nextLine(); // Clear the buffer
            }
        }
        return input;
    }

    private int getOptionInput(String prompt, int min, int max) {
        int input = 0;
        while (true) {
            input = getIntInput(prompt);
            if (input >= min && input <= max) {
                break;
            } else {
                System.out.println("\n\tInvalid input. Please enter a valid option between " + min + " and " + max + ".");
                scanner.nextLine(); // Clear the buffer
            }
        }
        return input;
    }

    // BMR RESULTS
    private void displayBMRResults(int bmr, int goalCalories) {
        System.out.println("----------------------------------------\n");
        System.out.println("You have a Basal Metabolic Rate (BMR) of " + bmr + " calories per day.");
        System.out.println("You should aim to consume " + goalCalories + " calories per day to reach your goal weight.");
        closeBMRCalc();
    }
    // CLOSING BANNER
    private void closeBMRCalc() {
        System.out.print("Thank you for using the BMR Calculator! \n\nPress \"Enter\" to return to the main menu.");
        scanner.nextLine(); // Wait for user input
        scanner.nextLine(); // Wait for Enter
        System.out.print("\033[H\033[2J");
    }

    //MAIN FUNCTION
    public void display_bmr_calc() {
        displayWelcomeMessage();

        // Load data from profile text file
        int[] profileData = fileHandler.loadProfile("user_profile.txt");
        int age = profileData[0];
        int weight = profileData[1];
        int height = profileData[2];
 
        // Get additional input from user if not found in profile text file
        if (age == 0 || weight == 0 || height == 0) {
            age = getIntInput("\n\tAge: ");
            weight = getIntInput("\tWeight (in pounds): ");
            height = getIntInput("\tHeight (in inches): ");
        }

        // Get sex and activity level
        int sex = getOptionInput("\n\tEnter (1) for Female and (2) for Male: ", 1, 2);
        int activityLevel = getOptionInput(
            "\nEnter your activity level (1-Sedentary, 2-Lightly Active, 3-Moderately Active, 4-Very Active, 5-Super Active): ", 
            1, 5);

        // Load goal weight FROM FILE ... ELSE GET USER INPUT
        int goalWeight = fileHandler.loadGoal("goals.txt");
        if (goalWeight == 0) {
            goalWeight = getIntInput("\nGoal weight (in pounds): ");
        }

        // Calculate BMR
        calculator = new BMR_Calculator(weight, height, age, sex, activityLevel, goalWeight);
        calculator.calculateBMR();

        // Display the results
        int BMR = calculator.getBMR();
        int goal_calories = calculator.getGoalCalories();
        displayBMRResults(BMR, goal_calories);
    }
}
