package main;

import java.awt.image.BufferedImage;

import gfx.Assets;
import gfx.ImageLoader;
import gfx.SpriteSheet;

public class Launcher {
	
	public static void initTextures() {
		BufferedImage playerSprite = ImageLoader.loadImage("/textures/characters_sprites/sprite.png");
		BufferedImage tileset = ImageLoader.loadImage("/textures/final_rtp.png");
		
		SpriteSheet playerSheet = new SpriteSheet(playerSprite);
		SpriteSheet tilesetSheet = new SpriteSheet(tileset);
		
		Assets.initAssets();
		
		Assets.setPlayer(playerSheet);
		Assets.setTileset(tilesetSheet);
	}
	
	public static void main(String[] args) {
		initTextures();
		Game game = new Game("Nograd", 800, 800);
		game.start();
	}
}
