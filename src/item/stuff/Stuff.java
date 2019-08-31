package item.stuff;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import buffs.BuffItem;
import item.Item;

public class Stuff extends Item{

	protected ArrayList<BuffItem> buffs;
	
	public Stuff(BufferedImage icon, String name, String description, ArrayList<BuffItem> buffs) {
		super(icon, name, description);
		this.buffs = buffs;
	}

	public ArrayList<BuffItem> getBuffs(){
		return buffs;
	}
	
	public BuffItem getBuff(int index) {
		return buffs.get(index);
	}
}
