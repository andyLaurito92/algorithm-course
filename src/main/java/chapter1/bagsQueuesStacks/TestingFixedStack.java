package chapter1.bagsQueuesStacks;

import java.util.Iterator;

public class TestingFixedStack {
    public static void main(String[] args) {
		MyStack<Double> myStackDoubles = new MyStackArray<>();
		myStackDoubles.push(2.0);
		myStackDoubles.push(3.3);
		myStackDoubles.push(-3.2);
		myStackDoubles.push(-3.2);
		myStackDoubles.push(-3.2);
		myStackDoubles.push(-3.2);
		myStackDoubles.push(-3.2);
		myStackDoubles.push(-3.2);
		myStackDoubles.push(-3.2);
		myStackDoubles.push(-3.2);
		myStackDoubles.push(-3.2);
		myStackDoubles.push(-3.2);
		myStackDoubles.push(-3.2);
		myStackDoubles.push(-3.2);
		myStackDoubles.push(-3.2);
		myStackDoubles.push(-3.2);
		myStackDoubles.push(-3.2);
		myStackDoubles.push(-3.2);
		myStackDoubles.push(-3.2);
		myStackDoubles.push(-3.2);
		myStackDoubles.push(8.8);
		myStackDoubles.push(-3.2);

		for (Double val : myStackDoubles) {
			System.out.println(val);
		}
    }
}
