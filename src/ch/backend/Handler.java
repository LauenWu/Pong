package ch.backend;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import ch.Game;
import ch.backend.objects.Bricks;
import ch.backend.objects.GameObjects;
import ch.backend.objects.Racket;

public class Handler {
	private List<GameObjects> objects;
	private Game game;
	
	public Handler(Game game) {
		this.game = game;
	}
	
	public void addObject(GameObjects o) {
		if (objects == null) {
			objects = new ArrayList<GameObjects>();
		}
		objects.add(o);
	}
	
	public void tick() {
		List<GameObjects> toRemove = new ArrayList<>();
		for(GameObjects o : objects) {
			if (o instanceof Racket) {
				Racket racket = (Racket)o;
				racket.setPosX(game.getXMouse());
			}
			
			for (GameObjects other : objects) {
				if(o == other) {
					continue;
				}
				
				if(o.intersects(other)) {
					o.collision(other);
					
					if(o instanceof Bricks) {
						toRemove.add(o);
					}
				}
			}
			
			o.tick();
		}
		objects.removeAll(toRemove);
	}
	
	public void render(Graphics g) {
		for(GameObjects o : objects) {
			o.render(g);
		}
	}
}
