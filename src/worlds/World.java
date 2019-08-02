package worlds;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.regex.Pattern;

import main.Game;
import main.Handler;
import tiles.Tile;
import utils.Utils;

public class World {

	private Handler handler;
	
	private int width;
	private int height;
	
	private int spawnX;
	private int spawnY;
	
	private int [][] tiles;
	
	//private ArrayList<ArrayList<ArrayList<Integer>>> tiles2;
	private int [][][] tiles2;
	
	public World (Handler handler, String path) {
		//loadWorld(path);
		/**/
		loadWorld2(path);
		this.handler = handler;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILE_WIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILE_HEIGHT);
		int yEnd = (int) Math.min(width, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);
/*		
		for (int y = yStart ; y < yEnd ; y ++) {
			for (int x = xStart ; x < xEnd ; x++) {
				getTile(y,x).render(g, (int)(x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()), 
						               (int)(y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
*/
		
		for (int y = yStart ; y < yEnd ; y ++) {
			for (int x = xStart ; x < xEnd ; x++) {
				for (int layer = 0 ; layer < 3 ; layer ++) {
					Tile tile = getTile2(y,x, layer);
					if (tile != null)
						tile.render(g, (int)(x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()), 
						               (int)(y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
				}
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		
		if (x < 0 || y < 0 || x >= height || y >= width)
			return Tile.grassTile;
		
		Tile t = Tile.tiles[tiles[x][y]];
		if (t == null)
			return Tile.dirtTile;
		return t;
	}
	
	public Tile getTile2(int x, int y, int layer) {
		
		//Coordinates conditions
		if (x < 0 || y < 0 || x >= height || y >= width)
			return Tile.tiles[0];
		
		if (tiles2[x][y][layer] == -1)
			//return Tile.tiles[0];
			return null;
		
		Tile t = Tile.tiles[tiles2[x][y][layer]];
		if (t == null)
			return Tile.tiles[0];
		
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
	
	private void loadWorld2(String path) {
		String file = Utils.loadFileAsString(path);
		
		String [] tokens = file.split(Pattern.quote("\n"));
		
		String [] dimensions = tokens[0].split(" ");
		String [] initialPosition = tokens[1].split(" ");
		
		width = Utils.parseInt(dimensions[0]);
		height = Utils.parseInt(dimensions[1]);
		
		spawnX = Utils.parseInt(initialPosition[0]);
		spawnY = Utils.parseInt(initialPosition[1]);
		
		int indexLine = 2;
		tiles2 = new int[height][width][3];
		
		for (int layer = 0 ; layer < 3 ; layer ++) {
			for (int line = 0 ; line < height ; line ++) {
				String [] splittedLine = tokens[indexLine].split(" ");
				for (int column = 0 ; column < width ; column ++) {
					int tileId = Utils.parseInt(splittedLine[column]) - 1;
					tiles2[line][column][layer] = tileId;
				}
				indexLine ++;
			}
		}
	}
}
