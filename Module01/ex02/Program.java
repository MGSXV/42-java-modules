public class Program {
	public static void main(String[] args) {
		UsersArrayList usersArrayList = UsersArrayList.getInstance();
		User user1 = new User("User1", 100);
		User user2 = new User("User2", 200);
		User user3 = new User("User3", 300);
		User user4 = new User("User4", 400);
		User user5 = new User("User5", 500);
		
		System.out.println("Users count before: " + usersArrayList.getUsersCount());
		
		usersArrayList.addUser(user1);
		usersArrayList.addUser(user2);
		usersArrayList.addUser(user3);
		usersArrayList.addUser(user4);
		usersArrayList.addUser(user5);
		
		System.out.println("Users count after: " + usersArrayList.getUsersCount());
		
		for (int i = 0; i < usersArrayList.getUsersCount(); i++) {
			System.out.println(usersArrayList.getUserByIndex(i).print());
		}
		System.out.println("<=======================================>");
		System.out.println(usersArrayList.getUserById(0).print());
		System.out.println("<=======================================>");
		try {
			System.out.println(usersArrayList.getUserById(10).print());
		} catch (UsersArrayList.UserNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}
