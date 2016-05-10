public class Transmit implements Cipher {
	@Override
	public String encrypt(String m, String k, String w) {
		int wordCount = 0;
		
		for (int i = 0; i < m.length(); i++) {
			if (m.substring(i, i + 1).equals(" ")) {
				wordCount++;
			}
		}
		
		String[] words = new String[wordCount];
		
		for (int i = 0; i < wordCount; i++) {
			words[i] = m.substring(0, m.indexOf(" "));
//			m = m.substring(m.indexOf(" ") + 1, m.length());
		}
		
		words[wordCount] = m;
		
		String encrypt = "";
		
		for (String word : words) {
			encrypt += word;
		}
		
		return encrypt;
	}

	@Override
	public String decode(String m, String k, String w) {
		return null;
	}

	public static void main(String argv[]) {
		Transmit t = new Transmit();
		System.out.println(t.encrypt("h ello", "k", "w"));
	}
}
