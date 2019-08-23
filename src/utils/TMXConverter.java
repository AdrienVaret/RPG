package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

public class TMXConverter {

	/**
	 * Takes the path of a tmx file and convert it into my map format
	 */
	public static void convert(String inputPath, String outputPath, int x, int y) {
		
		try {
			
			BufferedReader r = new BufferedReader(new FileReader(new File(inputPath)));
			BufferedWriter w = new BufferedWriter(new FileWriter(new File(outputPath)));
			
			int width = 0;
			int height = 0;
			
			String line = r.readLine();
			int index = 0;
			
			//Rewriting dimensions and initial position
			while (true) {
				if (index == 1) {
					
					String [] splittedLine = line.split(" ");
					String stringWidth = splittedLine[5];
					String stringHeight = splittedLine[6];
					
					String [] splittedStringWidth = stringWidth.split(Pattern.quote("="));
					String [] splittedStringHeight = stringHeight.split(Pattern.quote("="));
					
					String stringWidth2 = splittedStringWidth[1];
					String stringHeight2 = splittedStringHeight[1];
					
					String stringWidth3 = stringWidth2.substring(1);
					String stringHeight3 = stringHeight2.substring(1);
					
					String stringWidth4 = stringWidth3.substring(0, stringWidth3.length() - 1);
					String stringHeight4 = stringHeight3.substring(0, stringHeight3.length() - 1);
					
					width = Integer.parseInt(stringWidth4);
					height = Integer.parseInt(stringHeight4);
					
					w.write(width + " " + height + " " + "\n");
					w.write(x + " " + y + "\n");
					line = r.readLine();
					break;
				}
				
				line = r.readLine();
				index ++;
			}
			
			//Rewriting map's data
			for (int layer = 0 ; layer < 3 ; layer ++) {
				
				//Skipping 3 lines
				for (int i = 0 ; i < 3 ; i++) line = r.readLine();
				
				//Reading and rewriting height lines
				for (int i = 0 ; i < height ; i++) {
					String [] splittedLine = line.split(Pattern.quote(","));
					
					String toWrite = "";
					for (int j = 0 ; j < width ; j++) {
						toWrite += splittedLine[j];
						if (j < width - 1) toWrite += " ";
					}
					w.write(toWrite + "\n");
					
					line = r.readLine();
				}
				
				//Skipping another line
				line = r.readLine();
			}
			
			r.close();
			w.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void convert(String inputPath, String outputPath) {
		convert(inputPath, outputPath, 0, 0);
	}
	
	public static void displayUsage() {
		System.err.println("USAGE java -jar $EXEC_NAME input_file output_file [x_initial y_initial]");
	}
	
	public static void main(String [] args) {
		
		if (args.length != 2 && args.length != 4) {
			displayUsage();
			System.exit(1);
		}
			
		String inputPath = args[0];
		String outputPath = args[1];
		int x, y;
		
		if (args.length == 2) 
			convert(inputPath, outputPath);
	
		else {
			try {
				x = Integer.parseInt(args[2]);
				y = Integer.parseInt(args[3]);
				convert(inputPath, outputPath, x, y);
			} catch (NumberFormatException e) {
				System.err.print("Correct args, but wrong format");
				displayUsage();
				System.exit(1);
			}
		}
		
		
	}
}
