import java.util.Scanner;

public class Program {

	private static final int MAX_NUMBERS_TO_SHOW = 10;
	private static final int MAX_UNICODE = 65535;
	private static final int MAX_CHAR_OCCURRENCES = 999;

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine();
		char[] strArray = str.toCharArray();
		int[] charOccurrences = Program.countOccurrences(strArray);
		int[][] charFrequency = Program.getOnlyOccuredCharcters(charOccurrences);
		Program.sortByOccurences(charFrequency);
		// Program.printOccurrencesChars(charFrequency);
		Program.printGraph(charFrequency, Program.MAX_NUMBERS_TO_SHOW + 1);
		scanner.close();
	}

	private static int[] countOccurrences(char[] strArray) {
		int[] charOccurrences = new int[MAX_UNICODE];
		for (char c : strArray) {
			charOccurrences[c]++;
			if (charOccurrences[c] > Program.MAX_CHAR_OCCURRENCES)
				charOccurrences[c] = Program.MAX_CHAR_OCCURRENCES;
		}
		return charOccurrences;
	}

	private static int[][] getOnlyOccuredCharcters(int[] charOccurrences) {
		int[][]	charFrequency = new int[MAX_UNICODE][2];
		for (int i = 0, j = 0; i < MAX_UNICODE; i++) {
			if (charOccurrences[i] > 0) {
				charFrequency[j][0] = i;
				charFrequency[j][1] = charOccurrences[i];
				j++;
			}
		}
		return charFrequency;
	}

	private static void sortByOccurences(int[][] charFrequency) {
		int	max = 0;
		for (int i = 0; i < MAX_UNICODE; i++) {
			if (charFrequency[i][1] == 0) {
				max = i;
				break;
			}
		}
		for (int i = 0; i < max; i++) {
			for (int j = 0; j < max - i - 1; j++) {
				if (charFrequency[j][1] < charFrequency[j + 1][1]) {
					int temp = charFrequency[j][1];
					charFrequency[j][1] = charFrequency[j + 1][1];
					charFrequency[j + 1][1] = temp;
					temp = charFrequency[j][0];
					charFrequency[j][0] = charFrequency[j + 1][0];
					charFrequency[j + 1][0] = temp;
				}
			}
		}
	}

	private static void printOccurrencesChars(int[][] charFrequency) {
		for (int i = 0; i < MAX_NUMBERS_TO_SHOW; i++) {
			if (charFrequency[i][1] == 0)
				break;
			System.out.println((char) charFrequency[i][0] + ": " + charFrequency[i][1]);
		}
	}

	private static void printGraph(int[][] arr, int level) {
		for (int i = 0; arr[i] != null && i < Program.MAX_NUMBERS_TO_SHOW; i++) {
			if (arr[i][1] == 0)
				break;
			if (Program.getScale(arr[0][1], arr[i][1]) == level - 1)
				Program.styleNum(arr[i][1]);
			else if (Program.getScale(arr[0][1], arr[i][1]) < level && level > 0)
				Program.styleChar(' ', arr[i][1]);
			else if (level == 0)
				Program.styleChar((char) arr[i][0], arr[i][1]);
			else
				Program.styleChar('#', arr[i][1]);
		}
		System.out.println();
		if (level > 0)
			Program.printGraph(arr, level - 1);
	}

	private static int getScale(int ref, int occurence) {
		return occurence * Program.MAX_NUMBERS_TO_SHOW / ref;
	}
	
	private static void styleNum(int text) {
		if (text < 10)
			System.out.print("  " + text + "  ");
		else if (text >= 10 && text < 100) {
			System.out.print(" " + text + " ");
		}
		else
			System.out.print(" " + text);
	}

	private static void styleChar(char text, int num) {
		if (num < 10)
			System.out.print("  " + text + "  ");
		else if (num >= 10 && text < 100) {
			System.out.print(" " + text + " ");
		}
		else
		System.out.print(" " + text);
	}

	// public static void main(String args[]) {
	// 	int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	// 	int a = 0;
	// 	System.err.println("Before editing:");
	// 	Program.printArray(arr);
	// 	Program.editArray(arr);
	// 	System.err.println("After editing:");
	// 	Program.printArray(arr);
	// 	System.out.println("---------------------------------");
	// 	System.err.println("Before testing:");
	// 	System.out.println(a);
	// 	Program.test(a);
	// 	System.err.println("After testing:");
	// 	System.out.println(a);
	// }

	// private static void printArray(int[] arr) {
	// 	for (int i = 0; i < arr.length; i++) {
	// 		System.out.println(arr[i]);
	// 	}
	// }

	// private static void test(int a) {
	// 	a = 10;
	// }

	// private static void editArray(int[] arr) {
	// 	for (int i = 0; i < arr.length; i++) {
	// 		arr[i] = arr[i] * 2;
	// 	}
	// }
}
