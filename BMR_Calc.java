import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BMR_Calc {
    private int BMR;
    private int weight;
    private int height;
    private int activityLevel;
    private int age;
    private int sex;
    private int goalWeight;
    private int goalCalories;

    private Scanner scanner;

    // Default constructor
    public BMR_Calc() {
        this.scanner = new Scanner(System.in);
    }

    public void display_bmr_calc() {
        getInfo();
        calculateBMR();
        adjustCalories();
        clear();
        System.out.println("----------------------------------------\n");
        System.out.println("You have a Basal Metabolic Rate (BMR) of " + this.BMR + " calories per day.");
        System.out.println("You should aim to consume " + this.goalCalories + " calories per day to reach your goal of " + this.goalWeight + " pounds.");
        close_bmr_calc();
    }

    private void getInfo() {
        File profileFile = new File("user_profile.txt");
        File goalFile = new File("goals.txt");

        if (profileFile.exists()) {
            loadProfile(profileFile);
        } else {
            clear();
            System.out.println("Please enter the following information to calculate your BMR...");
            this.age = getIntInput("\n\tAge: ");
            this.weight = getIntInput("\tWeight (in pounds): ");
            this.height = getIntInput("\tHeight (in inches): ");
        }

        clear();

        this.sex = getOptionInput("\n\tEnter (1) for Female and (2) for Male: ", 1, 2);
        clear();

        System.out.println("----------------------------------------\n");
        System.out.println("Please enter the number for the category that best fits your lifestyle...");
        System.out.println("\n\t(1) Sedentary \n\t(2) Lightly Active \n\t(3) Moderately Active \n\t(4) Very Active \n\t(5) Super Active");
        this.activityLevel = getOptionInput("\nEnter your choice: ", 1, 5);

        if (goalFile.exists()) {
            loadGoal(goalFile);
        } else {
            this.goalWeight = getIntInput("\nGoal weight (in pounds): ");
        }
    }

    private void calculateBMR() {
        if (this.sex == 2) { // Male
            this.BMR = (int) (66 + (6.23 * this.weight) + (12.7 * this.height) - (6.8 * this.age));
        } else { // Female
            this.BMR = (int) (655 + (4.35 * this.weight) + (4.7 * this.height) - (4.7 * this.age));
        }

        switch (this.activityLevel) {
            case 1:
                this.goalCalories = (int) (this.BMR * 1.2);
                break;
            case 2:
                this.goalCalories = (int) (this.BMR * 1.375);
                break;
            case 3:
                this.goalCalories = (int) (this.BMR * 1.55);
                break;
            case 4:
                this.goalCalories = (int) (this.BMR * 1.725);
                break;
            case 5:
                this.goalCalories = (int) (this.BMR * 1.9);
                break;
        }
    }

    private void adjustCalories() {
        int weightDifference = this.weight - this.goalWeight;
        if (weightDifference <= -40) {
            this.goalCalories *= 1.4;
        } else if (weightDifference <= -20 && weightDifference > -40) {
            this.goalCalories *= 1.3;
        } else if (weightDifference <= -10 && weightDifference > -20) {
            this.goalCalories *= 1.2;
        } else if (weightDifference < 0 && weightDifference > -10) {
            this.goalCalories *= 1.1;
        } else if (weightDifference >= 40) {
            this.goalCalories *= 0.6;
        } else if (weightDifference >= 20 && weightDifference < 40) {
            this.goalCalories *= 0.7;
        } else if (weightDifference >= 10 && weightDifference < 20) {
            this.goalCalories *= 0.8;
        } else if (weightDifference > 0 && weightDifference < 10) {
            this.goalCalories *= 0.9;
        }
    }

    private void loadProfile(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
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

    private void loadGoal(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Goal Weight (in pounds):")) {
                    this.goalWeight = Integer.parseInt(line.split(":")[1].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getIntInput(String prompt) {
        int input = 0;
        while (true) {
            try {
                System.out.print(prompt);
                input = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                clear();
                System.out.println("\n\tInvalid input. Please enter a valid integer.");
                scanner.nextLine(); // clear the buffer
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
                clear();
                System.out.println("\n\tInvalid input. Please enter a valid option between " + min + " and " + max + ".");
            }
        }
        return input;
    }

    private void close_bmr_calc() {
        System.out.println("\n----------------------------------------\n");
        System.out.print("Thank you for using the BMR Calculator! \n\nPress \"Enter\" to return to the main menu.");
        scanner.nextLine();
        scanner.nextLine();
    }

    private void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
