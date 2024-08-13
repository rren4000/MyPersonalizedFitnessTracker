import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Cal_Tracker {
    private int total_cal;
    private int goal_cal;
    private LocalDate date;
    private Cal_Tracker_File_Handler fileHandler;
    private Scanner scanner;

    // Constructor
    public Cal_Tracker() {
        this.total_cal = 0;
        this.goal_cal = 0;
        this.date = LocalDate.now();
        this.fileHandler = new Cal_Tracker_File_Handler();
        this.scanner = new Scanner(System.in);
    }

    // GETTERS
    public int get_total_cal() { return total_cal; }
    public int get_goal_cal() { return goal_cal; }
    public LocalDate getDate() { return date; }

    // SETTERS
    public void set_total_cal(int total_cal) { this.total_cal = total_cal; }
    public void set_goal_cal(int goal_cal) { this.goal_cal = goal_cal; }
    public void setDate(LocalDate date) { this.date = date; }

    // DISPLAY BEGINNING SCREEN
    public void begin_tracker() {
        System.out.print("\033[H\033[2J");
        System.out.println("----------------------------------------\n");
        System.out.println("Welcome to the Calorie Tracker!");
        System.out.println("\n----------------------------------------\n");
    }

    // DISPLAY ENDING SCREEN
    public void close_Cal_tracker() {
        System.out.println("\n----------------------------------------\n");
        System.out.print("Thank you for using the Calorie Tracker! \n\nPress \"Enter\" to return to the main menu.");
        scanner.nextLine();
    }

    // Display the Calorie Tracker
    public void display_cal_tracker() {
        begin_tracker();
        update_calories();
        System.out.print("\033[H\033[2J");
        System.out.println("----------------------------------------\n");
        System.out.println("You have consumed a total of " + total_cal + " today.");
        close_Cal_tracker();
    }

    // Update the calories consumed
    private void update_calories() {
        int cal = getCalorieInput();
        total_cal += cal;
        fileHandler.update_file(this);
    }

    // Get calorie input from the user
    private int getCalorieInput() {
        while (true) {
            try {
                System.out.print("How many calories have you consumed: ");
                int cal = scanner.nextInt();
                scanner.nextLine(); // consume newline
                return cal;
            } catch (InputMismatchException e) {
                System.out.print("\033[H\033[2J");
                System.out.println("----------------------------------------");
                System.out.println("\nInvalid input. Please enter a valid integer for calories.\n");
                scanner.nextLine(); // clear the buffer
            }
        }
    }
}
