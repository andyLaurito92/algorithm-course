import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.Scanner;
import java.io.File;

public class SampleClient {
    public static void main(String[] args) {
	// read the n points from a file
	File file = new File(args[0]);
	Scanner scanner;
	try {
	    scanner = new Scanner(file);
	} catch (Exception e) {
	    System.out.println("Error while reading the file");
	    System.out.println(e);
	    return;
	}
	int n = scanner.nextInt();
	Point[] points = new Point[n];
	for (int i = 0; i < n; i++) {
	    int x = scanner.nextInt();
	    int y = scanner.nextInt();
	    points[i] = new Point(x, y);
	}
	Point point1 = new Point(1234, 5678);
	Point point2 = new Point(32000, 10000);
	Point point3 = new Point(19000, 10000);
	// System.out.println(String.format("%s %s slope is %f", point1, point2, point1.slopeTo(point2)));
	// System.out.println(String.format("%s %s slope is %f", point1, point3, point1.slopeTo(point3)));
	// System.out.println(String.format("%s %s slope is %f", point3, point2, point3.slopeTo(point2)));


	// draw the points
	StdDraw.enableDoubleBuffering();
	StdDraw.setXscale(0, 32768);
	StdDraw.setYscale(0, 32768);
	for (Point p : points) {
	    p.draw();
	}
	StdDraw.show();

	// print and draw the line segments
	// BruteCollinearPoints collinear = new BruteCollinearPoints(points);
	FastCollinearPoints collinear = new FastCollinearPoints(points);
	System.out.println(collinear.numberOfSegments());
	for (LineSegment segment : collinear.segments()) {
	    StdOut.println(segment);
	    segment.draw();
	}
	StdDraw.show();
    }

    public static void print(Object[] array) {
	StringBuilder str = new StringBuilder();
	for (Object val : array) {
	    str.append(val);
	    str.append(" ");
	}
	System.out.println(str.toString());
    }
}
