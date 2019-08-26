package characters;

import java.awt.image.BufferedImage;

public abstract class Character {

	public enum Type {
		NEUTRAL, FIRE, EARTH, STEEL, WATER, WOOD, LIGHT, DARK
	};
	
	//Character's sprite sheet
	protected BufferedImage sprite;
	
	//Character's basics statistics
	protected String name;
	protected String description;
	protected int level;
	protected long experience;
	protected Type type;
	
	public Character(String name, String description, Type type, int level, int experience) {
		this.name = name;
		this.description = description;
		this.level = level;
		this.experience = experience;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
}
