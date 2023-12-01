package org.example.utils;

import java.util.Scanner;

public class Input {
    public static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    /**
     * Prompts the user to press 'q' to return to menu
     */
    public static void promptReturnToMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter 'q' to return to menu. ");
            String input = scanner.nextLine();
            input = input.toLowerCase();
            if (input.equals("q")) {
                break;
            }
        }
    }

    /**
     * Get user input and check that the input is an integer
     * between two numbers, inclusive
     * @param start
     * @param end
     * @return The integer value entered by user
     */
    public static int readIntInRange(int start, int end){
        Scanner scanner = new Scanner(System.in);
        String input = getUserInput();
        while(!Validator.isIntBetweenInclusive(start, end, input)) {
            System.out.println("Please enter a valid number between " + start + " - " + end);
            input = scanner.nextLine();
        }

        return Integer.parseInt(input);
    }
}
