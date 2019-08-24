package entities.statics;

import java.awt.Graphics;

import main.Handler;
import tiles.Tile;

public class StuffChest extends Chest{

	private int idItem;
	
	public StuffChest(Handler handler, float x, float y, int direction, int idItem) {
		super(handler, x, y, direction);
		
		this.idItem = idItem;
		
		if (direction == UP) {
			closedSprite = Tile.tiles[6546].getTexture();
			openedSprite = Tile.tiles[6642].getTexture();
		}
		
		else if (direction == DOWN) {
			closedSprite = Tile.tiles[6418].getTexture();
			openedSprite = Tile.tiles[6514].getTexture();
		}
		
		else if (direction == RIGHT) {
			closedSprite = Tile.tiles[6534].getTexture();
			openedSprite = Tile.tiles[6630].getTexture();
		}
		
		else if (direction == LEFT) {
			closedSprite = Tile.tiles[6406].getTexture();
			openedSprite = Tile.tiles[6502].getTexture();
		}
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		
		if (closed) {
			g.drawImage(closedSprite, (int)(x - handler.getGameCamera().getxOffset()), 
                        (int)(y - handler.getGameCamera().getyOffset()), 
                         width, height, null);
		}
		
		else {
			g.drawImage(openedSprite, (int)(x - handler.getGameCamera().getxOffset()), 
                    (int)(y - handler.getGameCamera().getyOffset()), 
                     width, height, null);
		}
	}

	@Override
	public void action() {
		if (closed) {
			System.out.println("You found item " + idItem + "!");
			closed = false;
		} else {
			System.out.println("This chest was already opened.");
		}
	}

	
}
