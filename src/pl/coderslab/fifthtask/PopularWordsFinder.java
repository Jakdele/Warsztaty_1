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
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

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
                   // StringTokenizer strToken = new StringTokenizer(elem.text().trim().replaceAll("[\\?\\.\\,\\:\\;\\(\\)\\!\"]",""));
                    StringTokenizer strToken = new StringTokenizer(elem.text().trim().replaceAll("[^\\p{L} ]","")); // ^ not Unicode or space
                    String element;
                    while (strToken.hasMoreTokens()) {
                        element = strToken.nextToken();
                        if (element.length() > 2)
                            fw.append(element + "\n");
                       // System.out.println(elem.text());
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
        String [] wordsFilter = {"oraz", "lub", "ponieważ", "także", "nie", "jest", "otrzyma", "się", "jak", "ale"};

        filterHeadlines("popular_words.txt", wordsFilter);
    }

    /**
     * This method reads from a file, and checks the contents of a file against a user defined array of words,
     * and writes words filtered in that way to a new text file.
     * @param filename The name of the file from which to read.
     * @param array A user created array containing words to filter.
     */
    private static void filterHeadlines (String filename, String [] array) {
        Path path = Paths.get(filename);

        try {
            Scanner scan = new Scanner(path);
            FileWriter filew = new FileWriter("filtered_popular_words.txt", true);
            while (scan.hasNextLine()) {
                String str = scan.nextLine();
                if(!Arrays.toString(array).contains(str))
                    filew.append(str + "\n");

            }
            filew.close();
        } catch (IOException e) {
        System.out.println("Cannot read from file.");
        e.printStackTrace();
    }
    }

}

