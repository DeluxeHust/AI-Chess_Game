/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamecovuaai;

public class Game {
    private Board board;
    private Player white;
    private Player black;
    public Game() {
        super();
        board=new Board();
        this.white=new Player(true);
        this.black=new Player(false);
        initializeBoardGivenPlayers(white);
        initializeBoardGivenPlayers(black);
    }

    public void setColorWhite(Player player) {
        this.white = player;
    }

    public void setColorBlack(Player player) {
        this.black = player;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getWhite() {
        return white;
    }

    public void setWhite(Player white) {
        this.white = white;
    }

    public Player getBlack() {
        return black;
    }

    public void setBlack(Player black) {
        this.black = black;
    }

    public boolean initializeBoardGivenPlayers(Player X) {
        if(X==null)
            return false;
        for(int i=0; i<X.getPieces().size(); i++){
            board.getSpot(X.getPieces().get(i).getX(), X.getPieces().get(i).getY() ).occupySpot(X.getPieces().get(i));
        }
        return true;
    }
    
    
    public void resetGame()
    {
    	
    	white.removeAllElement();
    	black.removeAllElement();
    	
    	for(int i=0; i<8 ;i++)
    	{
    		for(int j=0; j<8 ; j++)
    		{
    			board.getSpot(i, j).releaseSpot();
    		}
    	}
    	
    	white.initializePieces();
    	black.initializePieces();
    	
    	initializeBoardGivenPlayers(white);
        initializeBoardGivenPlayers(black);
        
    }
    
}