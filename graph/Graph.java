package PSDS.graph;

import java.util.ArrayList;
import java.util.LinkedList;

import java.util.Queue;

/**
 * =================================
 * GRAPHS
 * ================================
 * 
 * Bunch of nodes connected via edges
 * - Every tree is graph but every graph is not a tree
 * - There are no levels and hierarchy
 * - For N nodes there are N-1 edges in a tree
 * - BFS gives shortest path from S to D in unweighted graph
 * 
 * -------------------
 * NO OF EDGES
 * -------------------
 * - Undirected: V * (V - 1)/2
 * - Directed: V * (V - 1)
 * 
 * -------------------
 * ADJACENCY MATRIX
 * -------------------
 * - Check if u & v are adjacent: O(1)
 * - Find all vertices adjacent to u: O(V)
 * \_ All the vertices may be connected to U
 * \_ Need to traverse an entire row to check
 * - Find degree of U: O(V)
 * \_ All the vertices may be connected to U
 * - Add/remove an edge: O(1)
 * \_ We have access to indices so setting values is O(1) work
 * - Add/remove a vertex: O(V * V)
 * \_ Adding/removing can result in creating/shrinking entrie matrix
 * \_ We might have to create a new matrix for adding/removing an edge
 * - Space required: O(V * V)
 * 
 * 
 * -------------------
 * ADJACENCY LIST
 * -------------------
 * - Check if u & v are adjacent: O(V)
 * - Find all vertices adjacent to u: O(degree(U))
 * - Find degree of U: O(1)
 * - Add an edge: O(1)
 * \_ Appending a vertex add end of an array is O(1) work
 * - Remove an edge: O(V)
 * \_ Directed: O(V + E)
 * \_ Undirected: O(V + 2E)
 * - Space: Less space required than matrix
 * 
 * 
 * -------------------
 * BFS APPLICATIONS
 * -------------------
 * - Shortest path in unweighted graph
 * - Crawlers in search engines
 * - Social network search
 * - Cycle detection
 * - Broadcasting in a network
 * 
 * -------------------
 * DFS APPLICATIONS
 * -------------------
 * - Cycle detection
 * - Strongly connected components
 * \_ Kosaraju: 2 DFS traversal
 * \_ Tarjan: 1 DFS traversal
 * - Topological sorting
 * \_ Used for dependency graphs
 * \_ When you have dependencies among  the jobs and we need to schedule the jobs
 * \_ Eg: Makefile utility
 * - Solving maze problems
 * 
 */

public class Graph {
  /* Create adjacency list */
  public static void addEdge(ArrayList<ArrayList<Integer>> adjList, int u, int v) {
    // As the graph is undirected
    // "u" is connected to "v" and vice versa
    adjList.get(u).add(v);
    adjList.get(v).add(u);
  }

  /* Prints adjacency list */
  public static void printAdjList(ArrayList<ArrayList<Integer>> adjList) {
    for (int i = 0; i < adjList.size(); i++) {
      for (int j = 0; j < adjList.get(i).size(); j++) {
        System.out.print(adjList.get(i).get(j) + " ");
      }
      System.out.println();
    }
  }

  /**
   * BFS Traversal of a Graph
   * 
   * @param adjList
   * @param V
   * @param S
   * 
   *                COMPLEXITY
   *                Time: O(V + E) | Every node & edge is explored
   *                Space: O(V) | Array to store visited nodes
   */
  public static void bfsTraversal(ArrayList<ArrayList<Integer>> adjList, int S, boolean[] visited) {
    Queue<Integer> queue = new LinkedList<>();
    visited[S] = true;
    queue.add(S);

    while (!queue.isEmpty()) {
      int u = queue.poll();
      System.out.print(u + " ");

      for (int v : adjList.get(u)) {
        if (!visited[v]) {
          visited[v] = true;
          queue.add(v);
        }
      }
    }
  }

  /**
   * BFS Traversal with disconnected nodes
   * \_ Count disconnected components
   * \_ Find no. of islands
   * 
   * @param adjList
   * @param V
   * @param S
   * 
   *                COMPLEXITY
   *                Time: O(V + E) | Since graph is disconnected
   *                Space: O(V) | Array to store visited nodes
   */
  public static void bfsTraversalDisconnected(ArrayList<ArrayList<Integer>> adjList, int V) {
    int count = 0;
    boolean[] visited = new boolean[V + 1];
    for (int i = 0; i < V; i++) {
      if (!visited[i]) {
        count++;
        bfsTraversal(adjList, i, visited);
      }
    }
    System.out.println("NO OF ISLANDS: " + count);
  }

  /**
   * DFS Traversal of a Graph
   * 
   * @param adjList
   * @param V
   */
  public static void dfsTraversal(ArrayList<ArrayList<Integer>> adjList, int V) {
    boolean[] visited = new boolean[V + 1];
    int count = 0;
    // dfsHelper(adjList, V, visited);
    for (int i = 0; i < V; i++) {
      if (!visited[i]) {
        count++;
        dfsHelper(adjList, i, visited);
      }
    }
    System.out.println("NO OF ISLANDS: " + count);
  }

  public static void dfsHelper(ArrayList<ArrayList<Integer>> adjList, int S, boolean[] visited) {
    visited[S] = true;
    System.out.print(S + " ");
    for (int u : adjList.get(S)) {
      if (!visited[u]) {
        dfsHelper(adjList, u, visited);
      }
    }
  }

  public static void main(String[] args) {
    // int V = 5;
    // ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>(V);
    // for (int i = 0; i < V; i++) {
    // // Each index/vertex will contain an ArrayList
    // adjList.add(new ArrayList<>());
    // }
    // addEdge(adjList, 0, 1);
    // addEdge(adjList, 0, 2);
    // addEdge(adjList, 1, 2);
    // addEdge(adjList, 1, 3);
    // printAdjList(adjList);
    // 1 2
    // 0 2 3
    // 0 1
    // 1

    // int V = 7;
    // ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(V);
    // for (int i = 0; i < V; i++) {
    // adj.add(new ArrayList<Integer>());
    // }
    // addEdge(adj, 0, 1);
    // addEdge(adj, 0, 2);
    // addEdge(adj, 2, 3);
    // addEdge(adj, 1, 3);
    // addEdge(adj, 4, 5);
    // addEdge(adj, 5, 6);
    // addEdge(adj, 4, 6);
    // bfsTraversalDisconnected(adj, V); // [0 1 2 3 4 5 6] 2

    // int V = 5;
    // ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(V);
    // for (int i = 0; i < V; i++) {
    // adj.add(new ArrayList<Integer>());
    // }
    // addEdge(adj, 0, 1);
    // addEdge(adj, 0, 2);
    // addEdge(adj, 1, 2);
    // addEdge(adj, 3, 4);
    // dfsTraversal(adj, V); // [0 1 2 3 4] 2
  }
}
