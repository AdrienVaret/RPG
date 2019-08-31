package item;

import java.awt.image.BufferedImage;

public abstract class Item {

	protected BufferedImage icon;
	protected String name;
	protected String description;
	
	public Item(BufferedImage icon, String name, String description) {
		this.icon = icon;
		this.name = name;
		this.description = description;
	}

	public BufferedImage getIcon() {
		return icon;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
}
