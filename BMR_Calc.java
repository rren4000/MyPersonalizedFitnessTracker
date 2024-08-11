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
    
    // Constructor
    public BMR_Calc() {
        display_bmr_calc();
        System.out.println("\n----------------------------------------\n");
        System.out.print("Thank you for using the BMR Calculator! Press \"Enter\" to return to the main menu.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    //Displays BMR Calculator
    public void display_bmr_calc(){
        request_information();
        System.out.println("You have a Basal Metabolic Rate (BMR) of " + this.BMR + " calories per day.\n"); 
        System.out.println("You should aim to consume " + this.goal_cal + " calories per day to reach your goal of " + this.goal_weight + " pounds.");
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
            System.out.println("Please enter your sex...");
            System.out.print("\n\tEnter (1) for Female and (2) for Male: ");
            this.sex = scanner.nextInt();
            System.out.println("Please enter the number for the category that best fits your lifestyle...");
            System.out.println("\n\t(1) Sedentary (2) Lightly Active (3) Moderately Active (4) Very Active (5) Super Active");
            System.out.print("\nEnter your choice: ");
            this.activity_level = scanner.nextInt();
        }
        else{
            clear();

            System.out.println("Please enter the following information to calculate your BMR... ");

            Scanner scanner = new Scanner(System.in);

            //SET AGE
            while(true){
                try{
                    System.out.print("\n\tAge: ");
                    this.age = scanner.nextInt();
                    break;
                } catch(InputMismatchException e){
                    System.out.println("\n\tInvalid input. Please enter a valid integer for age.");
                    scanner.nextLine();
                }
            }

            //SET WEIGHT
            while(true){
                try{
                    System.out.print("\tWeight (in pounds): ");
            this.weight = scanner.nextInt();
                    break;
                } catch(InputMismatchException e){
                    System.out.println("\n\tInvalid input. Please enter a valid integer for weight.\n");
                    scanner.nextLine();
                }
            }

            //SET HEIGHT
            while(true){
                try{
                    System.out.print("\tHeight (in inches): ");
                    this.height = scanner.nextInt();
                    break;
                } catch(InputMismatchException e){
                    System.out.println("\n\tInvalid input. Please enter a valid integer for height.\n");
                    scanner.nextLine();
                }
            }


            clear();

            //SET SEX
            while(true){
                try{
                    System.out.println("Please enter your sex...");
                    System.out.print("\n\tEnter (1) for Female and (2) for Male: ");
                    this.sex = scanner.nextInt();
                    break;
                } catch(InputMismatchException e){
                    System.out.println("\n\tInvalid input. Please enter a valid integer for sex.\n");
                    scanner.nextLine();
                }
            }

            clear();

            System.out.println("Please enter the number for the category that best fits your lifestyle...");
            System.out.println("\n\t(1) Sedentary \n\t(2) Lightly Active \n\t(3) Moderately Active \n\t(4) Very Active \n\t(5) Super Active");
 
            //SET LIFESTYLE
            while(true){
                try{
                    System.out.print("\nEnter your choice: ");
                    this.activity_level = scanner.nextInt();
                    break;
                } catch(InputMismatchException e){
                    System.out.println("\nInvalid input. Please enter a valid integer for lifestyle category.");
                    scanner.nextLine();
                }
            }
            
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
            Scanner scanner = new Scanner(System.in);

            while(true){
                try{
                    System.out.print("\nGoal weight (in pounds): ");
                    this.goal_weight = scanner.nextInt();
                    break;
                } catch(InputMismatchException e){
                    System.out.println("\nInvalid input. Please enter a valid integer for goal weight.");
                    scanner.nextLine();
                }
            }
        }

        calculate_bmr();
    }

    //Calculates BMR and goal calories
    public int calculate_bmr(){
        clear();
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
        return BMR;
    }

}
