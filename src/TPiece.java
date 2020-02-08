import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;



public class TPiece extends MovablePiece{
	Color color = new Color(0, 5, 150);
	
	public TPiece(PlayableTetrisArea p, Border bottom, Border right, Border left) {
		super(p, bottom, right, left);
	}

	@Override	
	public void createPiece(Graphics g) {
		g.setColor(color);
		blocks[0] = new Block(x, y, width, height, color);
		blocks[0].createBlock(g);
		switch(current_orientation) {	
			case ORIENTATION_UP:	
				blocks[1] = new Block(x, y-height, width, height, color);
				blocks[2] = new Block(x+width, y, width, height, color);
				blocks[3] = new Block(x-width, y, width, height, color);
				
				blocks[1].createBlock(g);
				blocks[2].createBlock(g);
				blocks[3].createBlock(g);
				break;
				
			case ORIENTATION_DOWN:
				blocks[1] = new Block(x, y+height, width, height, color);
				blocks[2] = new Block(x+width, y, width, height, color);
				blocks[3] = new Block(x-width, y, width, height, color);
				
				blocks[1].createBlock(g);
				blocks[2].createBlock(g);
				blocks[3].createBlock(g);
				break;
				
			case ORIENTATION_RIGHT:
				blocks[1] = new Block(x, y-height, width, height, color);
				blocks[2] = new Block(x+width, y, width, height, color);
				blocks[3] = new Block(x, y+height, width, height, color);
				
				blocks[1].createBlock(g);
				blocks[2].createBlock(g);
				blocks[3].createBlock(g);
				break;
				
			case ORIENTATION_LEFT:
				blocks[1] = new Block(x, y-height, width, height, color);
				blocks[2] = new Block(x-width, y, width, height, color);
				blocks[3] = new Block(x, y+height, width, height, color);
				
				blocks[1].createBlock(g);
				blocks[2].createBlock(g);
				blocks[3].createBlock(g);
				break;
		}
	}
}
