import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		Menu menu = new Menu();
		Scanner scanner = new Scanner(System.in);
		UsersArrayList users = UsersArrayList.getInstance();
		int choice;
		while (true) {
			menu.viewMenu();
			System.out.print("-> ");
			if (!scanner.hasNextInt()) {
				System.out.println("Invalid choice");
				scanner.close();
				break ;
			}
			choice = scanner.nextInt();
			boolean res =  menu.execute(choice, scanner);
			if (!res)
				break;
		}
		System.out.println("<===== Users =====>");
		try {
			for (int i = 0; i < users.getUsersCount(); i++) {
				User user = users.getUserByIndex(i);
				System.out.println(user.print());
			}
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
		scanner.close();
	}
}
