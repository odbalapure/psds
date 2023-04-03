package PSDS.graph;

import java.util.ArrayList;

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

  public static void main(String[] args) {
    int V = 5;
    ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>(V);
    for (int i = 0; i < V; i++) {
      // Each index/vertex will contain an ArrayList
      adjList.add(new ArrayList<>());
    }

    addEdge(adjList, 0, 1);
    addEdge(adjList, 0, 2);
    addEdge(adjList, 1, 2);
    addEdge(adjList, 1, 3);

    printAdjList(adjList);
  }
}
