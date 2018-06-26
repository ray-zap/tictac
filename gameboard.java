/**
 class for the gameboard and it's components 
*/


public class gameboard{
	private boolean gameover; 
	public int moves; 
	char[] grid;

/**
	constructor 
*/
	public gameboard(){
		gameover = false; 
		grid = new char[9];
		for (int i = 0; i < 9; i++){
			grid[i] = (char)(i + '0'); 
		}
		moves = 1; 
	}

/**
	reset function for the array, and probably anything else that would need to be reset. 
*/
	public void reset(){
		for (int i = 0; i < 9; i++){
			grid[i] = (char)(i + '0'); 
		}
		moves = 1;
		gameover = false; 
	}

	/**
	display the current grid state of affairs 
	*/
	public void displayGrid(){
		String torb = "%6c | %1c | %1c\n";
		String mid =  "    ---+---+---";
		System.out.printf(torb, grid[0], grid[1], grid[2]);
		System.out.println(mid);
		System.out.printf(torb, grid[3], grid[4], grid[5]);
		System.out.println(mid);
		System.out.printf(torb, grid[6], grid[7], grid[8]);
	}

/**
checkWinner() 
checks the array to see if anyone has won the game yet. 
*/
	public void checkWinner(){
		if (moves > 4){
			int oughts = 0; 
			int naughts = 0;
			String winO = "the computer has won the game! \nI know now why you cry [wipes a tear from Johns face] but its something that I can never do ";
			String winN = "The human being has won the game!";
			if (!gameover){ // check the rows for a number one winner 
				for (int i = 0; i < 9; i++){
					if (grid[i] == 'O'){
						oughts++;
					}
					else if (grid[i] == 'X'){
						naughts++;
					}
					if ((i + 1) % 3 == 0){
						if (oughts == 3){
							displayGrid();
							System.out.println(winO);
							gameover = true;
							i = 10;
						}
						else if (naughts == 3){
							displayGrid();
							System.out.println(winN);
							gameover = true;
							i = 10;
						}
						else {
							oughts = 0;
							naughts = 0;
						}
					}
				}
			}
			if (!gameover){ // check vertically for the best winner of all 
				int vertical = 0;
				do {
					for (int i = vertical, count = 1; i < 9; i += 3, count++){
						if (grid[i] == 'O'){
							oughts++;
						}
						else if (grid[i] == 'X'){
							naughts++;
						}
						if (count % 3 == 0){
							if (oughts == 3){
								displayGrid();
								System.out.println(winO);
								gameover = true;
								i = 10;
							}
							else if (naughts == 3){
								displayGrid();
								System.out.println(winN);
								gameover = true;
								i = 10;
							}
							else {
								oughts = 0;
								naughts = 0;
							}
						}
					}
					vertical++;	
				} while (vertical < 3 && !gameover);
			}
			if (!gameover){ //check across down 
				for (int i = 0; i < 9; i += 4){
					if (grid[i] == 'O'){
						oughts++;
					}
					else if (grid[i] == 'X'){
						naughts++;
					}
				}
				if (oughts == 3){
					displayGrid();
					System.out.println(winO);
					gameover = true;
				}
				else if (naughts == 3){
					displayGrid();
					System.out.println(winN);
					gameover = true;
				}
				else {
					oughts = 0;
					naughts = 0;
				}
			}
			if (!gameover){ //check across up
				for (int i = 2; i < 8; i +=2){
					if (grid[i] == 'O'){
						oughts++;
					}
					else if (grid[i] == 'X'){
						naughts++;
					}
				}
				if (oughts == 3){
					displayGrid();
					System.out.println(winO);
					gameover = true;
				}
				else if (naughts == 3){
					displayGrid();
					System.out.println(winN);
					gameover = true;
				}
				else {
					oughts = 0;
					naughts = 0;
				}
			}
		}
	}
/**
	isGameOver() is basically a get function getting gameover 
*/
	public boolean isGameOver(){
		return gameover;
	} 
/**
	itsaTie() returns gameover, after adjusting for checking moves 
*/
	public boolean itsaTie(){
		if (moves > 9){
			gameover = true;
		}
		return gameover;
	}

	/**
	::getMoves():: 
	*/
	public int getMoves(){
		return moves;
	}

	/**
	:: getSpace() :: - 
	*/
	public char getSpace(int place){
		return grid[place];
	}

	/**
	setSpace() used primarily for the computer entry. 
	*/
	public void setSpace(int place){
		grid[place] = 'O';
		moves++;
	}

	/**
	validateEntry() validates entry - also sets the array for the position if valid/available for the human moves. 
	*/
	public boolean validateEntry(String input){
		boolean valid = true; 
		int len = input.length();
		if (len != 1 || !Character.isDigit(input.charAt(0))){
			valid = false; 
			System.out.println("enter only one digit.");
		}
		else if (input.charAt(0) < '0' || input.charAt(0) > '8'){
			valid = false; 
			System.out.println("Enter only numbers 0 - 8");
		}

		if (valid){
			valid = positionAvailability(input);
			if (!valid){
				System.out.println("Position is not available.");
			}
			else {
				int place = Integer.parseInt(input);
				grid[place] = 'X'; 
				moves++; 
			}
		}
		return valid; 
	}

	/**
	positionAvailability()
	*/
	public boolean positionAvailability(String input){
		boolean available = false; 
		int place = Integer.parseInt(input); 
		if (grid[place] == input.charAt(0)){
			available = true; 
		}
		return available; 
	}

	/**
	
	*/

}