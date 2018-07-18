package pl.coderslab.fifthtask;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class PopularWordsFinder {
    public static void main(String[] args) {
        readHeadlines();

    }

    /**
     * This method read headlines from one of the most popular websites and writes every single word to a text file,
     * omitting any special characters.
     */
    static void readHeadlines() {

        Connection connect = Jsoup.connect("http://www.onet.pl/");
        try {
            Document document = connect.get();
            Elements links = document.select("span.title");
            try {
                FileWriter fw = new FileWriter("popular_words.txt", true);
                for (Element elem : links) {
                    StringTokenizer strToken = new StringTokenizer(elem.text().trim().replaceAll("[^\\p{L} ]","")); // ^ not Unicode or space
                    String element;
                    while (strToken.hasMoreTokens()) {
                        element = strToken.nextToken();
                        if (element.length() > 2)
                            fw.append(element + "\n");
                    }
                }
                fw.close();
            } catch (IOException ex) {
                System.out.println("Error writing to file");
            }

        } catch
                (IOException e) {
            e.printStackTrace();
        }

        filterHeadlines("popular_words.txt", "words_filter.txt");
        sortedPopularWords("filtered_popular_words.txt");
    }

    /**
     *  This method sorts the filtered popular words file and creates a new text file with
     *  numerical values describing each word count.
     * @param filename Name of the file with words to sort.
     */
    private static void sortedPopularWords (String filename) {
        Path path = Paths.get(filename);
        ArrayList<String> uncounted = new ArrayList<>();
        ArrayList<String> counted = new ArrayList<>();
        int wordCounter = 0;
        try {
            Scanner scn = new Scanner(path);
            while (scn.hasNextLine()){
                uncounted.add(scn.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Cannot read from file.");
        }
        Collections.sort(uncounted);
        for (int i  = 0; i < uncounted.size() -1; i++) {
            if (uncounted.get(i).equalsIgnoreCase(uncounted.get(i+1))){
                wordCounter++;
            } else {
                counted.add(String.valueOf(wordCounter) + " " + uncounted.get(i));
                wordCounter = 1;
            }
        }

        Collections.sort(counted, new Comparator<String>() {
            public int compare(String s1, String s2) {
                int pop1 = Integer.parseInt(s1.split(" ")[0]);
                int pop2 = Integer.parseInt(s2.split(" ")[0]);

                /*For ascending order*/
                //return pop1-pop2;
                return pop2 - pop1;
            }
        });

        Path mostPopularWords = Paths.get("most_popular_words.txt");
        try {
            Files.write(mostPopularWords,counted);
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }

    }

    /**
     * This method creates a collection of words from a user defined file, and uses it to filter
     * a list of words from a different file. The outcome is then written to 'filtered_popular_words.txt' file
     * @param filename File with words to filter.
     * @param filterFilename Name of the file containing filter words.
     */
    private static void filterHeadlines (String filename, String filterFilename) {
        Path path = Paths.get(filename);
        Path filterPath = Paths.get(filterFilename);
        ArrayList<String> filter = new ArrayList<>();

        Scanner scan;
        try {
            scan = new Scanner(filterPath);
            while (scan.hasNextLine()) {
                filter.add(scan.nextLine().trim());
            }
        } catch (IOException e) {
            System.out.println("Cannot read from file.");
        }

        try {
            scan = new Scanner(path);
            FileWriter filew = new FileWriter("filtered_popular_words.txt", true);
            while (scan.hasNextLine()) {
                String str = scan.nextLine();
                if(!filter.contains(str))
                    filew.append(str + "\n");

            }
            filew.close();
        } catch (IOException e) {
        System.out.println("Cannot read from file.");
        e.printStackTrace();
    }
    }

}

