import java.io.*;
public class Dog extends Animal implements jInterface {
    public void test(int a) {
        System.out.println("in subclass int a ");
    }

    public void test(double a) {
		System.out.println("in subclass double a");	
        return;
    }

    public static void main(String[] args) {
    	int a = 5;
    	double b = 3.14159;
    	// test(a);
    	// test(b);
    	return;
    }
}
