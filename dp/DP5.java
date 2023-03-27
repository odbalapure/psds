package PSDS.dp;

import java.util.Arrays;

public class DP5 {
  /**
   * Dungeon princess problem
   * Min health required at (0, 0) to reach (N-1, N-1)
   * 
   * OBSERVATION:
   * - Case 1: Single cell with A[0]=-5
   * \_ x-5=1 => x=6
   * \_ Hence min health needed is 6
   * - Case 2: Two cells with A[0][0]=-2, A[1][0]=-5
   * \_ x-5=1 => x=6
   * \_ x-2=6 => 8
   * \_ Hence min health needed is 8
   * - 
   * - DP state dp[i][j] => Min health needed at (i,j) so we can reach (N-1,N-1)
   * - DP Expression => dp[i][j] = max(1, min(dp[i,j+1], dp[i+1,j]) - H[i,j])
   * \_ min(dp[i,j+1], dp[i+1,j]) - H[i,j] can be 0 or less then 0 at some point
   * \_ The health must never be 0 at any point of time
   * \_ So dp[i][j] = max(1, min(dp[i,j+1], dp[i+1,j]) - H[i,j])
   * - DP Table => dp[N][M]
   * 
   * @param A
   * @param N
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N * M)
   *         Space: O(N * M)
   */
  public static int minHealthToReachN(int[][] A, int N, int M) {
    int[][] dp = new int[N][M];
    for (int[] row : dp) {
      Arrays.fill(row, -1);
    }

    // At last index the health is supposed to be '1'
    // So that means => x + H = 1, if x = 1-H <= 0
    // We can take max(1, 1-H)
    dp[N - 1][M - 1] = Math.max(1, 1 - A[N - 1][M - 1]);
    return helperHealth(dp, A, A.length, A[0].length, 0, 0);
  }

  public static int helperHealth(int[][] dp, int[][] H, int N, int M, int i, int j) {
    if (i == N || j == M) {
      return Integer.MAX_VALUE;
    }

    if (dp[i][j] == -1) {
      int a = helperHealth(dp, H, N, M, i, j + 1);
      int b = helperHealth(dp, H, N, M, i + 1, j);
      dp[i][j] = Math.max(1, Math.min(a, b) - H[i][j]);
    }

    return dp[i][j];
  }

  /**
   * Find the length of longest common subsequence
   * 
   * Backtrack time: O(2^N * 2^M)
   * 
   * OBSERVATION:
   * - Compare last char of s1 and s2 for length 5, 5
   * \_ If equal then reduce search space to [0,4] and [0,4] => add 1 to answer
   * \_ Else search will have 2 options [0,3][0,4] OR [0,4][0,3] => pick the max
   * one
   * - DP State dp[i][j] => LCS of s1[0,i] and s2[0,j]
   * - DP Expression => dp[i][j]
   * 
   * @param s1
   * @param s2
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N * M)
   *         Space: O(N * M)
   * 
   */
  public static int longestCommonSubsequence(String s1, String s2, int N, int M) {
    int[][] dp = new int[N + 1][M + 1];
    for (int[] row : dp) {
      Arrays.fill(row, -1);
    }

    int ans = helperLcs(s1, s2, N, M, dp);
    // for (int[] row : dp) {
    // System.out.println(Arrays.toString(row));
    // }

    return ans;
  }

  public static int helperLcs(String s1, String s2, int i, int j, int[][] dp) {
    // If entire string is iterated then LCS will be '0'
    if (i == 0 || j == 0) {
      return 0;
    }

    if (dp[i][j] == -1) {
      if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
        dp[i][j] = 1 + helperLcs(s1, s2, i - 1, j - 1, dp);
      } else {
        dp[i][j] = Math.max(helperLcs(s1, s2, i, j - 1, dp), helperLcs(s1, s2, i - 1,
            j, dp));
      }
    }

    return dp[i][j];
  }

  /**
   * Min no. of operations on s1 to make s1 same as s2
   * 
   * OBSERVATIONS:
   * - If last 2 characters are equal in s1 & s2 reduce search space for both
   * - Else Insert last char of s1 to s2 to make atleast 1 char equal
   * - Else delete a char from s1
   * - Else replace a char from s1
   * - Pick the minimum value of operations
   * - DP state dp[i][j] => min # operations to make s1 == s2
   * - DP Table => dp[N-1][M-1]
   * - DP Expressison
   * \_ s1 == s2 => dp[i][j] = dp[i][j]
   * 
   * @param s1
   * @param s2
   * @param N
   * @param M
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N * M)
   *         Space: O(N * M)
   */
  public static int minOpToMakeStringSame(String s1, String s2, int N, int M) {
    int[][] dp = new int[N + 1][M + 1];
    for (int[] row : dp) {
      Arrays.fill(row, -1);
    }
    return helperOperation(s1, s2, N, M, dp);
  }

  public static int helperOperation(String s1, String s2, int i, int j, int[][] dp) {
    if (i == 0 && j == 0) {
      // If both strings are empty there can will 0 operations
      return 0;
    }

    if (i == -1) {
      return j + 1; // j + 1 insertions
    }

    if (j == -1) {
      return i + 1; // i + 1 deletions
    }

    if (dp[i][j] == -1) {
      if (s1.charAt(i) == s2.charAt(j)) {
        dp[i][j] = 1 + helperOperation(s1, s2, i - 1, j - 1, dp);
      } else {
        int insert = helperOperation(s1, s2, i, j - 1, dp);
        int delete = helperOperation(s1, s2, i - 1, j, dp);
        int replace = helperOperation(s1, s2, i - 1, j - 1, dp);
        dp[i][j] = Math.min(insert, Math.min(delete, replace));
      }
    }

    return dp[i][j];
  }

  public static void main(String[] args) {
    // int health[][] = { { -2, -3, 3 },
    //     { -5, -10, 1 },
    //     { 10, 30, -5 } }; // 7
    // System.out.println(minHealthToReachN(health, health.length, health[0].length));

    // abcefgh, agch => ach
    // abbcdgf, bachegf => agf
    // klagrip, lgigkm =>lg

    // String str1 = "abcefgh", str2 = "agch";
    // System.out.println(longestCommonSubsequence(str1, str2, str1.length() - 1,
    // str2.length() - 1));

    // String str1 = "sunday", str2 = "saturday"; // 7
    // String str1 = "abad", str2 = "abac"; // 1
    String str1 = "Anshuman", str2 = "Antihuman"; // 2
       System.out.println(minOpToMakeStringSame(str1, str2, str1.length() - 1,
        str2.length() - 1));
  }
}
