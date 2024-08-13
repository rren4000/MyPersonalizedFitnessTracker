import java.util.Scanner;
import java.util.InputMismatchException;

public class BMI_Calc_Handler {
    private Scanner scanner = new Scanner(System.in);
    private BMI_File_Handler fileHandler;
    private BMI_Display display;

    public BMI_Calc_Handler(BMI_File_Handler fileHandler, BMI_Display display) {
        this.fileHandler = fileHandler;
        this.display = display;
    }

    //VERIFY INPUT IS VALID 
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

    //CLOSING BANNER
    private void closeBMI_Calc() {
        System.out.print("Thank you for using the BMI Calculator! \n\nPress \"Enter\" to return to the main menu.");
        scanner.nextLine(); // Wait for Enter
    }

    public void start() {
        //TRY TO READ BMI INFO FROM FILE... ELSE GET USER INPUT
        int[] data = new int[2]; // [weight, height]

        boolean fileReadSuccess = fileHandler.readProfile(data);
        int weight, height;

        if (fileReadSuccess) {
            weight = data[0];
            height = data[1];
        } else {
            weight = getWeight();
            scanner.nextLine();
            height = getHeight();
            scanner.nextLine();
        }

        //CALCULATE BMI WITH INFO 
        BMI_Calculator calculator = new BMI_Calculator(weight, height);
        double bmi = calculator.calculateBMI();

        //DISPLAY BMI & END 
        display.displayBMI(bmi);
        closeBMI_Calc();
    }

    //GET REQUIRED DATA FROM USER
    private int getWeight() {return getIntInput("Weight (in pounds): ");}
    private int getHeight() {return getIntInput("Height (in inches): ");}
}
