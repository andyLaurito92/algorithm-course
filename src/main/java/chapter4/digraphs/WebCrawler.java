package chapter4.digraphs;

import java.net.URL;
import java.net.URLConnection;

import java.io.InputStream;
import java.io.BufferedInputStream;

import java.util.Scanner;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayDeque;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class WebCrawler {
    private static final Pattern EVERYTHING_PATTERN = Pattern.compile("\\A");
    
    public static void main(String[] args) throws Exception {
		Queue<String> queue = new ArrayDeque<String>();
		Set<String> discovered = new HashSet<String>();

		String root = "https://www.dc.uba.ar/";
		queue.add(root);
		discovered.add(root);

		String regex = "http://(\\w+\\.)*(\\w+)";
		Pattern pattern = Pattern.compile(regex);


		while (!queue.isEmpty()) {
			String currentPage = queue.remove();
			print(String.format("Reading %s", currentPage));
			String webContent = readPage(currentPage);
			//print(String.format("Web content is %s", webContent));

			Matcher matcher = pattern.matcher(webContent);
			while (matcher.find()) {
			String anotherLink = matcher.group();
			print(String.format("Found %s", anotherLink));
			if (!discovered.contains(anotherLink)) {
				discovered.add(anotherLink);
				queue.add(anotherLink);
			}
			}
		}
    }

    private static String readPage(String urlName) throws Exception {
		URL url = new URL(urlName);
		URLConnection site = url.openConnection();
		InputStream is = site.getInputStream();
		Scanner scanner = new Scanner(new BufferedInputStream(is));
		if (!scanner.hasNextLine()) return "";

		String everything = scanner.useDelimiter(EVERYTHING_PATTERN).next();
		return everything;
    }

    private static void print(String str) {
		System.out.println(str);
    }
}
