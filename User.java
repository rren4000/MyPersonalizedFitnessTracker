
import java.io.*;
import java.util.*;

public class User {
    private static final String FILE_NAME = "user_profile.txt";

    private String name;
    private int age;
    private int weight;
    private int height;  

    public Scanner scanner;

    public User(){
        this.name = ""; 
        this.age = 0;
        this.weight = 0;
        this.height = 0;
        this.scanner = new Scanner(System.in);
    }

    public User(String name, int age, int weight, int height){
        this.name = name; 
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.scanner = new Scanner(System.in);
    }

    public static void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void set_profile(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("----------------------------------------\n");
        System.out.println("Welcome to User Settings!\n");
        System.out.println("----------------------------------------\n");
        System.out.println("Please Enter the Following Information to Create a User Profile...");
        
        System.out.print("\tName: ");
        String name = scanner.nextLine();
        this.set_name(name);

        System.out.print("\tAge (in numerical years): ");
        int age = scanner.nextInt();
        this.set_age(age);

        System.out.print("\tWeight (in numerical pounds): ");
        int weight = scanner.nextInt();
        this.set_weight(weight);

        System.out.print("\tHeight (in numerical inches): ");
        int height = scanner.nextInt();
        this.set_height(height);
        
        clear();
        saveData();
        scanner.nextLine();
 
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write("Name: " + this.get_name() + "\nAge: " + this.get_age() + "\nWeight: " + this.get_weight() + "\nHeight: " + this.get_height() + "\n");
            writer.newLine();
            System.out.println("----------------------------------------\n");
            System.out.println("Data saved successfully.");
            System.out.println("\n----------------------------------------");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

}
