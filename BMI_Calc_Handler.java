import java.util.Scanner;

public class BMI_Calc_Handler {
    private BMI_Input_Handler inputHandler;
    private BMI_File_Handler fileHandler;
    private BMI_Display display;
    private Scanner scanner; 

    public BMI_Calc_Handler(BMI_Input_Handler inputHandler, BMI_File_Handler fileHandler, BMI_Display display, Scanner scanner) {
        this.inputHandler = inputHandler;
        this.fileHandler = fileHandler;
        this.display = display;
        this.scanner = scanner; 
    }

    public BMI_File_Handler getBMIFileHandler() {return fileHandler;}
    public BMI_Input_Handler getBMIInputHandler() {return inputHandler;}
    public BMI_Display getBMIDisplay() {return display;}
    public Scanner getScanner() {return scanner;}

    public void start() {
        int[] data = new int[2]; // [weight, height]

        boolean fileReadSuccess = fileHandler.readProfile(data);
        int weight, height;

        if (fileReadSuccess) {
            weight = data[0];
            height = data[1];
        } else {
            weight = inputHandler.getWeight();
            scanner.nextLine();
            height = inputHandler.getHeight();
            scanner.nextLine();
        }

        BMI_Calculator calculator = new BMI_Calculator(weight, height);
        double bmi = calculator.calculateBMI();

        display.displayBMI(bmi);
        closeBMI_Calc();
    }

    private void closeBMI_Calc() {
        System.out.print("Thank you for using the BMI Calculator! \n\nPress \"Enter\" to return to the main menu.");
        scanner.nextLine(); // Wait for Enter
        
    }
}
