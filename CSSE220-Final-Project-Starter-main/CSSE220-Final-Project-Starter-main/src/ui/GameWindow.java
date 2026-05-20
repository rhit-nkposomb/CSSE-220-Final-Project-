package ui;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.GameModel;
/**
 * GameWindow owns the frame
 **/
public class GameWindow {
	
	private final JFrame frame;
	private final GameModel model;
	private GameComponent component;
	
	public GameWindow(GameModel model) {
		this.model = model;
		this.frame = new JFrame("CSSE220 Final Project");

		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel cards = new JPanel(new CardLayout());
		StartPanel startPanel = new StartPanel();
		component = new GameComponent(this.model);
		cards.add(startPanel, "START");
		cards.add(component, "GAME");
		frame.setContentPane(cards);
		CardLayout cl = (CardLayout) cards.getLayout();
		cl.show(cards, "START");
		startPanel.getStartButton().addActionListener(e -> {
		    this.startGame();
		    cl.show(cards, "GAME");
		});
		
		//		this.frame.add(new GameComponent(this.model));
		this.frame.pack();
		this.frame.setLocationRelativeTo(null);
	}

	public void startGame() {
	    component.startTimer();
	}
	public void show() {
		this.frame.setVisible(true);
		}
}

//JButton for next level 
//method that load next level when button clicked 
