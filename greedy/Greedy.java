package PSDS.greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Greedy {

  /**
   * =====================
   * GREEDY
   * =====================
   * 
   * Used for optmization problem - maximize or minmize something
   * Eg: Shortes/Longest or Min/Max weight path problem
   * Use cases: Djikstra, Prim's, Krushkal, Knapsack are Greedy algorithm
   * 
   * res = 0
   * while (All items are not allowed) {
   * * i = select an item
   * * if (isFeasible (i)) {
   * * * res += i
   * * }
   * return res
   * }
   * 
   * NOTE: Greedy does not work at all times
   * A: {18,1,10}
   * amount: 20
   * Here we will get result as 3
   * Whereas correct/minimum solution is 2
   * 
   * Another example is getting longest/max weight path
   * which can get wrong result
   * 
   * @param A
   * @param N
   * @return
   */

  /**
   * Min coins to get max value
   * 
   * @param A
   * @param N
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(NlogN)
   *         Space: O(logN)
   */
  public static int minNoCoins(Integer[] A, int N, int amount) {
    Arrays.sort(A, Collections.reverseOrder());
    int ans = 0;

    for (int i = 0; i < N; i++) {
      if (A[i] <= amount) {
        int c = (int) Math.floor(amount / A[i]);
        ans += c;
        amount = amount - c * A[i];
      }
      if (amount == 0) {
        break;
      }
    }

    return ans;
  }

  /**
   * Max no. of activities
   * 
   * - Sort the array using finish time
   * - Initialize ans = 1
   * - Compare start time of curr with finish time of prev
   * 
   * @param A
   * @param N
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(NlogN)
   *         Space: O(logN)
   */
  public static int maxActivitySelection(Activity[] A, int N) {
    Arrays.sort(A, new Comparator<Activity>() {
      @Override
      public int compare(Activity a1, Activity a2) {
        return a1.finish - a2.finish;
      }
    });

    int ans = 1;
    int prev = 0;
    for (int i = 1; i < N; i++) {
      if (A[i].start >= A[prev].finish) {
        ans++;
        prev = i;
      }
    }

    return ans;
  }

  /**
   * Fractional Knapsack
   * 
   * - Find ratio (value/weight) for every item
   * - Sort items in descending order of ratio
   * - If A[i].weight <= capcity keeping adding the value
   * - Else add A[i].value * capcity / A[i].weight
   * 
   * @param Items
   * @param N
   * @return
   */
  public static double fractionalKnapsack(Item[] A, int N, int W) {
    Arrays.sort(A);
    double ans = 0.00;

    for (int i = 0; i < N; i++) {
      if (A[i].weight <= W) {
        ans = ans + A[i].value;
        W = W - A[i].weight;
      } else {
        ans = ans + (A[i].value * W / A[i].weight);
        return ans;
      }
    }

    return ans;
  }

  /**
   * Job scheduling problem
   * 
   * @param A
   * @param N
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(NlogN + NM)
   *         Space: O(M)
   */
  public static int[] maxJobScheduleProfit(Job[] A, int N) {
    // Sort based on profits
    Arrays.sort(A, (a, b) -> (b.profit - a.profit));

    // Get max deadline
    int maxDeadline = Integer.MIN_VALUE;
    for (int i = 0; i < N; i++) {
      maxDeadline = Math.max(maxDeadline, A[i].deadline);
    }

    // Array to keep track of jobs
    int[] ans = new int[maxDeadline + 1];
    Arrays.fill(ans, -1);

    int countJobs = 0, jobProfit = 0;
    for (int i = 0; i < N; i++) {
      // Get a job "id" and try to do that job as late as possible
      for (int j = maxDeadline; j > 0; j--) { // OR int j = A[i].deadline
        if (ans[j] == -1) {
          ans[j] = i;
          countJobs++;
          jobProfit += A[i].profit;
          break;
        }
      }
    }

    return new int[] { countJobs, jobProfit };
  }

  public static void main(String[] args) {
    // Integer[] A = { 10, 5, 2, 1 }; // 7
    // System.out.println(minNoCoins(A, A.length, 57));

    // Activity activities[] = { new Activity(12, 25),
    // new Activity(10, 20),
    // new Activity(20, 30) }; // 2
    // System.out.println(maxActivitySelection(activities, activities.length));

    // Item items[] = { new Item(10, 60),
    // new Item(40, 40),
    // new Item(20, 100),
    // new Item(30, 120) }; // 240.0
    // System.out.println(fractionalKnapsack(items, items.length, 50));

    // Item items[] = { new Item(10, 60),
    // new Item(50, 600),
    // new Item(20, 500),
    // new Item(30, 400) }; // 1140.0
    // System.out.println(fractionalKnapsack(items, items.length, 70));

    Job[] jobs = {
        new Job(1, 4, 20),
        new Job(2, 5, 60),
        new Job(3, 6, 70),
        new Job(4, 6, 65),
        new Job(5, 4, 25),
        new Job(6, 2, 80),
        new Job(7, 2, 10),
        new Job(8, 2, 22),
    }; // [6, 322]
    System.out.println(Arrays.toString(maxJobScheduleProfit(jobs, jobs.length)));
  }
}

class Activity {
  int start, finish;

  Activity(int start, int finish) {
    this.start = start;
    this.finish = finish;
  }
}

class Item implements Comparable<Item> {
  int weight;
  int value;

  Item(int weight, int value) {
    this.weight = weight;
    this.value = value;
  }

  @Override
  public int compareTo(Item item) {
    return (this.weight * item.value) - (value * item.weight);
  }
}

class Job {
  int id, deadline, profit;

  Job(int id, int deadline, int profit) {
    this.id = id;
    this.deadline = deadline;
    this.profit = profit;
  }
}
