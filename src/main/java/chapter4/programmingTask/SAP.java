package chapter4.programmingTask;

//import java.util.HashMap;

import edu.princeton.cs.algorithms.BreadthFirstDirectedPaths;
import edu.princeton.cs.algorithms.Digraph;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

// Shortest Ancestral Path of a digraph
public class SAP {
    private final Digraph d;

   // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
		notNull(G);
		d = G;
    }

   // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
		notNull(v, w);
		inRange(v, w);

		Pair res = calculateSAP(Pair.of(v, w));

		return res.y();
    }

   // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
		notNull(v, w);
		inRange(v, w);

		Pair res = calculateSAP(Pair.of(v, w));

		return res.x();
    }

   // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> setV, Iterable<Integer> setW) {
		notNull(setV, setW);

		int minLength = d.E() + 1;
		for (int v : setV) {
			for (int w : setW) {
			notNull(v, w);
			int newLength = length(v, w);
			if (newLength < minLength) {
				minLength = newLength;
			}
			}
		}

		if (minLength == d.E() + 1) {
			return -1;
		} else {
			return minLength;
		}
    }

   // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> setV, Iterable<Integer> setW) {
		notNull(setV, setW);

		int minLength = d.E() + 1;
		int minAncestor = -1;
		for (int v : setV) {
			for (int w : setW) {
				notNull(v, w);
				int newLength = length(v, w);
				if (newLength < minLength) {
					minAncestor = ancestor(v, w);
					minLength = newLength;
				}
			}
		}

		if (minLength == d.E() + 1) {
			return -1;
		} else {
			return minAncestor;
		}
    }

    // private void precalculate(int v, int w) {
    // 	Pair pair = v < w ? Pair.of(v, w) : Pair.of(w, v);

    // 	if (!ancestorsAndLengths.containsKey(pair.x())) {
    // 	    HashMap<Integer, Pair> map = new HashMap<>();
    // 	    ancestorsAndLengths.put(pair.x(), map);
    // 	}

    // 	HashMap<Integer, Pair> map = ancestorsAndLengths.get(pair.x());
    // 	if (!map.containsKey(pair.y())) {
    // 	    Pair ancestorAndLength = calculateSAP(pair);
    // 	    map.put(pair.y(), ancestorAndLength);
    // 	}
    // }

    private void inRange(int v, int w) {
		if (!(0 <= v && v < d.V()) || !(0 <= w && w < d.V())) {
			throw new IllegalArgumentException();
		}
    }

    private Pair calculateSAP(Pair pair) {
		int v = pair.x();
		int w = pair.y();

		BreadthFirstDirectedPaths fromV = new BreadthFirstDirectedPaths(d, v); //paths.get(v);
		BreadthFirstDirectedPaths fromW = new BreadthFirstDirectedPaths(d, w); //paths.get(w);

		int minDistance = d.E() + 1;
		int commonAncestor = -1;
		for (int x = 0; x < d.V(); x++) {
			if (fromV.hasPathTo(x) && fromW.hasPathTo(x)) {
			// This is an ancestral path. Now we have to see if it is the shortest!
			int distanceAncestralPath = fromV.distTo(x) + fromW.distTo(x);
			if (distanceAncestralPath < minDistance) {
				minDistance = distanceAncestralPath;
				commonAncestor = x;
			}
			}
		}

		if (minDistance == d.E() + 1) {
			return Pair.of(-1, -1);
		} else {
			return Pair.of(commonAncestor, minDistance);
		}
    }

    private void notNull(Object... parameters) {
		for (Object object: parameters) {
			if (object == null) throw new IllegalArgumentException();
		}
    }

    private static class Pair {
		private final int x;
		private final int y;

		public static Pair of(int v, int w) {
			return new Pair(v, w);
		}

		private Pair(int v, int w) {
			this.x = v;
			this.y = w;
		}

		public int x() {
			return x;
		}

		public int y() {
			return y;
		}
    }

   // do unit testing of this class
    public static void main(String[] args) {
		In in = new In(args[0]);
		Digraph G = new Digraph(in);
		SAP sap = new SAP(G);
		while (!StdIn.isEmpty()) {
			int v = StdIn.readInt();
			int w = StdIn.readInt();
			int length   = sap.length(v, w);
			int ancestor = sap.ancestor(v, w);
			StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
		}
    }
}
