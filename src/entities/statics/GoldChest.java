package entities.statics;

import java.awt.Graphics;

import main.Handler;
import tiles.Tile;

public class GoldChest extends Chest{

	private int nbGolds;
	
	public GoldChest(Handler handler, float x, float y, int direction, int nbGolds) {
		super(handler, x, y, direction);
		
		this.nbGolds = nbGolds;
		
		if (direction == UP) {
			closedSprite = Tile.tiles[6784].getTexture();
			openedSprite = Tile.tiles[6880].getTexture();
		}
		
		else if (direction == DOWN) {
			closedSprite = Tile.tiles[6662].getTexture();
			openedSprite = Tile.tiles[6758].getTexture();
		}
		
		else if (direction == RIGHT) {
			closedSprite = Tile.tiles[6793].getTexture();
			openedSprite = Tile.tiles[6889].getTexture();
		}
		
		else if (direction == LEFT) {
			closedSprite = Tile.tiles[6665].getTexture();
			openedSprite = Tile.tiles[6761].getTexture();
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
			System.out.println("You earned " + nbGolds + " golds !");
			closed = false;
		} else {
			System.out.println("This chest was already opened.");
		}
	}

	
}
