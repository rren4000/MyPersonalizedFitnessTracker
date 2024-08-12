import java.util.InputMismatchException;
import java.util.Scanner;

public class User_Input_Handler {
    private Scanner scanner;

    public User_Input_Handler() {
        this.scanner = new Scanner(System.in);
    }

    public void set_user_profile(User user) {
        System.out.println("----------------------------------------\n");
        System.out.println("Welcome to User Settings!\n");
        System.out.println("----------------------------------------\n");
        System.out.println("Please Enter the Following Information to Create a User Profile...");

        // Set Name
        System.out.print("\n\tName: ");
        String name = scanner.nextLine();
        user.set_name(name);

        // Set Age
        while (true) {
            try {
                System.out.print("\tAge (in numerical years): ");
                int age = scanner.nextInt();
                user.set_age(age);
                break;
            } catch (InputMismatchException e) {
                System.out.println("\n\tInvalid input. Please enter a valid integer for age.\n");
                scanner.nextLine();
            }
        }

        // Set Weight
        while (true) {
            try {
                System.out.print("\tWeight (in numerical pounds): ");
                int weight = scanner.nextInt();
                user.set_weight(weight);
                break;
            } catch (InputMismatchException e) {
                System.out.println("\n\tInvalid input. Please enter a valid integer for weight.\n");
                scanner.nextLine();
            }
        }

        // Set Height
        while (true) {
            try {
                System.out.print("\tHeight (in numerical inches): ");
                int height = scanner.nextInt();
                user.set_height(height);
                break;
            } catch (InputMismatchException e) {
                System.out.println("\n\tInvalid input. Please enter a valid integer for height.\n");
                scanner.nextLine();
            }
        }

        System.out.println("\nYour user profile has been successfully created.");
    }
}
