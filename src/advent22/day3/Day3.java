package day3;

import java.util.HashSet;
import java.util.Scanner;

public class Day3 {

    private static final String inputFile = "day3.txt";

    public static void main(String args[]) {
        long startTime = System.nanoTime();
        System.out.println("Total Of The Priorities " + calculateTotalPriority());
        System.out.println("Total group Priority " + calculateTotalGroupPriority());
        System.out.println("Running Time  " + (System.nanoTime() - startTime) / 1000);
    }

    private static int calculateTotalPriority() {
        Scanner sc = new Scanner(Day3.class.getResourceAsStream(inputFile));
        int totalPriority = 0;
        while (sc.hasNext()) {
            String rucksack = sc.nextLine();
            int compartmentSize = rucksack.length() / 2;
            String compartmentFirst = rucksack.substring(0, compartmentSize);
            String compartmentSecond = rucksack.substring(compartmentSize);
            Character commonElement = findCommonElement(compartmentFirst, compartmentSecond);
            int priority = findPriority(commonElement);
            // System.out.println(commonElement + " " + priority);
            totalPriority += priority;
        }
        sc.close();
        return totalPriority;
    }

    // Part 2
    private static int calculateTotalGroupPriority() {
        Scanner sc = new Scanner(Day3.class.getResourceAsStream(inputFile));
        int totalPriority = 0;
        int lineCounter = 0;
        String[] currentGroup = new String[3]; // index=0 unused

        while (sc.hasNext()) {
            String rucksack = sc.nextLine();
            int currentIndex = lineCounter % 3;
            currentGroup[currentIndex] = rucksack;
            if (currentIndex + 1 == 3) {
                // get common from 1st and second
                HashSet<Character> commonElementsFirstPair = findCommonElements(currentGroup[0].toCharArray(),
                        currentGroup[1].toCharArray());
                // get commons from result and 3rd . O(2n^2) < O(n^3)
                Character[] commonElementsFirstPairArray = commonElementsFirstPair
                        .toArray(new Character[commonElementsFirstPair.size()]); // Set to Character
                char[] charArray = new char[commonElementsFirstPairArray.length]; // Character to char
                for (int i = 0; i < commonElementsFirstPairArray.length; i++)
                    charArray[i] = commonElementsFirstPairArray[i];
                HashSet<Character> commonElements = findCommonElements(charArray, currentGroup[2].toCharArray());
                Character commonElement = (Character) commonElements.toArray()[0];

                int priority = findPriority(commonElement);
                // System.out.println(commonElement + " " + priority);
                totalPriority += priority;
            }
            lineCounter++;
        }
        sc.close();
        return totalPriority;
    }

    private static int findPriority(Character commonElement) {
        if (Character.isUpperCase(commonElement)) {
            return (commonElement - 64 + 26); // A's ASCII is 65 and Priority 26
        } else {
            return commonElement - 96; // a's ASCII is 97 and priority is 1
        }
    }

    private static Character findCommonElement(String first, String second) {
        // int length = first.length();
        for (char element1 : first.toCharArray()) {
            for (char element2 : second.toCharArray()) {
                if (element1 == element2) {
                    return element1;
                }
            }
        }
        return null;
    }

    private static HashSet<Character> findCommonElements(char[] first, char[] second) {
        // int length = first.length();
        HashSet<Character> commonElements = new HashSet<>();
        for (char element1 : first) {
            for (char element2 : second) {
                if (element1 == element2) {
                    commonElements.add(element1);
                }
            }
        }
        return commonElements;
    }
}
