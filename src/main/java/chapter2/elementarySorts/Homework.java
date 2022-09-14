package chapter2.elementarySorts;

public class Homework {
    /**
     ** Intersection of two sets. Given two arrays a[]\mathtt{a[]}a[] and b[]\mathtt{b[]}b[], each containing nnn distinct 2D 
     ** points in the plane, design a subquadratic algorithm to count the number of points that are contained both in array a[]\mathtt{a[]}a[] 
     * and array b[]\mathtt{b[]}b[].
     **/

    public static void main(String[] args) {
	System.out.println(String.format("Num: %s", intersection(new Point[]{new Point(2, 3), new Point(-3, -4)},
								 new Point[]{new Point(1, 3), new Point(2, 3)})));

	int[] dutchFlag = new int[]{2, 2, 1, 1, 0, 2, 0, 1, 1, 0, 0, 2};
	dutchNationalFlag(dutchFlag);
	System.out.println(print(dutchFlag));
    }

    /** Pregunta 3
     ** Dutch national flag. Given an array of nnn buckets, each containing a red, white, or blue pebble, sort them by color.
    **/
    static int RED = 0;
    static int WHITE = 1;
    static int BLUE = 2;
    public static void dutchNationalFlag(int[] buckets) {
	int reds = 0;
	int whites = 0;
	int bluePebbles = 0;

	for (int j = 0; j < buckets.length; j++) {
	    if (buckets[j] == RED) reds ++;
	    if (buckets[j] == WHITE) whites++;
	    if (buckets[j] == BLUE) bluePebbles++;
	}

	int j = 0;
	j =  fillWith(buckets, reds, RED, j);
	j = fillWith(buckets, whites, WHITE, j);
	fillWith(buckets, bluePebbles, BLUE, j);
    }

    static String print(int[] array) {
	StringBuilder str = new StringBuilder();
	for (int j = 0; j < array.length; j++) {
	    str.append(array[j]);
	    str.append(" ");
	}
	return str.toString();
    }

    static int fillWith(int[] buckets, int itemsWithColor, int COLOR, int init) {
	while (itemsWithColor > 0) {
	    buckets[init++] = COLOR;
	    itemsWithColor --;
	}
	return init;
    }


    public static int intersection(Point[] a, Point[] b) {
	Insertion.sort(a);
	Insertion.sort(b);

	int equals = 0;
	int idxA = 0;
	int idxB = 0;

	while (idxA < a.length && idxB < b.length) {
	    if (Insertion.less(a[idxA], b[idxB])) idxA++;
	    else if (Insertion.less(b[idxB], a[idxA])) idxB++;
	    else {
		System.out.println(String.format("Found %s in both", a[idxA]));
		equals++;
		idxA++;
		idxB++;
	    }
	}
	
	return equals;
    }

    static class Insertion {
	static void sort(Point[] points) {
	    for (int j = 1; j < points.length; j++) {
		Point newPoint = points[j];
		int i = j;
		while (i > -1 && less(newPoint, points[i])) {
		    swap(points, i, i -1);
		    i--;
		}
		points[i] = newPoint;
	    }
	}

	static void swap(Point[] points, int i, int j) {
	    Point temp = points[i];
	    points[i] = points[j];
	    points[j] = temp;
	}
	
	static boolean less(Point a, Point b) {
	    return a.compareTo(b) < 0;
	}
    }

    static class Point implements Comparable<Point> {
	public Integer x;
	public Integer y;

	Point(Integer x, Integer y) {
	    this.x = x;
	    this.y = y;
	}

	Double distanceToCenter() {
	    return Math.sqrt(x*x + y*y);
	}

	// We compare points by it's euclidean distance to the center
	public int compareTo(Point another) {
	    return this.distanceToCenter().compareTo(another.distanceToCenter());
	}

	boolean equalsTo(Point another) {
	    return this.x == another.x && this.y == another.y;
	}

	public String toString() {
	    return String.format("(%s, %s)", this.x, this.y);
	}
    }
}
