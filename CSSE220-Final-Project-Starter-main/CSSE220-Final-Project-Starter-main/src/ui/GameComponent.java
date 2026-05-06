package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import model.GameModel;
import model.Player;

public class GameComponent extends JComponent {

	private GameModel model;
	public static final int WIDTH = 400;
	public static final int HEIGHT = 150;
	private BufferedImage background;

// set preferred size in 
	
	public GameComponent(GameModel model) {
	this.model = model;
	this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
	this.setOpaque(true);
	
	try {
		background = ImageIO.read(GameComponent.class.getResource("background.png"));
	} catch (IOException | IllegalArgumentException e) {
		background = null;
	}
	
	
	}


	@Override
	protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D) g;

	// Minimal placeholder to test  it’s running
	g2.drawString("Final Project Starter: UI is running ✅", 20, 30);
	if (background!= null) {
		g2.drawImage(background,0,0, WIDTH,HEIGHT,null);
		}
		else {
			g2.setColor(Color.MAGENTA);
			g2.fillRect(0,0, WIDTH,HEIGHT);
		}
	model.getPlayer().drawOn(g2);
	model.getEnemy().drawOn(g2);

	}
}
