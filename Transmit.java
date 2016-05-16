import java.util.Scanner;

public class Transmit implements Cipher {
	private String message;
	private String keywordOne;
	private String keywordTwo;
	private String pad;
	
	public Transmit() {
		message = "";
		keywordOne = "";
		keywordTwo = "";
		pad = "";
	}
	
	public Transmit(String m, String k, String w, String p) {
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
	
	public String encrypt() {
		return encrypt(message, keywordOne, keywordTwo, pad);
	}
	
	public String encrypt(String m, String k, String w, String p) {
		String[][] grid;
		int[] values;
		message = m;
		message = message.replace(" ", "");
		message = message.toLowerCase();
		
		keywordOne = k.toLowerCase();
		keywordTwo = w.toLowerCase();
		pad = p.toLowerCase();
		
		//COLUMNAR TRANSPOSITION
		double lineCount = keywordOne.length();		
		lineCount = message.length() / lineCount;
		lineCount = Math.ceil(lineCount);
		
		int lines = (int)lineCount;
		
		grid = new String[lines][keywordOne.length()];
		values = new int[keywordOne.length()];
		
		String tempMess = message;
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[r].length; c++) {
				if (tempMess.length() > 0) {
					grid[r][c] = tempMess.substring(0, 1);
					tempMess = tempMess.substring(1, tempMess.length());
				}
				
				else {
					grid[r][c] = pad;
				}
			}
		}
		
		for (int i = 0; i < values.length; i++)
			values[i] = alphabet.indexOf(keywordOne.substring(i, i + 1));
		
		String columns = "";
		int low = 0;
		for (int i = 0; i < values.length; i++) {
			for (int n = 0; n < values.length; n++) {
				if (values[n] < values[low]) {
					low = n;
				}
				
				else if (values[n] == values[low]) {
					if (n < low)
						low = n;
				}
			}
			
			for (int c = 0; c < grid.length; c++) {
				columns += grid[c][low];
			}
			
			values[low] = 27;
		}
		
		String g = "";
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[r].length; c++)
				g += grid[r][c] + " ";
			
			g += "\n";
		}
		
		//COLUMNAR TRANSPOSITION
		String[][] grid2;
		int[] values2;
		double lineCount2 = keywordTwo.length();		
		lineCount2 = columns.length() / lineCount2;
		lineCount2 = Math.ceil(lineCount2);
		
		int lines2 = (int)lineCount2;
		
		grid2 = new String[lines2][keywordTwo.length()];
		values2 = new int[keywordTwo.length()];
		
		String tempMess2 = columns;
		for (int r = 0; r < grid2.length; r++) {
			for (int c = 0; c < grid2[r].length; c++) {
				if (tempMess2.length() > 0) {
					grid2[r][c] = tempMess2.substring(0, 1);
					tempMess2 = tempMess2.substring(1, tempMess2.length());
				}
				
				else {
					grid2[r][c] = pad;
				}
			}
		}
		
		for (int i = 0; i < values2.length; i++)
			values2[i] = alphabet.indexOf(keywordTwo.substring(i, i + 1));
		
		String columns2 = "";
		int low2 = 0;
		for (int i = 0; i < values2.length; i++) {
			for (int n = 0; n < values2.length; n++) {
				if (values2[n] < values2[low2]) {
					low2 = n;
				}
				
				else if (values2[n] == values2[low2]) {
					if (n < low2)
						low2 = n;
				}
			}
			
			for (int c = 0; c < grid2.length; c++) {
				columns2 += grid2[c][low2];
			}
			
			values2[low2] = 27;
		}
		
		String g2 = "";
		for (int r = 0; r < grid2.length; r++) {
			for (int c = 0; c < grid2[r].length; c++)
				g2 += grid2[r][c] + " ";
			
			g2 += "\n";
		}
		
		return message +
				"\n" + keywordOne +
				"\n" + g +
				"\nencrypted one = " + columns +
				"\n" + keywordTwo +
				"\n" + g2 +
				"\nencrypted two = " + columns2;
	}
	
	public void readFile(String fileName) {
		try {
			Scanner f = new Scanner(fileName);
//			int scans = f.nextInt();
			String rtn = "";
			
//			f.next();
			
//			for (int i = 0; i < scans; i++) {
				message = f.next();
				keywordOne = f.next();
				keywordTwo = f.next();
				pad = f.next();
				
				rtn += encrypt();
				
//				f.next();
//			}
		}
		
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public String toString() {
		return "message : " + message + "\nkeyword one : " + keywordOne + "\nkeyword two : " + keywordTwo + "\nencryption : " + encrypt();
	}

	public static void main(String argv[]) {
//		Transmit m = new Transmit("tetrismaster", "tetris", "master", "x");
//		System.out.println(m.encrypt());
		
		Transmit d = new Transmit();
		d.readFile("C:\\Users\\Sydney\\Documents\\School\\Junior Year\\GitHub\\Double Transposition Cipher\\src\\DTC.dat");
		System.out.println(d.encrypt());
	}
}
