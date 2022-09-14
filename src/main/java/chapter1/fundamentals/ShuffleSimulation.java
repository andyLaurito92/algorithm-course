package chapter1.fundamentals;

import java.util.Random;

public class ShuffleSimulation {


    public static void main(String[] args) {
	double[] anArray = new double[] {1.0, 3.3, 1992.0, 882.32, 44.21, 12.3, 99.9, 39.44};
	System.out.println(String.format("Array shuffle: %s", print(shuffle(anArray))));

	System.out.println(String.format("Array shuffle: %s", print(shuffle(anArray))));

	System.out.println(String.format("Array shuffle: %s", print(shuffle(anArray))));

	System.out.println(String.format("Array shuffle: %s", print(shuffle(anArray))));
    }

    public static double[] shuffle(double[] a) {
	int N = a.length;
	for (int i = 0; i < N; i++) {
	    int r = i + new Random().nextInt(N - i); 
	    double temp = a[i];
	    a[i] = a[r];
	    a[r] = temp;
	}
	return a;
    }

    public static String print(double[] array) {
	StringBuilder str = new StringBuilder();
	for (int j = 0; j < array.length; j++) {
	    str.append(array[j]);
	    str.append(" ");
	}
	return str.toString();
    }
}
