/* Inheritance.java */

import java.io.*;
import java.lang.*;

class printPrimes {

	public static void printPrimes(int n) {
		boolean[] prime = new boolean[n+1];
		int i;
		for (i = 2; i <= n; i++) {
			prime[i] = true;
		}
		for (int divisor = 2; divisor * divisor <= n; divisor++) {
			if (prime[divisor]) {
				for (i = 2 * divisor; i <= n; i = i + divisor) {
					prime[i] = false;    // find the divisible i
				}
			}
		}
		for (i = 2; i <= n; i++) {
			if (prime[i]) {
				System.out.print(" " + i);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.print("Set prime number boundaries: ");
		BufferedReader keybd =
			new BufferedReader(new InputStreamReader(System.in));
		int limit = Integer.parseInt(keybd.readLine());
		printPrimes(limit);
	}
}