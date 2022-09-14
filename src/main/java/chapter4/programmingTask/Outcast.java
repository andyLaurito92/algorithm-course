package chapter4.programmingTask;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;

public class Outcast {
    private final WordNet wordnet;
    // constructor takes a WordNet object
    public Outcast(WordNet wordnet) {
		this.wordnet = wordnet;
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
		int maxDistance = -1;
		String theOutcast = "";
		for (int j = 0; j < nouns.length; j++) {
			int distToAllNouns = 0;
			for (int i = 0; i < nouns.length; i++) {
			if (i != j) {
				distToAllNouns += wordnet.distance(nouns[j], nouns[i]);
			}
			}

			if (distToAllNouns > maxDistance) {
			maxDistance = distToAllNouns;
			theOutcast = nouns[j];
			}
		}
		return theOutcast;
    }

    // see test client below
    public static void main(String[] args) {
		WordNet wordnet = new WordNet(args[0], args[1]);
		Outcast outcast = new Outcast(wordnet);
		for (int t = 2; t < args.length; t++) {
			In in = new In(args[t]);
			String[] nouns = in.readAllStrings();
			StdOut.println(args[t] + ": " + outcast.outcast(nouns));
		}
    }
}
