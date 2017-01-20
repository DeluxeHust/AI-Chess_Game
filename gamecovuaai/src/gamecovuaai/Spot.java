/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamecovuaai;

public class Spot {
    int x;
    int y;
    private Piece piece;

   

    public Spot(int x, int y) {
        this.x = x;
        this.y = y;
        piece = null;
    }
     public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    public void occupySpot(Piece piece){
        this.piece = piece;
    }

    public boolean isOccupied() {  //true -> nếu chứa quân cờ 
        if(piece != null)    //> false -> nếu không chứa quân cờ
            return true;
        return false;
    }

    public Piece releaseSpot() {
        Piece releasedPiece = this.piece;
        this.piece = null;
        return releasedPiece;
    }

}
