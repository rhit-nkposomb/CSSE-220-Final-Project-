package app;

import javax.swing.SwingUtilities;

import ui.GameWindow;
import model.GameModel;

/**
 * MainApp starts the program
 * @author Put your team name here: CS Survivors: Minika and Lily 
 */
public class MainApp {
	GameModel model;
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
		new MainApp().run();
		});
		}
	
	// This runs only once at startup:
	// MainApp creates and connects objects (no game logic here)
	public void run() {
		this.model = new GameModel();
		GameWindow window = new GameWindow(model);
		window.show();
	}
	
}