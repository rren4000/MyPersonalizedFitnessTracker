import java.io.*;
import java.util.*;

public class BMI_Calc {

    private int weight;
    private int height;
    private double bmi;
    private Scanner scanner;

    public BMI_Calc(){
        this.weight = 0;
        this.height = 0;
        this.bmi = 0.0;
        this.scanner = new Scanner(System.in);
    }

    //GETTERS
    public int get_weight(){return weight;}
    public int get_height(){return height;}
    public double get_bmi(){return bmi;}

    //SETTERS
    public void set_weight(int weight){ this.weight = weight;}
    public void set_height(int height){ this.height = height;}
    public void set_bmi(double bmi){ this.bmi = bmi;}

    //CLOSING TITLE
    public void close_BMI_Calc(){
        System.out.print("Thank you for using the BMI Calculator! \n\nPress \"Enter\" to return to the main menu.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    //CLEAR THE CURRENT SCREEN
    public static void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    //DISPLAY OPENING TITLE & USER'S BMI
    public void display_BMI_Calc(){
        System.out.println("----------------------------------------\n");
        System.out.println("Welcome to the BMI Calculator!");
        System.out.println("\n----------------------------------------");

        request_information();

        clear();

        System.out.println("----------------------------------------\n");
        
        System.out.println("Your Body Mass Index (BMI) is: " + this.bmi + "\n");

        //UNDERWEIGHT
        if(this.bmi < 18.5){    
            System.out.println("Your BMI indicates that you are underweight.");
            System.out.println("\n----------------------------------------\n");
            System.out.println("It is advisable to seek medical attention.");
            System.out.println("\n----------------------------------------\n");
        }

        //HEALTHY WEIGHT
        else if(this.bmi >= 18.5 && this.bmi < 24.9){
            System.out.println("Your BMI indicates that you are at a healthy weight.");
            System.out.println("\n----------------------------------------\n");
            System.out.println("Continue eating well and exercising to maintain a healthy lifestyle!");
            System.out.println("\n----------------------------------------\n");
        }

        //OVERWEIGHT
        else if(this.bmi >= 25 && this.bmi < 29.9){
            System.out.println("Your BMI indicated that you are overweight.");
            System.out.println("\n----------------------------------------\n");
            System.out.println("You would from making healthy lifestyle changes. Focus on balanced nutrition and regular physical activity.");
            System.out.println("\n----------------------------------------\n");
        }

        //OBESE
        else{
            System.out.println("Your BMI indicates that you are obese.");
            System.out.println("\n----------------------------------------\n");
            System.out.println("You're in a range where managing your weight could have a significant impact on your overall well-being. \nIt is advisable to enact lifestyle changes or seek medical attention.");
            System.out.println("\n----------------------------------------\n");
        }
    }

    //GET BMI INFO FROM USER OR FILE
    public void request_information(){
        File profile_file = new File("user_profile.txt");
        String[] data = new String[4];

        if(profile_file.exists()){
            try(BufferedReader br = new BufferedReader(new FileReader("user_profile.txt"))){
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.startsWith("Weight:")) {
                        this.weight = Integer.parseInt(line.split(":")[1].trim());
                    } 
                    
                    else if (line.startsWith("Height:")) {
                        this.height = Integer.parseInt(line.split(":")[1].trim());
                    }
                }
            } catch (IOException e) {
                System.out.println("An error occurred while reading the file.");
            }
        }
        else{
            Scanner scanner = new Scanner(System.in);

            System.out.println("\nPlease enter the following information to calculate your BMI... ");
            
            //SET WEIGHT
            while(true){
                try{
                    System.out.print("\n\tWeight (in pounds): ");
                    this.weight = scanner.nextInt();
                    break;
                } catch(InputMismatchException e){
                    System.out.println("\n\tInvalid input. Please enter a valid integer for weight.");
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
        }
        calculate_BMI();
    }
    
    //CALCULATE USER'S BMI
    public double calculate_BMI(){
        this.bmi = ((double)this.weight / (this.height * this.height)) * 703;
        this.bmi = Math.round(this.bmi * 10.0) / 10.0;
        return this.bmi;
    }

    
}
