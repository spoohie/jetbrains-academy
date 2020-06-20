package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class ReadabilityIndexer {
    private String text;
    private double numOfCharacters;
    private double numOfWords;
    private double numOfSentences;
    private double score;

    ReadabilityIndexer(String text) {
        this.text = text;
        this.numOfCharacters = text.replace(" ", "").length();
        this.numOfWords = text.split(" ").length;
        this.numOfSentences = text.split("[.!?]").length;
        this.score = 4.71 * (numOfCharacters/numOfWords) + 0.5*(numOfWords/numOfSentences) - 21.43;
    }

    public double getNumOfChars() {
        return numOfCharacters;
    }

    public double getNumOfWords() {
        return numOfWords;
    }

    public double getNumOfSentences() {
        return numOfSentences;
    }

    public double getScore() {
        return score;
    }

    public String resolveReadersAge() {
        String[] scores = { "5-6", "6-7", "7-9", "9-10", "10-11", "11-12", "12-13", "13-14", "14-15", "15-16",
                "16,17", "17-18", "18-24", "24+" };

        int s = (int) Math.floor(score);

        return scores[s];
    }
}

public class Main {
    public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Wrong number of arguments!");
            return;
        }

        String text;
        try {
            text = readFileAsString(args[0]);
        } catch (IOException e) {
            System.out.println("Cannot read file: " + e.getMessage());
            return;
        }

        ReadabilityIndexer indexer = new ReadabilityIndexer(text);

        System.out.println("The text is: " + text + "\n");
        System.out.format("Words: %f%n", indexer.getNumOfWords());
        System.out.format("Sentences: %f%n", indexer.getNumOfSentences());
        System.out.format("Characters: %f%n", indexer.getNumOfChars());
        System.out.format("The score is: %f%n", indexer.getScore());
        System.out.println("This text should be understood by " + indexer.resolveReadersAge() + " year olds.");
    }
}