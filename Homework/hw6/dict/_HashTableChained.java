/* HashTableChained.java */

package dict;
import list.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/

  private int numBuckets;
  private int numEntries;
  private int numCollisions;
  private List[] table;

  /**
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
      numEntries = 0;
      numBuckets = nextPrime(sizeEstimate);
      table = new DList[numBuckets];
      for (int i = 0; i < numBuckets; i++) {
          table[i] = new DList();
      }
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
      // nextPrime(100) == 101
      this(101);
  }

  /** 
   *  Find a prime number >= i
   *  @param i the number to start at to find next prime
   *  @return the first prim >= i
   **/

  public static int nextPrime(int i) {
      while (true) {
          if (isPrime(i)) { return i; }
          i++;
      }
  }

  /** 
   *  isPrime(int i) checks if a number 'i' is prime or not
   *  @param i the number to be check is prime or not
   *  @return true if i is prime, otherwise false
   **/

  public static boolean isPrime(int i) {
    int divisor = 2;
    while (divisor * divisor <= i) {
      if (i % divisor == 0) return false;
      divisor++;
    }
    return true;
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/


  int compFunction(int code) {
    int BIG_PRIME_NUMBER = nextPrime(numBuckets * 100);
    return ((2 * code + 3) % BIG_PRIME_NUMBER + BIG_PRIME_NUMBER) % numBuckets;
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    return numBuckets;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    return numEntries == 0;
  }

  /**
   *  Return the number of collisions in the hash table;
   *  Expected number of collisions is N - N * (1 - 1/N)^n
   *  @return number of collisions
   **/

  public int collisions() {
      return numCollisions;
  }

  /**
   *  Return the number of buckets in the hash table;
   *  @return number of buckets
   **/

  public int buckets() {
      return numBuckets;
  }

  /**
   *  Return the number of entries in the hash table;
   *  @return number of entries
   **/

  public int entries() {
      return numEntries;
  }

    /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
      Entry e = new Entry();
      e.key = key;
      e.value = value;
      int hashCode = compFunction(key.hashCode());
      if (table[hashCode].length() != 0 ) { numCollisions++; }
      table[hashCode].insertBack(e);
      numEntries++;
      return e;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
      int hashCode = compFunction(key.hashCode());
      List lst = table[hashCode];
      ListNode curr = lst.front();
      try {
          while (curr.isValidNode()) {
              if (curr.item() instanceof Entry && ((Entry) curr.item()).key.equals(key)) {
                  return (Entry) curr.item();
              } else {
                  curr = curr.next();
              }
          }
      } catch (InvalidNodeException e) {
          e.printStackTrace();
      }
      return null;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
      int hashCode = compFunction(key.hashCode());
      List lst = table[hashCode];
      ListNode curr = lst.front();
      try {
          while (curr.isValidNode()) {
              if (curr.item() instanceof Entry && ((Entry) curr.item()).key.equals(key)) {
                  Entry e = (Entry) curr.item();
                  curr.remove();
                  numEntries--;
                  return e;
              } else {
                  curr = curr.next();
              }
          }
      } catch (InvalidNodeException e) {
          e.printStackTrace();
      }
      return null;
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
      for (List lst : table) {
          lst = new DList();
      }
      numEntries = 0;
  }

}
