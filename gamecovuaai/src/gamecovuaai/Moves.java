package gamecovuaai;

import java.util.ArrayList;

public class Moves {
	private int fromX;
	private int fromY;
	private int toX;
	private int toY;
	private Piece piece; 
	private Piece pieceNoChangeLocation;
	private Piece deletedPiece;
	
	public Moves()
	{
		
	}
	
	public Moves(int toX, int toY, Piece piece)
	{
		this.fromX = piece.getX();
		this.fromY = piece.getY();
		this.toX = toX;
		this.toY = toY;
		this.piece = piece;  // piece này có thể thay đổi tọa độ khi ta truyền move vào trong phương thức performedMove();
		this.pieceNoChangeLocation = copyPiece(piece); // thằng này sẽ không bị thay đổi tọa độ khi ta truyền vào phương thức performedMove()
		deletedPiece = null;
	}
	
	
	public Moves(Moves move)  // phương thức này dùng để copy move truyền vào.
	{
		
	}
	
	
	
	public void setFromX(int fromX)
	{
		this.fromX = fromX;
	}
	
	public int getFromX()
	{
		return this.fromX;
	}
	
	public void setFromY(int fromY)
	{
		this.fromY = fromY;
	}
	
	public int getFromY()
	{
		return this.fromY;
	}
	
	public void setToX(int toX)
	{
		this.toX = toX;
	}
	
	public int getToX()
	{
		return this.toX;
	}
	
	public void setToY(int toY)
	{
		this.toY = toY;
	}
	
	public int getToY()
	{
		return this.toY;
	}
	
	public void setPiece(Piece piece)
	{
		this.piece = piece;
	}
	
	public Piece getPiece()
	{
		return this.piece;
	}
		
	public Piece getPieceNoChangeLocation() {
		return pieceNoChangeLocation;
	}
	
	
	public Piece getDeletedPiece() {
		return deletedPiece;
	}

	public void setDeletedPiece(Piece deletedPiece) {
		this.deletedPiece = deletedPiece;
	}

	public Piece copyPiece( Piece originalPiece)
	{
		Piece copyPiece;
		if(originalPiece.getColor().equals("white") ) // quan trang
		{
			if(originalPiece instanceof King)
			{
				copyPiece = new King("white", originalPiece.getX(), originalPiece.getY(),20000);
			}
			else if(originalPiece instanceof Queen)
			{
				copyPiece = new Queen("white", originalPiece.getX(), originalPiece.getY(),900);
			}
			else if(originalPiece instanceof Rook)
			{
				copyPiece = new Rook("white", originalPiece.getX(), originalPiece.getY(),500);
			}
			else if(originalPiece instanceof Bishop)
			{
				copyPiece = new Bishop("white", originalPiece.getX(), originalPiece.getY(),330);
			}
			else if(originalPiece instanceof Knight)
			{
				copyPiece = new Knight("white", originalPiece.getX(), originalPiece.getY(),320);
			}
			else
			{
				copyPiece = new Pawn("white", originalPiece.getX(), originalPiece.getY(),100);
			}
		}
		else        // quan den
		{
			if(originalPiece instanceof King)
			{
				copyPiece = new King("black", originalPiece.getX(), originalPiece.getY(),20000);
			}
			else if(originalPiece instanceof Queen)
			{
				copyPiece = new Queen("black", originalPiece.getX(), originalPiece.getY(),900);
			}
			else if(originalPiece instanceof Rook)
			{
				copyPiece = new Rook("black", originalPiece.getX(), originalPiece.getY(),500);
			}
			else if(originalPiece instanceof Bishop)
			{
				copyPiece = new Bishop("black", originalPiece.getX(), originalPiece.getY(),330);
			}
			else if(originalPiece instanceof Knight)
			{
				copyPiece = new Knight("black", originalPiece.getX(), originalPiece.getY(),320);
			}
			else
			{
				copyPiece = new Pawn("black", originalPiece.getX(), originalPiece.getY(),100);
			}
		}
		return copyPiece;
	}
	
	
	
	
	
	public void displayMove()
	{
		System.out.println(this.piece.namePiece + " (" + this.fromX + "," + this.fromY + ")" + "-->" + "(" + this.toX + "," + this.toY + ")" );
	}
}