import java.util.UUID;

public class TransactionsService {
	UsersArrayList users = UsersArrayList.getInstance();
	TransactionsLinkedList transactions = TransactionsLinkedList.getInstance();

	public void addUser(User user) {
		users.addUser(user);
	}

	public User getUserById(long id) {
		return users.getUserById(id);
	}

	public double getUserBalance(long id) {
		User user = getUserById(id);
		if (user == null) {
			return 0;
		}
		return user.getBalance();
	}

	public void performTransaction(long senderId, long receiverId, double amount) throws IllegalTransactionException {
			TransactionsLinkedList transactions = TransactionsLinkedList.getInstance();
			User sender = getUserById(senderId);
			User receiver = getUserById(receiverId);
			if (sender == null || receiver == null) {
				throw new IllegalTransactionException("Illegal transaction");
			}
			UUID id = UUID.randomUUID();
			Transaction debit = new Transaction(id, sender, receiver, amount, Transaction.ETransitionCategory.DEBIT);
			Transaction credit = new Transaction(id, sender, receiver, amount, Transaction.ETransitionCategory.CREDIT);
			if (debit.execute()) {
				transactions.addTransaction(debit);
				transactions.addTransaction(credit);
			} else {
				throw new IllegalTransactionException("Illegal transaction");
		}
	}

	public Transaction[] getTransactionsByUser(long id) {
		Transaction[] allTransactions = transactions.toArray();
		Transaction[] userTransactions = new Transaction[allTransactions.length];
		int j = 0;
		for (int i = 0; i < allTransactions.length; i++) {
			if (allTransactions[i].getSender().getID() == id || allTransactions[i].getReceiver().getID() == id) {
				userTransactions[j] = allTransactions[i];
				j++;
			}
		}
		return userTransactions;
	}

	public Transaction[] getInvalideTransactions() {
		Transaction[] allTransactions = transactions.toArray();
		Transaction[] invalidTransactions = new Transaction[allTransactions.length];
		int j = 0;
		for (int i = 0; i < allTransactions.length; i++) {
			if (!isValideTransaction(allTransactions[i])) {
				invalidTransactions[j] = allTransactions[i];
				j++;
			}
		}
		return invalidTransactions;
	}

	public boolean isValideTransaction(Transaction transaction) {
		Transaction[] transictions = transactions.toArray();
		Transaction.ETransitionCategory category = transaction.getCategory() == Transaction.ETransitionCategory.DEBIT ? Transaction.ETransitionCategory.CREDIT : Transaction.ETransitionCategory.DEBIT;
		for (int i = 0; i < transictions.length; i++) {
			if ((transictions[i].getSender().getID() == transaction.getSender().getID()
				|| transictions[i].getReceiver().getID() == transaction.getReceiver().getID())
				&& transictions[i].getCategory() == category) {
				return true;
			}
		}
		return false;
	}

	public class IllegalTransactionException extends Exception {
		public IllegalTransactionException(String message) {
			super(message);
		}
	}
}
