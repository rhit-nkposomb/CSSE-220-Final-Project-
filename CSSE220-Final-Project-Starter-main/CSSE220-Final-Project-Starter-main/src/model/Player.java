package model;

import java.awt.Graphics2D;

public class Player {
	private int row;
	private int col;
	private static final int TILE_SIZE = 40;
	
	public Player(int row, int col) {
		this.row = row;
		this.col = col;
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

		g2.fillRect(x, y, TILE_SIZE, TILE_SIZE);
	}
	
	public void moveBy(int dRow, int dCol) {
		if (dRow > 0) {
			this.row = row-dRow;
		}
		else if (dRow < 0) {
			this.row = row+dRow;
		}
		if (dCol > 0) {
			this.col = col-dCol;
		}
		else if (dRow < 0) {
			this.col = col+dCol;
		}
	}
	
}
