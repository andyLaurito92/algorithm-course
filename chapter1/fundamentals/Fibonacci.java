public class Fibonacci {
    public static long F(int N) {
	if (N==0) {
	    return 0;
	}
	if (N==1) {
	    return 1;
	}
	return F(N-1) + F(N-2);
    }

    public static long optimized(int N) {
	if (N == 0) {
	    return 0;
	}

	if (N == 1) {
	    return 1;
	}

	long[] computed = new long[N+1];
	for (int i = 0; i < computed.length; i++){
	    computed[i] = -1;
	}
	computed[0] = 0;
	computed[1] = 1;
	return recursiveFibonnaci(N, computed);
    }

    public static long recursiveFibonnaci(int N, long[] computed) {
	if (computed[N] != -1) {
	    System.out.println(String.format("%d was precomputed! Value is: %d", N, computed[N]));
	} else {
	    long first = recursiveFibonnaci(N-1, computed);
	    long second = recursiveFibonnaci(N-2, computed);
	    computed[N] = first + second;
	}
	return computed[N];
    }

    public static void main(String[] args) {
	for (int n = 0; n < 100; n++) {
	    long startTime = System.currentTimeMillis();
	    long res = optimized(n);
	    long endTime = System.currentTimeMillis();
	    System.out.println(String.format("%d : %d, took %d m", n, res, (endTime - startTime)/60000));
	}
    }
}
