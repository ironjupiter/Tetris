import java.awt.Color;
import java.awt.Graphics;

public class SPiece extends MovablePiece{
	
	public SPiece(PlayableTetrisArea p, Border bottom, Border right, Border left) {
		super(p, bottom, right, left);
	}

	@Override
	public void createPiece(Graphics g) {
		blocks[0] = new Block(x, y, width, height, Color.GREEN);
		blocks[0].createBlock(g);
		switch (current_orientation) {
			case ORIENTATION_UP: 
			case ORIENTATION_DOWN:
				blocks[1] = new Block(x-width, y, width, height, Color.GREEN);
				blocks[2] = new Block(x, y-height, width, height, Color.GREEN);
				blocks[3] = new Block(x+width, y-height, width, height, Color.GREEN);
				
				blocks[1].createBlock(g);
				blocks[2].createBlock(g);
				blocks[3].createBlock(g);
			break;
			case ORIENTATION_LEFT:
			case ORIENTATION_RIGHT:
				blocks[1] = new Block(x, y+height, width, height, Color.GREEN);
				blocks[2] = new Block(x-width, y, width, height, Color.GREEN);
				blocks[3] = new Block(x-width, y-height, width, height, Color.GREEN);
				
				blocks[1].createBlock(g);
				blocks[2].createBlock(g);
				blocks[3].createBlock(g);
			break;
		}
	}
}
