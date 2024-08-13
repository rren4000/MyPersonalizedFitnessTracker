public class BMI_Display {
    public void displayBMI(double bmi) {
        System.out.println("----------------------------------------\n");
        System.out.println("Your Body Mass Index (BMI) is: " + bmi + "\n");
        displayResult(bmi);
    }

    private void displayResult(double bmi) {
        if (bmi < 18.5) {
            System.out.println("Your BMI indicates that you are underweight.");
            System.out.println("\n----------------------------------------\n");
            System.out.println("It is advisable to seek medical attention.");
            System.out.println("\n----------------------------------------\n");
        } else if (bmi >= 18.5 && bmi < 24.9) {
            System.out.println("Your BMI indicates that you are at a healthy weight.");
            System.out.println("\n----------------------------------------\n");
            System.out.println("Continue eating well and exercising to maintain a healthy lifestyle!");
            System.out.println("\n----------------------------------------\n");
        } else if (bmi >= 25 && bmi < 29.9) {
            System.out.println("Your BMI indicates that you are overweight.");
            System.out.println("\n----------------------------------------\n");
            System.out.println("You would benefit from making healthy lifestyle changes. Focus on balanced nutrition and regular physical activity.");
            System.out.println("\n----------------------------------------\n");
        } else {
            System.out.println("Your BMI indicates that you are obese.");
            System.out.println("\n----------------------------------------\n");
            System.out.println("You're in a range where managing your weight could have a significant impact on your overall well-being. \nIt is advisable to enact lifestyle changes or seek medical attention.");
            System.out.println("\n----------------------------------------\n");
        }
    }
}
