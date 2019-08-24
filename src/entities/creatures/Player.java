package entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import entities.Entity;
import entities.Entity.TypeAction;
import gfx.Assets;
import main.Handler;

public class Player extends Creature{
	
	
	//Constants variables
	private final int DEFAULT_ANIM_SPEED = 250;
	
	//Timer variable
	private long timer, lastTime;
	
	//Direction variables
	int lastDirection;
	private int direction, frame;
	private int ANIM_SPEED = DEFAULT_ANIM_SPEED;
	
	//Action checking
	private boolean action = false;
	
	public Player(Handler handler, float x, float y) {
	
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT, TypeAction.NONE);
		
		//Defining collisions bounds
		bounds.x = 9;
		bounds.y = 21;
		bounds.width = 14;
		bounds.height = 11;
		
		//Initialising direction
		lastDirection = DOWN;
		direction = DOWN;
		frame = 0;
		
		//Initialising timer
		timer = 0;
		lastTime = System.currentTimeMillis();
	}

	@Override
	public void tick() {
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
	}
	
	private void getInput() {
		 xMove = 0;
		 yMove = 0;
		 
		 //Checking action
		 if (handler.getKeyManager().action && !action) {
			 actionWithEntitiesAround(lastDirection);
			 action = true;
		 } else if (!handler.getKeyManager().action){
			 action = false;
		 }
		 
		 
		 //Setting running speed modifications
		 if (handler.getKeyManager().run) {
			 speed = DEFAULT_SPEED * 2;
			 ANIM_SPEED = DEFAULT_ANIM_SPEED / 4;
		 }
		 else {
			 speed = DEFAULT_SPEED;
			 ANIM_SPEED = DEFAULT_ANIM_SPEED;
		 }
		 
		 //Moving player and updating frame
		 if (handler.getKeyManager().up) {
				
				if (lastDirection != UP) {
					
					timer = 0;
					lastTime = System.currentTimeMillis();
					direction = UP;
					frame = 0;
					lastDirection = UP;
					
				} else {
					
					timer += System.currentTimeMillis() - lastTime;
					lastTime = System.currentTimeMillis();
					
					if (timer > ANIM_SPEED) {
						timer = 0;
						if (frame + 1 > 2) 
							frame = 0;
						else
							frame ++;
					}
				}
				
				yMove = -speed;
			}
			
			if (handler.getKeyManager().down) {
				
				if (lastDirection != DOWN) {
					
					timer = 0;
					lastTime = System.currentTimeMillis();
					direction = DOWN;
					frame = 0;
					lastDirection = DOWN;
					
				} else {
					
					
					timer += System.currentTimeMillis() - lastTime;
					lastTime = System.currentTimeMillis();
					
					if (timer > ANIM_SPEED) {
						timer = 0;
						if (frame + 1 > 2) 
							frame = 0;
						else
							frame ++;
					}
				}
				
				yMove = speed;
			}
			
			if (handler.getKeyManager().left) {
				
				if (lastDirection != LEFT) {
					
					timer = 0;
					lastTime = System.currentTimeMillis();
					direction = LEFT;
					frame = 0;
					lastDirection = LEFT;
					
				} else {
					timer += System.currentTimeMillis() - lastTime;
					lastTime = System.currentTimeMillis();
					
					if (timer > ANIM_SPEED) {
						timer = 0;
						if (frame + 1 > 2) 
							frame = 0;
						else
							frame ++;
					}
				}
				
				xMove = -speed;
			}
			
			if (handler.getKeyManager().right) {
				
				if (lastDirection != RIGHT) {
					
					timer = 0;
					lastTime = System.currentTimeMillis();
					direction = RIGHT;
					frame = 0;
					lastDirection = RIGHT;
					
				} else {
					timer += System.currentTimeMillis() - lastTime;
					lastTime = System.currentTimeMillis();
					
					if (timer > ANIM_SPEED) {
						timer = 0;
						if (frame + 1 > 2) 
							frame = 0;
						else
							frame ++;
					}
				}
				
				xMove = speed;
			}
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.player.get(direction).get(frame), (int)(x - handler.getGameCamera().getxOffset()), 
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}

	public void action() {
		//DO_NOTHING
	}
	
	public void turn(int direction) {
		//DO_NOTHING
	}
}
