import java.util.*;

public class MyFitnessTracker{
    public static void print_and_get_choices(){
        System.out.println("Main Window (Please Choose One of the Following Options):");
        System.out.println("(1) Add User Profile \n(2) Track Activities \n(3) Calorie Manager \n(4) Set Goals \n(5)BMI Calculator \n (6) Activity Generator \n(7) Quit");
    }
    public static void main (String args[]){
        Scanner scanner = new Scanner(System.in);
        int user_choice = 0;

        // LOOP RUNS UNTIL USER CHOOSES TO EXIT
        while (user_choice != 7) {
            //PRINT ALL OPTIONS TO THE USER AND USE INPUT TO DETERMINE NEXT ACTION
            print_and_get_choices();
            user_choice = scanner.nextInt();
            scanner.nextLine();

            switch (user_choice) {
                case 1:
                // ADD USER PROFILE
                break;

                case 2:
                // TRACK ACTIVITIES
                break;

                case 3:
                // CALORIE MANAGER
                break;

                //SET GOALS
                case 4: 
                break;

                //BMI CALCULATOR
                case 5:
                break;

                //ACTIVITY GENERATOR
                case 6:
                break;
            }
            }
            scanner.close();
    }
}
