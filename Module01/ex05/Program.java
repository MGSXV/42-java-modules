import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java Program --profile=[dev|prod]");
			return ;
		}
		Menu.EProfile profile = getProfile(args[0]);
		if (profile == null) {
			System.out.println("Usage: java Program --profile=[dev|prod]");
			return ;
		}
		Menu menu = new Menu();
		menu.setProfile(profile);
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

	private static Menu.EProfile getProfile(String arg) {
		if (arg.equals("--profile=dev")) {
			return Menu.EProfile.DEV;
		} else if (arg.equals("--profile=prod")) {
			return Menu.EProfile.PROD;
		}
		return null;
	}
}
