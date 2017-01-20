/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamecovuaai;

/**
 *
 * @author ANL
 */
public class Pawn extends Piece {
    
    public Pawn(String color, int x, int y,int valuePiece) {
        super(color, x, y,valuePiece);
        super.namePiece = "pawn";
    }
    public boolean isValid(String color,int fromX,int fromY,int toX,int toY){
//        if(super.isValid( fromX, fromY, toX, toY) == false){
//            return false;
//        }
        if(color.equals("white")){
            if(toX<fromX) return false;
            if(fromY==toY){
               if(fromX==1){
                   if( (toX-fromX <= 2 && toX-fromX>0) ) return true;    //---------------------
               }
               else
                   if(toX - fromX == 1) return true;
            }
            return false;   
        }
        else{
            if(toX > fromX) return false;
            if(fromY == toY){
                if(fromX==6){
                    if(fromX-toX <= 2&&fromX-toX > 0) return true;
                }    
                else
                    if(fromX - toX == 1) return true;
            }
            return false;
        }
    }
    
    public boolean isKillRival(String color,Spot from, Spot to){
       
//        if( super.isValid( from.x, from.y, to.x, to.y) == false){
//            return false;
//        }
        if(color.equals("white")){
            if(to.x - from.x==1 && (to.y - from.y == 1||to.y - from.y == -1)){
                if(to.getPiece()!=null&& to.getPiece().getColor().equals("black") ) return true;
            }
            return false;
        }
        else{
            if(from.x-to.x==1&&(to.y-from.y==1||to.y-from.y==-1)){
                if(to.getPiece()!=null && to.getPiece().getColor().equals("white")) return true;
            }
            return false;
        }
    }
}
