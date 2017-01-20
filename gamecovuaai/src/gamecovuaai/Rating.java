package gamecovuaai;

public class Rating {
	
	// giá trị thế cờ cho quân cờ đen.	
	private static int[][] pawnBlack=
            {
		{0,  0,  0,  0,  0,  0,  0,  0},
                {50, 50, 50, 50, 50, 50, 50, 50},
		{10, 10, 20, 30, 30, 20, 10, 10},
		{5,  5, 10, 25, 25, 10,  5,  5},
		{ 0,  0,  0, 20, 20,  0,  0,  0},
		{ 5, -5,-10,  0,  0,-10, -5,  5},
		{5, 10, 10,-20,-20, 10, 10,  5},
                {0,  0,  0,  0,  0,  0,  0,  0}
            };
	
	private static int[][] knightBlack= 
		{
				{-50,-40,-30,-30,-30,-30,-40,-50},
				{-40,-20,  0,  0,  0,  0,-20,-40},
				{-30,  0, 10, 15, 15, 10,  0,-30},
				{-30,  5, 15, 20, 20, 15,  5,-30},
				{-30,  0, 15, 20, 20, 15,  0,-30},
				{-30,  5, 10, 15, 15, 10,  5,-30},
				{-40,-20,  0,  5,  5,  0,-20,-40},
				{-50,-40,-30,-30,-30,-30,-40,-50}				
		};
	
	private static int[][] bishopBlack=
		{
				{-20,-10,-10,-10,-10,-10,-10,-20},
				{-10,  0,  0,  0,  0,  0,  0,-10},
				{-10,  0,  5, 10, 10,  5,  0,-10},
				{-10,  5,  5, 10, 10,  5,  5,-10},
				{-10,  0, 10, 10, 10, 10,  0,-10},
				{-10, 10, 10, 10, 10, 10, 10,-10},
				{-10,  5,  0,  0,  0,  0,  5,-10},
				{-20,-10,-10,-10,-10,-10,-10,-20}
		};
	
	private static int[][] rookBlack= 
		{
				{0,  0,  0,  0,  0,  0,  0,  0},
				{ 5, 10, 10, 10, 10, 10, 10,  5},
				{-5,  0,  0,  0,  0,  0,  0, -5},
				{-5,  0,  0,  0,  0,  0,  0, -5},
				{-5,  0,  0,  0,  0,  0,  0, -5},
				{-5,  0,  0,  0,  0,  0,  0, -5},
				{-5,  0,  0,  0,  0,  0,  0, -5},
				{ 0,  0,  0,  5,  5,  0,  0,  0}
		};
	
	private static int[][] queenBlack=
	{
		{-20,-10,-10, -5, -5,-10,-10,-20},
		{-10,  0,  0,  0,  0,  0,  0,-10},
		{-10,  0,  5,  5,  5,  5,  0,-10},
		{-5,  0,  5,  5,  5,  5,  0, -5},
		{ 0,  0,  5,  5,  5,  5,  0, -5},
		{-10,  5,  5,  5,  5,  5,  0,-10},
		{-10,  0,  5,  0,  0,  0,  0,-10},
		{-20,-10,-10, -5, -5,-10,-10,-20}
	};
	
	private static int[][] kingBlack=
		{
				{-30,-40,-40,-50,-50,-40,-40,-30},
				{-30,-40,-40,-50,-50,-40,-40,-30},
				{-30,-40,-40,-50,-50,-40,-40,-30},
				{-30,-40,-40,-50,-50,-40,-40,-30},
				{-20,-30,-30,-40,-40,-30,-30,-20},
				{-10,-20,-20,-20,-20,-20,-20,-10},
				{20, 20,  0,  0,  0,  0, 20, 20},
				{ 20, 30, 10,  0,  0, 10, 30, 20}
		};
	
	
	// giá trị thế cờ cho quân cờ trắng.
	
	private static int [][] pawnWhite=
		{
			{0,  0,  0,  0,  0,  0,  0,  0},
			{5, 10, 10,-20,-20, 10, 10,  5},
			{ 5, -5,-10,  0,  0,-10, -5,  5},
			{ 0,  0,  0, 20, 20,  0,  0,  0},
			{5,  5, 10, 25, 25, 10,  5,  5},
			{10, 10, 20, 30, 30, 20, 10, 10},
			{50, 50, 50, 50, 50, 50, 50, 50},
			{0,  0,  0,  0,  0,  0,  0,  0}
		};
	
	private static int [][] knightWhite=
		{
			{-50,-40,-30,-30,-30,-30,-40,-50},
			{-40,-20,  0,  5,  5,  0,-20,-40},
			{-30,  5, 10, 15, 15, 10,  5,-30},
			{-30,  0, 15, 20, 20, 15,  0,-30},
			{-30,  5, 15, 20, 20, 15,  5,-30},
			{-30,  0, 10, 15, 15, 10,  0,-30},
			{-40,-20,  0,  0,  0,  0,-20,-40},
			{-50,-40,-30,-30,-30,-30,-40,-50}
		};
	
	private static int[][]  bishopWhite=
		{
			{-20,-10,-10,-10,-10,-10,-10,-20},
			{-10,  5,  0,  0,  0,  0,  5,-10},
			{-10, 10, 10, 10, 10, 10, 10,-10},
			{-10,  0, 10, 10, 10, 10,  0,-10},
			{-10,  5,  5, 10, 10,  5,  5,-10},
			{-10,  0,  5, 10, 10,  5,  0,-10},
			{-10,  0,  0,  0,  0,  0,  0,-10},
			{-20,-10,-10,-10,-10,-10,-10,-20}
		};
	
	private static int[][] rookWhite=
		{
			{ 0,  0,  0,  5,  5,  0,  0,  0},
			{-5,  0,  0,  0,  0,  0,  0, -5},
			{-5,  0,  0,  0,  0,  0,  0, -5},
			{-5,  0,  0,  0,  0,  0,  0, -5},
			{-5,  0,  0,  0,  0,  0,  0, -5},
			{-5,  0,  0,  0,  0,  0,  0, -5},
			{ 5, 10, 10, 10, 10, 10, 10,  5},
			{0,  0,  0,  0,  0,  0,  0,  0}
		};
	
	private static int[][] queenWhite=
		{
			{-20,-10,-10, -5, -5,-10,-10,-20},
			{-10,  0,  5,  0,  0,  0,  0,-10},
			{-10,  5,  5,  5,  5,  5,  0,-10},
			{ 0,  0,  5,  5,  5,  5,  0, -5},
			{-5,  0,  5,  5,  5,  5,  0, -5},
			{-10,  0,  5,  5,  5,  5,  0,-10},
			{-10,  0,  0,  0,  0,  0,  0,-10},
			{-20,-10,-10, -5, -5,-10,-10,-20}
		};
	
	private static int[][] kingWhite=
		{
			{ 20, 30, 10,  0,  0, 10, 30, 20},
			{20, 20,  0,  0,  0,  0, 20, 20},
			{-10,-20,-20,-20,-20,-20,-20,-10},
			{-20,-30,-30,-40,-40,-30,-30,-20},
			{-30,-40,-40,-50,-50,-40,-40,-30},
			{-30,-40,-40,-50,-50,-40,-40,-30},
			{-30,-40,-40,-50,-50,-40,-40,-30},
			{-30,-40,-40,-50,-50,-40,-40,-30}
		};

    public static int[][] getPawnBlack() {
        return pawnBlack;
    }

    public static int[][] getKnightBlack() {
        return knightBlack;
    }

    public static int[][] getBishopBlack() {
        return bishopBlack;
    }

    public static int[][] getRookBlack() {
        return rookBlack;
    }

    public static int[][] getQueenBlack() {
        return queenBlack;
    }

    public static int[][] getKingBlack() {
        return kingBlack;
    }

    public static int[][] getPawnWhite() {
        return pawnWhite;
    }

    public static int[][] getKnightWhite() {
        return knightWhite;
    }

    public static int[][] getBishopWhite() {
        return bishopWhite;
    }

    public static int[][] getRookWhite() {
        return rookWhite;
    }

    public static int[][] getQueenWhite() {
        return queenWhite;
    }

    public static int[][] getKingWhite() {
        return kingWhite;
    }
	
        
	//..................... hàm getter quân Đen
	public static int getPawnBlack(int i, int j)
	{
		return pawnBlack[i][j];
	}
	
	public static int getKnightBlack(int i, int j)
	{
		return knightBlack[i][j];
	}
	
	public static int getBishopBlack(int i, int j)
	{
		return bishopBlack[i][j];
	}
	
	public static int getRookBlack(int i, int j)
	{
		return rookBlack[i][j];
	}
	
	public static int getQueenBlack(int i, int j)
	{
		return queenBlack[i][j];
	}
	
	public static int getKingBlack(int i,int j)
	{
		return kingBlack[i][j];
	}
		
	//....... Hàm getter quân trắng
	
	public static int getPawnWhite(int i, int j)
	{
		return pawnWhite[i][j];
	}
	
	public static int getKnightWhite(int i,int j)
	{
		return knightWhite[i][j];
	}
	
	public static int getBishopWhite(int i,int j)
	{
		return bishopWhite[i][j];
	}
	
	public static int getRookWhite(int i,int j)
	{
		return rookWhite[i][j];
	}
	
	public static int getQueenWhite(int i,int j)
	{
		return queenWhite[i][j];
	}
	
	public static int getKingWhite(int i,int j)
	{
		return kingWhite[i][j];
	}
}
