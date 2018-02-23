Name: 
Jay Shah

Programming Language used:
Java

Code structure: 
Input to the program maxconnect4.java is an input file input.txt which is an empty integer array of size 6*7. The program can be run in two modes one-move and interactive. For one move mode, only one move is made for both computer and human and the program ends. For interactive mode, human and computer player play the game turn by turn until both have played half of the moves i.e. 21. The logic for the computer to select its best move is developed in AiPlayer.java where the depth specified as parameter during input is used and alpha beta minimax algorithm with depth limited search is implemented. The GameBoard.java program has various functions implemented for the game such as getScore, getCurrentTurn, getPieceCount, etc.

Running the code:
For one move mode, the command for running the game is java maxconnect4 one-move [input_file] [output_file] [depth]
where input_file is the one to which we initialize the game board, output_file is the one where the board state will be stored after computer has played it's move and depth is the number for which the computer thinks about that many steps ahead.
For interactive mode, the command is java maxconnect4 interactive [input_file] [computer-next/human-next] [depth]
where input_file and depth are similar to one move mode and other field decides who will play first

Tournament participation:
I want to participate in the tournament.
