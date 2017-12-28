package list;

/* list/DList.java */

/**
 *  A DList is a mutable doubly-linked list.  Its implementation is
 *  circularly-linked and employs a sentinel (dummy) node at the head
 *  of the list.
 *  imported from ~cs61b/lab/lab4/list.
 */

public class DList {

  /**
   *  head references the sentinel node.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  protected DListNode head;
  protected long size;

  /* DList invariants:
   *  1)  head != null.
   *  2)  For any DListNode x in a DList, x.next != null.
   *  3)  For any DListNode x in a DList, x.prev != null.
   *  4)  For any DListNode x in a DList, if x.next == y, then y.prev == x.
   *  5)  For any DListNode x in a DList, if x.prev == y, then y.next == x.
   *  6)  size is the number of DListNode2s, NOT COUNTING the sentinel
   *      (denoted by "head"), that can be accessed from the sentinel by
   *      a sequence of "next" references.
   */

  /**
   *  DList() constructor for an empty DList.
   */
  public DList() {
    head = new DListNode();
    head.item = new int[4];
    head.next = head;
    head.prev = head;
    size = 0;
  }

//  public void insert(int[] item) {
//    insertBack(item);
//  }

  public void insertBack(int[] item) {
    DListNode inserts = new DListNode(item);
    inserts.prev = head.prev;
    inserts.next = head;
    inserts.prev.next = inserts;
    head.prev = inserts;
    size++;
  }

  // insertAfter and insertBefore differ when insert multiple objects

  public void insertBefore(DListNode DNode, int[] item) {
    DListNode inserts = new DListNode(item);
    inserts.prev = DNode.prev;
    inserts.next = DNode;
    DNode.prev.next = inserts;
    DNode.prev = inserts;
    size++;
  }

  public void insertAfter(DListNode DNode, int[] item) {
    DListNode inserts = new DListNode(item);
    inserts.prev = DNode;
    inserts.next = DNode.next;
    DNode.next.prev = inserts;
    DNode.next = inserts;
    size++;
  }

  public void insertAfter(DListNode DNode, DListNode inserts) {
    inserts.prev = DNode;
    inserts.next = DNode.next;
    DNode.next.prev = inserts;
    DNode.next = inserts;
    size++;
  }

  public void removeFront() {
    if (head.next != head) {
      head.next = head.next.next;
      head.next.prev = head;
      size--;
    }
  }

  public void removeAt(DListNode DNode) {
    if (DNode != this.head) {
      DNode.prev.next = DNode.next;
      DNode.next.prev = DNode.prev;
      size--;
    }
  }

  public DListNode getHead() {
    return head;
  }

  public long getSize() {
    return size;
  }

  /**
   *  toString() returns a String representation of this DList.
   *
   *  DO NOT CHANGE THIS METHOD.
   *
   *  @return a String representation of this DList.
   */
  public String toString() {
    String result = "[  ";
    DListNode current = head.next;
    while (current != head) {
      result = result + current.item + "  ";
      current = current.next;
    }
    return result + "]";
  }

  public static void main(String[] args) {
      System.out.println("Running Dlist.__main__");
    }

}
