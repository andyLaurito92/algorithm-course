import java.util.Scanner;
import java.io.File;
import java.util.Random;

public class BinarySearch {

    public static void main(String[] args) {
	randomMatches();
	readFromFileMain();
	
    }

    public static void randomMatches() {

	int N = 1000;
	int[] array1= generateArray(N);
	int[] array2 = generateArray(N);

	// TODO: Sort arrays!! Instead of using Math.random, let's come back in the sorting chapter :)
    }
    
    public static void readFromFileMain(String[] args) throws Exception {
	int[] arrayReceived = readIntegersFromFile(args[0], Integer.parseInt(args[1]));
	Scanner myScanner = new Scanner(System.in);

	while (true) {
	    int searchFor = Integer.parseInt(myScanner.nextLine());
	    long startTime = System.currentTimeMillis();
	    int res = BinarySearch.search(searchFor, arrayReceived);
	    long endTime = System.currentTimeMillis();

	    System.out.printf("Result is: %d, took: %d ms\n", res, (endTime - startTime));
	}
    }

    // Generate array of size N. Integers positives of six digit
    public static int[] generateArray(int N) {
	int[] random = new int[N];
	for (int j = 0; j < N; j++) {
	    random[j] = getNextRandomInt();
	}
	return random;
    }

    public static int getNextRandomInt() {
	return new Random().nextInt(1000000);
    }

    public static int[] readIntegersFromFile(String filename, int length) throws Exception {
	if (filename == null || filename.isEmpty()) {
	    throw new IllegalArgumentException(String.format("File name not valid %s", filename));
	}

	File myFile = new File(filename);
	Scanner scanner = new Scanner(myFile);
	int[] res = new int[length];
	int i = 0;
	while (scanner.hasNextLine()) {
	    int next = Integer.parseInt(scanner.nextLine());
	    res[i] = next;
	    i++;
	}
	return res;
    }

    /** Looks for an element in a sorted array. If found, return it's index position, otherwise returns -1. **/
    public static int search(int elem, int[] array) {
	int lo = 0;
	int hi = array.length - 1;
	int med, current;
	
	while (lo <= hi) {
	    med = lo + (hi-lo)/ 2;
	    current = array[med];
	    if (current == elem) {
		return med;
	    } else if (current > elem) { 
		hi = med - 1;
	    } else {
		lo = med + 1;
	    }
	}
	return -1;
    }
}
