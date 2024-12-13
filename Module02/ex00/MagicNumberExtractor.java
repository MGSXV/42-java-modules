import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.util.HashMap;

public class MagicNumberExtractor {

	public String extract(String fileName) {
		String magicNumber = "";
		try {
			FileInputStream fileInputStream = new FileInputStream(fileName);
			byte[] buffer = new byte[SignatureReader.longestSignature];

			fileInputStream.read(buffer, 0, SignatureReader.longestSignature);
			for (byte b : buffer) {
				magicNumber += String.format("%02X ", b);
			}
			fileInputStream.close();
		} catch (Exception e) {
			
		}
		return magicNumber;
	}

	public void execute(HashMap<String, String> signatures) {
		Scanner scanner = new Scanner(System.in);
		try {
			while (true) {
				System.out.print("--> ");
				if (scanner.hasNextLine()) {
					String fileName = scanner.nextLine();
					if (fileName.equals("42")) {
						break;
					}
					String magicNumber = extract(fileName);
					String ext = isInMap(magicNumber, signatures);
					if (!ext.equals("UNDEFINED")) {
						saveExtentionToFile(ext);
						System.out.println("PROCESSED");
					} else {
						System.out.println(ext);
					}
				}
			}
			scanner.close();
		} catch (Exception e) {
			scanner.close();
			System.err.println("Error: " + e.getMessage());
		}
	}

	public String isInMap(String magicNumber, HashMap<String, String> signatures) {
		for (HashMap.Entry<String, String> entry : signatures.entrySet()) {
			if (magicNumber.indexOf(entry.getKey()) != 0)
				return entry.getValue();
		}
		return "UNDEFINED";
	}

	public void saveExtentionToFile(String ext) {
		try {
			FileOutputStream output = new FileOutputStream("result.txt", true);
			output.write(ext.getBytes());
			output.write("\n".getBytes());
			output.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}
