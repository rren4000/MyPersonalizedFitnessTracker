import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BMR_Calc {

    private int BMR;
    private int weight;
    private int height;
    private int activity_level;
    private int age;
    private int sex;
    private int goal_cal;
    private int goal_weight;
    private Scanner scanner = new Scanner(System.in);

    // Constructor to initialize default values
    public BMR_Calc() {
        this.BMR = 0;
        this.weight = 0;
        this.height = 0;
        this.activity_level = 0;
        this.age = 0;
        this.sex = 0;
        this.goal_cal = 0;
        this.goal_weight = 0;
    }

    // Close the BMR calculator
    public void close_BMR_calc() {
        System.out.println("\n----------------------------------------\n");
        System.out.print("Thank you for using the BMR Calculator! Press \"Enter\" to return to the main menu.");
        this.scanner.nextLine();
        this.scanner.nextLine();
    }

    // Clear the console
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Display the BMR calculator
    public void display_bmr_calc() {
        getInfo();
        System.out.println("----------------------------------------\n");
        System.out.println("You have a Basal Metabolic Rate (BMR) of " + this.BMR + " calories per day.\n");
        System.out.println("You should aim to consume " + this.goal_cal + " calories per day to reach your goal of " + this.goal_weight + " pounds.");
    }

    // Gather user information
    public void getInfo() {
        File profile_file = new File("user_profile.txt");

        if (profile_file.exists()) {
            loadProfile(profile_file);
        } else {
            clear();
            System.out.println("Please enter the following information to calculate your BMR... ");
            this.age = getInput("Age: ");
            this.weight = getInput("Weight (in pounds): ");
            this.height = getInput("Height (in inches): ");
        }

        this.sex = getInput("Enter (1) for Female and (2) for Male: ", 1, 2);
        this.activity_level = getInput("Enter your lifestyle choice: \n\t(1) Sedentary \n\t(2) Lightly Active \n\t(3) Moderately Active \n\t(4) Very Active \n\t(5) Super Active", 1, 5);

        File goal_file = new File("goals.txt");
        if (goal_file.exists()) {
            loadGoal(goal_file);
        } else {
            this.goal_weight = getInput("Goal weight (in pounds): ");
        }

        calculateBMR();
    }

    // Load profile information from a file
    private void loadProfile(File profile_file) {
        try (BufferedReader br = new BufferedReader(new FileReader(profile_file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Age:")) {
                    this.age = Integer.parseInt(line.split(":")[1].trim());
                } else if (line.startsWith("Weight:")) {
                    this.weight = Integer.parseInt(line.split(":")[1].trim());
                } else if (line.startsWith("Height:")) {
                    this.height = Integer.parseInt(line.split(":")[1].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load goal information from a file
    private void loadGoal(File goal_file) {
        try (BufferedReader br = new BufferedReader(new FileReader(goal_file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Goal Weight (in pounds):")) {
                    this.goal_weight = Integer.parseInt(line.split(":")[1].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get input from the user
    private int getInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return this.scanner.nextInt();
            } catch (InputMismatchException e) {
                e.printStackTrace();
                this.scanner.nextLine();
            }
        }
    }

    // Get input from the user within a specific range
    private int getInput(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = this.scanner.nextInt();
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Invalid input. Please enter a valid integer between " + min + " and " + max + ".");
                }
            } catch (InputMismatchException e) {
                e.printStackTrace();
                this.scanner.nextLine();
            }
        }
    }

    // Calculate BMR and set goal calories
    public int calculateBMR() {
        clear();
        if (this.sex == 2) {
            this.BMR = (int) (66 + (6.23 * this.weight) + (12.7 * this.height) - (6.8 * this.age));
        } else {
            this.BMR = (int) (655 + (4.35 * this.weight) + (4.7 * this.height) - (4.7 * this.age));
        }
        switch (this.activity_level) {
            case 1:
                this.goal_cal = (int) (this.BMR * 1.2);
                break;
            case 2:
                this.goal_cal = (int) (this.BMR * 1.375);
                break;
            case 3:
                this.goal_cal = (int) (this.BMR * 1.55);
                break;
            case 4:
                this.goal_cal = (int) (this.BMR * 1.725);
                break;
            case 5:
                this.goal_cal = (int) (this.BMR * 1.9);
                break;
        }
        adjustCalories();
        return BMR;
    }

    // Adjust goal calories based on the weight difference
    private void adjustCalories() {
        int weight_difference = this.weight - this.goal_weight;
        if (weight_difference <= -40) {
            this.goal_cal *= 1.4;
        } else if (weight_difference <= -20 && weight_difference > -40) {
            this.goal_cal *= 1.3;
        } else if (weight_difference <= -10 && weight_difference > -20) {
            this.goal_cal *= 1.2;
        } else if (weight_difference < 0 && weight_difference > -10) {
            this.goal_cal *= 1.1;
        } else if (weight_difference >= 40) {
            this.goal_cal *= 0.6;
        } else if (weight_difference >= 20 && weight_difference < 40) {
            this.goal_cal *= 0.7;
        } else if (weight_difference >= 10 && weight_difference < 20) {
            this.goal_cal *= 0.8;
        } else if (weight_difference > 0 && weight_difference < 10) {
            this.goal_cal *= 0.9;
        }
    }
}
