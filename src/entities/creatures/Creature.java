package entities.creatures;

import entities.Entity;
import main.Handler;
import tiles.Tile;

public abstract class Creature extends Entity{

	public static final int DEFAULT_HEALTH = 10;
	public static final float DEFAULT_SPEED = 2.0f; //3.0f initialement
	public static final int DEFAULT_CREATURE_WIDTH = 32; 
	public static final int DEFAULT_CREATURE_HEIGHT = 32;
	
	protected int health;
	protected float speed;
	
	protected float xMove;
	protected float yMove;
	
	public Creature(Handler handler, float x, float y, int width, int height, TypeAction typeAction) {
		super(handler, x, y, width, height, typeAction);
		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}
	
	public void move() {
		if (!checkEntityCollisions(xMove, 0f))
			moveX();
		if (!checkEntityCollisions(0f, yMove))
			moveY();
	}
	
	public void moveX() {
		
		if (xMove > 0) { //RIGHT
			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;
			
			if (!collisionWithTile(tx, (int)(y + bounds.y) / Tile.TILE_HEIGHT) && 
				!collisionWithTile(tx, (int)(y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
				x += xMove;
				
			} else {
				
				x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
			}
		} 
		
		else if (xMove < 0) { //LEFT
			int tx = (int) (x + xMove + bounds.x) / Tile.TILE_WIDTH;
			
			if (!collisionWithTile(tx, (int)(y + bounds.y) / Tile.TILE_HEIGHT) && 
				!collisionWithTile(tx, (int)(y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
				x += xMove;
				
			} else {
				 x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
			}
		}
		
	}
	
	public void moveY() {
		
		if (yMove < 0) { //UP
			int ty = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT;
			
			if (!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) &&
				!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
				y += yMove;
			} else {
				y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
			}
		}
		
		else if (yMove > 0) { //DOWN
			int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;
			
			if (!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) &&
				!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
				y += yMove;
			} else {
				y = ty * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
			}
		}
		
	}
	
	protected boolean collisionWithTile(int x, int y) {		
		for (int layer = 0 ; layer < 3 ; layer ++) {
			Tile tile = handler.getWorld().getTile(y, x, layer);
			if (tile != null && tile.isSolid()) return true;
		}
		
		return false;
	}

	public abstract void turn(int direction);
}
