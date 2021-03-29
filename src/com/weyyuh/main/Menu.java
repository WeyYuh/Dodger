package com.weyyuh.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.weyyuh.main.Game.STATE;

public class Menu extends MouseAdapter {

	private Game game;
	private Handler handler;
	private HUD hud;
	private Random random;

	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.hud = hud;
		this.handler = handler;
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		random = new Random();

		if (game.gameState == STATE.Menu) {

			// play button
			if (mouseOver(mx, my, 210, 150, 200, 64)) {
//				game.gameState = STATE.Game;
//				handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
//				handler.clearEnemies();
//				handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH), random.nextInt(Game.HEIGHT), ID.BasicEnemy,
//						handler));
				game.gameState = STATE.Select;
				AudioPlayer.getSound("menu_sound").play();
				return;
			}

			// help button
			if (mouseOver(mx, my, 210, 250, 200, 64)) {
				game.gameState = STATE.Help;
				AudioPlayer.getSound("menu_sound").play();
			}

			// quit button
			if (mouseOver(mx, my, 210, 350, 200, 64)) {
				AudioPlayer.getSound("menu_sound").play();
				System.exit(1);
			}
		}
		
		if (game.gameState == STATE.Select) {

			// normal button
			if (mouseOver(mx, my, 210, 150, 200, 64)) {
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
				handler.clearEnemies();
				handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH-16), random.nextInt(Game.HEIGHT-32), ID.BasicEnemy,
						handler));
				
				game.diff = 0;
				AudioPlayer.getSound("menu_sound").play();
			}

			// hard button
			if (mouseOver(mx, my, 210, 250, 200, 64)) {
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
				handler.clearEnemies();
				handler.addObject(new HardEnemy(random.nextInt(Game.WIDTH-16), random.nextInt(Game.HEIGHT-32), ID.BasicEnemy,
						handler));
				
				game.diff = 1;
				AudioPlayer.getSound("menu_sound").play();
			}

			// back button
			if (mouseOver(mx, my, 210, 350, 200, 64)) {
				game.gameState = STATE.Menu;
				AudioPlayer.getSound("menu_sound").play();
				return;
			}
		}

		// back button for help
		if (game.gameState == STATE.Help) {
			if (mouseOver(mx, my, 210, 350, 200, 64)) {
				game.gameState = STATE.Menu;
				AudioPlayer.getSound("menu_sound").play();
				return;
			}
		}
		// try again button
		if (game.gameState == STATE.End) {
			if (mouseOver(mx, my, 210, 350, 200, 64)) {
				AudioPlayer.getSound("menu_sound").play();
				game.gameState = STATE.Menu;
				hud.setLevel(1);
				hud.score(0);
				
			}
		}
	}

	public void mouseReleased(MouseEvent e) {

	}

	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	public void tick() {

	}

	public void render(Graphics graphics) {
		if (game.gameState == STATE.Menu) {
			Font font = new Font("arial", 1, 50);
			Font font2 = new Font("arial", 1, 30);

			graphics.setFont(font);
			graphics.setColor(Color.white);
			graphics.drawString("Dodger", 220, 70);

			graphics.setFont(font2);
			graphics.drawRect(210, 150, 200, 64);
			graphics.drawString("Play", 270, 190);

			graphics.drawRect(210, 250, 200, 64);
			graphics.drawString("Help", 270, 290);

			graphics.drawRect(210, 350, 200, 64);
			graphics.drawString("Quit", 270, 390);

		} else if (game.gameState == STATE.Help) {
			Font font = new Font("arial", 1, 50);
			Font font2 = new Font("arial", 1, 30);
			Font font3 = new Font("arial", 1, 20);

			graphics.setFont(font);
			graphics.setColor(Color.white);
			graphics.drawString("Help", 260, 70);

			graphics.setFont(font3);
			graphics.drawString("Use WASD keys to move player and dodge enemies", 80, 200);

			graphics.setFont(font2);
			graphics.drawRect(210, 350, 200, 64);
			graphics.drawString("Back", 270, 390);

		} else if (game.gameState == STATE.End) {
			Font font = new Font("arial", 1, 50);
			Font font2 = new Font("arial", 1, 30);
			Font font3 = new Font("arial", 1, 20);

			graphics.setFont(font);
			graphics.setColor(Color.white);
			graphics.drawString("Game Over", 180, 70);

			graphics.setFont(font3);
			graphics.drawString("You lost with a score of: " + hud.getScore(), 170, 200);

			graphics.setFont(font2);
			graphics.drawRect(210, 350, 200, 64);
			graphics.drawString("Try Again", 242, 390);
		}else if (game.gameState == STATE.Select) {
			Font font = new Font("arial", 1, 50);
			Font font2 = new Font("arial", 1, 30);

			graphics.setFont(font);
			graphics.setColor(Color.white);
			graphics.drawString("SELECT DIFFICULTY", 140, 70);

			graphics.setFont(font2);
			graphics.drawRect(210, 150, 200, 64);
			graphics.drawString("Normal", 270, 190);

			graphics.drawRect(210, 250, 200, 64);
			graphics.drawString("Hard", 270, 290);

			graphics.drawRect(210, 350, 200, 64);
			graphics.drawString("Back", 270, 390);

		}

	}
}
