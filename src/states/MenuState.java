package states;

import java.awt.Graphics;

import main.Game;
import main.Handler;

public class MenuState extends State{

	public MenuState(Handler handler) {
		super(handler);
	}
	
	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		g.drawString("Main menu", 185, 25);
	}

}
