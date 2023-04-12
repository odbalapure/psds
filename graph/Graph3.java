package PSDS.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph3 {
  public static void addEdge(ArrayList<ArrayList<Integer>> adjList, int u, int v) {
    adjList.get(u).add(v);
  }

  /**
   * Check if there's a cycle in an undirected graph
   * 
   * - The code is same as DFS
   * - Maintain a variable called "parent"
   * \_ The vertex through which we came is a parent vertex
   * \_ If a vertex is visited and it's not a parent then there's a cycle
   * 
   * @param adj
   * @param V
   */
  public static boolean detectCycleUndirected(ArrayList<ArrayList<Integer>> adj, int V) {
    boolean[] visited = new boolean[V];
    for (int i = 0; i < V; i++) {
      if (!visited[i]) {
        if (helperCycle(adj, i, visited, -1)) {
          return true;
        }
      }
    }
    return false;
  }

  public static boolean helperCycle(ArrayList<ArrayList<Integer>> adj, int S, boolean[] visited, int parent) {
    visited[S] = true;
    for (int u : adj.get(S)) {
      if (!visited[u]) {
        if (helperCycle(adj, u, visited, S)) {
          return true;
        }
      } else if (u != parent) {
        return true;
      }
    }
    return false;
  }

  /**
   * Topological Sorting
   * 
   * We consider nodes as jobs and directed edges as dependencies
   * Eg: An edge 0 -> 1 & 0 -> 2, means 0 needs to finish before 1 & 2
   * But after 0 we can either do 1 or 2, so there are multiple topologial sorting
   * 
   * APPROACH
   * - Find indegree edges of every vertex
   * - Store vertices whose indegrees are 0
   * - If indegrees get 0 then add it to the queue
   * 
   * @param adj
   * @param V
   */
  public static void topologicalSort(ArrayList<ArrayList<Integer>> adj, int V) {
    int[] inDegree = new int[V];
    for (int u = 0; u < V; u++) {
      for (int x : adj.get(u)) {
        inDegree[x]++;
      }
    }

    java.util.Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < V; i++) {
      if (inDegree[i] == 0) {
        queue.add(i);
      }
    }

    while (!queue.isEmpty()) {
      int u = queue.poll();
      System.out.print(u + " ");

      for (int x : adj.get(u)) {
        if (--inDegree[x] == 0) {
          queue.add(x);
        }
      }
    }
  }

  /**
   * Dijkstra's Algorithm
   * 
   * FACTS
   * - It follows a greedy approach
   * - Shortest path from source node to any node with weighted edges
   * - Does not work for negative edges
   * - Adding weight to the original path changes the shortest path
   * 
   * APPROACH
   * - Create a MIN heap -> O(V)
   * - Create distance[V] array with Infinity value
   * - Store dist[S] = 0
   * - Insert all distance in pq -> O(V)
   * - while (queue is not empty)
   * \_ u = pq.extractMin() -> O(logV)
   * \_ Relax adjacent of u that are not in pq -> O(logV)
   * 
   * Using adjacency list and min heap the complexity will be 
   * \_ O(VlogV) + O(V + E) X log(V)
   * \_ Hence, O(V + E) X log(V)
   * 
   * @param adj
   * @param V
   * 
   *            COMPLEXITY
   *            Time: O(V*V)
   *            Space: O(V)
   * 
   */
  public static int[] dijkstraAlgo(int[][] adj, int S) {
    int V = adj.length;
    int[] distance = new int[V];
    Arrays.fill(distance, Integer.MAX_VALUE);
    distance[S] = 0;
    boolean[] finalised = new boolean[V]; // node finalised from 0 -> v

    for (int count = 0; count < V - 1; count++) {
      // Pick un-finalized vertices
      int u = -1;
      // ** NOTE: Use MIN heap to optimize this step to O(VlogV)
      for (int i = 0; i < V; i++) {
        // Find the least weighted edge
        if (!finalised[i] && (u == -1 || distance[i] < distance[u])) {
          u = i;
        }
      }

      // Finialize u
      finalised[u] = true;

      // ** NOTE: Can use adjacency list to reduce time since
      // since, we don't go over entire row for each vertex
      // Hence time taken will be O(V + E) X log(V)
      for (int v = 0; v < V; v++) {
        if (!finalised[v] && adj[u][v] != 0) {
          distance[v] = Math.min(distance[v], distance[u] + adj[u][v]);
        }
      }
    }

    return distance;
  }

  public static void main(String[] args) {
    // int V = 6;
    // ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(V);
    // for (int i = 0; i < V; i++) {
    // adj.add(new ArrayList<Integer>());

    // }
    // addEdge(adj, 0, 1);
    // addEdge(adj, 1, 2);
    // addEdge(adj, 2, 4);
    // addEdge(adj, 4, 5);
    // addEdge(adj, 1, 3);
    // addEdge(adj, 2, 3);
    // System.err.println("CYCLE PRESENT: " + detectCycleUndirected(adj, V)); //
    // true

    // int V = 5;
    // ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(V);

    // for (int i = 0; i < V; i++) {
    // adj.add(new ArrayList<Integer>());
    // }

    // addEdge(adj, 0, 2);
    // addEdge(adj, 0, 3);
    // addEdge(adj, 1, 3);
    // addEdge(adj, 1, 4);
    // addEdge(adj, 2, 3);

    // System.out.println("Following is a Topological Sort of: ");
    // topologicalSort(adj, V); // 0 1 2 4 3

    // int graph[][] = new int[][] { { 0, 50, 100, 0 },
    //     { 50, 0, 30, 200 },
    //     { 100, 30, 0, 20 },
    //     { 0, 200, 20, 0 }, };
    // System.out.println(Arrays.toString(dijkstraAlgo(graph, 0))); // 0 50 80 100
  }
}
