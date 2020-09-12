package ch.frontend;

import java.awt.Canvas;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

import ch.Game;

public class Window extends Canvas {
	private int x;
	private JPanel panel;
	
	public Window(int width, int height, Game game){
		panel = new JPanel();
		panel.add(game);
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				x = e.getX();
				System.out.println(e.getX());
			}
		});
	}
	
}
