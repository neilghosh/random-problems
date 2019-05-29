public class CountRuns {
    public static void main(String[] args) {
        int n = 10;
        System.out.println(CountWays(n, false));
    }

    static int CountWays(int n, boolean flag) {
        if (n == 0) // base case
            return 1;

        int sum = 0;

        // 2 is not scored last time so we can score either 2 or 1
        if (n > 0) {
            if (flag == false)
                sum = sum + CountWays(n - 1, false)
                        + CountWays(n - 2, false)
                        + CountWays(n - 4, true)
                        + CountWays(n - 6, false);

                // 2 is scored last time so we can only score 1
            else
                sum = sum + CountWays(n - 1, false)
                        + CountWays(n - 2, false)
                        + CountWays(n - 6, false);
        }
        return sum;
    }

}

