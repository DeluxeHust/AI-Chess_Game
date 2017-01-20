package gamecovuaai;

public class Human { // chua thong tin cua nguoi choi.
	
	public Piece playMove(Game game, Moves move) // trả về quân cờ dk chon.
	{
		Board board = game.getBoard();
		int fromX  = move.getFromX();
		int fromY = move.getFromY();
		int toX = move.getToX();
		int toY = move.getToY();
				
		
		Piece selectedPiece = move.getPiece();  // trỏ đến piece ở game.getBoard().getSpot(fromX,fromY).getPiece(); cũng chính là thằng piece trong game.getWhite()
		Piece deletedPiece = board.getSpot(toX, toY).getPiece();
		move.setDeletedPiece(deletedPiece);
		
		if(toX == 7 && selectedPiece instanceof Pawn)
		{
			Queen queen = new Queen("white",toX,toY,1000);
			 
			board.getSpot(fromX, fromY).releaseSpot();
			board.getSpot(toX, toY).setPiece(queen);
			
			
			game.getBlack().getPieces().remove(deletedPiece);
			game.getWhite().getPieces().remove(selectedPiece);
			game.getWhite().getPieces().add(queen);
			
			move.setPiece(queen);
			return queen;
		}
		
		game.getBlack().getPieces().remove(deletedPiece);
		board.getSpot(fromX, fromY).releaseSpot();
		board.getSpot(toX, toY).setPiece(selectedPiece);
		selectedPiece.setX(toX);
		selectedPiece.setY(toY);
		return selectedPiece;
	}
}
