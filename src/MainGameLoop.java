import javax.swing.*;
import java.awt.*;

public class MainGameLoop {
	static MovablePiece []tetrominos_list = new MovablePiece[7];
	static int timer = 0;
	public static void main(String[] args) {
		//set up playing area DONT EDIT THIS PART IF YOU DONT UNDERSTAND THE ALL THE SOURCE CODE
		GameWindow game_window = new GameWindow();
		PlayableTetrisArea playable_tetris_area = new PlayableTetrisArea(game_window);
		Border bottom_border = new Border(100, 560, 200, 10);//Border(x, y, width, height);
		Border left_border = new Border(bottom_border.x, bottom_border.y-400, 10, 400);
		Border top_border = new Border(bottom_border.x, left_border.y, bottom_border.width, bottom_border.height);//this one just marks the top
		Border right_border = new Border((bottom_border.x+bottom_border.width)-10, left_border.y, left_border.width, left_border.height);
		//create array
		createTetrominosList(playable_tetris_area, bottom_border, right_border, left_border);
		
		//add extra scatered blocks to arraylist
//		playable_tetris_area.block_list.add(new Block(250, 400, 10, 10, Color.RED));
//		playable_tetris_area.block_list.add(new Block(110, 500, 10, 10, Color.YELLOW));
//		playable_tetris_area.block_list.add(new Block(300, 600, 10, 10, Color.BLUE));
		
		//add panel
		game_window.addPanel(playable_tetris_area);
		
		//pick tetromino from array
		MovablePiece tetromino = pickATetromino();
		
		//add tetromino to panel
		playable_tetris_area.addPiece(tetromino);
		playable_tetris_area.addBorders(bottom_border, left_border, right_border, top_border);
		playable_tetris_area.addKeyListener(tetromino);
		playable_tetris_area.grabFocus();
		
		while(true) {
			try {Thread.sleep(1000);} catch (Exception e) {}
			playable_tetris_area.mp.printYValue();
			tetromino.fall();
			
			//if tetromino hit the ground replace it with static blocks and create a new one for the player
			if(tetromino.checkIfAnyBlockBottomCollided() == true || tetromino.checkIfOneBlockBottomSideWillCollide() == true) {
				if(timer == 1) {
						for(int i = 0; i < tetromino.blocks.length;) {
							playable_tetris_area.block_list.add(new Block(tetromino.blocks[i].x, tetromino.blocks[i].y, tetromino.blocks[i].width, tetromino.blocks[i].height, tetromino.blocks[i].c));
							System.out.println("static block length: " + playable_tetris_area.block_list.size()); 
							i++;
						}
					tetromino.y = 180;
					tetromino.x = 180;
					playable_tetris_area.removeKeyListener(tetromino);
					tetromino = pickATetromino();
					playable_tetris_area.addPiece(tetromino);
					playable_tetris_area.addKeyListener(tetromino);
					timer = 0;
					}
				timer++;
			}
			playable_tetris_area.repaint();
		}
	}
	
	public static void createTetrominosList(PlayableTetrisArea playable_tetris_area,Border bottom_border,Border right_border,Border left_border) {
		tetrominos_list[0] = new SPiece(playable_tetris_area, bottom_border, right_border, left_border);
		tetrominos_list[1] = new SquarePiece(playable_tetris_area, bottom_border, right_border, left_border);
		tetrominos_list[2] = new ZPiece(playable_tetris_area, bottom_border, right_border, left_border);
		tetrominos_list[3] = new LPiece(playable_tetris_area, bottom_border, right_border, left_border);
		tetrominos_list[4] = new JPiece(playable_tetris_area, bottom_border, right_border, left_border);
		tetrominos_list[5] = new LinePiece(playable_tetris_area, bottom_border, right_border, left_border);
		tetrominos_list[6] = new TPiece(playable_tetris_area, bottom_border, right_border, left_border);
	}
	
	public static MovablePiece pickATetromino() {
		int rand = (int) (Math.random() * tetrominos_list.length);
		MovablePiece mp = tetrominos_list[rand];
		return mp;
	}
}