package entities.statics;

import entities.Entity;
import main.Handler;

public abstract class StaticEntity extends Entity{
	
	public StaticEntity(Handler handler, float x, float y, int width, int height, TypeAction typeAction) {
		super(handler, x, y, width, height, typeAction);
	}
	
}
  