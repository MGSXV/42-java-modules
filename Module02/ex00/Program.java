import java.util.HashMap;

public class Program {

	public static void main(String[] args) {
		SignatureReader signatureReader = new SignatureReader();
		MagicNumberExtractor extractor = new MagicNumberExtractor();
		signatureReader.readSignatures();
		HashMap<String, String> signatures = signatureReader.getSignatures();
		extractor.execute(signatures);
	}
}