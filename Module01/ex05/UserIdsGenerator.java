public class UserIdsGenerator {
	static private UserIdsGenerator instance = null;
	private long id = 1;

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
