package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import entities.Entity;
import entities.creatures.NPC;
import entities.statics.GoldChest;
import entities.statics.Warp;
import main.Handler;
import tiles.Tile;

public class EntityMaker {

	public static Entity buildEntity(String line, Handler handler) {
		
		try {
			
			String [] splittedLine = line.split(" ");
			String typeEntity = splittedLine[0];
			
			float x = Float.parseFloat(splittedLine[1]) * Tile.TILE_WIDTH;
			float y = Float.parseFloat(splittedLine[2]) * Tile.TILE_HEIGHT;
			
			if (typeEntity.toUpperCase().equals("WARP")) {
				return new Warp(handler, x, y);
			}
		
			else if (typeEntity.toUpperCase().equals("NPC")) {
				
				int width = Integer.parseInt(splittedLine[3]);
				int height = Integer.parseInt(splittedLine[4]);
				
				String filename = "/textures/characters_sprites/" + splittedLine[5];
				String name = splittedLine[6];
				
				String message = "";
				for (int i = 7 ; i < splittedLine.length ; i++) {
					message += splittedLine[i] + " ";
				}
				
				return new NPC(handler, x, y, width, height, filename, name, message);
				
			}
		
			else if (typeEntity.toUpperCase().equals("GOLD_CHEST")) {
				
				String strDirection = splittedLine[3];
				int direction = -1;
				
				if (strDirection.toUpperCase().equals("UP"))
					direction = Entity.UP;
				
				else if (strDirection.toUpperCase().equals("DOWN"))
					direction = Entity.DOWN;
				
				else if (strDirection.toUpperCase().equals("LEFT"))
					direction = Entity.LEFT;
				
				else if (strDirection.toUpperCase().equals("RIGHT"))
					direction = Entity.RIGHT;
						
				else {
					System.err.println("ERROR : " + strDirection + " is a bad direction.");
					return null;
				}
				
				int nbGolds = Integer.parseInt(splittedLine[4]);
				
				return new GoldChest(handler, x, y, direction, nbGolds);
			}
		
			else if (typeEntity.toUpperCase().equals("ITEM_CHEST") || typeEntity.toUpperCase().equals("STUFF_CHEST") ||
					typeEntity.toUpperCase().equals("RARE_CHEST")) {
				
				//TODO: create these classes
				System.err.println("Entity coming soon");
				return null;
			}
			else {
				System.err.println("InvalidEntity");
				return null;
			}
			
		} catch (IndexOutOfBoundsException | NumberFormatException e) {
			System.err.println("BAD_ENTITY : " + line);
			return null;
		}
		
	}
	
	public static void addEntity(String path, String entity) {
			
		try {
			
			String tmp = path + "_copy";
			
			File input = new File(path);
			File output = new File(tmp);
			
			BufferedReader r = new BufferedReader(new FileReader(input));
			BufferedWriter w = new BufferedWriter(new FileWriter(output));
			
			String line = null;
			
			while ((line = r.readLine()) != null) {
				w.write(line + "\n");
			}
			
			w.write(entity + "\n");
			
			output.renameTo(input);
			
			r.close();
			w.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void displayUsage() {
		System.err.println("USAGE : ");
	}
	
	@SuppressWarnings("unused")
	public static boolean checkEntityValidity(String [] args) {
			
		try {
			
			String typeEntity = args[1];
			
			int x = Integer.parseInt(args[2]);
			int y = Integer.parseInt(args[3]);
			
			if (typeEntity.toUpperCase().equals("WARP")) {
				return true;
			}
		
			else if (typeEntity.toUpperCase().equals("NPC")) {
				
				String filename = args[4];
				File file = new File("/textures/characters_sprites/" + filename);
				
				String name = args[5];
				String message = args[6];
				
				return true;
				
			}
		
			else if (typeEntity.toUpperCase().equals("GOLD_CHEST")) {
				
				String direction = args[4];
				
				if (!(direction.toUpperCase().equals("UP") || direction.toUpperCase().equals("DOWN") ||
					  direction.toUpperCase().equals("LEFT") || direction.toUpperCase().equals("RIGHT"))) {
					System.err.println("ERROR : " + direction + " is a bad direction.");
				}
				
				int nbGolds = Integer.parseInt(args[5]);
				
				return true;
			}
		
			else if (typeEntity.toUpperCase().equals("ITEM_CHEST") || typeEntity.toUpperCase().equals("STUFF_CHEST") ||
					typeEntity.toUpperCase().equals("RARE_CHEST")) {
				
				String direction = args[4];
				
				if (!(direction.toUpperCase().equals("UP") || direction.toUpperCase().equals("DOWN") ||
					  direction.toUpperCase().equals("LEFT") || direction.toUpperCase().equals("RIGHT"))) {
					System.err.println("ERROR : " + direction + " is a bad direction.");
				}
				
				int idItem = Integer.parseInt(args[5]);
				
				return true;
			}
			else 
				return false;
			
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
			displayUsage();
			return false;
		}
	}
	
	public static void main (String [] args) {
		 try {
			 if (checkEntityValidity(args)) {
				 String path = args[0];
				 String entity = "";
				 for (int i = 1 ; i < args.length ; i++) {
					 if (i == 1) entity += args[i].toUpperCase() + " ";
					 else entity += args[i] + " ";
				 }
				 addEntity(path, entity);
			 }
		 } catch (IndexOutOfBoundsException e) {
			 displayUsage();
		 }
	}
}
