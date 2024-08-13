import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Cal_Tracker_FileHandler {
    private static final String FILE_NAME = "calorie_tracker_data.txt";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void update_file(Cal_Tracker tracker) {
        LocalDate currentDate = tracker.getDate();
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
                        tracker.set_total_cal(tracker.get_total_cal() + existingCalories);
                        fileContent.append(currentDate.format(DATE_FORMAT)).append(": ").append(tracker.get_total_cal()).append("\n");
                        dateFound = true;
                    } else {
                        fileContent.append(line).append("\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!dateFound) {
            fileContent.append(currentDate.format(DATE_FORMAT)).append(": ").append(tracker.get_total_cal()).append("\n");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(fileContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
