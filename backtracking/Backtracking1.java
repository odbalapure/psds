package PSDS.backtracking;

import java.util.Arrays;

public class Backtracking1 {

  /*
   * =================
   * BACKTRACKING
   * ==================
   * We try out all possibilities so BT is a brute
   * force solution but not vice versa
   * 
   * Backtracking = Recursion + Tracing Back
   * 
   * NOTE: All backtracking solutions are recursive in nature
   * Since exploring all the paths is possible through recursion
   */

  /**
   * Print all possible digits using {1, 2}
   * 
   * @param A
   * @return
   */
  public static void nDigitUsing12(int N, int idx, int[] list) {
    if (N == 3) {
      System.out.println(Arrays.toString(list));
      return;
    }

    list[idx] = 1;
    nDigitUsing12(N, idx + 1, list);
    // Backtracking step - going back and changing our decision
    list[idx] = 2;
    nDigitUsing12(N, idx + 1, list);
  }

  public static void main(String[] args) {
    nDigitUsing12(3, 0, new int[] { 0, 0, 0 });
  }
}
