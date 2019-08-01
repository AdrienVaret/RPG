package worlds;

import java.awt.Graphics;

import main.Game;
import tiles.Tile;
import utils.Utils;

public class World {

	private Game game;
	
	private int width;
	private int height;
	
	private int spawnX;
	private int spawnY;
	
	private int [][] tiles;
	
	public World (Game game, String path) {
		loadWorld(path);
		this.game = game;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		for (int i = 0 ; i < height ; i++) {
			for (int j = 0 ; j < width ; j++) {
				getTile(i,j).render(g, (int)(j * Tile.TILE_WIDTH - game.getGameCamera().getxOffset()), 
						               (int)(i * Tile.TILE_HEIGHT - game.getGameCamera().getyOffset()));
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		Tile t = Tile.tiles[tiles[x][y]];
		if (t == null)
			return Tile.dirtTile;
		return t;
	}
	
	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		
		String [] tokens = file.split("\\s+");
		
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[height][width];
		
		for (int y = 0 ; y < height ; y++) {
			for (int x = 0 ; x < width ; x++) {
				tiles[y][x] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}
}
