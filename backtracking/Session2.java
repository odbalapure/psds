package PSDS.backtracking;

import java.util.ArrayList;
import java.util.Arrays;

public class Session2 {

  /**
   * Sudoku solver 9 X 9 grid
   * \_ Number cannot repeat in a row
   * \_ Number cannot repeat in a column
   * \_ Number cannot repeat in a 3 X 3 sub-grid
   * 
   * OBSERVATION:
   * Getting row and column inside of a grid cell
   * For x = 24
   * \_ r = 24 / 9
   * \_ c = 24 % 9
   * 
   * This way we don't need to keep track of i & j
   * 
   * Finding the starting index of 3 X 3 sub-grid
   * 4, 4 -> 3, 3
   * 5, 2 -> 3, 0
   * 2, 7 -> 0, 6
   * 7, 7 -> 6, 6
   * 4, 1 -> 3, 0
   * This is nearest multiple of 3 <= to that index
   * r = r - r % 3
   * c = c - c % 3
   * 
   * NOTE: For every cell 9 possibilities so for 81 cells time is 9^81
   * Max depth in recursuion will be and 81 function calls at max in the stack
   * 
   * @param A
   * @param x
   * 
   *          COMPLEXITY
   *          Time: O(9^81)
   *          Space: O(9^2)
   * 
   */
  public static void sudokuSolver(int[][] A, int x) {
    if (x == 81) {
      for (int[] row : A) {
        System.out.println(Arrays.toString(row));
      }
      return;
    }

    int r = x / 9, c = x % 9;
    if (A[r][c] != 0) {
      // Is a filled cell so move to next cell
      sudokuSolver(A, x + 1);
    } else {
      // Is an empty cell
      for (int i = 1; i <= 9; i++) {
        // Check whether 'i' can be filled in (r X c)
        if (canFillCell(A, r, c, i)) {
          A[r][c] = i;
          // Move to next cell
          sudokuSolver(A, x + 1);
          // If function does not return i.e. print matrix and finishes abruptly
          // so we reset it and check for possibilities again using recursion
          A[r][c] = 0;
        }
      }
    }
  }

  /* Helper function for Sudoku */
  public static boolean canFillCell(int[][] A, int r, int c, int d) {
    for (int i = 0; i < 9; i++) {
      if (A[r][i] == d) {
        return false;
      }
      if (A[i][c] == d) {
        return false;
      }
    }

    int x = r - r % 3;
    int y = c - c % 3;
    for (int i = x; i < x + 3; i++) {
      for (int j = y; j < y + 3; j++) {
        if (A[i][j] == d) {
          return false;
        }
      }
    }

    return true;
  }

  /**
   * Print all valid cases of placing N queens in a matrix
   * 
   * @param A
   * @param N
   * @param i
   * 
   *          COMPLEXITY
   *          Time: O(N! * N)
   *          Space: O(N^2)
   */
  public static void nQueens(int[][] A, int N, int i) {
    if (i == N) {
      for (int[] row : A) {
        System.out.println(Arrays.toString(row));
      }
      return;
    }

    for (int j = 0; j < N; j++) {
      // Check if we can place a Queen at i, j
      if (canPlaceQueen(A, N, i, j)) {
        A[i][j] = 1;
        nQueens(A, N, i + 1);
        A[i][j] = 0;
      }
    }
  }

  /* Helper function */
  public static boolean canPlaceQueen(int[][] A, int N, int i, int j) {
    // Check upward
    for (int r = 0; r < i; r++) {
      if (A[r][j] == 1) {
        return false;
      }
    }

    // Check right diagonal -> i-- and j++
    int r = i - 1, c = j + 1;
    while (r >= 0 && c < N) {
      if (A[r][c] == 1) {
        return false;
      }
      r--;
      c++;
    }

    // Check left diagonal -> i-- and j--
    r = i - 1;
    c = j - 1;
    while (r >= 0 && c >= 0) {
      if (A[r][c] == 1) {
        return false;
      }
      r--;
      c--;
    }

    return true;
  }

  /**
   * Rat in maze path
   * 
   * @param A
   * @param N
   * @param M
   * @param i
   * @param j
   * @param path
   * 
   *             COMPLEXITY
   *             Time: O(N X M) | Each is visited exactly once
   *             Space: (N X M) | If no cells are blocked
   */
  public static void ratInMaze(int[][] A, int N, int M, int i, int j, ArrayList<String> path) {
    if (i < 0 || i >= N || j < 0 || j >= M) {
      return;
    }

    if (A[i][j] == 1 || A[i][j] == 2) {
      return;
    }

    if (i == N - 1 && j == M - 1) {
      for (String pair : path) {
        System.out.print(pair + ", ");
      }
      return;
    }

    if (A[i][j] == 0) {
      A[i][j] = 1;
      path.add("[]" + i + ", " + j + "]");
      ratInMaze(A, N, M, i - 1, j, path);
      ratInMaze(A, N, M, i, j - 1, path);
      ratInMaze(A, N, M, i + 1, j, path);
      ratInMaze(A, N, M, i, j + 1, path);
      A[i][j] = 0;
      path.remove(path.size() - 1);
    }
  }
}
