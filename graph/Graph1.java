package PSDS.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph1 {

  /**
   * Check whether there exist a path form S -> D
   * 
   * @param N
   * @param E
   * @param u
   * @param v
   * @param S
   * @param D
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(E)
   *         Space: O(E + N)
   */
  public static boolean pathFromStoD(int N, int E, int[] u, int[] v, int S, int D) {
    ArrayList<ArrayList<Integer>> adjList = new ArrayList<>(N + 1);
    for (int i = 0; i < E; i++) {
      adjList.get(u[i]).add(v[i]);
      adjList.get(v[i]).add(u[i]);
    }

    Queue<Integer> queue = new LinkedList<>();
    boolean[] visited = new boolean[N + 1];
    queue.add(S);
    visited[S] = true;

    while (!queue.isEmpty()) {
      int currU = queue.poll();
      for (int i = 0; i < adjList.get(currU).size(); i++) {
        int currV = adjList.get(currU).get(i);
        if (!visited[currV]) {
          visited[currV] = true;
          queue.add(currU);
        }
      }
    }

    return visited[D];
  }

}
