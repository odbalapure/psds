package PSDS.graph;

/**
 * DFS won't always give shortest path b/w 2 nodes
 * - Only visit do DFS
 * - Shortest path do BFS
 * 
 * Problems:
 * 1. DFS
 * 2. No. of connected components
 * 3. No. of islands
 * 4. Multi source BFS
 * 5. Rotten oranges
 */

public class Graph2 {

  /**
   * DFS Traversal w/o adjacency list
   * 
   * @param mat
   * @param N
   * @param i
   * @param j
   * 
   *            COMPLEXITY
   *            Time: O(N X M)
   *            Space: O(N X M)
   */
  public static void dfs(int[][] mat, int N, int M, int i, int j) {
    if (i < 0 || j < 0 || i == N || j == N || mat[i][j] == 0) {
      return;
    }

    dfs(mat, N, M, i + 1, j); // down
    dfs(mat, N, M, i - 1, j); // up
    dfs(mat, N, M, i, j + 1); // right
    dfs(mat, N, M, i, j - 1); // left
  }

  /**
   * Find no. of islaands using DFS w/o an adjacency list
   * 
   * @param mat
   * @param N
   * @param M
   * @return
   */
  public static int noOfIslands(int[][] mat, int N, int M) {
    int count = 0;

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (mat[i][j] == 1) {
          dfs(mat, N, M, i, j);
          count++;
        }
      }
    }

    return count;
  }

  /**
   * Find the min. time for all oranges to rot
   * 1 means good orange
   * 2 mean rotten orange
   * 
   * @param A
   * @param N
   * @param M
   * @return
   */
  public static int minTimeRotOranges(int[][] A, int N, int M) {
    return 0;
  }

  public static void main(String[] args) {
    int M[][] = new int[][] { { 1, 1, 0, 0, 0 },
        { 0, 1, 0, 0, 1 },
        { 1, 0, 0, 1, 1 },
        { 0, 0, 0, 0, 0 },
        { 1, 0, 1, 0, 1 } };
    System.out.println(noOfIslands(M, M.length, M[0].length));
  }
}
