package PSDS.dp;

public class DP1 {

  /**
   * # Ways to climb N stairs using 1 OR 2 steps
   * 
   * OBSERVATION:
   * - To reach Nth stair you can reach there from N-1 or N-2
   * - Similarly you reach N-1 from => N-2 and N-3
   * - Similarly you reach N-2 from => N-3 and N-4
   * - There are optimal substructures & overlapping subproblems here
   * - DP State DP[i] => No. of ways to reach ith step
   * - DP Expression => dp[i] = dp[i - 1] + dp[i - 2]
   * - DP Table => size is N + 1
   * \_ From (i-2) there are 2 ways to climb till "i"
   * \_ In dp[i-1] there are all possible ways to climb till (i-1)
   * \_ Hence using dp[i] + 2 * dp[i - 2] would give wrong result
   * 
   * @param A
   * @param N
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N)
   *         Space: O(N)
   */
  public static int nStairs(int N) {
    int[] dp = new int[N + 1];
    dp[0] = 1; // # ways to reach ground floor
    dp[1] = 1; // # ways to reach 1st stair

    for (int i = 2; i <= N; i++) {
      dp[i] = dp[i - 1] + dp[i - 2];
    }

    return dp[N];
  }

  /**
   * # Ways to get value N by throwing a two-face die
   * 
   * @return
   */
  public static int throwDiceMakeSumN2Face(int N) {
    // NOTE: The solution is same as DP1::nStairs
    return -1;
  }

  /**
   * # Ways to get value N by throwing a two-face die
   * 
   * OBSERVATION:
   * - To get sum N you need results from N-1 ... N-6
   * - Similarly you get N-1 from N-2 ... N-7
   * - There are optimal substructures & overlapping subproblems here
   * - DP State DP[i] => No. of ways to make sum N
   * - DP Table => size is N + 1
   * - DP Expression
   * \_ dp[0] = 1
   * \_ dp[1] = dp[1 - 1]
   * \_ dp[2] = dp[2 - 1] + dp[2 - 2]
   * \_ dp[3] = dp[3 - 1] + dp[3 - 2] + dp[3 - 3]
   * 
   * @param N
   * @return
   */
  public static int throwDiceMakeSumN6Face(int N) {
    int[] dp = new int[N + 1];
    dp[0] = 1; // A way of playing the game & not throwing the dice

    for (int i = 1; i <= N; i++) {
      int sum = 0;
      for (int j = 1; j <= i && j <= 6; j++) {
        sum += dp[i - j];
      }
      dp[i] = sum;
    }

    return dp[N];
  }

  public static void main(String[] args) {
    // System.out.println(nStairs(4)); // 5
    // System.out.println(nStairs(5)); // 8

    // System.out.println(throwDiceMakeSumN6Face(4)); // 8
    // System.out.println(throwDiceMakeSumN6Face(5)); // 16
  }
}
