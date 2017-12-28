/* RunLengthEncoding.java */

/**
 * The RunLengthEncoding class defines an object that run-length encodes
 * a PixImage object.  Descriptions of the methods you must implement appear
 * below.  They include constructors of the form
 * <p>
 * public RunLengthEncoding(int width, int height);
 * public RunLengthEncoding(int width, int height, int[] red, int[] green,
 * int[] blue, int[] runLengths) {
 * public RunLengthEncoding(PixImage image) {
 * <p>
 * that create a run-length encoding of a PixImage having the specified width
 * and height.
 * <p>
 * The first constructor creates a run-length encoding of a PixImage in which
 * every pixel is black.  The second constructor creates a run-length encoding
 * for which the runs are provided as parameters.  The third constructor
 * converts a PixImage object into a run-length encoding of that image.
 * <p>
 * See the README file accompanying this project for additional details.
 */

import list.*;

public class RunLengthEncoding implements Iterable {

    /**
     *  Define any variables associated with a RunLengthEncoding object here.
     *  These variables MUST be private.
     */

    private DList runList;
    private DListNode head;
    private int width;
    private int height;


    /**
     *  The following methods are required for Part II.
     */

    /**
     *  RunLengthEncoding() (with two parameters) constructs a run-length
     *  encoding of a black PixImage of the specified width and height, in which
     *  every pixel has red, green, and blue intensities of zero.
     *
     *  @param width the width of the image.
     *  @param height the height of the image.
     */

    public RunLengthEncoding(int width, int height) {
        runList = new DList();
        this.head = runList.getHead();
        this.width = width;
        this.height = height;
        int[] run = new int[4];
        run[0] = width * height;
        run[1] = 0;
        run[2] = 0;
        run[3] = 0;

        runList.insertBack(run);

        // which is better?

//    RunLengthEncoding(width, height, new int[1] = {0}, new int[1] = {0}, new int[1] = {0},
//                      new int[1] = {width * height});
    }

    /**
     *  RunLengthEncoding() (with six parameters) constructs a run-length
     *  encoding of a PixImage of the specified width and height.  The runs of
     *  the run-length encoding are taken from four input arrays of equal length.
     *  Run i has length runLengths[i] and RGB intensities red[i], green[i], and
     *  blue[i].
     *
     *  @param width the width of the image.
     *  @param height the height of the image.
     *  @param red is an array that specifies the red intensity of each run.
     *  @param green is an array that specifies the green intensity of each run.
     *  @param blue is an array that specifies the blue intensity of each run.
     *  @param runLengths is an array that specifies the length of each run.
     *
     *  NOTE:  All four input arrays should have the same length (not zero).
     *  All pixel intensities in the first three arrays should be in the range
     *  0...255.  The sum of all the elements of the runLengths array should be
     *  width * height.  (Feel free to quit with an error message if any of these
     *  conditions are not met--though we won't be testing that.)
     */

    public RunLengthEncoding(int width, int height, int[] red, int[] green,
                             int[] blue, int[] runLengths) {
        runList = new DList();
        this.head = runList.getHead();
        this.width = width;
        this.height = height;
        for (int i = 0; i < runLengths.length; i++) {
            int[] item = new int[4];
            item[0] = runLengths[i];
            item[1] = red[i];
            item[2] = green[i];
            item[3] = blue[i];

            runList.insertBack(item);
        }
    }

    /**
     *  getWidth() returns the width of the image that this run-length encoding
     *  represents.
     *
     *  @return the width of the image that this run-length encoding represents.
     */

    public int getWidth() {
        return this.width;
    }

    /**
     *  getHeight() returns the height of the image that this run-length encoding
     *  represents.
     *
     *  @return the height of the image that this run-length encoding represents.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     *  iterator() returns a newly created RunIterator that can iterate through
     *  the runs of this RunLengthEncoding.
     *
     *  @return a newly created RunIterator object set to the first run of this
     *  RunLengthEncoding.
     */
    public RunIterator iterator() {
        return new RunIterator(runList);
        // You'll want to construct a new RunIterator, but first you'll need to
        // write a constructor in the RunIterator class.
    }

    /**
     *  toPixImage() converts a run-length encoding of an image into a PixImage
     *  object.
     *
     *  @return the PixImage that this RunLengthEncoding encodes.
     */
    public PixImage toPixImage() {
        PixImage PixImg = new PixImage(width, height);
        int x = 0, y = 0;
        RunIterator iter = this.iterator();
        while (iter.hasNext()) {
            int[] item = iter.next();
            int num = item[0];
            short red = (short) item[1], green = (short) item[2], blue = (short) item[3];
//      int {num, red, green, blue} = item;
            for (int i = 0; i < num; i++) {
                if (x == width) {
                    y++;
                    x = 0;
                } else {
                    x++;
                }
                PixImg.setPixel(x, y, red, green, blue);
            }


        }
        // System.out.println(PixImg.toString());
        return PixImg;
    }

    /**
     *  toString() returns a String representation of this RunLengthEncoding.
     *
     *  This method isn't required, but it should be very useful to you when
     *  you're debugging your code.  It's up to you how you represent
     *  a RunLengthEncoding as a String.
     *
     *  @return a String representation of this RunLengthEncoding.
     */
    public String toString() {
        String result = "{ ";
        DListNode head = runList.getHead();
        DListNode current = head.next;
        while (current != head) {
            int[] run = (int[]) current.item;
            result += "[";
            for (int i = 0; i < run[0]; i++) {
                result = "(" + result + run[1] + ", " + run[2] + ", " + run[3] + ") ";
            }
            result += "] ";
            current = current.next;
        }
        result += "} ";
        return result;
    }

    /**
     *  The following methods are required for Part III.
     */

    /**
     *  RunLengthEncoding() (with one parameter) is a constructor that creates
     *  a run-length encoding of a specified PixImage.
     *
     *  Note that you must encode the image in row-major format, i.e., the second
     *  pixel should be (1, 0) and not (0, 1).
     *
     *  @param image is the PixImage to run-length encode.
     */
    public RunLengthEncoding(PixImage image) {
        img2List(image);
        check();
    }

    /**
     *  check() walks through the run-length encoding and prints an error message
     *  if two consecutive runs have the same RGB intensities, or if the sum of
     *  all run lengths does not equal the number of pixels in the image.
     */
    public void check() {
        // something like a modular test
        DListNode head = runList.getHead();
        DListNode runNode = head.next;

        long length = 0;
        while (runNode != head) {
            int[] item = runNode.item;
//      if (isSameRun(item, (int[]) runNode.next.item)) {
//        System.out.println("ERROR! Two consecutive runs have same content " +
//                           runNode.toString() + "at " + length);
//      }
            if (item[0] < 1) {
                System.out.println("ERROR! Construct an invalid length: " + item[0]);
            }
            length += item[0];
            // System.out.println(item[0]);
            runNode = runNode.next;
        }
        // fix this later
        if (!(length == width * height)) {
            System.out.println("ERROR! Sum of runs does not equal the size of PixImage");
            System.out.println("size of run: " + length);
            System.out.println("size of PixImage: " + width * height);
        }
    }

    // to implement check()
    private boolean isSameRun(int[] curr, int[] next) {
        return curr[1] == next[1] && curr[2] == next[2] && curr[3] == next[3];
    }

    /**
     *  The following method is required for Part IV.
     */

    /**
     *  setPixel() modifies this run-length encoding so that the specified color
     *  is stored at the given (x, y) coordinates.  The old pixel value at that
     *  coordinate should be overwritten and all others should remain the same.
     *  The updated run-length encoding should be compressed as much as possible;
     *  there should not be two consecutive runs with exactly the same RGB color.
     *
     *  @param x the x-coordinate of the pixel to modify.
     *  @param y the y-coordinate of the pixel to modify.
     *  @param red the new red intensity to store at coordinate (x, y).
     *  @param green the new green intensity to store at coordinate (x, y).
     *  @param blue the new blue intensity to store at coordinate (x, y).
     */
    void setPixel(int x, int y, short red, short green, short blue) {
        // modify the DList type and add insertAfter and removeAt method

        /** First find the Pixel and Run that this coordinate is referring to.
         * 1) If the pixel is different that the RGB of the run it is stored in:
         *    1) If the coordinate is the first pixel in the Run
         *       1) Check if the pixel matches the pixel before
         *          1) increase the length in previous Run
         *          2) decrease the length in current Run
         *          3) break
         *       2) Add a new Run
         *    2) If the cooridinate is the last pixel in the Run
         *       1) Check if the pixel matches the pixel after
         *          1) increase the length in next Run
         *          2) decrease the length in current Run
         *       2) Add a new Run
         *    3) If the pixel is in the middle of the Run
         *       1) Break this RUN into two pieces: Run1_length =  - 1
         *          Run2_length = Run_length - pixel_place
         *       2) Create a new Run: nRun = {length=1, red, green, blue}
         *       3) Run.prev -> Run1 -> nRun -> Run2 -> Run.next
         * 2) If the pixel is the same RGB as it used to
         *    1) Do nothing.
         */

        if (inBound(x, y)) {
            DListNode head = runList.getHead();
            DListNode runNode = head.next;
            int posit = y * width + x + 1; // (1, width * height)
            int length = 1;  // find the right run and locate pixel in the run

            int[] newItem = new int[4];
            newItem[0] = 1;
            newItem[1] = (int) red;
            newItem[2] = (int) green;
            newItem[3] = (int) blue;

            while (runNode != head) {
                int[] item = runNode.item;
                if (posit > length + item[0]) {
                    length += item[0];
                } else {
                    break;
                }
                runNode = runNode.next;
            }

            if (!isSameRun(runNode.item, newItem)) {
                if (runNode.item[0] == 1 &&
                        !isSameRun(runNode.prev.item, runNode.item) &&
                        isSameRun(runNode.item, runNode.next.item)) {
                    runNode.prev.item[0] += 1 + runNode.next.item[0];
                    runList.removeAt(runNode.next);
                    runList.removeAt(runNode);
                } else {
                    // at tail of the run
                    if (posit == length + runNode.item[0]) {
                        if (isSameRun(runNode.item, runNode.next.item)) {
                            runNode.item[0]--;
                            runNode.next.item[0]++;
                        } else {
                            runNode.item[0]--;
                            runList.insertAfter(runNode, newItem);
                        }
//                        int[][] concatRun = concatRun(unpackRun(runNode.next.item), rawRun);
//
//                        DList newRun = new DList();
//                        runNode.prev.prev.next = newRun.getHead().next
//                        newRun.getHead().next;
                    }
                    // at head of the run
                    else if (posit == length) {
                        if (isSameRun(runNode.item, runNode.prev.item)) {
                            runNode.item[0]--;
                            runNode.prev.item[0]++;
                        } else {
                            runNode.item[0]--;
                            runList.insertBefore(runNode, newItem);
                        }
                    }
                    // at the middle of the run
                    else {
                        int runLength = runNode.item[0];
                        runNode.item[0] = posit - length;
                        runList.insertAfter(runNode, newItem);
                        runList.insertAfter(runNode.next, runNode.item);
                        runNode.next.next.item[0] = runLength - runNode.item[0] - 1;
                    }
                }

//                int[][] rawRun = setPixel(unpackRun(runNode.item), (posit - length), newItem);
            }
            check();
        }

//    if (pixelNode.prev != head) {}


    /* dummy method, find out in readme */
//    PixImage PixImg = this.toPixImage();
//    PixImg.setPixel(x, y, red, green, blue);
//    img2List(PixImg);
//    check();
    }

    // img2List support setPixel, RunLengthEncoding,
    private void img2List(PixImage image) {
        width = image.getWidth();
        height = image.getHeight();
        runList = new DList();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int[] item = new int[4];
                item[0] = 1;
                item[1] = (int) image.getRed(i, j);
                item[2] = (int) image.getGreen(i, j);
                item[3] = (int) image.getBlue(i, j);
                runList.insertBack(item);
            }
        }

        DListNode head = runList.getHead();
        DListNode runNode = head.next;

        while (runNode != head) {
            if (isSameRun(runNode.item, runNode.next.item)) {
                runNode.item[0]++;

//        runNode.next.next.prev = runNode;
//        runNode.next = runNode.next.next;
//        runNode.next.prev = runNode;

        /* DList.removeAt(DListNode) would help */
                runList.removeAt(runNode);
            }
            runNode = runNode.next;
        }
    }

    private boolean inBound(int x, int y) {
        if (x >= width || y >= height || x < 0 || y < 0) {
            System.out.println("ERROR! setPixel: index out off bound!");
            return false;
        } else {
            return true;
        }
    }

//    private void refactorRun(DListNode runHead, DListNode runTail) {}
//
//    private int[][] unpackRun(int[] runItem) {
//        int runLength = runItem[0];
//        int[][] unpackRun = new int[runLength][4];
//        runItem[0] = 1;
//
//        for (int i = 0; i < runLength; i++) {
//            unpackRun[i] = runItem;
//        }
//        return unpackRun;
//    }
//
//    private DList packRun(int[][] packRun) {
//        DList packRun
//        return
//    }
//
//    private int[][] concatRun(int[][] initRun, int[][] secondRun) {
//    }
//
//    private int[][] setPixel(int[][] rawRun, int posit, int[] newPixel) {
//        rawRun[posit] = newPixel;
//        return rawRun;
//    }

    /**
     * TEST CODE:  YOU DO NOT NEED TO FILL IN ANY METHODS BELOW THIS POINT.
     * You are welcome to add tests, though.  Methods below this point will not
     * be tested.  This is not the autograder, which will be provided separately.
     */


    /**
     * doTest() checks whether the condition is true and prints the given error
     * message if it is not.
     *
     * @param b the condition to check.
     * @param msg the error message to print if the condition is false.
     */
    private static void doTest(boolean b, String msg) {
        if (b) {
            System.out.println("Good.");
        } else {
            System.err.println(msg);
        }
    }

    /**
     * array2PixImage() converts a 2D array of grayscale intensities to
     * a grayscale PixImage.
     *
     * @param pixels a 2D array of grayscale intensities in the range 0...255.
     * @return a new PixImage whose red, green, and blue values are equal to
     * the input grayscale intensities.
     */
    private static PixImage array2PixImage(int[][] pixels) {
        int width = pixels.length;
        int height = pixels[0].length;
        PixImage image = new PixImage(width, height);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setPixel(x, y, (short) pixels[x][y], (short) pixels[x][y],
                        (short) pixels[x][y]);
            }
        }

        return image;
    }

    /**
     * setAndCheckRLE() sets the given coordinate in the given run-length
     * encoding to the given value and then checks whether the resulting
     * run-length encoding is correct.
     *
     * @param rle the run-length encoding to modify.
     * @param x the x-coordinate to set.
     * @param y the y-coordinate to set.
     * @param intensity the grayscale intensity to assign to pixel (x, y).
     */
    private static void setAndCheckRLE(RunLengthEncoding rle,
                                       int x, int y, int intensity) {
        rle.setPixel(x, y,
                (short) intensity, (short) intensity, (short) intensity);
        rle.check();
    }

    /**
     * main() runs a series of tests of the run-length encoding code.
     */
    public static void main(String[] args) {
        // Be forwarned that when you write arrays directly in Java as below,
        // each "row" of text is a column of your image--the numbers get
        // transposed.
        PixImage image1 = array2PixImage(new int[][]{{0, 3, 6},
                {1, 4, 7},
                {2, 5, 8}});

        System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                "on a 3x3 image.  Input image:");
        System.out.print(image1);
        RunLengthEncoding rle1 = new RunLengthEncoding(image1);
        rle1.check();
        System.out.println("Testing getWidth/getHeight on a 3x3 encoding.");
        doTest(rle1.getWidth() == 3 && rle1.getHeight() == 3,
                "RLE1 has wrong dimensions");

        System.out.println("Testing toPixImage() on a 3x3 encoding.");
        doTest(image1.equals(rle1.toPixImage()),
                "image1 -> RLE1 -> image does not reconstruct the original image");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 0, 0, 42);
        image1.setPixel(0, 0, (short) 42, (short) 42, (short) 42);
        doTest(rle1.toPixImage().equals(image1),
           /*
                       array2PixImage(new int[][] { { 42, 3, 6 },
                                                    { 1, 4, 7 },
                                                    { 2, 5, 8 } })),
           */
                "Setting RLE1[0][0] = 42 fails.");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 1, 0, 42);
        image1.setPixel(1, 0, (short) 42, (short) 42, (short) 42);
        doTest(rle1.toPixImage().equals(image1),
                "Setting RLE1[1][0] = 42 fails.");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 0, 1, 2);
        image1.setPixel(0, 1, (short) 2, (short) 2, (short) 2);
        doTest(rle1.toPixImage().equals(image1),
                "Setting RLE1[0][1] = 2 fails.");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 0, 0, 0);
        image1.setPixel(0, 0, (short) 0, (short) 0, (short) 0);
        doTest(rle1.toPixImage().equals(image1),
                "Setting RLE1[0][0] = 0 fails.");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 2, 2, 7);
        image1.setPixel(2, 2, (short) 7, (short) 7, (short) 7);
        doTest(rle1.toPixImage().equals(image1),
                "Setting RLE1[2][2] = 7 fails.");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 2, 2, 42);
        image1.setPixel(2, 2, (short) 42, (short) 42, (short) 42);
        doTest(rle1.toPixImage().equals(image1),
                "Setting RLE1[2][2] = 42 fails.");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 1, 2, 42);
        image1.setPixel(1, 2, (short) 42, (short) 42, (short) 42);
        doTest(rle1.toPixImage().equals(image1),
                "Setting RLE1[1][2] = 42 fails.");


        PixImage image2 = array2PixImage(new int[][]{{2, 3, 5},
                {2, 4, 5},
                {3, 4, 6}});

        System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                "on another 3x3 image.  Input image:");
        System.out.print(image2);
        RunLengthEncoding rle2 = new RunLengthEncoding(image2);
        rle2.check();
        System.out.println("Testing getWidth/getHeight on a 3x3 encoding.");
        doTest(rle2.getWidth() == 3 && rle2.getHeight() == 3,
                "RLE2 has wrong dimensions");

        System.out.println("Testing toPixImage() on a 3x3 encoding.");
        doTest(rle2.toPixImage().equals(image2),
                "image2 -> RLE2 -> image does not reconstruct the original image");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle2, 0, 1, 2);
        image2.setPixel(0, 1, (short) 2, (short) 2, (short) 2);
        doTest(rle2.toPixImage().equals(image2),
                "Setting RLE2[0][1] = 2 fails.");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle2, 2, 0, 2);
        image2.setPixel(2, 0, (short) 2, (short) 2, (short) 2);
        doTest(rle2.toPixImage().equals(image2),
                "Setting RLE2[2][0] = 2 fails.");


        PixImage image3 = array2PixImage(new int[][]{{0, 5},
                {1, 6},
                {2, 7},
                {3, 8},
                {4, 9}});

        System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                "on a 5x2 image.  Input image:");
        System.out.print(image3);
        RunLengthEncoding rle3 = new RunLengthEncoding(image3);
        rle3.check();
        System.out.println("Testing getWidth/getHeight on a 5x2 encoding.");
        doTest(rle3.getWidth() == 5 && rle3.getHeight() == 2,
                "RLE3 has wrong dimensions");

        System.out.println("Testing toPixImage() on a 5x2 encoding.");
        doTest(rle3.toPixImage().equals(image3),
                "image3 -> RLE3 -> image does not reconstruct the original image");

        System.out.println("Testing setPixel() on a 5x2 encoding.");
        setAndCheckRLE(rle3, 4, 0, 6);
        image3.setPixel(4, 0, (short) 6, (short) 6, (short) 6);
        doTest(rle3.toPixImage().equals(image3),
                "Setting RLE3[4][0] = 6 fails.");

        System.out.println("Testing setPixel() on a 5x2 encoding.");
        setAndCheckRLE(rle3, 0, 1, 6);
        image3.setPixel(0, 1, (short) 6, (short) 6, (short) 6);
        doTest(rle3.toPixImage().equals(image3),
                "Setting RLE3[0][1] = 6 fails.");

        System.out.println("Testing setPixel() on a 5x2 encoding.");
        setAndCheckRLE(rle3, 0, 0, 1);
        image3.setPixel(0, 0, (short) 1, (short) 1, (short) 1);
        doTest(rle3.toPixImage().equals(image3),
                "Setting RLE3[0][0] = 1 fails.");


        PixImage image4 = array2PixImage(new int[][]{{0, 3},
                {1, 4},
                {2, 5}});

        System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                "on a 3x2 image.  Input image:");
        System.out.print(image4);
        RunLengthEncoding rle4 = new RunLengthEncoding(image4);
        rle4.check();
        System.out.println("Testing getWidth/getHeight on a 3x2 encoding.");
        doTest(rle4.getWidth() == 3 && rle4.getHeight() == 2,
                "RLE4 has wrong dimensions");

        System.out.println("Testing toPixImage() on a 3x2 encoding.");
        doTest(rle4.toPixImage().equals(image4),
                "image4 -> RLE4 -> image does not reconstruct the original image");

        System.out.println("Testing setPixel() on a 3x2 encoding.");
        setAndCheckRLE(rle4, 2, 0, 0);
        image4.setPixel(2, 0, (short) 0, (short) 0, (short) 0);
        doTest(rle4.toPixImage().equals(image4),
                "Setting RLE4[2][0] = 0 fails.");

        System.out.println("Testing setPixel() on a 3x2 encoding.");
        setAndCheckRLE(rle4, 1, 0, 0);
        image4.setPixel(1, 0, (short) 0, (short) 0, (short) 0);
        doTest(rle4.toPixImage().equals(image4),
                "Setting RLE4[1][0] = 0 fails.");

        System.out.println("Testing setPixel() on a 3x2 encoding.");
        setAndCheckRLE(rle4, 1, 0, 1);
        image4.setPixel(1, 0, (short) 1, (short) 1, (short) 1);
        doTest(rle4.toPixImage().equals(image4),
                "Setting RLE4[1][0] = 1 fails.");
    }
}
