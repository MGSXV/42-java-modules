import java.io.FileInputStream;
import java.util.HashMap;

public class SignatureReader {
	private static final String sourceFile = "signatures.txt";
	public static int 	longestSignature = -1;

	private HashMap<String, String> signatures;

	public SignatureReader() {
		signatures = new HashMap<String, String>();
	}

	public void readSignatures() {
		String ext = "";
		String signature = "";
		int i;
		boolean flag = false;
		try {
			FileInputStream fileInputStream = new FileInputStream(SignatureReader.sourceFile);
			while ((i = (int) fileInputStream.read()) != -1) {
				char c = (char) i;
				if (c == ',')
					flag = true;
				else if (c == '\n') {
					signature = signature.trim();
					ext = ext.trim();
					if (ext != "" && signature != "" && flag) {
						signatures.put(signature, ext);
						ext = "";
						signature = "";
						flag = false;
						longestSignature = Math.max(longestSignature, signature.length());
					}
				} else {
					if (!flag)
						ext += c;
					else
						signature += c;
				}
			}
			fileInputStream.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public HashMap<String, String> getSignatures() {
		return signatures;
	}

	public void printSignatures() {
		for (String key : signatures.keySet()) {
			System.out.println(key + " | " + signatures.get(key));
		}
	}
}
