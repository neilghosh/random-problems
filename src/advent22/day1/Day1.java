package day1;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

class Day1 {
    public static void main(String args[]) throws FileNotFoundException {
        System.out.println("Part1 " + getMaxCalories());
        System.out.println("Part2 " + getTop3Calories());
    }

    // part1
    // TODO Currently ignores the very last line of the file if thats part of the
    // top calory holder, it will gove wrong answer
    static int getMaxCalories() throws FileNotFoundException {
        Scanner sc = new Scanner(Day1.class.getResourceAsStream("day1.txt"));
        int currentMaxCalories = 0;
        int currentCumulativeCalories = 0;
        while (sc.hasNext()) {
            String calories = sc.nextLine();
            if (calories.isBlank()) {
                if (currentMaxCalories < currentCumulativeCalories) {
                    currentMaxCalories = currentCumulativeCalories;
                }

                currentCumulativeCalories = 0;

            } else {
                currentCumulativeCalories += Integer.parseInt(calories);
            }
        }
        sc.close();
        return currentMaxCalories;
    }

    static int getTop3Calories() throws FileNotFoundException {
        // File file = new File();
        Scanner sc = new Scanner(Day1.class.getResourceAsStream("day1.txt"));
        TreeSet<Integer> maxCalories = new TreeSet<>();
        int currentCumulativeCalories = 0;
        boolean doneWithCurrentPerson = false;
        while (sc.hasNext()) {
            String calories = sc.nextLine();
            if (calories.isBlank()) {
                doneWithCurrentPerson = true;

            } else {
                currentCumulativeCalories += Integer.parseInt(calories);
                // TODO I don't like this but special handling for the last line of the input
                if (!sc.hasNextLine()) {
                    doneWithCurrentPerson = true;
                }
            }
            if (doneWithCurrentPerson) {
                maxCalories = checkAndAddToTopCalories(maxCalories, currentCumulativeCalories);
                currentCumulativeCalories = 0;
                doneWithCurrentPerson = false;
            }
        }
        sc.close();
        return maxCalories.stream().mapToInt(Integer::valueOf).sum();
    }

    private static TreeSet<Integer> checkAndAddToTopCalories(TreeSet<Integer> maxCalories,
            int currentCumulativeCalories) {
        if (maxCalories.size() >= 3) {
            if (maxCalories.first() < currentCumulativeCalories) {
                maxCalories.pollFirst();
                maxCalories.add(currentCumulativeCalories);
            }
        } else {
            maxCalories.add(currentCumulativeCalories);
        }
        return maxCalories;
    }
}