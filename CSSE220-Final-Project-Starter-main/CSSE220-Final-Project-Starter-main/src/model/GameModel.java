package model;

import javax.swing.JPanel;

/**
 * Stores the current state of the game and controls the main game rules.
 * 
 * This is where the game keeps track of objects such as the player,
 * walls, gems, zombies, score, lives, and levels.
 * 
 * GameModel should update the game state, but it should not draw anything.
 * Drawing belongs in GameComponent.
 */

public class GameModel extends JPanel{
	private Player player;
	private Enemy enemy;
	public GameModel() {
		this.player = new Player(5,5);
		this.enemy = new Enemy(4,7);
		
		
		
		
	}
	public Enemy getEnemy() {
		return enemy;
	}
	public Player getPlayer() {
		return player;
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
	
	
	
}
