/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamecovuaai;

public class King extends Piece{
    
    public King(String color, int x, int y,int valuePiece) {
        super(color, x, y,valuePiece);
        super.namePiece="king";
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean isValid( int fromX, int fromY, int toX, int toY) {
//        if(super.isValid(fromX, fromY, toX, toY) == false)
//            return false;
//        if(Math.sqrt(Math.pow(Math.abs((toX - fromX)),2) + Math.pow(Math.abs((toY - fromY)), 2)) > Math.sqrt(2)){
//            return false;
//        }
////          if(Math.abs(toX-fromX)==1||Math.abs(toY-fromY)==1) return true;
//        return true;
        return !(Math.sqrt(Math.pow(Math.abs((toX - fromX)),2) + Math.pow(Math.abs((toY - fromY)), 2)) > Math.sqrt(2));
    }

}
