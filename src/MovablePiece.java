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
		if(blocks[0].willBlockBottomSideCollideWith(pta.block_list) == true 
		|| blocks[1].willBlockBottomSideCollideWith(pta.block_list) == true
		|| blocks[2].willBlockBottomSideCollideWith(pta.block_list) == true
		|| blocks[3].willBlockBottomSideCollideWith(pta.block_list) == true) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean checkIfOneBlockRightSideWillCollide() {
		if(blocks[0].willBlockRightSideCollideWith(pta.block_list) == true 
		|| blocks[1].willBlockRightSideCollideWith(pta.block_list) == true
		|| blocks[2].willBlockRightSideCollideWith(pta.block_list) == true
		|| blocks[3].willBlockRightSideCollideWith(pta.block_list) == true) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean checkIfOneBlockLeftSideWillCollide() {
		if(blocks[0].willBlockLeftSideCollideWith(pta.block_list) == true 
		|| blocks[1].willBlockLeftSideCollideWith(pta.block_list) == true
		|| blocks[2].willBlockLeftSideCollideWith(pta.block_list) == true
		|| blocks[3].willBlockLeftSideCollideWith(pta.block_list) == true) {
			return true;
		}else {
			return false;
		}
	}
	
	
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
				}
			
		}
	@Override
	public void keyReleased(KeyEvent e) {}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	public void printYValue() {
		System.out.println("current Y = " + y);	
	}
}
