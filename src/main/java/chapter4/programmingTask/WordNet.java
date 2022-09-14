/** Performance requirements.  Your data type should use space linear in the input size (size of synsets and hypernyms files). 

The constructor should take time linearithmic (or better) in the input size. 

The method isNoun() should run in time logarithmic (or better) in the number of nouns. 

The methods distance() and sap() should run in time linear in the size of the WordNet digraph. 

For the analysis, assume that the number of nouns per synset is bounded by a constant. **/

package chapter4.programmingTask;

import edu.princeton.cs.algorithms.Digraph;
import edu.princeton.cs.algorithms.DirectedCycle;
import edu.princeton.cs.introcs.In;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

//import java.util.Collections;

public class WordNet {
    private HashMap<String, ArrayList<Synset>> nounsToSynsets;
    private HashMap<Integer, Synset> idsToSynsets;
    private int numSynsets;
    private Digraph d;
    private SAP sap;

    // constructor takes the name of the two input files
    public WordNet(String pathToSynsets, String pathToHypernyms) {
	notNull(pathToSynsets, pathToHypernyms);
	readSynsetFile(pathToSynsets);
	readHypernymsFile(pathToHypernyms);

	sap = new SAP(d);
	//DigraphCycle cycle = new DigraphCycle(d);
	DirectedCycle cycle = new DirectedCycle(d);
	if (cycle.hasCycle()) {
	    throw new IllegalArgumentException();
	}

	// Scanner myScanner = new Scanner(System.in);
	// while (true) {
	//     String noun = myScanner.nextLine();
	//     if (!isNoun(noun)) print(String.format("%s was not a valid noun!", noun));

	//     print(String.format("Synsets for noun %s are: ", noun));
	//     ArrayList<Synset> synsets = nounsToSynsets.get(noun);
	//     printList(synsets);
	// }
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
		return nounsToSynsets.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
		notNull(word);
		return nounsToSynsets.containsKey(word);
    }

    // run in time linear in the size of the WordNet digraph --> O(V + E) ?
    //  distance(x, y) = length of shortest ancestral path of subsets A and B 
    public int distance(String nounA, String nounB) {
		notNull(nounA, nounB);
		validNouns(nounA, nounB);

		Iterable<Synset> synsetsNounA = nounsToSynsets.get(nounA);
		Iterable<Synset> synsetsNounB = nounsToSynsets.get(nounB);

		int minDistance = d.V() + 1;
		int commonAncestor = -1;

		for (Synset s1: synsetsNounA) {
				for (Synset s2: synsetsNounB) {
				if (s1.id() != s2.id()) {
					int newLength = sap.length(s1.id(), s2.id());
					if (newLength < minDistance) {
					minDistance = newLength;
					}
				}
			}
	}

		if (commonAncestor == -1) return 0;
		else return minDistance;
    }

    // run in time linear in the size of the WordNet digraph
    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
   // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
		notNull(nounA, nounB);
		validNouns(nounA, nounB);

		Iterable<Synset> synsetsNounA = nounsToSynsets.get(nounA);
		Iterable<Synset> synsetsNounB = nounsToSynsets.get(nounB);

		int minDistance = d.V() + 1;
		int commonAncestor = -1;

		for (Synset s1: synsetsNounA) {
			for (Synset s2: synsetsNounB) {
				if (s1.id() != s2.id()) {
					int v = s1.id();
					int w = s2.id();

					int newLength = sap.length(v, w);
					if (newLength < minDistance) {
					minDistance = newLength;
					commonAncestor = sap.ancestor(v, w);
					}
				}
			}
		}

		if (commonAncestor == -1) {
			ArrayList<Synset> listSynsets = nounsToSynsets.get(nounA);
			for (Synset synset : listSynsets) {
			String synonymSet = synset.synonymSet();
			if (synonymSet.contains(nounB)) return synonymSet;
			}
		}
		return idsToSynsets.get(commonAncestor).synonymSet();
    }

    private void print(String str) {
		System.out.println(str);
    }

    private void readSynsetFile(String pathToSynsets) {
		In myScanner = new In(pathToSynsets);
		// Scanner myScanner;
		// try {
		//     myScanner = new Scanner(new File(pathToSynsets));
		// } catch (FileNotFoundException e) {
		//     throw new IllegalArgumentException();
		// }
		idsToSynsets = new HashMap<>();
		nounsToSynsets = new HashMap<>();
		while (myScanner.hasNextLine()) {
			String line = myScanner.readLine();
			String[] splittedLine = line.split(",");

			int synsetId = Integer.parseInt(splittedLine[0]);
			Synset synset = Synset.of(synsetId, splittedLine[1]);
			idsToSynsets.put(synsetId, synset);
			numSynsets++;
			for (String noun : splittedLine[1].split(" ")) {
			if (nounsToSynsets.containsKey(noun)) {
				nounsToSynsets.get(noun).add(synset);
			} else {
				ArrayList<Synset> synsets = new ArrayList<>();
				synsets.add(synset);
				nounsToSynsets.put(noun, synsets);
			}
			}
		}
    }

    private void readHypernymsFile(String pathToHypernyms) {
		In myScanner = new In(pathToHypernyms);
		// Scanner myScanner;
		// try {
		//     myScanner = new Scanner(new File(pathToHypernyms));
		// } catch (FileNotFoundException e) {
		//     throw new IllegalArgumentException();
		// }
		d = new Digraph(numSynsets);
		while (myScanner.hasNextLine()) {
			String line = myScanner.readLine();
			String[] splittedLine = line.split(",");

			Integer synsetId = Integer.parseInt(splittedLine[0]);
			for (int j = 1; j < splittedLine.length; j++) {
			String s = splittedLine[j];
			Integer hypernymId = Integer.parseInt(s);
			d.addEdge(synsetId, hypernymId);
			}
		}
    }

    private void notNull(String... arguments) {
		for (String s : arguments) {
			if (s == null) throw new IllegalArgumentException();
		}
    }

    private void validNouns(String nounA, String nounB) {
		if (!isNoun(nounA) || !isNoun(nounB)) {
			throw new IllegalArgumentException();
		}
    }

    private static class Synset {
		int id;
		String synonymSet;

		public static Synset of(int id, String synonymSet) {
			return new Synset(id, synonymSet);
		}

		public int id() {
			return id;
		}

		public String synonymSet() {
			return synonymSet;
		}

		public String toString() {
			return String.format("%d: %s", id, synonymSet);
		}

		private Synset(int id, String synonymSet) {
			this.id = id;
			this.synonymSet = synonymSet;
		}
    }

    // do unit testing of this class
    public static void main(String[] args) {
		WordNet wordNet = new WordNet(args[0], args[1]);
    }
}
