import java.io.*;

public class BMR_File_Handler {
    //LOAD PROFILE INFO FROM FILE (IF IT EXISTS)
    public int[] loadProfile(String fileName) {
        int[] profileData = new int[3]; // Array to store age, weight, and height
        File file = new File(fileName);

        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.startsWith("Age:")) {profileData[0] = Integer.parseInt(line.split(":")[1].trim());} 
                    else if (line.startsWith("Weight:")) {profileData[1] = Integer.parseInt(line.split(":")[1].trim());} 
                    else if (line.startsWith("Height:")) {profileData[2] = Integer.parseInt(line.split(":")[1].trim());}
                }
            } 
            catch (IOException e) {e.printStackTrace();}
        }
        return profileData; // Returns an array with age, weight, height
    }

    //LOAD GOAL WEIGH FROM FILE (IF IT EXISTS)
    public int loadGoal(String fileName) {
        int goalWeight = 0;
        File file = new File(fileName);

        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.startsWith("Goal Weight (in pounds):")) {goalWeight = Integer.parseInt(line.split(":")[1].trim());}
                }
            } 
            catch (IOException e) {e.printStackTrace();}
        }
        return goalWeight; // Returns the goal weight if found, otherwise returns 0
    }
}
