
import java.util.Scanner;

public class cyberdyne{

	private int human; 
	private int cyberneticOrganism;
	private int available;
	boolean madeMove; 

/**
	constructor 
	rather than put the gameboard object through terminator i could put it in through the constructor, and then i wont have to worry at all about tossing it around through perameters. 
*/
	public cyberdyne(){
		// System.out.println("I'm a cybernetic organism."); 
	}
/**
	terminator() 
	serves as the starting point for all of the AI of the computer's game play. 
*/
	public void terminator(gameboard game){
		if (game.getMoves() == 2){
			if (game.getSpace(0) == 'X' || game.getSpace(2) == 'X' || game.getSpace(6) == 'X' || game.getSpace(8) == 'X'){ // testing for cowardly three corner technique
				if (game.positionAvailability("1")){
					game.setSpace(1, 'O');
				}
				else if (game.positionAvailability("3")){
					game.setSpace(3, 'O');
				}
				else if (game.positionAvailability("5")){
					game.setSpace(5, 'O');
				}
				else if (game.positionAvailability("7")){
					game.setSpace(7, 'O');
				}
				// {game.setSpace(0, 'O');}
			}
			else if (game.positionAvailability("4")){
				game.setSpace(4, 'O');
			}
			else 
				systematic(game);
		}
		else if (game.getMoves() == 4){
			if (wbp(game, 0)){

			}
			else if (wbp(game, 1)){

			}
			else if (game.positionAvailability("4")){
				game.setSpace(4, 'O');
			}
			else 
				systematic(game);
		}
		else 
			systematic(game);
	}
/**
	systematic() event handler 
*/
	private void systematic(gameboard game){
		if (wbp(game, 0)){} 		//try to win the game 
		else if (wbp(game, 1)){}	//try to block the human from winning
		else if (wbp(game, 2)){}	//plot a winning move 
		else 
			wbp(game, 3);			//random spot picker 
	}
/**
	winBlockPlan() event handler 
	these functions are for finding a potential immediate win of the computer, or blocking an impededing win of the human. 
*/
	// private boolean winBlockPlan(gameboard game, int type){
	// 	if (xDown(game, type)){
	// 	}
	// 	else if (xUp(game, type)){
	// 	}
	// 	else if (columI(game, type)){
	// 	}
	// 	else if (columII(game, type)){
	// 	}
	// 	else if (columIII(game, type)){
	// 	}
	// 	else if (rowI(game, type)){
	// 	}
	// 	else if (rowII(game, type)){
	// 	}
	// 	else if (rowIII(game, type)){
	// 	}
	// 	return madeMove;
	// }

/*	for this to work you're going to have to change the available test in finalfrontier regarding available, 
 * since i'm relying on the gameboard interpretation of the flag which is -1, instead of 10 as was written in here. */

	private boolean wbp(gameboard game, int type){
		madeMove = false; 
		for (int i = 0; i < 8; i++){
			available = game.statusofpartsA(i); 
			if (available != -1){
				cyberneticOrganism = game.statusofpartsO(i);
				human = game.statusofpartsX(i);
				finalFrontier(game, type); 
				if (madeMove) return madeMove; 
			}
		}
		return madeMove; 
	}
	/**
	xDown()
	*/
	// private boolean xDown(gameboard game, int type){
	// 	human = 0; 
	// 	cyberneticOrganism = 0; 
	// 	available = 10; 
	// 	madeMove = false; 
	// 	if (type == 2){
	// 		// available = 10;
	// 		for (int i = 0; i < 9; i += 8){
	// 			if (game.getSpace(i) == (char)i){
	// 				available = i;
	// 			}
	// 		}
	// 	}
	// 	else {
	// 		for (int i = 0; i < 9; i += 4){
	// 			if (game.getSpace(i) == 'X'){
	// 				human++; 
	// 			}
	// 			else if (game.getSpace(i) == 'O'){
	// 				cyberneticOrganism++; 
	// 			}
	// 			else 
	// 				available = i;
	// 		}
	// 	}
	// 	finalFrontier(game, type); 
	// 	return madeMove; 
	// }

/**
  finalFrontier() event handler 
  maybe illogical cohesion here 
  
  plus, every routine leading up to here for type 2 explicitly leaves cyberorg and human at 0 seemingly on purpose, 
  yet case 2 clearly needs those variables checked for it run. so case 2 never worked as long as i had this stupid 
  program. i don't remember why i did that, maybe case 2 was ruining something. 
*/
	private void finalFrontier(gameboard game, int type){
		switch (type){
			case 0: //try to win the game, 
				if (cyberneticOrganism == 2 && available < 9){ // win the game. 
					game.setSpace(available, 'O');
					madeMove = true;
				}
			break;
			case 1: //block human from winning.
				if (human == 2 && available < 9){ //block the human from winning
					game.setSpace(available, 'O'); 
					madeMove = true;
					// System.out.println("get to the choppa!");
				}			
			break;
			case 2: //there are no immediate threats of or potential wins, so plan ahead
				if (cyberneticOrganism == 1 && available < 9 && human == 0){
					game.setSpace(available, 'O');
					madeMove = true;
				}
			break;
			case 3: //there is no way to win the game, so just pick a spot. 
				if (available < 9){
					game.setSpace(available, 'O');
					madeMove = true;
				}
			break;
		}

	}
}

/**
	xUp()
*/
// 	/*/*/*private boolean xUp(gameboard game, int type){
// 		human = 0; 
// 		cyberneticOrganism = 0; 
// 		available = 10; 
// 		madeMove = false; 
// 		if (type == 2){
// 			for (int i = 0; i < 7; i += 4){
// 				if (game.getSpace(i) == (char)i){
// 					available = i;
// 				}
// 			}
// 		}
// 		else {
// 			for (int i = 2; i < 7; i += 2){
// 				if (game.getSpace(i) == 'X'){
// 					human++; 
// 				}
// 				else if (game.getSpace(i) == 'O'){
// 					cyberneticOrganism++; 
// 				}
// 				else 
// 					available = i;
// 			}
// 		}
// 		finalFrontier(game, type); 
// 		return madeMove; 
// 	}

// /**
// 	columsI
// */
// 	private boolean columI(gameboard game, int type){
// 		human = 0; 
// 		cyberneticOrganism = 0; 
// 		available = 10; 
// 		madeMove = false; 

// 		if (type == 2){
// 			for (int i = 0; i < 7; i += 3){
// 				if (game.getSpace(i) == (char)i){
// 					available = i;
// 				}
// 			}
// 		}
// 		else {
// 			for (int i = 0; i < 7; i += 3){
// 				if (game.getSpace(i) == 'X'){
// 					human++; 
// 				}
// 				else if (game.getSpace(i) == 'O'){
// 					cyberneticOrganism++; 
// 				}
// 				else 
// 					available = i;
// 			}
// 		}
// 		finalFrontier(game, type); 
// 		return madeMove; 
// 	}

// 	/**
// 		columsII
// 	*/
// 	private boolean columII(gameboard game, int type){
// 		human = 0; 
// 		cyberneticOrganism = 0; 
// 		available = 10; 
// 		madeMove = false; 

// 		if (type == 2){
// 			for (int i = 1; i < 8; i += 3){
// 				if (game.getSpace(i) == (char)i){
// 					available = i;
// 				}
// 			}
// 		}
// 		else {
// 			for (int i = 1; i < 8; i += 3){
// 				if (game.getSpace(i) == 'X'){
// 					human++; 
// 				}
// 				else if (game.getSpace(i) == 'O'){
// 					cyberneticOrganism++; 
// 				}
// 				else 
// 					available = i;
// 			}
// 		}
// 		finalFrontier(game, type); 
// 		return madeMove; 
// 	}

// 	/**
// 		columsIII
// 	*/
// 	private boolean columIII(gameboard game, int type){
// 		human = 0; 
// 		cyberneticOrganism = 0; 
// 		available = 10; 
// 		madeMove = false; 

// 		if (type == 2){
// 			for (int i = 2; i < 9; i += 3){
// 				if (game.getSpace(i) == (char)i){
// 					available = i; 
// 				}
// 			}
// 		}
// 		else {
// 			for (int i = 2; i < 9; i += 3){
// 				if (game.getSpace(i) == 'X'){
// 					human++; 
// 				}
// 				else if (game.getSpace(i) == 'O'){
// 					cyberneticOrganism++; 
// 				}
// 				else 
// 					available = i;
// 			}
// 		}
// 		finalFrontier(game, type); 
// 		return madeMove; 
// 	}

// 	/**
// 		rowI
// 	*/
// 	private boolean rowI(gameboard game, int type){
// 		human = 0; 
// 		cyberneticOrganism = 0; 
// 		available = 10; 
// 		madeMove = false; 

// 		if (type == 2){
// 			for (int i = 0; i < 3; i++){
// 				if (game.getSpace(i) == (char)i){
// 					available = i;
// 				}
// 			}
// 		}
// 		else {
// 			for (int i = 0; i < 3; i++){
// 				if (game.getSpace(i) == 'X'){
// 					human++; 
// 				}
// 				else if (game.getSpace(i) == 'O'){
// 					cyberneticOrganism++; 
// 				}
// 				else 
// 					available = i;
// 			}
// 		}
// 		finalFrontier(game, type); 
// 		return madeMove; 
// 	}

// /**
// 	rowII()
// */
// 	private boolean rowII(gameboard game, int type){
// 		human = 0; 
// 		cyberneticOrganism = 0; 
// 		available = 10; 
// 		madeMove = false; 

// 		if (type == 2){
// 			for (int i = 3; i < 6; i++){
// 				if (game.getSpace(i) == (char)i){
// 					available = i;
// 				}
// 			}
// 		}
// 		else {
// 			for (int i = 3; i < 6; i++){
// 				if (game.getSpace(i) == 'X'){
// 					human++; 
// 				}
// 				else if (game.getSpace(i) == 'O'){
// 					cyberneticOrganism++; 
// 				}
// 				else 
// 					available = i;
// 			}
// 		}
// 		finalFrontier(game, type); 
// 		return madeMove; 
// 	}

// /**
// 	rowIII()
// */
// 	private boolean rowIII(gameboard game, int type){
// 		human = 0; 
// 		cyberneticOrganism = 0; 
// 		available = 10; 
// 		madeMove = false; 

// 		if (type == 2){
// 			for (int i = 6; i < 9; i++){
// 				if (game.getSpace(i) == (char)i){
// 					available = i;
// 				}
// 			}
// 		}
// 		else{
// 			for (int i = 6; i < 9; i++){
// 				if (game.getSpace(i) == 'X'){
// 					human++; 
// 				}
// 				else if (game.getSpace(i) == 'O'){
// 					cyberneticOrganism++; 
// 				}
// 				else 
// 					available = i;
// 			}
// 		}
// 		finalFrontier(game, type); 
// 		return madeMove; 
// 	}
// }*/*/*/