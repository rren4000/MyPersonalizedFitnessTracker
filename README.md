MY PERSONALIZED FITNESS TRACKER

OVERALL DESCRIPTION:
Welcome to MY PERSONALIZED FITNESS TRACKER! This program is designed to help you keep track of your "fitness" including stats about your health.
It is intended to be used by one person! Users should start by inputting their profile information. Then, they will have
the ability to track their activities, calories, set goals, calculate their bmi, and generate activity suggestions! 



Class Description:

MyFitnessTracker.java: This is the main class that takes user input to determine the next action and uses the other classes (described below) to complete these actions.

User.java: This class stores the user's name, age, weight, and height. This class includes constructors, getters, and setters.  This class is used within the main class (MyActivityTracker) as well as parameters for methods within 'File_User_Data_Saver.java' and 'User_Input_handler.java'.

User_Data_Saver.java: This class is an interface with a saveData() method that uses 'User' objects as a parameter.  This class is implemented by 'File_Goal_Data_Saver'.

File_User_Data_Saver.java: This class is responsible for implementing 'User_Data_Saver.java' interface and its saveData() method.  This saves all of the information given by the user about their goals to a text file called "user_profile.txt". This class uses 'User' objects as parameters within its method and is invoked by the main class (MyFitnessTracker).

User_Input_Handler: This class is responsible for taking user input and setting the user's profile (name, age, weight, and height).  This class uses 'User' objects within its parameters and is invoked by the main class (MyFitnessTracker).

ActivityTracker.java: This class contains 7 features:
  1. Add new activity: (activity name + duration)
  2. Edit an activity: (allows user to change activity name and duration by the activity id number)
  3. Delete an activity: (allows user to delete an activity by the activity id number)
  4. Step Count: (takes amount of steps from the user. this also converts the step counts to distance in miles travelled assuming average stride of 2.5 feet)
  5. Calculate hours of sleep: (calculate hours slept based on bedtime and awake time entered. Note: this uses military time)
  6. Display all saved activity information: (displays the following data: DATE, STEPCOUNT, APPROXIMATE DISTANCE, SLEEP HOURS, ACTIVITIES AND DURATIONS)
  7. Save All Data and Return to main menu: (saves all data to file called ActivityFile.txt)

ActivityObj.java: This class creates an activity object with all necessary information about an activity (as it assigns an id to the activity for editing and deleting purposes,
an activity name, and the duration), whilst providing methods to access and modify that information. This class is used within the ActivityTracker class.

Activity.java: interface used by ActivityObj.java. Contains getting and setting id, name, and duration.

ActivityTrackerHandler.java: encapsulates the logic for handling and tracking basic activity-related data such as steps, sleep hours, and date. It also includes a method to calculate the distance in miles based on the number of steps. The class interacts with an ActivityManager.

ActivityManager.java: manages a list of activities and provides functionalities to add, edit, delete, and display activities along with some additional functionalities related to tracking steps and sleep.

File_ActivityTracker_Saver.java: saves activity tracker information to a text file.

Cal_Manager.java:
This class provides the menu that allows the user to choose action's concerning calorie calculation and management. Manages both the Cal_Tracker and BMR_Calc class and creates such objects according to user input.

Cal_Tracker.java:
This class is responsible for managing the user's daily calorie intake. It initializes by loading existing calorie data from a specified file (calorie_tracker_data.txt) for the current date. Users can add calorie entries, which are added to calculate the total daily intake. The class interacts with other components like Cal_Tracker_UI for user input and Cal_Tracker_FileHandler for data updating.

BMR_Calc_Handler.java: 
This class is responsible for calculating and managing the user's Basal Metabolic Rate (BMR). It collects user information such as weight, height, age, sex, and activity level, and works with the BMR_Calculator class to perform the BMR calculation. The BMR_UI class handles user input and interaction, while BMR_FileHandler takes care of saving and loading BMR data. Under the management of this class, these components help users easily calculate and keep track of their metabolic rate.

Goals.java: This class stores the user's weight, step, and activity duration goal. This class uses an 'ActivityDuration.java' object  to store its activity duration goal (this is comprised of move, exercise, and stand hours) and this class is used within the main class (MyFitnessTracker). Objects of this class are also used as parameters in 'Goal_Input_Handler.java' and 'File_Goal_Data_Saver.java'. THis class includes constructors, getters, and setters.

Goal_Data_Saver: This class is an interface with a saveData() method that uses 'Goals' objects as a parameter.  This class is implemented by 'File_Goal_Data_Saver.java'.

File_Goal_Data_Saver: This class is responsible for implementing 'Goal_Data_Saver.java' interface and its saveData() method.  This saves all of the information given by the user about their goals to a text file called "goals.txt". This class uses 'Goals' objects as parameters within its method and is invoked by the main class (MyFitnessTracker).

Goal_Input_Handler: This class is responsible for taking user input and setting weight, step, and/or activity duration goals.  This class uses 'Goals' objects within its parameters and is invoked by the main class (MyFitnessTracker).  This class includes constructors and setting methods. 

ActivityDuration: This class is used within 'Goals.java' in order to set the activity duration goal.  This involves a goal of the number of hours the user would like to move, exercise, and stand.  This class includes constructors, getters, and setters. 

BMI_Calc_Handler.java:
This class is designed to calculate and manage the user's Body Mass Index (BMI). It works by gathering user data, such as weight and height, through the BMIInputHandler class and then uses the BMICalculator class to perform the BMI calculation. The results are displayed using the BMIDisplay class, while the BMIFileHandler class manages the saving and loading of BMI data.

ActivityGenerator.java: This class generates random activity suggestions to the user. If the user wants more suggestions, they can simply type 'r'.
