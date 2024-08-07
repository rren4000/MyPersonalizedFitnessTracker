import java.util.*;

public class MyFitnessTracker{
    public static void print_and_get_choices(){
        System.out.println("\nMain Window:");
        System.out.println("\t(1) Add User Profile \n\t(2) Track Activities \n\t(3) Calorie Manager \n\t(4) Set Goals \n\t(5) BMI Calculator \n\t(6) Activity Generator \n\t(7) Quit");
        System.out.print("Please Enter Your Choice: ");
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
                // CREATE USER OBJECT
                User curr_user = new User(); 
                //SET USER OBJECT WITH USER'S INPUT
                curr_user.set_profile();  // **** NEEDED EDIT ---> SAVE TO FILE ******
                                        // *** I ALSO DON'T CLOSE THE SCANNER BUT PROBS SHOULD ***
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
