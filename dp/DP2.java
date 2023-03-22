package PSDS.dp;

public class DP2 {

  /**
   * # Ways to pair people in a party
   * NOTE: A person can party alone as well
   * 
   * OBSERVATION
   * - A person can pr cannot be part of a group/pair
   * - For p1, p2, p3, p4, p5
   * \_ p5 makes a pair with p1, result is => {p1, p5} {p2, p3, p4} and so on
   * \_ If p5 wishes to stay alone => ways(4)
   * \_ Else # ways p5 can make a pair is => 4 X ways(3)
   * \_ Hence we have => ways(5) = ways(4) + 4 X ways(3)
   * \_ So general expression is => ways(i) = ways(i - 1) + (i - 1) * ways(i - 2)
   * - DP State dp[i] => # ways "i" person can party
   * - DP Table => dp[N + 1]
   * - DP Expression => dp[i] = dp[i - 1] + (i - 1) * dp[i - 2]
   * 
   * @param N
   * @return
   */
  public static int noOfPartyPairs(int N) {
    int[] dp = new int[N + 1];
    dp[0] = 1; // Not paryting is also doing something
    dp[1] = 1; // 1 person can party in 1 way

    for (int i = 2; i <= N; i++) {
      dp[i] = dp[i - 1] + (i - 1) * dp[i - 2];
    }

    return dp[N];
  }

  /**
   * Min no. of squares to get the sum equal to N
   * 
   * OBSERVATIONS
   * - For N=14 we can remove 1^2, 2^2, 3^2 => 1 + N=13, 1 + N=10, 1 + N=5
   * - For N=13 we can remove 1^2, 2^2, 3^2 => 1 + N=12, 1 + N=9, 1 + N=4
   * - We have to get min of N-x^2
   * - DP State dp[i] => min no. of squares to get sum "i"
   * - DP Table => dp[N + 1]
   * - DP Expression => dp[i] = 1 + dp[i - j*j]
   * 
   * @param N
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N * sqrt(N))
   *         Space: O(N)
   */
  public static int minSquaresToGetN(int N) {
    int[] dp = new int[N + 1];
    dp[0] = 0; // Philosophy won't work here, try out expressions on paper

    for (int i = 1; i < N; i++) {
      int ans = i; // We can have max "i" prefect squares for "i" i.e. 1^2 + 1^2 + ...
      for (int j = 1; j * j <= i; j++) {
        ans = Math.min(ans, dp[i - j * j] + 1);
      }
      dp[i] = ans;
    }

    return dp[N];
  }

  /**
   * Find the max subsequence sum
   * 
   * @param A
   * @param N
   * @return
   */
  public static int maxSubSequenceSum(int[] A, int N) {
    // Find SUM of all +ve numbers
    // If all are -ve then return the MAX value
    return -1;
  }

  /**
   * Find the max subsequence sum & cannot pick 2 adjacent elements
   * NOTE: Can't be solved using sum of odd/even indices
   * 
   * OBSERVATION:
   * - Say we have A: {2,-1,-4,5,3,-1,4,2}
   * - A[7] = 2 can be included or excluded
   * - If included operation space will be [0 - 5], result is A[7] + ms[0 - 5]
   * - Else excluded operation space will be [0 - 6], result is ms[0 - 6]
   * - A no. can either be included or excluded
   * - DP State dp[i] => max subseq sum from [0 to i]
   * - DP Table => dp[N]
   * - DP expression => dp[i] = Math.max(dp[i - 1], A + dp[i - 2])
   * 
   * @param A
   * @param N
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N)
   *         Space: O(N)
   */
  public static int maxSubSequenceSumNo2Adjacent(int[] A, int N) {
    int[] dp = new int[N];
    dp[0] = A[0]; // Initially we'll have only 1 value
    dp[1] = Math.max(A[0], A[1]);

    for (int i = 2; i < N; i++) {
      dp[i] = Math.max(dp[i - 1], A[i] + dp[i - 2]);
    }

    return dp[N - 1];
  }

  public static void main(String[] args) {
    // System.out.println(noOfPartyPairs(4)); // 10
    // System.out.println(minSquaresToGetN(14)); // 3

    // int[] A = { 9, 14, 3 }; // 14
    // int[] A = { 9, 4, 13, 24 }; // 9 + 24 = 33
    // int[] A = { 13, 14, 2 }; // 13 + 2 = 15
    // System.out.println(maxSubSequenceSumNo2Adjacent(A, A.length));
  }
}
