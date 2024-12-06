
import java.util.Scanner;


class Program {
	public static void main(String args[]) {
		int	number;
		int	i;

		number = readNumber();
		if (number <= 1) {
			System.err.println("IllegalArgument");
			System.exit(-1);
		}
		i = 2;
		while (i * i <= number) {
			if (number % i == 0) {
				i--;
				System.out.print("false ");
				System.out.println(i);
				return ;
			}
			i++;
		}
		i--;
		System.out.print("true ");
		System.out.println(i);
	}

	private static int readNumber() {
		int	number;
		Scanner scanner = new Scanner(System.in);

		if (!scanner.hasNextInt())
			number = -1;
		else
			number = scanner.nextInt();
		scanner.close();
		return number;
	}
}