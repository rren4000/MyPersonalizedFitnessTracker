import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Cal_Tracker{
    private int total_cal;
    private int goal_cal;

    private static String FILE_NAME = "calorie_tracker_data.txt";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private LocalDate date;

    Scanner scanner = new Scanner(System.in);

    // Constructor
    public Cal_Tracker() { 
        this.total_cal = 0;
        this.goal_cal = 0;
        this.date = LocalDate.now();
    }

    // GETTERS
    public int get_toal_cal() { return total_cal; }
    public int get_goal_cal() { return goal_cal; }
    public LocalDate getDate() { return date; }

    // SETTERS
    public void set_toal_cal(int total_cal) { this.total_cal = total_cal; }
    public void set_goal_cal(int goal_cal) { this.goal_cal =  goal_cal; }
    public void setDate(LocalDate date) { this.date = date; }

    // DISPLAY BEGINNING SCREEN
    public void begin_tracker(){
        clear();
        System.out.println("----------------------------------------\n");
        System.out.println("Welcome to the Calorie Tracker!");
        System.out.println("\n----------------------------------------\n");
    }

    // DISPLAY ENDING SCREEN
    public void close_Cal_tracker(){
        System.out.println("\n----------------------------------------\n");
        System.out.print("Thank you for using the Calorie Tracker! \n\nPress \"Enter\" to return to the main menu.");
        this.scanner.nextLine();
    }

    // CLEAR THE CURRENT SCREEN
    public static void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Display the Calorie Tracker
    public void display_cal_tracker() {
        begin_tracker();

        update_calories();

        clear();

        System.out.println("----------------------------------------\n");
        System.out.println("You have consumed a total of "+ this.total_cal + " today.");

        close_Cal_tracker();
    }

    // Update the calories consumed
    private void update_calories() {
        while(true){
            try{
                System.out.print("How many calories have you consumed: ");
                int cal = this.scanner.nextInt();
                this.total_cal += cal;
                this.scanner.nextLine();
                update_file();
                break;
            } catch(InputMismatchException e){
                clear();
                System.out.println("----------------------------------------");
                System.out.println("\nInvalid input. Please enter a valid integer for calories.\n");
                this.scanner.nextLine();
            }
        }
    }

    // Update the calorie tracker data in the file
    private void update_file() {
        LocalDate currentDate = LocalDate.now();
        StringBuilder fileContent = new StringBuilder();
        boolean dateFound = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(": ");
                if (parts.length == 2) {
                    String date = parts[0].trim();
                    int existingCalories = Integer.parseInt(parts[1].trim());

                    if (date.equals(currentDate.format(DATE_FORMAT))) {
                        this.total_cal += existingCalories;
                        fileContent.append(currentDate.format(DATE_FORMAT)).append(": ").append(this.total_cal).append("\n");
                        dateFound = true;
                    } 
                    else { fileContent.append(line).append("\n"); }
                }
            }
        } catch (IOException e) { e.printStackTrace(); }

        if (!dateFound) {
            fileContent.append(currentDate.format(DATE_FORMAT)).append(": ").append(this.total_cal).append("\n");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(fileContent.toString());
        } catch (IOException e) { e.printStackTrace(); }
    }
}
