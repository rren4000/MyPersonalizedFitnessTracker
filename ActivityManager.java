import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.*;
import java.time.*;


public class ActivityManager {
    private Scanner scanner = new Scanner (System.in);
    private List<Activity> activities;

    public ActivityManager() {
        this.activities = new ArrayList<>();
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void addActivity(Activity activity) {
        int id = activities.size() + 1;
        activity.setId(id);
        activities.add(activity);
    }

    public Optional<Activity> getActivityById(int id) {
        return activities.stream().filter(activity -> activity.getId() == id).findFirst();
    }

    public void editActivity(int id, String newName, int newDuration) {
        Optional<Activity> activityOptional = getActivityById(id);

        while (!activityOptional.isPresent()) {
            System.out.println("Invalid ID. Please enter a valid ID: ");
            id = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            activityOptional = getActivityById(id);
        }

        activityOptional.ifPresent(activity -> {
            if (newName != null && !newName.isEmpty()) {
                activity.setName(newName);
            }
            if (newDuration > 0) {
                activity.setDuration(newDuration);
            }
        });
    }

    public void deleteActivity(int id) {
        activities.removeIf(activity -> activity.getId() == id);
        // Reassign IDs after deletion
        for (int i = 0; i < activities.size(); i++) {
            activities.get(i).setId(i + 1);
        }
    }

     // Calculate miles from steps (assuming average stride length of 2.5 feet)
    private double calculateMiles(int steps) {
        double rawStepsToMiles = (steps * 2.5) / 5280.0; // 5280 feet in a mile
        return Math.round(rawStepsToMiles * 10.0) / 10.0;
    }

    public void displayAllActivities(LocalDate date, int steps, double sleepHours) {
        // Display the date, step count, distance, and sleep hours
        System.out.println("DATE: " + date);
        System.out.println("STEP COUNT: " + steps);
        System.out.println("APPROXIMATE DISTANCE: " + calculateMiles(steps) + " miles");
        System.out.println("SLEEP HOURS: " + sleepHours);
        System.out.println("ACTIVITIES AND DURATIONS:");

        // Display all activities
        for (Activity activity : activities) {
            System.out.println("\t" + activity.toString());
        }
    }
    
    
}