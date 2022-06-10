import java.util.Comparator;

public class Comparators {
    public static void main(String[] args) {
	Student[] algebraClass = new Student[]{
	    new Student("Andres", 28),
	    new Student("Gabriela", 32),
	    new Student("Mila", 20)
	};

	// Integer[] myArray = new Integer[]{3, -4, -12, 8, 33, 7, 3, 1, 1, 2, -24};
	// Shell.sort(myArray, Comparator.naturalOrder());
	// System.out.println(print(myArray));

	//Shell.sort(algebraClass, Student.ORDER_BY_NAME);
	Shell.sort(algebraClass, Student.ORDER_BY_AGE);
	System.out.println(print(algebraClass));
    }

    static String print(Object[] array) {
	StringBuilder str = new StringBuilder();
	for (Object val : array) {
	    str.append(val);
	    str.append(" ");
	}
	return str.toString();
    }

    static class Shell {
	public static <Item> void sort(Item[] array, Comparator<Item> comparator) {
	    int h = 1;
	    while (h < array.length) h = 3*h + 1;
	    
	    while (h >= 1) {
		for (int j = h; j < array.length; j++) {
		    int i = j;
		    Item newElement = array[i];
		    while (i - h >= 0 && less(comparator, newElement, array[i - h])) {
			swap(array, i, i - h);
			i -= h;
		    }
		}
		h = h/3;
	    }
	}

	public static <Item> boolean less(Comparator<Item> comparator, Item a, Item b) {
	    return comparator.compare(a, b) < 0;
	}

	public static <Item> void swap(Item[] array, int i, int j) {
	    Item temp = array[i];
	    array[i] = array[j];
	    array[j] = temp;
	}
    }

    static class Student {
	public static final Comparator<Student> ORDER_BY_NAME = new Comparator<Student>() {
		public int compare(Student first, Student second) {
		    return first.name.compareTo(second.name);
		}
	    };
	public static final Comparator<Student> ORDER_BY_AGE = new Comparator<Student>() {
		public int compare(Student first, Student second) {
		    return first.age.compareTo(second.age);
		}
	    };
	public String name;
	public Integer age;

	Student(String name, int age) {
	    this.name = name;
	    this.age = age;
	}

	public String toString() {
	    return String.format("My name is: %s and I have %d \n", name, age);
	}
				  
    }
}
