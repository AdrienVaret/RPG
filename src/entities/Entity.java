package entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import main.Handler;

public abstract class Entity {

	protected Handler handler;
	protected float x,y;
	protected int width, height;
	protected Rectangle bounds;
	protected boolean isActionnable;
	
	public Entity(Handler handler, float x, float y, int width, int height, boolean actionnable) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.handler = handler;
		this.isActionnable = actionnable;
		bounds = new Rectangle(0,0, width, height);
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if (e.equals(this))
				continue;
			if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
				e.contactAction();
				return true;
			}
		}
		return false;
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int) (x + bounds.x + xOffset),
				             (int) (y + bounds.y + yOffset), 
				             bounds.width, bounds.height);
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract void contactAction();
	
}
