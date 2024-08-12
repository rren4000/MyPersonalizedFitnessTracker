import java.util.Scanner;

public class BMR_Calc {
    private BMR_UI ui;
    private BMR_FileHandler fileHandler;
    private BMR_Calculator calculator;

    public BMR_Calc() {
        Scanner scanner = new Scanner(System.in);
        this.ui = new BMR_UI(scanner);
        this.fileHandler = new BMR_FileHandler();
    }

    public void display_bmr_calc() {
        this.ui.displayWelcomeMessage();
        // Load profile data
        int[] profileData = fileHandler.loadProfile("user_profile.txt");
        int age = profileData[0];
        int weight = profileData[1];
        int height = profileData[2];

        // Get additional input from user if not found in profile
        if (age == 0 || weight == 0 || height == 0) {
            age = ui.getIntInput("\n\tAge: ");
            weight = ui.getIntInput("\tWeight (in pounds): ");
            height = ui.getIntInput("\tHeight (in inches): ");
        }

        // Get sex and activity level
        int sex = ui.getOptionInput("\n\tEnter (1) for Female and (2) for Male: ", 1, 2);
        int activityLevel = ui.getOptionInput(
            "\nEnter your activity level (1-Sedentary, 2-Lightly Active, 3-Moderately Active, 4-Very Active, 5-Super Active): ", 
            1, 5);

        // Load goal weight
        int goalWeight = fileHandler.loadGoal("goals.txt");
        if (goalWeight == 0) {
            goalWeight = ui.getIntInput("\nGoal weight (in pounds): ");
        }

        // Calculate BMR
        calculator = new BMR_Calculator(weight, height, age, sex, activityLevel, goalWeight);
        calculator.calculateBMR();

        // Display the results
        ui.displayBMRResults(calculator.getBMR(), calculator.getGoalCalories());
    }
}
