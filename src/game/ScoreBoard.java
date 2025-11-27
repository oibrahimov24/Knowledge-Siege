package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The class for ScoreBoard
 */
public class ScoreBoard {
    /**
     * Path to the score file
     */
    public static final String SCORES_FILE="scores.txt";

    /**
     * Sorts the file contents for descending order and updates the file
     * @param username
     * @param score
     */
    public void recordVictory(String username, int score) {
        String record=username+" won by "+score+" points!";
        List<String> scores=new ArrayList<>();

        try (java.util.Scanner scanner=new java.util.Scanner(new File(SCORES_FILE))) {
            while (scanner.hasNextLine()) {
                String line=scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    scores.add(line);
                }
            }
        } catch (FileNotFoundException e) {
        	System.err.println(e.getMessage());
        }

        scores.add(record);

        scores.sort(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int score1=extractScore(s1);
                int score2=extractScore(s2);
                return Integer.compare(score2, score1);
            }
        });

        try (FileWriter writer=new FileWriter(SCORES_FILE)) {
            for (String line:scores) {
                writer.write(line+System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    /**
     * Extracts the scores
     * @param line
     * @return
     */
    private int extractScore(String line) {
        try {
            String scorePart=line.substring(line.indexOf("won by ")+7, line.indexOf(" points!"));
            return Integer.parseInt(scorePart.trim());
        } catch (Exception e) {
            System.err.println(line);
            return 0;
        }
    }
}