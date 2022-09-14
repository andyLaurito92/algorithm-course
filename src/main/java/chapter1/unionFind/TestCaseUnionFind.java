
package chapter1.unionFind;
import java.util.Scanner;

// Our goal here is to identify from Standard input those pairs that are not connected.
// For these pairs:
// 1. We priint them
// 2. We connect them
public class TestCaseUnionFind {
    public static void main(String[] args) throws Exception {
	Scanner myScanner = new Scanner(System.in);
	String firstLine = myScanner.nextLine();

	int N = Integer.parseInt(firstLine);
	//UnionFind myUnionFind = new UnionFindQuickFind(N);
	UnionFind myUnionFind = new UnionFindQuickUnion(N);
	//UnionFind myUnionFind = new UnionFindWeighted(N);
	int linesSeen = 0;

	while (true) {
	    String line = myScanner.nextLine();
	    if (line.isEmpty()) break;
	    
	    String[] splittedLine = line.split(" ");
	    if (splittedLine.length != 2) {
		throw new Exception("Expected a pair of numbers");
	    }

	    int p = Integer.parseInt(splittedLine[0]);
	    int q = Integer.parseInt(splittedLine[1]);;

	    if (!myUnionFind.connected(p, q)) {
		System.out.println(String.format("Pairs %d %d were not connected. Connecting", p, q));
		myUnionFind.union(p,q);
	    }
	    linesSeen ++;
	}

	System.out.println(String.format("Final connected components: %d", myUnionFind.count()));
    }
}
