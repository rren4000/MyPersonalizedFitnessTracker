My Personalized Fitness Tracker

Description:

MyFitnessTracker.java
\nThis is the main class that takes user input to determine the next action and uses the other classes (described below) to complete these actions.  

User.java
\nThis class stores the user's name, age, weight, and height within a text file called "user_profile.txt".  This class is used within the main ActivityTracker class.

ActivityTracker.java

Cal_Manager.java

Calc_Tracker.java

BMR_Calc.java

Goals.java
\nThis class stores the user's weight, step, and activity duration goal within a text file called "goals.txt".  This class uses the ActivityDuration class to store its activity           duration goal (this is comprised of move, exercise, and stand hours) and this class is used within the main ActivityTracker class.  

ActivityDuration.java
\nThis class creates an activity duration object that stores the user's goal of moving, exercising, and standing hours.  This class is used with the Goals class.

BMI_Calc.java

ActivityGenerator.java

