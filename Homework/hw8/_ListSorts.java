/* ListSorts.java */

import list.*;

public class ListSorts {

  private final static int[] SORTSIZES = {100, 1000, 10000, 100000, 1000000};

  /**
   *  makeQueueOfQueues() makes a queue of queues, each containing one item
   *  of q.  Upon completion of this method, q is empty.
   *  @param q is a LinkedQueue of objects.
   *  @return a LinkedQueue containing LinkedQueue objects, each of which
   *    contains one object from q.
   **/
  public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {
    LinkedQueue queue = new LinkedQueue();
      int size = q.size();
      for (int i = 0; i < size; i++) {
        try {
          LinkedQueue p = new LinkedQueue();
          p.enqueue(q.dequeue());
          queue.enqueue(p);
        } catch (QueueEmptyException e) {
         System.err.println();
        }
      }
      return queue;
  }

  /**
   *  mergeSortedQueues() merges two sorted queues into a third.  On completion
   *  of this method, q1 and q2 are empty, and their items have been merged
   *  into the returned queue.
   *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @return a LinkedQueue containing all the Comparable objects from q1 
   *   and q2 (and nothing else), sorted from smallest to largest.
   **/
  public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {
      LinkedQueue queue = new LinkedQueue();
    try {
      while (q1.size() > 0 && q2.size() > 0) {
        if (((Comparable) q1.front()).compareTo(q2.front()) <= 0) {
          queue.enqueue(q1.dequeue());
        } else {
          queue.enqueue(q2.dequeue());
        }
      }
      while (q1.size() > 0) {
        queue.enqueue(q1.dequeue());
      }
      while (q2.size() > 0) {
        queue.enqueue(q2.dequeue());
      }
    } catch (QueueEmptyException e) {
      System.err.println();
    }
    return queue;
  }

  /**
   *  partition() partitions qIn using the pivot item.  On completion of
   *  this method, qIn is empty, and its items have been moved to qSmall,
   *  qEquals, and qLarge, according to their relationship to the pivot.
   *  @param qIn is a LinkedQueue of Comparable objects.
   *  @param pivot is a Comparable item used for partitioning.
   *  @param qSmall is a LinkedQueue, in which all items less than pivot
   *    will be enqueued.
   *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
   *    will be enqueued.
   *  @param qLarge is a LinkedQueue, in which all items greater than pivot
   *    will be enqueued.  
   **/   
  public static void partition(LinkedQueue qIn, Comparable pivot, 
                               LinkedQueue qSmall, LinkedQueue qEquals, 
                               LinkedQueue qLarge) {
    try {
      while (qIn.size() > 0) {
        Object obj = qIn.dequeue();
        int sgn = pivot.compareTo(obj);
        if (sgn > 0) {
          qSmall.enqueue(obj);
        } else if (sgn < 0) {
          qLarge.enqueue(obj);
        } else {
          qEquals.enqueue(obj);
        }
      }
    } catch (QueueEmptyException e) {
      System.err.println();
    }
  }

  /**
   *  mergeSort() sorts q from smallest to largest using mergesort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void mergeSort(LinkedQueue q) {
    if (q.size() > 1) {
      LinkedQueue queue = makeQueueOfQueues(q);
      try {
        while (queue.size() > 1) {
          queue.enqueue(mergeSortedQueues((LinkedQueue) queue.dequeue(), (LinkedQueue) queue.dequeue()));
        }
        q.append((LinkedQueue) queue.dequeue());
      } catch (QueueEmptyException e) {
        System.err.println();
      }
    }
  }

  /**
   *  quickSort() sorts q from smallest to largest using quicksort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void quickSort(LinkedQueue q) {
    // Choose a random integer between 1 and q.size().
    // Find the corresponding item using the nth() method,
    // and use the item as the pivot in a call to partition().
    // Recursively quickSort() the smaller and larger partitions,
    // and then put all of the items back into q in sorted order
    // by using the append() method.
    if (q.size() > 1) {
      int idx = (int) (Math.random() * q.size()) + 1;
      LinkedQueue qSmall = new LinkedQueue();
      LinkedQueue qLarge = new LinkedQueue();
      LinkedQueue qEquals = new LinkedQueue();
      Comparable pivot = (Comparable) q.nth(idx);
      partition(q, pivot, qSmall, qEquals, qLarge);
      quickSort(qSmall);
      quickSort(qLarge);
      q.append(qSmall);
      q.append(qEquals);
      q.append(qLarge);
    }
  }

  /**
   *  makeRandom() builds a LinkedQueue of the indicated size containing
   *  Integer items.  The items are randomly chosen between 0 and size - 1.
   *  @param size is the size of the resulting LinkedQueue.
   **/
  public static LinkedQueue makeRandom(int size) {
    LinkedQueue q = new LinkedQueue();
    for (int i = 0; i < size; i++) {
      q.enqueue(new Integer((int) (size * Math.random())));
    }
    return q;
  }

  /**
   *  main() performs some tests on mergesort and quicksort.  Feel free to add
   *  more tests of your own to make sure your algorithms works on boundary
   *  cases.  Your test code will not be graded.
   **/
  public static void main(String [] args) {

    LinkedQueue q = makeRandom(10);
    System.out.println(q.toString());
    mergeSort(q);
    System.out.println(q.toString());

    q = makeRandom(10);
    System.out.println(q.toString());
    quickSort(q);
    System.out.println(q.toString());

    /* Remove these comments for Part III. */
    for (int SORTSIZE : SORTSIZES) {

      Timer stopWatch = new Timer();
      q = makeRandom(SORTSIZE);
      stopWatch.start();
      mergeSort(q);
      stopWatch.stop();
      System.out.println("Mergesort time, " + SORTSIZE + " Integers:  " +
              stopWatch.elapsed() + " msec.");

      stopWatch.reset();
      q = makeRandom(SORTSIZE);
      stopWatch.start();
      quickSort(q);
      stopWatch.stop();
      System.out.println("Quicksort time, " + SORTSIZE + " Integers:  " +
              stopWatch.elapsed() + " msec.");
    }

  }

}
