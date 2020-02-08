import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LinePiece extends MovablePiece {
	
	public LinePiece(PlayableTetrisArea p, Border bottom, Border right, Border left) {
		super(p, bottom, right, left);
	}

	@Override
	public void createPiece(Graphics g) {
		Color color = Color.YELLOW;
		blocks[0] = new Block(x, y, width, height, Color.YELLOW);
		blocks[0].createBlock(g);
		if(current_orientation == ORIENTATION_UP || current_orientation == ORIENTATION_DOWN) {
			blocks[1] = new Block(x, y-height, width, height, color);
			blocks[2] = new Block(x, y+height, width, height, color);
			blocks[3] = new Block(x, y+height*2, width, height, color);
			
			blocks[1].createBlock(g);
			blocks[2].createBlock(g);
			blocks[3].createBlock(g);
		}else if(current_orientation == ORIENTATION_RIGHT || current_orientation == ORIENTATION_LEFT) {
			blocks[1] = new Block(x+width, y,    width, height, color);
			blocks[2] = new Block(x-width, y, width, height, color);
			blocks[3] = new Block(x-width*2, y, width, height, color);
			
			blocks[1].createBlock(g);
			blocks[2].createBlock(g);
			blocks[3].createBlock(g);
			
		}			
	}
}
