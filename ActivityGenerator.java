import java.util.Random;

public class ActivityGenerator {
    private String[][] fitnessActivity;
    String activitySuggestion;
    private int generateNum;
    private String[] findMatchNum;

    //DEFAULT CONSTRUCTOR 
    public ActivityGenerator(){
        this.fitnessActivity = new String[][]{
            {"Running", "Beginner: 20-30 minutes", "Intermediate: 30-45 minutes", "Advanced: 45-60 minutes"},
            {"Cycling", "Beginner: 30-45 minutes", "Intermediate: 45-60 minutes", "Advanced: 60-90 minutes"},
            {"Swimming", "Beginner: 20-30 minutes", "Intermediate: 30-45 minutes", "Advanced: 45-60 minutes"},
            {"Jump Rope", "Beginner: 10-15 minutes", "Intermediate: 15-25 minutes", "Advanced: 25-35 minutes"},
            {"Rowing", "Beginner: 20-30 minutes", "Intermediate: 30-45 minutes", "Advanced: 45-60 minutes"},
            {"Elliptical Trainer", "Beginner: 20-30 minutes", "Intermediate: 30-45 minutes", "Advanced: 45-60 minutes"},
            {"HIIT", "Beginner: 10-20 minutes", "Intermediate: 20-30 minutes", "Advanced: 30-45 minutes"},
            {"Dancing", "Beginner: 20-30 minutes", "Intermediate: 30-45 minutes", "Advanced: 45-60 minutes"},
            {"Hiking", "Beginner: 30-60 minutes", "Intermediate: 60-90 minutes", "Advanced: 90-120 minutes"},
            {"Kickboxing", "Beginner: 20-30 minutes", "Intermediate: 30-45 minutes", "Advanced: 45-60 minutes"},
            {"Yoga", "Beginner: 20-30 minutes", "Intermediate: 30-45 minutes", "Advanced: 45-60 minutes"},
            {"Pilates", "Beginner: 30-45 minutes", "Intermediate: 30-45 minutes", "Advanced: 45-60 minutes"},
            {"Bodyweight Exercises (e.g., push-ups, squats, lunges", "Beginner: 15-25 minutes", "Intermediate: 25-35 minutes", "Advanced: 35-45 minutes"},
            {"Kettlebell", "Beginner: 15-25 minutes", "Intermediate: 25-35 minutes", "Advanced: 35-45 minutes"},
            {"Climb Stairs", "Beginner: 15-20 minutes", "Intermediate: 20-30 minutes", "Advanced: 30-45 minutes"},
            {"Kayaking", "Beginner: 30-45 minutes", "Intermediate: 45-60 minutes", "Advanced: 60-75 minutes"},
            {"Skiing", "Beginner: 30-45 minutes", "Intermediate: 45-60 minutes", "Advanced: 60-75 minutes"},
            {"Gymnastics", "Beginner: 30-45 minutes", "Intermediate: 30-45 minutes", "Advanced: 45-60 minutes"},
            {"Situps", "Beginner: 30 per min", "Intermediate: 45 per min", "Advanced: 60+ per min"},
            {"Jumping-Jacks", "Beginner: 30 per min", "Intermediate: 45 per min", "Advanced: 60+ per min"},
            {"Lunges", "Beginner: 30 per min", "Intermediate: 45 per min", "Advanced: 60+ per min"},
            {"Burpee Jump", "Beginner: 25 per min", "Intermediate: 40 per min", "Advanced: 60 per min"},
            };
        }


        //GENERATE RANDOM ACTIVITY FROM ARRAYLIST 
        public String getRandomActivity(){
            ClearConsole.clear();
            Random random = new Random();
            generateNum = random.nextInt(fitnessActivity.length);

            findMatchNum = fitnessActivity[generateNum];
            activitySuggestion = "\nFitness Suggestion:\n\n\t" +
                "Activity: " + findMatchNum[0] + "\n\n\t" +
                "Recommended Time:\n\t\t - " +
                findMatchNum[1] + "\n\t\t - " +
                findMatchNum[2] + "\n\t\t - " +
                findMatchNum[3] + "\n";

            return activitySuggestion;
        }

        
    
}
