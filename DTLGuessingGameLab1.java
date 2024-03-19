// Programmer Daniel Torres Larreta
// Class: CS 145
// Date 01/09/2024
// Assignemnt: Lab 1 Guessing Game 
// Purpose: This program will create a basic guessing game. This program will gather user data and be interactive with the user.
// Working on improving the basics and making a previous lab cleaner and more efficient. 
// Small issue: When typing yes to play again you have to type it twice to continue playing


import java.util.Random;
import java.util.Scanner;

public class DTLGuessingGameLab1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        //Game Introduction
        gameIntro();

        //Multiple Games
        while (playSingleGame(scanner)) {
            //Players choice to continute playing
            System.out.print("Would you like to play again? (yes/no): ");
            String playAgain = scanner.next();

            if (!playAgain.toLowerCase().startsWith("y")) {
                break;
            }
        }

        //End of Game Stats
        reportFinalStats();

        scanner.close();
    }

    //Game Introduction Method
    private static void gameIntro() {
        System.out.println("This program allows you to play a guessing game.");
        System.out.println("I will think of a number between 1 and 100 and will allow you to guess until you get it.");
        System.out.println("For each guess, I will tell you whether the right answer is higher or lower than your guess.");
    }

    //Single Game Method
    private static boolean playSingleGame(Scanner scanner) {
        Random random = new Random();
        int randNum = random.nextInt(100) + 1;

        System.out.println("\nNew game! Guess the number.");

        int guesses = 0;
        int bestGuess = Integer.MAX_VALUE;

        while (true) {
            System.out.print("Enter Guess: ");
            int playerGuess = scanner.nextInt();
            guesses++;

            if (playerGuess == randNum) {
                System.out.println("You guessed correctly in " + guesses + (guesses == 1 ? " guess!" : " guesses!"));

                //Update for bestGuess
                bestGuess = Math.min(bestGuess, guesses);
                break;
            } else if (playerGuess < randNum) {
                System.out.println("The correct answer is higher. Guess again.");
            } else {
                System.out.println("The correct answer is lower. Guess again");
            }


            }

            updateFinalStats(guesses, bestGuess);

            return playAgain(scanner);
        }

        // Final stats report method
        private static void reportFinalStats() {
            System.out.println("\nFinal Stats:");
            System.out.println("Total games played: " + GameStatistics.totalGames);
            System.out.println("Total attempted guesses: " + GameStatistics.totalGuess);
            System.out.printf("Average guesses made per game: %.1f%n", GameStatistics.averageGuesses());
            System.out.println("Fewest guesses in a single game: " + GameStatistics.bestGuess);
        }

        
        private static void updateFinalStats(int guesses, int bestGuess) {
            GameStatistics.totalGames++;
            GameStatistics.totalGuess += guesses;
            GameStatistics.bestGuess = Math.min(GameStatistics.bestGuess, bestGuess);
        }

        //Continue playing method
        private static boolean playAgain (Scanner scanner) {
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.next();
            return playAgain.toLowerCase().startsWith("y");
        }

        static class GameStatistics {
            static int totalGames = 0;
            static int totalGuess = 0;
            static int bestGuess = Integer.MAX_VALUE;

            static double averageGuesses() {
                return totalGames > 0 ? (double) totalGuess / totalGames : 0.0;
            }
        }

    }
    
