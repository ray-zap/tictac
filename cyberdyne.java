
// import java.util.Scanner;

public class cyberdyne{

	private int human; 
	private int cyberneticOrganism;
	private int available;
	boolean madeMove; 
	private gameboard game; 

/**
	constructor
*/
	public cyberdyne(gameboard play){
		game = play; 
		// System.out.println("I'm a cybernetic organism."); 
	}
/**
	terminator() 
	serves as the starting point for all of the AI of the computer's game play.
    012
    345
    678
*/
	public void terminator(){
		if (game.getMoves() == 2){
            int move = 0;
            //human chose a corner
			if (game.getSpace(move = 1) == 'X' || game.getSpace(move = 3) == 'X' || game.getSpace(move = 5) == 'X' || game.getSpace(move = 7) == 'X') {
                switch (move) {
                    case 1:
                        game.setSpace(0, 'O');
                        break;
                    case 3:
                        game.setSpace(0, 'O');
                        break;
                    case 5:
                        game.setSpace(8, 'O');
                        break;
                    case 7:
                        game.setSpace(8, 'O');
                        break;
                }
            }
			//human chose an edge
			else if (game.getSpace(0) == 'X' || game.getSpace(2) == 'X' || game.getSpace(6) == 'X' || game.getSpace(8) == 'X'){
                    game.setSpace(4, 'O');
			}
            //human chose the center
            else game.setSpace(0, 'O');
		}

		else systematic();
	}
/**
	systematic() event handler 
*/
	private void systematic(){
		if (wbp(0)){} 		//try to win the game 
		else if (wbp(1)){}	//try to block the human from winning
		else if (wbp(2)){}	//plot a winning move 
		else 
			wbp(3);			//random spot picker 
	}


/**
winblockplan wbp
picks up the data regarding each winning move (a winning move being three positions on the board across
 up and down, each row and column's three positions.) from the gameboard class' API and sends that to be tested in
 finalfrontier if there's an available position in the cross, row or column.
 */

	private boolean wbp(int type){
		madeMove = false; 
		for (int i = 0; i < 8; i++){
			available = game.statusofpartsA(i); 
			if (available != -1){
				cyberneticOrganism = game.statusofpartsO(i);
				human = game.statusofpartsX(i);
				finalFrontier(type); 
				if (madeMove) return madeMove; 
			}
		}
		return madeMove; 
	}


/**
  finalFrontier() event handler 
  maybe illogical cohesion here
*/
	private void finalFrontier(int type){
		switch (type){
			case 0: //try to win the game, 
				if (cyberneticOrganism == 2 ){ // win the game. 
					game.setSpace(available, 'O');
					madeMove = true;
				}
			break;
			case 1: //block human from winning.
				if (human == 2 ){ //block the human from winning
					game.setSpace(available, 'O'); 
					madeMove = true;
					// System.out.println("get to the choppa!");
				}			
			break;
			case 2: //there are no immediate threats of or potential wins, so plan ahead
				if (cyberneticOrganism == 1 && human == 0){
					game.setSpace(available, 'O');
					madeMove = true;
				}
			break;
			case 3: //there is no way to win the game, so just pick a spot. 
				// if (available < 9){
					game.setSpace(available, 'O');
					madeMove = true;
				// }
			break;
		}
	}
}
