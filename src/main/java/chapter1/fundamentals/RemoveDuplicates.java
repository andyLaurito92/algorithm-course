package chapter1.fundamentals;

import java.util.List;
import java.util.Collections;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.ArrayList;

public class RemoveDuplicates {
   public static void main(String[] args) {
       System.out.println(String.format("res is: %s", removeDuplicates(new int[]{1, 2, 2, 3, 3, 4, 5, 6})));
       System.out.println(String.format("res is: %s", removeDuplicates(new int[]{1, 2, 2, 3, 3, 3, 3, 3})));
       System.out.println(String.format("res is: %s", removeDuplicates(new int[]{1})));
       System.out.println(String.format("res is: %s", removeDuplicates(new int[]{2, 2, 2, 2, 2, 2})));
       System.out.println(String.format("res is: %s", removeDuplicates(new int[]{1, 2, 3, 4, 5, 6, 7, 8})));


       System.out.println(String.format("res is: %s", anotherRemoveDuplicates(new int[]{1, 2, 2, 3, 3, 4, 5, 6})));
       System.out.println(String.format("res is: %s", anotherRemoveDuplicates(new int[]{1, 2, 2, 3, 3, 3, 3, 3})));
       System.out.println(String.format("res is: %s", anotherRemoveDuplicates(new int[]{1})));
       System.out.println(String.format("res is: %s", anotherRemoveDuplicates(new int[]{2, 2, 2, 2, 2, 2})));
       System.out.println(String.format("res is: %s", anotherRemoveDuplicates(new int[]{1, 2, 3, 4, 5, 6, 7, 8})));
   }

    /** This one works in O(N) **/
    public static Collection<Integer>anotherRemoveDuplicates(int[] array) {
	if (array.length < 1) {
	    return Collections.emptyList();
	} else if ( array.length == 1) {
	    return List.of(array[0]);
	}
	
	int uniques = 0;
	for (int j = 0; j < array.length - 1; j++) { // j < 1
	    if (array[j] != array[j+1]) { // 1 2
		array[uniques] = array[j]; // uniques = 1, j = 1, 1 != 2
		uniques++;
	    } 
	}

	if (uniques != 0 && array[uniques-1] != array[array.length - 1]) { // uniques == 1, 
	    array[uniques++] = array[array.length-1];
	}
	
	// All elements were equal to the first one!
	if (uniques == 0) {
	    array[uniques++] = array[0];
	}

	Integer[] res = new Integer[uniques];
	for (int j = 0; j < uniques; j ++) {
	    res[j] = array[j];
	}
	return Arrays.asList(res);
    }
    
   public static Collection<Integer> removeDuplicates(int[] array) {
       for (int j = 0; j < array.length; j++) {
	   if (array[j] != Integer.MAX_VALUE) {
	       removeDuplicates(array[j], array);
	   }
       }
       int finalLength = 0;
       for (int j = 0; j < array.length; j++) {
	   if (array[j] != Integer.MAX_VALUE) {
	       finalLength ++;
	   }
       }
       Integer[] res = new Integer[finalLength];
       int current = 0;
       for (int j = 0; j < array.length; j++) {
	   if (array[j] != Integer.MAX_VALUE) {
	       res[current] = array[j];
	       current++;
	   }
       }
       return Arrays.asList(res);
   }

    // This method works in O(lg(N) + k) where k is usually a constant. If it's not,
    // then it implies that we had a lot of duplicates which implies that there is no
    // way around and we need to go over the entire array. In this case, the algorithm
    // becomes O(N)
   public static void removeDuplicates(int key, int[] array) {
       // We first find the elem in the array
       int lo = 0;
       int hi = array.length;
       int med = lo + (hi - lo)/2;
       while (lo <= hi) {
	   med = lo + (hi - lo)/2;
	   if (array[med] == key) break;
	   if (array[med] > key) hi = med - 1; 
	   if (array[med] < key) lo = med + 1;
       }

       // We remove all keys from med - 1 to first occurence
       int current = med - 1;
       while (current > -1) {
	   if (array[current] == key) {
	       array[current] = Integer.MAX_VALUE;
	       current --;
	   } else {
	       // We are at the first occurrence!
	       break;
	   }
       }

       // We remove all keys from med + 1 to last occurence
       current = med + 1;
       while (current < array.length) {
	  if (array[current] == key) {
	       array[current] = Integer.MAX_VALUE;
	       current ++;
	   } else {
	       // We are at the last occurrence!
	       break;
	  }
       }
     }

    public static String print(int[] array) {
	StringBuilder str = new StringBuilder();
	Arrays.stream(array).forEach(i -> {
		str.append(i);
		str.append(" ");
		    });
	return str.toString();
    }
}
