import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class File_User_Data_Saver implements User_Data_Saver {
    private static final String FILE_NAME = "user_profile.txt";

    @Override
    public void saveData(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, false))) {
            writer.write("Name: " + user.get_name() + "\n");
            writer.write("Age: " + user.get_age() + "\n");
            writer.write("Weight: " + user.get_weight() + "\n");
            writer.write("Height: " + user.get_height() + "\n");
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving user data: " + e.getMessage());
        }
    }
}
