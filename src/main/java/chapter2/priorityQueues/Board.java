package chapter2.priorityQueues;

import java.util.Arrays;
import java.util.Random;

public class Board {
    private final int[][] board;
    private Board[] neighbors;
    private Board myTwin;
    private int rowBlankSquare;
    private int colBlankSquare;
    private int manhattan = -1;
    private int hamming = -1;
    private String stringRepresentation;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
		board = new int[tiles.length][tiles.length];

		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
			board[i][j] = tiles[i][j];
			if (board[i][j] == 0) {
				rowBlankSquare = i;
				colBlankSquare = j;
			}
			}
		}
    }
                                           
    // string representation of this board
    public String toString() {
		if (stringRepresentation == null) {
			StringBuilder str = new StringBuilder();
			int N = dimension();
			str.append(N);
			str.append("\n");
			for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				str.append(board[i][j]);
				str.append(" ");
			}
			str.append("\n");
			}
			stringRepresentation = str.toString();
		}
		return stringRepresentation;
    }

    // board dimension n
    public int dimension() {
		return board.length;
    }

    // number of tiles out of place
    public int hamming() {
		if (hamming == -1) {
			int expectedNum = 1;
			int tilesInWrongPosition = 0;
			int N = dimension();
			for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] != 0 && board[i][j] != expectedNum) {
				tilesInWrongPosition++;
				}
				expectedNum++;
			}
			}
			hamming = tilesInWrongPosition;
		}
		return hamming;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
		if (manhattan == -1) {
			int manhattanDistance = 0;
			int N = dimension();
			int expectedNumber = 1;

			for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 0) {
				expectedNumber++;
				continue;
				}
				//int distance = calculateManhattan(board[i][j], i, j, expectedNumber);
				//System.out.println(String.format("(%d, %d), val is: %d, distance: %d", i, j, board[i][j], distance));
				//manhattanDistance += distance;
				manhattanDistance += calculateManhattan(board[i][j], i, j, expectedNumber);
				expectedNumber++;
			}
			}
			manhattan = manhattanDistance;
		}
		return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
		return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
		if (y == null) return false;
		if (!(y.getClass().equals(this.getClass()))) return false;
		Board other = (Board) y;
		int N = dimension();
		if (N != other.dimension()) return false;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
			if (board[i][j] != other.board[i][j]) return false;
			}
		}
		return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
		if (neighbors == null) {
			neighbors = getBlankNeighbors();
		}
		return Arrays.asList(neighbors);
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
		if (myTwin == null) {
			Random random = new Random();
			int n = dimension();
			int row1 = random.nextInt(n);
			int col1 = random.nextInt(n);
			int row2 = random.nextInt(n);
			int col2 = random.nextInt(n);

			exchange(board, row1, col1, row2, col2);
			myTwin = new Board(board);
			exchange(board, row2, col2, row1, col1);
		}
		return myTwin;
    }

    private Board[] getBlankNeighbors() {
		Board[] aux = new Board[4];
		int N = dimension();
		int numNeighbors = 0;
		if (0 <= rowBlankSquare - 1) {
			exchange(board, rowBlankSquare, colBlankSquare, rowBlankSquare - 1, colBlankSquare);
			aux[numNeighbors++] = new Board(board);
			exchange(board, rowBlankSquare - 1, colBlankSquare, rowBlankSquare, colBlankSquare);
		}

		if (rowBlankSquare + 1 <= N-1) {
			exchange(board, rowBlankSquare, colBlankSquare, rowBlankSquare + 1, colBlankSquare);
			aux[numNeighbors++] = new Board(board);
			exchange(board, rowBlankSquare + 1, colBlankSquare, rowBlankSquare, colBlankSquare);
		}

		if (0 <= colBlankSquare -1) {
			exchange(board, rowBlankSquare, colBlankSquare, rowBlankSquare, colBlankSquare - 1);
			aux[numNeighbors++] = new Board(board);
			exchange(board, rowBlankSquare, colBlankSquare - 1, rowBlankSquare, colBlankSquare);
		}

		if (colBlankSquare + 1 <= N-1) {
			exchange(board, rowBlankSquare, colBlankSquare, rowBlankSquare, colBlankSquare + 1);
			aux[numNeighbors++] = new Board(board);
			exchange(board, rowBlankSquare, colBlankSquare + 1, rowBlankSquare, colBlankSquare);
		}

		Board[] res = new Board[numNeighbors];
		for (int i = 0; i < numNeighbors; i++) res[i] = aux[i];

		return res;
    }

    private void exchange(int[][] someBoard, int row1, int col1, int row2, int col2) {
		int temp = someBoard[row1][col1];
		someBoard[row1][col1] = board[row2][col2];
		someBoard[row2][col2] = temp;
		}

		private int calculateManhattan(int num, int row, int col, int expected) {
		if (num == expected) return 0;

		int N = dimension();
		boolean lastColumn = num % N == 0;
		int expectedRow = lastColumn ? (num / N) - 1 : (num/N);
		int expectedCol = lastColumn ? N - 1 : (num % N) - 1;
		// System.out.println(String.format("For %d expected (%d, %d) , is (%d, %d)", num, expectedRow, expectedCol, row, col));
		return (Math.abs(expectedRow - row) + Math.abs(expectedCol - col));
    }

    // unit testing (not graded)
    public static void main(String[] args) {
		Board myBoard = new Board(new int[][]{
			new int[]{1, 0, 3},
			new int[]{4, 2, 5},
			new int[]{7, 8 , 6}
			});
		System.out.println(myBoard.toString());
		Iterable<Board> boards = myBoard.neighbors();
		for (Board neighbor : boards) {
			System.out.println(neighbor.toString());
		}

		System.out.println("Twin");
		System.out.println(myBoard.twin());
		Board getTwin = new Board(new int[][] {
			{3, 2},
			{0, 1}
			});
		System.out.println(getTwin.twin());

		Board anotherBoard = new Board(new int[][] {
			{0, 1, 3},
			{4, 2, 5},
			{7, 8, 6}
			});
		System.out.println("Distances");
		System.out.println(anotherBoard.toString());
		System.out.println(String.format("Hamming %d", anotherBoard.hamming()));
		System.out.println(String.format("Manhattan %d", anotherBoard.manhattan()));

		Board goal = new Board(new int[][] {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 0}
			});

		Board equalBoard = new Board(new int[][] {
			{8, 1, 3},
			{4, 0, 2},
			{7, 6, 5}
			});

		System.out.println("Goal Board");
		System.out.println(goal.toString());
		System.out.println(goal.isGoal());

		System.out.println("Equal");
		System.out.println(anotherBoard.toString());
		System.out.println(equalBoard.toString());
		System.out.println(equalBoard.equals(anotherBoard));
		System.out.println(equalBoard.equals(goal));
    }
}
