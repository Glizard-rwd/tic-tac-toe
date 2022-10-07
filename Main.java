package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;
/*
Tic-tac-toe game: OOP -> only use gridBoard as parameter
- game can create its own environment
- game can decide X-player or O-player will play
- game can check input of each player: isNumber, isOccupied, isRange
- game can find who win and display game result (status)
- beginner project so it will be simple.
- we set boolean xTurn (player's X turn) to true, that mean player 1 will go first.
 -- player of this game will include 2 player so we can use static boolean variable (not sure)
- reference code: My code is another version of David Fernando Blanco code. https://hyperskill.org/profile/286130027
His code seems easy to understand for me
 */

public class Main {
    static boolean xTurn = true; // who turn? (only 2 player)
    public static void main(String[] args) {
        String[][] gameBoard = createBoard(); // no parameter
        displayGrid(gameBoard);
        playerInput(gameBoard);
        gameStatus(gameBoard);
    }

    private static void gameStatus(String[][] gameBoard) {
        boolean isNotFinished = true;
        while (isNotFinished) {
            if (checkWin(gameBoard, "X")) {
                System.out.println("X wins"); // display game status
                isNotFinished = false; // end game
            }
            else if (checkWin(gameBoard, "O")) {
                System.out.println("O wins");
                isNotFinished = false; // end game
            }
            else if (isEmpty(gameBoard)) playerInput(gameBoard); // input in empty cell
            else {
                System.out.println("Draw"); // no empty cell but no winner
                isNotFinished = false; // end game :)
            }
        }
    }

    private static boolean isEmpty(String[][] gameBoard) {
        boolean empty = false;
        for (String[] row: gameBoard) {
            for (String cell: row) {
                if (cell.equals(" ")) empty = true;
            }
        }
        return empty;
    }

    private static boolean checkWin(String[][] gB, String p) {
        // row, column, diagonal is == "X" or "O"
        boolean isWin = false;
        for (int i=0; i<gB.length; i++) {
            if ((gB[i][0].equals(p) && gB[i][1].equals(p) && gB[i][2].equals(p)) // check row
                    || (gB[0][i].equals(p) && gB[1][i].equals(p) && gB[2][i].equals(p)) // col
                    || (gB[0][0].equals(p) && gB[1][1].equals(p) && gB[2][2].equals(p)) // diagonal
                    || (gB[0][2].equals(p) && gB[1][1].equals(p) && gB[2][0].equals(p))) {
                isWin = true;
            }
        }
        return isWin;
    }

    private static void playerInput(String[][] gameBoard) {
        // player position
        Scanner playerInput = new Scanner(System.in);
        // repeat from here
        boolean repeat = true;
        while (repeat) {
            // enter position
            try {
                int xBoard = playerInput.nextInt();
                int yBoard = playerInput.nextInt();
                if (xBoard < 1 || xBoard > 3 || yBoard < 1 || yBoard > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (gameBoard[xBoard-1][yBoard-1].equals(" ")) {
                    if (xTurn) {
                        gameBoard[xBoard-1][yBoard-1] = "X"; // fill "X" on board
                        displayGrid(gameBoard);
                        repeat = false; // break loop for next player
                        xTurn = false; // Player O turn
                    } else {
                        gameBoard[xBoard-1][yBoard-1] = "O"; // fill "O" on board
                        displayGrid(gameBoard); // display board
                        repeat = false; // break loop for next player
                        xTurn = true; // Player X turn
                    }
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            } catch(InputMismatchException exception) {
                System.out.println("You should enter number!");
                repeat = false; // break infinite
                playerInput(gameBoard);
            }
        }
    }

    private static void displayGrid(String[][] gameBoard) {
        System.out.println("---------");
        for (String[] row: gameBoard) {
            System.out.print("| ");
            for (String c: row) {
                System.out.print(c + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private static String[][] createBoard() {
        String[][] grid = new String[3][3];
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid.length; j++) {
                grid[i][j] = " ";
            }
        }
        return grid;
    }

}