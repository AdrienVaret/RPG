package tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gfx.Assets;
import gfx.ImageLoader;
import gfx.SpriteSheet;

public class Tile {

	public static Tile[] tiles = new Tile[2048];
	public static Tile grassTile = new GrassTile(0);
	public static Tile dirtTile = new DirtTile(1);
	public static Tile rockTile = new RockTile(2);
	
	public static final int TILE_WIDTH = 32;
	public static final int TILE_HEIGHT = 32;
	
	protected BufferedImage texture;
	protected final int id;
	
	public static void initTileset(ArrayList<ArrayList<BufferedImage>> textures) {
		int id = 0;
		for(int i = 0 ; i < textures.size() ; i++) {
			for (int j = 0 ; j < textures.get(i).size() ; j++) {
				tiles[id] = new Tile(textures.get(i).get(j), id);
				id ++;
			}
		}
	}
	
	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		tiles[id] = this;
	}
	
	public int getId() {
		return id;
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}
}
