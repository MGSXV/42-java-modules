public class UserIdsGenerator {
	static private long id = 0;
	static private UserIdsGenerator instance = null;

	private UserIdsGenerator() {
	}

	public static UserIdsGenerator getInstance() {
		if (instance == null) {
			instance = new UserIdsGenerator();
		}
		return instance;
	}

	public long generateId() {
		return id++;
	}
}
