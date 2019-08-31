package main;

import gfx.GameCamera;
import input.KeyManager;
import input.MouseManager;
import ui.Menu;
import worlds.World;

public class Handler {

	private Game game;
	private World world;
	private Menu menu;
	
	public Handler(Game game) {
		 this.game = game;
		 menu = new Menu();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
	
	public int getWidth() {
		return game.width;
	}
	
	public int getHeight() {
		return game.height;
	}
	
	public Menu getMenu() {
		return menu;
	}
	
	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	
	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}
	
	public GameCamera getGameCamera() {
		return game.getGameCamera();
	}
}
