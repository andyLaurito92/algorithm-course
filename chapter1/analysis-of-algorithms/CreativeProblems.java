import java.util.Arrays;
import java.util.HashMap;

public class CreativeProblems {
    public static void main(String[] args) {
	//System.out.println(String.format("Pairs: %d", twoSumProblem(new int[]{-3, 4, 8, 3, -4, 0, 2, -4, -2, 12, -8})));
	//System.out.println(String.format("Pairs: %d", twoSumProblemFastest(new int[]{-3, 4, 8, 3, -4, 0, 2, -4, -2, 12, -8})));
	// System.out.println(String.format("Pairs: %d", threeSumProblem(new int[]{10, 5, -5, -5, 8, 9, 3, -8, -2, -1, -4, -4, -7, -3})));
	// System.out.println(String.format("Pairs: %d", threeSumProblemFaster(new int[]{10, 5, -5, -5, 8, 9, 3, -8, -2, -1, -4, -4, -7, -3})));

	System.out.println(String.format("Found closes pair: %s", closestPair(new double[]{1.0, 100.0, 300.0, 2.0, 200.0})));
    }

    public static void fourSumProblem(int[] numbers) {
	
    }

    public static int threeSumProblem(int[] numbers) {
	int count = 0;
	Arrays.sort(numbers);
	for (int j = 0; j < numbers.length; j++) { // a + b + c = 0 ==> a + b = -c
	    for (int i = j + 1; i < numbers.length; i++) {
		int index = searchFor(-(numbers[j] + numbers[i]), numbers);
		if (index > i) {
		    System.out.println(String.format("Triple pair found %d %d %d", numbers[index], numbers[j], numbers[i]));
		    count++;
		}
	    }
	}
	return count;
    }

    // This is O(NlgN). If the array is sorted, you can iterate over it and the min distance will be those 2 numbers whose distance
    // Is less that the other distances. Given that the array is sorted, you can guaranteed that distances between numbers that are
    // not next to each other cannot have a minor distance
    public static MyPair<Double, Double> closestPair(double[] array) {
	Arrays.sort(array);
	double minDistance = Double.POSITIVE_INFINITY;
	MyPair<Double, Double> res = null;

	for (int j = 1; j < array.length; j++) {
	    Double first = Math.abs(array[j-1]);
	    Double second = Math.abs(array[j]);
	    Double currentDistance = Math.abs(second - first);
	    if (minDistance > currentDistance) {
		res = new MyPair<>(first, second);
		minDistance = currentDistance;
	    }
	}

	System.out.println(String.format("Minor distance is %f; Pairs: %f %f", minDistance, res.first, res.second));
	return res;
    }

    // This is O(N). The trick is understanding that the farthest distance is the max - min
    public static MyPair<Double, Double> farthestPair(double[] array) {
	Double min = Double.POSITIVE_INFINITY;
	Double max = Double.NEGATIVE_INFINITY;

	for (Double val : array) {
	    if (val > max) {
		max = val;
	    }
	}

	for (Double val : array) {
	    if (val < min) {
		min = val;
	    }
	}

	double distance = Math.abs(Math.abs(max) - Math.abs(min));
	MyPair<Double, Double> res = new MyPair<>(max, min);
	System.out.println(String.format("Farthest distance is %f; Pairs: %f %f", distance, res.first, res.second));
	return res;
    }

    public static int threeSumProblemFaster(int[] numbers) {
	int count = 0;
	HashMap<Integer, SumPair> pairSum = new HashMap<>();

	// We create a hashmap with all the possible pair sums of the array
	for (int j = 0; j < numbers.length; j++) {
	    for (int i = j + 1; i < numbers.length; i++) {
		int sum = numbers[i]+numbers[j];
		if (pairSum.containsKey(sum)) {
		    SumPair container = pairSum.get(sum);
		    container.frequency += 1;
		} else {
		    SumPair container = new SumPair(sum, j, i);
		    pairSum.put(sum, container);
		}
	    }
	}
	// So far, our hashMap has a + b, let's find c, such that a + b = -c 
	for (int j = 0; j < numbers.length; j++) {
	    if(pairSum.containsKey(-numbers[j])) {
		SumPair container = pairSum.get(-numbers[j]);
		if (container.secondIndex < j) {
		    count += container.frequency;
		    System.out.println(String.format("Found %d %d %d", numbers[container.firstIndex], numbers[container.secondIndex], numbers[j]));
		    System.out.println(String.format("Indexes %d %d %d", container.firstIndex, container.secondIndex, j));
		}
	    }
	}

	return count / 2;
    }

    static class MyPair<FirstType, SecondType> {
	public FirstType first;
	public SecondType second;

	MyPair(FirstType x, SecondType y) {
	    this.first = x;
	    this.second = y;
	}

	@Override
	public String toString() {
	    return String.format("(%s, %s)", first, second);
	}
    }

    static class SumPair {
	public int sum;
	public int firstIndex;
	public int secondIndex;
	public int frequency;

	SumPair(int sum, int firstIndex, int secondIndex) {
	    this.sum = sum;
	    this.firstIndex = firstIndex;
	    this.secondIndex = secondIndex;
	    this.frequency = 0;
	}
    }

    // Uses Double space of memory, but linear grow with respect to the entry
    public static int twoSumProblemFastest(int[] numbers) {
	HashMap<Integer, Integer> values = new HashMap<>();
	int count = 0;

	for (int j = 0; j < numbers.length; j++) {
	    int num = numbers[j];
	    if (values.containsKey(num)) {
		values.put(num, values.get(num)+1);
	    } else {
		values.put(num, 1);
	    }
	}

	for (int j = 0; j < numbers.length; j++) {
	    int val = numbers[j];
	    if (values.containsKey(-val) && val != 0) {
		count += values.get(-val);
		System.out.println(String.format("Found %d %d", val, -val));
	    }
	}

	return count / 2; 
    }

    // O(NlgN)
    public static int twoSumProblem(int[] numbers) {
	Arrays.sort(numbers);
	int countPairs = 0;
	for (int j = 0; j < numbers.length; j++) {
	    int index = searchFor(-numbers[j], numbers);
	    // Index != j in order to avoid, in this case, 0 against 0
	    if (index > j) {
		System.out.println(String.format("Found pairs %d and %d", numbers[j], numbers[index]));
		countPairs ++;
	    }
	}
	return countPairs;
    }

    // Binary search: Pre-requirement: Array is sorted
    public static int searchFor(int key, int[] array) {
	int hi = array.length - 1;
	int lo = 0;
	while (lo <= hi) {
	    int med = lo + (hi - lo)/2;
	    if (array[med] == key) return med;
	    if (key < array[med]) hi = med - 1;
	    if (key > array[med]) lo = med + 1;
	}
	return -1;
    }
}
