public class Program {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java Program --current-folder=[absolute path to current folder]");
			return;
		}
		String[] inputs = args[0].split("=");
		if (inputs.length != 2 || !inputs[0].equals("--current-folder")) {
			System.out.println("Usage: java Program --current-folder=[absolute path to current folder]");
			return;
		}
		try {
			FileManager fileManager = new FileManager(inputs[1]);
			fileManager.commandLoop();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
