public interface Cipher {
	public String message = "";
	public String keywordOne = "";
	public String keywordTwo = "";
	public final String alphabet = "abcdefghijklmnopqrstuvwxyz";
	
	public String encrypt(String m, String k, String w);
	public String decode(String m, String k, String w);
}
