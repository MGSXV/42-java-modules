public class Program {
	public static void main(String[] args) {
		TransactionsService transactions = new TransactionsService();
		User user1 = new User("John", 1000);
		User user2 = new User("Jane", 2000);
		transactions.addUser(user1);
		transactions.addUser(user2);
		try {
			transactions.performTransaction(user1.getID(), user2.getID(), 500);
		} catch (TransactionsService.IllegalTransactionException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(user1.print());
		System.out.println(user2.print());
		System.out.println("<====================>");
		try {
			transactions.performTransaction(user1.getID(), user2.getID(), 1500);
		} catch (TransactionsService.IllegalTransactionException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(user1.print());
		System.out.println(user2.print());
		System.out.println("<====================>");
	}
}
