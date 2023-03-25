package PSDS.linkedlist;

public class ListNode {
  int data;
  ListNode next;
  ListNode random;

  ListNode(int data) {
    this.data = data;
  }

  public static void printList(ListNode head) {
    ListNode temp = head;
    while (temp != null) {
      System.out.print(temp.data + " ");
      temp = temp.next;
    }
    System.out.println();
  }
}
