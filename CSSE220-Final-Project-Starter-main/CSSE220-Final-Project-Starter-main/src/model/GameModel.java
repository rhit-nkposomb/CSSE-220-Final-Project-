package model;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D.Double;
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
	private WinCondition bone;
	private ArrayList<Enemy> enemies;
	private ArrayList<Collectable> sticks;
	private ArrayList<Walls> walls;
	private int totalsticks;
	private int caughtsticks;
	private int win;
	private int lives;
	private int alltimesticks;


	public GameModel() {
		//this.player = new Player(0,0);
		enemies= new ArrayList<>();
		sticks = new ArrayList<>();
		walls=new ArrayList <>();
		init(1);

	}
	
	public void init(int level) {
		enemies.clear();
		sticks.clear();
		walls.clear();
		caughtsticks=0;
		lives=4;
		win = 0;
	    if (level == 1) {
	        loadLevel("level1.txt");
	    } else if (level == 2) {
	        loadLevel("level2.txt");
	    }
	    totalsticks=sticks.size();
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
			if(e.collidesWith(player) || player.collidesWith(e)) {
    			lives= lives-1;
    			player.reset();
    			System.out.println("Got Player.");
    		}
			e.update();
		}


	    //checks if 2 enemies collide
	    for (int i=0; i<enemies.size();i++) {
	    	for(int j=i+1; j<enemies.size();j++) {
	    		Enemy a = enemies.get(i);
	    		Enemy b = enemies.get(j);

	    		if(a.collidesWith(b)) {
	    			System.out.println("Enemies collide.");
	    			a.reverse();
	    			b.reverse();
	    		}
	    	}
	    }
			};

			


	//player updating and getting sticks
	public void movePlayer(int dx, int dy) {
		player.moveBy(dx, dy);
		
		for (Walls w : walls) {
			if (player.collidesWith(w)) {
				player.moveBy(-dx, -dy);
				return;
			}
		}
		
		for(int k=sticks.size()-1; k>=0;k--) {
			if(player.collidesWith(sticks.get(k))) {
				sticks.remove(k);
				caughtsticks++;
				alltimesticks++;
				System.out.println("Got stick.");
			}
		}
		
		if(player.collidesWith(bone)) {
			win = 1;
			player.reset();
			System.out.println("Got Bone!");
		};

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

	         //   System.out.println(ch);
	            if (ch == 'P') {
	            	player = new Player(row, col);
//	                System.out.println("p="+col +" " +row);
	            }
	            if (ch == 'B') {
	            	bone = new WinCondition(row, col);
//	                System.out.println("b="+col +" " +row);
	            }
                if (ch == 'E') {
 	                enemies.add(new Enemy(row,col));
// 	               System.out.println(row+" "+col);
                }
                if (ch == 'S') {
 	                sticks.add(new Collectable(row,col));
// 	               System.out.println(row+" "+col);
                }
                if (ch == 'W') {
 	                walls.add(new Walls(row,col));
// 	               System.out.println("walls= "+row+" "+col);
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
		for(Collectable s: sticks) {
			s.drawOn(g2);
		}
       for(Walls w: walls) {
    	   w.drawOn(g2);
		}
       if(bone != null) {
			bone.drawOn(g2);
		}

	}

	
	public boolean isGameWon() {
		return win == 1;
//				|| lives==0;
	}


	public boolean isGameLost() {
		return lives==0;
	}

	public int getLives() {
		return lives;
	}

	public ArrayList<Collectable> getSticks() {
		return sticks;
	}

	public int getTotalsticks() {
		return totalsticks;
	}

	public int getCaughtsticks() {
		return caughtsticks;
	}

	public int getAllSticks() {
		return alltimesticks;
	}

	public void reset() {
		alltimesticks = 0;
		init(1);
	}
	public void nextLevel() {
		init(2);
	}





}




