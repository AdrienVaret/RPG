package tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Tile {

	public static Tile[] tiles = new Tile[15000];

	public static final int TILE_WIDTH = 32;
	public static final int TILE_HEIGHT = 32;
	
	protected BufferedImage texture;
	protected final int id;
	
	private int solidity;
	
	public static void initTileset(ArrayList<ArrayList<BufferedImage>> textures) {
		
		//Reading solidity file
		ArrayList<Integer> solidities = new ArrayList<Integer>();
		
		try {
			BufferedReader r = new BufferedReader(new FileReader(new File("res/textures/solidity.data")));
			String line = null;
			
			while((line = r.readLine()) != null) {
				String [] splittedLine = line.split(" ");
				solidities.add(Integer.parseInt(splittedLine[1]));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Creating tiles objects
		int id = 0;
		for(int i = 0 ; i < textures.size() ; i++) {
			for (int j = 0 ; j < textures.get(i).size() ; j++) {
				int solidity = solidities.get(id);
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
