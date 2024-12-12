public class Program {
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: java Program <file1> <file2>");
			return;
		}

		FrequencyOfOccurrence fo = new FrequencyOfOccurrence(args[0], args[1]);
		System.out.println("Similarity = " + Math.floor(fo.calculateSimilarity() * 100) / 100);
	}
}
