package list;

/* list/DListNode.java */


/**
 *  A DListNode is a node in a DList (doubly-linked list).
 *  imported from ~cs61b/lab/lab4/list.
 */

public class DListNode {

  /**
   *  item references the item stored in the current node.
   *  prev references the previous node in the DList.
   *  next references the next node in the DList.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  public int[] item;
  public DListNode prev;
  public DListNode next;

  /**
   *  DListNode() constructor.
   */
  public DListNode() {
    this.item = new int[] {};
    this.prev = null;
    this.next = null;
  }

//  public DListNode(Object o) {
//    item = o;
//    prev = null;
//    next = null;
//  }

  public DListNode(int[] item) {
    this.item = item;
    this.prev = null;
    this.next = null;
  }

  public String toString() {
    String result = "(  ";
    for (int i : (int[]) this.item) {
      result += i + ", ";
    }
    return result + ")";
  }

}
