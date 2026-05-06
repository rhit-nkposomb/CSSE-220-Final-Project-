
package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import ui.GameComponent;

public class Player {
	private int row;
	private int col;
	private int startRow;
	private int startCol;
	private int x;
	private int y;
	private static final int TILE_SIZE = 40;
	BufferedImage sprite;
	
	public Player(int row, int col) {
		this.row = row;
		this.col = col;
		this.startRow = row;
		this.startCol = col;
		try {
			sprite = ImageIO.read(Player.class.getResource("Player.png"));
		} catch (IOException | IllegalArgumentException e) {
			sprite = null;
		}
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
	
	public void drawOn(Graphics2D g2) {
		this.x = this.col * TILE_SIZE;
		this.y = this.row * TILE_SIZE;
		
		if (sprite!= null) {
		g2.drawImage(sprite, x,y,TILE_SIZE,TILE_SIZE,null);
//		g2.fillRect(x, y, TILE_SIZE, TILE_SIZE);
		}
		else {
			g2.setColor(Color.MAGENTA);
			g2.fillRect(x, y, TILE_SIZE, TILE_SIZE);
		}
	}
	
	public void moveBy(int dRow, int dCol) {
		if (dRow > 0) {
			this.row = row-Math.abs(dRow);
			if (this.row <= 0) {this.row = 0;} 
		}
		else if (dRow < 0) {
			this.row = row+Math.abs(dRow);
			if (this.row >= GameComponent.HEIGHT) {this.row = GameComponent.HEIGHT-y;}
		}
		if (dCol > 0) {
			this.col = col+Math.abs(dCol);
			if (this.col >= GameComponent.WIDTH) {this.col = GameComponent.WIDTH-x;}
		}
		else if (dCol < 0) {
			this.col = col-Math.abs(dCol);
			if (this.col <= 0 ) {this.col = 0;}
		}
	}
	
	
	
}

