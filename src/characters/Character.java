package characters;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import spell.Spell;

public abstract class Character {

	public enum Type {
		NEUTRAL, FIRE, EARTH, STEEL, WATER, WOOD, LIGHT, DARK
	};
	
	//Classe's graphics
	protected BufferedImage sprite;
	public BufferedImage face;
	
	//Character's basics statistics
	protected String name;
	protected String description;
	protected int level;
	protected long experience;
	protected Type type;
	
	//Classe's current stats without stats
	protected int HP, ATK, DEF, SPEED;
	
	//Character's spells
	protected ArrayList<Spell> spells;
	protected ArrayList<Spell> allSpells;
	
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
	
	public ArrayList<Spell> getSpells() {
		return spells;
	}
	
	public ArrayList<Spell> getAllSpells() {
		return allSpells;
	}
	
	public BufferedImage getSprite() {
		return sprite;
	}
	
	public BufferedImage getFace() {
		return face;
	}
}
