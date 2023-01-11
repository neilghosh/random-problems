package day3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day3 {

    private static final String inputFile = "day3.txt";

    public static void main(String args[]) {
        System.out.println("Total Of The Priorities " + calculateTotalPriority());
        System.out.println("Total group Priority " + calculateTotalGroupPriority());
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
                Character[] commonElementsFirstPair = findCommonElements(currentGroup[0], currentGroup[1]);
                Character[] commonElements = findCommonElements(String.valueOf(Arrays.stream(commonElementsFirstPair)
                        .map(Object::toString)
                        .collect(Collectors.joining())), currentGroup[2]);
                Character commonElement = commonElements[0];

                int priority = findPriority(commonElement);
                System.out.println(commonElement + " " + priority);
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

    private static Character[] findCommonElements(String first, String second) {
        // int length = first.length();
        ArrayList<Character> commonElements = new ArrayList<>();
        for (char element1 : first.toCharArray()) {
            for (char element2 : second.toCharArray()) {
                if (element1 == element2) {
                    commonElements.add(element1);
                }
            }
        }
        Character[] commonElementsArray = new Character[commonElements.size()];
        commonElements.toArray(commonElementsArray);
        return commonElementsArray;
    }
}
