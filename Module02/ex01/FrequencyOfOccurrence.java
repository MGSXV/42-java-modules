import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class FrequencyOfOccurrence {

	private String file1;
	private String file2;
	private HashMap<String, Integer> frequency1;
	private HashMap<String, Integer> frequency2;
	private HashMap<String, Integer> allWords;

	public FrequencyOfOccurrence(String file1, String file2) {
		this.file1 = file1;
		this.file2 = file2;
		this.frequency1 = new HashMap<>();
		this.frequency2 = new HashMap<>();
		this.allWords = new HashMap<>();
	}

	public void parseFile(String fileName, HashMap<String, Integer> frequency) throws Exception {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] words = line.split(" ");
				for (String word : words) {
					frequency.put(word, frequency.getOrDefault(word, 0) + 1);
					allWords.put(word, allWords.getOrDefault(word, 0) + 1);
				}
			}
		} catch (Exception e) {
			throw new Exception("Error reading file: " + e.getMessage());
		}
	}

	public double calculateSimilarity() {
		try {
			parseFile(file1, frequency1);
			parseFile(file2, frequency2);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(-1);
		}

		double similarity = 0;
		double numerator = 0;
		double denominator = 0;
		double a = 0;
		double b = 0;
		for (String word : allWords.keySet()) {
			int freq1 = frequency1.getOrDefault(word, 0);
			int freq2 = frequency2.getOrDefault(word, 0);
			numerator += freq1 * freq2;
			a += freq1 * freq1;
			b += freq2 * freq2;
		}
		denominator = Math.sqrt(a) * Math.sqrt(b);
		similarity = numerator / denominator;
		return similarity;
	}

}
