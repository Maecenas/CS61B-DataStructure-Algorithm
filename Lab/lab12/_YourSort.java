/* YourSort.java */

public class YourSort {

  public static void sort(int[] A) {
    if(A.length < 65) {
      Sort.insertionSort(A);
    } else {
      Sort.quicksort(A);
    }
  }
}
