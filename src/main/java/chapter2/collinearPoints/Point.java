package chapter2.collinearPoints; /******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *  
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import edu.princeton.cs.introcs.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
	if (that == null) throw new NullPointerException();

	if (this.areEquals(that)) {
	    return Double.NEGATIVE_INFINITY;
	} else if (this.x == that.x) {
	    return Double.POSITIVE_INFINITY;
	} else if (this.y == that.y) {
	    return 0.0;
	}else {
	    double numerator = that.y - this.y;
	    double denominator = that.x - this.x;
	    return numerator/denominator;
	}
    }

    private boolean areEquals(Point that) {
	if (this.x == that.x && this.y == that.y) return true;
	else return false;
    }

	

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
	if (this.y < that.y) return -1;
	else if (this.y > that.y) return 1;
	else if (this.x < that.x) return -1;
	else if (this.x > that.x) return 1;
	else return 0;
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return ((Point point1, Point point2) -> {
		double comparisson = this.slopeTo(point1) - this.slopeTo(point2);
		if (comparisson < 0) return -1;
		else if (comparisson > 0) return 1;
		else return 0;
	    });
    }


    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
         Point point1 = new Point(0, 0);
	 Point point2 = new Point(3, 3);
	 Point point3 = new Point(7, 7);
	 Point point4 = new Point(30697, 24705);
	 Point point5 = new Point(30697, 5387);

	 Comparator<Point> slopePoint1 = point1.slopeOrder();

	// System.out.println(String.format("%s < %s ?: %d", point1, point2, point1.compareTo(point2)));
	// System.out.println(String.format("%s < %s ?: %d", point1, point1, point1.compareTo(point1)));
	// System.out.println(String.format("%s < %s ?: %d", point1, point2, point1.compareTo(point2)));
	// System.out.println(String.format("%s < %s ?: %d", point3, point2, point3.compareTo(point2)));

	// System.out.println(String.format("slope point1 to point2 %f", point1.slopeTo(point2)));
	// System.out.println(String.format("slope point1 to point3 %f", point1.slopeTo(point3)));
	// System.out.println(String.format("slope point4 to point5 %f", point4.slopeTo(point5)));

	 System.out.println(String.format("slope point4 to point5 %f", point4.slopeTo(point5)));

	System.out.println(String.format("Slope point2 vs point3 against point1?: %d", slopePoint1.compare(point2, point3)));
    }
}
