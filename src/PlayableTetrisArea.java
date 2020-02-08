import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayableTetrisArea extends JPanel{//test 
	JPanel panel;
	int width;
	int height; 
	int x = 0;
	int y = 0;
	MovablePiece mp;
	GameWindow gw;
	Border []borderList = new Border[4];
	ArrayList<Block> block_list = new ArrayList<Block>( );
	public PlayableTetrisArea(GameWindow g) {
		height = g.height;
		width = g.width;
		gw = g;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x, y, width, height);	
		
		mp.createPiece(g);
		borderList[0].createBorder(g);
		borderList[1].createBorder(g);
		borderList[2].createBorder(g);
		
		createAllStaticBlocks(g);
		createAllBorders(g);
	}
	private void createAllStaticBlocks(Graphics g) {
		int i = 0;
		while(block_list.size() > i) {
			block_list.get(i).createBlock(g);
			i++;
		}
		//note: will only work with a block object!
	}
	
	private void createAllBorders(Graphics g) {
		int i = 0;
		while(borderList.length > i) {
			borderList[i].createBorder(g);
			i++;
		}
		//note: will only work with a block object!
	}

	public void addPiece(MovablePiece x) {
		mp = x;
	}
	public void removePiece() {
		mp = null;
	}
	
	public void addBorders(Border x,Border y,Border z, Border a) {
		borderList[0] = x;
		borderList[1] = y;
		borderList[2] = z;
		borderList[3] = a;
	}
}
