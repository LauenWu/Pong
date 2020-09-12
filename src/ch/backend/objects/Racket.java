package ch.backend.objects;

import java.awt.Color;
import java.awt.Graphics;

import ch.Game;
import ch.backend.Handler;

public class Racket extends GameObjects{
	public Racket(Handler handler) {
		super(100, 10, 0, Game.HEIGHT-100, handler);
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(getPosX() - getWidth()/2, getPosY() - getHeight()/2, getWidth(), getHeight());
	}
}
