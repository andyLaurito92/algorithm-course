package libraries;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.IllegalArgumentException;

public class MyReader {
    public static int[] readIntegersFrom(String fileName) throws FileNotFoundException, IOException {
		if (fileName == null || fileName.isEmpty()) {
			throw new IllegalArgumentException(String.format("Wrong argument received. Expected file name, got %s", fileName));
		}

		File myFile = new File(fileName);
		int numNumbers = countLines(myFile);
		Scanner my_scanner = new Scanner(myFile);

		int[] res = new int[numNumbers];
		int current = 0;
		int index = 0;
		while (my_scanner.hasNextLine()) {
			current = Integer.parseInt(my_scanner.nextLine());
			res[index] = current;
			index++;
		}
		return res;
    }

    public static int countLines(File aFile) throws IOException {
		FileInputStream stream = new FileInputStream(aFile);
		byte[] buffer = new byte[8192];
		int count = 0;
		int n;
		while ((n = stream.read(buffer)) > 0) {
			for (int i = 0; i < n; i++) {
			if (buffer[i] == '\n') count++;
			}
		}
		stream.close();
		System.out.println("Number of lines: " + count);
		return count;
    }
}
