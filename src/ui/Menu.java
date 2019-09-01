package ui;

import java.awt.Color;
import java.awt.Graphics;
import characters.Character;
import gfx.Assets;

public class Menu extends UIObject{
	
	private boolean visible;
	private Character currentCharacter;
	
	public Menu() {
		super(0, 0, 800, 161);
		visible = true;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(8, 16, 126, 126);
		if (currentCharacter != null)
			g.drawImage(currentCharacter.getFace(), 8, 16, 126, 126, null);
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

	public void setCurrentCharacter(Character currentCharacter) {
		this.currentCharacter = currentCharacter;
	}
}
