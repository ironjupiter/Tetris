import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;
public class ResartButton extends JButton implements ActionListener{
	JButton button = new JButton("restart game");
	PlayableTetrisArea pta;
	public ResartButton(PlayableTetrisArea p) {
		pta = p;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Iterator<String> i = pta.block_list.keySet().iterator();
		ArrayList<String> list_of_keys = new ArrayList<String>();
		while( i.hasNext() ) {
			String key = i.next();
			list_of_keys.add(key);
		}
		
		for(int j = 0; j < list_of_keys.size(); j++) {
			pta.block_list.remove(list_of_keys.get(j));
		}
		
		System.out.println("GAME RESTARTED");
	}
}
