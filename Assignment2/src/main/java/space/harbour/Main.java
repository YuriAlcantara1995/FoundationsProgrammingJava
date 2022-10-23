package space.harbour;

import java.util.Scanner;

public class Main {
    public static Move findBestMove(Board board) {
        int bestVal = -(1 << 30);
        Move bestMove = new Move();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int slot = 3 * i + j + 1;
                if (board.isEmpty(slot)) {
                    board.play("X", slot);
                    int moveVal = minimax(board, 0, false);
                    board.revertPlay(slot);

                    if (moveVal > bestVal) {
                        bestMove.setRow(i);
                        bestMove.setColumn(j);
                        bestVal = moveVal;
                    }
                }
            }
        }

        return bestMove;
    }

    public static int minimax(Board board, int depth, boolean isMax) {
        int score = evaluate(board);

        if (score == 1 || score == -1)
            return score;

        if (board.isMovesLeft() == false)
            return 0;

        if (isMax) {
            int best = -1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int slot = 3 * i + j + 1;
                    if (board.isEmpty(slot)) {
                        board.play("X", slot);
                        best = Math.max(best, minimax(board, depth + 1, !isMax));
                        board.revertPlay(slot);
                    }
                }
            }
            return best;
        } else {
            int best = 1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int slot = 3 * i + j + 1;
                    if (board.isEmpty(slot)) {
                        board.play("O", slot);
                        best = Math.min(best, minimax(board, depth + 1, !isMax));
                        board.revertPlay(slot);
                    }
                }
            }
            return best;
        }
    }

    public static int evaluate(Board board) {
        for (int row = 0; row < 3; row++) {
            if (board.checkRowWin(row)) {
                if (board.getPlayer(row, 0) == "X")
                    return 1;
                if (board.getPlayer(row, 0) == "O")
                    return -1;
            }
        }

        for (int col = 0; col < 3; col++) {
            if (board.checkColumnWin(col)) {
                if (board.getPlayer(0, col) == "X")
                    return 1;
                if (board.getPlayer(0, col) == "O")
                    return -1;
            }
        }

        if (board.checkDiagonalWin(0)) {
            if (board.getPlayer(0, 0) == "X")
                return 1;
            if (board.getPlayer(0, 0) == "O")
                return -1;
        }

        if (board.checkDiagonalWin(1)) {
            if (board.getPlayer(0, 2) == "X")
                return 1;
            if (board.getPlayer(0, 2) == "O")
                return -1;
        }

        return 0;
    }

    public static int readNextPlay(String currentPlayer, Board board, Scanner scanner) {
        boolean notValidPlay;
        int slot = -1;
        System.out.println("Is " + currentPlayer + "'s turn");
        do {
            notValidPlay = false;
            try {
                System.out.println("Enter the slot to play:");
                slot = Integer.parseInt(scanner.nextLine());
                if (slot < 1 || slot > 9) {
                    notValidPlay = true;
                    System.out.println("Not valid play, try again");
                } else if (!board.isEmpty(slot)) {
                    notValidPlay = true;
                    System.out.println("Not valid play, try again");
                }

            } catch (NumberFormatException e) {
                notValidPlay = true;
                System.out.println("You should enter a number between 1 and 9");
            }
        } while (notValidPlay == true);

        return slot;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        String currentTurn = "X";

        System.out.println("Tic Tac Toe 3x3");

        int option;
        try {
            System.out.println("Choose the game mode: ");
            System.out.println("1- human vs CPU");
            System.out.println("2- human vs human");
            option = Integer.parseInt(scanner.nextLine());
            if (option != 1 && option != 2) {
                System.out.println("You should enter 1 or 2");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("You should enter 1 or 2");
            return;
        }

        if (option == 2) {
            for (int i = 1; i <= 9; i++) {
                System.out.print(board.toString());
                int slot = readNextPlay(currentTurn, board, scanner);
                board.play(currentTurn, slot);
                if (board.checkWinner()) {
                    System.out.println(currentTurn + " is the winner congratulation!");
                    System.out.print(board.toString());
                    return;
                }
                if (currentTurn.equals("X"))
                    currentTurn = "O";
                else
                    currentTurn = "X";
            }
            System.out.println("It's a draw!");
        } else {
            for (int i = 1; i <= 9; i++) {
                System.out.print(board.toString());
                int slot;

                if (currentTurn == "X") {
                    Move bestMove = findBestMove(board);
                    System.out.println("Is CPU's turn (X)");
                    slot = bestMove.getRow() * 3 + bestMove.getColumn() + 1;
                } else {
                    slot = readNextPlay(currentTurn, board, scanner);
                }

                board.play(currentTurn, slot);
                if (board.checkWinner()) {
                    System.out.println(currentTurn + " is the winner congratulation!");
                    System.out.print(board.toString());
                    return;
                }

                if (currentTurn.equals("X"))
                    currentTurn = "O";
                else
                    currentTurn = "X";
            }

            System.out.println("It's a draw!");
            System.out.print(board.toString());

        }
    }

}