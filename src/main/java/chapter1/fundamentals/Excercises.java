package chapter1.fundamentals;

import java.util.HashMap;
import java.util.Collection;

public class Excercises {
    public static void main(String[] args) {
	System.out.println(String.format("Value: %f", 2.0e-6 * 100000000.1));

	System.out.println('b');
	System.out.println('b' + 'c');
	System.out.println((char) ('a' + 4));

	System.out.println(String.format("Representation of 17 is %s", integerToBinaryRepresentation(17)));
	System.out.println(String.format("Representation of 16 is %s", integerToBinaryRepresentation(16)));


	int[][] myMatrix = new int[][] {
	    {1, 2},
	    {3, 4},
	    {5, 6}
	};
	int[][] res = trasposeMatrix(myMatrix, 3, 2);

	StringBuilder strRepresentation = new StringBuilder();
	for (int j = 0; j < 2; j++) {
	    StringBuilder currentRow = new StringBuilder();
	    for (int i = 0; i < 3; i++) {
		currentRow.append(res[j][i]);
		currentRow.append(" ");
	    }
	    strRepresentation.append(currentRow.toString());
	    strRepresentation.append("\n");
	}
	System.out.println(String.format("Matrix: \n %s", strRepresentation));


	System.out.println(String.format("Largest base 2: %d", largestBase2Num(33)));
	System.out.println(String.format("Largest base 2: %d", largestBase2Num(125)));
	System.out.println(String.format("Largest base 2: %d", largestBase2Num(12)));

	System.out.println(String.format("Histogram: %s", histogram(new int[]{1, 2 ,3, 3, 1, 3}, 5)));
	System.out.println(String.format("Histogram: %s", histogram(new int[]{0, 8, 1, 1, 11, 1, 2 ,3, 3, 1, 3}, 9)));


	System.out.println(String.format("ln(10!) iterative: %f", naturalLogFactorian(10)));
	System.out.println(String.format("ln(10!) recursive: %f", recursiveNaturalLogFactorian(10)));

	System.out.println(String.format("mcd %d", euclideanAlgorithm(1111111, 1234567)));
	System.out.println(String.format("mcd %d", iterativeEuclideanAlgorithm(41111111, 1234567)));

	System.out.println(String.format("binomial 100, 50, .25: %f", optimizedBinomial(100,50,.25)));
	System.out.println(String.format("binomial 100, 50, .25: %f", binomial(100,50,.25)));
    }

    public static double optimizedBinomial(int N, int k, double p) {
	double[][] memory = new double[N+1][k+1];
	memory[0][0] = 1.0;
	return optimizedBinomial(N, k, p, memory);
    }

    public static double optimizedBinomial(int N, int k, double p, double[][] memory) {
	if (N < 0 || k < 0) return 0.0;
	
	if (memory[N-1][k] == 0) {
	    memory[N-1][k] = optimizedBinomial(N-1, k, p, memory);
	}
	if (memory[N-1][k-1] == 0) {
	    memory[N-1][k-1] = optimizedBinomial(N-1, k-1, p, memory);
	}

	memory[N][k] = (1.0 - p)*memory[N-1][k] + p*memory[N-1][k-1];

	return memory[N][k];
    }

    public static double binomial(int N, int k, double p) {
	if ((N==0) || (k < 0)) return 1.0;
	return (1.0 - p)*binomial(N-1, k, p) + p*binomial(N-1,k-1, p);
    }

    public static int euclideanAlgorithm(int p, int q) {
	if (q == 0) return p;
	int r = p % q;
	return euclideanAlgorithm(q, r);
    }

    public static int iterativeEuclideanAlgorithm(int p, int q) {
	int r = 0;
	while (q != 0) {
	    r = p % q;
	    p = q;
	    q = r;
	}
	return p;
    }

    /**
     ** For computing ln(N!) you just need to remember that:
     ** 1) ln(1) = 0
     ** 2) ln(x * y) = ln(x) + ln(y)
     ** From the above, you can deduce that ln(n!) = ln(n*(n-1)*(n-2)*...*1*0) = ln(n)+ln(n-1)+..+ln(0) = SUM i=0,N ln(i)
     **/
    public static double naturalLogFactorian(int n) {
	double res = 0;
	for (int j = 1; j < n+1; j++) {
	    res += Math.log(j);
	}
	return res;
    }

    public static double recursiveNaturalLogFactorian(int n) {
	if (n == 0 || n == 1) {
	    return 0;
	}

	return Math.log(n) + recursiveNaturalLogFactorian(n-1);
    }

    public static Collection<Integer> histogram(int[] array, int M) {
	HashMap<Integer,Integer> ocurrences = new HashMap<>();

	for (int j = 0; j < array.length; j++) {
	    int current = array[j];

	    if (j < M && !ocurrences.containsKey(j)) {
		ocurrences.put(j, 0);
	    }

	    if (current < M && !ocurrences.containsKey(current)) {
		ocurrences.put(current, 0);
	    }
	    
	    if (ocurrences.containsKey(current)) {
		ocurrences.put(current, ocurrences.get(current) + 1);
	    }
	}
	return ocurrences.values();
    }

    public static String integerToBinaryRepresentation(int n) {
	if (n == 0) {
	    return "0";
	}

	StringBuilder res = new StringBuilder();
	for(int current=n; current > 0; current /= 2) { // Can we make this faster?
	    res.insert(0, current%2);
	}
	return res.toString();
    }

    public static int[][] trasposeMatrix(int[][] A, int rowSize, int colSize) { // matrix n x m
	int[][] B = new int[colSize][rowSize];

	for (int j = 0; j < rowSize; j++) {
	    for (int i = 0; i < colSize; i++) {
		B[i][j] = A[j][i];
	    }
	}

	return B;
    }

    /**
     ** @return the largest base 2 number that
     ** is not larger than n
     **/
    public static int largestBase2Num(int n) { // TODO: Think how make this faster :)
	int current = 1;
	while(current < n) {
	    current *= 2;
	}
	return current/2;
    }
}
