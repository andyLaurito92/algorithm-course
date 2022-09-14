/** Client that uses the priority queue implementation to grab the greatest M values. 
 ** Assuming that the stream to read is to high and we cannot store them, we can use the
 ** MinPQ to grab the first M elements and always delete the minimum of the current store 
 ** elements
 **/
import java.util.Scanner;
import java.util.Stack;

public class TopM {
    public static void main(String[] args) {
	int M = Integer.parseInt(args[0]);
	MinPQ<Integer> pq = new MinPQArray<Integer>(M+1);
	Scanner myScanner = new Scanner(System.in);
	while (myScanner.hasNextLine()) {
	    pq.insert(Integer.parseInt(myScanner.nextLine()));
	    if (pq.size() > M) {
		pq.delMin();
	    }
	}
	Stack<Integer> stack = new Stack<>();
	while (!pq.isEmpty()) stack.push(pq.delMin());
	for (Integer s : stack) System.out.println(s);
    }
}
