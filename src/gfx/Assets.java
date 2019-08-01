package gfx;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Assets {

	private static final int width = 32, height = 32;
	
	public static ArrayList<ArrayList<BufferedImage>> player;
	public static ArrayList<ArrayList<BufferedImage>> tileset;
	
	//Temporary
	public static BufferedImage grassTile;
	public static BufferedImage dirtTile;
	public static BufferedImage rockTile;
	
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
		tileset = init(sheet);
		grassTile = tileset.get(15).get(1);
		dirtTile = tileset.get(12).get(0);
		rockTile = tileset.get(32).get(7);
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
