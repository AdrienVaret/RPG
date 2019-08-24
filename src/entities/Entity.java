package entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import entities.creatures.Creature;
import main.Handler;

public abstract class Entity {

	public enum TypeAction {
		CONTACT_ACTION, KEY_ACTION, NONE,
	};
	
	protected Handler handler;
	protected float x,y;
	protected int width, height;
	protected Rectangle bounds;
	protected TypeAction typeAction;
	
	public Entity(Handler handler, float x, float y, int width, int height, TypeAction typeAction) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.handler = handler;
		this.typeAction = typeAction;
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
				if (e.getTypeAction() == TypeAction.CONTACT_ACTION) e.action();
				return true;
			}
		}
		return false;
	}
	
	public void actionWithEntitiesAround(int lastDirection) {
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if (e.equals(this))
				continue;
			
			Rectangle r, r2;
			
			if (lastDirection == 0) { //DOWN
				r = new Rectangle ((int)(x + bounds.x), (int)(y + bounds.y + 2), bounds.width, bounds.height);
			 	r2 = new Rectangle ((int)(e.getX() + e.getBounds().x), (int)(e.getY() + e.getBounds().y), e.getBounds().width, e.getBounds().height);
			 	if(r.intersects(r2) && e.getTypeAction() == TypeAction.KEY_ACTION) {
			 		e.action();
			 		if (e instanceof Creature) ((Creature) e).turn(3); 
			 	}
			}
			
			if (lastDirection == 1) { //LEFT
				r = new Rectangle ((int)(x + bounds.x - 2), (int)(y + bounds.y), bounds.width, bounds.height);
			 	r2 = new Rectangle ((int)(e.getX() + e.getBounds().x), (int)(e.getY() + e.getBounds().y), e.getBounds().width, e.getBounds().height);
			 	if(r.intersects(r2) && e.getTypeAction() == TypeAction.KEY_ACTION) {
			 		e.action();
			 		if (e instanceof Creature) ((Creature) e).turn(2); 
			 	}
			}
			
			if (lastDirection == 2) { //RIGHT
				r = new Rectangle ((int)(x + bounds.x + 2), (int)(y + bounds.y), bounds.width, bounds.height);
			 	r2 = new Rectangle ((int)(e.getX() + e.getBounds().x), (int)(e.getY() + e.getBounds().y), e.getBounds().width, e.getBounds().height);
			 	if(r.intersects(r2) && e.getTypeAction() == TypeAction.KEY_ACTION) {
			 		e.action();
			 		if (e instanceof Creature) ((Creature) e).turn(1); 
			 	}
			}
			
			if (lastDirection == 3) { //UP
				r = new Rectangle ((int)(x + bounds.x), (int)(y + bounds.y - 2), bounds.width, bounds.height);
			 	r2 = new Rectangle ((int)(e.getX() + e.getBounds().x), (int)(e.getY() + e.getBounds().y), e.getBounds().width, e.getBounds().height);
			 	if(r.intersects(r2) && e.getTypeAction() == TypeAction.KEY_ACTION) {
			 		e.action();
			 		if (e instanceof Creature) ((Creature) e).turn(0); 
			 	}
		 	}
		}
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
	
	public TypeAction getTypeAction() {
		return typeAction;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract void action();
	
}
