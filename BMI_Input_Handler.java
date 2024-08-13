import java.util.Scanner;
import java.util.InputMismatchException;

public class BMI_Input_Handler {
    private Scanner scanner;

    public BMI_Input_Handler(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getWeight() {return getIntInput("Weight (in pounds): ");}
    public int getHeight() {return getIntInput("Height (in inches): ");}

    private int getIntInput(String prompt) {
        int input = 0;
        while (true) {
            try {
                System.out.print(prompt);
                input = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("\n\tInvalid input. Please enter a valid integer.");
                scanner.nextLine(); // clear the buffer
            }
        }
        return input;
    }
}
