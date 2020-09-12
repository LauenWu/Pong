package ch.backend.objects;

import java.awt.Color;
import java.awt.Graphics;

import ch.Game;
import ch.backend.Handler;

public class Ball extends GameObjects {
	private double v;
	private double vX, vY;
	private int radius;
	private Handler handler;
	
	public Ball(int size, int posX, int posY, Handler handler) {
		super(size, size, posX, posY, handler);
		this.radius = size/2;
		this.handler = handler;
		vX = 10;
		vY = 10;
		v = Math.sqrt((vX*vX)+(vY*vY));
	}
	
	public void tick() {
		setPosX(getPosX() + (int)vX);
		setPosY(getPosY() + (int)vY);
		
		if (getPosY() + radius > Game.HEIGHT || getPosY() - radius < 0) {
			vY = -vY;
		}
		
		if (getPosX() + radius > Game.WIDTH || getPosX() - radius < 0) {
			vX = -vX;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval(getPosX() - getSize()/2, getPosY() - getSize()/2, getSize(), getSize());
	}
	
	@Override
	public void collision(GameObjects go) {
		if (go instanceof Racket) {
			int sign = (vY > 0 ? -1 : 1);
			
			this.setPosY(this.getPosY() + sign * this.radius);
			
			int dx = go.getPosX() - this.getPosX();
			
			if(dx == 0) {
				vY = -vY;
				return;
			}
			
			double vY_sq = vY*vY;
			double v_sq = (vX*vX)+(vY_sq);
			
			vX -= dx/10;
			vY = sign * Math.sqrt(v_sq-vY_sq);
			double vReal = Math.sqrt((vX*vX)+(vY*vY));
			vX = vX * v/vReal;
			vY = vY * v/vReal;
		} else if(go instanceof Bricks) {
			vY = -vY;
		}
	}
}
