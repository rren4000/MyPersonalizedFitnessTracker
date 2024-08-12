import java.io.*;
import java.time.format.DateTimeFormatter;

public class File_Goal_Data_Saver implements Goal_Data_Saver {
    private static final String FILE_NAME = "goals.txt";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void saveData(Goals goals) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(goals.get_date().format(DATE_FORMAT) + ":");
            writer.newLine();
            writer.newLine();

            // IF WEIGHT IS SET... ADD TO FILE
            if(goals.get_goal_weight() != 0) {
                writer.write("Goal Weight (in pounds): " + goals.get_goal_weight());
                writer.newLine();
            }

            //IF STEP IS SET... ADD TO FILE
            if(goals.get_goal_steps() != 0) {
                writer.write("Goal Step Count: " + goals.get_goal_steps());
                writer.newLine();
            }

            //IF ACTIVITY DURATION IS SET... ADD TO FILE 
            ActivityDuration duration = goals.get_goal_activity_duration();
            if(duration.get_move() != 0 || duration.get_exercise() != 0 || duration.get_stand() != 0) {
                writer.write("Goal Activity Duration (in hours): ");
                writer.newLine();
                writer.write("\tMove: " + duration.get_move());
                writer.newLine();
                writer.write("\tExercise: " + duration.get_exercise());
                writer.newLine();
                writer.write("\tStand: " + duration.get_stand());
                writer.newLine();
            }

            writer.write("----------------------------------------");
            writer.newLine();

            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}
