import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
	private static TransactionsLinkedList instance;
	private Node head;
	private int size;

	private TransactionsLinkedList() {
		this.head = null;
		this.size = 0;
	}

	public static TransactionsLinkedList getInstance() {
		if (instance == null) {
			instance = new TransactionsLinkedList();
		}
		return instance;
	}

	@Override
	public void addTransaction(Transaction transaction) {
		Node newNode = new Node(transaction);
		newNode.next = this.head;
		this.head = newNode;
		this.size++;
	}

	@Override
	public boolean removeTransactionById(UUID id) {
		Node current = this.head;
		Node previous = null;

		while (current != null) {
			if (current.transaction.getID().equals(id)) {
				if (previous != null) {
					previous.next = current.next;
				} else {
					this.head = current.next;
				}
				this.size--;
				return true;
			}
			previous = current;
			current = current.next;
		}
		throw new TransactionNotFoundException("Transaction not found");
	}

	@Override
	public Transaction[] toArray() {
		Transaction[] transactions = new Transaction[this.size];
		Node current = this.head;
		int i = 0;

		while (current != null) {
			transactions[i] = current.transaction;
			current = current.next;
			i++;
		}
		return transactions;
	}

	private class Node {
		Transaction transaction;
		Node next;

		Node(Transaction transaction) {
			this.transaction = transaction;
			this.next = null;
		}
	}

	public class TransactionNotFoundException extends RuntimeException {
		TransactionNotFoundException(String message) {
			super(message);
		}
	}
	
}
