package ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartPanel extends JPanel {
	private JLabel label;
	private JButton startButton;
	
	public StartPanel() {
		label = new JLabel("Game Start");
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
