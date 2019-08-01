package main;

import java.awt.image.BufferedImage;

import gfx.Assets;
import gfx.ImageLoader;
import gfx.SpriteSheet;

public class Launcher {
	
	public static void initTextures() {
		BufferedImage playerSprite = ImageLoader.loadImage("/textures/sprite.png");
		BufferedImage tileset = ImageLoader.loadImage("/textures/tileset.png");
		
		SpriteSheet playerSheet = new SpriteSheet(playerSprite);
		SpriteSheet tilesetSheet = new SpriteSheet(tileset);
		
		Assets.setPlayer(playerSheet);
		Assets.setTileset(tilesetSheet);
	}
	
	public static void main(String[] args) {
		initTextures();
		Game game = new Game("Tiled Game", 500, 382);
		game.start();
	}
}
