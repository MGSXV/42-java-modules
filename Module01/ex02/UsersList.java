interface UsersList {

	public void addUser(User user);
	public User getUserById(long id);
	public User getUserByIndex(int index);
	public long getUsersCount();
}