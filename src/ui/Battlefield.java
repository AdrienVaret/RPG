package ui;

import java.awt.Color;
import java.awt.Graphics;

import main.Handler;
import tiles.Tile;
import worlds.World;

public class Battlefield extends UIObject{

	private final int GRID_DIMENSION = 25;
	private int halfGrid;
	
	private int xMin, yMin, xMax, yMax;
	private int gridWidth, gridHeight;
	private Handler handler;
	
	public Battlefield(Handler handler, int x, int y) {
		super(0, 0, 800, 800);
		this.handler = handler;
		halfGrid = (GRID_DIMENSION - 1) / 2;
		computeArea(x, y);
	}

	public void computeArea(int x, int y) {
		
		//Computing the player's tile
		int tileX = (int)((x / Tile.TILE_HEIGHT) + 0.5f);
		int tileY = (int)((y / Tile.TILE_WIDTH) + 0.5f);
		
		int xMinLost = 0; 
		int xMaxLost = 0;
		int yMinLost = 0; 
		int yMaxLost = 0;
		
		//Test if there is at least 12 tiles to the left of player
		if (tileX >= halfGrid)
			xMin = tileX - halfGrid;
		else {
			xMin = 0;
			xMinLost = halfGrid - tileX;
		}
		
		//Test if there is at least 12 tiles to the left of player
		if (tileX + halfGrid <= (handler.getWorld().getWidth() - 1))
			xMax = tileX + halfGrid;
		else {
			xMax = handler.getWorld().getWidth() - 1;
			xMaxLost = halfGrid + ((handler.getWorld().getWidth() - 1) - tileX);
		}
		
		//Test if there is at least 12 tiles above player
		if (tileY >= 12)
			yMin = tileY - 12;
		else {
			yMin = 0;
			yMinLost = halfGrid - tileY;
		}
		
		//Test if there is at least 12 tiles below player
		if (tileY + halfGrid <= (handler.getWorld().getHeight() - 1))
			yMax = tileY + halfGrid;
		else {
			yMax = handler.getWorld().getHeight() - 1;
			yMaxLost = halfGrid + ((handler.getWorld().getHeight() - 1) - tileY);
		}
		
		//If some tiles was lost, trying to add them to the opposite side
		if (xMinLost > 0) {
			if (tileX + halfGrid + xMinLost <= (handler.getWorld().getWidth() - 1)) 
				xMax += xMinLost;
			else 
				xMax += ( (handler.getWorld().getWidth() - 1) - tileX);	
		}
		
		if (xMaxLost > 0) {
			if (tileX - halfGrid - xMaxLost >= 0)
				xMin -= xMaxLost;
			else 
				xMin -= xMin;
		}
		
		if (yMinLost > 0) {
			if (tileY + halfGrid + yMinLost <= (handler.getWorld().getHeight() - 1)) 
				yMax += yMinLost;
			else 
				yMax += ( (handler.getWorld().getHeight() - 1) - tileY);	
		}
		
		if (yMaxLost > 0) {
			if (tileY - halfGrid - yMaxLost >= 0)
				yMin -= yMaxLost;
			else 
				yMin -= yMin;
		}
		
		gridWidth = xMax - xMin;
		gridHeight = yMax - yMin;
	}
	
	public void drawGrid(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		for (int i = 0 ; i <= GRID_DIMENSION * Tile.TILE_WIDTH ; i += Tile.TILE_WIDTH) {
			
			if (i == 0 || i == GRID_DIMENSION * Tile.TILE_WIDTH)
				g.drawLine(i, 0, i, GRID_DIMENSION * Tile.TILE_WIDTH);
			else {
				g.drawLine(i - 1, 0, i - 1, GRID_DIMENSION * Tile.TILE_WIDTH);
				g.drawLine(i, 0, i, GRID_DIMENSION * Tile.TILE_WIDTH);
				g.drawLine(i + 1, 0, i + 1, GRID_DIMENSION * Tile.TILE_WIDTH);
			}
			
			if (i == 0 || i == GRID_DIMENSION * Tile.TILE_HEIGHT)
				g.drawLine(0, i, GRID_DIMENSION * Tile.TILE_HEIGHT, i);
			else {
				g.drawLine(0, i - 1, GRID_DIMENSION * Tile.TILE_HEIGHT, i - 1);
				g.drawLine(0, i, GRID_DIMENSION * Tile.TILE_HEIGHT, i);
				g.drawLine(0, i + 1, GRID_DIMENSION * Tile.TILE_HEIGHT, i + 1);
			}
		}
	}
	
	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		for (int y = yMin ; y < yMax ; y ++) {
			for (int x = xMin ; x < xMax ; x++) {
				for (int layer = 0 ; layer < 3 ; layer ++) {
					Tile tile = handler.getWorld().getTile(y,x, layer);
					if (tile != null)
						tile.render(g, (int)(x * Tile.TILE_WIDTH), (int)(y * Tile.TILE_HEIGHT));
				}
			}
		}
		drawGrid(g);
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		
	}

}
