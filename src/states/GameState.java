package states;

import java.awt.Graphics;
import entities.creatures.Player;
import main.Handler;
import worlds.World;

public class GameState extends State{
	
	private Player player;
	private World world;
	
	public GameState(Handler handler) {
		super(handler);
		world = new World(handler, "res/worlds/test_big_world_layer.map");
		handler.setWorld(world);
		player = new Player(handler, 3008, 512); //100, 100 AVT
		
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
