package entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import gfx.Assets;
import gfx.ImageLoader;
import gfx.SpriteSheet;
import main.Handler;

public class NPC extends Creature{

	private String name;
	private String message;
	private ArrayList<ArrayList<BufferedImage>> sprite;
	
	private int direction, frame;
	
	public NPC(Handler handler, float x, float y, int width, int height, String pathImage, String name, String message) {
		
		//Loading Creature attributes
		super(handler, x, y, width, height, TypeAction.KEY_ACTION);
		
		//Loading name and message
		this.name = name;
		this.message = message;
		
		//Loading NPC's sprite
		BufferedImage img = ImageLoader.loadImage(pathImage);
		SpriteSheet sheet = new SpriteSheet(img);
		sprite = Assets.init(sheet);
		
		//Setting initial frame
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
	}

	@Override
	public void action() {
		System.out.println(name + " : " + message);
	}

	public void move() {
		//TODO: takes move pattern and apply it (later)
	}
	
	public void turn(int direction) {
		this.direction = direction;
		frame = 0;
	}
}
