/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamecovuaai;

public class Knight extends Piece{
    
    public Knight(String color, int x, int y,int valuePiece) {
        super(color, x, y,valuePiece);
        super.namePiece="knight";
    }

    @Override
    public boolean isValid(int fromX, int fromY, int toX, int toY) {
//        if(super.isValid( fromX, fromY, toX, toY) == false)
//            return false;
//        if(Math.abs(toX-fromX)<=2&&Math.abs(toY-fromY)<=2&&toX!=fromX&&toY!=fromY&&(Math.abs(toX-fromX)!=Math.abs(toY-fromY)))
//            return true;
//        return false;
           return (Math.abs(toX-fromX)<=2&&Math.abs(toY-fromY)<=2&&toX!=fromX&&toY!=fromY&&(Math.abs(toX-fromX)!=Math.abs(toY-fromY)));
        
    }

}
