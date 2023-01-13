package day5;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day5 {

    /*
     * [D]
     * [N] [C]
     * [Z] [M] [P]
     * 1 2 3
     * 
     * move 1 from 2 to 1
     * move 3 from 1 to 3
     * move 2 from 2 to 1
     * move 1 from 1 to 2
     */
    private static final String inputFile = "day5.txt";

    public static void main(String args[]) {
        long startTime = System.nanoTime();
        System.out.println("Top Elements Moved One By One: " + getTopElements(true));
        System.out.println("Top Elements Moved All At Once: " + getTopElements(false));
        System.out.println("Running Time  " + (System.nanoTime() - startTime) / 1000);
    }

    private static String getTopElements(boolean moveOneByOne) {
        List<ArrayDeque<Character>> stacks = new ArrayList<ArrayDeque<Character>>();
        List<String> stackInputLines = new ArrayList<>();
        int totalStacks = 0;
        Scanner sc = new Scanner(Day5.class.getResourceAsStream(inputFile));
        // Get Lines with Initial Stack elements
        while (sc.hasNext()) {
            String currentLine = sc.nextLine();
            if (currentLine.isBlank()) {
                String stackNumbersLine = stackInputLines.remove(stackInputLines.size() - 1);
                totalStacks = stackNumbersLine.charAt(stackNumbersLine.length() - 2) - '0';
                break;
            } else {
                stackInputLines.add(currentLine);
            }
        }
        // initialize stacks
        IntStream.range(0, totalStacks).forEach(i -> stacks.add(new ArrayDeque<Character>()));

        // Build the initial state of the stacks
        for (int i = stackInputLines.size() - 1; i >= 0; i--) { // iterate from the bottoms of the stacks
            String stackInputLine = stackInputLines.get(i);
            // every 4th char in a stack inputline is a elemenet
            int elementCount = 0;
            for (char element : stackInputLine.toCharArray()) {
                if (elementCount % 4 == 1 && element != ' ') {
                    ArrayDeque<Character> stack = stacks.get(elementCount / 4);
                    stack.push(element);
                }
                elementCount++;
            }
        }

        // Move elements
        while (sc.hasNext()) {
            String[] instructions = sc.nextLine().split(" ");
            int numElementsToMove = Integer.parseInt(instructions[1]);
            Deque<Character> fromStack = stacks.get(Integer.parseInt(instructions[3]) - 1); // Index by 0
            Deque<Character> toStack = stacks.get(Integer.parseInt(instructions[5]) - 1);
            // Move 1 by one
            if (moveOneByOne) {
                IntStream.range(0, numElementsToMove).forEach(i -> toStack.push(fromStack.poll()));
            } else {
                // Move all at once
                Deque<Character> tempStack = new ArrayDeque<>();
                IntStream.range(0, numElementsToMove).forEach(i -> tempStack.push(fromStack.poll()));
                tempStack.forEach(element -> toStack.push(element));
            }
        }
        sc.close();

        // Return top elements of all stacks concatenated
        return stacks.stream().map(stack -> stack.peek().toString()).collect(Collectors.joining());
    }
}
