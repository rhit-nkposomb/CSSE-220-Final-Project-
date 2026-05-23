package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ActionMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.Timer;

import model.Enemy;
import model.GameModel;
import model.Player;

/**
 * Draws everything, has key listeners to cause the updating in game model
 */
public class GameComponent extends JComponent {

	private final GameModel model;
	public static final int WIDTH = 400;
	public static final int HEIGHT = 600;
	private BufferedImage background;
	private Timer timer;
	private JButton reset;
	private Runnable onReset;

// set preferred size in 

	public GameComponent(GameModel model, Runnable onReset) {
		this.model = model;
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setOpaque(true);
		this.setFocusable(true);
		this.requestFocusInWindow();

		try {
			background = ImageIO.read(Enemy.class.getResource("grass.jpg"));
		} catch (IOException | IllegalArgumentException e) {
			background = null;
		}

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
					model.movePlayer(0,1);
					repaint();
				} else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
					model.movePlayer(0,-1);
					repaint();
				} else if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
					model.movePlayer(-1,0);
					repaint();
				} else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
					model.movePlayer(1,0);
					repaint();
				}
			}
		}

		);
		
		reset = new JButton("Restart");
		reset.setBounds(150, 300, 100, 40); 
	    reset.setVisible(false);
	    reset.addActionListener(e -> onReset.run());
	    this.add(reset);

		timer = new Timer(500, e -> {
			if(!model.isGameLost()&&!model.isGameWon()) {
				model.updateEnemy();
			}else {
		        reset.setVisible(true);  
		        stopTimer();             
		    }
			repaint();
		});

		//timer.start();

	}
	public void startTimer() {
		reset.setVisible(false); 
		timer.start();
	}
	public void stopTimer() {
	    timer.stop();
	}
	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		// Minimal placeholder to test it’s running
		g2.drawString("Final Project Starter: UI is running ✅", 20, 30);
		if (background != null) {
			g2.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
			if(!model.isGameWon()&&!model.isGameLost()) {
				g2.drawString("Total Lives: " + model.getLives(),60, 60);
				g2.drawString("Sticks caught: " + model.getCaughtsticks(),60, 100);
			}
			else if (model.isGameWon()) {
				g2.drawString("You Won!",100,70);
				g2.drawString("Score: " + model.getCaughtsticks(),100, 100);
			}
			
			else if (model.isGameLost()) {
				g2.drawString("You Lost.",100,70);
				g2.drawString("Score: " + model.getCaughtsticks(),100, 90);
			}
				
		
		} else {
			g2.setColor(Color.blue);
			g2.fillRect(0, 0, WIDTH, HEIGHT);
		}
		
		model.draw(g2);
		
		//model.getEnemy().drawOn(g2);

	}
}
