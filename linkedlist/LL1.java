package PSDS.linkedlist;

import java.util.HashMap;

public class LL1 {

  /**
   * Insert a value in proper place inside a sorted LL
   * 
   * @param head
   * @param k
   * @return
   */
  public static ListNode insertSortedOrder(ListNode head, int k) {
    ListNode node = new ListNode(k);
    ListNode temp = head;

    while (temp.next != null && temp.next.data < k) {
      temp = temp.next;
    }

    node.next = temp.next;
    temp.next = node;

    return head;
  }

  /**
   * Reverse a linked list
   * 
   * @param head
   * @return
   */
  public static ListNode reverse(ListNode head) {
    ListNode curr = head, prev = null;
    
    while (curr != null) {
      ListNode next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }

    return prev;
  }

  /**
   * Copy a linked list with random pointers
   * 
   * APPROACH:
   * - Store the copy all nodes in hahsmap <Node, Node>
   * - Do map.put(node, new ListNode(node.data))
   * - Traverse the ll again and do
   * \_ ListNode clone = map.get(curr)
   * \_ clone.next = map.get(curr.next)
   * \_ clone.random = map.get(curr.random)
   * 
   * @param head
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N)
   *         Space: O(N)
   */
  public static ListNode copyListHash(ListNode head) {
    // Store deep copy nodes
    // key -> original node, value -> deep copy node
    HashMap<ListNode, ListNode> map = new HashMap<>();
    ListNode temp = head;

    // Create node deep copies w/o pointers
    while (temp != null) {
      map.put(temp, new ListNode(temp.data));
      temp = temp.next;
    }

    // Link the deep copy node
    temp = head;
    while (temp != null) {
      ListNode clone = map.get(temp);
      clone.next = map.get(temp.next);
      clone.random = map.get(temp.random);
    }

    return map.get(head);
  }

  /**
   * 
   * APPOACH
   * - Create a deep copy of node and insert it right after that original node
   * - Connect the cloned nodes
   * - Segregate original nodes from deep copied ones
   * 
   * @param head1
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N)
   *         Space: O(N)
   */
  public static ListNode copyListHashBetter(ListNode head1) {
    ListNode curr = head1;

    // Insert deep copied nodes next to original ones
    while (curr != null) {
      ListNode next = curr.next;
      curr.next = new ListNode(curr.data);
      curr.next.next = next;
      curr = next;
    }

    // Start setting up pointers for copied nodes
    for (ListNode temp = head1; temp != null; temp = temp.next.next) {
      temp.next.random = temp.random == null ? null : temp.random.next;
    }

    // Separate cloned and og nodes
    ListNode head2 = head1.next;
    ListNode clone = head2;
    for (ListNode temp = head2; temp != null; temp = temp.next) {
      // Skip 1 node to connect copy and og nodes
      temp.next = temp.next.next;
      clone.next = clone.next != null ? clone.next.next : null;
      // Move to next OG node
      clone = clone.next;
    }

    return head2;
  }

  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    head.next = new ListNode(10);
    head.next.next = new ListNode(20);
    head.next.next.next = new ListNode(21);
    head.next.next.next.next = new ListNode(25);
    // head = insertSortedOrder(head, 11); // 1 10 11 20 21 25
    head = reverse(head);
    ListNode.printList(head);
  }
}
