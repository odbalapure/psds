package PSDS.graph;

import java.util.ArrayList;
import java.util.Arrays;
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

  /**
   * Shortest path from 0 to every other node
   * 
   * @param adj
   * @param V
   * @param S
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(E)
   *         Space: O(E + N)
   */
  public static int[] shortestPathsFromSource(ArrayList<ArrayList<Integer>> adj, int V, int S) {
    Queue<Integer> queue = new LinkedList<>();
    boolean[] visited = new boolean[V + 1];
    int[] distance = new int[V + 1];
    distance[S] = 0;
    Arrays.fill(distance, Integer.MAX_VALUE);

    queue.add(S);
    visited[S] = true;

    while (!queue.isEmpty()) {
      int u = queue.poll();
      for (int v : adj.get(u)) {
        if (!visited[v]) {
          distance[v] = distance[u] + 1;
          visited[v] = true;
          queue.add(v);
        }
      }
    }

    return distance;
  }

}
