import java.util.Random;

public class SortCompare {
    public static void main(String[] args) {
	String sortingAlg1 = args[0];
	String sortingAlg2 = args[1];
	Integer size = Integer.parseInt(args[2]);
	Integer numExperiments = Integer.parseInt(args[3]);

	Double timeAlg1 = runExperimentsFor(sortingAlg1, size, numExperiments);
	Double timeAlg2 = runExperimentsFor(sortingAlg2, size, numExperiments);

	System.out.println(String.format("Running time for %s was %f", sortingAlg1, timeAlg1));
	System.out.println(String.format("Running time for %s was %f", sortingAlg2, timeAlg2));
    }

    public static Double runExperimentsFor(String algName, int size, int numExperiments) {
	Double[] array = new Double[size];
	Double accumulativeTime = 0.0;
	for (int j = 0; j < numExperiments; j++) {
	    generateRandomArray(array);
	    
	    long start = System.currentTimeMillis();
	    switch (algName) {
	    case "insertion":
		InsertionSort.sort(array);
		break;
	    case "insertionWithoutExchanges":
		InsertionSort.sortWithoutExchanges(array);
		break;
	    case "selection":
		SelectionSort.sort(array);
		break;
	    case "shell":
		ShellSort.sort(array);
		break;
	    default:
		System.out.println(String.format("%s not found", algName));
	    }
	    long end = System.currentTimeMillis();
	    accumulativeTime += (end - start) / 1000.0;// time in seconds
	}
	return accumulativeTime;
    }

    public static void generateRandomArray(Double[] array) {
	Random random = new Random();
	for (int i = 0; i < array.length; i++) {
	   array[i] = random.nextDouble();
	}
    }
}
