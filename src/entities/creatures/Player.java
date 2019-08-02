package entities.creatures;

import java.awt.Color;
import java.awt.Graphics;

import gfx.Assets;
import main.Game;
import main.Handler;

public class Player extends Creature{
	
	int DOWN = 0;
	int LEFT = 1;
	int RIGHT = 2;
	int UP = 3;

	int lastDirection; //0 : DOWN, 1 : LEFT, 2 : RIGHT, 3 : UP
	
	private int row, column;
	
	//private Game game;
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		bounds.x = 9;
		bounds.y = 21;
		bounds.width = 14;
		bounds.height = 11;
		
		//bounds.x = 0;
		//bounds.y = 0;
		//bounds.width = 32;
		//bounds.height = 32;
		
		//this.game = game;
		lastDirection = DOWN;
		row = DOWN;
		column = 0;
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
		 
		 if (handler.getKeyManager().up) {
				
				if (lastDirection != UP) {
					row = UP;
					column = 0;
					lastDirection = UP;
				} else {
					if (column + 1 > 2) 
						column = 0;
					else
						column ++;
				}
				
				yMove = -speed;
			}
			
			if (handler.getKeyManager().down) {
				
				if (lastDirection != DOWN) {
					row = DOWN;
					column = 0;
					lastDirection = DOWN;
				} else {
					if (column + 1 > 2) 
						column = 0;
					else
						column ++;
				}
				
				yMove = speed;
			}
			
			if (handler.getKeyManager().left) {
				
				if (lastDirection != LEFT) {
					row = LEFT;
					column = 0;
					lastDirection = LEFT;
				} else {
					if (column + 1 > 2) 
						column = 0;
					else
						column ++;
				}
				
				xMove = -speed;
			}
			
			if (handler.getKeyManager().right) {
				
				if (lastDirection != RIGHT) {
					row = RIGHT;
					column = 0;
					lastDirection = RIGHT;
				} else {
					if (column + 1 > 2) 
						column = 0;
					else
						column ++;
				}
				
				xMove = speed;
			}
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.player.get(row).get(column), (int)(x - handler.getGameCamera().getxOffset()), 
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null); //.get 0, 0 avant
		
		g.setColor(Color.RED);
		//g.fillRect((int)(x + bounds.x - handler.getGameCamera().getxOffset()),
		//		   (int)(y + bounds.y - handler.getGameCamera().getyOffset()),
		//		   bounds.width, bounds.height);
	}

}
