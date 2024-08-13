import java.io.*;

public class BMI_File_Handler {
    private File profileFile;

    public BMI_File_Handler(String fileName) {
        this.profileFile = new File(fileName);
    }

    //RETURNS TRUE IF ABLE TO READ PROFILE... ELSE RETURNS FALSE 
    public boolean readProfile(int[] data) {
        if (!profileFile.exists()) {return false;}

        try (BufferedReader br = new BufferedReader(new FileReader(profileFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Weight:")) {data[0] = Integer.parseInt(line.split(":")[1].trim());} 
                else if (line.startsWith("Height:")) {data[1] = Integer.parseInt(line.split(":")[1].trim());}
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            return false;
        }

        return true;
    }
}
