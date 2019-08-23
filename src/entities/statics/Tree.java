package entities.statics;

import java.awt.Graphics;

import main.Handler;
import tiles.Tile;

public class Tree extends StaticEntity{

	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Tile.tiles[1062].getTexture(), (int)(x - handler.getGameCamera().getxOffset()), 
				                                   (int)(y - handler.getGameCamera().getyOffset()), 
				                                   width, height, null);
	}

	public void contactAction() {
		System.out.println("Attention à l'arbre !");
	}
}
