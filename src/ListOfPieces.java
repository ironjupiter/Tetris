import java.awt.*;
public class ListOfPieces {
	PlayableTetrisArea canvas;
	Border yb;
	MovablePiece[] listOfMovablePieces = new MovablePiece[7];
	public ListOfPieces(PlayableTetrisArea p, Border y) {
		canvas = p;
		yb  = y;
	}
	
	public void createList() {
		listOfMovablePieces[0] = new LinePiece(canvas, yb);
		listOfMovablePieces[1] = new SquarePiece(canvas, yb);
		listOfMovablePieces[2] = new SPiece(canvas, yb);
		listOfMovablePieces[3] = new ZPiece(canvas, yb);
		listOfMovablePieces[4] = new TPiece(canvas, yb);
		listOfMovablePieces[5] = new JPiece(canvas, yb);
		listOfMovablePieces[6] = new LPiece(canvas, yb);
	}
	
	public MovablePiece giveAPiece() {
		int r = (int) Math.random() * listOfMovablePieces.length;
		return listOfMovablePieces[r];
	}
}
