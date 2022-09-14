package chapter2.priorityQueues;

import edu.princeton.cs.algorithms.MinPQ;

import java.util.ArrayList;
import java.util.Comparator;

public class Solver {
    private boolean isSolvable;
    private SearchNode last;
         // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
	if (initial == null) throw new IllegalArgumentException();
	
	isSolvable = false;
	//astar(initial, SearchNode.HAMMING_DISTANCE_COMPARATOR); 
	astar(initial, SearchNode.MANHATTAN_DISTANCE_COMPARATOR); 
    }

    private void astar(Board initialBoard, Comparator<SearchNode> comparator) {
	MinPQ<SearchNode> queue = new MinPQ<>(comparator);
	MinPQ<SearchNode> twinQueue = new MinPQ<>(comparator);
	    
	SearchNode current = new SearchNode(null, initialBoard, 0);
	SearchNode twinCurrent = new SearchNode(null, initialBoard.twin(), 0);
	
	while(!current.current().isGoal() && !twinCurrent.current().isGoal()) {
	    // System.out.println(currentSearchNode.current());
	    // System.out.println(String.format("Num moves: %d, priority: %d", currentSearchNode.moves(), priority));
	    addNeighbors(current, queue);
	    addNeighbors(twinCurrent, twinQueue);
	    
	    current = queue.delMin();
	    twinCurrent = twinQueue.delMin();
	}

	if (current.current().isGoal()) {
	    isSolvable = true;
	    last = current;
	}
    }

    private void addNeighbors(SearchNode current, MinPQ<SearchNode> queue) {
	Iterable<Board> neighbors = current.current().neighbors();
	for (Board neighbor : neighbors) {
	    SearchNode prev = current.prev();
	    if ((prev != null && !prev.current().equals(neighbor)) || prev == null) {
		queue.insert(new SearchNode(current, neighbor, current.moves() + 1));
	    }
	}
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
	return isSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
	if (!isSolvable()) return -1;
	return last.moves();
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
	if (!isSolvable()) return null;
	
	ArrayList<Board> solution = new ArrayList<>();
	SearchNode currentNode = last;
	while (currentNode != null) {
	    solution.add(0, currentNode.current());
	    currentNode = currentNode.prev();
	}
	return solution;
    }

    // test client (see below) 
    public static void main(String[] args) {
	// create initial board from file
	// File myFile;
	// Scanner scanner;
	// try {
	//     myFile = new File(args[0]);
	//     scanner = new Scanner(myFile);
	// } catch (Exception e) {
	//     System.out.println("Error while reading file");
	//     return;
	// }
	
	
	// int n = Integer.parseInt(scanner.nextLine());
	// int[][] tiles = new int[n][n];
	// for (int i = 0; i < n; i++) {
	//     for (int j = 0; j < n; j++) {
	// 	tiles[i][j] = scanner.nextInt();
	//     }
	//     scanner.nextLine();
	// }
	int[][] tiles = new int[][] {
		{8, 1, 3},
		{4, 0, 2},
		{7, 6, 5}
	};
    
	Board initial = new Board(tiles);

	// solve the puzzle
	Solver solver = new Solver(initial);

	// print solution to standard output
	if (!solver.isSolvable())
	    System.out.println("No solution possible");
	else {
	    System.out.println("Minimum number of moves = " + solver.moves());
	    for (Board board : solver.solution())
		System.out.println(board.toString());
	}
    }

    private static class SearchNode implements Comparable<SearchNode> {
	public static final Comparator<SearchNode> HAMMING_DISTANCE_COMPARATOR =
	new Comparator<SearchNode>() {
	    public int compare(SearchNode one, SearchNode another) {
		return one.hammingPriority() - another.hammingPriority();
	    }
	};

	public static final Comparator<SearchNode> MANHATTAN_DISTANCE_COMPARATOR =
	new Comparator<SearchNode>() {
	    public int compare(SearchNode one, SearchNode another) {
		return one.manhattanPriority() - another.manhattanPriority();
	    }
	};
    
	private final Board current;
	private final int moves;
	private final SearchNode prev;
	private final int hammingPriority;
	private final int manhattanPriority;

	public SearchNode(SearchNode prev, Board current, int currentMoves) {
	    this.current = current;
	    this.moves = currentMoves;
	    this.prev = prev;
	    //this.hammingPriority = current().hamming() + moves();
	    this.hammingPriority = 0;
	    this.manhattanPriority = current().manhattan() + moves();
	}

	public Board current() {
	    return current;
	}

	public SearchNode prev() {
	    return prev;
	}

	public int moves() {
	    return moves;
	}

	public int compareTo(SearchNode another) {
	    return this.moves - another.moves();
	}

	public int hammingPriority() {
	    return hammingPriority;
	}

	public int manhattanPriority() {
	    return manhattanPriority;
	}
    }

}
