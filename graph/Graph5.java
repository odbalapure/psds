package PSDS.graph;

public class Graph5 {

  /**
   * Bellman Ford's Algorithm
   * 
   * FACTS:
   * - Can handle -ve weighted edges
   * - DAG shortest path using topological sort -> O(V+E) for both
   * weighted/non-weighted graph
   * - Dijkstra works for DAG/DCG but does not work for -ve weights
   * 
   * APPROACH
   * - Is a DP algorithm, we find shorest paths that are one edge length, then two
   * edge length & so on
   * - Iterate over every edge and update node distance
   * - Repeat process N-1 times
   */
  public static void bellmanFord() {

  }

  /**
   * Krushkal Algorithm
   * NOTE: It's a greedy algorithm
   * 
   * APPROACH
   * - Sort all edges "E" in increasing order
   * - Have MST = [], ans = 0
   * - Do this until MST.size != V-1
   * \_ If adding "E" does not create a cycle then
   * \_ MST = MST U E
   * \_ ans += E.weight
   * - Return ans
   * 
   */
  public static void krushkalAlgorithm() {

  }

  public static void main(String[] args) {
  }
}