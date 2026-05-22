package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class StartPanel extends JPanel {
	private JLabel label;
	private JLabel label2;
	private JButton startButton;
	private BufferedImage background;
	
	public StartPanel() {
		try {
			background = ImageIO.read(StartPanel.class.getResource("Background (1).png"));
		} catch (IOException | IllegalArgumentException e) {
			System.out.println("Image failed to load: " + e.getMessage());
			background = null;
		}
		
		label = new JLabel("Welcome to Puppy Simulator!", SwingConstants.CENTER);
		label2 = new JLabel("Collect Sticks and Don't Get Caught!", SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Serif", Font.BOLD, 18));
		label2.setForeground(Color.BLACK);
		label2.setFont(new Font("Serif", Font.BOLD, 16));
		setStartButton(new JButton("Start"));
		this.setLayout(new BorderLayout());
		this.add(label, BorderLayout.NORTH);
		this.add(label2, BorderLayout.CENTER);
		this.add(getStartButton(),BorderLayout.SOUTH);
	
	
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    if (background != null) {
	        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
	    }
	}

	public JButton getStartButton() {
		return startButton;
	}

	public void setStartButton(JButton startButton) {
		this.startButton = startButton;
	}
}
