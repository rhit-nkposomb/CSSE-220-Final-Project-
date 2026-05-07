package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import ui.GameComponent;

public class Enemy {
	private int x, y;
	private int row;
	private int col;
	private int startRow;
	private int startCol;
	private static final int TILE_SIZE = 40;
	BufferedImage sprite;
	
	public Enemy(int row, int col) {
		this.row = row;
		this.col = col;
		this.startRow = row;
		this.startCol = col;
		try {
			sprite = ImageIO.read(Enemy.class.getResource("download.png"));
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
	
	public void update(int dx, int dy) {
		 this.x += dx;
		 this.y += dy;
		 System.out.println("X:" + x);
		 if (x * TILE_SIZE <= 0) {
		        x = 0;
		        dx = -dx;
		    }
		 if (x * TILE_SIZE >= GameComponent.WIDTH) {
		        x = GameComponent.WIDTH ;
		        dx = -dx;
		 }
		 if (y *TILE_SIZE<= 0) {
		        y = 0;
		        dy = -dy;
		    }
		 if (y *TILE_SIZE>= GameComponent.HEIGHT) {
		        y = GameComponent.HEIGHT;
		        dy = -dy;
	}
	}
	
}
