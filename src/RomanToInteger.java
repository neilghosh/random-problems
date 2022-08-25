import java.util.HashMap;
import java.util.Map;

/**
 * A number in Roman Numerals is a string of these symbols written in descending
 * order(e.g. M’s first, followed by D’s, etc.). However, in a few specific
 * cases, to avoid four characters being repeated in succession(such as IIII or
 * XXXX), subtractive notation is often used as follows:
 * 
 * I placed before V or X indicates one less, so four is IV (one less than 5)
 * and 9 is IX (one less than 10).
 * X placed before L or C indicates ten less, so forty is XL (10 less than 50)
 * and 90 is XC (ten less than a hundred).
 * C placed before D or M indicates a hundred less, so four hundred is CD (a
 * hundred less than five hundred) and nine hundred is CM (a hundred less than a
 * thousand).
 * 
 */

class RomanToInteger {
    static final Map<Character, Integer> romanMap = Map.of(
            'I', 1,
            'V', 5,
            'X', 10,
            'L', 50,
            'C', 100,
            'D', 500,
            'M', 1000);

    public static void main(String[] args) {
        String input = "CDLIX";
        System.out.println(convert(input));
    }

    static int convert(String romanNumber) {
        int sum = 0;
        Character previousChar = null;
        for (Character currentChar : romanNumber.toCharArray()) {
            if (previousChar != null) {
                if (romanMap.get(previousChar) < romanMap.get(currentChar)) {
                    sum += (romanMap.get(currentChar) - romanMap.get(previousChar));
                    previousChar = null; // we have consumed two characters so would need another iteration to have a
                                         // previous character
                } else {
                    sum += romanMap.get(previousChar);
                    previousChar = currentChar;
                }
            } else {
                previousChar = currentChar;
            }

        }
        return sum + (previousChar == null ? 0 : romanMap.get(previousChar));
    }
}