package ui.battle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import characters.Character;
import gfx.Assets;
import main.Handler;
import spell.Spell;
import states.State;
import tiles.Tile;
import ui.Menu;
import ui.UIManager;
import ui.UIObject;
import utils.Utils;

public class Battlefield extends UIObject{

	private final int GRID_DIMENSION = 25;
	private int halfGrid;
	
	private int xMin, yMin, xMax, yMax;
	private int gridWidth, gridHeight;
	private Handler handler;
	
	//Bounds
	private Rectangle boundsGridBattle;
	private Rectangle boundsSpells;
	private Rectangle boundsItemButton;
	private Rectangle boundsRunButton;
	
	//Spell description rectangle
	private Rectangle spellDescriptionRectangle;
	
	private Cell[][] grid;
	
	//Team variables
	private ArrayList<Character> team;
	private int playerTurn;
	
	private Spell hoveringSpell;
	
	public Battlefield(Handler handler, int x, int y, ArrayList<Character> team) {
		super(0, 0, 1200, 800);
		
		this.handler = handler;
		handler.getGame().resize(1200, 822);
		
		//Defining bounds
		boundsGridBattle = new Rectangle(0, 0, GRID_DIMENSION * Tile.TILE_WIDTH, GRID_DIMENSION * Tile.TILE_HEIGHT);
		boundsSpells = new Rectangle(861, 640, 273, 76);
		boundsItemButton = new Rectangle(874, 732, 121, 47);
		boundsRunButton = new Rectangle (1005, 732, 121, 47);
		
		spellDescriptionRectangle = new Rectangle(887, 284, 212, 20); //x = 900 initialement
		
		halfGrid = (GRID_DIMENSION - 1) / 2;
		grid = new Cell[GRID_DIMENSION][GRID_DIMENSION];
		this.team = team;
		
		playerTurn = 0;
		
		computeArea(x, y);
		initGrid();
	}

	public void initGrid() {
		int line = 0;
		for (int y = yMin ; y <= yMax ; y ++) {
			int column = 0;
			for (int x = xMin ; x <= xMax ; x++) {
				boolean isSolid = false;
				boolean hidePlayer = false;
				for (int layer = 0 ; layer < 3 ; layer ++) {
					Tile tile = handler.getWorld().getTile(y,x, layer);
					
					if (tile != null) {
						if (tile.isSolid()) isSolid = true;
						if (tile.hidePlayer()) hidePlayer = true;
					}
				}
				grid[column][line] = new Cell(line, column, isSolid, hidePlayer);
				column ++;
			}
			line ++;
		}
	}
	
	public void quit() {	
		handler.getMouseManager().setUIManager(null);
		handler.getGame().resize(800, 800);
		
		UIManager uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		uiManager.addObject(handler.getMenu());
		
		State.setState(handler.getGame().gameState); 
	}
	
	public Cell getCase(int x, int y) {
		int xCase = (int)Math.floor(x / Tile.TILE_WIDTH);
		int yCase = (int)Math.floor(y / Tile.TILE_HEIGHT);
		return grid[xCase][yCase];
	}

	public Spell getSpell(int x, int y) {
		int xSpell = (int) Math.floor((x - 861) / 39);
		int ySpell = (int) Math.floor((y - 640) / 38);
		
		int index = 7 * ySpell + xSpell;
		
		if (index < currentPlayer().getSpells().size())
			return currentPlayer().getSpells().get(index);
		else
			return null;
	}
	
	public Character currentPlayer() {
		return team.get(playerTurn);
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
			xMaxLost = halfGrid - ((handler.getWorld().getWidth() - 1) - tileX);
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
			yMaxLost = halfGrid - ((handler.getWorld().getHeight() - 1) - tileY);
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
			
				g.drawLine(i, 0, i, GRID_DIMENSION * Tile.TILE_WIDTH);
				g.drawLine(0, i, GRID_DIMENSION * Tile.TILE_HEIGHT, i);
		}
	}
	
	@Override
	public void tick() {
		
	}

	/*
	 * Rendering methods
	 */
	
	public void renderBattleGrid(Graphics g) {
		int line = 0;
		for (int y = yMin ; y <= yMax ; y ++) {
			int column = 0;
			for (int x = xMin ; x <= xMax ; x++) {
				for (int layer = 0 ; layer < 3 ; layer ++) {
					Tile tile = handler.getWorld().getTile(y,x, layer);
					if (tile != null)
						tile.render(g, column, line);
				}
				column += 32;
			}
			line += 32;
		}
		drawGrid(g);
	}
	
	public void renderSpellsIcons(Graphics g) {
		int column = 861;
		int line = 640;
		for (int i = 0 ; i < 14 ; i++) {
			
			Spell spell;
			if (i < currentPlayer().getSpells().size())
				spell = currentPlayer().getSpells().get(i);
			else spell = null;
			
			if (i == 7) {
				column = 861;
				line = 678;
			}
			
			if (spell != null)
				g.drawImage(spell.getIcon(), column, line, 38, 38, null);
			
			column += 40;
		}
	}
	
	public void renderBattleGui(Graphics g) {
		
		g.setColor(Color.WHITE);
		
		g.drawImage(currentPlayer().getFace(), 869, 18, 126, 126, null);
		
		g.drawImage(Assets.battleGui, 800, 0, 400, 800, null);
		
		g.drawImage(Assets.hpBar, 1000, 53, 131, 25, null);
		g.drawImage(Assets.xpBar, 1000, 80, 131, 7, null);
		
		renderSpellsIcons(g);
		
		//Rendering hovering spell
		if (hoveringSpell != null) {
			g.setColor(Color.WHITE);
			g.setFont(Assets.optimusPrincepsSemiBold);
			g.drawImage(Assets.spellDescription, 800, 0, 400, 800, null);
			Utils.centerString(g, spellDescriptionRectangle, hoveringSpell.getName(), Assets.optimusPrincepsSemiBold);
		}
		
		g.drawImage(Assets.battleGuiBrackets, 800, 0, 400, 800, null);
		
	}

	
	@Override
	public void render(Graphics g) {
		
		//Setting background
		g.drawImage(Assets.background, 0, 0, 1200, 822, null);
		
		//Rendering battle grid
		renderBattleGrid(g);
		
		//Rendering battle GUI
		renderBattleGui(g);	
			
	}
	
	/*
	 * Area checkers
	 */
	
	public boolean clickOnBattleGrid(int x, int y) {
		return boundsGridBattle.contains(x, y);
	}
	
	public boolean clickOnSpellsGrid(int x, int y) {
		return boundsSpells.contains(x, y);
	}
	
	public boolean clickOnItemButton(int x, int y) {
		return boundsItemButton.contains(x, y);
	}
	
	public boolean clickOnRunButton(int x, int y) {
		return boundsRunButton.contains(x, y);
	}
	
	/*
	 * Mouse-related methods
	 */
	
	@Override
	public void onClick() {
		
		int mouseX = handler.getMouseManager().getMouseX();
		int mouseY = handler.getMouseManager().getMouseY();
		
		System.out.println("Mouse coordinates : (" + mouseX + ", " + mouseY + ")");
		
		if (clickOnBattleGrid(mouseX, mouseY)){
			Cell cell = getCase(mouseX, mouseY);
			System.out.println("Click on cell : (" + cell.getX() + ", " + cell.getY() + ")");
		}
		
		else if (clickOnSpellsGrid(mouseX, mouseY)){
			System.out.println("Clicking on spell grid");
		}
		
		else if (clickOnItemButton(mouseX, mouseY)) {
			System.out.println("Clicking on item button");
		}
		
		else if (clickOnRunButton(mouseX, mouseY)) {
			System.out.println("Fuite");
			quit();
		}
	}

	@Override
	public void onMouseMove(MouseEvent e) {
	
		if (bounds.contains(e.getX(), e.getY()))
			hovering = true;
		else
			hovering = false;
		
		
		if (clickOnSpellsGrid(e.getX(), e.getY()))
			hoveringSpell = getSpell(e.getX(), e.getY());
		else 
			hoveringSpell = null;
			
	}
}
