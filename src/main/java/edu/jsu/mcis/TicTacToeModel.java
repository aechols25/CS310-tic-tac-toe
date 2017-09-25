package edu.jsu.mcis;

public class TicTacToeModel{
    
    private static final int DEFAULT_WIDTH = 3;
    
    /* Mark (represents X, O, or an empty square */
    
    public enum Mark {
        
        X("X"), 
        O("O"), 
        EMPTY("-");

        private String message;
        
        private Mark(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* Result (represents the final state of the game: X wins, O wins, a tie,
       or NONE if the game is not yet over) */
    
    public enum Result {
        
        X("X"), 
        O("O"), 
        TIE("Tie"), 
        NONE("none");

        private String message;
        
        private Result(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    private Mark[][] grid; /* Game grid */
    private boolean xTurn; /* True if X is current player */
    private int width;     /* Size of game grid */
    
    /* DEFAULT CONSTRUCTOR */
    
    public TicTacToeModel() {
        
        /* No arguments (call main constructor; use default size) */
        
        this(DEFAULT_WIDTH);
        
    }
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel(int width) {
        
        /* Initialize width; X goes first */
        
        this.width = width;
        xTurn = true;
        
        /* Create grid (width x width) as a 2D Mark array */

        grid = new Mark [width][width];
        /* Initialize grid by filling every square with empty marks */

        for (int i = 0; i < grid.length; i++ ){
			for(int j = 0; j < grid[i].length; j++){
				grid[i][j] = Mark.EMPTY;
			}
        }
    }
	
    public boolean makeMark(int row, int col) {
        
        /* Place the current player's mark in the square at the specified
           location, but only if the location is valid and if the square is
           empty! */
        
		if(xTurn && isValidSquare(row,col) && !isSquareMarked(row,col)){
			grid[row][col] = Mark.X;
				xTurn = false;
				return true;
		}	
		
		else if(!xTurn && isValidSquare(row,col) && !isSquareMarked(row,col)){
			
			grid[row][col] = Mark.O;
				xTurn = true;
				return true;
		}
		
		else
			return false;
        
    }
	
    private boolean isValidSquare(int row, int col) {
        
        /* Return true if specified location is within grid bounds */
        
        if ((row >= 0)&&(row < width)){
			if ((col >= 0)&&(col < width)){

					return true;
			}
		}	
		
		return false; /* remove this line! */
    }
	
    private boolean isSquareMarked(int row, int col) {
        
        /* Return true if square at specified location is marked */
        
        if(grid[row][col] == Mark.EMPTY){

        return false; /* remove this line! */
        }

		else
			return true;
    }
	
    public Mark getMark(int row, int col) {
        
        /* Return mark from the square at the specified location */
        return grid[row][col];

            
    }
	
    public Result getResult() {
        
        /* Use isMarkWin() to see if X or O is the winner, if the game is a
           tie, or if the game is not over, and return the corresponding Result
           value */
        
        if (isMarkWin(Mark.X)){
			return Result.X;
		}
		else if (isMarkWin(Mark.O)){
			return Result.O;
		}
		else if(isTie()){
			return Result.TIE;
		}
		else 
			return Result.NONE; /* remove this line! */
    }
	
    private boolean isMarkWin(Mark mark) {
        
        /* Check the squares of the board to see if the specified mark is the
           winner */
        
        boolean win = true;
		
		//vertical
		for (int i = 0; i < width; ++i){
			win = true;
			for(int j = 0; j < width; ++j){
				if (!grid[j][i].equals(mark)){
					win = false;
				}
			}
			if(win){
				return true;
			}
			
			
		}
		//horizontal
		for (int i = 0; i < width; ++i){
			win = true;
			for(int j = 0; j < width; ++j){
				if (!grid[i][j].equals(mark)){
					win = false;
				}
			}
			if (win){
				return true;
			}
		}
		// Diagonal left to right
		win = true;
		for(int i = 0; i < width; ++i){
			if(!grid[i][i].equals(mark)){
				win = false;
			}
			
		}
		if (win){
			return true;
		}
		
		
		// Diagonal right to left
		win = true;
		for(int i = 0; i < width; ++i){
			if(!grid[i][width-1-i].equals(mark)){
				win = false;
			}
	
		}
		if (win){
			return true;
		}
        return false; /* remove this line! */ //Change to isTie?

    }
	
    private boolean isTie() {
        
        /* Check the squares of the board to see if the game is a tie */

        int count  = 0;
		for (int row = 0; row < width; row++){
			for (int col = 0; col < width; col++){
				if (grid[row][col] != Mark.EMPTY){
					count++;
				}
			}
		}
		if (count == (width*width)){
			return true;
		}
        return false; /* remove this line! */
        
    }

    public boolean isGameover(){
        
        /* Return true if the game is over */
        
        return Result.NONE != getResult();
        
    }

    public boolean isXTurn(){
        
        /* Getter for xTurn */
        
        return xTurn;
        
    }

    public int getWidth(){
        
        /* Getter for width */
        
        return width;
        
    }
    
}