package ch;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferStrategy;
import java.util.Random;

import ch.backend.Handler;
import ch.backend.objects.Ball;
import ch.backend.objects.Bricks;
import ch.backend.objects.GameObjects;
import ch.backend.objects.Racket;

public class Game extends Canvas implements Runnable {
	
	public static final int WIDTH = 800, HEIGHT = WIDTH/12*9;
    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private int xMouse;
    
    public Game() {
    	handler = new Handler(this);
    	Random r = new Random();
    	
    	for (int i = 0; i<1; i++) {
    		handler.addObject(new Ball(50, r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT), handler));
    	}
    	handler.addObject(new Racket(handler));
    	handler.addObject(new Bricks(100, handler));
    	this.setBounds(new Rectangle(100, 100, WIDTH, HEIGHT));
    	this.addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseMoved(MouseEvent e){
                xMouse = e.getX();
            
            }
        });
    }

	public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run(){
        //game-loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running){
                render();
            }
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        handler.tick();

    }

    private void render(){
    	BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.YELLOW);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        g.dispose();
        bs.show();
    }

    public static int clamp(int var, int max, int min){
        if(var <= min){
            return min;
        }else if(var >= max){
            return max;
        }else{
            return var;
        }
    }

    public static void main(String args[]){
        new Game();
    }
    
    public int getXMouse() {
    	return xMouse;
    }

}
