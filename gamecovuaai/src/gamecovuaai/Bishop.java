/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamecovuaai;

public class Bishop extends Piece{
    
    public Bishop(String color, int x, int y,int valuePiece) {
        super(color, x, y,valuePiece);
        super.namePiece="bishop";
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean isValid(int fromX, int fromY, int toX, int toY) {
//        if(super.isValid(fromX, fromY, toX, toY) == false)
//            return false;

//        if(fromX+fromY == toX+toY || Math.abs(fromY-toY)==Math.abs(fromX-toX)){
//           return true;
//        }
//        return false;
        return (fromX+fromY == toX+toY || Math.abs(fromY-toY)==Math.abs(fromX-toX));
    }

}
