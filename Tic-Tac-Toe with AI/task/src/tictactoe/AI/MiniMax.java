package tictactoe.AI;

import tictactoe.GameField;
import tictactoe.Point;

import java.util.Arrays;

import static tictactoe.Main.gameField;
class MiniMax {
    private char player;
    private static double maxPly;

    public char getPlayer() {
        return player;
    }

    /**
     * MiniMax cannot be instantiated.
     */
    MiniMax(char player) {
        this.player = player;
    }

    /**
     * Execute the algorithm.
     //* @param player        the player that the AI will identify as
     //* @param board         the Tic Tac Toe board to play on
     * @param maxPly        the maximum depth
     */
    static void run (GameField gameField, char player, double maxPly) {
        if (maxPly < 1) {
            throw new IllegalArgumentException("Maximum depth must be greater than 0.");
        }

        MiniMax.maxPly = maxPly;
        miniMax(gameField, player, 0);
    }

    /**
     * The meat of the algorithm.
     //* @param player        the player that the AI will identify as
     //* @param board         the Tic Tac Toe board to play on
     * @param currentPly    the current depth
     * @return              the score of the board
     */
    private static int miniMax (GameField gameField, char player, int currentPly) {
        if (currentPly++ == maxPly || gameField.isFinished()) {
            return score(gameField, player);
        }

        if (gameField.getTurn() == player) {
            return getMax(gameField, player, currentPly);
        } else {
            return getMin(gameField, player, currentPly);
        }

    }

    /**
     * Play the move with the highest score.
     * @param player        the player that the AI will identify as
    // * @param board         the Tic Tac Toe board to play on
     * @param currentPly    the current depth
     * @return              the score of the board
     */
    private static int getMax (GameField gameField, char player, int currentPly) {
        double bestScore = Double.NEGATIVE_INFINITY;
        Point bestMovePoint = null;

        for (Point point : gameField.getAvailableMoves()) {

            GameField modField = gameField.getDeepCopy();
            modField.setCell(point.getX(), point.getY());

            int score = miniMax(modField, player, currentPly);

            if (score >= bestScore) {
                bestScore = score;
                bestMovePoint = point;
            }

        }

        gameField.setCell(bestMovePoint.getX(), bestMovePoint.getY());
        return (int)bestScore;
    }

    /**
     * Play the move with the lowest score.
     * @param player        the player that the AI will identify as
     //* @param board         the Tic Tac Toe board to play on
     * @param currentPly    the current depth
     * @return              the score of the board
     */
    private static int getMin (GameField gameField, char player, int currentPly) {
        double bestScore = Double.POSITIVE_INFINITY;
        Point bestMovePoint = null;

        for (Point point : gameField.getAvailableMoves()) {

            GameField modField = gameField.getDeepCopy();
            modField.setCell(point.getX(), point.getY());

            int score = miniMax(modField, player, currentPly);

            if (score <= bestScore) {
                bestScore = score;
                bestMovePoint = point;
            }

        }

        gameField.setCell(bestMovePoint.getX(), bestMovePoint.getY());
        return (int)bestScore;
    }

    /**
     * Get the score of the board.
     //* @param player        the play that the AI will identify as
     //* @param board         the Tic Tac Toe board to play on
     * @return              the score of the board
     */
    private static int score (GameField gameField, char player) {
        /*if (player == Board.State.Blank) {
            throw new IllegalArgumentException("Player must be X or O.");
        }*/

        char opponent = (player == 'X') ? 'O' : 'X';

        if (gameField.getState().equals("X wins")) {
            if (opponent == 'X'){
                return -10;
            } else
            return 10;
        } else if (gameField.getState().equals("O wins")) {
            if (opponent == 'O'){
                return -10;
            } else
                return 10;
        } else {
            return 0;
        }
    }


}
