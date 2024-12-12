import java.io.File;
import java.util.Scanner;

public class FileManager {

	private String currentPath;

	public FileManager(String currentPath) {
		File file = new File(currentPath);
		if (!file.exists() || !file.isDirectory()) {
			throw new IllegalArgumentException("Invalid folder");
		}
		try {
			this.currentPath = file.getCanonicalPath();
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid folder");
		}
		System.out.println(this.currentPath);
	}

	private boolean executeCommand(String command) {
		command = command.trim();
		String[] inputs = command.split(" ");
		if (inputs.length == 0) {
			return true;
		}
		switch (inputs[0]) {
			case "ls":
				File file = new File(currentPath);
				ls(file, 0);
				break;
			case "cd":
				if (inputs.length != 2) {
					System.out.println("Usage: cd [folder]");
					break;
				}
				cd(inputs[1]);
				break;
			case "mv":
				if (inputs.length != 3) {
					System.out.println("Usage: mv [source] [destination]");
					break;
				}
				mv(inputs[1], inputs[2]);
				break;
			case "exit":
				return false;
			default:
				System.out.println("Invalid command");
				break;
		}
		return true;
	}

	public void commandLoop() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("-> ");
			String command = scanner.nextLine();
			if (!executeCommand(command))
				break;
		}
		scanner.close();
	}

	private long ls(File file, int depth) {
		File[] files = file.listFiles();
		long size = 0;
		for (File f : files) {
			long z = 0;
			if (f.isDirectory()) {
				size = (depth > 0) ?  size + ls(f, depth + 1) : ls(f, depth + 1) ;
				if (depth == 0)
					System.out.println(f.getName() + " " +  size / 1024.0 + " KB");
			}
			else {
				z = f.length();
				if (depth == 0)
					System.out.println(f.getName() + " " + z / 1024.0  + " KB");
				if (depth > 0)
					size += z;
			}
		}
		return size;
	}

	private void cd(String folder) {
		File file;

		if (folder.charAt(0) == '/')
			file = new File(folder);
		else
			file = new File(currentPath + "/" + folder);
		if (!file.exists() || !file.isDirectory()) {
			System.out.println("Folder not found");
			System.out.println(this.currentPath);
			return;
		}
		try {
			this.currentPath = file.getCanonicalPath();
			System.out.println(this.currentPath);
		} catch (Exception e) {
			System.out.println("Folder not found");
			System.out.println(this.currentPath);
		}
	}

	private void mv(String source, String destination) {
		File sourceFile = new File(currentPath + "/" + source);
		File destinationFile = new File(currentPath + "/" + destination);
		if (!sourceFile.exists()) {
			System.out.println("Source file not found");
			return;
		}
		if (destinationFile.exists()) {
			System.out.println("Destination file already exists");
			return;
		}
		sourceFile.renameTo(destinationFile);
	}
}
