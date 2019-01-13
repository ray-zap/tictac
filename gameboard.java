/**
 class for the gameboard and it's components 
 */


public class gameboard{
    private boolean gameover;
    private int moves;
    private char[] grid;
    private Statscollection monitor = new Statscollection();

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
        monitor.reset();
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
            // monitor.check();
            if (gameover){
                System.out.println("amazing! the " + monitor.winner + " has won the game!");
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
    public void setSpace(int place, char lett){
        grid[place] = lett;
        moves++;
        monitor.check();
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
                setSpace(place, 'X');
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
    public int statusofpartsX(int i){
        return monitor.naughts(i);
    }
    public int statusofpartsO(int i){
        return monitor.oughts(i);
    }
    public int statusofpartsA(int i){
        return monitor.available(i);
    }


    //inner classes for handling the status of each of the potential win types, ie rows, cols etc. provides dynamic programming to reduce the number of times the grid is traveresed. especially useful for the api of the computer challenger class.
    //
    class Status{
        public int oughts;
        public int naughts;
        public int availablePosition;

        Status(){
            oughts = 0;
            naughts = 0;
            availablePosition = -1;
        }

    }

    class Statscollection{
        int o = 0;
        int x = 0;
        int pa = -1;
        Status[] s = new Status[8];
        int statposition = 0;
        public String winner = "";

        Statscollection(){
            reset();
        }

        public void reset(){
            for (int i = 0; i < 8; i++){
                s[i] = new Status();
            }
        }

        public void check(){
            statposition = 0;
            across();
            rows();
            columns();
        }

        private void rows(){
            if (!gameover){ // check the rows
                for (int i = 0; i < 9; i++){
                    examineGrid(i);
                    if ((i + 1) % 3 == 0){
                        testWinner();
                        updateCollection();
                        statposition++;
                    }
                }
            }
        }

        private void examineGrid(int i){
            if (grid[i] == 'O'){
                o++;
            }
            else if (grid[i] == 'X'){
                x++;
            }
            else pa = i;
        }

        private void updateCollection(){
            s[statposition].oughts = o;
            s[statposition].naughts = x;
            if (o + x == 3) s[statposition].availablePosition = -1;
            else s[statposition].availablePosition = pa;
            o = 0;
            x = 0;
            //does pa need to be reset as well? what are the consequences if not?
        }

        private void testWinner(){
            if (o == 3){
                gameover = true;
                winner = "computer";
            }
            else if (x == 3){
                gameover = true;
                winner = "human";
            }
        }

        private void columns(){
            if (!gameover){ // check vertically
                int vertical = 0;
                do {
                    for (int i = vertical, count = 1; i < 9; i += 3, count++){
                        examineGrid(i);
                        if (count % 3 == 0){
                            testWinner();
                            updateCollection();
                            statposition++;
                        }
                    }
                    vertical++;
                } while (vertical < 3 && !gameover);
            }
        }

        private void across(){
            if (!gameover){ //check across down
                for (int i = 0; i < 9; i += 4){
                    examineGrid(i);
                }
                testWinner();
                updateCollection();
                statposition++;
            }
            if (!gameover){ //check across up
                for (int i = 2; i < 8; i +=2){
                    examineGrid(i);
                }
                testWinner();
                updateCollection();
                statposition++;
            }
        }

        public int oughts(int stat){
            return s[stat].oughts;
        }

        public int naughts(int stat){
            return s[stat].naughts;
        }

        public int available(int stat){
            return s[stat].availablePosition;
        }
    }
}