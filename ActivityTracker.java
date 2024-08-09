import java.io.*;
import java.nio.file.*;
import java.time.*;
// import java.time.LocalDate;
// import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ActivityTracker {
    private static final String FILE_NAME = "activity_tracker_data.txt";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private String activity;
    private int duration; // in minutes
    private int steps;
    private double sleepHours;
    private LocalDate date;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constructors
    public ActivityTracker() {
        this.activity = "";
        this.duration = 0;
        this.steps = 0;
        this.sleepHours = 0.0;
        this.date = LocalDate.now();
    }

    // Getters and Setters
    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public double getSleepHours() {
        return sleepHours;
    }

    public void setSleepHours(double sleepHours) {
        this.sleepHours = sleepHours;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Calculate miles from steps (assuming average stride length of 2.5 feet)
    private double calculateMiles() {
        double raw_steps_to_miles = (steps * 2.5) / 5280; // 5280 feet in a mile
        double steps_to_miles = Math.round(raw_steps_to_miles *10.0)/ 10.0;
        return steps_to_miles;
    }

    // Display activity information
    private void displayActivityInfo() {
        System.out.println("Date: " + this.date);
        System.out.println("\tActivity: " + activity);
        System.out.println("\tDuration: " + duration + " minutes");
        System.out.println("\tSteps: " + steps);
        System.out.println("\t\tApproximate Miles: " + calculateMiles());
        System.out.println("\tSleep: " + sleepHours + " hours");
    }

    // Method to calculate sleep hours from bedtime and awake time
    private static double calculateSleepHours(LocalTime bedtime, LocalTime awakeTime) {
        Duration duration = Duration.between(bedtime, awakeTime);
        if (duration.isNegative()) {
            duration = duration.plusDays(1); // Adjust for bedtime past midnight
        }
        double sleep_minutes = (double) Math.round((duration.toMinutesPart()/60.0) * 10.0) / 10.0;
        return duration.toHours() + sleep_minutes;
    }

    private static void printActivityTrackerMenu() {
        System.out.println("Choose an option:");
        System.out.println("\t1. Enter activity and duration");
        System.out.println("\t2. Enter number of steps");
        System.out.println("\t3. Enter bedtime and awake time");
        System.out.println("\t4. Display activity information");
        System.out.println("\t5. Save data and return to main menu");
        System.out.println("Please enter your choice: ");
    }

    public void activityClassRunnerCode() {
        Scanner scanner = new Scanner(System.in);
        int activityTrackerChoice = 0;

        while (activityTrackerChoice != 5) {
            printActivityTrackerMenu();
            activityTrackerChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (activityTrackerChoice) {
                case 1:
                    System.out.println("Enter activity: ");
                    this.setActivity(scanner.nextLine());

                    System.out.println("Enter duration in minutes: ");
                    this.setDuration(scanner.nextInt());
                    scanner.nextLine(); // Consume newline
                    break;

                case 2:
                    System.out.println("Enter number of steps: ");
                    this.setSteps(scanner.nextInt());
                    scanner.nextLine(); // Consume newline
                    break;

                case 3:
                    System.out.println("Enter bedtime in military time (HH:mm, ie. 22:00): ");
                    String bedtimeStr = scanner.nextLine();
                    LocalTime bedtime = LocalTime.parse(bedtimeStr);

                    System.out.println("Enter awake time in military time (HH:mm, ie. 07:00): ");
                    String awakeTimeStr = scanner.nextLine();
                    LocalTime awakeTime = LocalTime.parse(awakeTimeStr);

                    this.setSleepHours(calculateSleepHours(bedtime, awakeTime));
                    break;

                case 4:
                    this.displayActivityInfo();
                    break;

                case 5:
                    saveData();
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    // Save data to a file
    private void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(this.date.format(DATE_FORMAT) + ": " + this.activity + ", " + this.duration + ", " +
                    this.steps + ", " + calculateMiles() + ", " + this.sleepHours);
            writer.newLine();
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}
