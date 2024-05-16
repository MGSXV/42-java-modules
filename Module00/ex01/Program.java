import java.util.Scanner;

class Main {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int	number = -1;
		boolean	isValid = false;
		while (!isValid) {
			System.out.print("-> ");
			if (scanner.hasNextInt()) {
				number = scanner.nextInt();
				isValid = true;
			} else {
				System.out.println("IllegalArgument");
				scanner.next();
			}
		}
		if (number < 2)
			System.out.println("IllegalArgument");
		else {
			long i = 2;
			while (i * i <= number) {
				if (number % i == 0) {
					System.out.println("false " + (i - 1));
					break;
				}
				i++;
			}
			if (i * i > number)
				System.out.println("true " + (i - 1));
		}
		scanner.close();
	}
}