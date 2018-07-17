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
        int counter = 1;
        int min = 0;
        int max = 1000;
        String answer;
        boolean win = false;
        while (true) {
            int guess = (((max - min) / 2) + min);
            System.out.println("Zgaduję : " + guess);
            System.out.println("Zgadłem?");


            // while (scn.hasNextLine()) {
            answer = userAnswer();
            if (answer.equals("tak")) {
                System.out.println("Wygrałem!");
                System.out.println("W " + counter + " krokach");
                break; //przerywa i wraca do flagi.
            }

            System.out.println("Za dużo?");
            answer = userAnswer();
            if (answer.equals("tak")) {
                counter++;
                max = guess;
            } else {

                System.out.println("Za mało?");
                answer = userAnswer();
                if (answer.equals("tak")) {
                    counter++;
                    min = guess;
                } else {
                    System.out.println("Nie oszukuj!");
                    counter--;
                }

                //}
            }
        }
    }

    /**
     * This method prompts user to enter an answer, and if the answer is different than
     * either 'yes' or 'no', the method is invoked again.
     * @return The answer.
     */
    public static String userAnswer() {
        Scanner scn = new Scanner(System.in);
        String answer = "";
        answer = scn.nextLine().trim().toLowerCase();
        if(answer.equals("tak") || answer.equals("nie")){
        } else {
            System.out.println("Odpowiedz 'tak', albo 'nie'.");
            answer = userAnswer();
        }
        return answer;
    }




}
