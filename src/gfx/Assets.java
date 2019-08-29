package gfx;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import tiles.Tile;

public class Assets {

	private static final int width = 32, height = 32;
	
	public static ArrayList<ArrayList<BufferedImage>> player;
	public static BufferedImage[] btn_start;
	
	public static BufferedImage blackTile48px;
	
	public static Font playerNameFont;
	
	public static BufferedImage background;
	
	public static BufferedImage battleGui;
	public static BufferedImage battleGuiBrackets;
	
	public static void initAssets() {
		
		btn_start = new BufferedImage[2];
		btn_start[0] = ImageLoader.loadImage("/textures/start_button.png");
		btn_start[1] = ImageLoader.loadImage("/textures/start_button_selected.png");
		
		blackTile48px = ImageLoader.loadImage("/textures/black_tile_48.png");
		
		battleGui = ImageLoader.loadImage("/textures/battlefield_ressources/battle_gui_wo_brackets.png");
		battleGuiBrackets = ImageLoader.loadImage("/textures/battlefield_ressources/battle_gui_brackets.png");
		
		try {
			playerNameFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/Achafexp.ttf"));
			playerNameFont = playerNameFont.deriveFont(30f);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		
		background = ImageLoader.loadImage("/textures/background.png");
		
	}
	
	public static ArrayList<ArrayList<BufferedImage>> init(SpriteSheet sheet) {
	
		ArrayList<ArrayList<BufferedImage>> assets = new ArrayList<ArrayList<BufferedImage>>();
		
		int nbColumns = sheet.getSheet().getWidth() / width;
		int nbRows = sheet.getSheet().getHeight() / height;
		
		for (int i = 0 ; i < nbRows ; i ++) {
			ArrayList<BufferedImage> list = new ArrayList<BufferedImage>();	
			for (int j = 0 ; j < nbColumns ; j++) {
				list.add(sheet.crop(j*32, i*32, width, height));
				
			}
			assets.add(list);
		}
		
		return assets;
	}
	
	public static void setPlayer(SpriteSheet sheet) {
		player = init(sheet);
	}
	
	public static void setTileset(SpriteSheet sheet) {
		ArrayList<ArrayList<BufferedImage >> textures = init(sheet);
		
		//commenter si bug
		Tile.initTileset(textures);
	}
	
	public static void displayAssets(ArrayList<ArrayList<BufferedImage>> assets, Graphics g) {
		int nbRows = assets.size();
		int nbColumns = assets.get(0).size();
		
		for (int i = 0 ; i < nbRows ; i++) {
			for (int j = 0 ; j < nbColumns ; j++) {
				g.drawImage(assets.get(i).get(j), j*32, i*32, null);
			}
		} 	
	}
}
