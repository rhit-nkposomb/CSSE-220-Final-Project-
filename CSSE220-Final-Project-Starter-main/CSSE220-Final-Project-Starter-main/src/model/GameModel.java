package model;

import java.awt.Graphics2D;
import java.io.InputStream;
import java.util.ArrayList;
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
	private ArrayList<Enemy> enemies;
	
	public GameModel() {
		//this.player = new Player(0,0);
		enemies= new ArrayList<>();
		
		
		loadLevel("level1.txt");
		//int col=0;
		
		InputStream stream= GameModel.class.getResourceAsStream("level1.txt");
		if (stream == null) {
			throw new RuntimeException("Level file not found");
			
		}
		
	}

	//public Enemy getEnemy() {
		//System.out.println(enemy.getRow());
	//	return enemy;
	//}
	
	//public Player getPlayer() {
		//System.out.println(player.getCol());
	//	return player;
	//}
	
	public void updateEnemy() {
		for (Enemy e: enemies) {
			e.update();
			if(player.collidesWith(e)) {
				//life lost
				
				player.reset();
				
			};
		}
			}
	public void movePlayer(int dx, int dy) {
		player.moveBy(dx, dy);
		 for (Enemy e: enemies) {
			 if(player.collidesWith(e)) {
					//life lost
					player.reset();	
				};
		 }
		
	}
//	public void movePlayerDown() {
//		player.moveBy(0, -1);
//	}
//	public void movePlayerLeft() {
//		player.moveBy(-1, 0);
//	}
//	public void movePlayerRight() {
//		player.moveBy(1, 0);
//	}
//	
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
	            
	            System.out.println(ch);
	            if (ch == 'P') {
	            	player = new Player(row, col);
	                System.out.println(col +" " +row);
	            }
	                
                if (ch == 'E') {
 	                enemies.add(new Enemy(row,col));
 	               // System.out.println(col +" " +row);
 	                 // stop after first ball
                }
			
		}
			row++;
		
		}
		scanner.close();
		//throw new IllegalStateException("No P found in level file");
		
		
	}
	
	public void draw( Graphics2D g2) {
		if(player != null) {
			player.drawOn(g2);
		}
		
		for(Enemy e: enemies) {
			e.drawOn(g2);
		}	
	}
		
	
}
	
	 
	
	
