package ui;

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
	private BufferedImage background;


	public GameComponent(GameModel model) {
	this.model = model;
	
	try {
		background = ImageIO.read(Player.class.getResource("Background.png"));
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

	model.getPlayer().drawOn(g2);
	// TODO: draw based on model state
	}
}
