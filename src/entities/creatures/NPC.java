package entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Entity;
import gfx.Assets;
import gfx.ImageLoader;
import gfx.SpriteSheet;
import main.Handler;
import tiles.Tile;

public class NPC extends Entity{

	private String message;
	private ArrayList<ArrayList<BufferedImage>> sprite;
	
	private int direction, frame;
	
	public NPC(Handler handler, float x, float y, int width, int height, String pathImage, String message) {
		super(handler, x, y, width, height, TypeAction.KEY_ACTION);
		this.message = message;
		
		//Loading NPC's sprite
		BufferedImage img = ImageLoader.loadImage(pathImage);
		SpriteSheet sheet = new SpriteSheet(img);
		sprite = Assets.init(sheet);
		
		//Setting initial direction
		direction = 0;
		frame = 0;
	}

	@Override
	public void tick() {
		
	}
	
	private BufferedImage getCurrentFrame() {
		return sprite.get(direction).get(frame);
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentFrame(), (int)(x - handler.getGameCamera().getxOffset()), 
                (int)(y - handler.getGameCamera().getyOffset()), 
                width, height, null);
		
		g.setColor(Color.red);
		Rectangle r = this.getCollisionBounds(0, 0);
		g.drawRect((int)(x + r.x), (int)(y + r.y), r.width, r.height);
	}

	@Override
	public void action() {
		System.out.println(message);
	}

}
