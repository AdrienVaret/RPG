package states;

import java.awt.Graphics;
import entities.creatures.Player;
import main.Game;
import main.Handler;
import tiles.Tile;
import worlds.World;

public class GameState extends State{
	
	private Player player;
	private World world;
	
	public GameState(Handler handler) {
		super(handler);
		//world = new World(handler, "res/worlds/world2.txt");
		world = new World(handler, "res/worlds/test_world_layer.map");
		handler.setWorld(world);
		player = new Player(handler, 100, 100);
		
	}
	
	@Override
	public void tick() {
		world.tick();
		player.tick();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		player.render(g);
	}

}
