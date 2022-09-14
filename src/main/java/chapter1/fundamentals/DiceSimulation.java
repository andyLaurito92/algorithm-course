package chapter1.fundamentals;

import java.util.Random;

public class DiceSimulation {
    public static void main(String[] args) {
	double[] sumProbabilities = twoDiceSumProbabilities();
	System.out.println(String.format("Probabilities are %s", print(sumProbabilities)));

	int numThrows = 30;
	System.out.println(String.format("Probabilities with numThrows %d are %s", numThrows, print(simulateDices(numThrows))));
	numThrows = 100;
	System.out.println(String.format("Probabilities with numThrows %d are %s", numThrows, print(simulateDices(numThrows))));
	numThrows = 300;
	System.out.println(String.format("Probabilities with numThrows %d are %s", numThrows, print(simulateDices(numThrows))));
	numThrows = 800;
	System.out.println(String.format("Probabilities with numThrows %d are %s", numThrows, print(simulateDices(numThrows))));
    }

    public static double[] simulateDices(int numThrows) {
	double[] occurrencesSum = new double[13]; // Keep track of occurrences of sum of dices
	for (int j = 0; j < numThrows; j++) {
	    int dice1 = throwDice();
	    int dice2 = throwDice();
	    occurrencesSum[dice1+dice2] += 1.0;
	}

	for (int j = 2; j < 13; j++) {
	    occurrencesSum[j] /= numThrows;
	}
	return occurrencesSum;
    }

    public static int throwDice() {
	return (int) (Math.random() * 6 + 1);
    }

    public static double[] twoDiceSumProbabilities() {
	// We create an array of all possible sums that we can get from summing the two dices.
	// Values are between 2 and 12
	double[] sums = new double[12+1];

	for (int j = 1; j < 7; j++) {
	    for (int i = 1; i < 7; i++) {
		sums[j+i] += 1.0; // Count occurrences of sum
	    }
	}

	// Convert occurrences into probabilities
	for (int j = 2; j < 13; j++) {
	    sums[j] /= 36.0;
	}

	return sums;
    }

    public static String print(double[] array) {
	StringBuilder str = new StringBuilder();
	for (int j = 0; j < array.length; j++) {
	    str.append(array[j]);
	    str.append("\n");
	}
	return str.toString();
    }
}
