/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamecovuaai;

import java.util.ArrayList;

public class Player {
    public final int PAWNS = 8;
    public boolean white;

    private ArrayList<Piece> pieces = new ArrayList<>();

    public Player(boolean white) {
//        super();
        this.white = white;
        initializePieces();
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }


    public void initializePieces(){//Quan trang o tren, quan den o duoi
        if(white==true){
            for(int i=0; i<PAWNS; i++){ // draw pawns
                pieces.add(new Pawn("white",1,i,100) );
            }
            pieces.add(new Rook("white", 0, 0,500) );
            pieces.add(new Rook("white", 0, 7,500) );
            pieces.add(new Bishop("white", 0, 2,330) );
            pieces.add(new Bishop("white", 0, 5,330) );
            pieces.add(new Knight("white", 0, 1,320) );
            pieces.add(new Knight("white", 0, 6,320) );
            pieces.add(new Queen("white", 0, 3,900) );
            pieces.add(new King("white", 0, 4,20000) );
        }
        else{
            for(int i=0; i<PAWNS; i++){ // draw pawns
                pieces.add(new Pawn("black",6,i,100) );
            }
            pieces.add(new Rook("black", 7, 0,500) );
            pieces.add(new Rook("black", 7, 7,500) );
            pieces.add(new Bishop("black",7, 2,330) );
            pieces.add(new Bishop("black", 7, 5,330) );
            pieces.add(new Knight("black", 7, 1,320) );
            pieces.add(new Knight("black", 7, 6,320) );
            pieces.add(new Queen("black", 7, 3,900) );
            pieces.add(new King("black", 7, 4,20000) );
        }

    }
    public void removeAllElement()
    {
    	pieces.clear();
    }
}
