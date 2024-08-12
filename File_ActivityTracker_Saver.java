import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class File_ActivityTracker_Saver {
    private static final String FILE_NAME = "activity_tracker_data.txt";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void saveData(ActivityTrackerHandler data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write("DATE: " + data.getDate().format(DATE_FORMAT));
            writer.newLine();
            writer.write("STEP COUNT: " + data.getSteps());
            writer.newLine();
            writer.write("APPROXIMATE DISTANCE: " + data.calculateMiles() + " miles");
            writer.newLine();
            writer.write("SLEEP HOURS: " + data.getSleepHours());
            writer.newLine();
            writer.write("ACTIVITIES AND DURATIONS:");
            writer.newLine();
            for (Activity activity : data.getActivityManager().getActivities()) {
                writer.write("\t" + activity.toString());
                writer.newLine();
            }
            writer.newLine();
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public void loadData(ActivityTrackerHandler data) {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("Creating new file....");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("DATE: ")) {
                    data.setDate(LocalDate.parse(line.substring(6), DATE_FORMAT));
                } else if (line.startsWith("STEP COUNT: ")) {
                    data.setSteps(Integer.parseInt(line.substring(12)));
                } else if (line.startsWith("SLEEP HOURS: ")) {
                    data.setSleepHours(Double.parseDouble(line.substring(13)));
                } else if (line.startsWith("\t")) {
                    String[] activityData = line.trim().split("\\. ", 2);
                    if (activityData.length == 2) {
                        String[] details = activityData[1].split(" - ");
                        if (details.length == 2) {
                            String activityName = details[0];
                            int duration = Integer.parseInt(details[1].replace(" minutes", ""));
                            int id = Integer.parseInt(activityData[0]);
                            data.getActivityManager().addActivity(new ActivityObj(id, activityName, duration));
                        }
                    }
                }
            }
            System.out.println("Data loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}
