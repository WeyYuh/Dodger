package com.weyyuh.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Taskbar.State;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {
	/**
	 * Generated serial version ID below:
	 */
	private static final long serialVersionUID = 8561317155150334445L;

	// Everything that needs to be handled will be in this class.

	// Variables
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	private boolean running = false;
	
	public static boolean paused = false;
	public int diff = 0;
	
	// 0 = normal
	// 1 = hard

	
	// Instances
	private Thread thread;
	private Handler handler;
	private Random random;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;

	public enum STATE {
		Menu,
		Select,
		Help,
		Game,
		End
	};
	
	public static STATE gameState = STATE.Menu;
	
	public Game() {
		
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);

		// adds key listener
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		
		AudioPlayer.load();
		AudioPlayer.getMusic("music").loop();
		AudioPlayer.getSound("menu_sound");
		
		
		
		// creates the window
		new Window(WIDTH, HEIGHT, "Dodger", this);

		spawner = new Spawn(handler, hud, this);
		random = new Random();
		handler.addObject(
				new BasicEnemy(random.nextInt(Game.WIDTH), random.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
		if (gameState == STATE.Game) {
			handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
			// handler.addObject(new EnemyBoss((Game.WIDTH / 2)-48, -120, ID.EnemyBoss,
			// handler));
			handler.addObject(
					new BasicEnemy(random.nextInt(Game.WIDTH), random.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
			
		}else {
			for(int i = 0; i < 20; i++) {
				handler.addObject(new MenuParticle(random.nextInt(WIDTH)/2, random.nextInt(HEIGHT)/2, ID.MenuParticle, handler));	
			}
		}


	}

	public synchronized void start() {
		// start the thread
		thread = new Thread(this);
		thread.start();
		// Set running to true
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			// Set running to false
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void newGame() {
		spawner.newGame();
	}
	public void run() {
		// focus on the window when running
		this.requestFocus();

		long lastTime = System.nanoTime();
		// update 60 fps
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running) {
				render();
			}
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// print FPS
				// System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

	private void tick() {
		
		if (gameState == STATE.Game) {
			if (!paused) {
				
				hud.tick();
				spawner.tick(); 
				handler.tick();
				
				if (HUD.HEALTH <= 0) {
					HUD.HEALTH = 100;
					gameState = STATE.End;
					handler.clearEnemies();
					for(int i = 0; i < 10; i++) {
						handler.addObject(new MenuParticle(random.nextInt(WIDTH)/2, random.nextInt(HEIGHT)/2, ID.MenuParticle, handler));	
					}
				}
				
			}
		}else if (gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Help || gameState == STATE.Select){
			menu.tick();
			handler.tick();
		}
		
	}

	private void render() {
		// Creating a Buffer Strategy
		BufferStrategy buffer = this.getBufferStrategy();

		// buffer is null when being run the first time
		// needs to start the buffer in order to render
		if (buffer == null) {

			// creates 3 buffers in the game
			this.createBufferStrategy(3);
			return;
		}

		Graphics graphics = buffer.getDrawGraphics();

		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, WIDTH, HEIGHT);

		handler.render(graphics);
		
		if(paused) {
			graphics.setColor(Color.white);
			graphics.drawString("PAUSED", 100, 100);
		}
		
		if (gameState == STATE.Game) {
			hud.render(graphics);
			
		}else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select){
			menu.render(graphics);
		}
		

		graphics.dispose();
		buffer.show();
	}

	public static float clamp(float var, float min, float max) {
		if (var >= max) {
			return var = max;
		} else if (var <= min) {
			return var = min;
		} else {
			return var;
		}
	}

	public static void main(String args[]) {
		new Game();
	}
}
