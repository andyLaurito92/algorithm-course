package chapter2.collinearPoints;

import java.util.Comparator;
import java.util.Arrays;
//import edu.princeton.cs.algs4.StdRandom;

public class FastCollinearPoints {
    private LineSegment[] segments;
    // private Double[] slopesSeen;
    private int numSegments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
		//print(points);
		validateInput(points);

		numSegments = 0;
		segments = new LineSegment[points.length];
		// slopesSeen = new Double[points.length];

		Point[] copyPoints = copy(points);
		for (int j = 0; j < points.length; j++) {
			Point p = points[j];
			// System.out.println(String.format("Going to sort by %s", p));
			Arrays.sort(copyPoints, p.slopeOrder());
			// Quick.sort(points, origin.slopeOrder());
			findCollinearsOf(p, copyPoints);
		}
		}

		private Point[] copy(Point[] array) {
		Point[] res = new Point[array.length];
		for (int j = 0; j < array.length; j++) res[j] = array[j];
		return res;
    }

    private void findCollinearsOf(Point p, Point[] points) {
		// System.out.println(String.format("Finding collinears of %s", p));
		// print(points);
		Point[] remaining = new Point[points.length];

		int remainingPoints = 0;
		int current = 0;
		int start = current;
		double currentSlope = p.slopeTo(points[current++]);
		int numPoints = 2;  // p && points[current] always belong to segment
		while (current < points.length) {
			// System.out.println(String.format("Current slope %f", currentSlope));
			while (current < points.length && p.slopeTo(points[current]) == currentSlope) {
				current++;
				numPoints++;
			}

			if (numPoints >= 4) {
				// System.out.println(String.format("current %s start %s", current, start));
				Point[] segment = new Point[current - start + 1]; // We add p
				int idxSegment = 0;
				for (int i = start; i < current; i++) {
					segment[idxSegment++] = points[i];
				}
				segment[idxSegment] = p;
				Arrays.sort(segment);
				// System.out.println("Segment is");
				// print(segment);
				// Only add the segment when we found the initial point of it
				 // System.out.println(String.format("p %s equals to segment[0] %s, res: %b", p, segment[0], p.equals(segment[0])));
				if (p.equals(segment[0])) {
					double slope = points[start].slopeTo(points[current-1]);
					LineSegment line = new LineSegment(p, segment[segment.length-1]);
					addSegment(line);
				}
			}

			if (current == points.length) break;
			// System.out.println(String.format("Started: %s, Stopped at %s; numPoints: %d", points[start], current == points.length? current : points[current], numPoints));

			start = current;
			currentSlope = p.slopeTo(points[current]);
			numPoints = 2;
			current++;
		}
    }

    private void addSegment(LineSegment segment) {
		if (segments.length == numSegments) resize(segments.length * 2);

		segments[numSegments++] = segment;
    }

    private void resize(int newSize) {
		LineSegment[] res = new LineSegment[newSize];
		for (int j = 0; j < numSegments; j++) res[j] = segments[j];
		segments = res;
    }

    // private boolean firstTimeSeen(double slope) {
    // 	for (int j = 0; j < numSegments; j++) {
    // 	    double seen = slopesSeen[j];
    // 	    if (seen == slope) {
    // 		return false;
    // 	    }
    // 	}

    // 	return true;
    // }

    // the number of line segments
    public int numberOfSegments() {
		return numSegments;
    }

    // the line segments
    public LineSegment[] segments() {
		LineSegment[] res = new LineSegment[numSegments];
		for (int j = 0; j < numSegments; j++) {
			res[j] = segments[j];
		}
		return res;
    }
    
    private void validateInput(Point[] points) {
		if (points == null) throw new IllegalArgumentException();
		else {
			for (int j = 0; j < points.length; j++) {
			if (points[j] == null) throw new IllegalArgumentException();
			for (int i = j + 1; i < points.length; i++) {
				if (points[j].equals(points[i])) throw new IllegalArgumentException();
			}
			}
		}
    }

    public static void main(String[] args) {
		FastCollinearPoints brute = new FastCollinearPoints(new Point[]{
			new Point(1, 1),
			new Point(2, 2),
			new Point(3, 3),
			new Point(3, 1),
			new Point(-1, 3),
			new Point(5, 1),
			new Point(4, 4)
		});
		 //System.out.println(brute.numberOfSegments());
		// print(brute.segments());
    }

    private static void print(Object[] a) {
		StringBuilder str = new StringBuilder();
		for (int j = 0; j < a.length; j++) {
			str.append(a[j]);
			str.append(" ");
		}
		System.out.println(str.toString());
    }

   // private static class Quick {
   // 	public static void sort(Point[] array, Comparator<Point> comparator) {
   // 	    shuffle(array);
   // 	    sort(array, 0, array.length - 1, comparator);
   // 	}

   // 	private static void sort(Point[] array, int low, int high, Comparator<Point> comparator) {
   // 	    if (high <= low) return;
	    
   // 	    Point pivot = array[low];
   // 	    int lt = low;
   // 	    int gt = high;
   // 	    int i = low + 1;
   // 	    while (i <= gt) {
   // 		int comparisson = comparator.compare(array[i], pivot);
   // 		if (comparisson < 0) exchange(array, lt++, i++);
   // 		else if (comparisson > 0) exchange(array, gt--, i);
   // 		else i++;
   // 	    }
   // 	    sort(array, low, lt - 1, comparator);
   // 	    sort(array, gt + 1, high, comparator);
   // 	}

   // 	public static void shuffle(Point[] array) {
   // 	    for (int j = 0; j < array.length; j++) {
   // 		int next = j + StdRandom.uniform(array.length - j);
   // 		exchange(array, j, next);
   // 	    }
   // 	}

   // 	private static void exchange(Point[] array, int i, int j) {
   // 	    Point temp = array[i];
   // 	    array[i] = array[j];
   // 	    array[j] = temp;
   // 	}
   //  }
}
