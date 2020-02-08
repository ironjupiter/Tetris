import java.awt.*;
import java.util.ArrayList;
public class Block {
	int x;
	int y;
	int width;
	int height;
	Graphics g;
	Color c;
	
	public static void main(String[] args) {
		int height = 10;
		int width = 10;	
	}
	
	public void createBlock(Graphics g) {
		g.setColor(c);
		g.fill3DRect(x, y, width, height, true);
	}
	
	public boolean willBlockRightSideCollideWith(ArrayList<Block> allStaticBlocks) {
		int i = 0;
		while(allStaticBlocks.size() > i) {
//			System.out.println("check" + (i+1));
			if (this.x + this.width < allStaticBlocks.get(i).x + allStaticBlocks.get(i).width &&
			this.x + (this.width*2) > allStaticBlocks.get(i).x &&
			this.y < allStaticBlocks.get(i).y + allStaticBlocks.get(i).height &&
			this.y + this.height > allStaticBlocks.get(i).y) {
				System.out.println(true);
				return true;
			}
			i++;
		}
		return false;
	}
	
	public boolean willBlockBottomSideCollideWith(ArrayList<Block> allStaticBlocks) {
		int i = 0;
		while(allStaticBlocks.size() > i) {
//			System.out.println("check" + (i+1));
			if (this.x < allStaticBlocks.get(i).x + allStaticBlocks.get(i).width &&
			this.x + this.width > allStaticBlocks.get(i).x &&
			this.y + this.height< allStaticBlocks.get(i).y + allStaticBlocks.get(i).height &&
			this.y + (this.height*2)> allStaticBlocks.get(i).y) {
//				System.out.println(true);
				return true;
			}
			i++;
		}
		return false;
	}
	
	public boolean willBlockLeftSideCollideWith(ArrayList<Block> allStaticBlocks) {
		int i = 0;
		while(allStaticBlocks.size() > i) {
//			System.out.println("check" + (i+1));
			if (this.x - this.width< allStaticBlocks.get(i).x + allStaticBlocks.get(i).width &&
			this.x > allStaticBlocks.get(i).x &&
			this.y < allStaticBlocks.get(i).y + allStaticBlocks.get(i).height &&
			this.y + this.height > allStaticBlocks.get(i).y) {
				System.out.println(true);
				return true;
			}
			i++;
		}
		return false;
	}
	
	public Block(int set_x, int set_y, int set_width, int set_height,  Color set_color) {
		x =set_x;
		y = set_y;
		width = set_width;
		height = set_height;
		c = set_color;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
//		System.out.println("blocks current y = " + y);
		return y;
	}
	
	public boolean checkIfBlockHitBottomBarrier(Border b) {
		if(b.y == y+height) {
			System.out.println("player has hit border!");
			
			return true;
		}else{
			return false;
		}
	}
	
	public boolean checkIfBlockHitRightBarrier(Border b) {
		if(getTopRightCorner() >= b.x ) {
			System.out.println("hit right wall");
			return true;
		}else {
			return false;
		}
	}
	
	public boolean checkIfBlockHitLeftBarrier(Border b) {
//		System.out.println(getTopLeftCorner() + "," + b.x);
		if(getTopLeftCorner() <= b.x ) {
			System.out.println("hit left wall");
			return true;
		}else {
			return false;
		}
	}

	private int getTopLeftCorner() {
		return x;
	}
	private int getTopRightCorner() {
		return x + width;
	}
	private int getBottomLeftCorner() {
		return y + height;
	}
	private int getBottomRightCorner() {
		return(x + width) + (height + y);
	}
}

// stuff for later in case needed

//if(this.getBottomLeftCorner() > block.getTopLeftCorner() 
//&& this.getBottomRightCorner() > block.getTopRightCorner()
//&& this.getTopLeftCorner() < block.getBottomLeftCorner()
//&& this.getTopRightCorner() < block.getBottomRightCorner()){//check if bottom hit the top of another block
//	return 3;
//}else if(this.getTopRightCorner() > block.getTopLeftCorner() 
//&& this.getBottomRightCorner() > block.getBottomLeftCorner()
//&& this.getBottomLeftCorner() < block.getBottomRightCorner()) {//check if  right side hit
//	return 2;
//}else if(block.getTopLeftCorner() < this.getTopRightCorner()
//&& block.getBottomLeftCorner() < this.getBottomRightCorner()) {// check if left side hit
//	return 1;
//}else{//if no hit 
//	return 0;
//}