package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Collectable implements Collideable {
	private int row;
	private int col;
	//private int startRow;
	//private int startCol;
	private int x;
	private int y;
	private static final int TILE_SIZE = 40;
	BufferedImage sprite;
	
	public Collectable(int row, int col) {
		this.row = row;
		this.col = col;
		//this.startRow = row;
		//this.startCol = col;
		try {
			sprite = ImageIO.read(Collectable.class.getResource("Stick-removebg-preview.png"));
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
		g2.setColor(Color.YELLOW);
		//g2.draw(getBounds());
		}
		else {
			g2.setColor(Color.MAGENTA);
			g2.fillRect(x, y, TILE_SIZE, TILE_SIZE);
			g2.draw(getBounds());
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, TILE_SIZE, TILE_SIZE);
	}
	
	@Override
	public boolean collidesWith(Collideable e) {
		// TODO Auto-generated method stub
		return this.getBounds().intersects(e.getBounds());
	}
}

