import java.awt.*;
public class ListOfPieces {
	PlayableTetrisArea canvas;
	Border bottom;
	Border left;
	Border right;
	MovablePiece[] listOfMovablePieces = new MovablePiece[7];
	public ListOfPieces(PlayableTetrisArea p, Border s_bottom, Border s_left, Border s_right) {
		canvas = p;
		bottom = s_bottom;
		left = s_left;
		right = s_right;
	}
	
	public void createList() {
		listOfMovablePieces[0] = new LinePiece(canvas, bottom, left, right);
		listOfMovablePieces[1] = new SquarePiece(canvas, bottom, left, right);
		listOfMovablePieces[2] = new SPiece(canvas, bottom, left, right);
		listOfMovablePieces[3] = new ZPiece(canvas, bottom, left, right);
		listOfMovablePieces[4] = new TPiece(canvas, bottom, left, right);
		listOfMovablePieces[5] = new JPiece(canvas, bottom, left, right);
		listOfMovablePieces[6] = new LPiece(canvas, bottom, left, right);
	}
	
	public MovablePiece giveAPiece() {
		int r = (int) Math.random() * listOfMovablePieces.length;
		return listOfMovablePieces[r];
	}
}
