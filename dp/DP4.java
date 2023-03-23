package PSDS.dp;

public class DP4 {

  /**
   * # Ways to reach from (0,0) to (N-1, M-1)
   * 
   * OBSERVATION:
   * - For 4 X 5 matrix we can reach N-1 X M-1 we can come from 4,4 or 3,5
   * - DP State dp[i][j] => # ways to reach (i, j) from (0, 0)
   * - DP Table => N X M
   * - DP Expression => dp[i][j] = dp[i][j-1] + dp[i-1][j]
   * - Base case
   * \_ i == 0 OR j == 0 fill the 1st row and column with 1
   * 
   * @param A
   * @param N
   * @return # Ways to reach N, M
   * 
   *         COMPLEXITY
   *         Time: O(N X M)
   *         Space: O(N X M)
   */
  public static int noOfWaysToReach(int N, int M) {
    int[][] dp = new int[N][M];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (i == 0 || j == 0) {
          // Can do in separate loops as well
          dp[i][j] = 1;
        } else {
          dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        }
      }
    }

    return dp[N - 1][M - 1];
  }

  /**
   * # Ways to reach from (0,0) to (N-1, M-1) with few cells blocked
   * NOTE: This is TOP-DOWN approach
   * 
   * OBSERVATION:
   * - For 4 X 3 matrix we can reach N-1 X M-1 we can come from 3 X 3 or 4 X 2
   * - Reaching a cell is not possible if it's blocked
   * - DP State dp[i][j] => # ways to reach from (0, 0) to (i, j)
   * - DP Table => N X M
   * - DP Expression
   * \_ If dp[i][j] == 0 as there is no way to reach that (i,j) cell
   * \_ Else dp[i][j] = dp[i][j-1] + dp[i-1][j]
   * - Base case
   * \_ For i = 0 OR j = 0 if '0' is encountered fill i,0 OR 0,j with "0"
   * 
   * @param A
   * @param N
   * @param M
   * @return # Ways to reach N, M
   * 
   *         COMPLEXITY
   *         Time: O(N X M)
   *         Space: O(N X M)
   */
  public static int noOfWaysToReachBlocked(int[][] A, int N, int M) {
    int[][] dp = new int[N][M];

    // Fill 0th row
    for (int i = 1; i < N; i++) {
      if (A[i][0] == 1) {
        dp[i][0] = 1;
      } else {
        break;
      }
    }

    // Fill 0th column
    for (int j = 1; j < M; j++) {
      if (A[0][j] == 1) {
        dp[0][j] = 1;
      } else {
        break;
      }
    }

    // Check paths for remaining matrix
    for (int i = 1; i < N; i++) {
      for (int j = 1; j < M; j++) {
        if (A[i][j] == 0) {
          dp[i][j] = 0;
        } else {
          dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        }
      }
    }

    return dp[N][M];
  }

  public static void main(String[] args) {
    System.out.println(noOfWaysToReach(3, 3)); // 6
    System.out.println(noOfWaysToReach(4, 5)); // 35
  }
}
