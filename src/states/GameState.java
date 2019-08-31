package states;

import java.awt.Graphics;

import gfx.Assets;
import main.Handler;
import ui.Menu;
import ui.UIManager;
import worlds.World;

public class GameState extends State{
	
	private World world;
	
	public GameState(Handler handler) {
		super(handler);
		world = new World(handler, "res/worlds/test_big_world_layer.map");
		handler.setWorld(world);
		
		UIManager uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		uiManager.addObject(handler.getMenu());
	}
	
	@Override
	public void tick() {
		world.tick();
	}
	
	@Override
	public void render(Graphics g) {
		world.render(g);
		handler.getMenu().render(g);
	}

}
