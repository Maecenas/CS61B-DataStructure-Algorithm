import java.io.*;

public class test {
    public static void main(String[] argv) {
        // for part I
        X[] xa = new Y[10];
        Y[] ya = new Y[10];
        X xe = new X();
        Y ye = new Y();
        for(int i = 0; i < 10; i++)
        {
            xa[i] = new Y();
            ya[i] = new Y();
        }
        // xa[0] = ya[0];

        System.out.println(xe.x);
        System.out.println(ye.y);


        xa = ya;
        Y[] ya = (Y[]) xa;
        // System.out.println(xa[0].x);
        System.out.println(ya[0].y);



        // for (int i = 0; i < xa.length; i++) {
        //     System.out.println(xa[i].x);
        // }

        // System.out.println(xa[0].x);
        // System.out.println(ya[0].y);
    }
}
