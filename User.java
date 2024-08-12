
import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.*;

public class User {
    private static final String FILE_NAME = "user_profile.txt";

    private String name;
    private int age;
    private int weight;
    private int height;  

    //public Scanner scanner;
    public Scanner scanner = new Scanner(System.in);

    //DEFAULT USER CONSTRUCTOR
    public User(){
        this.name = ""; 
        this.age = 0;
        this.weight = 0;
        this.height = 0;
        //this.scanner = new Scanner(System.in);
    }

    //PARAMETER USER CONSTRUCTOR 
    public User(String name, int age, int weight, int height){
        this.name = name; 
        this.age = age;
        this.weight = weight;
        this.height = height;
        //this.scanner = new Scanner(System.in);
    }

    //CLEAR THE CURRENT SCREEN
    public static void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    //SET USER PROFILE WITH USER INPUT 
    public void set_profile(){
        //Scanner scanner = new Scanner(System.in);
        //this.scanner = new Scanner(System.in);

        //TITLE & INTRODCUCTION 
        System.out.println("----------------------------------------\n");
        System.out.println("Welcome to User Settings!\n");
        System.out.println("----------------------------------------\n");
        System.out.println("Please Enter the Following Information to Create a User Profile...");
        
        //SET NAME
        System.out.print("\n\tName: ");
        String name = scanner.nextLine();
        this.set_name(name);

        //SET AGE
        while(true){
            try{
                System.out.print("\tAge (in numerical years): ");
                int age = scanner.nextInt();
                this.set_age(age);
                break;
            } catch(InputMismatchException e){
                System.out.println("\n\tInvalid input. Please enter a valid integer for age.\n");
                scanner.nextLine();
            }
        }

        //SET WEIGHT
        while(true){
            try{
                System.out.print("\tWeight (in numerical pounds): ");
                int weight = scanner.nextInt();
                this.set_weight(weight);
                break;
            } catch(InputMismatchException e){
                System.out.println("\n\tInvalid input. Please enter a valid integer for weight.\n");
                scanner.nextLine();
            }
        }

        //SET HEIGHT
        while(true){
            try{
                System.out.print("\tHeight (in numerical inches): ");
                int height = scanner.nextInt();
                this.set_height(height);
                break;
            } catch(InputMismatchException e){
                System.out.println("\n\tInvalid input. Please enter a valid integer for height.\n");
                scanner.nextLine();
            }
        }
        
        //CLEAR CURRENT SCREEN
        clear();

        //SAVE USER DATA TO "USER_PROFILE.TXT" FILE
        saveData();
        scanner.nextLine();
        
        //BRING USER BACK TO MAIN MENU
        System.out.println("\nWelcome " + name + "!");
        System.out.print("\nYour user profile has been successfully created. \n\nPlease press \"Enter\" to go back to the main menu. ");
        scanner.nextLine();
        clear();
        //scanner.close();
    }

    //GETTERS
    public String get_name(){return name;}
    public int get_age(){return age;}
    public int get_weight(){return weight;}
    public int get_height(){return height;}

    //SETTERS
    public void set_name(String name){ this.name = name;}
    public void set_age(int age){this.age = age;}
    public void set_weight(int weight){this.weight = weight;}
    public void set_height(int height){this.height = height;}

    // SAVE DATA TO FILE
    private void saveData() {
        clear();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, false))) {
            //WRITE USER INFO TO THE TEXT FILE 
            writer.write("Name: " + this.get_name() + "\nAge: " + this.get_age() + "\nWeight: " + this.get_weight() + "\nHeight: " + this.get_height() + "\n");
            writer.newLine();

            //CONFIRM TO USER THAT DATA WAS SAVED TO FILE 
            System.out.println("----------------------------------------\n");
            System.out.println("Data saved successfully.");
            System.out.println("\n----------------------------------------");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

}
