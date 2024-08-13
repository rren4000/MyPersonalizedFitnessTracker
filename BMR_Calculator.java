public class BMR_Calculator {
    private int weight;
    private int height;
    private int age;
    private int sex;
    private int activityLevel;
    private int goalWeight;
    private int bmr;
    private int goalCalories;

    public BMR_Calculator(int weight, int height, int age, int sex, int activityLevel, int goalWeight) {
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.sex = sex;
        this.activityLevel = activityLevel;
        this.goalWeight = goalWeight;
    }

    public void setWeight(int weight) {this.weight = weight;}
    public void setHeight(int height) {this.height = height;}
    public void setAge(int age) {this.age = age;}
    public void setSex(int sex) {this.sex = sex;}
    public void setActivityLevel(int activityLevel) {this.activityLevel = activityLevel;}
    public void setGoalWeight(int goalWeight) {this.goalWeight = goalWeight;}
    public void setBMR(int bmr) {this.bmr = bmr;}
    public void setGoalCalories(int goalCalories) {this.goalCalories = goalCalories;}

    public int getWeight() {return this.weight;}
    public int getHeight() {return this.height;}
    public int getAge() {return this.age;}
    public int getSex() {return this.sex;}
    public int getActivityLevel() {return this.activityLevel;}
    public int getGoalWeight() {return this.goalWeight;}
    public int getBMR() {return this.bmr;}
    public int getGoalCalories() {return this.goalCalories;}


    public void calculateBMR() {
        // BMR calculation based on sex
        if (this.sex == 2) { // Male
            this.bmr = (int) (66 + (6.23 * this.weight) + (12.7 * this.height) - (6.8 * this.age));
        } else { // Female
            this.bmr = (int) (655 + (4.35 * this.weight) + (4.7 * this.height) - (4.7 * this.age));
        }

        // Adjust BMR based on activity level
        switch (this.activityLevel) {
            case 1:
                this.goalCalories = (int) (this.bmr * 1.2);
                break;
            case 2:
                this.goalCalories = (int) (this.bmr * 1.375);
                break;
            case 3:
                this.goalCalories = (int) (this.bmr * 1.55);
                break;
            case 4:
                this.goalCalories = (int) (this.bmr * 1.725);
                break;
            case 5:
                this.goalCalories = (int) (this.bmr * 1.9);
                break;
        }
        adjustCalories(); // Further adjust calories based on goal weight
    }

    private void adjustCalories() {
        int weightDifference = this.weight - this.goalWeight;
        if (weightDifference <= -40) {this.goalCalories *= 1.4;} 
        else if (weightDifference <= -20 && weightDifference > -40) {this.goalCalories *= 1.3;} 
        else if (weightDifference <= -10 && weightDifference > -20) {this.goalCalories *= 1.2;} 
        else if (weightDifference < 0 && weightDifference > -10) {this.goalCalories *= 1.1;} 
        else if (weightDifference >= 40) {this.goalCalories *= 0.6;} 
        else if (weightDifference >= 20 && weightDifference < 40) {this.goalCalories *= 0.7;} 
        else if (weightDifference >= 10 && weightDifference < 20) {this.goalCalories *= 0.8;} 
        else if (weightDifference > 0 && weightDifference < 10) {this.goalCalories *= 0.9;}
    }
}
