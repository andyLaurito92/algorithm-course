import java.util.Stack;
import java.util.ArrayList;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.Font;
import java.awt.Color;

public class KdTree {
    private Node root;
    private int size;
    
    public KdTree() {
	root = null;
	size = 0;
    }

    // is the set empty? 
    public boolean isEmpty() {
	return root == null;
    }

    // number of points in the set 
    public int size() {
	return size;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
	if (p == null) throw new IllegalArgumentException();

	if (contains(p)) return;
	root = insert(root, p, true);
	size++;
    }

    // does the set contain point p? 
    public boolean contains(Point2D p) {
	if (p == null) throw new IllegalArgumentException();

	Node current = root;
	while (current != null) {
	    if (current.point().equals(p)) return true;

	    if (current.isVertical()) {
		if (p.x() < current.x()) current = current.left;
		else current = current.right;
	    } else {
		if (p.y() < current.y()) current = current.left;
		else current = current.right;
	    }
	}
	return false;
    }

    // draw all points to standard draw 
    public void draw() {
	Stack<Node> points = new Stack<>();
	Node current = root;
	current.draw(null);
	points.push(current);
	while (!points.empty()) {
	    current = points.pop();
	    if (current.left != null) {
		points.push(current.left);
		current.left.draw(current);
	    }
	    if (current.right != null) {
		points.push(current.right);
		current.right.draw(current);
	    }
	}
    }

    // all points that are inside the rectangle (or on the boundary) 
    public Iterable<Point2D> range(RectHV rect) {
	if (rect == null) throw new IllegalArgumentException();

	rect.draw();
	ArrayList<Point2D> res = new ArrayList<>();
	range(root, rect, res);
	return res;
    }

    // a nearest neighbor in the set to point p; null if the set is empty 
    public Point2D nearest(Point2D p) {
	if (p == null) throw new IllegalArgumentException();
	if (isEmpty()) return null;

	drawPoint(p, StdDraw.BLUE);
	return nearest(root, root, p, Double.POSITIVE_INFINITY).point();
    }

    private Node nearest(Node actual, Node currentNearest, Point2D p, double distance) {
	if (actual == null) return currentNearest;
	if (actual.point().equals(p)) return actual;

	double newDistance = actual.point().distanceSquaredTo(p);
	if (newDistance < distance) {
	    currentNearest = actual;
	    distance = newDistance;

	    if (actual.isVertical()) {
		if (p.x() < actual.x()) {
		    currentNearest = nearest(actual.left, currentNearest, p, distance);
		    currentNearest = nearest(actual.right, currentNearest, p, currentNearest.distanceSquaredTo(p));
		} else {
		    currentNearest = nearest(actual.right, currentNearest, p, distance);
		    currentNearest = nearest(actual.left, currentNearest, p, currentNearest.distanceSquaredTo(p));
		}
	    } else {
		if (p.y() < actual.y()) {
		    currentNearest = nearest(actual.left, currentNearest, p, distance);
		    currentNearest = nearest(actual.right, currentNearest, p, currentNearest.distanceSquaredTo(p));
		} else {
		    currentNearest = nearest(actual.right, currentNearest, p, distance);
		    currentNearest = nearest(actual.left, currentNearest, p, currentNearest.distanceSquaredTo(p));
		}
	    }
	}
	return currentNearest;
    }

    private ArrayList<Point2D> range(Node current, RectHV rect, ArrayList<Point2D> res) {
	if (current == null) return res;
	if (rect.contains(current.point())) res.add(current.point());

	if (current.isVertical()) {
	    if (rect.xmax() < current.x()) {
		res = range(current.left, rect, res);
	    } else if (rect.xmin() > current.x()) {
		res = range(current.right, rect, res);
	    } else {
		res = range(current.left, rect, res);
		res = range(current.right, rect, res);
	    }
	} else {
	    if (rect.ymax() < current.y()) {
		res = range(current.left, rect, res);
	    } else if (rect.ymin() > current.y()) {
		res = range(current.right, rect, res);
	    } else {
		res = range(current.left, rect, res);
		res = range(current.right, rect, res);
	    }
	}
	return res;
    }

    private Node insert(Node current, Point2D point, boolean isVertical) {
	if (current == null) return new Node(point, isVertical);

	if (current.isVertical()) {
	    if (point.x() < current.x()) current.left = insert(current.left, point, false);
	    else current.right = insert(current.right, point, false);
	} else {
	    if (point.y() < current.y()) current.left = insert(current.left, point, true);
	    else current.right = insert(current.right, point, true);
	}

	return current;
    }

    private static class Node {
	Point2D point;
	boolean isVertical;
	public Node left;
	public Node right;

	public Node(Point2D aPoint, boolean vertical) {
	    this.point = aPoint;
	    this.isVertical = vertical;
	    this.left = null;
	    this.right = null;

	    Font font = new Font("Arial", Font.BOLD, 10);
	    StdDraw.setFont(font);
	}

	public Point2D point() {
	    return point;
	}

	public boolean isVertical() {
	    return isVertical;
	}

	public double x() {
	    return point.x();
	}

	public double y() {
	    return point.y();
	}

	public double distanceSquaredTo(Point2D another) {
	    return point.distanceSquaredTo(another);
	}

	public void draw(Node prev) {
	    drawPoint(point, StdDraw.BLACK);
	    double x = point.x();
	    double y = point.y();
	    StdDraw.setPenRadius(0.001);
	    //print(String.format("Drawing: %s %s", point, prev));
	    if (isVertical()) {
		Point2D prevPoint = prev != null ? prev.point() : new Point2D(0, 0);
		StdDraw.setPenColor(StdDraw.RED);
		if (prevPoint.y() < y) {
		    StdDraw.line(x, prevPoint.y(), x, y);
		    StdDraw.line(x, y, x, 1);
		} else {
		    StdDraw.line(x, 0, x, y);
		    StdDraw.line(x, y, x, prevPoint.y());
		}
	    } else {
		Point2D prevPoint = prev.point();
		StdDraw.setPenColor(StdDraw.BLUE);
		if (prevPoint.x() < x) {
		    StdDraw.line(prevPoint.x(), y, x, y);
		    StdDraw.line(x, y, 1, y);
		} else {
		    StdDraw.line(0, y, x, y);
		    StdDraw.line(x, y, prevPoint.x(), y);
		}
	    }
	}
    }

    // unit testing of the methods (optional) 
    public static void main(String[] args) {
	KdTree tree = new KdTree();
	print(tree.isEmpty());
	print(tree.size());

	tree.insert(new Point2D(0.7, 0.2));
	tree.insert(new Point2D(0.5, 0.4));
	tree.insert(new Point2D(0.2, 0.3));
	tree.insert(new Point2D(0.4, 0.7));
	tree.insert(new Point2D(0.9, 0.6));

	tree.draw();

	print(tree.isEmpty());
	print(tree.size());

	print("Contains");
	print(tree.contains(new Point2D(0.5, 0.1)));
	print(tree.contains(new Point2D(0.7, 0.2)));
	print(tree.contains(new Point2D(0.1, 0.1)));

	print("Nearest");
	print(tree.nearest(new Point2D(0, 0.4)));
	print(tree.nearest(new Point2D(0.4, 0.4)));
	print(tree.nearest(new Point2D(0.25, 0.25)));

	print("Points in rectangle");
	Iterable<Point2D> pointsWithinRange = tree.range(new RectHV(0, 0, 0.5, 0.5));
	for (Point2D pointInRectangle : pointsWithinRange) {
	    print(pointInRectangle);
	}
    }

    private static void print(Object obj) {
	System.out.println(obj);
    }

    private static void drawPoint(Point2D point, Color color) {
	StdDraw.setPenColor(color);
	StdDraw.setPenRadius(0.01);

	double x = point.x();
	double y = point.y();
	StdDraw.point(x, y);
	StdDraw.text(x, y - 0.02, String.format("(%.1f, %.1f)", x, y));
    }
}
