package ui;

import java.awt.Graphics;

import gfx.Assets;

public class Menu extends UIObject{

	//public static Menu menu = new Menu();
	
	private boolean visible;
	
	public Menu() {
		super(0, 0, 800, 161);
		visible = true;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {	
		g.drawImage(Assets.menuBar, 0, 0, 299, 161, null);
		if (visible)
			g.drawImage(Assets.menuButtons, 0, 0, 299, 161, null);
	}

	@Override
	public void onClick() {
		
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void switchVisibility() {
		visible = !visible;
	}

}
