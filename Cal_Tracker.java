import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Cal_Tracker {
    private int total_cal;
    private int cal_goal;
    private static String FILE_NAME = "calorie_tracker_data.txt";

    // Constructor
    public Cal_Tracker() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Read the existing data for the current date
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                LocalDate date = LocalDate.parse(data[0], formatter);
                if (date.equals(currentDate)) {
                    this.total_cal = Integer.parseInt(data[1]);
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
        }

        // Display the tracker after initializing
        display_cal_tracker();
        System.out.println("Thank you for using the Calorie Tracker! Press Enter to return to the main menu.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    // Display the Calorie Tracker
    public void display_cal_tracker() {
        System.out.println("Welcome to the Calorie Tracker!");
        System.out.println("How many calories have you consumed: ");
        Scanner scanner = new Scanner(System.in);
        int cal = scanner.nextInt();
        this.total_cal += cal;
        update_cal_tracker();
        System.out.println("Today's Total Calories: " + this.total_cal);
    }

    // Update the calorie tracker data in the file
    public void update_cal_tracker() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        StringBuilder fileContent = new StringBuilder();
        boolean dateFound = false;

        // Read the file content and update the current date's entry
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String date = parts[0].trim();
                    if (date.equals(currentDate.format(formatter))) {
                        fileContent.append(currentDate.format(formatter)).append(",").append(this.total_cal).append("\n");
                        dateFound = true;
                    } else {
                        fileContent.append(line).append("\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // If the date was not found, add a new entry
        if (!dateFound) {
            fileContent.append(currentDate.format(formatter)).append(",").append(this.total_cal).append("\n");
        }

        // Write the updated content back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(fileContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
