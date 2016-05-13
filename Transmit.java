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
		//COLUMNAR TRANSPOSITION
		double lineCount = k.length();
		String message = "";
		String[] words;
		k = k.toLowerCase();
		w = w.toLowerCase();

		message = m.replace(" ", "");
		message = message.toLowerCase();
		
		while (message.length() % k.length() != 0) {
			message += p;
		}
		
		lineCount = message.length() / lineCount;
		lineCount = Math.ceil(lineCount);
		
		int lines = (int)lineCount;
		words = new String[lines];
		
		String tempMess = message;

		for (int i = 0; i < lines; i++) {
			words[i] = tempMess.substring(0, k.length());
			tempMess = tempMess.replace(tempMess.substring(0, k.length()), "");
		}
		
		String array = "";
		for (String word : words)
			array += word + " ";
		
		String[] columns = new String[k.length()];
		for (int i = 0; i < columns.length; i++) {
			columns[i] = words[0].substring(i, i + 1);
			
			for (int n = 1; n < words.length; n++) {
				columns[i] += words[n].substring(i, i + 1);
			}
		}
		
		String columnArray = "";
		for (String col : columns) 
			columnArray += col + " ";
		
		int[] values = new int[k.length()];
		
		for (int i = 0; i < values.length; i++)
			values[i] = alphabet.indexOf(k.substring(i, i + 1));
		
		String valArray = "";
		for (int val : values)
			valArray += val + " ";
		
		String temp = "";
		int tempInt = 0;
		for (int i = 0; i < columns.length; i++) {
			for (int n = i + 1; n < values.length; n++) {
				if (values[i] > values[n]) {
					temp = columns[n];
					columns[n] = columns[i];
					columns[i] = temp;
					
					tempInt = values[n];
					values[n] = values[i];
					values[i] = tempInt;
				}
			}
		}
		
		String columnArrayTwo = "";
		for (String col : columns)
			columnArrayTwo += col + " ";
		
		String valArrayTwo = "";
		for (int val : values)
			valArrayTwo += val + " ";
		
		String encrypt1 = "";
		for (String col : columns)
			encrypt1 += col;
		
		//DOUBLE TRANSPOSITION
		double lineCount2 = w.length();
		String[] words2;
		
		encrypt1 = encrypt1.toLowerCase();
		while (encrypt1.length() % w.length() != 0) {
			encrypt1 += p;
		}
		
		lineCount2 = encrypt1.length() / lineCount2;
		lineCount2 = Math.ceil(lineCount2);
		
		int lines2 = (int)lineCount2;
		words2 = new String[lines2];
		
		String tempMess2 = encrypt1;

		for (int i = 0; i < lines2; i++) {
			words2[i] = tempMess2.substring(0, w.length());
			tempMess2 = tempMess2.replace(tempMess2.substring(0, w.length()), "");	
		}
		
		String array2 = "";
		for (String word : words2)
			array2 += word + " ";
		
		String[] columns2 = new String[w.length()];
		for (int i = 0; i < columns2.length; i++) {
			columns2[i] = words2[0].substring(i, i + 1);
			
			for (int n = 1; n < words2.length; n++) {
				columns2[i] += words2[n].substring(i, i + 1);
			}
		}
		
		String columnArray2 = "";
		for (String col : columns2)
			columnArray2 += col + " ";
		
		int[] values2 = new int[w.length()];
		
		for (int i = 0; i < values2.length; i++)
			values2[i] = alphabet.indexOf(w.substring(i, i + 1));
		
		String valArray2 = "";
		for (int val : values2)
			valArray2 += val + " ";
		
		String temp2 = "";
		int tempInt2 = 0;
		for (int i = 0; i < columns2.length; i++) {
			for (int n = i + 1; n < values2.length; n++) {
				if (values2[i] > values2[n]) {
					temp2 = columns2[n];
					columns2[n] = columns2[i];
					columns2[i] = temp2;
					
					tempInt2 = values2[n];
					values2[n] = values2[i];
					values2[i] = tempInt2;
				}
			}
		}
		
		String columnArrayTwo2 = "";
		for (String col : columns2)
			columnArrayTwo2 += col + " ";
		
		String valArrayTwo2 = "";
		for (int val : values2)
			valArrayTwo2 += val + " ";
		
		String encrypt2 = "";
		for (String col : columns2)
			encrypt2 += col;
		
		return message +
//				"\nlines = " + lines + 
//				"\narray = " + array + 
//				"\ncolumns = " + columnArray + 
//				"\nvalues = " + valArray +
//				"\ncolumns after = " + columnArrayTwo +
//				"\nvalues after = " + valArrayTwo +
				"\ntransposition one = " + encrypt1 +
//				"\nlines two = " + lines2 + 
//				"\narray two = " + array2 + 
//				"\ncolumns two = " + columnArray2 + 
//				"\nvalues two = " + valArray2 +
//				"\ncolumns two after = " + columnArrayTwo2 +
//				"\nvalues two after = " + valArrayTwo2 +
//				"\ntransposition two = " + 
				"\nencrypted = " + encrypt2;
	}
	
	public String toString() {
		return "message : " + message + "\nkeyword one : " + keywordOne + "\nkeyword two : " + keywordTwo + "\nencryption : " + encrypt();
	}

	public static void main(String argv[]) {
		Transmit m = new Transmit("michaelisawesome", "hello", "three", "x");
		System.out.println(m.encrypt());
	}
}
