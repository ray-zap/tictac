# tictac
A console tic tac toe game vs the computer in Java. Should be rather hard to beat as I've accounted for a lot. By no means unbeatable though. 


The ttt.java file is where main is and controls switching players. gameboard.java is a class that controls the rules of the gameplay, testing for a winner and data validation, while cyberdyne.java is a class built for controlling the computer's moves and its decision making. This way if you want to create a different computer strategy to play against, you can easily just change where ttt.java directs to in main without affecting the rest of the program. 