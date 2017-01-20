/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamecovuaai;

public class Queen extends Piece{
    
    public Queen(String color, int x, int y,int valuePiece) {
        super(color, x, y,valuePiece);
        super.namePiece="queen";
    }

    @Override
    public boolean isValid( int fromX, int fromY, int toX, int toY) {
//        if(super.isValid(fromX, fromY, toX, toY) == false)
//            return false;
        //diagonal
        if(Math.abs(toX - fromX) == Math.abs(toY - fromY))
            return true;
        if(toX == fromX)
            return true;
        if(toY == fromY)
            return true;

        return false;
    }

}