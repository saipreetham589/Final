import java.util.*;
public class StartAppMulti {
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
			String line = scan.nextLine();
			if (line.equals("exit")) break;
			List<PageRanking> pages = new ArrayList<>();
			Map<String, PageRanking> pMap = new HashMap<>();
			for (String word : line.split(" ")) {
				if (StopWord.is(word)) continue;
				for(int i = 0; i < links.length; i++)
				{
					Integer count = d.getData(links[i],word);
					if (count > 0) {
						PageRanking pr = pMap.get(links[i]);
						if (pr == null) {
							pMap.put(links[i], new PageRanking(links[i]));
							pr = pMap.get(links[i]);
							pages.add(pr);
						}
						pr.insertWord(word, count);
					}
				} 
			}
			pages.sort(new PageComparatorMulti());
			for (PageRanking page : pages) {
				System.out.println(page.getName() + " has " + page.getWords() + " " + page.getRank() + " times");
			}
		}
		scan.close();
		System.exit(0);
	}
}
