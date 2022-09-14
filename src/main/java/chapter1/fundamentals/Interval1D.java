package chapter1.fundamentals;

public class Interval1D {
    public final double start;
    public final double end;
    
    public Interval1D(double start, double end) {
	this.start = start;
	this.end = end;
    }

    public boolean intersect(Interval1D other) {
	if (this.start <= other.start) {
	    return this.start <= other.end &&
		other.start <= this.end;
	} else {
	    return other.start <= this.end &&
		this.start <= other.end;
	}
    }

    public String toString() {
	return String.format("[%f, %f]", start, end);
    }
}
