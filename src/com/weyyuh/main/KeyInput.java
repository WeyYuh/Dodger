package com.weyyuh.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.weyyuh.main.Game.STATE;

public class KeyInput extends KeyAdapter {

	// Instances
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	
	Game game;

	public KeyInput(Handler handler, Game game) {
		this.handler = handler;

		this.game = game;
		
		keyDown[0] = false; // W key
		keyDown[1] = false; // S key
		keyDown[2] = false; // D key
		keyDown[3] = false; // A key
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getID() == ID.Player) {
				// Key events for player 1
				if (key == KeyEvent.VK_W) {
					tempObject.setVelY(-5);
					keyDown[0] = true;
				}
				if (key == KeyEvent.VK_S) {
					tempObject.setVelY(5);
					keyDown[1] = true;
				}
				if (key == KeyEvent.VK_D) {
					tempObject.setVelX(5);
					keyDown[2] = true;
				}
				if (key == KeyEvent.VK_A) {
					tempObject.setVelX(-5);
					keyDown[3] = true;
				}
			}

		}

		if (key == KeyEvent.VK_P) {
			
			if (game.gameState == STATE.Game) {
				
				if(Game.paused) Game.paused = false;
				else Game.paused = true;
			}
		}
		
		if (key == KeyEvent.VK_ESCAPE)
			System.exit(1);
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getID() == ID.Player) {
				// Key events for player 1
				if (key == KeyEvent.VK_W)
					keyDown[0] = false;
				// tempObject.setVelY(0);
				if (key == KeyEvent.VK_S)
					keyDown[1] = false;
				// tempObject.setVelY(0);
				if (key == KeyEvent.VK_D)
					keyDown[2] = false;
//					tempObject.setVelX(0);
				if (key == KeyEvent.VK_A)
					keyDown[3] = false;
				// tempObject.setVelX(0);
				
				// vertical movement
				if (!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
				
				// horizontal movement
				if (!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
				
			}

		}
	}
}
