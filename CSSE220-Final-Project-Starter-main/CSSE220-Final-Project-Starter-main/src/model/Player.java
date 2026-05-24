package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import ui.GameComponent;

public class Player implements Collideable{
	private double row;
	private double col;
	private int startRow;
	private int startCol;
	private double x;
	private double y;
	private static final int TILE_SIZE = 40;
	BufferedImage sprite;
	Walls w;
	
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

	public double getRow() {
		return row;
	}

	public void setRow(double row) {
		this.row = row;
	}
	
	public double getCol() {
		return col;
	}

	public void setCol(double col) {
		this.col = col;
	}

	
	
	public void drawOn(Graphics2D g2) {
		this.x = this.col * TILE_SIZE;
		this.y = this.row * TILE_SIZE;
		
		if (sprite!= null) {
		g2.drawImage(sprite, (int)x,(int)y,TILE_SIZE,TILE_SIZE,null);
		g2.setColor(Color.RED);
		g2.draw(getBounds());
		}
		else {
			g2.setColor(Color.MAGENTA);
			g2.fillRect((int)x, (int)y, TILE_SIZE, TILE_SIZE);
			g2.draw(getBounds());
			
		}
	}
	
	public void moveBy(double dx, double dy) {
		double newrow = this.row;
		double newcol = this.col;
		if (dy > 0) { //upwards
			newrow = row - Math.abs(dy); 
		}
		else if (dy < 0) {//downwards 
			newrow = row + Math.abs(dy);
		}
		if (dx > 0) { //forwards 
			newcol = col + Math.abs(dx);
//			if (this.col >= GameComponent.WIDTH/TILE_SIZE) {this.col = GameComponent.WIDTH/TILE_SIZE;}
		}
		else if (dx < 0) {//backwards
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
	
	public void reverse(double dx, double dy) {
		dx=-dx;
		dy=-dy;
	}
	
	public Rectangle2D.Double getBounds() { //Horizontal collisions
		return new Rectangle2D.Double(x, y, TILE_SIZE, TILE_SIZE);
	}

	@Override
	public boolean collidesWith(Collideable e) {
		return this.getBounds().intersects(e.getBounds());
		}
	

	public Rectangle2D.Double getBoundsvertical() { //Vertical collisions
		return new Rectangle2D.Double(x, y, TILE_SIZE, TILE_SIZE);
	}
	
	public boolean collidesWithvertical(Collideable e) {
		return this.getBoundsvertical().intersects(e.getBounds());
		}
	
	
	public void reset() {
		this.row = this.startRow;
		this.col = this.startCol;
	}
	
	
	
	
	
	
}