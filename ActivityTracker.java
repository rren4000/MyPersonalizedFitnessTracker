import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

public class ActivityTracker {
    private static final String FILE_NAME = "activity_tracker_data.txt";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private List<ActivityObj> activities;
    private int steps;
    private double sleepHours;
    private LocalDate date;

    Scanner scanner = new Scanner(System.in);

    // Constructors
    public ActivityTracker() {
        this.activities = new ArrayList<>();
        this.steps = 0;
        this.sleepHours = 0.0;
        this.date = LocalDate.now();
        loadData();
    }

    // Getters and Setters
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

    //CLEAR THE CURRENT SCREEN
    public static void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Calculate miles from steps (assuming average stride length of 2.5 feet)
    private double calculateMiles() {
        double raw_steps_to_miles = (steps * 2.5) / 5280; // 5280 feet in a mile
        return Math.round(raw_steps_to_miles * 10.0) / 10.0;
    }

    // Display activity information
    private void displayAllSavedActivityInfo() {
        System.out.println("DATE: " + this.date);
        System.out.println("\tSTEP COUNT: " + steps);
        System.out.println("\tAPPROXIMATE DISTANCE: " + calculateMiles() + " miles");
        System.out.println("\tSLEEP HOURS: " + sleepHours + " hours");
        System.out.println("\tACTIVITIES AND DURATION:");
        for (ActivityObj activity : activities) {
            System.out.println("\t\t" + activity.toString());
        }
    }

    // Method to calculate sleep hours from bedtime and awake time
    private static double calculateSleepHours(LocalTime bedtime, LocalTime awakeTime) {
        Duration duration = Duration.between(bedtime, awakeTime);
        if (duration.isNegative()) {
            duration = duration.plusDays(1); // Adjust for bedtime past midnight
        }
        double sleep_minutes = (double) Math.round((duration.toMinutesPart() / 60.0) * 10.0) / 10.0;
        return duration.toHours() + sleep_minutes;
    }

    private static void printActivityTrackerMenu() {
        System.out.println("----------------------------------------\n");
        System.out.println("Choose an option:\n\t(Note: All saved data will be displayed in option \"6\")");
        System.out.println("\n\t1. Add a new activity");
        System.out.println("\t2. Edit an activity");
        System.out.println("\t3. Delete an activity");
        System.out.println("\t4. Step Count");
        System.out.println("\t5. Calculate hours of sleep");
        System.out.println("\t6. Display all saved activity information");
        System.out.println("\t7. Save All Data and Return to main menu");
    }

    public void activityClassRunnerCode() {
        int activityTrackerChoice = 0;

        while (activityTrackerChoice != 7) {
            printActivityTrackerMenu();
            while(true){
                try{
                    System.out.print("\nPlease enter your choice: ");
                    activityTrackerChoice = scanner.nextInt();
                    scanner.nextLine(); 
                    break;
                } catch(InputMismatchException e){
                    System.out.println("\nInvalid input. Please enter a valid integer for tracker option.");
                    scanner.nextLine();
                }
            }

            switch (activityTrackerChoice) {
                // ADD ACTIVITY 
                case 1:
                    clear();
                    addActivity(scanner);
                    clear();
                    break;
                // EDIT ACTIVITY
                case 2:
                    clear();
                    editActivity(scanner);
                    clear();
                    break;
                // DELETE ACTIVITY 
                case 3:
                    clear();
                    deleteActivity(scanner);
                    clear();
                    break;

                // STEPS
                case 4:
                    clear();
                    System.out.println("----------------------------------------\n");
                    while(true){
                        try{
                            System.out.print("Enter number of steps: ");
                            setSteps(scanner.nextInt());
                            break;
                        } catch(InputMismatchException e){
                            System.out.println("\nInvalid input. Please enter a valid integer for number of steps.\n");
                            scanner.nextLine();
                        }
                    }
                    System.out.print("Step Count Saved... Press \"Enter\" to continue.");
                    scanner.nextLine(); 
                    clear();
                    break;
                // SLEEP
                case 5:
                    clear();
                    enterSleepData(scanner);
                    System.out.print("(Hours Slept Calculated and Saved.... Press \"Enter\" to continue)");
                    clear();
                    break;
                // DISPLAY
                case 6:
                    clear();
                    displayAllSavedActivityInfo();
                    System.out.print("\n(Data loaded. Press \"Enter\" to continue)");
                    scanner.nextLine();
                    clear();
                    break;
                // INVLAID CHOICE 
                default:
                    clear(); 
                    System.out.println("----------------------------------------\n");
                    System.out.println("Invalid choice. Please enter a valid integer between 1 and 7.");
            }
        }
        // SAVE DATA 
        if (activityTrackerChoice == 7) {
            saveData();
            clear();
        }
    }

    private void addActivity(Scanner scanner) {
        int duration;

        // ACTIVITY 
        System.out.println("----------------------------------------\n");
        System.out.print("Activity: ");
        String activityName = scanner.nextLine();

        //DURATION
        while(true){
            try{
                System.out.print("Duration (in minutes): ");
                duration = scanner.nextInt();
                break;
            } catch(InputMismatchException e){
                System.out.println("\nInvalid input. Please enter a valid integer for duration.\n");
                scanner.nextLine();
            }
        }

        int id = activities.size() + 1;
        ActivityObj new_activity = new ActivityObj(id, activityName, duration);
        activities.add(new_activity);

        System.out.print("\n(New activity saved... press \"Enter\" to continue)");
        scanner.nextLine(); 
        saveData();
    }

    private void editActivity(Scanner scanner) {
        int id; 

        System.out.println("----------------------------------------\n");

        if (activities.isEmpty()) {
            System.out.println("No activities to edit.");
            return;
        }

        displayAllSavedActivityInfo();

        //SET ACTIVITY ID 
        while(true){
            try{
                System.out.print("\nEnter the ID of the activity you want to edit: ");
                id = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch(InputMismatchException e){
                System.out.println("\nInvalid input. Please enter a valid integer for ID.");
                scanner.nextLine();
            }
        }

        //EDIT SPECIFIC ACTIVITY VIA ITS ID
        ActivityObj activityToEdit = getActivityById(id);
        if (activityToEdit != null) {
            clear();
            
            // NEW NAME
            System.out.print("Enter new activity name (leave empty to keep current): ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                activityToEdit.setName(newName);
            }

            //NEW DURATION
            System.out.print("Enter new duration in minutes (leave empty to keep current): ");
            String newDurationStr = scanner.nextLine();

            //IF THE USER ENTERS SOMEETHING....
            while (!newDurationStr.isEmpty()) {
                try {
                    int newDuration = Integer.parseInt(newDurationStr);
                    activityToEdit.setDuration(newDuration);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("\nInvalid input. Please enter a valid integer or leave it empty to keep the current duration.\n");
                    System.out.print("Enter new duration in minutes (leave empty to keep current): ");
                    newDurationStr = scanner.nextLine();
                }
            }

            System.out.println("\n----------------------------------------");
            System.out.print("\n(Activity Edited.... press \"Enter\" to continue)");
            scanner.nextLine();

            clear();
        } else {
            System.out.println("Activity not found.");
        }
        saveData();
    }

    private void deleteActivity(Scanner scanner) {
        int id; 

        if (activities.isEmpty()) {
            System.out.println("No activities to delete.");
            return;
        }
        displayAllSavedActivityInfo();
        //GET DESIRED ID
        while(true){
            try{
                System.out.print("\nEnter the ID of the activity you want to edit: ");
                id = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch(InputMismatchException e){
                System.out.println("\nInvalid input. Please enter a valid integer for ID.");
                scanner.nextLine();
            }
        }

        ActivityObj activityToDelete = getActivityById(id);
        if (activityToDelete != null) {
            activities.remove(activityToDelete);
            // Reassign IDs
            for (int i = 0; i < activities.size(); i++) {
                activities.get(i).setId(i + 1);
            }
            System.out.println("\n----------------------------------------");
            System.out.print("\n(Activity deleted.... press \"Enter\" to continue)");
            scanner.nextLine();
        } else {
            System.out.println("Activity not found.");
        }
        saveData();
    }

    private void enterSleepData(Scanner scanner) {
        LocalTime bedtime = null;
        LocalTime awakeTime = null;

        System.out.println("----------------------------------------\n");

        // Loop until a valid bedtime is entered
        while (bedtime == null) {
            try {
                System.out.print("Enter bedtime in military time (HH:mm, i.e., 22:00): ");
                String bedtimeStr = scanner.nextLine();
                bedtime = LocalTime.parse(bedtimeStr);
            } catch (DateTimeParseException e) {
                System.out.println("\nInvalid time format. Please enter the bedtime in HH:mm format.\n");
            }
        }

        // Loop until a valid awake time is entered
        while (awakeTime == null) {
            try {
                System.out.print("Enter awake time in military time (HH:mm, i.e., 07:00): ");
                String awakeTimeStr = scanner.nextLine();
                awakeTime = LocalTime.parse(awakeTimeStr);
            } catch (DateTimeParseException e) {
                System.out.println("\nInvalid time format. Please enter the awake time in HH:mm format.\n");
            }
        }

        // Calculate sleep hours and save data
        this.setSleepHours(calculateSleepHours(bedtime, awakeTime));
        saveData();
        System.out.println("Sleep data saved successfully.");
    }


    private ActivityObj getActivityById(int id) {
        return activities.stream().filter(activity -> activity.getId() == id).findFirst().orElse(null);
    }

    // Save data to a file
    /*private void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write("DATE: " + this.date.format(DATE_FORMAT));
            writer.newLine();
            writer.write("\tSTEPCOUNT COUNT: " + this.steps);
            writer.newLine();
            writer.write("\tAPPROXIMATE DISTANCE: " + calculateMiles() + " miles");
            writer.newLine();
            writer.write("\tSLEEP HOURS: " + this.sleepHours);
            writer.newLine();
            writer.write("\tACTIVITIES AND DURATIONS:");
            writer.newLine();
            for (ActivityObj activity : activities) {
                writer.write("\t\t" + activity.toString());
                writer.newLine();
            }
            writer.newLine();
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }*/
    private void saveData() {
        File file = new File(FILE_NAME);
        StringBuilder fileContent = new StringBuilder();
        boolean dateExists = false;

        try {
            // Read the existing file content if the file exists
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.startsWith("DATE: " + this.date.format(DATE_FORMAT))) {
                            dateExists = true;
                            // Add the date line
                            fileContent.append(line).append(System.lineSeparator());

                            // Append the updated step count, distance, sleep hours, and activities
                            fileContent.append("\tSTEP COUNT: ").append(this.steps).append(System.lineSeparator());
                            fileContent.append("\tAPPROXIMATE DISTANCE: ").append(calculateMiles()).append(" miles").append(System.lineSeparator());
                            fileContent.append("\tSLEEP HOURS: ").append(this.sleepHours).append(System.lineSeparator());
                            fileContent.append("\tACTIVITIES AND DURATIONS:").append(System.lineSeparator());

                            // Add the activities
                            for (ActivityObj activity : activities) {
                                fileContent.append("\t\t").append(activity.toString()).append(System.lineSeparator());
                            }

                            // Skip the old activities for this date
                            while ((line = reader.readLine()) != null && !line.startsWith("DATE: ")) {
                                // Skip existing activities
                            }
                        } else {
                            // If not the current date, append the line as-is
                            fileContent.append(line).append(System.lineSeparator());
                        }
                    }
                }
            }

            // If the date does not exist, add a new section for today
            if (!dateExists) {
                fileContent.append("DATE: ").append(this.date.format(DATE_FORMAT)).append(System.lineSeparator());
                fileContent.append("\tSTEP COUNT: ").append(this.steps).append(System.lineSeparator());
                fileContent.append("\tAPPROXIMATE DISTANCE: ").append(calculateMiles()).append(" miles").append(System.lineSeparator());
                fileContent.append("\tSLEEP HOURS: ").append(this.sleepHours).append(System.lineSeparator());
                fileContent.append("\tACTIVITIES AND DURATIONS:").append(System.lineSeparator());

                for (ActivityObj activity : activities) {
                    fileContent.append("\t\t").append(activity.toString()).append(System.lineSeparator());
                }
                fileContent.append(System.lineSeparator());
            }

            // Write everything back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(fileContent.toString());
                System.out.println("Data saved successfully.");
            }

        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
    

    private void loadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("DATE: ")) {
                    this.date = LocalDate.parse(line.substring(6), DATE_FORMAT);
                        // Parses by starting with the 7th index to the end of the string
                } else if (line.startsWith("STEP COUNT: ")) {
                    this.steps = Integer.parseInt(line.substring(12));
                } else if (line.startsWith("APPROXIMATE DISTANCE: ")) {
                    //ignorable since we calculate distance
                } else if (line.startsWith("SLEEP HOURS: ")) {
                    this.sleepHours = Double.parseDouble(line.substring(13));
                } else if (line.startsWith("\t")) {
                    String[] activityData = line.trim().split("\\. ", 2);
                    if (activityData.length == 2) {
                        String[] details = activityData[1].split(" - ");
                        if (details.length == 2) {
                            String activityName = details[0];
                            int duration = Integer.parseInt(details[1].replace(" minutes", ""));
                            int id = Integer.parseInt(activityData[0]);
                            activities.add(new ActivityObj(id, activityName, duration));
                        }
                    }
                }
            }
            System.out.println("----------------------------------------\n");
            System.out.println("Data loaded successfully.");
            System.out.println("\n----------------------------------------");
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}
