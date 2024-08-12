import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.*;


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

    public void displayAllActivities() {
        for (Activity activity : activities) {
            System.out.println(activity.toString());
        }
    }
    
}