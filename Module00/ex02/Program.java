import java.util.Scanner;

class Program {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int number = -1;
		int i = 0;

		while (scanner.hasNextInt() && number != 42) {
			number = scanner.nextInt();
			if (number == 42 || number < 2)
				break ;
			if (isPrime(sumOfDigits(number)))
				i++;
		}
		if (number != 42) {
			System.out.println("illegalArgument");
			scanner.close();
			return ;
		}
		System.out.print("Count of coffee-request : ");
		System.out.println(i);
		scanner.close();
	}

	private static int sumOfDigits(int number) {
		if (number == 0) {
			return number;
		}
		return number % 10 + sumOfDigits(number / 10);
	}

	private static boolean isPrime(int number) {
		int i = 2;

		if (number <= 1)
			return false;
		while (i * i < number) {
			if (number % i == 0)
				return false;
			i++;
		}
		return true;
	}
}