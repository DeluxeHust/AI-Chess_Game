 package gamecovuaai;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class UserInterface extends JFrame implements ActionListener{
	
	
    JButton[][] cell = new JButton[8][8];
    //JButton firstButton = new JButton();  // nút này dùng để lưu nút chứa quân cờ cần để đi được chọn lần thứ nhất
    
    private final ImageIcon totDenIcon=new ImageIcon("picture\\TotDen.png");
    private final ImageIcon totTrangIcon=new ImageIcon("picture\\TotVang.png");
    private final ImageIcon maDenIcon=new ImageIcon("picture\\MaDen.png");
    private final ImageIcon maTrangIcon=new ImageIcon("picture\\MaVang.png");
    private final ImageIcon tinhDenIcon=new ImageIcon("picture\\TinhDen.png");
    private final ImageIcon tinhTrangIcon=new ImageIcon("picture\\TinhVang.png");
    private final ImageIcon hauDenIcon=new ImageIcon("picture\\HauDen.png");
    private final ImageIcon hauTrangIcon=new ImageIcon("picture\\HauVang.png");
    private final ImageIcon vuaDenIcon=new ImageIcon("picture\\VuaDen.png");
    private final ImageIcon vuaTrangIcon=new ImageIcon("picture\\VuaVang.png");
    private final ImageIcon xeDenIcon=new ImageIcon("picture\\XeDen.png");
    private final ImageIcon xeTrangIcon=new ImageIcon("picture\\XeVang.png");
    
    
    private Minimax AI = new Minimax();
    private static int fromX, fromY, toX, toY; // lưa tọa độ cũ và tọa độ mới của quân cờ đen được máy đi.
    private static int oldX, oldY;
    
    private Human human = new Human();
    private Moves movePiece = null;  // lưa nước di chuyển của Human
    private Piece selectedPiece = null;  // con cờ lựa chọn để đi
    
    private Game game=new Game();
    private boolean flag  = false;  
    private boolean turnWhite=true;

    private ArrayList<Spot> suggest = new ArrayList();   // dùng đểu lưu các nước đi gợi ý của quân cờ.
    
    public UserInterface(){
        setSize(500, 500);
        setTitle("Chess Game");
        setLayout(new GridLayout(8, 8));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        addButtonInFrame();
        playerChooseLocation();
        
    }
    private void playerChooseLocation(){   
        int i,j;
        for(i= 0;i<game.getWhite().getPieces().size();i++){
            setImageForWhitePiece(game.getWhite().getPieces().get(i),cell[game.getWhite().getPieces().get(i).getX()][game.getWhite().getPieces().get(i).getY()]);
        }
        for(i= 0;i<game.getBlack().getPieces().size();i++){
            setImageForBlackPiece(game.getBlack().getPieces().get(i), cell[game.getBlack().getPieces().get(i).getX()][game.getBlack().getPieces().get(i).getY()]);
        }
    }
  
    
    private void setImageForWhitePiece(Piece piece,JButton button) //set image for button from white piece
    {
    	switch(piece.namePiece)
    	{
    	case "pawn":
            button.setIcon(totTrangIcon);
            break;
        case "queen":
            button.setIcon(hauTrangIcon);
            break;
        case "rook":
            button.setIcon(xeTrangIcon);
            break;
        case "bishop":
            button.setIcon(tinhTrangIcon);
            break;
        case "knight":
            button.setIcon(maTrangIcon);
            break;
        case "king":
            button.setIcon(vuaTrangIcon);
            break;
        default:
            button.setIcon(null);
            break;
        }
    }
    
    private void setImageForBlackPiece(Piece piece, JButton button)
    {
    	 switch(piece.namePiece){
         case "pawn":
             button.setIcon(totDenIcon);
             break;
         case "queen":
             button.setIcon(hauDenIcon);
             break;
         case "rook":
             button.setIcon(xeDenIcon);
             break;
         case "bishop":
             button.setIcon(tinhDenIcon);
             break;
         case "knight":
             button.setIcon(maDenIcon);
             break;
         case "king":
             button.setIcon(vuaDenIcon);
             break;
         default:
             button.setIcon(null);
             break;
        }
    }
    
    
    private void  addButtonInFrame()
    {
        int i,j;
        for(i=0;i<8;i++){
           for(j=0;j<8;j++){
               cell[i][j]=new JButton();
               this.add(cell[i][j]);
               cell[i][j].addActionListener(this);
               if((i+j)%2==0) cell[i][j].setBackground(Color.WHITE);
               else cell[i][j].setBackground(new Color(153,153,153));
           }
        }
    }
    
    
    
  //di chuyển vị trí quân cờ
      
    
    public void changeTurn()
    {
    	boolean gameover = false;
    	while(gameover == false)
    	{
    		if(turnWhite)  // HumanTurn
    		{    			
    			if(movePiece != null)
    			{
    				selectedPiece = human.playMove(game,movePiece);
    				cell[movePiece.getFromX()][movePiece.getFromY()].setIcon(null);
    				setImageForWhitePiece(selectedPiece,cell[movePiece.getToX()][movePiece.getToY()]);
    				
    				if( (movePiece.getFromX() + movePiece.getFromY() )%2==0)
    				{
    					cell[movePiece.getFromX()][movePiece.getFromY()].setBackground(Color.white);
    				}else
    				{
    					cell[movePiece.getFromX()][movePiece.getFromY()].setBackground(new Color(153,153,153));
    				}
    				
    				movePiece = null;
    				selectedPiece = null; // không quan trọng không có cũng được
        			turnWhite = false;
        			        			        			        			
        			
        			if( checkWin("black") )
       	            {
                   	   JOptionPane.showMessageDialog(null, "Player Win!");
                 	   game.resetGame();
             	   
                  	   for(int m=0 ; m<8; m++)
              		   for(int n= 0;n<8;n++)
             		   {
             			   cell[m][n].setIcon(null);
             		   }
                  	   if( (fromX + fromY)%2 == 0)
                  	   {
                  		   cell[fromX][fromY].setBackground(Color.white);
                  	   }else
                  	   {
                  		   cell[fromX][fromY].setBackground(new Color(153,153,153));
                  	   }
                  	 
                  	   if( (toX + toY)%2 ==0 )
                  	   {
                      	   cell[toX][toY].setBackground(Color.white);
                  	   }else
                       {
                      	   cell[toX][toY].setBackground(new Color(153,153,153));
                  	   }
                   	   
                   	   playerChooseLocation();                 	   
              	       turnWhite = true;
          	       }                           
        			
        			
    			}    			    		    			
    			
    		}else  // Computer
    		{
    			AI.playMove(game);
    			setImageForBlackPiece(AI.getSelectedPiece(), cell[AI.getToX()][AI.getToY()]);
    			cell[AI.getFromX()][AI.getFromY()].setIcon(null);
    			
    			fromX = AI.getFromX();
                fromY = AI.getFromY();
                toX = AI.getToX();
                toY = AI.getToY();
                cell[AI.getFromX()][AI.getFromY()].setBackground(Color.red);
                cell[AI.getToX()][AI.getToY()].setBackground(Color.blue);    			    			    			    			    			
    			turnWhite = true;
    			
    			//<test
//    			game.getBoard().displayBoard();
//    			System.out.println("----->whitePiece: ");
//    			for(Piece p : game.getWhite().getPieces())
//    			{
//    				System.out.println(p.namePiece + " "+ p.getX() + ", " + p.getY());
//    			}
//    			
//    			
//    			System.out.println("------>blackPiece: ");
//    			for(Piece p : game.getBlack().getPieces())
//    			{
//    				System.out.println(p.namePiece + " "+ p.getX() + ", " + p.getY());
//    			}
//    			        			
//    			//test>
    			
    			
    			 if( checkWin("white"))
                 {
                     JOptionPane.showMessageDialog(null, "Computer win!");
                     game.resetGame();
                     for(int m= 0; m<8; m++){                    	                 
                         for(int n=0; n<8 ;n++){                        	                             
                          		   cell[m][n].setIcon(null);
                         }
                     }
                    if( (fromX + fromY)%2 == 0)
                 	{
                 		cell[fromX][fromY].setBackground(Color.white);
                 	}else
                 	{
                 		cell[fromX][fromY].setBackground(new Color(153,153,153));
                 	}
                 	 
                 	if( (toX + toY)%2 ==0 )
                 	{
                     	cell[toX][toY].setBackground(Color.white);
                 	}else
                 	{
                     	cell[toX][toY].setBackground(new Color(153,153,153));
                 	}
                     playerChooseLocation();                    
                     turnWhite = true;                    	               
                 }                    
    		}
    	}
    }
    
      
    
    @Override                  
    public void actionPerformed(ActionEvent e)
    {
    	Board board = game.getBoard();    	    	
    	if(turnWhite)
    	{
    		for(int i = 0; i < 8; i++)
        	{
        		for(int j = 0; j< 8; j++)
        		{    			    		
        			if(e.getSource() == cell[i][j])
        			{    			
        				if( (fromX + fromY)%2 == 0)
                    	{
                    		cell[fromX][fromY].setBackground(Color.white);
                    	}else
                    	{
                    		cell[fromX][fromY].setBackground(new Color(153,153,153));
                    	}
                    	 
                    	if( (toX + toY)%2 ==0 )
                    	{
                        	cell[toX][toY].setBackground(Color.white);
                    	}else
                    	{
                        	cell[toX][toY].setBackground(new Color(153,153,153));
                    	}
                    	
                    	
                    	
        				if(flag == false)
        				{
        					selectedPiece = board.getSpot(i,j).getPiece();
            				if(selectedPiece != null && selectedPiece.getColor().equals("white") )
            				{
            					SuggestMove sugMove = new SuggestMove();
            					suggest = sugMove.suggestWay(selectedPiece,board);
            					
            					cell[i][j].setBackground(Color.red);
            					oldX = i;
            					oldY = j;
            					
            					for(Spot spot : suggest)
            					{
            						cell[spot.x][spot.y].setBackground(Color.green);
            					}
            					flag = true;
            				}
        				}
        				else 
        				{
        					if( suggest.contains(board.getSpot(i,j)) )
        					{
        						if( (oldX + oldY)%2 ==0 )
            					{
            						cell[oldX][oldY].setBackground(Color.white);
            					}
            					else
            					{
            						cell[oldX][oldY].setBackground(new Color(153,153,153));
            					}
            					
            					for(Spot spot : suggest)
            					{
            						if( (spot.x + spot.y)%2 == 0)
            						{
            							cell[spot.x][spot.y].setBackground(Color.white);    							
            						}
            						else
            						{
            							cell[spot.x][spot.y].setBackground(new Color(153,153,153));
            						}
            					}    					    		
            					cell[oldX][oldY].setIcon(null);
            					//setImageForWhitePiece(selectedPiece,cell[i][j]);
            					movePiece = new Moves(i,j,selectedPiece);
            					flag = false;
            					suggest.clear();
        					}
        					else
            				{
            					
            					if( (oldX + oldY)%2 ==0 )
            					{
            						cell[oldX][oldY].setBackground(Color.white);
            					}
            					else
            					{
            						cell[oldX][oldY].setBackground(new Color(153,153,153));
            					}
            					
            					for(Spot spot : suggest)
            					{
            						if( (spot.x + spot.y)%2 == 0)
            						{
            							cell[spot.x][spot.y].setBackground(Color.white);    							
            						}
            						else
            						{
            							cell[spot.x][spot.y].setBackground(new Color(153,153,153));
            						}
            					}
            					flag = false;
            					suggest.clear();
            				}
        				}        				
        			}
        		}
        	}
    	}
    	
    }
  
    
    
    
    public boolean checkWin(String color)
    {
    	for(int i = 0;i < 8; i++)
    	{
    		for(int j = 0; j<8 ;j++)
    		{
    			if( game.getBoard().getSpot(i, j).getPiece() != null ){
    				if( (game.getBoard().getSpot(i, j).getPiece() instanceof King) && ( game.getBoard().getSpot(i, j).getPiece().getColor().equals(color) ) )
    				{
    					return false;
    				}
    			}
    		}
    	}	
    	return true;
    }
    
   //----------------------------------------------------------------
    
   
    
    
    
}


