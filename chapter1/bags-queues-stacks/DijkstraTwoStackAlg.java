import java.util.Scanner;
import java.util.Iterator;

public class DijkstraTwoStackAlg {

    public static void main(String[] args) throws Exception {
	//evaluateExpressions(args);
	evaluateEasier(args);
    }

    public static void testingStack(String[] args) {
	MyStack<String> myStack = new MyStackArray<>();
	myStack.push("hola");
	myStack.push("que");
	myStack.push("tal");
	myStack.push("chaauuuu");

	for (String head : myStack) {
	    System.out.println(head);
	}

	System.out.println(myStack.pop());
	System.out.println(myStack.pop());
	System.out.println(myStack.pop());
	System.out.println(myStack.pop());
	System.out.println(myStack.pop());
	System.out.println(myStack.pop());
    }

    public static void evaluateExpressions(String[] args) throws Exception {
	Scanner myScanner = new Scanner(System.in);

	while (true) {
	    try {
		System.out.println(String.format("Res: %s", evaluate(myScanner.nextLine())));
	    } catch (Exception ex) {
		System.out.println(ex);
	    }
	}
    }

    public static void evaluateEasier(String[] args) {
	Scanner myScanner = new Scanner(System.in);

	while (true) {
	    //try {
		System.out.println(String.format("Res: %s", easierEvaluation(myScanner.nextLine())));
	    // } catch (Exception ex) {
	    // 	System.out.println(ex);
	    // }
	}
    }

    // This method only evaluates expression of the kind S -> (S)|(N+N)|(N*N)|(N/N)|(N-N)
    // Where N -> [0-9]+
    // If an expression that doesn't match the above is passed, this method will fail  
    public static String easierEvaluation(String expression) {
	MyStack<String> operands = new MyStackArray<>();
	MyStack<String> numbers = new MyStackArray<>();

	String OPERANDS = "[+, /, *, -]";
	String NUMBERS = "[0-9]+";
	String[] values = expression.split("");

	for (String val : values) {
	    if (val.isBlank()) continue;
	    if (val.matches(OPERANDS)) operands.push(val);
	    if (val.equals("(")) continue;
	    if (val.matches(NUMBERS)) numbers.push(val);
	    if (val.equals(")")) {
		Integer number2 = Integer.parseInt(numbers.pop());
		Integer number1 = Integer.parseInt(numbers.pop());
		Integer res;
		String operand = operands.pop();
		if (operand.equals("+")) {
		    res = number1 + number2;
		    numbers.push(res.toString());
		}
		if (operand.equals("-")) {
		    res = number1 - number2;
		    numbers.push(res.toString());
		}
		if (operand.equals("/")) {
		    res = number1 / number2;
		    numbers.push(res.toString());
		}
		if (operand.equals("*")) {
		    res = number1 * number2;
		    numbers.push(res.toString());
		}
	    }
	}
	return numbers.pop();
    }

    // This method tries to evaluate an expression of the kind S -> N OP N where
    // N -> number and OP -> {+, -, /, *}
    // TODO: Add operator precedence :)
    public static String evaluate(String expression) throws Exception {
	String[] values = expression.split("");
	MyStack<String> numbers = new MyStackArray<>();
	MyStack<String> operandsAndParenthesis = new MyStackArray<>();
	String OPERAND = "[+, /, *, -]";
	String NUMBER = "[0-9]*";

	for (String val : values) { // 1 + (2 * 3)
	    if (val.isBlank()) {
		continue;
	    } else if (val.equals("(")) { // val = ) 
		operandsAndParenthesis.push("("); // operandsAndParenthesis = { + , ( , *}
	    } else if (val.equals(")")) {
		while (operandsAndParenthesis.peek() != null && !operandsAndParenthesis.peek().equals("(")) {
		    // Perform operations between parenthesis
		    if (operandsAndParenthesis.peek().matches(OPERAND)) {
			consumeOperandAndEvalExpression(numbers, operandsAndParenthesis);
		    } else {
			// A closing parenthesis comes, and either I cannot close the parenthesis or don't ahve enough operands to evaluate
			// an expression
			throw new Exception("Expression malformed");
		    }
		}

		if (operandsAndParenthesis.peek() == null) {
		    throw new Exception("Expression malformed");
		}
		// Consume open parenthesis
		operandsAndParenthesis.pop(); // numbers = {1, 6}, operandsAndParenthesis = { + }
	    } else if (val.matches(OPERAND)) {
		operandsAndParenthesis.push(val); // operandsAndParenthesis = { + , (, *}
	    } else if (val.matches(NUMBER)) {
		numbers.push(val); // numbers = { 1 , 2, 3}
	    }
	}

	// Consume operands and numbers without parenthesis
	while (!operandsAndParenthesis.isEmpty()) {
	    if (operandsAndParenthesis.peek().matches(OPERAND)) {
		consumeOperandAndEvalExpression(numbers, operandsAndParenthesis);
	    } else {
		throw new Exception("Expression malformed");
	    }
	}

	if (numbers.size() != 1) {
	    throw new Exception("Expression malformed");
	}
	return numbers.pop();
    }

    private static void consumeOperandAndEvalExpression(MyStack<String> numbers, MyStack<String> operandsAndParenthesis) throws Exception {
	String operand = operandsAndParenthesis.pop(); // *
	String number2 = numbers.pop(); // 3
	String number1 = numbers.pop(); // 2

	if (number1 == null || number2 == null) {
	    throw new Exception("Expression malformed");
	}

	Integer res = evalExpression(operand, number1, number2); // 2 * 3 = 6
	numbers.push(res.toString()); // numbers = { 1, 6 }, operandsAndParenthesis = {+, (}
    }

    private static Integer evalExpression(String operand, String num1, String num2) throws Exception {
	Integer number1 = Integer.parseInt(num1);
	Integer number2 = Integer.parseInt(num2);
	
	if (operand.equals("+")) {
	    return number1 + number2;
	} else if (operand.equals("-")) {
	    return number1 - number2;
	} else if (operand.equals("*")) {
	    return number1 * number2;
	} else if (operand.equals("/")) {
	    return number1 / number2;
	} else {
	    System.out.println(operand);
	    throw new Exception(String.format("Unexpected Operand %s", operand));
	}
    }
}
