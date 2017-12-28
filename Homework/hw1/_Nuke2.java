/* Nuke2.java */

import java.io.*;

/**  A class called Nuke2 whose main method reads a string from the keyboard 
*    and prints the same string, with its second character removed. */
class Nuke2 {
	public static void main(String[] arg) throws Exception {
		BufferedReader keybd = new BufferedReader(
			new InputStreamReader(System.in));
		String s = keybd.readLine();
		s = s.substring(0, 1) + s.substring(2);
		System.out.println(s);
	}
}