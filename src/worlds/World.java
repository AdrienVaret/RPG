package worlds;

import java.awt.Graphics;
import java.util.regex.Pattern;

import main.Handler;
import tiles.Tile;
import utils.Utils;

public class World {

	private Handler handler;
	
	private int width;
	private int height;
	
	@SuppressWarnings("unused")
	private int spawnX;
	@SuppressWarnings("unused")
	private int spawnY;
	
	private int [][][] tiles;
	
	public World (Handler handler, String path) {
		loadWorld(path);
		this.handler = handler;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILE_WIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILE_HEIGHT);
		int yEnd = (int) Math.min(width, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);

		for (int y = yStart ; y < yEnd ; y ++) {
			for (int x = xStart ; x < xEnd ; x++) {
				for (int layer = 0 ; layer < 3 ; layer ++) {
					Tile tile = getTile(y,x, layer);
					if (tile != null)
						tile.render(g, (int)(x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()), 
						               (int)(y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
				}
			}
		}
	}
	
	public Tile getTile(int x, int y, int layer) {
		
		//Coordinates conditions
		if (x < 0 || y < 0 || x >= height || y >= width)
			return Tile.tiles[0];
		
		//If there is no tile on this coordinates/layer
		if (tiles[x][y][layer] == -1)
			return null;
		
		//Normal case
		Tile t = Tile.tiles[tiles[x][y][layer]];
		if (t == null)
			return Tile.tiles[0];
		
		return t;
	}
	
	private void loadWorld(String path) {
		//Loading world file as a string
		String file = Utils.loadFileAsString(path);
		
		//Splitting the string line by line
		String [] tokens = file.split(Pattern.quote("\n"));
		
		//Retrieving map's dimensions and spawn position
		String [] dimensions = tokens[0].split(" ");
		String [] initialPosition = tokens[1].split(" ");
		
		width = Utils.parseInt(dimensions[0]);
		height = Utils.parseInt(dimensions[1]);
		
		spawnX = Utils.parseInt(initialPosition[0]);
		spawnY = Utils.parseInt(initialPosition[1]);
		
		int indexLine = 2;
		tiles = new int[height][width][3];
		
		//Retrieving all tiles for each layers
		for (int layer = 0 ; layer < 3 ; layer ++) {
			for (int line = 0 ; line < height ; line ++) {
				String [] splittedLine = tokens[indexLine].split(" ");
				for (int column = 0 ; column < width ; column ++) {
					int tileId = Utils.parseInt(splittedLine[column]) - 1;
					tiles[line][column][layer] = tileId;
				}
				indexLine ++;
			}
		}
	}
}
