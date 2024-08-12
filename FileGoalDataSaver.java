import java.io.*;
import java.time.format.DateTimeFormatter;

public class FileGoalDataSaver implements GoalDataSaver {
    private static final String FILE_NAME = "goal.txt";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void saveData(Goal goal) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(goal.getDate().format(DATE_FORMAT) + ":");
            writer.newLine();
            writer.newLine();

            // Add goal data to file...
            if(goal.getGoalWeight() != 0) {
                writer.write("Goal Weight (in pounds): " + goal.getGoalWeight());
                writer.newLine();
            }

            if(goal.getGoalSteps() != 0) {
                writer.write("Goal Step Count: " + goal.getGoalSteps());
                writer.newLine();
            }

            ActivityDuration duration = goal.getGoalActivityDuration();
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
