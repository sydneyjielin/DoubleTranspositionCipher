import java.util.Scanner;

public class Receive implements Cipher {
	private String message;
	private String keywordOne;
	private String keywordTwo;
	private String pad;
	
	public Receive() {
		message = "";
		keywordOne = "";
		keywordTwo = "";
		pad = "";
	}
	
	public Receive(String m, String k, String w, String p) {
		message = m;
		keywordOne = k;
		keywordTwo = w;
		pad = p;
	}
	
	public void setMessage(String m) {
		message = m;
	}
	
	public void setKeywordOne(String k) {
		keywordOne = k;
	}
	
	public void setKeywordTwo(String w) {
		keywordTwo = w;
	}
	
	public void setKeywords(String k, String w) {
		setKeywordOne(k);
		setKeywordTwo(w);
	}
	
	public void setPad(String p) {
		pad = p;
	}
	
	public String decrypt() {
		return decrypt(message, keywordOne, keywordTwo, pad);
	}

	public String decrypt(String m, String k, String w, String p) {
		String[][] grid;
		double rowCount = w.length();
		rowCount = m.length() / rowCount;
		
		rowCount = (Math.ceil(rowCount));
		int rows = (int)rowCount;
		grid = new String[rows][w.length()];
		
		m = m.toLowerCase();
		w = w.toLowerCase();
		k = k.toLowerCase();
		
		int[] values = new int[w.length()];
		for (int i = 0; i < values.length; i++)
			values[i] = alphabet.indexOf(w.substring(i, i + 1));
		
		String tempMess = m;
		int low = 0;
		for (int i = 0; i < values.length; i++) {
			for (int n = 0; n < values.length; n++) {
				if (values[n] < values[low])
					low = n;
			}
		
			for (int n = 0; n < grid.length; n++) {
				if (tempMess.length() > 0) {
					grid[n][low] = tempMess.substring(0, 1);
					tempMess = tempMess.substring(1, tempMess.length());
				}
				
				values[low] = 27;
			}
		}
		
		for (int n = 0; n < grid[0].length; n++) {
			if (values[n] < values[low])
				low = n;
		}
	
		for (int n = 0; n < grid.length; n++) {
			if (tempMess.length() > 0) {
				grid[n][low] = tempMess.substring(0, 1);
				tempMess = tempMess.substring(1, tempMess.length());
			}
			values[low] = 27;
		}
		
		String g = "";
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[r].length; c++)
				g += grid[r][c] + " ";
			g += "\n";
		}
		
		String decoded1 = "";
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[r].length; c++) {
				decoded1 += grid[r][c];
			}
		}
		
		String padding = "";
		for (int i = 0; i < grid.length; i++)
			padding += pad;
		
		if (decoded1.substring(decoded1.length() - padding.length(), decoded1.length()).equals(padding))
			decoded1 = decoded1.substring(0, decoded1.length() - padding.length());
		
//		System.out.println(decoded1);
		
		// DECRYPT TWO
		String[][] grid2;
		double rowCount2 = k.length();
		rowCount2 = decoded1.length() / rowCount2;
//		rowCount2 = (Math.ceil(rowCount2));
		
		int rows2 = (int)rowCount2;
		grid2 = new String[rows2][k.length()];
		
		decoded1 = decoded1.toLowerCase();
		
		int[] values2 = new int[k.length()];
		for (int i = 0; i < values2.length; i++)
			values2[i] = alphabet.indexOf(k.substring(i, i + 1));
		
		String values2Array = "";
		for (int val : values2)
			values2Array += val + " ";
		
		String tempMess2 = decoded1;
		int low2 = 0;
		for (int i = 0; i < values2.length; i++) {
			for (int n = 0; n < values2.length; n++) {
				if (values2[n] < values2[low2])
					low2 = n;
			}
			
			for (int n = 0; n < grid2.length; n++) {
				if (tempMess2.length() > 0) {
					grid2[n][low2] = tempMess2.substring(0, 1);
					tempMess2 = tempMess2.substring(1, tempMess2.length());
				}
				
				else {
					grid2[n][low2] = pad;
				}
			}
			
			values2[low2] = 27;
		}
		
		for (int n = 0; n < values2.length; n++) {
			if (values2[n] < values2[low2])
				low2 = n;
		}
		
		for (int n = 0; n < grid2.length; n++) {
			if (tempMess2.length() > 0) {
				grid2[n][low2] = tempMess2.substring(0, 1);
				tempMess2 = tempMess2.substring(1, tempMess2.length());
			}
		}
		
		values2[low2] = 27;
		
		String g2 = "";
		for (int r = 0; r < grid2.length; r++) {
			for (int c = 0; c < grid2[r].length; c++)
				g2 += grid2[r][c] + " ";
			
			g2 += "\n";
		}
		
		String decoded2 = "";
		for (int r = 0; r < grid2.length; r++) {
			for (int c = 0; c < grid2[r].length; c++)
				decoded2 += grid2[r][c];
		}
		
		for (int i = 0; i < message.length(); i++) {
			if (decoded2.substring(decoded2.length() - 1, decoded2.length()).equals(p))
				decoded2 = decoded2.substring(0, decoded2.length() - 1);
		}
		
		return 
//				message + 
//				"\n" + w +
//				"\n" + g +
//				"\ndecoded once = " + decoded1 +
//				"\n" + values2Array +
//				"\n" + g2 +
//				"\n" + k +
//				"\n" + g2 +
//				"\ndecoded two = " + 
//				"\ndecoded = " + 
				decoded2;
	}

	public String toString() {
		return "message : " + message + "\nkeyword one : " + keywordOne + "\nkeyword two : " + keywordTwo + "\ndecryption : " + decrypt();
	}
	
	public static void main(String argv[]) {
		Receive m = new Receive("xcsmmioxlwxaeehaiesx", "hello", "three", "x");
		System.out.println(m.decrypt());
		
		Receive t = new Receive("arrtestsitem", "tetris", "master", "x");
		System.out.println(t.decrypt());
		
		Scanner k = new Scanner(System.in);
		String choice = "y";
		
		while (choice.equalsIgnoreCase("y")) {
			System.out.print("message : ");
			String message = k.next();
			
			System.out.print("keyword one : ");
			String kwOne = k.next();
			
			System.out.print("keyword two : ");
			String kwTwo = k.next();
			
			System.out.print("null key : ");
			String pad = k.next();
			
			Receive s = new Receive(message, kwOne, kwTwo, pad);
			System.out.println("decryption = " + s.decrypt());
			
			System.out.println("decrypt again? ");
			choice = k.next();
		}
	}
}
