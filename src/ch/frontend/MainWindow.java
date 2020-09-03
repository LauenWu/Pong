package ch.frontend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;

import ch.Game;


public class MainWindow {
	private JFrame frame = new JFrame("Pong");
    //private Container mainContainer = frame.getContentPane();
    private JButton start = new JButton("Start");
    private Window window;
    public MainWindow(int width, int height, String title, Game game)
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(width, height));
        
        frame.addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseMoved(MouseEvent e){
                System.out.println(e.getX());
            
            }
        });
        
        window = new Window(width, height, game);
        frame.add(window,BorderLayout.CENTER);
        
        frame.pack();
        frame.setVisible(true);
    }
}
