import java.awt.*;
public class Border {
	int x;
	int y;
	int width; 
	int height;
	boolean raised = true;
	public Border(int set_x, int set_y, int set_width, int set_height) {
		x = set_x;
		y = set_y;
		width = set_width;
		height = set_height;
	}
	
	public void createBorder(Graphics g) {
		g.setColor(Color.RED);
		g.draw3DRect(x, y, width, height, raised);
	} 
}
