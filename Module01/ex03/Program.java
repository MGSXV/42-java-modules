public class Program {
	public static void main(String[] args) {
		TransactionsLinkedList transactions = TransactionsLinkedList.getInstance();
		User user1 = new User("John", 1000);
		User user2 = new User("Jane", 2000);
		Transaction transaction1 = new Transaction(user1, user2, 100, Transaction.ETransitionCategory.DEBIT);
		Transaction transaction2 = new Transaction(user2, user1, 200, Transaction.ETransitionCategory.CREDIT);
		Transaction transaction3 = new Transaction(user1, user2, 300, Transaction.ETransitionCategory.DEBIT);
		Transaction transaction4 = new Transaction(user2, user1, 400, Transaction.ETransitionCategory.CREDIT);
		Transaction transaction5 = new Transaction(user1, user2, 500, Transaction.ETransitionCategory.DEBIT);
		Transaction transaction6 = new Transaction(user2, user1, 600, Transaction.ETransitionCategory.CREDIT);
		transactions.addTransaction(transaction1);
		transactions.addTransaction(transaction2);
		transactions.addTransaction(transaction3);
		transactions.addTransaction(transaction4);
		transactions.addTransaction(transaction5);
		transactions.addTransaction(transaction6);
		Transaction[] transactionsArray = transactions.toArray();
		for (Transaction transaction : transactionsArray) {
			System.out.println(transaction.print());
		}
		System.out.println("<============>");
		transactions.removeTransactionById(transaction1.getID());
		transactions.removeTransactionById(transaction2.getID());
		transactions.removeTransactionById(transaction3.getID());
		transactionsArray = transactions.toArray();
		for (Transaction transaction : transactionsArray) {
			System.out.println(transaction.print());
		}
		System.out.println("<============>");
		try {
			transactions.removeTransactionById(transaction1.getID());
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
}
