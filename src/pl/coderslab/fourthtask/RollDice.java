package pl.coderslab.fourthtask;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RollDice {
    public static void main(String[] args) {

        String[] testRolling = {"2D10+10", "D100-10","fdsfh35", "-5D6" , "D100"};
        for (String roll : testRolling)
            rollDice(roll);


    }

    /**
     *  THis method takes a string and tries to match it against a regular expression coding dice throws.
     *  If match is found, it returns a reuslt according to the throw parameters (i.e. number of throws, die type and throw modifier).
     *  If input string doesn't match the defined pattern, it writes a message stating that in the console.
     * @param command A string which should contain dice throw instructions.
     * @return An integer with the result. If no match was found, it returns 0.
     */

    public static int rollDice (String command) {
        int [] diceTypeArray = { 3, 4, 6, 8, 10, 12, 20, 100 };
        int numberOfThrows = 0;
        int diceType = 0;
        int throwMod = 0;
        int result = 0;


        Pattern pat = Pattern.compile("(\\d*)D(\\d+)([-+]*\\d*)", Pattern.CASE_INSENSITIVE);
        Matcher m = pat.matcher(command.trim());


           if (m.matches()) {
               try {
                   if (m.group(1).equals("")) {
                       numberOfThrows = 1; //So the string doesn't have to contain '1D6'. It can contain just 'D6', and the program will set the number of dice to 1.
                   } else {
                       numberOfThrows = Integer.parseInt(m.group(1));
                   }
               } catch (Exception e) {
                   System.out.println("Wrong number format.");
                   return 0;
               }

               try {
                   if (m.group(3).equals("")) {
                       throwMod = 0;
                   } else {
                       throwMod = Integer.parseInt(m.group(3));
                   }
               } catch (NumberFormatException nfe) {
                   System.out.println("Wrong number format");
               }

               try {
                   diceType = Integer.parseInt(m.group(2));
               } catch (NumberFormatException nfe) {
                   System.out.println("Wrong number format");
               }

               if (ArrayUtils.contains(diceTypeArray, diceType)) {
                   for (int i = 1; i <= numberOfThrows; i++) {
                       result += generateNUmber(diceType);
                   }
                   int end = result + throwMod;
                   System.out.println("You rolled " + numberOfThrows + " times D" + diceType + " with a modifier of " + throwMod + ", and the result is: " + end);
                   return result + throwMod;
               } else {
                   System.out.println("There's no such dice.");
               }
           } else {
               System.out.println("Wrong input format");
           }

       return result;

    }

    /**
     *  This method generates a random number from  0 to an upper bound received as a parameter.
     * @param max The upper bound for random number generation.
     * @return A random number.
     */
    private static int generateNUmber (int max) {
        Random rand = new Random();
        return rand.nextInt(max +1);
    }

}
