package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.Timer;

public class GameComponent extends JComponent {
	private GameModel model;
	private BufferedImage background;
	public static final int WIDTH = 400;
	public static final int HEIGHT = 150;
	
	private Timer timer;
	
	public GameComponent(GameModel model) {
		this.model=model;
		try {
			background=ImageIO.read( getClass().getResource("background.png"));
		}catch (IOException | IllegalArgumentException e){
				background=null;
			}
		
		timer = new Timer(30, e -> {
			  repaint();
			});

			timer.start();
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		//Minimal placeholder to test if it's running
		g2.drawString("Final Project Starter: UI is running", 20, 30);
		
		model.getPlayer().drawOn(g2);
		
		if(background != null) {
			g2.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
		}else {
			g2.setColor(Color.GREEN);
		}
		
	}
	
	

}






