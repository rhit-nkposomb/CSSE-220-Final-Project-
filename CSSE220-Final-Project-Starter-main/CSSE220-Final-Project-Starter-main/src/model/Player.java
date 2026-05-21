package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import ui.GameComponent;

public class Player implements Collideable{
	private int row;
	private int col;
	private int startRow;
	private int startCol;
	private int x;
	private int y;
	private int dx;
	private int dy;
	private static final int TILE_SIZE = 40;
	BufferedImage sprite;
	
	public Player(int row, int col) {
		this.row = row;
		this.col = col;
		this.startRow = row;
		this.startCol = col;
		this.dx=10;
		this.dy=10;
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
		//g2.setColor(Color.RED);
		g2.draw(getBounds());
		}
		else {
			g2.setColor(Color.MAGENTA);
			g2.fillRect(x, y, TILE_SIZE, TILE_SIZE);
			g2.draw(getBounds());
		}
	}
	
	public void moveBy(int dx, int dy) {
		int newrow = this.row;
		int newcol = this.col;
		if (dy > 0) {
			newrow = row - Math.abs(dy); 
		}
		else if (dy < 0) {
			newrow = row + Math.abs(dy);
		}
		if (dx > 0) {
			newcol = col + Math.abs(dx);
//			if (this.col >= GameComponent.WIDTH/TILE_SIZE) {this.col = GameComponent.WIDTH/TILE_SIZE;}
		}
		else if (dx < 0) {
			newcol = col - Math.abs(dx);
//			if (this.col <= 0 ) {this.col = 0;}
		}
			
		this.col = newcol; 
		this.row = newrow;
		
		//second check
		if (this.row < 0) {this.row = 0;}
		if (this.row >= GameComponent.HEIGHT / TILE_SIZE) {this.row = GameComponent.HEIGHT / TILE_SIZE - 1;}
		if (this.col >= GameComponent.WIDTH / TILE_SIZE) {this.col = GameComponent.WIDTH / TILE_SIZE - 1; }
		if (this.col < 0 ) {this.col = 0; }
		
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, TILE_SIZE, TILE_SIZE);
	}

	@Override
	public boolean collidesWith(Collideable e) {
		// TODO Auto-generated method stub
		return this.getBounds().intersects(e.getBounds());
		
		
		
		}
	
	public void reset() {
		// TODO: Move the ball back to its original position
		// Replace the current x with the original x
		this.row = this.startRow;
		this.col = this.startCol;
	}
	
	public void reverse() {
		dx=-dx;
		dy=-dy;
	}
	
	
	
	
}