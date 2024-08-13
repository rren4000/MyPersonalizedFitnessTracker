import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Cal_Tracker_File_Handler {
    private static final String FILE_NAME = "calorie_tracker_data.txt";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    //UPDATE TOTAL CALORIES IN TEXT FILE
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
                    //ADD TO EXISTING DATE
                    if (date.equals(currentDate.format(DATE_FORMAT))) {
                        tracker.set_total_cal(tracker.get_total_cal() + existingCalories);
                        fileContent.append(currentDate.format(DATE_FORMAT)).append(": ").append(tracker.get_total_cal()).append("\n");
                        dateFound = true;
                    } 
                    //CREATE NEW DATE
                    else {fileContent.append(line).append("\n");}
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //CREATE NEW DATE & STORE DATA THERE
        if (!dateFound) {fileContent.append(currentDate.format(DATE_FORMAT)).append(": ").append(tracker.get_total_cal()).append("\n");}

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {writer.write(fileContent.toString());} 
        catch (IOException e) {e.printStackTrace();}
    }
}
