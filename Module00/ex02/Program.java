import java.util.Scanner;

class Program {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int	i = 0;

		while (true) {
			System.out.print("-> ");
			int	sum = 0;
			int	number = scanner.nextInt();
			if (number == 42)
				break ;
			sum = sumOfDigits(number);
			if (isPrime(sum))
				i++;

		}
		System.out.println("Count of coffee-request : " + i);
		scanner.close();
	}

	public static int sumOfDigits(int number)
	{
		if (number == 0)
			return (0);
		else
			return (number % 10 + sumOfDigits(number / 10));
	}

	public static boolean isPrime(int number) {
		long i = 2;
		while (i * i <= number) {
			if (number % i == 0) {
				return (false);
			}
			i++;
		}
		return (true);
	}
}
