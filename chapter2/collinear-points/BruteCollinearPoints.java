import java.util.Arrays;

public class BruteCollinearPoints {
    private LineSegment[] segments;
    private int countSegments;

    public static void main(String[] args) {
	BruteCollinearPoints brute = new BruteCollinearPoints(new Point[]{
		new Point(1, 1),
		new Point(2, 2),
		new Point(3, 3),
		new Point(3, 1),
		new Point(-1, 3),
		new Point(5, 1),
		new Point(4, 4)
	    });
	System.out.println(brute.numberOfSegments());
	print(brute.segments());
    }

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
	validateInput(points);

	//print(points);
	countSegments = 0;
	segments = new LineSegment[points.length];
	for (int j = 0; j < points.length; j++) {
	    for (int i = j + 1; i < points.length; i++) {
		for (int k = i + 1; k < points.length; k++) {
		    for (int h = k + 1; h < points.length; h++) {
			Point point1 = points[j];
			Point point2 = points[i];
			Point point3 = points[k];
			Point point4 = points[h];

			double slope1 = point1.slopeTo(point2);
			double slope2 = point1.slopeTo(point3);
			double slope3 = point1.slopeTo(point4);

			if (slope1 == slope2 && slope2 == slope3) {
			    Point[] segment = new Point[4];
			    segment[0] = point1;
			    segment[1] = point2;
			    segment[2] = point3;
			    segment[3] = point4;
			    Arrays.sort(segment);
			    
			    addSegment(new LineSegment(segment[0], segment[3]));
			}
		    }
		}
	    }
	}
    }

    private void addSegment(LineSegment segment) {
	if (segments.length == countSegments) resize(segments.length * 2);

	segments[countSegments++] = segment;
    }

    private void resize(int newSize) {
	LineSegment[] res = new LineSegment[newSize];
	for (int j = 0; j < countSegments; j++) res[j] = segments[j];
	segments = res;
    }

    private static void print(Object[] array) {
	StringBuilder str = new StringBuilder();
	for (Object val : array) {
	    str.append(val);
	    str.append(" ");
	}
	System.out.println(str.toString());
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

    // the number of line segments 
    public int numberOfSegments() {
	return countSegments;
    }

    // the line segments
    public LineSegment[] segments() {
	LineSegment[] res = new LineSegment[countSegments];
	for (int j = 0; j < countSegments; j++) {
	    res[j] = segments[j];
	}
	return res;
    }
}
