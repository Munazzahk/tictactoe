package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        new Main().play();
    }

    private final Board board;
    private final AI ai;
    private final Player player;

    public Main() {
        board = new Board();
        player = new Player();
        ai = new AI(board);
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        //Asks player to choose 'X' or 'O'
        char playerSymbol;
        do {
            System.out.print("Do you want to be X or O? Enter your choice: ");
            playerSymbol = scanner.next().toUpperCase().charAt(0);
        } while (playerSymbol != 'X' && playerSymbol != 'O');

        //Sets the AI as the opposite
        char aiSymbol = (playerSymbol == 'X') ? 'O' : 'X';
        player.setSymbol(playerSymbol);
        ai.setSymbol(aiSymbol);

        System.out.println("In this round, you are " + playerSymbol);

        //If AI is 'X' - moves first
        if (playerSymbol == 'O') {
            System.out.println("Your opponent will make the first move!");
            ai.makeMove(5);
        } else {
            System.out.println("You can make the first move :)");
        }

        while (true) {
            board.printBoard();

            if (board.hasWinner(playerSymbol)) {
                System.out.println("You win!");
                break;
            }
            if (board.hasWinner(aiSymbol)) {
                System.out.println("AI wins!");
                break;
            }
            if (board.isFull()) {
                System.out.println("It's a tie!");
                break;
            }

            //Players move
            player.makeMove(scanner, board);

            //AI move with depth = 5 -> depth can be changed
            if (!board.hasWinner(playerSymbol) && !board.isFull()) {
                ai.makeMove(5);
            }
        }

        scanner.close();
    }
}