import java.net.*;
import java.io.*;

class WHWWW {
	public static void main(String[] arg) throws Exception{
		URL u = new URL("http://www.whitehouse.gov/");
		InputStream ins = u.openStream();
		InputStreamReader isr = new InputStreamReader(ins);
		BufferedReader whiteHouse = new BufferedReader(isr);
		System.out.println(whiteHouse.readLine());
	}
}