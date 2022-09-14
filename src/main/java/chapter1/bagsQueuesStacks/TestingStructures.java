package chapter1.bagsQueuesStacks;

import chapter1.bagsQueuesStacks.MyLinkedList;

public class TestingStructures {
    public static void main(String[] args) {
		// MyStack<String> myStack = new ArrayStack<String>();
		// myStack.push("hola");
		// myStack.push("que tal");

		// System.out.println(myStack.pop());
		// System.out.println(myStack.pop());

		// MyLinkedList<String> linkedList = new MyLinkedList<String>();
		// linkedList.add("Hola");
		// linkedList.add("Que");
		// linkedList.add("Tul");
		// linkedList.add("Cuarto");
		// linkedList.add("Quinto");

		// System.out.println(String.format("Found %b", linkedList.find("Hey")));
		// System.out.println(String.format("Found %b", linkedList.find("Que")));
		// System.out.println(String.format("Found %b", linkedList.find("Cuarto")));
		// System.out.println(String.format("Found %b", linkedList.find("Hola")));
		// System.out.println(String.format("Found %b", linkedList.find("Quinto")));

		// linkedList.removeNodeAfter("Quinto");
		// printList(linkedList);

		// linkedList.insertAfter("Tul", "new element");
		// printList(linkedList);

		MyQueueCircular<String> myQueueCircular = new MyQueueCircular();
		myQueueCircular.enqueue("First");
		myQueueCircular.enqueue("Second");
		myQueueCircular.enqueue("Third");

		System.out.println(myQueueCircular.toString());

		System.out.println(myQueueCircular.dequeue());
		System.out.println(myQueueCircular.dequeue());
		myQueueCircular.enqueue("Fourth");
		myQueueCircular.enqueue("Fifth");
		myQueueCircular.enqueue("Sixth");
		System.out.println(myQueueCircular.toString());

		for (String item : myQueueCircular) {
			System.out.println(item);
		}

		System.out.println(myQueueCircular.dequeue());
		System.out.println(myQueueCircular.dequeue());

		// linkedList.removeKth(2);
		// printList(linkedList);

		// linkedList.removeKth(3);
		// printList(linkedList);

		// linkedList.removeKth(1);
		// printList(linkedList);

		// System.out.println("First");
		// printList(linkedList);

		// linkedList.removeLastElem();
		// printList(linkedList);

		// linkedList.removeLastElem();
		// printList(linkedList);

		// linkedList.removeLastElem();
		// System.out.println("List 1");
		// printList(linkedList);

		// linkedList.removeLastElem();
		// System.out.println("List 2");
		// printList(linkedList);

		// linkedList.removeLastElem();
		// System.out.println("List 3");
		// printList(linkedList);

		// linkedList.remove("no existo, je");
		// linkedList.remove("Que");

		// System.out.println("Second");
		// printList(linkedList);

		// linkedList.remove("Hola");

		// System.out.println("Third");
		// printList(linkedList);

		// MyStackList<String> anotherStack = new MyStackList<String>();
		// anotherStack.push("Hey man");
		// anotherStack.push("second");
		// anotherStack.push("third");

		// System.out.println("My new stack");
		// printCollection(anotherStack);

		// System.out.println(anotherStack.pop());
		// printCollection(anotherStack);


		// MyQueue<Integer> myQueue = new MyQueueList<>();
		// myQueue.enqueue(3);
		// myQueue.enqueue(8);
		// myQueue.enqueue(10);
		// myQueue.enqueue(-3);
		// printCollection(myQueue);

		// System.out.println(myQueue.dequeue());
		// System.out.println(myQueue.dequeue());
		// System.out.println(myQueue.dequeue());
		// System.out.println(myQueue.dequeue());
		// System.out.println(myQueue.dequeue());
		// System.out.println(myQueue.dequeue());
    }

    public static <Item> void printCollection(Iterable<Item> stack) {
		for (Item val : stack) {
			System.out.println(val);
		}
    }

    public static void printList(MyLinkedList<String> list) {
		for (String val : list) {
			System.out.println(val);
		}
    }
}
