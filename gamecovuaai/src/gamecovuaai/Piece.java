/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamecovuaai;

public class Piece {
    String namePiece;
    private String color;
    private int x;
    private int y;
    private int valuePiece;
   
    public Piece()
    {}
    
    public Piece(String color, int x, int y,int valuePiece) {
        this.color = color;
        this.x = x;
        this.y = y;
        namePiece=" ";
        this.valuePiece = valuePiece;
    }


    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    
    public void setValuePiece(int valuePiece)
    {
    	this.valuePiece = valuePiece;
    }
  
    public int getValuePiece()
    {
    	return this.valuePiece;
    }
    
    public boolean isValid(int fromX, int fromY, int toX, int toY){
//        return !(toX < 0 || toX > 7 || fromX < 0 || fromX > 7 || toY < 0 || toY > 7 || fromY <0 || fromY > 7 || (toX==fromX && toY==fromY));
////            return false;
        return true;
    }
}
