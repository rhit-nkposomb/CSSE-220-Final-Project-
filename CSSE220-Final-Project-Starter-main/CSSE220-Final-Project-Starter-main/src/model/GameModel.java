package model;

import java.awt.Graphics2D;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Stores the current state of the game and controls the main game rules.
 * 
 * This is where the game keeps track of objects such as the player,
 * walls, gems, zombies, score, lives, and levels.
 * 
 * GameModel should update the game state, but it should not draw anything.
 * Drawing belongs in GameComponent.
 */

public class GameModel {
	public static final int TILE_SIZE = 40;

	private Player player;
	private Enemy enemy;
	
	public GameModel() {
		this.player = new Player(0,0);
		this.enemy = new Enemy(1,1);
		
		loadLevel("level1.txt");
//		
//		InputStream stream= GameModel.class.getResourceAsStream(filename);
//		if (stream == null) {
//			throw new RuntimeException(filename + " not found");
//			
//		}
		
	}

	public Enemy getEnemy() {
		return enemy;
	}
	public Player getPlayer() {
		return player;
	}
	public void updateEnemy() {
		enemy.update();
			}
	public void movePlayerUp() {
		player.moveBy(0, 1);
	}
	public void movePlayerDown() {
		player.moveBy(0, -1);
	}
	public void movePlayerLeft() {
		player.moveBy(-1, 0);
	}
	public void movePlayerRight() {
		player.moveBy(1, 0);
	}
	
	public void loadLevel(String filename) {
		int row=0;
		
		InputStream stream= GameModel.class.getResourceAsStream(filename);
		if (stream == null) {
			throw new RuntimeException("Level file not found: " +filename);
		}
		
        Scanner scanner = new Scanner(stream);
		
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			for (int col = 0; col < line.length(); col++) {
	            char ch = line.charAt(col);
	            
	            if (ch == 'E') {
	                int x = col * TILE_SIZE;
	                int y = row * TILE_SIZE;

	                enemy = new Enemy(x, y);

	                scanner.close();
	                return; // stop after first ball
		}
			}
			row++;
		}
		scanner.close();
		throw new IllegalStateException("No E found in level file");
	}
	
		
		
	
}
	
	 
	
	
