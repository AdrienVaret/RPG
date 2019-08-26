package states;

import java.awt.Graphics;

import main.Handler;
import ui.Battlefield;
import ui.UIManager;

public class BattleState extends State{

	private Battlefield battlefield;
	
	public BattleState(Handler handler, int x, int y) {
		super(handler);
		battlefield = new Battlefield(handler, x, y);
		UIManager uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		uiManager.addObject(battlefield);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		battlefield.render(g);
	}

}
