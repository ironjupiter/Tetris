import javax.swing.*;
import java.awt.*;

public class MainGameLoop{
	MovablePiece []tetrominos_list = new MovablePiece[7];
	int timer = 0;
	GameWindow game_window;
	private PlayableTetrisArea playable_tetris_area;
	Border bottom_border;
	Border left_border;
	Border top_border;
	Border right_border;
	MovablePiece tetromino;
	
	//main lööp
	public static void main(String[] args) {
		System.out.println("when hard dropping do it inbetween the natrual drops so i won't glicth");
		try {Thread.sleep(1000);}catch (Exception e) {}
		MainGameLoop mgl = new MainGameLoop();
		mgl.mainGameLoop();
	}
	
//	bottom left 100,560
//	top left 100,160
//	bottom right 300,100
//	top right300,290
	public void mainGameLoop() {
	//set up playing area DONT EDIT THIS PART IF YOU DONT UNDERSTAND THE ALL THE SOURCE CODE
		game_window = new GameWindow();
		playable_tetris_area = new PlayableTetrisArea(game_window);
		bottom_border = new Border(100, 560, 200, 10);//Border(x, y, width, height);
		left_border = new Border(bottom_border.x, bottom_border.y-400, 10, 400);
		top_border = new Border(bottom_border.x, left_border.y, bottom_border.width, bottom_border.height);//this one just marks the top
		right_border = new Border((bottom_border.x+bottom_border.width)-10, left_border.y, left_border.width, left_border.height);

		
		//create array
		createTetrominosList(playable_tetris_area, bottom_border, right_border, left_border);

		//add panel
		game_window.addPanel(playable_tetris_area);
		
		//pick tetromino from array
		tetromino = pickATetromino();
		
		//add tetromino to panel
		playable_tetris_area.addPiece(tetromino);
		//DO NOT EDIT THE BORDERS, pls...
		playable_tetris_area.addBorders(bottom_border, left_border, right_border, top_border);
		playable_tetris_area.addKeyListener(tetromino);
		playable_tetris_area.grabFocus();
		
		int repaint_speed_millis = 100;
		int required_num_for_calculations = 100;
		int x = 0;
		while(true) {
			try {Thread.sleep(repaint_speed_millis);}catch (Exception e) {}
			playable_tetris_area.repaint();
			x+=10;
			if(x == repaint_speed_millis) {
				x = 0;
				playable_tetris_area.mp.printYValue();
				tetromino.fall();
				//if tetromino hit the ground replace it with static blocks and create a new one for the player
				if(tetromino.checkIfAnyBlockBottomCollided() == true || tetromino.checkIfOneBlockBottomSideWillCollide() == true) {
					if(timer == 1) {
						playable_tetris_area.addBlockToHashMap(tetromino);
						playable_tetris_area.removeKeyListener(tetromino);//old peice is removed					
						
						tetromino.y = 180;
						tetromino.x = 180;
						tetromino = pickATetromino();
						playable_tetris_area.addPiece(tetromino);
						playable_tetris_area.addKeyListener(tetromino);//add new peice
						timer = 0;
					}	
					timer++;
					playable_tetris_area.runLineChecker(right_border.height);
					playable_tetris_area.repaint();
				}
			}
		}
	}
	
	public void createTetrominosList(PlayableTetrisArea playable_tetris_area,Border bottom_border,Border right_border,Border left_border) {
		tetrominos_list[0] = new SPiece(playable_tetris_area, bottom_border, right_border, left_border);
		tetrominos_list[1] = new SquarePiece(playable_tetris_area, bottom_border, right_border, left_border);
		tetrominos_list[2] = new ZPiece(playable_tetris_area, bottom_border, right_border, left_border);
		tetrominos_list[3] = new LPiece(playable_tetris_area, bottom_border, right_border, left_border);
		tetrominos_list[4] = new JPiece(playable_tetris_area, bottom_border, right_border, left_border);
		tetrominos_list[5] = new LinePiece(playable_tetris_area, bottom_border, right_border, left_border);
		tetrominos_list[6] = new TPiece(playable_tetris_area, bottom_border, right_border, left_border);
	}
	
	public MovablePiece pickATetromino() {
		int rand = (int) (Math.random() * tetrominos_list.length);
		MovablePiece mp = tetrominos_list[rand];
		return mp;
	}
}