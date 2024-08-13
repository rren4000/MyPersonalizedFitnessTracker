public class BMI_Calculator {
    private int weight;
    private int height;

    public BMI_Calculator(int weight, int height) {
        this.weight = weight;
        this.height = height;
    }

    public void setWeight(int weight) {this.weight = weight;}
    public void setHeight(int height) {this.height = height;}

    public double calculateBMI() {
        double bmi = ((double) weight / (height * height)) * 703;
        return Math.round(bmi * 10.0) / 10.0;
    }
}
