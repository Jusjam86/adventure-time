package com.pluralsight;

import java.io.*;
import java.util.*;

public class AdventureTime {

    static ArrayList<StepClass> adventureSteps;

    static void main() {

        adventureSteps = loadAdventureTime();
        homeScreen();

    }

    public static void homeScreen() {

        Scanner userInput = new Scanner(System.in);

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
    }

    public static void gameScreen(int id) {
        for (int i = 0; i < adventureSteps.size(); i++){
            StepClass step = adventureSteps.get(i);
            if (step.getId() == id){
                System.out.println("Story Text: " + step.getStoryText());
                System.out.println();
                System.out.println("1) " + step.getOption1Text());
                System.out.println();
                System.out.print("2) " + step.getOption2Text());
                System.out.println();

            }

        }
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
