import java.lang.IllegalArgumentException;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class DataAbstractionExercises {
    public static void main(String[] args) {
	// if (args.length == 0) {
	//     throw new IllegalArgumentException("Expected number of random points to be generated");
	// }
	// intersects(Integer.parseInt(args[0]));

	System.out.println(String.format("Are rotation: %b", circularRotation("ACTGACG", "TGACGAC")));
	
    }

    public static void intersects(int N) {
	Scanner scanner = new Scanner(System.in);

	int count = 0;
	Interval1D[] intervals = new Interval1D[N];
	while (count < N) {
	    String line = scanner.nextLine();
	    Double[] interval = Arrays.stream(line.split(" ")).map(Double::parseDouble).toArray(Double[]::new);
	    intervals[count] = new Interval1D(interval[0], interval[1]);
	    System.out.println(intervals[count]);
	    count++;
	}

	for (int j = 0; j < intervals.length; j++) {
	    for (int i = j; i < intervals.length; i++) {
		if (i == j) continue;

		if (intervals[i].intersect(intervals[j])) {
		    System.out.println(String.format("%s intersects with %s", intervals[i], intervals[j]));
		}
	    }
	}
    }

    public static boolean circularRotation(String str1, String str2) {
	if (str1.length() != str2.length()) {
	    return false;
	}

	String extendedString = str2 + str2; // This string has length 2 * str1.length(). The string I'm looking for has to be in this one
	int init = 0;
	while (init < str1.length()) {
	    if (extendedString.indexOf(str1, init) != -1) {
		return true;
	    }
	    init ++;
	}
	
	return false;
    }


    public static void distances(String[] args) {
		int pointsToGenerate = Integer.parseInt(args[0]);
	Point2D[] points = new Point2D[pointsToGenerate];

	generatePoints(points);

	// We calculate the distance between each pair of points
	double[][] distances = new double[pointsToGenerate][pointsToGenerate];
	double minDistance = calculateDistances(distances, points);

	System.out.println(String.format("Min distance between points is %f", minDistance));
    }

    public static void generatePoints(Point2D[] points) {
	Random random = new Random();
	for (int j = 0; j < points.length; j++) {
	    double x = random.nextDouble();
	    double y = random.nextDouble();
	    points[j] = new Point2D(x, y);
	    System.out.println(String.format("%s \n", points[j]));
	}
    }


    // Calculates the distance between all points from the array. Put the distances into
    // a matrix and return the minimun distance
    public static double calculateDistances(double[][] distances, Point2D[] points) {
	double min = Double.POSITIVE_INFINITY;
	for (int i = 0; i < distances.length; i++) {
	    // Instead of going throught N*N, take advantage of simmetry between distances
	    // and just go through half of the matrix
	    for (int j = i; j > -1; j--) {
		// Do not calculate the diagonal since its the distance of the point to itself 
		if (i != j) {
		    Point2D currentPoint = points[i] ;
		    double distance = currentPoint.distanceTo(points[j]);
		    distances[i][j] = distance;
		    min = distance < min ? distance : min;
		}
	    }
	}
	return min;
    }

}
