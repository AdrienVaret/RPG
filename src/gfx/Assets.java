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
	
	public static BufferedImage blackTile;
	
	public static Font optimusPrincepsSemiBold;
	
	public static BufferedImage background;
	
	public static BufferedImage battleGui;
	public static BufferedImage battleGuiBrackets;
	public static BufferedImage spellDescription;
	
	public static BufferedImage hpBar, xpBar;
	
	//Menu interface
	public static BufferedImage menuBar;
	public static BufferedImage menuButtons;
	
	public static void initAssets() {
		
		//Loading main menu textures
		btn_start = new BufferedImage[2];
		btn_start[0] = ImageLoader.loadImage("/textures/start_button.png");
		btn_start[1] = ImageLoader.loadImage("/textures/start_button_selected.png");
		
		//Loading game menu textures
		menuBar = ImageLoader.loadImage("/textures/menu_buttons/menu_bar.png");
		menuButtons = ImageLoader.loadImage("/textures/menu_buttons/menu_buttons.png");
		
		//Loading battlefield textures
		battleGui = ImageLoader.loadImage("/textures/battlefield_ressources/battle_gui_wo_brackets.png");
		battleGuiBrackets = ImageLoader.loadImage("/textures/battlefield_ressources/battle_gui_brackets.png");
		spellDescription = ImageLoader.loadImage("/textures/battlefield_ressources/spell_descr.png");
		
		hpBar = ImageLoader.loadImage("/textures/battlefield_ressources/hp_bar.png");
		xpBar = ImageLoader.loadImage("/textures/battlefield_ressources/xp_bar.png");

		
		try {
			optimusPrincepsSemiBold = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/OptimusPrincepsSemiBold.ttf"));
			optimusPrincepsSemiBold = optimusPrincepsSemiBold.deriveFont(25f);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		
		background = ImageLoader.loadImage("/textures/background.png");
		//blackTile = ImageLoader.loadImage("/textures/")
		
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
