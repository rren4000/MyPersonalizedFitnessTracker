import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
    
    // Constructor
    public BMR_Calc() {
        display_bmr_calc();
        System.out.println("Thank you for using the BMR Calculator! Press Enter to return to the main menu.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    //Displays BMR Calculator
    public void display_bmr_calc(){
        request_information();
        System.out.println("Your Basal Metabolic Rate (BMR) is: " + this.BMR);
        System.out.println("The average amount of calories you should aim to eat per day to reach your goal is: " + this.goal_cal);
    }

    //Requests user information
    public void request_information(){
        File profile_file = new File("user_profile.txt");
        String[] data = new String[4];
        if(profile_file.exists()){
            try(BufferedReader br = new BufferedReader(new FileReader("user_profile.txt"))){
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
                System.out.println("An error occurred while reading the file.");
            }
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your sex: (1) Female (2) Male");
            this.sex = scanner.nextInt();
            System.out.println("Enter your activity level: (1) Sedentary (2) Lightly Active (3) Moderately Active (4) Very Active (5) Super Active");
            this.activity_level = scanner.nextInt();
        }
        else{
            System.out.println("Please enter the following information to calculate your BMR: ");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your age: ");
            this.age = scanner.nextInt();
            System.out.println("Enter your weight (in pounds): ");
            this.weight = scanner.nextInt();
            System.out.println("Enter your height (in inches): ");
            this.height = scanner.nextInt();
            System.out.println("Enter your sex: (1) Female (2) Male");
            this.sex = scanner.nextInt();
            System.out.println("Enter your activity level: (1) Sedentary (2) Lightly Active (3) Moderately Active (4) Very Active (5) Super Active");
            this.activity_level = scanner.nextInt();
        }

        File goal_file = new File("goals.txt");
        if(goal_file.exists()){
            try(BufferedReader br = new BufferedReader(new FileReader("goals.txt"))){
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.startsWith("Goal Weight (in pounds):")) {
                        this.goal_weight = Integer.parseInt(line.split(":")[1].trim());
                    }
                }
            } catch (IOException e) {
                System.out.println("An error occurred while reading the file.");
            }
        }
        else{
            System.out.println("Please enter your goal weight (in pounds): ");
            Scanner scanner = new Scanner(System.in);
            this.goal_weight = scanner.nextInt();
        }

        calculate_bmr();
    }

    //Calculates BMR and goal calories
    public int calculate_bmr(){
        if(this.sex == 2){
            this.BMR = (int) (66 + (6.23 * this.weight) + (12.7 * this.height) - (6.8 * this.age));
        }
        else{
            this.BMR = (int) (655 + (4.35 * this.weight) + (4.7 * this.height) - (4.7 * this.age));
        }
        switch(this.activity_level){
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
        int weight_difference = this.weight - this.goal_weight;
        if(weight_difference <= -40){
            this.goal_cal *= 1.4;
        }
        else if(weight_difference <= -20 && weight_difference > -40){
            this.goal_cal *= 1.3;
        }
        else if(weight_difference <= -10 && weight_difference > -20){
            this.goal_cal *= 1.2;
        }
        else if(weight_difference < 0 && weight_difference > -10){
            this.goal_cal *= 1.1;
        }
        else if(weight_difference >= 40){
            this.goal_cal *= 0.6;
        }
        else if(weight_difference >= 20 && weight_difference < 40){
            this.goal_cal *= 0.7;
        }
        else if(weight_difference >= 10 && weight_difference < 20){
            this.goal_cal *= 0.8;
        }
        else if(weight_difference > 0 && weight_difference < 10){
            this.goal_cal *= 0.9;
        }
        System.out.println("Your goal weight is: " + this.goal_weight);
        return BMR;
    }

}