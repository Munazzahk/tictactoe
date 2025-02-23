package org.example;

public class Board {
    private final char[] board;
    private final int[][] winPatterns = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, //Rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, //Columns
            {0, 4, 8}, {2, 4, 6}  //Diagonals
    };

    public Board() {
        board = new char[9];
        for (int i = 0; i < 9; i++) board[i] = ' ';
    }

    //The actual board
    public void printBoard() {
        System.out.println(" " + board[0] + " | " + board[1] + " | " + board[2]);
        System.out.println("---+---+---");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5]);
        System.out.println("---+---+---");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8]);
    }

    //Checks if there is three in a row
    public boolean hasWinner(char player) {
        for (int[] pattern : winPatterns) {
            if (board[pattern[0]] == player &&
                    board[pattern[1]] == player &&
                    board[pattern[2]] == player) {
                return true;
            }
        }
        return false;
    }

    //A tie
    public boolean isFull() {
        for (char cell : board) {
            if (cell == ' ') return false;
        }
        return true;
    }

    //Ensures move is in board, and in a void place
    public boolean isMoveValid(int index) {
        return index >= 0 && index < 9 && board[index] == ' ';
    }

    //Getters and setters
    public void setMove(int index, char symbol) {
        board[index] = symbol;
    }

    public char getMove(int index) {
        return board[index];
    }

    public char[] getBoard() {
        return board.clone();
    }
}
