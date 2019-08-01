package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Utils {

	public static String loadFileAsString(String path) {
		StringBuilder builder = new StringBuilder();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(path)));
			String line = null;
			
			while ((line = br.readLine()) != null)
				builder.append(line + "\n");
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return builder.toString();
	}
	
	public static int parseInt(String strNumber) {
		try {
			return Integer.parseInt(strNumber);
		} catch(NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
