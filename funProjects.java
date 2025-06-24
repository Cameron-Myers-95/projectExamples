package funProjects;
import java.util.*;

public class funProjects 
{



/**
 * TextAnalyzer.java
 * 
 * This program analyzes user-inputted text to provide statistics such as word count,
 * sentence count, average word length, and the most frequent word.
 * 
 * @author Cameron Myers
 * @version March 23, 2025
 */

 
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        
        // Prompt user for input
        System.out.println("Enter your text below:");
        String input = scanner.nextLine();
        scanner.close();

        // Split input into words and sentences
        String[] words = input.trim().toLowerCase().split("\\s+");
        String[] sentences = input.split("[.!?]+");

        Map<String, Integer> wordFreq = new HashMap<>();
        int totalWordLength = 0;

        // Count word frequency and accumulate word lengths
        for (String word : words) {
            // Remove punctuation from each word
            String cleanWord = word.replaceAll("[^a-zA-Z0-9]", "");
            if (cleanWord.isEmpty()) continue;

            totalWordLength += cleanWord.length();
            wordFreq.put(cleanWord, wordFreq.getOrDefault(cleanWord, 0) + 1);
        }

        // Find the most frequently occurring word
        String mostCommonWord = null;
        int maxFrequency = 0;
        for (Map.Entry<String, Integer> entry : wordFreq.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                mostCommonWord = entry.getKey();
                maxFrequency = entry.getValue();
            }
        }

        // Display results
        System.out.println("\n=== Text Analysis Results ===");
        System.out.println("Total words: " + words.length);
        System.out.println("Total sentences: " + sentences.length);
        System.out.println("Average word length: " +
            (words.length > 0 ? String.format("%.2f", (double) totalWordLength / words.length) : "N/A"));
        System.out.println("Most frequent word: " +
            (mostCommonWord != null ? mostCommonWord + " (" + maxFrequency + "x)" : "None"));
    }
}







