package entities.statics;

import java.awt.image.BufferedImage;

import main.Handler;
import tiles.Tile;

public abstract class Chest extends StaticEntity{

	protected BufferedImage closedSprite, openedSprite;
	boolean closed;
	
	public Chest(Handler handler, float x, float y, int direction) {
		super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT, TypeAction.KEY_ACTION);
		closed = true;
	}

	
}
