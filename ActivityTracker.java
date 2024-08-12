import java.time.*;
import java.time.format.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ActivityTracker {
    private ActivityTrackerHandler activityTrackerHandler;
    private File_ActivityTracker_Saver activityDataSaver;
    private Scanner scanner;

    public ActivityTracker() {
        this.activityTrackerHandler = new ActivityTrackerHandler();
        this.activityDataSaver = new File_ActivityTracker_Saver();
        this.scanner = new Scanner(System.in);
        activityDataSaver.loadData(activityTrackerHandler);
    }

    public static void printActivityTrackerMenu() {
        System.out.println("\nChoose an option:\n\t(Note: All saved data will be displayed in option \"6\")");
        System.out.println("\t1. Add a new activity");
        System.out.println("\t2. Edit an activity");
        System.out.println("\t3. Delete an activity");
        System.out.println("\t4. Step Count");
        System.out.println("\t5. Calculate hours of sleep");
        System.out.println("\t6. Display all saved activity information");
        System.out.println("\t7. Save All Data and Return to main menu");
        System.out.println("Please enter your choice: ");
    }

    public void activityClassRunnerCode() {
        int activityTrackerChoice = 0;

        while (activityTrackerChoice != 7) {
            printActivityTrackerMenu();
            try {
                activityTrackerChoice = scanner.nextInt();
                scanner.nextLine();

                switch (activityTrackerChoice) {
                    case 1:
                        ClearConsole.clear();
                        addActivity();
                        ClearConsole.clear();
                        break;

                    case 2:
                        ClearConsole.clear();
                        editActivity();
                        ClearConsole.clear();
                        break;

                    case 3:
                        ClearConsole.clear();
                        deleteActivity();
                        ClearConsole.clear();
                        break;

                    case 4:
                        ClearConsole.clear();
                        System.out.print("Enter number of steps: ");
                        activityTrackerHandler.setSteps(scanner.nextInt());
                        System.out.println("Step Count Saved... Press enter to continue.");
                        scanner.nextLine();
                        ClearConsole.clear();
                        break;

                    case 5:
                        ClearConsole.clear();
                        enterSleepData();
                        System.out.println("(Hours Slept Calculated and Saved.... Press enter to continue)");
                        ClearConsole.clear();
                        break;

                    case 6:
                        ClearConsole.clear();
                        //activityTrackerHandler.getActivityManager().displayAllActivities();
                        activityTrackerHandler.getActivityManager().displayAllActivities(
                            activityTrackerHandler.getDate(),
                            activityTrackerHandler.getSteps(),
                            activityTrackerHandler.getSleepHours()
                        );
                        System.out.println("(Data loaded. Press enter to continue)");
                        scanner.nextLine();
                        ClearConsole.clear();
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                ClearConsole.clear();
                System.out.println("\n\nInvalid input. Please enter a valid choice.");
                scanner.nextLine(); // Clear the invalid input
            }
        }

        if (activityTrackerChoice == 7) {
            activityDataSaver.saveData(activityTrackerHandler);
            ClearConsole.clear();
        }
    }

    private void addActivity() {
        System.out.print("Enter activity: ");
        String activityName = scanner.nextLine();

        System.out.print("Enter duration in minutes: ");
        int duration = scanner.nextInt();
        scanner.nextLine();

        activityTrackerHandler.getActivityManager().addActivity(new ActivityObj(0, activityName, duration));
        System.out.println("(New activity saved... press enter to continue)");
        scanner.nextLine();
    }

    private void editActivity() {
    if (activityTrackerHandler.getActivityManager().getActivities().isEmpty()) {
        System.out.println("No activities to edit.");
        return;
    }
        activityTrackerHandler.getActivityManager().displayAllActivities(
        activityTrackerHandler.getDate(),
        activityTrackerHandler.getSteps(),
        activityTrackerHandler.getSleepHours()
    );

    int id = -1;
    boolean validInput = false;

    while (!validInput) {
        try {
            System.out.print("Enter the ID of the activity you want to edit: ");
            id = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            if (activityTrackerHandler.getActivityManager().getActivityById(id).isPresent()) {
                validInput = true;
            } else {
                System.out.println("Activity with ID " + id + " not found. Please try again.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer for the ID.");
            scanner.nextLine();  // Consume the invalid input
        }
    }

    System.out.print("Enter new activity name (leave empty to keep current): ");
    String newName = scanner.nextLine();

    System.out.print("Enter new duration in minutes (leave empty to keep current): ");
    String newDurationStr = scanner.nextLine();
    int newDuration = newDurationStr.isEmpty() ? -1 : Integer.parseInt(newDurationStr);

    activityTrackerHandler.getActivityManager().editActivity(id, newName, newDuration);
    System.out.println("(Activity Edited.... press enter to continue)");
    scanner.nextLine();
}


    private void deleteActivity() {
        if (activityTrackerHandler.getActivityManager().getActivities().isEmpty()) {
            System.out.println("No activities to delete.");
            return;
        }

        activityTrackerHandler.getActivityManager().displayAllActivities(
        activityTrackerHandler.getDate(),
        activityTrackerHandler.getSteps(),
        activityTrackerHandler.getSleepHours()
    );


        int id = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Enter the ID of the activity you want to delete (or enter 0 to cancel): ");
                id = scanner.nextInt();
                scanner.nextLine();  // Consume the newline character

                if (id == 0) {
                    System.out.println("Deletion canceled. Returning to menu...");
                    return; // Exit the deletion process if the user enters 0
                }

                if (activityTrackerHandler.getActivityManager().getActivityById(id).isPresent()) {
                    activityTrackerHandler.getActivityManager().deleteActivity(id);
                    System.out.println("Activity deleted.... press enter to continue)");
                    scanner.nextLine();
                    validInput = true; // Exit loop if deletion is successful
                } else {
                    System.out.println("Activity with ID " + id + " not found. Please try again.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer for the ID.");
                scanner.nextLine();  // Consume the invalid input
            }
        }
    }

    private void enterSleepData() {
        LocalTime bedtime = null;
        LocalTime awakeTime = null;

        // Loop until a valid bedtime is entered
        while (bedtime == null) {
            try {
                System.out.print("Enter bedtime in military time (HH:mm, i.e., 22:00): ");
                String bedtimeStr = scanner.nextLine();
                bedtime = LocalTime.parse(bedtimeStr);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Please enter the bedtime in HH:mm format.");
            }
        }

        // Loop until a valid awake time is entered
        while (awakeTime == null) {
            try {
                System.out.print("Enter awake time in military time (HH:mm, i.e., 07:00): ");
                String awakeTimeStr = scanner.nextLine();
                awakeTime = LocalTime.parse(awakeTimeStr);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Please enter the awake time in HH:mm format.");
            }
        }

        activityTrackerHandler.setSleepHours(ActivityTracker.calculateSleepHours(bedtime, awakeTime));
    }

    private static double calculateSleepHours(LocalTime bedtime, LocalTime awakeTime) {
        Duration duration = Duration.between(bedtime, awakeTime);
        if (duration.isNegative()) {
            duration = duration.plusDays(1); // Adjust for bedtime past midnight
        }
        double sleepMinutes = (double) Math.round((duration.toMinutesPart() / 60.0) * 10.0) / 10.0;
        return duration.toHours() + sleepMinutes;
    }
}
