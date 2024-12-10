public class UsersArrayList implements UsersList {
	
	private static UsersArrayList instance;
	private User[] users = new User[10];

	private UsersArrayList() {
	}

	public static UsersArrayList getInstance() {
		if (instance == null) {
			instance = new UsersArrayList();
		}
		return instance;
	}

	private void resize() {
		User[] newUsers = new User[users.length * 2];
		for (int i = 0; i < users.length; i++) {
			newUsers[i] = users[i];
		}
		users = newUsers;
	}

	public void addUser(User user) {
		if (user == null) {
			return;
		}
		if (users[users.length - 1] != null) {
			resize();
		}
		for (int i = 0; i < users.length; i++) {
			if (users[i] == null) {
				users[i] = user;
				break;
			}
		}
	}

	public User getUserByIndex(int index) {
		if (index < 0 || index >= users.length) {
			throw new UserNotFoundException("User not found");
		}
		return users[index];
	}

	public User getUserById(long id) {
		for (int i = 0; i < users.length; i++) {
			if (users[i] != null && users[i].getID() == id) {
				return users[i];
			}
		}
		throw new UserNotFoundException("User not found");
	}

	public long getUsersCount() {
		long count = 0;
		for (int i = 0; i < users.length; i++) {
			if (users[i] != null) {
				count++;
			}
		}
		return count;
	}

	public class UserNotFoundException extends RuntimeException {
		public UserNotFoundException(String message) {
			super(message);
		}
	}
}
