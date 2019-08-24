package entities.statics;

import java.awt.Graphics;

import main.Handler;
import tiles.Tile;

public class Warp extends StaticEntity{

	public Warp(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT, TypeAction.CONTACT_ACTION);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Tile.tiles[0].getTexture(), (int)(x - handler.getGameCamera().getxOffset()), 
                (int)(y - handler.getGameCamera().getyOffset()), 
                width, height, null);
	}

	@Override
	public void action() {
		System.out.println("Contact with warping zone !");
	}

}
