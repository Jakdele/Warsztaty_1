package pl.coderslab.thirdtask;

import java.util.Scanner;

public class GuessNumbers2 {
    public static void main(String[] args) {
        letMeGuess();

    }

    /**
     * This method guesses the number between 0 and 1000 in max 10 steps.
     * The algorithm divides the interval in half, prints that number, asks the user if that number is too high,
     * and sets that number as a new upper bound if true.
     * If not, it asks the user if the is too low, and sets it as a lower bound if true. If false it asks the user not to cheat.
     */
    public static void letMeGuess () {
        Scanner scn = new Scanner(System.in);
        System.out.println("Pomyśl liczbę od 1 do 1000, a ja ją zgadnę w max. 10 krokach.");
        int counter = 0;
        int min = 0;
        int max = 1000;
        String answer;
        boolean win = false;
        loop1: //flaga
        while (true) {
            int guess = (((max - min) / 2) + min);
            System.out.println("Zgaduję : " + guess);
            System.out.println("Zgadłem?");
            counter ++;

            while (scn.hasNextLine()) {
                answer = scn.nextLine();
                if (answer.equals("tak")) {
                    System.out.println("Wygrałem!");
                    System.out.println("W " + counter + " krokach");
                    break loop1; //przerywa i wraca do flagi.
                }

                System.out.println("Za dużo?");
                answer = scn.nextLine();
                if (answer.equals("tak")) {
                    max = guess;
                    break;
                }

                System.out.println("Za mało?");
                answer = scn.nextLine();
                if (answer.equals("tak")) {
                    min = guess;
                    break;
                } else {
                    System.out.println("Nie oszukuj!");
                    counter--;
                    break;
                }

            }
        }





    }



}
