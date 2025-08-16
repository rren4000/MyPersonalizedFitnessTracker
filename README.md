# My Personalized Fitness Tracker (Java)

New York University – Object-Oriented Programming (Spring 2022)

## Course Context

This repository contains the final **group project** for NYU’s Object-Oriented Programming course. The assignment was to design and implement a fully functional Java application applying principles of encapsulation, inheritance, interfaces, and file persistence. Our team built *My Personalized Fitness Tracker*, a console-based tool to help users manage health and activity data.

**Tech Stack**: Java · Object-Oriented Design · File I/O

## Project Overview

*My Personalized Fitness Tracker* allows users to create a personal profile, log activities, track calories, calculate BMI and BMR, set fitness goals, and generate simple activity suggestions. The project demonstrates the integration of multiple OOP concepts into a cohesive system, with clear separation of concerns across classes and interfaces.

### Features

* **User Profile Management** – Store and update personal data such as name, age, weight, and height.
* **Activity Tracking** – Add, edit, delete, and display activities (with step count, duration, and sleep hours).
* **Calorie Tracking** – Manage daily calorie intake and calculate BMR based on user profile.
* **Goal Setting** – Set goals for weight, activity, steps, and duration.
* **BMI Calculation** – Compute and store BMI using user data.
* **Data Persistence** – Save user profile, activities, and goals to local files.
* **Activity Suggestions** – Generate random activity recommendations.

### Code Structure Highlights

* **User & Profile Handling**: `User.java`, `User_Input_Handler.java`, `File_User_Data_Saver.java`
* **Activity Tracking**: `ActivityTracker.java`, `ActivityObj.java`, `ActivityManager.java`, `File_ActivityTracker_Saver.java`
* **Calories & BMR**: `Cal_Manager.java`, `Cal_Tracker.java`, `BMR_Calc_Handler.java`
* **Goals & Progress**: `Goals.java`, `Goal_Input_Handler.java`, `File_Goal_Data_Saver.java`
* **Health Calculators**: `BMI_Calc_Handler.java`, `ActivityDuration.java`
* **Main Application**: `MyFitnessTracker.java` orchestrates user interaction across modules.

## Skills Developed

* Collaborating effectively on a multi-file, team-based project.
* Designing modular Java applications with multiple classes and interfaces.
* Applying OOP principles (encapsulation, inheritance, interfaces, abstraction).
* Implementing file-based persistence for saving/loading user data.
* Handling user input and managing program state across sessions.

## How to Run

Compile and run using the Java compiler or your preferred IDE:

```bash
javac MyFitnessTracker.java
java MyFitnessTracker
```

Make sure that the project’s text files (`user_profile.txt`, `goals.txt`, `ActivityFile.txt`, `calorie_tracker_data.txt`) are accessible in the same working directory.
