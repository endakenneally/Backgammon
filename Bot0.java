
public class Bot0 implements BotAPI {

    // The public API of Bot must not change
    // This is ONLY class that you can edit in the program
    // Rename Bot to the name of your team. Use camel case.
    // Bot may not alter the state of the game objects
    // It may only inspect the state of the board and the player objects

	public static final int BAR = 25;           // index of the BAR
    public static final int BEAR_OFF = 0;       // index of the BEAR OFF
	
    private PlayerAPI me, opponent;
    private BoardAPI board;
    private CubeAPI cube;
    private MatchAPI match;
    private InfoPanelAPI info;
    int[][] possibleBoard;

    Bot0(PlayerAPI me, PlayerAPI opponent, BoardAPI board, CubeAPI cube, MatchAPI match, InfoPanelAPI info) {
        this.me = me;
        this.opponent = opponent;
        this.board = board;
        this.cube = cube;
        this.match = match;
        this.info = info;
    }
    
    public String getName() {
        return "Bot0"; // must match the class name
    }

    public String getCommand(Plays possiblePlays) {
        // Add your code here
    
    	// CREATE AN INT ARRAY WITH SIZE OF HOW MANY AVAILABLE MOVES
    	
    	System.out.println("----------------");
    	
    	for(int i = 0 ; i < possiblePlays.number();i++)
    	{
    		int[][] possibleBoard = board.get();
    		possibleBoard = applyCurrentMove(possiblePlays,i);
    		//blockBlotDifference(possibleBoard);
    		//yourAnchors(possibleBoard);
    		System.out.println(longestPrime(possibleBoard));
    	}
    	
    	// FOR LOOP TO CALCULATE THE WEIGHTS FOR EACH DIFFERENT CHARACTERISTIC
    	int[][] possibleBoard = board.get();
    	
    	// applycurrentmove to possible board
    	
    	// calculate all the weights
    	
    	calculatePipDifference(possibleBoard);
    	
    	// Add them up
    	
    	// Output the biggest
    	
        return "1";
    }

    /*
     *  Simple Prime Function
     */
    public int longestPrime(int[][] possibleBoard)
    {
    	int longestPrime = 0;
    	int length = 0;
    	
    	/*
    	for(int i = 1; i <= 24; i++)
    	{
    		int currentLongest = 0;
    		
    		for(int j = i; j <= 24; j++)
    		{
    			if(possibleBoard[me.getId()][j] > 0)
    			{
    				currentLongest++;
    			}
    			else break;
    		}
    		
    		if(currentLongest > longestPrime)
    		{
    			longestPrime = currentLongest;
    		}
    	}
    	*/
    	for(int i = 1; i <= 24; i++)
    	{
    		
    		if(possibleBoard[me.getId()][i] > 0)
    		{
    			length++;
    		}
    		else
    		{
    			if(length > longestPrime)
    			{
    				longestPrime = length;
    			}
    			
    		}
    	}
    	
    	
    	return 0;
    }
    	
    public String getDoubleDecision() {
        // Add your code here
        return "n";
    }
    
    public int[][] move(int[][] possibleBoard, Move move) {
        possibleBoard[me.getId()][move.getFromPip()]--;
        possibleBoard[me.getId()][move.getToPip()]++;
       
        
        if (move.getToPip()<BAR && move.getToPip()>BEAR_OFF &&
                possibleBoard[opponent.getId()][calculateOpposingPip(move.getToPip())] == 1) {
            possibleBoard[opponent.getId()][calculateOpposingPip(move.getToPip())]--;
            possibleBoard[opponent.getId()][BAR]++;
        }
        
        return possibleBoard;
    }

    public int[][] move(int[][] possibleBoard, Play play) {
        for (Move move : play) {
            move(possibleBoard,move);
        }
        
        return possibleBoard;
    }
    
    private int calculateOpposingPip(int pip)
    {
    	return 24-pip+1;
    }
    
    public int[][] applyCurrentMove(Plays possiblePlays, int input)
    {
    	int[][] possibleBoard = board.get();
    	possibleBoard = move(possibleBoard,possiblePlays.get(input)); // CHANGE AND ADD INPUT

    	return possibleBoard;
    }
    
    
    public int calculateValue(){
    	int result = 0;
    	
    	return result;
    }
    
    /*
     *  Calculates the pip difference of the board after the move has taken place
     */
    
    public int calculatePipDifference(int[][] afterBoard) {
    	
    	int playerOneTotal = 0, playerTwoTotal = 0;
    	
    	// Calculate the pip value for player 0
    	for(int i = 0; i <= 25; i++)
    	{
    		if(!(i==0) || !(i==25))
    		{
    			playerOneTotal += i*(afterBoard[0][i]);
    		}
    	}
    	
    	// Calculate the pip value for player 1
    	for(int i = 0; i <= 25; i++)
    	{
    		if(!(i==0) || !(i==25))
    		{
    			playerTwoTotal += i*(afterBoard[1][i]);
    		}
    	}
    	
    	/*
    	System.out.println("Player One: " + playerOneTotal);
    	System.out.println("Player Two: " + playerTwoTotal);
    	*/
    	
    	//System.out.println(playerOneTotal-playerTwoTotal);
    	return playerOneTotal-playerTwoTotal;
    }
    
    public int calculatePipDifference(Board beforeBoard) {
    	
    	int[][] before = beforeBoard.get();
        int playerOneTotal = 0, playerTwoTotal = 0;
    	
    	// Calculate the pip value for player 0
    	for(int i = 0; i <= 25; i++)
    	{
    		if(!(i==0) || !(i==25))
    		{
    			playerOneTotal += i*(before[0][i]);
    		}
    	}
    	
    	// Calculate the pip value for player 1
    	for(int i = 0; i <= 25; i++)
    	{
    		if(!(i==0) || !(i==25))
    		{
    			playerTwoTotal += i*(before[1][i]);
    		}
    	}
    	
    	/*
    	System.out.println("Player One: " + playerOneTotal);
    	System.out.println("Player Two: " + playerTwoTotal);
    	System.out.println(playerOneTotal-playerTwoTotal);
    	*/
    	return playerOneTotal-playerTwoTotal;
    
    }
    
    /*
     * Gets the number of Anchors
     */
    public int yourAnchors(int[][] afterBoard)
    {
    	int anchortotal = 0;
    	
    	for(int i = 18; i <=24; i++)
    	{
    		if(afterBoard[me.getId()][i] > 1)
    		{
    			anchortotal += afterBoard[me.getId()][i];
    		}
    	}
    	
    	System.out.println("your anchors: " + anchortotal);
    	return anchortotal;
    }
    
    public int theirAnchors(int[][] afterBoard)
    {
    	int anchortotal = 0;
    	
    	for(int i = 18; i <=24; i++)
    	{
    		if(afterBoard[me.getId()][i] > 1)
    		{
    			anchortotal += afterBoard[opponent.getId()][i];
    		}
    	}
    	
    	return anchortotal;
    }
   
    // Your block to their blot 
    public int blockBlotDifference(int[][] afterBoard)
    {
    	int blockTotal = 0;
        int blotTotal = 0;
        int result;
    	
        // Calculate your blocks
        for(int i = 1; i <= 24; i++ ) {
        	
        	if(afterBoard[me.getId()][i] >= 2) 
    		{
    			blockTotal++;
    		}
        }
        
        // Calculate their blots
        for(int i = 1; i <= 24; i++ ) {
        	
        	if(afterBoard[opponent.getId()][i] == 1) 
    		{
    			blotTotal++;
    		}
        }
        
        result = blockTotal - blotTotal;
        
        System.out.println("bb : " + result);
    	return result;
    }
    
}
