public class User {

	private static int	idCounter = 0;

	private int	id;
	private String	name;
	private double	balance;

	User(String name, double balance) {
		if (balance < 0) {
			System.out.println("Balance can't be negative");
			return;
		}
		this.id = idCounter++;
		this.name = name;
		this.balance = balance;
	}


	public int		getID() {
		return this.id;
	}
	public String	getName() {
		return this.name;
	}
	public double	getBalance() {
		return this.balance;
	}

	public void		setID(int id) {
		this.id = id;
	}
	public void		setName(String name) {
		this.name = name;
	}
	public void		setBalance(double balance) {
		if (balance < 0) {
			System.out.println("Balance can't be negative");
			return;
		}
		this.balance = balance;
	}

	// Blast toString() method
	public String	print() {
		return "User{id=" + this.id + ", name='" + this.name + "', balance=" + this.balance + "}";
	}
}
