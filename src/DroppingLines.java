import java.util.ArrayList;

public class DroppingLines {
	protected ArrayList<Block> list_of_blocks_in_line = new ArrayList<Block>();
	protected ArrayList<String> list_of_keys_in_line = new ArrayList<String>();

	protected int times_to_drop;
	public DroppingLines(int set_lines_to_drop) {
		times_to_drop = set_lines_to_drop;
	}
	
	public void addBlock(Block b) {
		list_of_blocks_in_line.add(b);
	}
	
	public void dropBlocks(PlayableTetrisArea pta) {
		for(int i = 0; list_of_blocks_in_line.size() > i;) {
			pta.modifyBlockYAxis(list_of_keys_in_line.get(i), times_to_drop);//drop			
			String new_key = list_of_blocks_in_line.get(i).x + "," + list_of_blocks_in_line.get(i).y;//new key
			
			pta.placed_blcok_list.remove(list_of_keys_in_line.get(i));//remove old key
			pta.placed_blcok_list.put(new_key, list_of_blocks_in_line.get(i));//add new key/block
			System.out.print("Block:" + i + " :at y: " + pta.placed_blcok_list.get(new_key).y);//print
			i++;
		}
	}
	
//	public void swap_keys(PlayableTetrisArea pta) {
//		for(int i = 0; list_of_keys_in_line.size() > i; i++) {
//			
//		}
//	}
}
