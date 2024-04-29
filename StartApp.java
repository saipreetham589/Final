import java.util.*;
public class StartApp {
	public static void main(String[] args) {
		TreeMap<String, Integer> frequencyData = new TreeMap<String, Integer>();
		String[] links = {"https://www.w3schools.com/java/java_scope.asp",
				"https://www.w3schools.com/java/java_classes.asp",
				"https://www.w3schools.com/java/java_constructors.asp",
				"https://www.w3schools.com/java/java_ref_arrays.asp",
				"https://www.w3schools.com/java/java_ref_math.asp",
				"https://www.w3schools.com/java/java_lambda.asp"};
		DataReceived d = new DataReceived();
		for(String link : links)
		{
			d.getData(link,frequencyData);
		}
		PageRanking.printAllCounts(frequencyData);
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.println("Enter the word you want to search : ");
			String word = scan.next();
			if (word.equals("exit")) break;
			List<PageRanking> pages = new ArrayList<>();
			for(int i = 0; i < links.length; i++)
			{
				Integer count = d.getData(links[i],word);
				if (count > 0) {
					pages.add(new PageRanking(links[i], count));
				}
			}
			pages.sort(new PageComparator());
			for (PageRanking page : pages) {
				System.out.println(page.getName() + " has " + word + " " + page.getRank() + " times");
			}
		}
		scan.close();
		System.exit(0);
	}
}
