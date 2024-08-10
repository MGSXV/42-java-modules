
import java.util.Scanner;

class Program {

	private static int					studentCount = 0;
	private static int					classPerWeekCount = 0;
	private static final int			MAX_STUDENTS = 10;
	private static final int			MAX_CLASSES_PER_WEEK = 10;
	private static final int 			MAX_CLASSES_PER_MONTH = 50;
	private static final int			MAX_NAME_LENGTH = 10;
	private static final int			START_HOUR = 1;
	private static final int			END_HOUR = 6;
	private static final int			YEAR = 2020;
	private static final int			MONTH = 9;
	private static final String			SEP = ".";
	private static final String			HERE = "HERE";
	private static final String			NOT_HERE = "NOT_HERE";
	private static final String			illegalArgument = "IllegalArgument";
	private static final String[]		STUDENTS = new String[MAX_STUDENTS];
	private static final int[]			CLASSES = new int[MAX_CLASSES_PER_MONTH];
	private static final String[]		DAYS_OF_THE_WEEK = {
			"SA", "SU", "MO", "TU", "WE", "TH", "FR"
		};
	static {
		for (int i = 0; i < CLASSES.length; i++) {
			CLASSES[i] = -1;
		}
	}

	public static void main(String args[]) {
		short[][] attendence;
		int 	classesCount = 0;
		Scanner scanner = new Scanner(System.in);

		readStudents(scanner);
		readClasses(scanner);
		sortArray(CLASSES);
		classesCount = countClasses();
		attendence = readAttendences(scanner, classesCount);
		printSchedule(attendence);
		scanner.close();
	}

	private static void panic(String message) {
		System.err.println(message);
		System.exit(1);
	}

	private static int getDayOfTheMonth(int day, int month, int year) {
		int q = day;
		int m = month;
		if (m < 3) {
			m += 12;
			year--;
		}
		int k = year % 100;
		int j = year / 100;

		int h = (q + 13 * (m + 1) / 5 + k + k / 4 + j / 4 + 5 * j) % 7;
		return h;
	}

	private static int getDateOfTheDay(String day, int week, int month, int year) {
		int firstDay = getDayOfTheMonth(1, month, year);
		int dayOfWeekIndex = -1;
		int date = 0;

		for (int i = 0; i < DAYS_OF_THE_WEEK.length; i++) {
			if (DAYS_OF_THE_WEEK[i].equals(day)) {
				dayOfWeekIndex = i;
				break;
			}
		}
		if (dayOfWeekIndex == -1)
			panic(illegalArgument);
		date = 1 + (dayOfWeekIndex - firstDay + 7) % 7 + (week - 1) * 7;
		return date;
	}

	private static void saveStudent(String name) {
		if (studentCount == MAX_STUDENTS)
			panic(illegalArgument);
		if (!isValidName(name))
			panic(illegalArgument);
		STUDENTS[studentCount++] = name;
	}

	private static boolean isValidName(String name) {
		if (name.length() > MAX_NAME_LENGTH)
			return false;
		for (int i = 0; i < name.length(); i++) {
			if (name.toCharArray()[i] < 'A' || name.toCharArray()[i] > 'z')
				return false;
		}
		return true;
	}

	private static boolean isValidHour(int hour) {
		return hour >= START_HOUR && hour <= END_HOUR;
	}

	private static boolean isInArray(String[] array, String value) {
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(value))
				return true;
		}
		return false;
	}

	private static void sortArray(int[] array) {
		int temp = 0;

		for (int i = 0; i < array.length; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i] > array[j]) {
					temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
	}

	private static void readStudents(Scanner scanner) {
		String	name = "";
		int 	i = -1;

		while (++i < MAX_STUDENTS) {
			if (scanner.hasNextLine()) {
				name = scanner.nextLine();
				if (name.equals(SEP))
					break ;
				saveStudent(name);
			}
		}
	}

	private static boolean readClass(Scanner scanner) {
		int		hour = 0;
		String	day = "";

		if (classPerWeekCount >= MAX_CLASSES_PER_WEEK)
			panic(illegalArgument);
		if (scanner.hasNextInt())
			hour = scanner.nextInt();
		else {
			if (scanner.hasNext()) {
				day = scanner.next();
				if (!day.equals(SEP))
					panic(illegalArgument);
				else
					return true;
			}
		}
		if (!isValidHour(hour))
			panic(illegalArgument);
		if (scanner.hasNext()) {
			day = scanner.next();
			if (!isInArray(DAYS_OF_THE_WEEK, day))
				panic(illegalArgument);
			if (day.equals(DAYS_OF_THE_WEEK[0]) || day.equals(DAYS_OF_THE_WEEK[1]))
				panic(illegalArgument);
		}
		int[] dates = new int[]{ -1, -1, -1, -1, -1 };
		for (int i = 0; i < 5; i++) {
			int date  = getDateOfTheDay(day, i + 1, MONTH, YEAR);
			if (date > 30)
				break;
			dates[i] = date;
		}
		for (int i = 0; i < CLASSES.length; i++) {
			if (CLASSES[i] == -1) {
				for (int j = 0; j < dates.length; j++) {
					if (dates[j] != -1) {
						CLASSES[i++] = dates[j] * 100 + hour;
					}
				}
				break;
			}
		}
		return false;
	}

	private static void readClasses(Scanner scanner) {
		int		i = -1;
		boolean	end = false;

		while (++i < MAX_CLASSES_PER_WEEK && !end) {
			end = readClass(scanner);
			classPerWeekCount++;
		}
	}

	private static int countClasses() {
		int count = 0;

		for (int i = 0; i < CLASSES.length; i++) {
			if (CLASSES[i] != -1)
				count++;
		}
		return count;
	}

	private static void saveAttendence(String name, int hour, int day, String status, short[][] attendence, int classesCount) {
		int studentIndex = -1;
		int classIndex = -1;

		for (int i = 0; i < studentCount; i++) {
			if (STUDENTS[i].equals(name)) {
				studentIndex = i;
				break;
			}
		}
		int j = 0;

		for (int i = 0; i < CLASSES.length; i++) {
			if (CLASSES[i] == day * 100 + hour) {
				classIndex = i - j;
				break;
			} 
		}
		if (studentIndex == -1 || classIndex == -1)
			panic(illegalArgument);
		classIndex = classIndex + classesCount - MAX_CLASSES_PER_MONTH;
		if (status.equals(HERE))
			attendence[studentIndex][classIndex] = 1;
		else
			attendence[studentIndex][classIndex] = -1;
	}

	private static boolean readAttendence(Scanner scanner, short[][] attendence, int classesCount) {
		String	name = "";
		int		day = 0;
		int		hour = 0;
		String	status = "";

		if (scanner.hasNext()) {
			name = scanner.next();
			if (name.equals(SEP))
				return true;
			if (!isInArray(STUDENTS, name))
				panic(illegalArgument +  " 1");
		}
		if (scanner.hasNextInt())
			hour = scanner.nextInt();
		else
			panic(illegalArgument +  " 2");
		if (scanner.hasNextInt())
			day = scanner.nextInt();
		else
			panic(illegalArgument +  " 3");
		if (scanner.hasNext()) {
			status = scanner.next();
			if (!status.equals(HERE) && !status.equals(NOT_HERE))
				panic(illegalArgument +  " 4");
		}
		saveAttendence(name, hour, day, status, attendence, classesCount);
		return false;
	}

	private static short[][] readAttendences(Scanner scanner, int classesCount) {
		boolean		end = false;
		short[][] attendence = new short[studentCount][classesCount];

		for (int i = 0; i < studentCount; i++) {
			for (int j = 0; j < CLASSES.length; j++) {
				if (CLASSES[j] == -1)
					continue;
				end = readAttendence(scanner, attendence, classesCount);
				if (end)
					break;
			}
			if (end)
				break;
		}
		return attendence;
	}

	private static String decodeDate(int date) {
		int day = date / 100;
		int hour = date % 100;

		return hour + ":00 " + DAYS_OF_THE_WEEK[getDayOfTheMonth(day, MONTH, YEAR)] + " " + day;
	}

	private static void printDates() {
		int	i = 0;

		for (i = 0; i < MAX_NAME_LENGTH + 1; i++) {
			System.out.print(" ");
		}
		System.out.print("| ");
		for (i = 0; i < CLASSES.length; i++) {
			if (CLASSES[i] == -1)
				continue;
			String date = decodeDate(CLASSES[i]);
			if (date.length() < 12) {
				for (int j = 0; j < 12 - date.length(); j++) {
					System.out.print(" ");
				}
			}
			System.out.print(decodeDate(CLASSES[i])  + " | ");
		}
		System.out.println();
	}

	private static void printRow(short[] row, String name) {
		int i = 0;

		System.out.print(name);
		for (i = 0; i < MAX_NAME_LENGTH - name.length() + 1; i++)
			System.out.print(" ");
		System.out.print("| ");
		for (i = 0; i < row.length; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(" ");
			}
			if (row[i] == 1)
				System.out.print(" 1");
			else if (row[i] == -1)
				System.out.print("-1");
			else
				System.out.print("  ");
			System.out.print(" | ");
		}
		System.out.println();
	}

	private static void printSchedule(short[][] attendence) {
		printDates();
		for (int i = 0; i < attendence.length; i ++) {
			printRow(attendence[i], STUDENTS[i]);
		}
	}
}