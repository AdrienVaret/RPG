package states;

import java.awt.Graphics;

import main.Game;

public class MenuState extends State{

	public MenuState(Game game) {
		super(game);
	}
	
	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		g.drawString("Main menu", 185, 25);
	}

}
