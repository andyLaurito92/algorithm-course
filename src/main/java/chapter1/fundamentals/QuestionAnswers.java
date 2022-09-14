package chapter1.fundamentals;

public class QuestionAnswers {
    public static void main(String[] args) {
	// INTEGER.MAX_VALUE = 2^31 -1 = 2 147 483 647. The below example shows that if you tried to 
	
	// do Math.abs(-(INTEGER.MAX_VALUE+1)), the operation doesn't work (It doesn't take the absolute value)
	int x;

	// This is some of the issues you can have with integer overflow :)
	System.out.println(String.format("Val: %d", Math.abs(-2147483648)));

	System.out.println(String.format("Val: %f", Double.POSITIVE_INFINITY));

	System.out.println(String.format("Val: %d", Integer.MAX_VALUE));

	int[] myArray = new int[32];
	System.out.println(String.format("Val: %s", myArray.toString()));


	double infinity = 1.0/0.0;
	System.out.println(String.format("Val: %f", infinity));

	String myString1 = "Hey man";
	String myString2 = "Hola amigo";

	// You cannot use < between strings :)
	// < and > are only for primitive types

	//System.out.println(String.format("Val: %b", myString1 < myString2));

	System.out.println(String.format("Bitwise operator: %d", 2 & 3));
    }

    public static int exampleMethod() {
	return 2+2;
    }

    // public static int anotherMethod(Function<Integer, Void> myFunction) {
    // 	return myFunction.apply();
    //}
}
 
