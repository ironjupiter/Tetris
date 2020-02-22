import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class PlayableTetrisArea extends JPanel{//test 
	JPanel panel;
	int width;
	int height; 
	int x = 0;
	int y = 0;
	MovablePiece mp;
	GameWindow gw;
	Border []borderList = new Border[4];
	HashMap<String, Block> block_list = new HashMap<String, Block>();
	
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
		Iterator<String> i = block_list.keySet().iterator();
		while( i.hasNext() ) {
			String key = i.next();
			if(key != null) {
				Block b = block_list.get(key);
				b.createBlock(g);
			}
		}
	}
	
	public void addBlockToHashMap(MovablePiece mp) {
		for(int i = 0; i < mp.blocks.length;) {
			String key = mp.blocks[i].x+ "," +  mp.blocks[i].y;
			System.out.println("block placed at, " + key);
			block_list.put(key, new Block(mp.blocks[i].x, mp.blocks[i].y, mp.blocks[i].width, mp.blocks[i].height, mp.blocks[i].c));
			i++;
		}
	}
	
	
	private String[] checkTheNumOfBlocksInLine(int y_level) {
		String key = null;
		int farthest_left = 100;
		int x_tp_check = farthest_left;
		int right_egde = 300;
		int loops = 0;
		String []hashcodes = new String[10];
		while(x_tp_check < right_egde) {	
			key = x_tp_check + "," + y_level;
			if(block_list.get(key) != null) {
				hashcodes[loops] = key;
				System.out.println("block at "+ key);
			}else{
				hashcodes[loops] = null;
			}
			x_tp_check+=20;
			loops++;
		}
		return hashcodes;
	}
																	//deal with this later :|
	private DroppingLines ifLineFoundTrueDeleteLineIfNotRetunIt(int y_level_to_check, int num_of_dropped_lines) {
		System.out.println("finding line");
		String [] hashcodes = checkTheNumOfBlocksInLine(y_level_to_check);
		
		boolean is_complete_line = true;
		for(int i = 0; i < hashcodes.length; i++) {
			if( hashcodes[i] == null )
				is_complete_line = false;				 
		}
		
		if(is_complete_line) {
			System.out.println("line found and removed");
			for(int i = 0; i < hashcodes.length; i++) {
				block_list.remove(hashcodes[i]);
			}
			System.out.println("line is full&deleted");
			return null;
		}else{
			DroppingLines line_to_drop = new DroppingLines(num_of_dropped_lines);
			for(int i = 0; hashcodes.length > i; i++) {
				if(hashcodes[i] != null) {
					line_to_drop.addBlock(block_list.get(hashcodes[i]));
					line_to_drop.list_of_keys_in_line.add(hashcodes[i]);
				}
			}
			System.out.println("line is incomplete");
			return line_to_drop;
		}
	}
	
	public void runLineChecker(int play_area_height) {
		ArrayList<DroppingLines> lines_to_drop = new ArrayList<DroppingLines>();
		int numberOfLinesDropped = 0;
		System.out.println("line checker running");
		int y_level_to_check = 560;
		while(y_level_to_check >= 560-play_area_height) {	
			if (ifLineFoundTrueDeleteLineIfNotRetunIt(y_level_to_check, numberOfLinesDropped) == null) {
				numberOfLinesDropped++;
			}else{
				lines_to_drop.add(ifLineFoundTrueDeleteLineIfNotRetunIt(y_level_to_check, numberOfLinesDropped));
			}
			y_level_to_check-=20;
		}
		
		
		if(numberOfLinesDropped != 0) {
			for(int i = 0; i < lines_to_drop.size(); i++) {
				lines_to_drop.get(i).dropBlocks(this);//fix NOW!!!! >:[
			}
		}
	}
	

	
	protected void sortKeys(ArrayList<String> key_list, int above_y) {
		ArrayList<String[]> list_of_split_keys = new ArrayList<String[]>();
		ArrayList<String[]> list_of_sorted_keys = new ArrayList<String[]>();
		for(int i = 0; key_list.size() > i; i++) {
			list_of_split_keys.add(splitKeyPutItInArray(key_list.get(i)));
		}
		for(int i = 0; list_of_split_keys.size() > i; i++) {
			
		}
		
		//border array [x]: 0 = bottom border, 1 = left side, 2 = right side, 3 = top border
	}
	
	private String[] splitKeyPutItInArray(String key) {
		String[] split_key = key.split(","); 
		System.out.println(split_key);
		return split_key;
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
		//DO NOT EDIT
		borderList[0] = x;
		borderList[1] = y;
		borderList[2] = z;
		borderList[3] = a;
		//0 = bottom, 1 = left side, 2 = right side, 3 = top
	}
	
	public void modifyBlockYAxis(String key, int times_to_drop) {
		block_list.get(key).y = block_list.get(key).y + (block_list.get(key).height*times_to_drop);
	}
	
}
