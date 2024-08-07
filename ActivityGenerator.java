import java.util.Random;

public class ActivityGenerator {
    private String[][] fitnessActivity;
    String activitySuggestion;
    private int generateNum;
    private String[] findMatchNum;

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
            };
        }

        public String getRandomActivity(){
            Random random = new Random();
            generateNum = random.nextInt(fitnessActivity.length);

            findMatchNum = fitnessActivity[generateNum];
            activitySuggestion = "\nFitness Suggestion:\n\t" +
                "Activity: " + findMatchNum[0] + "\n\t" +
                "Recommended Time:\n\t\t - " +
                findMatchNum[1] + "\n\t\t - " +
                findMatchNum[2] + "\n\t\t - " +
                findMatchNum[3];

            return activitySuggestion;
        }

        
    
}
