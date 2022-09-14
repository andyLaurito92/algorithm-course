package chapter1.bagsQueuesStacks;

import java.util.Scanner;
import java.util.Map;

public class Exercises1 {

    public static void main(String[] args) {
		userInput();
    }

    public static void testingReverse(String[] args) {
		MyQueue<String> myQueue = new MyQueueList<>();
		myQueue.enqueue("hola");
		myQueue.enqueue("Ramon");
		myQueue.enqueue("Pedro");
		myQueue.enqueue("Cacho");

		reverseOrder(myQueue);
		System.out.println(myQueue);
	}

	public static void userInput() {
		Scanner myScanner = new Scanner(System.in);

		while (true) {
			//System.out.println(String.format("Balanced: %b", isBalanced(myScanner.nextLine())));
			//System.out.println(String.format("Binary representation: %s", binaryRepresentation(Integer.parseInt(myScanner.nextLine()))));
			//System.out.println(String.format("Res: %s", addLeftParenthesis(myScanner.nextLine())));
			System.out.println(String.format("Res: %s", infixToPostfix(myScanner.nextLine())));
		}
	}

    public static String infixToPostfix(String expression) {
		MyStack<String> res = new MyStackList<>();
		MyStack<String> operators = new MyStackList<>();

		String NUMBER = "[0-9]+";
		String OPERATOR = "[+,/,*,-]";

		String[] values = expression.split("");
		for (String val : values) {
			if (val.isBlank()) continue;
			if (val.matches(NUMBER)) {
			res.push(val);
			}
			if (val.matches(OPERATOR)) {
			operators.push(val);
			}

			if (val.equals(")")) {
			StringBuilder str = new StringBuilder();
			String operand2 = res.pop();
			String operand1 = res.pop();
			String operand = operators.pop();

			str.append("(");
			str.append(" ");
			str.append(operand1);
			str.append(" ");
			str.append(operand2);
			str.append(" ");
			str.append(operand);
			str.append(")");
			res.push(str.toString());
			}
		}
		return res.pop();
    }

    /** 
     ** Expression without left parenthesis and prints the equivalent infix expression with 
     ** the parentheses inserted
     ** For example: 1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )
     ** Should print: ( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6  ) ) )
     **/
    public static String addLeftParenthesis(String expression) {
		// 1 + 2 ) --> ( 1 + 2 )
		// 1 + 2 + 3 ) ) --> ( 1 + ( 2 + 3 ) )
		String[] values = expression.split("");
		MyStack<String> res = new MyStackList<>();
		MyStack<String> operands = new MyStackList<>();

		String NUMBERS = "[0-9]+";
		String SIGNS = "[+,/,*,-]";
		for (String val : values) {
			if (val.isBlank()) continue;
			if (val.matches(NUMBERS)) {
			res.push(val);
			}
			if (val.matches(SIGNS)) {
			operands.push(val);
			}
			if (val.equals(")")) {
			StringBuilder innerExpression = new StringBuilder();
			String operand2 = res.pop();
			String operand1 = res.pop();
			String sign = operands.pop();

			innerExpression.append(" ");
			innerExpression.append("(");
			innerExpression.append(" ");
			innerExpression.append(operand1);
			innerExpression.append(" ");
			innerExpression.append(sign);
			innerExpression.append(" ");
			innerExpression.append(operand2);
			innerExpression.append(" ");
			innerExpression.append(")");
			innerExpression.append(" ");

			res.push(innerExpression.toString());
			}
		}

		return res.pop();
    }

    public static boolean isBalanced(String expression) {
		MyStack<String> parenthesis = new MyStackList<String>();
		String OPEN_PARENTHESIS = "[\\{\\[\\(]";
		String CLOSING_PARENTHESIS = "[\\}\\]\\)]";
		Map<String, String> matchingClosing = Map.of( "(", ")",
								  "{", "}",
								  "[", "]");
		String[] values = expression.split("");

		for (String val : values) {
			if (val.isBlank()) continue;
			if (val.matches(OPEN_PARENTHESIS)) {
			parenthesis.push(val);
			}
			if (val.matches(CLOSING_PARENTHESIS)) {
			String lastOpenSeen = parenthesis.pop();
			if (lastOpenSeen == null || ! matchingClosing.get(lastOpenSeen).equals(val)) {
				// LastSeen parenthesis is not closing this one
				return false;
			}
			}
		}
		return parenthesis.isEmpty();
    }

    public static String binaryRepresentation(int number) {
		MyStack<Integer> digits = new MyStackList<>();

		for (int j = number; j > 0; j = j / 2) {
			digits.push(j % 2);
		}

		StringBuilder res = new StringBuilder();
		for (Integer digit : digits) {
			res.append(digit);
		}
		return res.toString();
    }

    public static <Item> void reverseOrder(MyQueue<Item> queue) {
		MyStack<Item> stack = new MyStackList<>();

		while (!queue.isEmpty()) {
			stack.push(queue.dequeue());
		}

		while (!stack.isEmpty()) {
			queue.enqueue(stack.pop());
		}
    }
}
