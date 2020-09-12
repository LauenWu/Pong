package ch.backend.objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import ch.backend.Handler;

public class GameObjects {
	private int width, height;
	private int posX, posY;
	private Handler handler;
	private Rectangle rectangle;
	
	public GameObjects(int width, int height, int posX, int posY, Handler handler) {
		this.handler = handler;
		this.rectangle = new Rectangle(posX, posY, width, height);
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
	}
	
	public int getPosX() {
		return (int)rectangle.getCenterX();
	}
	
	public void setPosX(int posX) {
		this.rectangle.setLocation(posX - (int)rectangle.getWidth()/2, (int)rectangle.getY());
	}
	
	public int getPosY() {
		return (int)rectangle.getCenterY();
	}
	
	public void setPosY(int posY) {
		this.rectangle.setLocation((int)rectangle.getX(), posY - (int)rectangle.getHeight()/2);
	}
	
	public int getSize() {
		if (getWidth() == getHeight()) {
			return getWidth();
		}
		throw new RuntimeException("Shit's not working");
	}
	
	public int getHeight() {
		return (int)this.rectangle.getHeight();
	}
	
	public int getWidth() {
		return (int)this.rectangle.getWidth();
	}
	
	public boolean intersects(GameObjects other) {
		return this.rectangle.intersects(other.rectangle);
	}
	
	public void collision(GameObjects other) {
		System.out.println("nothing");
	}
}
