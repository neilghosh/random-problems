package day4;

import java.util.Arrays;
import java.util.Scanner;

public class Day4 {

    /*
     * 2-4,6-8
     * 2-3,4-5
     * 5-7,7-9
     * 2-8,3-7
     * 6-6,4-6
     * 2-6,4-8
     */
    private static final String inputFile = "day4.txt";

    public static void main(String args[]) {
        long startTime = System.nanoTime();
        System.out.println("Fully Including Sections " + getFullyIncludingSections());
        System.out.println("Overlapping Sections " + getOverlappingSections());
        System.out.println("Running Time  " + (System.nanoTime() - startTime) / 1000);
    }

    private static int getFullyIncludingSections() {
        Scanner sc = new Scanner(Day4.class.getResourceAsStream(inputFile));
        int totalFullyContainedRanges = 0;
        while (sc.hasNext()) {
            String[] pairs = sc.nextLine().split(",");
            int[] firstRange = Arrays.stream(pairs[0].split("-")).mapToInt(Integer::parseInt).toArray();
            int[] secondRange = Arrays.stream(pairs[1].split("-")).mapToInt(Integer::parseInt).toArray();
            if (fullyContains(firstRange, secondRange)) {
                totalFullyContainedRanges++;
            }
        }
        sc.close();
        return totalFullyContainedRanges;
    }

    private static int getOverlappingSections() {
        Scanner sc = new Scanner(Day4.class.getResourceAsStream(inputFile));
        int totalFullyContainedRanges = 0;
        while (sc.hasNext()) {
            String[] pairs = sc.nextLine().split(",");
            int[] firstRange = Arrays.stream(pairs[0].split("-")).mapToInt(Integer::parseInt).toArray();
            int[] secondRange = Arrays.stream(pairs[1].split("-")).mapToInt(Integer::parseInt).toArray();
            if (overlapping(firstRange, secondRange)) {
                totalFullyContainedRanges++;
            }
        }
        sc.close();
        return totalFullyContainedRanges;
    }

    private static boolean fullyContains(int[] firstRange, int[] secondRange) {
        if (firstRange[0] < secondRange[0]) {
            return firstRange[1] >= secondRange[1]; // first includes second
        } else if (firstRange[0] > secondRange[0]) {
            return firstRange[1] <= secondRange[1]; // second includes first
        }

        return true;
    }

    

    private static boolean overlapping(int[] firstRange, int[] secondRange) {
        if (firstRange[0] <= secondRange[0] && firstRange[1] >= secondRange[0]) { // first range starts before 2nd range //2-6 4-8 2-6 2-2
            return true;
        } else if (firstRange[0] > secondRange[0] && firstRange[0] <= secondRange[1]) { // 1st range starts after 2nd
                                                                                        // range // 4-8 2-6
            return true;
        }
        return false;     // 6-6 4-4 
    }
}
