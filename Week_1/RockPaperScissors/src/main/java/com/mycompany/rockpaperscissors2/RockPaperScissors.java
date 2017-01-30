package com.mycompany.rockpaperscissors2;

import java.util.Random;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author apprentice
 */
public class RockPaperScissors {

    public static void main(String[] args) {

        Random randomizer = new Random();
        Scanner inputReader = new Scanner(System.in);

        int rock = 1;
        int paper = 2;
        int scissors = 3;
        int round;
        int weapon2 = 0;
        int countOfWins = 0;
        int countOfLosses = 0;
        int countOfTies = 0;
        int compChoice = randomizer.nextInt(2) + 1;
        String weapon;
        String replay;

        //Ask how many rounds you want to be played//
        do {
            System.out.println("How many rounds do you want to play? ");
            round = inputReader.nextInt();
            inputReader.nextLine();
            //Error message if that number doesn't apply//
            if (round > 10) {
                System.out.println("Error Message: Please type a number between 1 and 10.");

                //Choose rock, paper, or scissors//
            } else {
                System.out.println("Please choose a 'weapon'.");
            }
            weapon = inputReader.nextLine();

            //Turning weapons into number mapping//
            if (weapon.equals("rock")) {
                weapon2 = 1;
            } else if (weapon.equals("paper")) {
                weapon2 = 2;
            } else if (weapon.equals("scissors")) {
                weapon2 = 3;
            }

            //Number of Rounds Loop//
            for (round = 1; round <= 10; round++) {
                {

                    //Possibilities of Game//
                    if (weapon2 == rock && compChoice == paper) {
                        System.out.println("You lose.");
                        countOfLosses += 1;
                    }
                    if (weapon2 == rock && compChoice == scissors) {
                        System.out.println("You win. Whoo!");
                        countOfWins += 1;
                    }
                    if (weapon2 == paper && compChoice == rock) {
                        System.out.println("You win. Whoo!");
                        countOfWins += 1;
                    }
                    if (weapon2 == paper && compChoice == scissors) {
                        System.out.println("You lose.");
                        countOfLosses += 1;
                    }
                    if (weapon2 == scissors && compChoice == rock) {
                        System.out.println("You lose");
                        countOfLosses += 1;
                    }
                    if (weapon2 == scissors && compChoice == paper) {
                        System.out.println("You win. Whoo!");
                        countOfWins += 1;
                    }
                    if (weapon2 == compChoice) {
                        System.out.println("Tie game.");
                    }
                    countOfTies += 1;
                }
            }
            System.out.println("Number of Wins:" + countOfWins);
            System.out.println("Number of Losses: " + countOfLosses);
            System.out.println("Number of Ties: " + countOfTies);

            System.out.println("Do you want to play again?");
            replay = inputReader.nextLine();
            if ((replay == "no") || (replay == "No")) {
                System.out.println("Thanks for playing!");
            }
        } while ((replay == "yes") || (replay == "Yes"));

    }
}