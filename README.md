My Personalized Fitness Tracker

Description:

MyFitnessTracker.java:
This is the main class that takes user input to determine the next action and uses the other classes (described below) to complete these actions.  

User.java:
This class stores the user's name, age, weight, and height within a text file called "user_profile.txt".  This class is used within the main ActivityTracker class.

ActivityTracker.java:

Cal_Manager.java:
This class provides the menu that allows the user to choose action's concerning calorie calculation and management. Manages both the Cal_Tracker and BMR_Calc class.

Cal_Tracker.java:
This class tracks and updates a user's daily calorie intake, storing the data in a file, and allows the user to input their calorie consumption for the current day.

BMR_Calc.java: 
This class calculates a user's Basal Metabolic Rate (BMR) and daily caloric needs based on personal information (age, weight, height, sex, and activity level), which it retrieves from files or user input.

Goals.java: 
This class stores the user's weight, step, and activity duration goal within a text file called "goals.txt".  This class uses the ActivityDuration class to store its activity           duration goal (this is comprised of move, exercise, and stand hours) and this class is used within the main ActivityTracker class.  

ActivityDuration.java: 
This class creates an activity duration object that stores the user's goal of moving, exercising, and standing hours.  This class is used with the Goals class.

BMI_Calc.java: 
This class calculates the BMI of the user and depending on whether they have created a profile (based on user_profile.txt) will request information and calculate the BMI and providing a short description of what their BMI indicates about their health. 

ActivityGenerator.java: 

