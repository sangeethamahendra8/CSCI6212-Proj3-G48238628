import java.io.*;
import java.util.Random;
import java.util.Arrays;
public class PseudoPolynomialPartition {
	 // Returns true if arr[] can be partitioned into two subsets of equal sum, otherwise false
    static boolean findPartition(int[] arr) {
        int n = arr.length;
        int sum = Arrays.stream(arr).sum();

        // If sum is odd, there cannot be two subsets with equal sum
        if (sum % 2 != 0)
            return false;

        // Create a boolean array to store the result of subproblems
        boolean[] dp = new boolean[sum / 2 + 1];

        // Initialize the dp array
        dp[0] = true;

        // Fill the partition table in bottom-up manner
        for (int i = 0; i < n; i++) {
            // Iterate backwards through the dp array
            for (int j = sum / 2; j >= arr[i]; j--) {
                // Check if sum - arr[i] could be formed from a subset
                if (dp[j - arr[i]] || j == arr[i])
                    dp[j] = true;
            }
        }

        // Return the final result
        return dp[sum / 2];
    }

    // Generates a random array of specified size with values between 1 and 10
    static int[] generateArray(int n) {
        Random rand = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(10) + 1; // Random integers from 1 to 10
        }
        return arr;
    }

    // Driver Code
    public static void main(String[] args) {
        // Define sizes of the arrays
       int[] testSizes = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000};

        for (int n : testSizes) {
            int[] arr = generateArray(n);
            long startTime = System.nanoTime(); // Start time
            boolean canPartition = findPartition(arr);
            long endTime = System.nanoTime(); // End time

            // Calculate execution time in nanoseconds
            long duration = endTime - startTime;

            // Output the results
            System.out.println("Array size: " + n);
            System.out.println("Sum of the array: " + Arrays.stream(arr).sum());
            System.out.println("Can partition: " + (canPartition ? "Yes" : "No"));
            System.out.println("Execution time: " + duration + " ns\n");
        }
    }
}
