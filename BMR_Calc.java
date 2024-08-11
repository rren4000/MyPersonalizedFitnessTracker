import java.io.*;
import java.util.*;

public class BMR_Calc {

    private int BMR;
    private int weight;
    private int height;
    private int activity_level;
    private int age;
    private int sex;
    private int goal_cal;
    private int goal_weight;
    private Scanner scanner;

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
        this.scanner = new Scanner(System.in);
    }
    
    //GETTERS
    public int get_BMR(){return BMR;}
    public int get_weight(){return weight;}
    public int get_height(){return height;}
    public int get_activity_level(){return activity_level;}
    public int get_age(){return age;}
    public int get_sex(){return sex;}
    public int get_goal_cal(){return goal_cal;}

    //SETTERS
    public void set_BMR(int BMR){this.BMR = BMR;}
    public void set_weight(int weight){this.weight = weight;}
    public void set_height(int height){this.height = height;}
    public void set_activity_level(int activity_level){this.activity_level = activity_level;}
    public void set_age(int age){this.age = age;}
    public void set_sex(int sex){this.sex = sex;}
    public void set_goal_cal(int goal_cal){this.goal_cal = goal_cal;}

    // Close the BMR calculator
    public void close_BMR_calc() {
        System.out.println("\n----------------------------------------\n");
        System.out.print("Thank you for using the BMR Calculator! \n\nPress \"Enter\" to return to the main menu.");
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
        //GET INFO TO CALCULATE BMR
        getInfo();

        clear();

        //PRINT BMR INFO
        System.out.println("----------------------------------------\n");
        System.out.println("You have a Basal Metabolic Rate (BMR) of " + this.BMR + " calories per day.\n");
        System.out.println("You should aim to consume " + this.goal_cal + " calories per day to reach your goal of " + this.goal_weight + " pounds.");
        
        //PRINT CLOSING BANNER
        close_BMR_calc();
    }

    // Gather user information
    public void getInfo() {
        File profile_file = new File("user_profile.txt");

        if (profile_file.exists()) {
            loadProfile(profile_file);
        } else {
            clear();
            System.out.println("Please enter the following information to calculate your BMR... ");
            //SET AGE
            while(true){
                try{
                    System.out.print("\n\tAge: ");
                    this.age = scanner.nextInt();
                    break;
                } catch(InputMismatchException e){
                    clear();
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
                    clear();
                    System.out.println("\n\tInvalid input. Please enter a valid integer for height.\n");
                    scanner.nextLine();
                }
            }
        }

        clear();

        System.out.println("----------------------------------------\n");

        //SET SEX
        while(true){
            try{
                System.out.println("Please enter your sex...");
                System.out.print("\n\tEnter (1) for Female and (2) for Male: ");
                this.sex = scanner.nextInt();

                //CHECK IF INPUT IS VALID
                if (this.sex == 1 || this.sex == 2){break;}
                else{
                    clear();
                    System.out.println("\nInvalid input. Please enter (1) for Female or (2) for Male.\n");}
            } catch(InputMismatchException e){
                clear();
                System.out.println("\n\tInvalid input. Please enter a valid integer for sex.\n");
                scanner.nextLine();
            }
        }

        clear();

        System.out.println("----------------------------------------\n");

        System.out.println("Please enter the number for the category that best fits your lifestyle...");
        System.out.println("\n\t(1) Sedentary \n\t(2) Lightly Active \n\t(3) Moderately Active \n\t(4) Very Active \n\t(5) Super Active");
 
        //SET LIFESTYLE
        while(true){
            try{
                System.out.print("\nEnter your choice: ");
                this.activity_level = scanner.nextInt();
                    
                //CHECK IF INPUT IS VALID
                if(this.activity_level >= 1 && this.activity_level <= 5){break;}
                else{
                    clear();
                    System.out.println("\nInvalid input. Please enter a valid integer between 1 and 5.");
                    System.out.println("\n\t(1) Sedentary \n\t(2) Lightly Active \n\t(3) Moderately Active \n\t(4) Very Active \n\t(5) Super Active");}
            } catch(InputMismatchException e){
                clear();
                System.out.println("\nInvalid input. Please enter a valid integer for lifestyle category.");
                System.out.println("\n\t(1) Sedentary \n\t(2) Lightly Active \n\t(3) Moderately Active \n\t(4) Very Active \n\t(5) Super Active");
                scanner.nextLine();
            }
        }

        File goal_file = new File("goals.txt");
        if (goal_file.exists()) {
            loadGoal(goal_file);
        } else {
            while(true){
                try{
                    System.out.print("\nGoal weight (in pounds): ");
                    this.goal_weight = scanner.nextInt();
                    break;
                } catch(InputMismatchException e){
                    clear();
                    System.out.println("\nInvalid input. Please enter a valid integer for goal weight.");
                    scanner.nextLine();
                }
            }
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
