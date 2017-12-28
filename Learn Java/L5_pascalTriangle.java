/* pascalTriangle.java */

import java.io.*;
import java.lang.*;

class pascalTriangle {

	public static int[][] pascalTriangle(int n) {
		int[][] pt = new int[n][];
		for (int i = 0; i < n; i++) {
			pt[i] = new int[i + 1];                              // Construct row i.
			pt[i][0] = 1;                                // Leftmost value of row i.
			for (int j=1; j < 1; j++) {
				pt[i][j] = pt[i - 1][j - 1] + pt[i - 1][j];  // Sum 2 entries above.
			}
			pt[i][i] = 1;                               // Rightmost value of row i.
		}
		return pt;
	}

	public static void main(String[] args) throws Exception {
		System.out.print("Set pascal triangle numbers: ");
		BufferedReader keybd =
			new BufferedReader(new InputStreamReader(System.in));
		int limit = Integer.parseInt(keybd.readLine());
		System.out.print(pascalTriangle(limit));      // TODO: add __print__ method.
	}
}