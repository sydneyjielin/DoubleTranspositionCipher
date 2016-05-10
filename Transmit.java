public class Transmit implements Cipher {
	@Override
	public String encrypt(String m, String k, String w) {
		double lineCount = k.length();
		String message = "";
		String[] words;
		
		if (m.contains(" ")) {
			message = m.replace(" ", "");
		}
		
		message = message.toLowerCase();
		
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
			for (int n = 1; n < words.length; n++) {
				columns[i] = words[0].substring(i, i + 1);
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
		
		return message + 
				"\nlines = " + lines + 
				"\narray = " + array + 
				"\ncolumns = " + columnArray + 
				"\nvalues = " + valArray +
				"\ncolumns after = " + columnArrayTwo +
				"\nvalues after = " + valArrayTwo;
	}

	@Override
	public String decode(String m, String k, String w) {
		return null;
	}

	public static void main(String argv[]) {
		Transmit t = new Transmit();
		System.out.println(t.encrypt("hello world", "seven", "to"));
	}
}
