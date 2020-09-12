package ch.backend.objects;

import java.awt.Color;
import java.awt.Graphics;

import ch.Game;
import ch.backend.Handler;

public class Bricks extends GameObjects{
	private int score;
	
	public Bricks(int score, Handler handler) {
		super(100, 50, 0, Game.HEIGHT-300, handler);
		this.score = score;
		
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(getPosX() - getWidth()/2, getPosY() - getHeight()/2, getWidth(), getHeight());
	}
	
	
}
