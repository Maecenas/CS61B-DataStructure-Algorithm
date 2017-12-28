/* Set.java */

import list.*;

/**
 *  A Set is a collection of Comparable elements stored in sorted order.
 *  Duplicate elements are not permitted in a Set.
 **/
public class Set {
  /* Fill in the data fields here. */

  private List list;

  /**
   * Set ADT invariants:
   *  1)  The Set's elements must be precisely the elements of the List.
   *  2)  The List must always contain Comparable elements, and those elements 
   *      must always be sorted in ascending order.
   *  3)  No two elements in the List may be equal according to compareTo().
   **/

  /**
   *  Constructs an empty Set. 
   *
   *  Performance:  runs in O(1) time.
   **/
  public Set() { 
    list = new DList();
  }

  /**
   *  cardinality() returns the number of elements in this Set.
   *
   *  Performance:  runs in O(1) time.
   **/
  public int cardinality() {
    // Replace the following line with your solution.
    return list.length();
  }

  /**
   *  insert() inserts a Comparable element into this Set.
   *
   *  Sets are maintained in sorted order.  The ordering is specified by the
   *  compareTo() method of the java.lang.Comparable interface.
   *
   *  Performance:  runs in O(this.cardinality()) time.
   **/
  public void insert(Comparable c) {
    try {
      // try-catch clause?
      if (c == null) {
        // throw new InvalidNodeException("Insert invalid node.");
        // throw new InvalidNodeException();
        return;
      } else if (cardinality() == 0) {
        list.insertFront(c);
      } else {
          ListNode cur = this.list.front();
          while (cur.isValidNode()) {
            int sgn = c.compareTo(cur.item());
            if (sgn != 0) {

              if (sgn < 0) {
                cur.insertBefore(c);
                break;
              } else if (sgn > 0) {
                if (!cur.next().isValidNode()) {
                  list.insertBack(c);
                }
              }
            } else {
              break;
            }
            cur = cur.next();
          }
      }
    } catch (InvalidNodeException e) {
      e.printStackTrace();
    }
  }
    
  /**
   *  union() modifies this Set so that it contains all the elements it
   *  started with, plus all the elements of s.  The Set s is NOT modified.
   *  Make sure that duplicate elements are not created.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Your implementation should NOT copy elements of s or "this", though it
   *  will copy _references_ to the elements of s.  Your implementation will
   *  create new _nodes_ for the elements of s that are added to "this", but
   *  you should reuse the nodes that are already part of "this".
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT ATTEMPT TO COPY ELEMENTS; just copy _references_ to them.
   **/
  public void union(Set s) {
    
    // take notice when this.list end first
    try {
      if (s.cardinality() == 0) {
        // throw new InvalidNodeException("Union invalid set");
        return; 
      } else if (this.cardinality() == 0) {
        list = new DList();
        ListNode cur2 = s.list.front();
        System.out.println("length: " + s.list.length());
        while (cur2.isValidNode()) {
          list.insertBack(cur2.item());
          cur2 = cur2.next();
        }
      } else {
          ListNode cur1 = this.list.front();
          ListNode cur2 = s.list.front();

          while (cur1.isValidNode() && cur2.isValidNode()) {
            int sgn = ((Comparable) cur2.item()).compareTo(cur1.item());
            if (sgn < 0) {
              cur1.insertBefore(cur2.item());
              cur2 = cur2.next();
            } else if (sgn == 0) {
              // when cur1 = tail
              // the following line of code will cause error
              // cur1 = cur1.next();
              cur2 = cur2.next();
            } else if (sgn > 0) {
              if (cur1.next().isValidNode()) {
                cur1 = cur1.next();
              } else {
                // append the rest of cur2 to cur1
                while (cur2.isValidNode()) {
                list.insertBack(cur2.item());
                cur2 = cur2.next();
                }
              }
            }
          }
      }

    } catch (InvalidNodeException e) {
      e.printStackTrace();
    }
  }

  /**
   *  intersect() modifies this Set so that it contains the intersection of
   *  its own elements and the elements of s.  The Set s is NOT modified.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Do not construct any new ListNodes during the execution of intersect.
   *  Reuse the nodes of "this" that will be in the intersection.
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT CONSTRUCT ANY NEW NODES.
   *  DO NOT ATTEMPT TO COPY ELEMENTS.
   **/
  public void intersect(Set s) {
    
    // take notice when s.list end first
    try {
      if (s.cardinality() == 0 || this.cardinality() == 0) {
        // Constructor
        // Set();
        this.list = new DList();
      } else {
        ListNode cur1 = this.list.front();
        ListNode cur2 = s.list.front();

        while (cur1.isValidNode() && cur2.isValidNode()) {
          int sgn = ((Comparable) cur2.item()).compareTo(cur1.item());
          if (sgn > 0) {
            // when cur1 = tail will throw InvalidNodeException
            // cur1 = cur1.next();
            // cur1.prev().remove();

            ListNode tmp = cur1;
            cur1 = cur1.next();
            tmp.remove();
          } else if (sgn == 0) {
            cur1 = cur1.next();
            // when cur2 = tail
            // the following line of code will cause error
            // cur2 = cur2.next();
          } else if (sgn < 0) {
            if (cur2.next().isValidNode()) {
              cur2 = cur2.next();
            } else {
              // remove the rest of cur1 when cur2 ends
              while (cur1.isValidNode()) {
                ListNode tmp = cur1;
                cur1 = cur1.next();
                tmp.remove();
              }
            }
          }
        }
      }

    } catch (InvalidNodeException e) {
      e.printStackTrace();
    }
  }

  /**
   *  toString() returns a String representation of this Set.  The String must
   *  have the following format:
   *    {  } for an empty Set.  No spaces before "{" or after "}"; two spaces
   *            between them.
   *    {  1  2  3  } for a Set of three Integer elements.  No spaces before
   *            "{" or after "}"; two spaces before and after each element.
   *            Elements are printed with their own toString method, whatever
   *            that may be.  The elements must appear in sorted order, from
   *            lowest to highest according to the compareTo() method.
   *
   *  WARNING:  THE AUTOGRADER EXPECTS YOU TO PRINT SETS IN _EXACTLY_ THIS
   *            FORMAT, RIGHT UP TO THE TWO SPACES BETWEEN ELEMENTS.  ANY
   *            DEVIATIONS WILL LOSE POINTS.
   **/
  public String toString() {
    return list.toString();
  }

  public static void main(String[] argv) {
    // Set s = new Set();
    // s.insert(new Integer(3));
    // s.insert(new Integer(4));
    // s.insert(new Integer(3));
    // System.out.println("Set s = " + s);

    // Set s2 = new Set();
    // s2.insert(new Integer(4));
    // s2.insert(new Integer(5));
    // s2.insert(new Integer(5));
    // System.out.println("Set s2 = " + s2);

    // Set s3 = new Set();
    // s3.insert(new Integer(5));
    // s3.insert(new Integer(3));
    // s3.insert(new Integer(8));
    // System.out.println("Set s3 = " + s3);

    // s.union(s2);
    // System.out.println("After s.union(s2), s = " + s);
    // System.out.println("s.cardinality() = " + s.cardinality());

    // s.intersect(s3);
    // System.out.println("After s.intersect(s3), s = " + s);

    // System.out.println("s.cardinality() = " + s.cardinality());
    // // You may want to add more (ungraded) test code here.

    Set s = new Set();
    System.out.println("s.cardinality() = " + s.cardinality());
    s.insert(new Integer(3));
    s.insert(new Integer(4));
    s.insert(new Integer(3));
    s.insert(new Integer(1));
    s.insert(new Integer(9));
    s.insert(new Integer(10));
    s.insert(new Integer(11));
    System.out.println("Set s = " + s);
    System.out.println("s.cardinality() = " + s.cardinality());

    Set s2 = new Set();
    s2.insert(new Integer(4));
    s2.insert(new Integer(5));
    s2.insert(new Integer(5));
    System.out.println("Set s2 = " + s2);
    System.out.println("s2.cardinality() = " + s2.cardinality());

    Set s3 = new Set();
    s3.insert(new Integer(5));
    s3.insert(new Integer(3));
    s3.insert(new Integer(8));
    System.out.println("Set s3 = " + s3);
    System.out.println("s3.cardinality() = " + s3.cardinality());

    //s.union(s2);
    //System.out.println("After s.union(s2), s = " + s);
    //System.out.println("s.cardinality() = " + s.cardinality());

    s2.union(s);
    System.out.println("After s2.union(s), s2 = " + s2);
    System.out.println("s2.cardinality() = " + s2.cardinality());
    System.out.println("s: " + s);

    //s.intersect(s3);
    //System.out.println("After s.intersect(s3), s = " + s);
    s3.intersect(s2);
    System.out.println("After s3.intersect(s2), s3 = " + s3);
    System.out.println("s2: " + s2);

    System.out.println("s3.cardinality() = " + s3.cardinality());
    // You may want to add more (ungraded) test code here.
  }
}
