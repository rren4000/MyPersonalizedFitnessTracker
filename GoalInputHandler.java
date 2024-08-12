import java.util.InputMismatchException;
import java.util.Scanner;

public class GoalInputHandler {
    private Scanner scanner = new Scanner(System.in);

    public void set_goals(Goals goals) {
        int curr_goal;

        // LOOP TO CONTINUE SETTING GOALS
        while (true) {
            System.out.println("----------------------------------------\n");
            System.out.println("Welcome to Goal Settings!\n");
            System.out.println("----------------------------------------\n");
            System.out.println("\t(1) Body Weight \n\t(2) Steps \n\t(3) Activity Duration\n");

            //VERIFY INPUT TYPE IS CORRECT AND WITHIN APPROPRIATE RANGE
            while (true) {
                try {
                    System.out.print("Enter the goal you would like to set: ");
                    curr_goal = scanner.nextInt();

                    if (curr_goal >= 1 && curr_goal <= 3) {break;} 
                    else {System.out.println("\nInvalid input. Please enter 1, 2, or 3.\n");}
                } catch (InputMismatchException e) {
                    System.out.println("\nInvalid input. Please enter a valid integer.\n");
                    scanner.nextLine();
                }
            }

            clearScreen();

            switch (curr_goal) {
                //WEIGHT
                case 1:
                    set_weight_goal(goals);
                    break;
                //STEPS
                case 2:
                    set_step_goal(goals);
                    break;
                //ACTIVITY DURATION
                case 3:
                    set_activity_duration_goal(goals);
                    break;
            }

            System.out.println("----------------------------------------\n");
            System.out.println("Your goal has been successfully set!");

            System.out.println("\nPress 1 if you would like to set another goal or press \"Enter\" to go back to the main menu.\n");
            scanner.nextLine();  
            String input = scanner.nextLine().trim();

            //END LOOP IS USER PRESSED ENTER ---> NEEDS EDIT TO ONLY CONTINUE IF THEY ENTERED 1*********
            if (input.isEmpty()) {break;}
        }
    }

    private void set_weight_goal(Goals goals) {
        //VERIFY INPUTTED DATA IS VALID 
        while (true) {
            try {
                System.out.print("Goal Body Weight (in numerical pounds): ");
                int weight_goal = scanner.nextInt();
                goals.set_goal_weight(weight_goal);
                break;
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter a valid integer for weight.\n");
                scanner.nextLine();
            }
        }
    }

    private void set_step_goal(Goals goals) {
        while (true) {
            try {
                System.out.print("Goal Step Count (in numerical form): ");
                int stepGoal = scanner.nextInt();
                goals.set_goal_steps(stepGoal);
                break;
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter a valid integer for step count.\n");
                scanner.nextLine();
            }
        }
    }

    private void set_activity_duration_goal(Goals goal) {
        int move, exercise, stand;

        //VERIFY INPUTTED DATA IS VALID
        while (true) {
            try {
                System.out.print("Move (in numerical hours): ");
                move = scanner.nextInt();
                System.out.print("Exercise (in numerical hours): ");
                exercise = scanner.nextInt();
                System.out.print("Stand (in numerical hours): ");
                stand = scanner.nextInt();
                goal.set_goal_activity_duration(move, exercise, stand);
                break;
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter valid integers for activity duration.\n");
                scanner.nextLine();
            }
        }
    }

    //CLEAR THE CURRENT SCREEN
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
