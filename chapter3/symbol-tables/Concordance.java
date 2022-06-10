import java.io.File;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
import java.util.Scanner;

public class Concordance {
    private static int CONTEXT_SIZE = 8;

    public static void main(String[] args) throws Exception {
	File myFile = new File(args[0]);
	String[] words = getWordsFrom(myFile);
	HashMap<String, HashSet<Integer>> context = new HashMap<>();
	for (int i = 0; i < words.length; i++) {
	    String s = words[i];
	    if (!context.containsKey(s)) context.put(s, new HashSet<>());
	    HashSet<Integer> references = context.get(s);
	    references.add(i);
	}

	Scanner readInput = new Scanner(System.in);
	while (readInput.hasNextLine()) {
	    String word = readInput.nextLine();
	    Set<Integer> pages = context.get(word);
	    if (pages == null) print(String.format("%s not found", word));
	    else {
		for (Integer i : pages) {
		    StringBuilder str = new StringBuilder();
		    str.append(String.format("%s ", words[i]));
		    int j = i;
		    int count = 1;
		    while (count < CONTEXT_SIZE) {
			if (j + count < words.length) str.append(String.format("%s ", words[j + count]));
			if (j - count > 0) str.insert(0, String.format("%s ", words[j - count]));
			count++;
		    }
		    print(str.toString());
		}
		print(String.format("Num occurrences: %d \n", pages.size()));
	    }
	}
    }

    private static void printArray(String[] array) {
	for (String str : array) {
	    print(str);
	}
    }

    private static String[] getWordsFrom(File myFile) throws Exception {
	
	Scanner myScanner = new Scanner(myFile);
	String allWords = myScanner.useDelimiter("\\A").next();
	return allWords.split("\\s+");
    }

    private static void print(String str) {
	System.out.println(str);
    }
}
