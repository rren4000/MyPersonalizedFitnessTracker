MY PERSONALIZED FITNESS TRACKER

OVERALL DESCRIPTION:
Welcome to MY PERSONALIZED FITNESS TRACKER! This program is designed to help you keep track of your "fitness" including stats about your health.
It is intended to be used by one person! Users should start by inputting their profile information. Then, they will have
the ability to track their activities, calories, set goals, calculate their bmi, and generate activity suggestions! 



Class Description:

MyFitnessTracker.java: This is the main class that takes user input to determine the next action and uses the other classes (described below) to complete these actions.

User.java: This class stores the user's name, age, weight, and height within a text file called "user_profile.txt". This class is used within the main ActivityTracker class.

ActivityTracker.java: This class contains 7 features:
  1. Date: (gets the current date)
  2. Edit an activity: (allows user to change activity name and duration by the activity id number)
  3. Delete an activity: (allows user to delete an activity by the activity id number)
  4. Step Count: (takes amount of steps from the user. this also converts the step counts to distance in miles travelled assuming average stride of 2.5 feet)
  5. Calculate hours of sleep: (calculate hours slept based on bedtime and awake time entered. Note: this uses military time)
  6. Display all saved activity information: (displays the following data: DATE, STEPCOUNT, APPROXIMATE DISTANCE, SLEEP HOURS, ACTIVITIES AND DURATIONS)
  7. Save All Data and Return to main menu: (saves all data to file called ActivityFile.txt)

ActivityObj.java: This class creates an activity object with all necessary information about an activity (as it assigns an id to the activity for editing and deleting purposes,
an activity name, and the duration), whilst providing methods to access and modify that information. This class is used within the ActivityTracker class.

Cal_Manager.java:

Calc_Tracker.java:

BMR_Calc.java:

Goals.java: This class stores the user's weight, step, and activity duration goal within a text file called "goals.txt". This class uses the ActivityDuration class to store its activity duration goal (this is comprised of move, exercise, and stand hours) and this class is used within the main ActivityTracker class.

ActivityDuration.java: This class creates an activity duration object that stores the user's goal of moving, exercising, and standing hours. This class is used with the Goals class.

BMI_Calc.java:

ActivityGenerator.java: This class generates random activity suggestions to the user. If the user wants more suggestions, they can simply type 'r'.
