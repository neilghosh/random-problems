package day3;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Day3 {
    
    private static final inputFile = "day3.test.txt";

    public static void main(String args[]) {
        System.out.println("Total Of The Priorities " + calculateTotalPriority());
    }

    private static int calculateTotalScore() {
        Scanner sc = new Scanner(Day3.class.getResourceAsStream(inputFile));
        int totalPriority = 0;
        while (sc.hasNext()) {
            String rucksack = sc.nextLine();
            int compartmentSize = rucksack.length/2;
            String compartmentFirst = rucksack.substring(0, compartmentSize);
            String compartmentSecond = rucksack.substring(compartmentSize);
            Character commonElement = findCommonElement(compartmentFirst, compartmentSecond);
            int priority = findPriority(Character commonElement);
            totalPriority += priority;
        }
        sc.close();
        return totalPriority;
    }
}
