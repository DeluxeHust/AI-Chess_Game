package gamecovuaai;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Minimax {
	private static final int DEPTH = 4;
	private int fromX;
	private int fromY;
	private int toX;
	private int toY;
	private Piece selectedPiece;	
		
	public void playMove(Game game)  
	{
		Board board = game.getBoard();
		ArrayList <Piece> blackPieceList = game.getBlack().getPieces();// danh sach chua cac quan co cua quan den.
		
		Moves bestMove;  // giá trị lớn nhất trong những thằng tối thiểu
		ArrayList <Moves> bestmoveList = new ArrayList <Moves>();  // danh sách chứa các bestMove lớn nhất (có thể có nhiều bestMove lớn nhất cùng tồn tại) 
		int bestMoveScore;
				
		ArrayList<Moves> moves = new ArrayList<Moves>();    // chứa các nước đi 
							
				for(Piece piece : blackPieceList)
				{															  															
						SuggestMove sugMove = new SuggestMove();						
						ArrayList<Spot> suggestSpot = sugMove.suggestWay(piece, board);
                       
						for(Spot spot : suggestSpot)
						{
							
							Moves move = new Moves(spot.x,spot.y,piece); 
							moves.add(move);							
						}									
				}
				
		bestMove = moves.get(0);		
		performedMoveBlack(board,bestMove);
		bestMoveScore = Min(board, Integer.MIN_VALUE, Integer.MAX_VALUE, DEPTH-1);
		unMakeMove(board,bestMove); // quay lại trạng thái bàn cờ hiện tại lúc truyền vào.
		bestmoveList.add(bestMove);				
			
		for(int i = 1; i < moves.size(); i++)
		{
			Moves newMove = moves.get(i);
			performedMoveBlack(board,newMove);
			int evaluateValue = Min(board, Integer.MIN_VALUE, Integer.MAX_VALUE, DEPTH-1);			
			unMakeMove(board,newMove);
			
			if( evaluateValue > bestMoveScore)
			{
				bestMove = moves.get(i);
				bestMoveScore = evaluateValue;
				bestmoveList.clear(); // xóa sạch danh sách cũ đi 
				bestmoveList.add(bestMove); //> thay vào đó bời bestMove mới có giá trị lớn hơn
			}
			else if(evaluateValue == bestMoveScore)
			{
				bestMove = moves.get(i);
				bestmoveList.add(bestMove);
			}               
						
		}				
		
		
		
		int numberRandom = (int) Math.floor(Math.random()*(bestmoveList.size() ) );		
		//System.out.println("----numberRandom: "+ numberRandom);
		bestMove = bestmoveList.get(numberRandom);		
		
		this.fromX = bestMove.getFromX();
		this.fromY = bestMove.getFromY();
		this.toX = bestMove.getToX();
		this.toY = bestMove.getToY();
						  					
		//System.out.println("-> bestMove");
	    performedMoveBlack(board, bestMove);// con cờ bị xóa chỉ có thể là cờ Trắng
	    this.selectedPiece = bestMove.getPiece();
		
				
		game.getBlack().removeAllElement();
		game.getWhite().removeAllElement();
		
		for(int i =0; i < 4;i++)
		{
			for(int j =0; j< 8;j++)
			{
				Piece piece = board.getSpot(i, j).getPiece();
				Piece piece2 = board.getSpot(7-i,7-j).getPiece();
				if(piece != null )
				{
					if(piece.getColor().equals("white"))
					{
						game.getWhite().getPieces().add(piece);
					}else
					{
						game.getBlack().getPieces().add(piece);
					}
																					
				}
				
				if(piece2 != null)
				{
					if(piece2.getColor().equals("black") )
					{
						game.getBlack().getPieces().add(piece2);
					}
					else
					{
						game.getWhite().getPieces().add(piece2);
					}
				}
			}
		}
		
		
	}
	
	public Piece performedMoveBlack(Board board, Moves move)
	{
		int fromX = move.getFromX(); 
		int fromY = move.getFromY();
		int toX = move.getToX();
		int toY = move.getToY();
		
		Piece selectedPiece = move.getPiece();
		Piece deletedPiece = board.getSpot(toX, toY).getPiece();
		move.setDeletedPiece(deletedPiece);
		
		if(toX == 0 && selectedPiece instanceof Pawn)
		{
			Queen queen = new Queen("black",toX,toY,9000);
			
			board.getSpot(fromX, fromY).releaseSpot();
			board.getSpot(toX, toY).setPiece(queen);
			
			move.setPiece(queen);
			return deletedPiece;  
		}
		board.getSpot(fromX, fromY).releaseSpot();
		board.getSpot(toX, toY).setPiece(selectedPiece);
		
		selectedPiece.setX(toX);
		selectedPiece.setY(toY);
		
		return deletedPiece;
	}
	
	
	public Piece performedMoveWhite(Board board, Moves move)
	{
		int fromX = move.getFromX();
		int fromY = move.getFromY();
		int toX = move.getToX();
		int toY = move.getToY();
		
		Piece selectedPiece = move.getPiece();
		Piece deletedPiece = board.getSpot(toX, toY).getPiece();
		move.setDeletedPiece(deletedPiece);
		
		if(toX == 7 && selectedPiece instanceof Pawn)
		{
			Queen queen = new Queen("white",toX,toY, 9000);
			
			board.getSpot(fromX, fromY).releaseSpot();
			board.getSpot(toX, toY).setPiece(queen);
			
			move.setPiece(queen);
			return deletedPiece;
		}
		
		
		board.getSpot(fromX, fromY).releaseSpot();
		board.getSpot(toX, toY).setPiece(selectedPiece);
		selectedPiece.setX(toX);
		selectedPiece.setY(toY);
		return deletedPiece;
	}
	
	
	
	public int Max(Board board, int alpha, int beta, int depth) {
		if(depth == 0 || gameOver(board))	
			return evaluationFunction(board);
		
		ArrayList<Moves> moves = new ArrayList<Moves>();
		int newAlpha = alpha;
		
		for(int i=0; i<8; i++) {
			for(int j=0;j < 8; j++)	{
				Piece piece = board.getSpot(i, j).getPiece();
				if(piece != null && piece.getColor().equals("black") ) {																			
					SuggestMove sugMove = new SuggestMove();							
					ArrayList <Spot> suggestSpot = sugMove.suggestWay(piece, board);
					
					for(int m = 0; m < suggestSpot.size(); m++) {
						moves.add(new Moves(suggestSpot.get(m).x, suggestSpot.get(m).y, piece) );
					}							
				}
			}
		}
		for(Moves move: moves) {			
			performedMoveBlack(board, move);				
			newAlpha = Math.max(newAlpha, Min(board,newAlpha,beta,depth-1) );
			unMakeMove(board,move);
			if(newAlpha >= beta)
				break;
		}
		return newAlpha;
	}
	
	
	
	public int Min(Board board, int alpha, int beta, int depth)
	{    
		if(depth == 0 ||  gameOver(board))
		{						
			return evaluationFunction(board);
		}					
		
		ArrayList<Moves> moves = new ArrayList<Moves>(); 
		int newBeta = beta;
		for(int i = 0; i < 8; i++)   
		{
			for(int j = 0; j < 8 ;j++)
			{
				Piece piece = board.getSpot(i, j).getPiece();
				if(piece != null && piece.getColor().equals("white") )  //nếu chứa quân cờ thì thực hiện
				{						                    
					SuggestMove sugMove = new SuggestMove();  							
					ArrayList<Spot> suggestSpot = sugMove.suggestWay(piece, board);
					for(int m = 0; m < suggestSpot.size(); m++)
					{
						moves.add( new Moves(suggestSpot.get(m).x, suggestSpot.get(m).y,piece) );
					}
				}
			}
		}
		for(Moves move: moves)
		{			
			performedMoveWhite(board,move);		
			newBeta = Math.min(newBeta, Max(board,alpha,newBeta,depth - 1) );
			unMakeMove(board,move);			
			if(newBeta <= alpha)
				break;
		}
		//System.out.println("betaValue: " + newBeta);
		return newBeta;
	}
	
	
	
	
	public int evaluationFunction(Board board) {  // vì đây là hàm sử dụng cho máy nên nó sẽ lấy tổng giá trị các quân cờ đen trừ đi tổng giá trị các quân cờ trắng               
		int whiteScore = 0;
		int blackScore = 0;
		for(int i = 0; i < 8; i++){
			for(int j=0; j < 8; j++) {
				Piece piece = board.getSpot(i, j).getPiece();
				if(piece != null && piece.getColor().equals("white") )	{
					if(piece instanceof King) {
						whiteScore += 20000 + Rating.getKingWhite(i, j);
					}
					else if(piece instanceof Queen) {
						whiteScore += 900 + Rating.getQueenWhite(i, j);
					}
					else if(piece instanceof Rook) {
						whiteScore += 500 + Rating.getRookWhite(i, j);
					}
					else if(piece instanceof Knight) {
						whiteScore += 320 + Rating.getKnightWhite(i, j);
					}
					else if(piece instanceof Bishop) {
						whiteScore += 330 + Rating.getBishopWhite(i, j);
					}
					else if(piece instanceof Pawn) {
						whiteScore += 100 + Rating.getPawnWhite(i, j);
					}
				}	
				if(piece != null && piece.getColor().equals("black")) {
					if(piece instanceof King) {
						blackScore += 20000 + Rating.getKingBlack(i, j);
					}
					else if(piece instanceof Queen) {
						blackScore += 900 + Rating.getQueenBlack(i, j);
					}
					else if(piece instanceof Rook) {
						blackScore += 500 + Rating.getRookBlack(i, j);
					}
					else if(piece instanceof Knight) {
						blackScore += 320 + Rating.getKnightBlack(i, j);
					}
					else if(piece instanceof Bishop) {
						blackScore += 330 + Rating.getBishopBlack(i, j);
					}
					else if(piece instanceof Pawn) {
						blackScore += 100 + Rating.getPawnBlack(i, j);
					}
				}
				
			}
		}
		return (blackScore - whiteScore);
	}


	public void unMakeMove(Board board, Moves move)  // sử dụng quay lai nước đi 1 cách trực tiếp.
	{
		board.getSpot(move.getToX(), move.getToY()).setPiece(move.getDeletedPiece());
		Piece selectedPiece = copyPiece(move.getPieceNoChangeLocation());
		board.getSpot(move.getFromX(), move.getFromY()).setPiece(selectedPiece);
		move.setPiece(selectedPiece);
	}

	public int getFromX() {
		return fromX;
	}

	public void setFromX(int fromX) {
		this.fromX = fromX;
	}

	public int getFromY() {
		return fromY;
	}

	public void setFromY(int fromY) {
		this.fromY = fromY;
	}

	public int getToX() {
		return toX;
	}

	public void setToX(int toX) {
		this.toX = toX;
	}

	public int getToY() {
		return toY;
	}

	public void setToY(int toY) {
		this.toY = toY;
	}

	public Piece getSelectedPiece() {
		return selectedPiece;
	}

	public void setSelectedPiece(Piece selectedPiece) {
		this.selectedPiece = selectedPiece;
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
	
	public boolean gameOver(Board board) { // true -> game over
	
		int count= 0;
		for(int i = 0; i< 4;i++) {
			for(int j =0; j< 8;j++) {
				if(board.getSpot(i, j).getPiece() instanceof King)
					count++;

				if(board.getSpot(7-i, 7-j).getPiece() instanceof King ){
					count++;
				}
			}
		}
		if(count == 2)
			return false;
		return true;
	}
		
}
