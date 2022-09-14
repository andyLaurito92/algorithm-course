package chapter1.fundamentals;

import java.lang.Math;

public class Point2D {
    public final double x;
    public final double y;

    public Point2D(double x, double y) {
	this.x = x;
	this.y = y;
    }

    public double distanceTo(Point2D other) {
	return Math.sqrt(Math.pow(other.x - this.x, 2) + Math.pow(other.y - this.y, 2));
    }

    public String toString() {
	return String.format("x: %f, y:%f", this.x, this.y);
    }
}
