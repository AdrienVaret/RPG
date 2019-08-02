package main;

import gfx.GameCamera;
import input.KeyManager;
import worlds.World;

public class Handler {

	private Game game;
	private World world;
	
	public Handler(Game game) {
		 this.game = game;
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
	
	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	
	public GameCamera getGameCamera() {
		return game.getGameCamera();
	}
}
