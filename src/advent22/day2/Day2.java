package day2;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Day2 {
    static String getDecisionKey(Shape first, Shape second) {
        return first.toString() + second.toString();
    }

    static Boolean getDecision(Character myCharacter, Character opponentCharacter) {
        return winningShapeCombinations.contains(
                getDecisionKey(
                        shapeMap.get(myCharacter), shapeMap.get(opponentCharacter)));
    }

    public enum Shape {
        Rock,
        Paper,
        Scissors;
    }

    static final Map<Character, Integer> scoreMap = Map.of(
            'A', 1,
            'B', 2,
            'C', 3,
            'X', 1,
            'Y', 2,
            'Z', 3);
    static final Map<Character, Shape> shapeMap = Map.of(
            'A', Shape.Rock,
            'B', Shape.Paper,
            'C', Shape.Scissors,
            'X', Shape.Rock,
            'Y', Shape.Paper,
            'Z', Shape.Scissors);
    // Rock defeats Scissors, Scissors defeats Paper, and Paper defeats Rock
    static final Set<String> winningShapeCombinations = Set.of(
            getDecisionKey(Shape.Rock, Shape.Scissors),
            getDecisionKey(Shape.Scissors, Shape.Paper),
            getDecisionKey(Shape.Paper, Shape.Rock));
            
    //Could have used a circular array and move up and down to decide whats needed for win or loose 
    static final Map<Character, Character> neededSelectionToBeat = Map.of(
            'A', 'B',
            'B', 'C',
            'C', 'A');

    static final Map<Character, Character> neededSelectionToLoose = Map.of(
            'B', 'A',
            'C', 'B',
            'A', 'C');

    public static void main(String args[]) {
        System.out.println("Total Score Part 1 " + calculateTotalScore());
        System.out.println("Total Score Part 2 " + calculateTotalScorePart2());
    }

    private static int calculateTotalScore() {
        Scanner sc = new Scanner(Day2.class.getResourceAsStream("day2.txt"));
        int totalScore = 0;
        while (sc.hasNext()) {
            String line = sc.nextLine();
            Character opponentSelection = line.toCharArray()[0];
            Character ownSelection = line.toCharArray()[2];
            // If I win
            int score = scoreMap.get(ownSelection);
            // Draw
            if (shapeMap.get(ownSelection) == shapeMap.get(opponentSelection)) {
                score += 3;
            } else if (getDecision(ownSelection, opponentSelection)) { // Win
                score += 6;
            }
            // No points for loosing so no need to add any points
            totalScore += score;
        }
        sc.close();
        return totalScore;
    }

    private static int calculateTotalScorePart2() {
        Scanner sc = new Scanner(Day2.class.getResourceAsStream("day2.txt"));
        int totalScore = 0;
        while (sc.hasNext()) {
            String line = sc.nextLine();
            Character opponentSelection = line.toCharArray()[0];
            Character ownSelection = null;
            Character outComeIndication = line.toCharArray()[2];
            int score = 0;
            
            if (outComeIndication == 'Y') { //I need to draw
                ownSelection = opponentSelection;
                score = scoreMap.get(ownSelection) + 3;
            } else if (outComeIndication == 'Z') { // I need to Win
                ownSelection = neededSelectionToBeat.get(opponentSelection);
                score = scoreMap.get(ownSelection) + 6;
            } else { // I need to lose
                ownSelection = neededSelectionToLoose.get(opponentSelection);
                score = scoreMap.get(ownSelection);
                // mo points for loosing
            }
            totalScore += score;
        }
        sc.close();
        return totalScore;
    }
}
