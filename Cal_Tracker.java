import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Cal_Tracker{
    private int total_cal;
    private int cal_goal;
    private static String FILE_NAME = "calorie_tracker_data.txt";
    Scanner scanner = new Scanner(System.in);

    // Constructor
    public Cal_Tracker() { // ****EDIT 0--> THIS IS NOT A CRONSTRUCTOR 
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        //Check if file exists, create if it doesn't
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("File " + FILE_NAME + " not found. A new file has been created.");
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file.");
                e.printStackTrace();
            }
        }

        // Read the existing data for the current date
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(": ");
                LocalDate date = LocalDate.parse(data[0], formatter);
                if (date.equals(currentDate)) {
                    this.total_cal = Integer.parseInt(data[1]);
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
        }
        this.cal_goal = 2000;
    }

    public void close_Cal_tracker(){
        System.out.println("\n----------------------------------------\n");
        System.out.print("Thank you for using the Calorie Tracker! \n\nPress \"Enter\" to return to the main menu.");
        this.scanner.nextLine();
    }

    //CLEAR THE CURRENT SCREEN
    public static void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Display the Calorie Tracker
    public void display_cal_tracker() {
        clear();
        //PRINT INTRODUCTION
        System.out.println("----------------------------------------\n");
        System.out.println("Welcome to the Calorie Tracker!");
        System.out.println("\n----------------------------------------\n");



        //UPDATE CALORIES 
        while(true){
            try{
                System.out.print("How many calories have you consumed: ");
                int cal = this.scanner.nextInt();
                this.total_cal += cal;
                this.scanner.nextLine();
                update_cal_tracker();
                break;
            } catch(InputMismatchException e){
                System.out.println("\nInvalid input. Please enter a valid integer for calories.\n");
                this.scanner.nextLine();
            }
        }

        clear();

        //PRINT TOTAL CALORIES CONSUMED
        System.out.println("----------------------------------------\n");
        System.out.println("You have consumed a total of "+ this.total_cal + " today.");
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
                String[] parts = line.split(": ");
                if (parts.length == 2) {
                    String date = parts[0].trim();
                    if (date.equals(currentDate.format(formatter))) {
                        fileContent.append(currentDate.format(formatter)).append(": ").append(this.total_cal).append("\n");
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
            fileContent.append(currentDate.format(formatter)).append(": ").append(this.total_cal).append("\n");
        }

        // Write the updated content back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(fileContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
