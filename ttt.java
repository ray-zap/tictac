/**
Raymond Zapata
Final assignment 
*/

import java.util.Scanner;

public class finalRZ {
	public static void main(String[] args){
		splash();
		vsComputer();
		System.out.println("Thank you for a very enjoyable game.");
	}

/**
	splash();
*/
	public static void splash(){
		String format = "";
		System.out.print("tick tack tow game extraordinaire \n");
		System.out.printf(format, "");
		String about = "";
	}
/**
	vsComputer()
*/
	public static void vsComputer(){
		Scanner keyboard = new Scanner(System.in);
		String stuff ="";
		gameboard game = new gameboard();
		cyberdyne skynet = new cyberdyne();

		while (!game.isGameOver()){
			if (game.itsaTie()){
				game.displayGrid();
				System.out.println("It's a tie! ");
			}
			else {
				switchPlayers(game, skynet);
				clear();
				game.checkWinner();
			}

			if (game.isGameOver()){
				System.out.println("Play again? y/Y or any other key to quit.");
				// get string nextline
				stuff = keyboard.nextLine();
				// validate - where?
				if (stuff.length() == 1){
					if (stuff.charAt(0) == 'y' || stuff.charAt(0) == 'Y'){
						game.reset();
					}					
				}
			}
		}
	}

	/**
	switchPlayers()
	*/
	public static void switchPlayers(gameboard game, cyberdyne skynet){
		if (game.getMoves() % 2 == 0){
			skynet.terminator(game);
		}
		else {
			futileHumanMove(game);
		}
	}

	/**
	futileHumanMove()
	*/
	public static void futileHumanMove(gameboard game){
		Scanner keyboard = new Scanner(System.in);
		clear();
		game.displayGrid();
		System.out.println("Human, make your move.");
		String input = keyboard.nextLine();
		// validate 
		while (!game.validateEntry(input)){
			System.out.println("HUMAN ERROR");
			input = keyboard.nextLine();
		}
	}

	/**
	clear() clear the screen hopefully 
	*/
	public static void clear(){
		// System.out.print("\033[H\033[2J");  // ancsi escape codes
		// System.out.flush(); 
		// Runtime.getRuntime().exec("cls"); 
/*		for(int i = 0; i < 100; i++) {
		    System.out.println("\b") ;
		}
*/	
		// System.out.print("\f");
	}
}