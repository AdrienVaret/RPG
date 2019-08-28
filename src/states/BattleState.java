package states;

import java.awt.Graphics;
import java.util.ArrayList;
import characters.Character;

import main.Handler;
import ui.UIManager;
import ui.battle.Battlefield;

public class BattleState extends State{

	private Battlefield battlefield;
	
	public BattleState(Handler handler, int x, int y, ArrayList<Character> team) {
		super(handler);
		battlefield = new Battlefield(handler, x, y, team);
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
