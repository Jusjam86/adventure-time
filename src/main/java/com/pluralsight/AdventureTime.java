package com.pluralsight;

import java.io.*;
import java.util.*;


public class AdventureTime {

    static Scanner userInput = new Scanner(System.in);

    static ArrayList<StepClass> adventureSteps;

    static void main() {

        adventureSteps = loadAdventureTime();
        homeScreen();

    }

    public static void homeScreen() {

        System.out.println("Welcome to Adventure Time.");
        System.out.println("----------------------------------------");
        System.out.println("Press (P) to play ");
        System.out.println("Press (Q) to quit ");
        System.out.println("===========================");
        System.out.println("Your choice: ");

        String choice = userInput.nextLine().toUpperCase().trim();
        if (choice.equals("P")) {
            gameScreen(1);

        }
        else if (choice.equals("Q")){
            System.out.println("Exiting game.");

        }

    }


    public static void gameScreen(int id) {

        // 1 - finding the step
        int nextId = id;

        while (nextId != -1) {

            StepClass step = findStepClass(nextId);

            if (step == null) {

                System.out.println();
                System.out.println("An error has occurred. Step was not found.");

            }
            else
            {
                // 2 - display the step info
                System.out.println();
                System.out.println("Story Text: " + step.getStoryText());
                System.out.println();
                System.out.println("1) " + step.getOption1Text());
                System.out.println("2) " + step.getOption2Text());
                System.out.println();
                System.out.print("Make a selection: ");
                String optionSelect = userInput.nextLine().strip().toLowerCase();

                switch (optionSelect) {
                    case "1":
                        nextId = step.getOption1NextId();
                        break;
                    case "2":
                        nextId = step.getOption2NextId();
                        break;

                }

            }
        }
    }

    public static StepClass findStepClass(int id) {

        // gets arraylist size
        for (int i = 0; i < adventureSteps.size(); i++)
        {
            StepClass stepClass = adventureSteps.get(i);
            if (stepClass.getId() == id) {

                return stepClass;
            }

        }
        return null;
    }


    public static ArrayList<StepClass> loadAdventureTime(){

        // create the container
//        StepClass[] steps = new StepClass[100];
        // ArrayLists grow as needed when you add more items
        ArrayList<StepClass> steps = new ArrayList<>();

        // populate the container
        try{
            FileReader fileReader = new FileReader("adventure1.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();

            line = bufferedReader.readLine();

            while (line != null){

                String[] columns = line.split("\\|");
                int id = Integer.parseInt(columns[0]);
                String storyText = columns[1];
                String option1Text = columns[2];
                int option1NextId = Integer.parseInt(columns[3]);
                String option2Text = columns[4];
                int option2NextId = Integer.parseInt(columns[5]);

                // create a Step from the data in the current line
                StepClass stepClass = new StepClass(id, storyText, option1Text,
                        option1NextId, option2Text, option2NextId);

                // add the current step to the container (ArrayList)
                steps.add(stepClass);

//                System.out.println(stepClass.getStoryText());

                line = bufferedReader.readLine();
            }
            fileReader.close();

        }
        catch (Exception ex) {
            System.err.println(ex);
        }
        // return the container
        return steps;
    }
}
