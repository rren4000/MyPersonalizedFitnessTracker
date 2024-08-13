
import java.time.LocalDate;

public class Cal_Tracker {
    private int total_cal;
    private int goal_cal;

    private LocalDate date;

    private Cal_Tracker_File_Handler fileHandler;
    private Cal_Tracker_UI ui;

    // Constructor
    public Cal_Tracker() { 
        this.total_cal = 0;
        this.goal_cal = 0;
        this.date = LocalDate.now();
        this.fileHandler = new Cal_Tracker_File_Handler();
        this.ui = new Cal_Tracker_UI();
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
        ui.displayWelcomeMessage();
    }

    // DISPLAY ENDING SCREEN
    public void close_Cal_tracker() {
        ui.displayClosingMessage();
    }

    // Display the Calorie Tracker
    public void display_cal_tracker() {
        begin_tracker();

        update_calories();

        System.out.print("\033[H\033[2J");

        ui.displayTotalCalories(total_cal);

        close_Cal_tracker();
    }

    // Update the calories consumed
    private void update_calories() {
        int cal = ui.getCalorieInput();
        total_cal += cal;
        fileHandler.update_file(this);
    }
}
