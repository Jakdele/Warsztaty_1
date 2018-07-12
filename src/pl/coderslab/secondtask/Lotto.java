package pl.coderslab.secondtask;

import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Lotto {

    public static void main(String[] args) {

        lottoCheck();

    }


    /**
     * This method compares user generated numbers against random numbers
     * and checks for any matches.
     */
    public static void lottoCheck () {
        Integer[] user = generateArray();
        Integer[] rand = generateNumbers();
        int counter = 0;

        for (int i = 0; i < rand.length; i++) {
            if (Arrays.asList(rand).contains(user[i]))
                counter++;
        }


        if (counter >= 3) {
            System.out.println("You've won! You matched " + counter + " numbers.");
        } else {
            System.out.println("Tough luck. try next time");
        }


    }

    /**
     *
     * This method generates an array of 49 numbers, shuffles it and cuts it to a length of six,
     * in order to obtain six random numbers.
     */
    public static Integer [] generateNumbers (){
        Integer[] arr = new Integer[49];
        for (int i = 0; i < arr.length; i++)
            arr[i] = i + 1;

        Collections.shuffle(Arrays.asList(arr));

        Integer [] arrSix = Arrays.copyOf(arr,6);
       System.out.println("Generated numbers: " + Arrays.toString(arrSix));

        return Arrays.copyOf(arr,6);

    }

    /**
     *
     * This method prompts the user to put a number and checks if input is correct
     */

    public static int getNumber () {
        Scanner scn = new Scanner(System.in);

        try {
            System.out.println("Put a number ranging 1 - 49");
            return scn.nextInt();

        } catch (InputMismatchException ex) {
            System.out.println("You have to input a number");
            return getNumber(); //We invoke the method again, if input was mismatched
        }
    }

    /**
     *
     * This method generates an array of 6 numbers based on the user input
     */
    public static Integer[] generateArray (){
        Integer [] userSix = new Integer [6];

        for (int i = 0; i < userSix.length; i++) {
            int tmpNumber = getNumber();
            if (Arrays.asList(userSix).contains(tmpNumber)){
                System.out.println("Number already exists, choose a different one");
                i--; //This decreases the iterator, so the array will have 6 valid elements
            } else if (tmpNumber > 49 || tmpNumber <=0) {
                System.out.println("Number out of bounds");
                i--;
            } else {
                userSix[i] = tmpNumber;
            }
        }

        Arrays.sort(userSix);
        System.out.println("Your numbers: " + Arrays.toString(userSix));
        return userSix;
    }

}
