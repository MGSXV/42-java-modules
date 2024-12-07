public class Program {
	public static void main(String[] args) {
		User user1 = new User("Alice", 1000);
		User user2 = new User("Bob", 1000);

		user1.setName("Alice");
		user1.setBalance(1000);
		user2.setName("Bob");
		user2.setBalance(1000);

		System.out.println(user1.print());
		System.out.println(user2.print());
		System.out.println("====================================");

		System.out.println(user1.print());
		System.out.println(user2.print());
		System.out.println("====================================");
	}
}
