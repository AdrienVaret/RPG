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
	
	/*
	 * Used for define tile's solidity
	 * 0 : non solid
	 * 1 : solid
	 * 2 : transparent (hide the player)
	 */
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
			
			r.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Creating tiles objects
		int id = 0;
		for(int i = 0 ; i < textures.size() ; i++) {
			for (int j = 0 ; j < textures.get(i).size() ; j++) {
				int solidity = solidities.get(id);
				tiles[id] = new Tile(textures.get(i).get(j), id, solidity);
				id ++;
			}
		}
	}
	
	public Tile(BufferedImage texture, int id, int solidity) {
		this.texture = texture;
		this.id = id;
		this.solidity = solidity;
		tiles[id] = this;
	}
	
	public int getId() {
		return id;
	}
	
	public boolean isSolid() {
		return (solidity == 1);
	}
	
	public boolean hidePlayer() {
		return (solidity == 2);
	}
	
	public void tick() {
		
	}
	
	public BufferedImage getTexture() {
		return texture;
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}
}
