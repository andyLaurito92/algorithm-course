package chapterStrings;

import java.util.Scanner;
import java.util.HashMap;

public class StringMatchingAlgorithms {
    public static void main(String[] args) {
		Scanner myScanner = new Scanner(System.in);
		while (myScanner.hasNextLine()) {
			String nextLine = myScanner.nextLine();
			String[] textAndPattern = nextLine.split(" ");
			print("Usual search");
			searchPattern(textAndPattern[0], textAndPattern[1]);

			print("Back up search");
			searchWithBackup(textAndPattern[0], textAndPattern[1]);

			print("DFA search");
			searchWithDFA(textAndPattern[0], textAndPattern[1]);

		}
    }
    /*** ================================================ **/
    /*** ============ Brute Forece Algoritmh ============ **/
    /*** ================================================ **/


    /**
     ** Practical challenges when looking pattern matching: 
     ** 1 - Do we always have to do O(M * N) ? (M being text length and N being pattern length)
     ** 2 - I may not always have the possibility to buffer my text to backup! (Ex: Stream of strings)
     **/

    /**
     ** The idea of this algorithm is that it backs up when the pattern
     ** doesn't match. The term "back up" means that it needs to go back
     ** to i+1 and start over. 
     ** The problem with this is that if you have a stream input bc your
     ** string is too big, then you cannot back up and therefore you cannot
     ** use this approach
     **/
    private static void searchPattern(String text, String pattern) {
		for (int i = 0; i < text.length(); i++) {
			for (int j = 0; j < pattern.length(); j++) {
			if (text.charAt(i+j) != pattern.charAt(j)) {
				break;
			}

			if (j + 1 == pattern.length()) {
				print(String.format("Pattern found. Starts at %d", i));
				return;
			}
			}
		}
		print("Pattern not found");
    }

    /** 
     ** This implementation works the same as the one above. The only difference
     ** is that we explicitly show in the code the back up concept mentioned above.
     **/ 
    private static void searchWithBackup(String text, String pattern) {
		// In this implementation:
		// i points to end of sequence of already-matched chars in text.
		// j stores # of already-matched chars (end of sequence in pattern)
		int i, j = 0;
		for (i = 0; i < text.length(); i++) {
			if (text.charAt(i) == pattern.charAt(j)) {
				j++;
			} else {
				// This is back up: When the pattern doesn't fin anymore
				// then go back
				i -= j;
				j = 0;
			}

			if (j == pattern.length()) {
				print(String.format("Pattern found. Starts at %d", i - j + 1));
				return;
			}
			}
		print("Pattern not found");
    }

    /*** ================================================ **/
    /*** ============ Knuth Morris Prat ================= **/
    /*** ================================================ **/

    /**
     ** This approach creates a deterministc finite automata to consume the text; it first 
     ** creates the DFA from the pattern, and it then just consumes the text. This algorithm
     ** works in linear time O(N + M*R) where 
     ** N is the length of the text
     ** M is the length of the pattern
     ** R is the length of the alphabet (which will usually be constant, meaning that the complexity reduces to O(N + M)
     **/
    public static void searchWithDFA(String text, String pattern) {
		HashMap<Integer, HashMap<Character, Integer>> dfa = createDFAFrom(pattern);
		for (int i = 0, state = 0; i < text.length() && state < pattern.length(); i++) {
			state = dfa.get(state).get(text.charAt(i)); // No backup! We could
			//state = dfa[in.readChar()][state]; // can use a stream instead of string and still works!
			//print(String.format("state %d", state));
			if (state == pattern.length()) {
			print(String.format("Pattern found. Starts at %d", i - state + 1));
			return;
			}
		}
		print("Pattern not found");
    }

    private static HashMap<Integer, HashMap<Character, Integer>> createDFAFrom(String pattern) {
		char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		// State j represents j characters matched so far
		HashMap<Integer, HashMap<Character, Integer>> dfa = new HashMap<>();
		int stateX = 0; // simulates where are we if we consume from pattern[1:state-1]
		for (int state = 0; state < pattern.length(); state++) {
			Character currentChar = pattern.charAt(state);
			HashMap<Character, Integer> charToNewState = new HashMap<>();
			// Easy one: Given the current state, if i read the ith char of my pattern, move to next state
			charToNewState.put(currentChar, state + 1);
			dfa.put(state, charToNewState);

			// Not as easy: Mismatches
			// If we are in state j, and next char c is a mismatch, then we know that the last j-1 chars of
			// input are pattern[1...j-1] follow by c. We just need to consume the previous pattern and see
			// in which state the dfa would be!. INSTEAD of going trought the pattern again, we just use
			// stateX as a What if
			if (state > 1) stateX = dfa.get(stateX).get(pattern.charAt(state-1));
			HashMap<Character, Integer> letterToState = dfa.get(state);
			//print(String.format("current letter: %s \n stateX: %d", currentChar, stateX));
			for (Character letter : ALPHABET) {
				// If we haven't matched any char so far, and next char is not in patter
				// then we need to keep in the current state
				if (currentChar != letter) {
					if (state == 0) {
					letterToState.put(letter, 0);
					} else {
					int mismatchState = dfa.get(stateX).get(letter);
					letterToState.put(letter, mismatchState);
					//print(String.format("%s to %d", letter, mismatchState));
					}
				}
			}
		}

		//printDFA(dfa);
		return dfa;
    }

    private static int[][] KMP(String pat) {
		int M = pat.length();
		int R = 24; // alphabet length
		int[][] dfa = new int[R][M];
		dfa[pat.charAt(0)][0] = 1;
		for (int X = 0, j = 1; j < M; j++) {
			for (int c = 0; c < R; c++) dfa[c][j] = dfa[c][X]; // copy mismatch cases
			dfa[pat.charAt(j)][j] = j + 1; // set match case
			X = dfa[pat.charAt(j)][X]; // update restart state
		}
		return dfa;
    }

    //private static void printDFA(HashMap<Integer, HashMap<

    // TODO: Implement Boyer-Moore and Rabin-Karp algorithms :)


    private static void print(Object str) {
		System.out.println(str);
    }
}
