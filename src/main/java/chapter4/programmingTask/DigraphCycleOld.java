package chapter4.programmingTask;

import edu.princeton.cs.algorithms.Digraph;
import edu.princeton.cs.introcs.In;

import java.util.ArrayList;
    
public class DigraphCycleOld {
    private boolean cycleDetected;

    public DigraphCycleOld(Digraph d) {
		for (int v = 0; v < d.V(); v++) {
			if (cycleDetectedFrom(v, d, new ArrayList<>())) {
			cycleDetected = true;
			break;
			}
		}
    }

    private boolean cycleDetectedFrom(int s, Digraph d, ArrayList<Integer> inPath) {
        if (inPath.contains(s)) {
			return true;
		}

		inPath.add(s);
		for (int w : d.adj(s)) {
			if (cycleDetectedFrom(w, d, inPath)) {
			return true;
			}
		}
		inPath.remove(inPath.size()-1);
		return false;
    }

    public boolean hasCycle() { 
		return cycleDetected;
    }

    public static void main(String[] args) {
		Digraph d = new Digraph(new In(args[0]));
		DigraphCycleOld cycle = new DigraphCycleOld(d);
		System.out.println(cycle.hasCycle());
    }
}
