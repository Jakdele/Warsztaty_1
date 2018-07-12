package pl.coderslab.firsttask;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GuessNumber {
    public static void main(String[] args) {

        guessNumber();

    }

    /**
     * This method takes a random number and number input by user, compares them,
     * and gives the user hints if the numbers are different.
     *
     */
    public static void guessNumber () {
        int target = generateRandom();
        int number = getNumber();
        int counter = 0;

        while (number != target) {

            if (number > target) {
                System.out.println("Too high!");
            } else {
                System.out.println("Too low!");
            }
            counter++;
            number = getNumber();

        }
        System.out.println("You guessed right in "+ counter + " tries." + " The number was: " + target);

    }

    /**
     *
     * This method generates a random number from 1 to 100
     */

    public static int generateRandom () {
        Random random = new Random();

        return random.nextInt(99) + 1;
    }

    /**
     *
     * This method asks user to input a number
     * If user inputs anything else it asks again
     */
    public static int getNumber () {
        Scanner scn = new Scanner(System.in);


        try {
            System.out.println("Put a number ranging 1 - 100");

            return scn.nextInt();

        } catch (InputMismatchException ex) {
            //scn.next();
            System.out.println("You have to input a number");
        }
        return getNumber(); //We invoke the method again, if input was mismatched
    }
}


