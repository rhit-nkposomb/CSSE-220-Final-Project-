package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player {
	private int row;
	private int col;
	private int startRow;
	private int startCol;
	private static final int TILE_SIZE = 40;
	private BufferedImage sprite;
	
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
		
		int x = this.col * TILE_SIZE; //defining x, player sprite length
		int y = this.row * TILE_SIZE;// defining y, player sprite width
		
		//If sprite is NOT  null, draw the downloaded sprite image
		if (sprite!= null) {
			g2.drawImage(sprite, startRow, startCol,x,y,null);
		}
		
		//If it is null, draw a magenta colored rectangle 
		else {
			g2.setColor(Color.MAGENTA);
			g2.fillRect(x, y, TILE_SIZE, TILE_SIZE);
		}
	}
	
	public void shift(int dcol) {
		
		col=+dcol;
		
		if (col < 0) {
		    col= 0;
		  }
		  if (col + col * TILE_SIZE > GameComponent.WIDTH) {
		    col = GameComponent.WIDTH - col * TILE_SIZE;
		  }
	}
	
	public void moveBy(int dRow, int dCol) {
		if (dRow > 0) {
			this.row = row-Math.abs(dRow);
		}
		else if (dRow < 0) {
			this.row = row+Math.abs(dRow);
		}
		if (dCol > 0) {
			this.col = col-Math.abs(dCol);
		}
		else if (dRow < 0) {
			this.col = col+Math.abs(dCol);
		}
	}
	
	public void reset() {
		this.row = startRow;
		this.col = startCol;
	}
	
}
