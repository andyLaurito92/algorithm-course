package chapter3.symbolTables;

import java.util.Scanner;
import java.util.Iterator;
import java.io.File;

public class FrequencyCounter {
    public static void main(String[] args) throws Exception {
	File myFile = new File(args[0]);
	Scanner myScanner = new Scanner(myFile);
	int minLength = Integer.parseInt(myScanner.nextLine());

	//ST<String, Integer> myMap = new STList<>();
	STOrdered<String, Integer> myMap = new STArray();
	while (myScanner.hasNextLine()) {
	    String next = myScanner.nextLine();
	    if (next.length() < minLength) continue;

	    if (myMap.contains(next)) {
		myMap.put(next, myMap.get(next) + 1);
	    } else {
		myMap.put(next, 1);
	    }
	}

	System.out.println("Going to check for frequency keys");

	Iterator<String> iterable = myMap.keys().iterator();
	if (!iterable.hasNext()) {
	    System.out.println("No keys found");
	}
	
	String max = iterable.next();
	int frequency = myMap.get(max);
	while (iterable.hasNext()) {
	    String current = iterable.next();
	    int newFrequency = myMap.get(current);
	    if (newFrequency > frequency) {
		max = current;
		frequency = newFrequency;
	    }
	}

	System.out.println(String.format("%s has major frequency with %d",
					 max, frequency));
    }
}
