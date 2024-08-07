import java.util.Scanner;

public class User {
    private String name;
    private int age;
    private int weight;
    private int height;  

    public Scanner scanner;

    public User(){
        Scanner scanner = new Scanner(System.in);
        this.name = ""; 
        this.age = 0;
        this.weight = 0;
        this.height = 0;
    }

    public User(String name, int age, int weight, int height){
        Scanner scanner = new Scanner(System.in);
        this.name = name; 
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    public void set_profile(){
        System.out.println("Welcome to User Settings!");
        System.out.println("Please Enter the Following Information to Create a User Profile...");
        
        System.out.println("Name: ");
        String name = scanner.nextLine();
        this.set_name(name);

        System.out.println("Age (in numerical years): ");
        int age = scanner.nextInt();
        this.set_age(age);

        System.out.println("Weight (in numerical pounds): ");
        int weight = scanner.nextInt();
        this.set_height(weight);

        System.out.println("Height (in numerical inches): ");
        int height = scanner.nextInt();
        this.set_height(height);

        scanner.close();
    }

    //GETTERS
    public String get_name(){return name;}
    public int get_age(){return age;}
    public int get_weight(){return weight;}
    public int get_height(){return height;}

    //SETTERS
    public void set_name(String name){this.name = name;}
    public void set_age(int age){this.age = age;}
    public void set_weight(int weight){this.weight = weight;}
    public void set_height(int height){this.height = height;}

}
