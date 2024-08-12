import java.time.LocalDate;

public class ActivityTrackerHandler {
    private ActivityManager activityManager;
    private int steps;
    private double sleepHours;
    private LocalDate date;

    public ActivityTrackerHandler() {
        this.activityManager = new ActivityManager();
        this.steps = 0;
        this.sleepHours = 0.0;
        this.date = LocalDate.now();
    }

    public ActivityManager getActivityManager() {
        return activityManager;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public double getSleepHours() {
        return sleepHours;
    }

    public void setSleepHours(double sleepHours) {
        this.sleepHours = sleepHours;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double calculateMiles() {
        double rawStepsToMiles = (steps * 2.5) / 5280; // 5280 feet in a mile
        return Math.round(rawStepsToMiles * 10.0) / 10.0;
    }
}
