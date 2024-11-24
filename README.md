To compile and run this game you can use the following commands:
$ javac *.java 
$ java Othello
In addition to the game board, the main window also shows
• on the top left, the player agents that control the white and black pieces
• on the top right, the current board score
i.e. the difference between the number of white and black pieces
• on the bottom left, a message indicating who’s turn it is and how to proceed with the game

Note that the board score value will be positive when White is winning and negative when Black is winning. By default the Java program allows a human player (always Black) to play against a computer agent that moves randomly (always White). You can change the code in Othello.java to configure these game settings.

The graphical interface of the Java reversi game is not very responsive and a bit basic, to put it mildly. For example:
• The human agent waits for the user to click on a square and then makes that move. There is a 2 seconds delay before non-human agents can move, but the user can also click to request the agent to move.
• Clicking on squares which do not represent legal moves just produces an irritating beep.
• If it is the turn of the human player and there is no legal move, then the user must click anywhere in the green area outside the grid of the board, to generate a skip move and allow play to pass to the other player.
• The interaction between mouse click events from the user and timer delay events that trigger non-human agents is not perfect. . . It’s better not to click while the computer is ‘thinking’
• If you want to play another game, you need to close the window and re-run the program.


