import java.util.Scanner;
import java.util.UUID;

public class Menu {

	TransactionsService transactionsService;
	UsersArrayList users;
	EProfile profile;

	public Menu() {
		this.transactionsService = new TransactionsService();
		this.users = UsersArrayList.getInstance();
	}

	public void viewMenu() {
		String exitNumber = profile == EProfile.DEV ? "7" : "5";
		System.out.println("1. Add a user");
		System.out.println("2. View user balances");
		System.out.println("3. Perform a transfer");
		System.out.println("4. View all transactions for a specific user");
		System.out.println("5. DEV - remove a transfer by ID");
		System.out.println("6. DEV - check transfer validity");
		System.out.println(exitNumber + ". Exit");
	}

	public void setProfile(EProfile profile) {
		this.profile = profile;
	}

	private void addAUser(Scanner scanner) {
		System.out.print("Enter a user name and a balance: ");
		if (!scanner.hasNext()) {
			System.out.println("Invalid name");
			return;
		}
		String name = scanner.next();
		if (!scanner.hasNextDouble()) {
			System.out.println("Invalid balance");
			return;
		}
		double balance = scanner.nextDouble();
		User user = new User(name, balance);
		transactionsService.addUser(user);
		System.out.println("User with id = " + user.getID() + " is added");
	}

	private void viewUserBalances(Scanner scanner) {
		System.out.print("Enter a user ID: ");
		if (!scanner.hasNextLong()) {
			System.out.println("Invalid ID");
			return;
		}
		long id = scanner.nextLong();
		User user = transactionsService.getUserById(id);
		if (user == null) {
			System.out.println("User not found");
			return;
		}
		System.out.println(user.getName() + " - " + user.getBalance());
	}

	private void performTransaction(Scanner scanner) {
		System.out.print("Enter a sender ID, a recipient ID, and a transfer amount: ");
		if (!scanner.hasNextLong()) {
			System.out.println("Invalid ID");
			return;
		}
		long senderId = scanner.nextLong();
		if (!scanner.hasNextLong()) {
			System.out.println("Invalid ID");
			return;
		}
		long receiverId = scanner.nextLong();
		if (!scanner.hasNextDouble()) {
			System.out.println("Invalid amount");
			return;
		}
		double amount = scanner.nextDouble();
		try {
			transactionsService.performTransaction(senderId, receiverId, amount);
			System.out.println("Transaction is completed");
		} catch (TransactionsService.IllegalTransactionException e) {
			System.out.println(e.getMessage());
		}
	}

	private void viewAllTransactionsForASpecificUser(Scanner scanner) {
		System.out.print("Enter a user ID: ");
		if (!scanner.hasNextLong()) {
			System.out.println("Invalid ID");
			return;
		}
		long id = scanner.nextLong();
		Transaction[] transactions = transactionsService.getTransactionsByUser(id);
		for (int i = 0; i < transactions.length; i++) {
			if (transactions[i] != null) {
				System.out.println(transactions[i].print());
			}
		}
	}

	private void removeATransferByID(Scanner scanner) {
		System.out.print("Enter a transfer ID: ");
		if (!scanner.hasNext()) {
			System.out.println("Invalid ID");
			return;
		}
		String id = scanner.next();
		try {
			transactionsService.removeTransactionById((UUID.fromString(id)));
		} catch (Exception e) {
			System.out.println("Transaction not found");
		}
	}

	private void checkTransferValidity(Scanner scanner) {
		Transaction[] transactions = transactionsService.getInvalideTransactions();
		for (int i = 0; i < transactions.length; i++) {
			if (transactions[i] != null) {
				System.out.println(transactions[i].getReceiver().getName() + " (id = " + transactions[i].getSender().getID() +
					") has an unacknowledged transfer id = "+ transactions[i].getID() +
					" from " + transactions[i].getSender().getName() + " (id = " + transactions[i].getSender().getID() + ")");
			}
		}
	}

	public boolean execute(int choice, Scanner scanner) {
		
		if (choice == 1) {
			addAUser(scanner);
		} else if (choice == 2) {
			viewUserBalances(scanner);
		} else if (choice == 3) {
			performTransaction(scanner);
		} else if (choice == 4) {
			viewAllTransactionsForASpecificUser(scanner);
		} else if (choice == 5 && profile == EProfile.DEV) {
			removeATransferByID(scanner);
		} else if (choice == 6 && profile == EProfile.DEV) {
			checkTransferValidity(scanner);
		} else if (profile == EProfile.DEV &&  choice == 7 || profile == EProfile.PROD && choice == 5) {
			System.out.println("Exit");
			return false;
		} else {
			System.out.println("Invalid choice");
			return true;
		}
		return true;
	}

	public enum EProfile {
		DEV(1),
		PROD(2);

		private int value;

		EProfile(int value) {
			this.value = value;
		}
	}
}
