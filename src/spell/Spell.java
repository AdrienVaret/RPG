package spell;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import characters.Character.Type;
import gfx.ImageLoader;

public class Spell {

	public enum TypeSpell {
		OFFENSIVE, BUFF
	};
	
	private String name;
	private String description;
	private int damage;
	
	private TypeSpell typeSpell;
	private Type type;
	
	
	private BufferedImage icon;
	
	public Spell(String name, String description, int damage, Type type, TypeSpell typeSpell, String path) {
		this.name = name;
		this.description = description;
		icon = ImageLoader.loadImage(path);
		this.damage = damage;
		this.type = type;
	}
	
	//TODO: buffs
	
	//Getters and setters
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getDamage() {
		return damage;
	}

	public TypeSpell getTypeSpell() {
		return typeSpell;
	}

	public Type getType() {
		return type;
	}

	public BufferedImage getIcon() {
		return icon;
	}
	
	//Creating all spells given a class
	
	public static ArrayList<Spell> createWarriorSpells() {
		
		ArrayList<Spell> spells = new ArrayList<Spell>();
		
		spells.add(new Spell("Entaille", "Inflige des dégats moyens à un ennemi", 
				             15, Type.EARTH, TypeSpell.OFFENSIVE, "/textures/spell_icons/entaille_round.png"));
		
		spells.add(new Spell("Puissance", "Augmente les dégats du lanceur, ajoute 1 icone d'attaque à chaque début de tour pendant 3 tours" 
	               , 25, Type.NEUTRAL, TypeSpell.BUFF, "/textures/spell_icons/puissance_round.png"));
		
		spells.add(new Spell("Brise armure", "Inflige des dégats moyens à un ennemi et diminue tous ses effets défensifs d'un tour", 
	             15, Type.WOOD, TypeSpell.OFFENSIVE, "/textures/spell_icons/brise_armure_round.png"));
		
		spells.add(new Spell("Lame terrestre", "Inflige des dégats de zones", 
	             25, Type.EARTH, TypeSpell.OFFENSIVE, "/textures/spell_icons/lame_terrestre_round.png"));
		
		spells.add(new Spell("Cri de guerre", "Augmente les dommages", 
	               10, Type.EARTH, TypeSpell.BUFF, "/textures/spell_icons/cri_de_guerre_round.png"));
		
		spells.add(new Spell("Tranche", "Inflige de gros dégats à un ennemi", 
	             30, Type.STEEL, TypeSpell.OFFENSIVE, "/textures/spell_icons/tranche_round.png"));
		
		spells.add(new Spell("Lance enflammée", "Inflige des dégats en ligne", 
	             25, Type.FIRE, TypeSpell.OFFENSIVE, "/textures/spell_icons/lance_enflamee_round.png"));
		
		spells.add(new Spell("Danse de l'eau", "Inflige des dégats moyens à un ennemi, ajoute un icone de mouvement à chaque début de tour pendant 3 tours", 
	             25, Type.FIRE, TypeSpell.OFFENSIVE, "/textures/spell_icons/danse_de_l_eau_round.png"));
		
		spells.add(new Spell("Volonté", "Ajoute 3 icones d'attaque", 
	               3, Type.NEUTRAL, TypeSpell.BUFF, "/textures/spell_icons/volonte_round.png"));
		
		spells.add(new Spell("Rage", "Inflige de lourds dégats à un ennemi, augmente les dommages de 15% à chaque utilisation (max 3), bonus perdu si non utilisé", 
	             15, Type.EARTH, TypeSpell.OFFENSIVE, "/textures/spell_icons/rage_round.png"));
		
		return spells;
	}

	
}
