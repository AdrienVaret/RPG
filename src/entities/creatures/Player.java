package entities.creatures;

import java.awt.Graphics;

import gfx.Assets;
import main.Game;

public class Player extends Creature{
	
	int DOWN = 0;
	int LEFT = 1;
	int RIGHT = 2;
	int UP = 3;

	int lastDirection; //0 : DOWN, 1 : LEFT, 2 : RIGHT, 3 : UP
	
	private int row, column;
	
	//private Game game;
	
	public Player(Game game, float x, float y) {
		super(game, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		//this.game = game;
		lastDirection = DOWN;
		row = DOWN;
		column = 0;
	}

	@Override
	public void tick() {
		getInput();
		move();
		game.getGameCamera().centerOnEntity(this);
	}
	
	private void getInput() {
		 xMove = 0;
		 yMove = 0;
		 
		 if (game.getKeyManager().up) {
				
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
			
			if (game.getKeyManager().down) {
				
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
			
			if (game.getKeyManager().left) {
				
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
			
			if (game.getKeyManager().right) {
				
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
		g.drawImage(Assets.player.get(row).get(column), (int)(x - game.getGameCamera().getxOffset()), 
				(int) (y - game.getGameCamera().getyOffset()), width, height, null); //.get 0, 0 avant
	}

}
