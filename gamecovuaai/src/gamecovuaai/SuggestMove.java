package gamecovuaai;

import java.util.ArrayList;

public class SuggestMove {

	
	 public ArrayList<Spot> suggestWay(Piece piece,Board board)//Gợi ý các nước đi cho 1 quân cờ
	 {
	        int i,j;
	        ArrayList<Spot> suggestMove = new ArrayList<Spot>();
	        if( (piece instanceof Knight) || (piece instanceof King) ){
	            for(i = 0; i < 8 ; i++){
	                for(j=0; j<8 ;j++){
	                    if(piece.isValid(piece.getX(), piece.getY(), i, j)){
	                        if(board.getSpot(i, j).isOccupied() == false || board.getSpot(i, j).getPiece().getColor()!=piece.getColor()){
	                            suggestMove.add(board.getSpot(i, j)); 
	                        }
	                    }
	                }       
	            }
	        }
	        else if(piece instanceof Pawn){//Tốt có nước đi và nước ăn khác nhau nên xử lí riêng
	             for(i=0;i<8;i++){
	                for(j=0;j<8;j++){
	                    if(((Pawn) piece).isValid(piece.getColor(), piece.getX(), piece.getY(), i, j)){
	                    	
	                    	if(piece.getColor().equals("white"))    
	                    	{
	                    		if( piece.getX() == 1 && ( board.getSpot(2, j).isOccupied() ) )  // neu tot o vi tri ban dau
	                        	{                                         // ma co 1 con chan truoc mat thi no se khong di duoc
	                        		;
	                        	}
	                        	else if(!board.getSpot(i, j).isOccupied()){   // nếu ô đi tới rỗng thì đi tốt
	                                suggestMove.add(board.getSpot(i, j));     // còn nếu đã chứa quân cờ thì không
	                            }                                                        // được đi
	                    	}
	                    	else   	// kiểm tra tương tự với quân tốt Đen
	                    	{
	                    		if( piece.getX() == 6 && ( board.getSpot(5, j).isOccupied() ) )  
	                        	{
	                        		;
	                        	}
	                        	else if(!board.getSpot(i, j).isOccupied()){
	                                suggestMove.add(board.getSpot(i, j));
	                            }     
	                    	}                                   
	                    	
	                    }
	                    
	                    if(((Pawn) piece).isKillRival(piece.getColor(), board.getSpot(piece.getX(), piece.getY()), board.getSpot(i, j)))
	                        suggestMove.add(board.getSpot(i, j));
	                }       
	             }
	        }
	        else{
	            for(i=0;i<8;i++){//Các quân xe,tịnh,hậu không thể đi nếu bị cản nên phải xử lí riêng
	                for(j=0;j<8;j++){
	                    if(piece.isValid(piece.getX(), piece.getY(), i, j)){
	                        if(board.getSpot(i, j).isOccupied()==false||board.getSpot(i, j).getPiece().getColor()!=piece.getColor()){
	                            if(( piece.getX() + piece.getY() == i+j )||Math.abs(piece.getY()-j)==Math.abs(piece.getX()-i)){  //-----------------?
	                                if( isGoBias(piece.getX(), piece.getY(), i, j,board) == true )
	                                    suggestMove.add(board.getSpot(i, j));
	                            }
	                            if(piece.getX()==i|| piece.getY()==j){
	                                if( isGoStraight(piece.getX(), piece.getY(), i, j,board) )
	                                    suggestMove.add(board.getSpot(i, j));
	                            }
	                        }
	                    }
	            }       
	        }
	        }
	        return suggestMove;
	    }
	
	 private boolean isGoStraight(int fromX,int fromY,int toX,int toY,Board board)
	 {//kiểm tra xem có thể đi thẳng dc không
	        int min,max;
	        if(fromX==toX){
	            min=(fromY<toY)?fromY:toY;
	            max=(fromY>toY)?fromY:toY;
	            for(int i=min+1;i<=max-1;i++){
	                if(board.getSpot(fromX, i).getPiece()!=null)
	                    return false;
	            }
	            return true;
	        }
	        else{
	            min=(fromX<toX)?fromX:toX;
	            max=(fromX>toX)?fromX:toX;
	            for(int i=min+1;i<=max-1;i++){
	                if(board.getSpot(i, fromY).getPiece()!=null)
	                    return false;
	            }
	            return true;
	        }
	    }
	    
	    
	    
	    private boolean isGoBias(int fromX,int fromY,int toX,int toY,Board board)
	    {//kiểm tra xem có đi chéo được không
	        int minX,maxX,minY,maxY;
	        minX=fromX;
	        minY=fromY;
	        maxX=toX;
	        maxY=toY;
	        if(minX>maxX){
	            minX=toX;
	            minY=toY;
	            maxX=fromX;
	            maxY=fromY;
	        }
	        if(fromX+fromY==toX+toY){
	            for(int i=minX+1;i<=maxX-1;i++){
	                if(board.getSpot(i, fromX+fromY-i).isOccupied()==true){
	                    return false;
	                }
	            }
	        }
	        else{
	            for(int i=minX+1;i<=maxX-1;i++){
	                if(board.getSpot(i, ++minY).isOccupied()==true) return false;
	            }
	        }
	        return true;
	    }
	    
	    
	    
/**	    public void displaySuggestMove()
*	    {
*	    	for(int i=0; i < this.suggestMove.size(); i++)
*	    	{
	    		System.out.println("->Move "+ (i+1) + " (" + this.suggestMove.get(i).x + "," + this.suggestMove.get(i).y + ")" );
	    	}
	    }

*/    
	    
}
