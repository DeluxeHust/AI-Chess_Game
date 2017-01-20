/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamecovuaai;

public class Board {
    private Spot[][] spots = new Spot[8][8];

    public Board() {
        for(int i=0; i < spots.length; i++){
            for(int j=0; j<spots.length; j++){
                this.spots[i][j] = new Spot(i, j);
            }
        }
    }

    public Spot getSpot(int x, int y) {
        return spots[x][y];
    }
    public int evaluate(){
        int value=0;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(spots[i][j].getPiece()!=null)
                    value+=spots[i][j].getPiece().getValuePiece();
            }
        }
        return value;
    }
    
    public void displayBoard()
    {
//    	System.out.println("\n------------------------Board Chess---------------------------------");
//    	for(int i =0;i < 8;i++)
//    	{
//    		for(int j=0;j < 8; j++)
//    		{
//    			if(getSpot(i,j).isOccupied())
//    			{
//    				if(getSpot(i,j).getPiece().getColor().equals("white"))
//    				{
//    					if(getSpot(i,j).getPiece() instanceof Pawn)
//    					{
//    						System.out.print("| PawnWhite |");
//    					}
//    					if(getSpot(i,j).getPiece() instanceof Rook)
//    					{
//    						System.out.print("| RookWhite |");
//    					}
//    					if(getSpot(i,j).getPiece() instanceof Knight)
//    					{
//    						System.out.print("|KnightWhite|");
//    					}
//    					if(getSpot(i,j).getPiece() instanceof Bishop)
//    					{
//    						System.out.print("|BishopWhite|");
//    					}
//    					if(getSpot(i,j).getPiece() instanceof Queen)
//    					{
//    						System.out.print("|QueenWhite |");
//    					}
//    					if(getSpot(i,j).getPiece() instanceof King)
//    					{
//    						System.out.print("| KingWhite |");
//    					}
//    				}
//    				else  // black piece
//    				{
//    					if(getSpot(i,j).getPiece() instanceof Pawn)
//    					{
//    						System.out.print("| PawnBlack |");
//    					}
//    					if(getSpot(i,j).getPiece() instanceof Rook)
//    					{
//    						System.out.print("| RookBlack |");
//    					}
//    					if(getSpot(i,j).getPiece() instanceof Knight)
//    					{
//    						System.out.print("|KnightBlack|");
//    					}
//    					if(getSpot(i,j).getPiece() instanceof Bishop)
//    					{
//    						System.out.print("|BishopBlack|");
//    					}
//    					if(getSpot(i,j).getPiece() instanceof Queen)
//    					{
//    						System.out.print("|QueenBlack |");
//    					}
//    					if(getSpot(i,j).getPiece() instanceof King)
//    					{
//    						System.out.print("| KingBlack |");
//    					}
//    				}
//    			}
//    			else
//    			{
//    				System.out.print("|           |");
//    			}
//    		}
//    		System.out.println("");
//    	}
    }
          

}
