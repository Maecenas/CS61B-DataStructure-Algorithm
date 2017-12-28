/* RGB.java */

public class RGB{
	// better implement with protected, package or private
	public short red;
	public short green;
	public short blue;
	public final static String[] cARRAY = {"red", "green", "blue"};

	public RGB() {
		// this.red = 0;
		// this.green = 0;
		// this.blue = 0;
	}

	public RGB(short red, short green, short blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public short getColor(String color) {
		if (color.equals("red")) {
			return red;
		} else if (color.equals("green")) {
			return green;
		} else if (color.equals("blue")) {
			return blue;
		} else {
			// try to find a better implement
			return -1;
		}
	}

	public short getRed() {
		return this.red;
	}

	public short getGreen() {
		return this.green;
	}

	public short getBlue() {
		return this.blue;
	}	

//	public static String[] getColorArray {
//		String[] colors = new {"red", "green", "blue"};
//		return colors;
//	}


	public String toString() {
		return " (" + getRed() + ", " + getGreen() + 
		       ", " + getBlue() + ")";
	}

	public static void main(String[] args) {
		RGB rgb = new RGB();
		System.out.println(rgb.red + " " + rgb.green + " " + rgb.blue);

		short red, green, blue; 
		red = 100;
		green = 200;
		blue = 240;
		RGB rgb2 = new RGB(red, green, blue);
		System.out.println(rgb2.red + " " + rgb2.green + " " + rgb2.blue);

		RGB[] rgb3 = new RGB[2];
		rgb3[0] = rgb;
		rgb3[1] = rgb2;

		for (RGB color : rgb3) {
			System.out.println(color.red + " " + color.green + " " + color.blue);
		}

		System.out.println("rgb2's green is " + rgb2.getColor("green"));

		try {
			System.out.println(rgb3[-1].red);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(rgb3[0].red);
		}

		System.out.println(rgb2);
	}


}