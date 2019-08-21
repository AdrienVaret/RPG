package utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gfx.ImageLoader;
import gfx.SpriteSheet;

public class AutotileReader {

	public static BufferedImage createTile(BufferedImage tile1, BufferedImage tile2,
			                      BufferedImage tile3, BufferedImage tile4) {
		
		BufferedImage tile = new BufferedImage(32,32, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = tile.createGraphics();
        
        g2.drawImage(tile1, null, 0, 0);
        g2.drawImage(tile2, null, 16, 0);
        g2.drawImage(tile3, null, 0, 16);
        g2.drawImage(tile4, null, 16, 16);
        
        g2.dispose();
        return tile;
	}
	
	public static void readAutotile(String inputFile, String outputFile, boolean intermediarWrites) {
		
		//BufferedImage autotile = ImageLoader.loadImage(inputFile);
		BufferedImage autotile = null;
		try {
			autotile = ImageIO.read(new File(inputFile));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SpriteSheet sheetAutoTile = new SpriteSheet(autotile);
		
		BufferedImage[][] splittedAutotile = new BufferedImage[6][4];
		
		for (int i = 0 ; i < 6 ; i++) {
			for (int j = 0 ; j < 4 ; j++) {
				splittedAutotile[i][j] = sheetAutoTile.crop(j * 16, i * 16, 16, 16);
			}
		}
		
		//Generating minitiles
		BufferedImage a1 = splittedAutotile[0][2];
		BufferedImage a2 = splittedAutotile[2][0];
		BufferedImage a3 = splittedAutotile[4][2];
		BufferedImage a4 = splittedAutotile[2][2];
		BufferedImage a5 = splittedAutotile[4][0];
		
		BufferedImage b1 = splittedAutotile[0][3];
		BufferedImage b2 = splittedAutotile[2][3];
		BufferedImage b3 = splittedAutotile[4][1];
		BufferedImage b4 = splittedAutotile[2][1];
		BufferedImage b5 = splittedAutotile[4][3];
		
		BufferedImage c1 = splittedAutotile[1][2];
		BufferedImage c2 = splittedAutotile[5][0];
		BufferedImage c3 = splittedAutotile[3][2];
		BufferedImage c4 = splittedAutotile[5][2];
		BufferedImage c5 = splittedAutotile[3][0];
		
		BufferedImage d1 = splittedAutotile[1][3];
		BufferedImage d2 = splittedAutotile[5][3];
		BufferedImage d3 = splittedAutotile[3][2];
		BufferedImage d4 = splittedAutotile[5][1];
		BufferedImage d5 = splittedAutotile[3][3];
		
		
		//Generating complete tiles
		BufferedImage tile0 = createTile(d3, c3, b3, a3);
		
		BufferedImage tile1 = createTile(a2, b4, c5, d3);
		BufferedImage tile2 = createTile(a4, b2, c3, d5);
		BufferedImage tile3 = createTile(a3, b5, c4, d2);
		BufferedImage tile4 = createTile(a5, b3, c2, d4);
		
		BufferedImage tile5 = createTile(b4, a4, d3, c3);
		BufferedImage tile6 = createTile(c5, d3, a5, b3);
		BufferedImage tile7 = createTile(b3, a3, d4, c4);
		BufferedImage tile8 = createTile(c3, d5, a3, b5);
		BufferedImage tile9 = createTile(c5, d5, a5, b5);
		BufferedImage tile10 = createTile(b4, a4, d4, c4);
		
		BufferedImage tile11 = createTile(a2, b4, c5, d1);
		BufferedImage tile12 = createTile(a4, b2, c1, d5);
		BufferedImage tile13 = createTile(a1, b5, c4, d2);
		BufferedImage tile14 = createTile(a5, b1, c2, d4);
		
		BufferedImage tile15 = createTile(a1, b1, c1, d1);
		
		BufferedImage tile16 = createTile(a2, b2, c5, d5);
		BufferedImage tile17 = createTile(a4, b2, c4, d2);
		BufferedImage tile18 = createTile(a5, b5, c2, d2);
		BufferedImage tile19 = createTile(a2, b4, c2, d4);
		
		BufferedImage tile20 = createTile(c5, b1, a5, d1);
		BufferedImage tile21 = createTile(a1, d5, c1, b5);
		BufferedImage tile22 = createTile(b4, a4, c1, d1);
		BufferedImage tile23 = createTile(a1, b1, d4, c4);
		
		BufferedImage tile24 = createTile(a1, c3, b3, a3);
		BufferedImage tile25 = createTile(d3, b1, b3, a3);
		BufferedImage tile26 = createTile(d3, c3, b3, d1);
		BufferedImage tile27 = createTile(d3, c3, c1, a3);
		BufferedImage tile28 = createTile(a1, b1, b3, a3);
		BufferedImage tile29 = createTile(a1, c3, b3, d1);
		BufferedImage tile30 = createTile(a1, c3, c1, a3);
		BufferedImage tile31 = createTile(d3, b1, b3, d1);
		BufferedImage tile32 = createTile(d3, b1, c1, a3);
		BufferedImage tile33 = createTile(d3, c3, c1, d1);
		BufferedImage tile34 = createTile(a1, b1, b3, d1);
		BufferedImage tile35 = createTile(a1, b1, c1, a3);
		BufferedImage tile36 = createTile(d3, b1, c1, d1);
		BufferedImage tile37 = createTile(a1, c3, c1, d1);
		
		//Building final tileset
		BufferedImage tileset = new BufferedImage(10*32, 4*32, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = tileset.createGraphics();
        
        g2.setBackground(new Color(1f,0f,0f,1f));
		
        g2.drawImage(tile0, null, 0, 0);
        g2.drawImage(tile15, null, 32, 0);
        g2.drawImage(tile5, null, 64, 0);
        g2.drawImage(tile8, null, 96, 0);
        g2.drawImage(tile11, null, 128, 0);
        g2.drawImage(tile12, null, 160, 0);
        g2.drawImage(tile16, null, 192, 0);
        g2.drawImage(tile22, null, 224, 0);
        g2.drawImage(tile34, null, 256, 0);
        g2.drawImage(tile36, null, 288, 0);
        
        g2.drawImage(tile1, null, 0, 32);
        g2.drawImage(tile2, null, 32, 32);
        g2.drawImage(tile6, null, 64, 32);
        g2.drawImage(tile7, null, 96, 32);
        g2.drawImage(tile14, null, 128, 32);
        g2.drawImage(tile13, null, 160, 32);
        g2.drawImage(tile9, null, 192, 32);
        g2.drawImage(tile23, null, 224, 32);
        g2.drawImage(tile35, null, 256, 32);
        g2.drawImage(tile37, null, 288, 32);
        
        g2.drawImage(tile4, null, 0, 64);
        g2.drawImage(tile3, null, 32, 64);
        g2.drawImage(tile19, null, 64, 64);
        g2.drawImage(tile10, null, 96, 64);
        g2.drawImage(tile17, null, 128, 64);
        g2.drawImage(tile18, null, 192, 64);
        g2.drawImage(tile20, null, 224, 64);
        g2.drawImage(tile21, null, 256, 64);
        
        g2.drawImage(tile24, null, 0, 96);
        g2.drawImage(tile25, null, 32, 96);
        g2.drawImage(tile26, null, 64, 96);
        g2.drawImage(tile27, null, 96, 96);
        g2.drawImage(tile28, null, 128, 96);
        g2.drawImage(tile29, null, 160, 96);
        g2.drawImage(tile30, null, 192, 96);
        g2.drawImage(tile31, null, 224, 96);
        g2.drawImage(tile32, null, 256, 96);
        g2.drawImage(tile33, null, 288, 96);
        
        g2.dispose();
        
		try {
			if (intermediarWrites) {
				//writing minitiles
				ImageIO.write(a1, "png", new File("minitiles/a1.png"));
				ImageIO.write(a2, "png", new File("minitiles/a2.png"));
				ImageIO.write(a3, "png", new File("minitiles/a3.png"));
				ImageIO.write(a4, "png", new File("minitiles/a4.png"));
				ImageIO.write(a5, "png", new File("minitiles/a5.png"));
			
				ImageIO.write(b1, "png", new File("minitiles/b1.png"));
				ImageIO.write(b2, "png", new File("minitiles/b2.png"));
				ImageIO.write(b3, "png", new File("minitiles/b3.png"));
				ImageIO.write(b4, "png", new File("minitiles/b4.png"));
				ImageIO.write(b5, "png", new File("minitiles/b5.png"));
			
				ImageIO.write(c1, "png", new File("minitiles/c1.png"));
				ImageIO.write(c2, "png", new File("minitiles/c2.png"));
				ImageIO.write(c3, "png", new File("minitiles/c3.png"));
				ImageIO.write(c4, "png", new File("minitiles/c4.png"));
				ImageIO.write(c5, "png", new File("minitiles/c5.png"));
			
				ImageIO.write(d1, "png", new File("minitiles/d1.png"));
				ImageIO.write(d2, "png", new File("minitiles/d2.png"));
				ImageIO.write(d3, "png", new File("minitiles/d3.png"));
				ImageIO.write(d4, "png", new File("minitiles/d4.png"));
				ImageIO.write(d5, "png", new File("minitiles/d5.png"));
			
			
				//writing complete tiles
				ImageIO.write(tile0, "png", new File("tiles_complete/tile0.png"));
				ImageIO.write(tile1, "png", new File("tiles_complete/tile1.png"));
				ImageIO.write(tile2, "png", new File("tiles_complete/tile2.png"));
				ImageIO.write(tile3, "png", new File("tiles_complete/tile3.png"));
				ImageIO.write(tile4, "png", new File("tiles_complete/tile4.png"));
				ImageIO.write(tile5, "png", new File("tiles_complete/tile5.png"));
				ImageIO.write(tile6, "png", new File("tiles_complete/tile6.png"));
				ImageIO.write(tile7, "png", new File("tiles_complete/tile7.png"));
				ImageIO.write(tile8, "png", new File("tiles_complete/tile8.png"));
				ImageIO.write(tile9, "png", new File("tiles_complete/tile9.png"));
				ImageIO.write(tile10, "png", new File("tiles_complete/tile10.png"));
				ImageIO.write(tile11, "png", new File("tiles_complete/tile11.png"));
				ImageIO.write(tile12, "png", new File("tiles_complete/tile12.png"));
				ImageIO.write(tile13, "png", new File("tiles_complete/tile13.png"));
				ImageIO.write(tile14, "png", new File("tiles_complete/tile14.png"));
				ImageIO.write(tile15, "png", new File("tiles_complete/tile15.png"));
				ImageIO.write(tile16, "png", new File("tiles_complete/tile16.png"));
				ImageIO.write(tile17, "png", new File("tiles_complete/tile17.png"));
				ImageIO.write(tile18, "png", new File("tiles_complete/tile18.png"));
				ImageIO.write(tile19, "png", new File("tiles_complete/tile19.png"));
				ImageIO.write(tile20, "png", new File("tiles_complete/tile20.png"));
				ImageIO.write(tile21, "png", new File("tiles_complete/tile21.png"));
				ImageIO.write(tile22, "png", new File("tiles_complete/tile22.png"));
				ImageIO.write(tile23, "png", new File("tiles_complete/tile23.png"));
			}
			
			ImageIO.write(tileset, "png", new File(outputFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//readAutotile("/textures/autotile.png");
		try {
			boolean intermediarWrites = false;
			String inputFile = args[0];
			String outputFile = args[1];
			if (args.length > 2) {
				if (args[2].equals("-w"))
					intermediarWrites = true;
			}
			
			if (intermediarWrites) {
				new File("minitiles").mkdir();
				new File("tiles_complete").mkdir();
			}
			
			readAutotile(inputFile, outputFile, intermediarWrites);
			
		} catch (IndexOutOfBoundsException e) {
			System.err.println("#USAGE : autotile inputFile outputFile [-w]");
		}
	}
}
