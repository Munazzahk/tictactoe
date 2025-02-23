package org.example;

public class AI {
    private final Board board;
    private char aiSymbol, playerSymbol;
    private final int[] fieldValues = {3, 2, 3, 2, 4, 2, 3, 2, 3};

    public AI(Board board) {
        this.board = board;
    }

    //The opposite of what the player is
    public void setSymbol(char symbol) {
        this.aiSymbol = symbol;
        this.playerSymbol = (symbol == 'X') ? 'O' : 'X';
    }

    //Makes the best move with the chosen depth
    public void makeMove(int depth) {
        int bestScore = Integer.MIN_VALUE; //Stores highest score found
        int bestMove = -1;

        for (int i = 0; i < 9; i++) {
            if (board.isMoveValid(i)) {
                board.setMove(i, aiSymbol); //Temporarily place AI move
                //Evaluates move
                //AI has min_value to begin with so it will always try to find better
                int score = minmax(depth, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
                board.setMove(i, ' '); //Undo move as it is a simulation

                //Keeps track of best move
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
            }
        }
        board.setMove(bestMove, aiSymbol);
    }

    //MinMax algorithm with alpha beta pruning - simulates possible future moves to choose best move for AI
    private int minmax(int depth, boolean isMaximizing, int alpha, int beta) {
        if (board.hasWinner(aiSymbol)) return 10;
        if (board.hasWinner(playerSymbol)) return -10;
        if (board.isFull() || depth == 0) return evaluateBoard();

        int best;

        if (isMaximizing) { //AI turn - maximize score
            best = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board.isMoveValid(i)) {
                    board.setMove(i, aiSymbol);
                    best = Math.max(best, minmax(depth - 1, false, alpha, beta));
                    board.setMove(i, ' ');
                    alpha = Math.max(alpha, best); //Best of maximizing player
                    if (beta <= alpha) break; //Pruning - cuts off branches not needed
                }
            }
        } else { //Players turn - minimize AI score
            best = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board.isMoveValid(i)) {
                    board.setMove(i, playerSymbol);
                    best = Math.min(best, minmax(depth - 1, true, alpha, beta));
                    board.setMove(i, ' ');
                    beta = Math.min(beta, best); //Best of minimizing player
                    if (beta <= alpha) break; //Pruning - cuts off branches not needed
                }
            }
        }
        return best;
    }

    //Assigns the score when depth limit is reached - uses field values for strategic move
    private int evaluateBoard() {
        int score = 0;
        for (int i = 0; i < 9; i++) {
            if (board.getMove(i) == aiSymbol) score += fieldValues[i];
            if (board.getMove(i) == playerSymbol) score -= fieldValues[i];
        }
        return score;
    }
}
