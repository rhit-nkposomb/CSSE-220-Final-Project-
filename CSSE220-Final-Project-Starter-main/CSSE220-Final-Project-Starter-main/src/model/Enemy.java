package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import ui.GameComponent;

public class Enemy implements Collideable{
	//private int x, y;
	private int row;
	private int col;
	private int startRow;
	private int startCol;
	private int dx, dy;
	private static final int TILE_SIZE = 40;
	int x;
	int y;
	//int x = this.col * TILE_SIZE;
	//int y = this.row * TILE_SIZE;
	BufferedImage sprite;
	
	public Enemy(int row, int col) {
		this.row = row;
		this.col = col;
		this.startRow = row;
		this.startCol = col;
		this.dx = 1;
		this.dy = 1;
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
		int x = this.col * TILE_SIZE;
		int y = this.row * TILE_SIZE;
		
		if (sprite!= null) {
		g2.drawImage(sprite, x,y,TILE_SIZE,TILE_SIZE,null);
		g2.setColor(Color.CYAN);
		//g2.draw(getBounds());
		
		}
		else {
			g2.setColor(Color.MAGENTA);
			g2.fillRect(x, y, TILE_SIZE, TILE_SIZE);
			g2.draw(getBounds());
		}
		
	}
	
	
	public void update() {
//		x+=dx;
		int newrow = this.row + dy;
		int newcol = this.col + dx;
//		 this.x += dx;
//		 this.y += dy;
//		 System.out.println("col:" + col);
		 if (newcol <= 0) {
		    
			 this.col = 0;
		        dx = -dx;
		    }
		 else if (newcol  >= GameComponent.WIDTH/TILE_SIZE) {
			// System.out.println("Greater than 0");  
			 this.col = GameComponent.WIDTH/TILE_SIZE-1 ;
//			 System.out.println(col);  

			 
		        dx = -dx;
		 }
		 else if (newrow<= 0) {
		        this.row = 0;
		        dy = -dy;
		    }
		 else if (newrow >= GameComponent.HEIGHT/TILE_SIZE) {
		        this.row = GameComponent.HEIGHT/TILE_SIZE-1;
		        dy = -dy;
	}
		 else {
			 this.row += dy;
			 this.col += dx;
		 }
	}
	
	
	public Rectangle getBounds() {
		//int newrow = this.row + dy;
		//int newcol = this.col + dx;
		int x = this.col * TILE_SIZE;
		int y = this.row * TILE_SIZE;
		return new Rectangle(x,y,TILE_SIZE,TILE_SIZE);
	}

	@Override
	public boolean collidesWith(Collideable p) {
		// TODO Auto-generated method stub
		return this.getBounds().intersects(p.getBounds());
	}
	
	public void reverse() {
		dx=-dx;
		dy=-dy;
	}
	
	
	
}
