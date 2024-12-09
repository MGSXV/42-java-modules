import java.util.UUID;

public class Transaction {
	private UUID				id;
	private User				sender;
	private User				recipient;
	private double 				amount;
	private ETransitionCategory category;

	public enum ETransitionCategory {
		DEBIT("DEBIT"),
		CREDIT("CREDIT");

		private String value;

		ETransitionCategory(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	Transaction(User sender, User recipient, double amount, ETransitionCategory category) {
		this.id = UUID.randomUUID();
		this.sender = sender;
		this.recipient = recipient;
		this.category = category;
		this.amount = amount;
	}

	public UUID getID() {
		return this.id;
	}
	public User getSender() {
		return this.sender;
	}
	public User getReceiver() {
		return this.recipient;
	}
	public double getAmount() {
		return this.amount;
	}

	public void setID(UUID id) {
		this.id = id;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public void setReceiver(User recipient) {
		this.recipient = recipient;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public boolean execute() {
		if (this.category == ETransitionCategory.DEBIT) {
			if (this.sender.getBalance() < this.amount) {
				System.out.println("m7zou9a d3wa");
				return false;
			}
			this.sender.setBalance(this.sender.getBalance() - this.amount);
			this.recipient.setBalance(this.recipient.getBalance() + this.amount);
		} else {
			this.sender.setBalance(this.sender.getBalance() + this.amount);
			this.recipient.setBalance(this.recipient.getBalance() - this.amount);
		}
		return true;
	}

	// Blast toString() method
	public String print() {
		return "Transaction{id='" + this.id + "', sender=" + this.sender.print() + ", recipient=" + this.recipient.print() + ", amount=" + this.amount + ", category=" + this.category.getValue() + "}";
	}
}
