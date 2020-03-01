import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public abstract class MovablePiece implements KeyListener{
	Block[] blocks = new Block[4];
	PlayableTetrisArea pta;
	Border bottom_border;
	Border right_border;
	Border left_border;
	int x = 180;
	int y = 180;
	int width = 20; 
	int height = 20; 
	final int ORIENTATION_UP = 1;
	final int ORIENTATION_DOWN = 2;
	final int ORIENTATION_RIGHT = 3;
	final int ORIENTATION_LEFT = 4;
	int current_orientation = ORIENTATION_UP;	
	
	public abstract void createPiece(Graphics g);
	
	public MovablePiece(PlayableTetrisArea p, Border bottom, Border right, Border left) {
		pta = p;
		bottom_border = bottom;
		right_border = right;
		left_border = left;
	}
	
	public void fall() {
		if(checkIfAnyBlockBottomCollided() == false && checkIfOneBlockBottomSideWillCollide() == false) {
			y+=height;
		}else{		
			System.out.println("hit barrier");
		}
	}
	
	public boolean checkIfAnyBlockBottomCollided() {
		if(blocks[0].checkIfBlockHitBottomBarrier(bottom_border) == true 
		|| blocks[1].checkIfBlockHitBottomBarrier(bottom_border) == true
		|| blocks[2].checkIfBlockHitBottomBarrier(bottom_border) == true
		|| blocks[3].checkIfBlockHitBottomBarrier(bottom_border) == true) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean checkIfAnyBlockRightCollided() {
		if(blocks[0].checkIfBlockHitRightBarrier(right_border) == true 
		|| blocks[1].checkIfBlockHitRightBarrier(right_border) == true
		|| blocks[2].checkIfBlockHitRightBarrier(right_border) == true
		|| blocks[3].checkIfBlockHitRightBarrier(right_border) == true) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean checkIfAnyBlockLeftCollided() {
		if(blocks[0].checkIfBlockHitLeftBarrier(left_border) == true 
		|| blocks[1].checkIfBlockHitLeftBarrier(left_border) == true
		|| blocks[2].checkIfBlockHitLeftBarrier(left_border) == true
		|| blocks[3].checkIfBlockHitLeftBarrier(left_border) == true) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean checkIfOneBlockBottomSideWillCollide() {
		if(blocks[0].willBlockBottomSideCollideWith(pta.placed_blcok_list) == true 
		|| blocks[1].willBlockBottomSideCollideWith(pta.placed_blcok_list) == true
		|| blocks[2].willBlockBottomSideCollideWith(pta.placed_blcok_list) == true
		|| blocks[3].willBlockBottomSideCollideWith(pta.placed_blcok_list) == true) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean checkIfOneBlockRightSideWillCollide() {
		if(blocks[0].willBlockRightSideCollideWith(pta.placed_blcok_list) == true 
		|| blocks[1].willBlockRightSideCollideWith(pta.placed_blcok_list) == true
		|| blocks[2].willBlockRightSideCollideWith(pta.placed_blcok_list) == true
		|| blocks[3].willBlockRightSideCollideWith(pta.placed_blcok_list) == true) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean checkIfOneBlockLeftSideWillCollide() {
		if(blocks[0].willBlockLeftSideCollideWith(pta.placed_blcok_list) == true 
		|| blocks[1].willBlockLeftSideCollideWith(pta.placed_blcok_list) == true
		|| blocks[2].willBlockLeftSideCollideWith(pta.placed_blcok_list) == true
		|| blocks[3].willBlockLeftSideCollideWith(pta.placed_blcok_list) == true) {
			return true;
		}else {
			return false;
		}
	}
	
	/*THESE ARE KEY EVENTS
	 * DONT CHANGE THE ALREADY 
	 * EXSISTING ONES UNLESS 
	 * YOU ARE OPTIMIZING THEM*/
	@Override
	public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
				case KeyEvent.VK_W:
				case KeyEvent.VK_ENTER:
				case KeyEvent.VK_UP:
					
						System.out.println("block rotated");
						if(current_orientation == ORIENTATION_UP) {
							current_orientation = ORIENTATION_RIGHT;
						}else if(current_orientation == ORIENTATION_RIGHT){
							current_orientation = ORIENTATION_DOWN;
						}else if(current_orientation == ORIENTATION_DOWN){
							current_orientation = ORIENTATION_LEFT;
						}else if (current_orientation == ORIENTATION_LEFT){
							current_orientation = ORIENTATION_UP;
						}	
						pta.repaint();
					
					break;
				
					case KeyEvent.VK_D:
					case KeyEvent.VK_RIGHT:
						if(checkIfAnyBlockRightCollided() == false && checkIfOneBlockRightSideWillCollide() == false) {
							System.out.println("block moved");	
							x+=width;
							pta.repaint();
						}
						break;
						
					case KeyEvent.VK_A:
					case KeyEvent.VK_LEFT:
						if(checkIfAnyBlockLeftCollided() == false && checkIfOneBlockLeftSideWillCollide() == false) {
							x-=width;
							System.out.println("block moved");
							pta.repaint();
						}
						break;
						
					case KeyEvent.VK_S:
					case KeyEvent.VK_DOWN:
						fall();
						pta.repaint();
						break;
						
					case KeyEvent.VK_SPACE:
						hardDrop();
					break;
				}
		}
	
	private void hardDrop() {
		System.out.println("starting hard drop tests");
		int virtrual_y = y;
		boolean has_block_dropped = false; 
		while(virtrual_y <= bottom_border.y) {
			System.out.println("checking if any other block gets in way");
			if(areBlocksUnderThisPeice(virtrual_y) == true) {
				System.out.println("block got in way");
				this.y = virtrual_y;//move block if this is found true
				has_block_dropped = true;
				break;
			}
			virtrual_y+= height;
		}
		
		System.out.println("dropping it on very bottom");
		if(has_block_dropped == false) {//true = don't do it& false = JUST DO IT
			moveBlockToBottom();
		}
	}
	
	private boolean areBlocksUnderThisPeice(int virtrual_y) {
		if(runBottomHitTestWithDummyBlcok(makeDummyPiece(virtrual_y))) {
			return true;
		}
		return false;
	};
	
	private Block[] makeDummyPiece(int virtrual_y) {
		Block[] dummy_blocks = new Block[4];
		for(int i = 0; i < dummy_blocks.length; i++) {
			dummy_blocks[i] = blocks[i];
			dummy_blocks[i].y = virtrual_y;
			if(blocks[i].y > this.y) {
				
				int how_much_lower = blocks[i].y + this.y;
				int how_much_to_change_y = how_much_lower/height;
				dummy_blocks[i].y = dummy_blocks[i].y + how_much_to_change_y;
			}else if(blocks[i].y < this.y) {
				
				int how_much_higher = blocks[0].y - this.y;
				int how_much_to_change_y = how_much_higher/height;
				dummy_blocks[i].y = dummy_blocks[i].y + how_much_to_change_y;
			}else {
				dummy_blocks[i].y = dummy_blocks[i].y;
			}
		}
		return dummy_blocks;
	}
	
	private void moveBlockToBottom() {
		int virtrual_y = bottom_border.y;

		int blocks_directly_under_main_block = 1;
		for(int i = 0; i < blocks.length; i++) {
			if(blocks[i].y > this.y && blocks[i].x == this.x) {
				blocks_directly_under_main_block++;
			}
		}
		this.y = virtrual_y - (blocks_directly_under_main_block * height);
	}
	
	private boolean runBottomHitTestWithDummyBlcok(Block[] dummy_blocks) {
		if(dummy_blocks[0].willBlockBottomSideCollideWith(pta.placed_blcok_list) == true 
		|| dummy_blocks[1].willBlockBottomSideCollideWith(pta.placed_blcok_list) == true
		|| dummy_blocks[2].willBlockBottomSideCollideWith(pta.placed_blcok_list) == true
		|| dummy_blocks[3].willBlockBottomSideCollideWith(pta.placed_blcok_list) == true) {
			return true;
		}else {
			return false;
		}
	}
	
	//trash that i can't get rid of :\
	@Override
	public void keyReleased(KeyEvent e) {}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	public void printYValue() {
		System.out.println("current Y = " + y);	
	}
}
