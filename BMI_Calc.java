import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class BMI_Calc {

    private int weight;
    private int height;
    private double bmi;

    public BMI_Calc(){
        display_bmr_calc();
        System.out.println("Thank you for using the BMI Calculator! Press Enter to return to the main menu.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public void display_bmr_calc(){
        System.out.println("Welcome to the BMI Calculator!");
        request_information();
        System.out.println("Your Body Mass Index (BMI) is: " + this.bmi);
        if(this.bmi < 18.5){
            System.out.println("You are underweight.");
        }
        else if(this.bmi >= 18.5 && this.bmi < 24.9){
            System.out.println("Your BMI suggests that you are currently below the ideal weight range for your height. It might be beneficial to focus on increasing your nutrient intake to support your overall health. A healthcare provider can offer personalized advice to help you reach a healthier weight in a safe and balanced way.");
        }
        else if(this.bmi >= 25 && this.bmi < 29.9){
            System.out.println("Your BMI indicates that you fall into a range that's above the ideal weight for your height, which means you might benefit from making some healthy lifestyle changes. Focusing on balanced nutrition and regular physical activity could help you move towards a healthier weight. It might also be useful to talk with a healthcare provider for personalized advice.");
        }
        else{
            System.out.println("Based on your BMI, it looks like you're in a range where managing your weight could have a significant impact on your overall health and well-being. It might be a good idea to explore some lifestyle changes or speak with a healthcare provider to discuss a plan that works best for you.");
        }
    }

    public void request_information(){
        File profile_file = new File("user_profile.txt");
        String[] data = new String[4];
        if(profile_file.exists()){
            try(BufferedReader br = new BufferedReader(new FileReader("user_profile.txt"))){
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.startsWith("Weight:")) {
                        this.weight = Integer.parseInt(line.split(":")[1].trim());
                    } else if (line.startsWith("Height:")) {
                        this.height = Integer.parseInt(line.split(":")[1].trim());
                    }
                }
            } catch (IOException e) {
                System.out.println("An error occurred while reading the file.");
            }
        }
        else{
            System.out.println("Please enter the following information to calculate your BMI: ");
            System.out.print("Enter your weight in pounds: ");
            Scanner scanner = new Scanner(System.in);
            this.weight = scanner.nextInt();
            System.out.print("Enter your height in inches: ");
            this.height = scanner.nextInt();
        }
        calculate_bmi();
    }
    

    public double calculate_bmi(){
        this.bmi = ((double)this.weight / (this.height * this.height)) * 703;
        this.bmi = Math.round(this.bmi * 10.0) / 10.0;
        return this.bmi;
    }
}
