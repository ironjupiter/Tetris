import java.awt.*;
import javax.swing.*;
public class GameWindow {
	JFrame frame;
//	JButton start_button;
	int width = 500;
	int height = 800; 
	
	public GameWindow() {
		frame = new JFrame();
//		start_button = new JButton("start!");
//		start_button.setPreferredSize(new Dimension(40, 40));
//		frame.add(BorderLayout.NORTH,start_button);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	public GameWindow(int width, int height) {
		frame = new JFrame();
//		start_button = new JButton("start!");
//		start_button.setPreferredSize(new Dimension(40, 40));
//		frame.add(BorderLayout.NORTH,start_button);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	public void addPanel(PlayableTetrisArea s) {
		frame.add(s);
		frame.setVisible(true);
	}
}
