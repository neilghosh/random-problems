import java.util.*;

public class UniqAfterFields {
    public static void main(String[] args) {
        String[] input = new String[]{
                "1. - Hello",
                "2. - World",
                "3. + Hello"
        };

        printTruelyUnique(input,2);
    }

    private static void printTruelyUnique(String[] input, int n) {
        Set<String> unique = new HashSet<>();
        for(int i=0; i < input.length; i++) {
            String s = input[i];
            String[] parts = s.split(" ");
            //String[] remainingSegments = Arrays.copyOfRange(parts,n,parts.length-1);
            List<String> remainingSegments =  Arrays.asList(parts).subList(n, parts.length);
            String trimmed = String.join(" ", remainingSegments);

            unique.add(trimmed);
        }
        for(String s : unique){
            System.out.println(s);
        }
    }
}
