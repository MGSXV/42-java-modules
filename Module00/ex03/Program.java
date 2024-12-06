
import java.util.Scanner;



public class Program {
	private static final int MAX_WEEKS = 18;
	private static final int MAX_GRADES_PER_WEEK = 5;
	private static final String EOF = "42";
	private static final String PREFIX = "Week";
	private static long grades = 0L;
	private static int weeks = 0;
	private static String input = "";


	public static void main(String[] args) {
		int		i;
		int 	weeks;
		Scanner scanner;

		i = -1;
		scanner = new Scanner(System.in);
		while (++i < Program.MAX_WEEKS) {
			Program.readWeek(scanner);
			if (Program.input.equals(Program.EOF))
				break ;
			Program.readGrades(scanner);
		}
		i = 0;
		weeks = Program.weeks;
		while (i++ < Program.weeks) {
			weeks--;
			Program.printData(i, Program.getGrade(weeks));
		}
		scanner.close();
	}

	private static void printData(int week, int data) {
		System.out.print("Week ");
		System.out.print(week);
		while (data != 0) {
			System.out.print("=");
			data--;
		}
		System.out.println(">");
	}

	private static void panic() {
		System.err.println("illegalArgument");
		System.exit(-1);
	}

	private static void readWeek(Scanner scanner) {
		int week = 0;
		if (scanner.hasNextLine()) {
			Program.input = scanner.next();
			if (Program.input.equals(Program.EOF))
				return ;
			if (!Program.input.equals(Program.PREFIX))
				Program.panic();
			if (scanner.hasNextInt()) // TODO: HEHE
				week = scanner.nextInt();
			else
				Program.panic();
			if (week != Program.weeks + 1)
				Program.panic();
			Program.weeks++;
		}
	}

	private static void readGrades(Scanner scanner) {
		int	i;
		int	grade;
		int	min;

		i = -1;
		min = 10;
		while (++i < Program.MAX_GRADES_PER_WEEK) {
			if (scanner.hasNextInt()) {
				grade = scanner.nextInt();
				if (grade < 1 || grade > 9)
					Program.panic();
				min = (grade < min) ? grade : min;
			} else {
				Program.panic();
			}
		}
		if (i != Program.MAX_GRADES_PER_WEEK)
			Program.panic();
		Program.storeGrade(min);
	}

	private static void storeGrade(int grade) {
		Program.grades = (Program.grades * 10) + grade;
	}

	private static int getGrade(int index) {
		int	divisor;

		divisor = 1;
		while (index-- > 0)
			divisor *= 10;
		return (int) (Program.grades % (divisor * 10) / divisor);
	}
}
