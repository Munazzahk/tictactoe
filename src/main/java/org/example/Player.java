package org.example;

import java.util.Scanner;

public class Player {
    private char symbol;

    //Either x or o
    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    //Checks if move is valid, and if yes, makes the move for player
    public void makeMove(Scanner scanner, Board board) {
        int move;
        do {
            System.out.print("Enter move (1-9): ");
            move = scanner.nextInt() - 1;
        } while (!board.isMoveValid(move));

        board.setMove(move, symbol);
    }
}
