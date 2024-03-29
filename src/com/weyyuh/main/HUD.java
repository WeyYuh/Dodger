package com.weyyuh.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {

	public static float HEALTH = 100;
	
	private float greenValue = 255f;
	
	private int score = 0;
	private int level = 1;
	
	public void tick() {
		
		HEALTH = Game.clamp(HEALTH, 0, 100);
		
		greenValue = Game.clamp(greenValue, 0, 255);
		
		greenValue = HEALTH * 2;
		
		score++;
	}
	
	public void render(Graphics graphics) {
		graphics.setColor(Color.gray);
		graphics.fillRect(15, 15, 200, 32);
		graphics.setColor(new Color(75, (int) greenValue, 0));
		graphics.fillRect(15, 15, (int) HEALTH * 2, 32);
		graphics.setColor(Color.white);
		graphics.drawRect(15, 15, 200, 32);

		//System.out.println("Score: "+ score);
		
		
		Font font3 = new Font("arial", 1, 15);
		graphics.setFont(font3);
		graphics.setColor(Color.white);
		graphics.drawString("Score: " + score, 15, 64);
		graphics.drawString("Level: " + level, 15, 80);
		
		
	}
	
	
	public int getScore() {
		return score;
	}
	public void score(int score) {
		this.score = score;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
}
