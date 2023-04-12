package PSDS.graph;

import java.util.Arrays;

public class Graph4 {

  /**
   * Minimum Spanning tree
   * 
   * A tree where all verices are connected (even indirectly)
   * and it must not contain a cycle
   * So if we have V vertex then we must have V-1 edges
   * 
   * FACTS
   * - The input graph needs to be connected undirect graph
   * - It follows greedy approach
   * - Has 2 sets -> nodes in MST and nodes not in MST
   * 
   * @param graph
   * @param S
   * 
   *              COMPLEXITY
   *              Time: O(V*V)
   *              Space: O(V)
   */
  public static int primsMst(int[][] graph, int V) {
    int ans = 0;
    int[] key = new int[V];
    Arrays.fill(key, Integer.MAX_VALUE);
    key[0] = 0;
    boolean[] mstSet = new boolean[V];

    for (int count = 0; count < V; count++) {
      int u = -1;
      for (int i = 0; i < V; i++) {
        if (!mstSet[i] && (u == -1 || key[i] < key[u])) {
          u = i;
        }
      }

      mstSet[u] = true;
      ans += key[u];

      for (int v = 0; v < V; v++) {
        if (!mstSet[v] && graph[u][v] != 0) {
          key[v] = Math.min(key[v], graph[u][v]);
        }
      }
    }

    return ans;
  }

  public static void main(String[] args) {
    int graph[][] = new int[][] { { 0, 5, 8, 0 },
        { 5, 0, 10, 15 },
        { 8, 10, 0, 20 },
        { 0, 15, 20, 0 }, };

    System.out.print(primsMst(graph, graph.length)); // 28
  }
}
