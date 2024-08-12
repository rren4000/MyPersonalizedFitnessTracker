import java.util.Scanner;

public class BMI_Calc {
    private BMIInputHandler inputHandler;
    private BMIFileHandler fileHandler;
    private BMIDisplay display;
    private Scanner scanner; 

    public BMI_Calc(BMIInputHandler inputHandler, BMIFileHandler fileHandler, BMIDisplay display, Scanner scanner) {
        this.inputHandler = inputHandler;
        this.fileHandler = fileHandler;
        this.display = display;
        this.scanner = scanner; 
    }

    public BMIFileHandler getBMIFileHandler() {return fileHandler;}
    public BMIInputHandler getBMIInputHandler() {return inputHandler;}
    public BMIDisplay getBMIDisplay() {return display;}
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
            height = inputHandler.getHeight();
        }

        BMICalculator calculator = new BMICalculator(weight, height);
        double bmi = calculator.calculateBMI();

        display.displayBMI(bmi);
        closeBMI_Calc();
    }

    private void closeBMI_Calc() {
        System.out.print("Thank you for using the BMI Calculator! \n\nPress \"Enter\" to return to the main menu.");
        scanner.nextLine();
        scanner.nextLine();  // Wait for the user to press Enter
        
    }
}
