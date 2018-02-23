public class AiPlayer {

	public int depthLevel;
	public int choice = 100;
	public int alpha = -1000;
	public int beta = 1000;

	public AiPlayer(int depthLevel) {
		this.depthLevel = depthLevel;
	}

	public int findBestPlay(GameBoard curr_game) {
		if (curr_game.getCurrentTurn() == 1) // max player
		{
			int v = 1000;
			for (int i = 0; i < 7; i++) {
				if (curr_game.isValidPlay(i)) {
					GameBoard playnext = new GameBoard(curr_game.getGameBoard());
					playnext.playPiece(i);
					int max_value = max(playnext, depthLevel, -1000, 1000);
					if (v > max_value) {
						choice = i;
						v = max_value;
					}
				}
			}
		} else {
			int v = -1000;
			for (int i = 0; i < 7; i++) {
				if (curr_game.isValidPlay(i)) {
					GameBoard playnext = new GameBoard(curr_game.getGameBoard());
					playnext.playPiece(i);
					int min_value = min(playnext, depthLevel, alpha, beta);
					if (v < min_value) {
						choice = i;
						v = min_value;
					}
				}
			}
		}
		return choice;
	}

	public int max(GameBoard game, int depth, int alpha, int beta) {
		if (game.getPieceCount() < 42 && depth != 0) {
			int v = -1000;
			for (int i = 0; i < 7; i++) {
				if (game.isValidPlay(i)) {
					GameBoard max_game = new GameBoard(game.getGameBoard());
					max_game.playPiece(i);
					int min_value = min(max_game, depth - 1, alpha, beta);
					if (v < min_value) {
						v = min_value;
					}
					if (v >= beta) {
						return v;
					}
					if (alpha < v) {
						alpha = v;
					}
				}
			}
			return v;
		} else
			return game.getScore(2) - game.getScore(1);
	}

	public int min(GameBoard game, int depth, int alpha, int beta) {
		if (game.getPieceCount() < 42 && depth != 0) {
			int v = 1000;
			for (int i = 0; i < 7; i++) {
				if (game.isValidPlay(i)) {
					GameBoard min_game = new GameBoard(game.getGameBoard());
					min_game.playPiece(i);
					int max_value = max(min_game, depth - 1, alpha, beta);
					if (v > max_value) {
						v = max_value;
					}
					if (v <= alpha) {
						return v;
					}
					if (beta > v) {
						beta = v;
					}
				}
			}
			return v;
		} else
			return game.getScore(2) - game.getScore(1);
	}
}