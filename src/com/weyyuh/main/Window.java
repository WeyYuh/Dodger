package com.weyyuh.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas{

	/**
	 * Generated serial version id below:
	 */
	private static final long serialVersionUID = 6768581090436225369L;

	public Window(int width, int height, String title, Game game) {
		// Create a window for the game
		JFrame frame = new JFrame(title);
		
		// Set the size of the window for the game
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		// Close the program when the window is closed
		// frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Forbids the user to resize the window
		frame.setResizable(false);
		
		// Sets the location of the window in the middle of the computer screen
		frame.setLocationRelativeTo(null);
		
		frame.add(game);
		
		// Set window to be focused
		frame.setVisible(true);

		
		// Start the game
		game.start();
	}
}
