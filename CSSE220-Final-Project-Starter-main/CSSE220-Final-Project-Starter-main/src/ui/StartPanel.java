package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class StartPanel extends JPanel {
	private JLabel label;
	private JButton startButton;
	private BufferedImage background;
	
	public StartPanel() {


		try {
			background = ImageIO.read(GameComponent.class.getResource("Background (1).jpg"));
		} catch (IOException | IllegalArgumentException e) {
			background = null;
		}
		
		label = new JLabel("Welcome to Puppy Simulator!", SwingConstants.CENTER);
		label.setForeground(Color.PINK);
		label.setFont(new Font("Serif", Font.BOLD, 18));
		setStartButton(new JButton("Start"));
		this.setLayout(new BorderLayout());
		this.add(label, BorderLayout.CENTER); 
		this.add(getStartButton(), BorderLayout.SOUTH);
	
	
	}

	public JButton getStartButton() {
		return startButton;
	}

	public void setStartButton(JButton startButton) {
		this.startButton = startButton;
	}
}
