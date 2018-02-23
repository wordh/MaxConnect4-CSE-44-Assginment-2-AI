import java.util.*;

public class maxconnect4 {

	public static GameBoard currentGame;
	public static AiPlayer calculon;
	public static int playColumn = 99;
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		if (args.length != 4) {
			System.out.println("Four command-line arguments are needed:\n"
					+ "Usage: java [program name] interactive [input_file] [computer-next / human-next] [depth]\n"
					+ " or:  java [program name] one-move [input_file] [output_file] [depth]\n");

			exit_function(0);
		}
		// parse the input arguments
		String game_mode = args[0].toString(); // the game mode
		String input = args[1].toString(); // the
																									// input
																									// game
																									// file
		String next_move = args[2].toString(); // decide whether human will move
												// next or computer
		int depthLevel = Integer.parseInt(args[3]); // the depth level of the ai
													// search

		// create and initialize the game board
		currentGame = new GameBoard(input);

		// create the Ai Player
		calculon = new AiPlayer(depthLevel);

		// variables to keep up with the game
		// the players choice of column to play
		// boolean playMade = false; // set to true once a play has been made

		if (game_mode.equalsIgnoreCase("interactive")) {
			if (!next_move.equalsIgnoreCase("computer-next") && !next_move.equalsIgnoreCase("human-next")) {
				System.out.println("Select a valid interactive mode \n");
				return;
			}
			if (next_move.equalsIgnoreCase("computer-next")) {
				currentGame.setNext_chance("C"); // C for Computer
				ComputerTurn();
			} else if (next_move.equalsIgnoreCase("human-next")) {
				currentGame.setNext_chance("H"); // H for Human
				HumanTurn();
			}
		}

		else if (!game_mode.equalsIgnoreCase("one-move")) {
			System.out.println("\n" + game_mode + " is an unrecognized game mode \n try again. \n");
			return;
		}

		///////////// one-move mode ///////////
		// get the output file name
		String output = args[2].toString(); // the output game file

		System.out.print("\nMaxConnect-4 game\n");
		System.out.print("game state before move:\n");

		// print the current game board
		currentGame.printGameBoard();
		// print the current scores
		System.out.println(
				"Score: Player 1 = " + currentGame.getScore(1) + ", Player2 = " + currentGame.getScore(2) + "\n ");

		// ****************** this chunk of code makes the computer play
		if (currentGame.getPieceCount() < 42) {
			int current_player = currentGame.getCurrentTurn();
			// AI play - random play
			playColumn = calculon.findBestPlay(currentGame);

			// play the piece
			currentGame.playPiece(playColumn);

			// display the current game board
			System.out.println(
					"move " + currentGame.getPieceCount() + ": Player " + current_player + ", column " + playColumn);
			System.out.print("game state after move:\n");
			currentGame.printGameBoard();

			// print the current scores
			System.out.println(
					"Score: Player 1 = " + currentGame.getScore(1) + ", Player2 = " + currentGame.getScore(2) + "\n ");

			currentGame.printGameBoardToFile(output);
		} else {
			System.out.println("\nI can't play.\nThe Board is Full\n\nGame Over");
		}

		// ************************** end computer play

		return;

	} // end of main()

	private static void HumanTurn() {
		currentGame.printGameBoard();
		int human_ip = 0;
		if (currentGame.getPieceCount() >= 42) {
			currentGame.printGameBoard();
			System.out.println("Final Result");
			System.out.println(
					"Score: Player 1 = " + currentGame.getScore(1) + ", Player 2 = " + currentGame.getScore(2) + "\n ");
			exit_function(0);
		} else {
			System.out.println(
					"Score: Player 1 = " + currentGame.getScore(1) + ", Player2 = " + currentGame.getScore(2) + "\n ");
			System.out.println("Its Human turn. Please choose the move as column number from 1 to 7");
			do {
				human_ip = sc.nextInt();
			} while (!currentGame.isValidPlay(human_ip - 1));
			currentGame.playPiece(human_ip - 1);
			currentGame.printGameBoardToFile("human.txt");
			ComputerTurn();
		}
	}

	private static void ComputerTurn() {
		currentGame.printGameBoard();
		System.out.println("Computer's turn");
		if (currentGame.getPieceCount() >= 42) {
			currentGame.printGameBoard();
			System.out.println("Final Result");
			System.out.println(
					"Score: Player 1 = " + currentGame.getScore(1) + ", Player 2 = " + currentGame.getScore(2) + "\n ");
		} else {
			playColumn = calculon.findBestPlay(currentGame);
			System.out.println(playColumn + 1);
			currentGame.playPiece(playColumn);
			currentGame.printGameBoardToFile("computer.txt");
			HumanTurn();
		}
	}

	/**
	 * This method is used when to exit the program prematurely.
	 * 
	 * @param value
	 *            an integer that is returned to the system when the program
	 *            exits.
	 */
	private static void exit_function(int value) {
		System.out.println("exiting from MaxConnectFour.java!\n\n");
		System.exit(value);
	}
} // end of class connectFour
