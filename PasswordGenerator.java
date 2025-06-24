package funProjects;

import java.util.*;

public class PasswordGenerator
{


/**
 * PasswordTool.java
 *
 * This program allows users to generate a secure password based on their input preferences
 * (such as length and character types), and then evaluates the strength of the generated password.
 *
 * Author: Cameron Myers
 * Version: January 2025
 */



    // Character sets for building passwords
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_=+<>?";

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Password Generator & Strength Checker ===");

        // Get desired password length from user
        System.out.print("Password length: ");
        int length = scanner.nextInt();
        scanner.nextLine(); // Clear the input buffer

        // Get character type preferences
        System.out.print("Include uppercase letters? (y/n): ");
        boolean useUpper = scanner.nextLine().equalsIgnoreCase("y");

        System.out.print("Include numbers? (y/n): ");
        boolean useDigits = scanner.nextLine().equalsIgnoreCase("y");

        System.out.print("Include symbols? (y/n): ");
        boolean useSymbols = scanner.nextLine().equalsIgnoreCase("y");

        // Generate password
        String password = generatePassword(length, useUpper, useDigits, useSymbols);
        System.out.println("\nGenerated Password: " + password);

        // Check password strength
        String strength = checkStrength(password);
        System.out.println("Password Strength: " + strength);

        scanner.close();
    }

    /**
     * Generates a random password based on selected options.
     *
     * @param length     Desired password length
     * @param useUpper   Include uppercase letters
     * @param useDigits  Include numbers
     * @param useSymbols Include symbols
     * @return The generated password
     */
    public static String generatePassword(int length, boolean useUpper, boolean useDigits, boolean useSymbols) {
        StringBuilder charPool = new StringBuilder(LOWER);

        if (useUpper) charPool.append(UPPER);
        if (useDigits) charPool.append(DIGITS);
        if (useSymbols) charPool.append(SYMBOLS);

        Random random = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charPool.length());
            password.append(charPool.charAt(index));
        }

        return password.toString();
    }

    /**
     * Evaluates the strength of a given password based on length and character diversity.
     *
     * @param password The password to evaluate
     * @return A strength rating as a string
     */
    public static String checkStrength(String password)
    {
        int score = 0;

        if (password.length() >= 8) score++; // Sufficient length
        if (password.matches(".*[A-Z].*")) score++; // Contains uppercase
        if (password.matches(".*[0-9].*")) score++; // Contains digit
        if (password.matches(".*[!@#$%^&*()\\-_=+<>?].*")) score++; // Contains symbol

        switch (score) {
            case 4: return "Strong";
            case 3: return "Moderate";
            case 2: return "Weak";
            default: return "Very Weak";
        }
    }


}

