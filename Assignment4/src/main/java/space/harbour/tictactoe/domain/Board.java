package space.harbour.tictactoe.domain;

public class Board {
    private Tile[][] board;

    public Board() {
        board = new Tile[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = new Tile(String.valueOf(3 * i + (j + 1)));
    }

    public boolean isEmpty(int slot) {
        int i = (slot - 1) / 3;
        int j = (slot - 1) % 3;
        return board[i][j].getValue().equals(String.valueOf(slot));
    }

    public void play(String player, int slot) {
        int i = (slot - 1) / 3;
        int j = (slot - 1) % 3;
        board[i][j].setValue(player);
    }
    public void revertPlay(int slot) {
        int i = (slot - 1) / 3;
        int j = (slot - 1) % 3;
        board[i][j].setValue(String.valueOf(slot));
    }
    public boolean checkRowWin(int row) {
        return board[row][0].getValue() == board[row][1].getValue() && board[row][0].getValue() == board[row][2].getValue();
    }

    public boolean checkColumnWin(int column) {
        return board[0][column].getValue() == board[1][column].getValue() && board[0][column].getValue() == board[2][column].getValue();
    }

    public boolean checkDiagonalWin(int diagonal) {
        if(diagonal == 0)
            return board[0][0].getValue() ==  board[1][1].getValue() && board[0][0].getValue() == board[2][2].getValue();

        return board[0][2].getValue() ==  board[1][1].getValue() && board[0][2].getValue() == board[2][0].getValue();
    }

    public String getPlayer(int slot) {
        int i = (slot - 1) / 3;
        int j = (slot - 1) % 3;
        return board[i][j].getValue();
    }
    public String getPlayer(int row, int column) {
        return board[row][column].getValue();
    }
    public boolean checkWinner() {

        for (int i = 0; i < 8; i++) {
            boolean threeInRow = false;

            switch (i) {
                case 0:
                    //first row
                    threeInRow = checkRowWin(0);
                    break;
                case 1:
                    //second row
                    threeInRow = checkRowWin(1);
                    break;
                case 2:
                    //third row
                    threeInRow = checkRowWin(2);
                    break;
                case 3:
                    //first column
                    threeInRow = checkColumnWin(0);
                    break;
                case 4:
                    //second column
                    threeInRow = checkColumnWin(1);
                    break;
                case 5:
                    //third column
                    threeInRow = checkColumnWin(2);
                    break;
                case 6:
                    // diagonal 1
                    threeInRow = checkDiagonalWin(0);
                    break;
                case 7:
                    // diagonal 2
                    threeInRow = checkDiagonalWin(1);
                    break;
            }

            if(threeInRow)
                return true;
        }

        return false;
    }

    @Override
    public String toString() {
        String boardString = "";
        boardString += "|---|---|---|\n";
        boardString += "| " + board[0][0].toString() + " | " + board[0][1].toString() + " | " + board[0][2].toString() + " |\n";
        boardString += "|-----------|\n";
        boardString += "| " + board[1][0].toString() + " | " + board[1][1].toString() + " | " + board[1][2].toString() + " |\n";
        boardString += "|-----------|\n";
        boardString += "| " + board[2][0].toString() + " | " + board[2][1].toString() + " | " + board[2][2].toString() + " |\n";
        boardString += "|---|---|---|\n";

        return boardString;
    }

    public boolean isMovesLeft()
    {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (this.isEmpty(3 * i + (j + 1)))
                    return true;
        return false;
    }

    public Move findBestMove() {
        int bestVal = -(1 << 30);
        Move bestMove = new Move();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int slot = 3 * i + j + 1;
                if (this.isEmpty(slot)) {
                    this.play("O", slot);
                    int moveVal = minimax(0, false);
                    this.revertPlay(slot);

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

    public int minimax(int depth, boolean isMax) {
        int score = evaluate();

        if (score == 1 || score == -1)
            return score;

        if (isMovesLeft() == false)
            return 0;

        if (isMax) {
            int best = -1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int slot = 3 * i + j + 1;
                    if (isEmpty(slot)) {
                        this.play("O", slot);
                        best = Math.max(best, minimax(depth + 1, !isMax));
                        this.revertPlay(slot);
                    }
                }
            }
            return best;
        } else {
            int best = 1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int slot = 3 * i + j + 1;
                    if (isEmpty(slot)) {
                        this.play("X", slot);
                        best = Math.min(best, minimax(depth + 1, !isMax));
                        this.revertPlay(slot);
                    }
                }
            }
            return best;
        }
    }

    public int evaluate() {
        for (int row = 0; row < 3; row++) {
            if (checkRowWin(row)) {
                if (getPlayer(row, 0) == "O")
                    return 1;
                if (getPlayer(row, 0) == "X")
                    return -1;
            }
        }

        for (int col = 0; col < 3; col++) {
            if (checkColumnWin(col)) {
                if (getPlayer(0, col) == "O")
                    return 1;
                if (getPlayer(0, col) == "X")
                    return -1;
            }
        }

        if (checkDiagonalWin(0)) {
            if (getPlayer(0, 0) == "O")
                return 1;
            if (getPlayer(0, 0) == "X")
                return -1;
        }

        if (checkDiagonalWin(1)) {
            if (getPlayer(0, 2) == "O")
                return 1;
            if (getPlayer(0, 2) == "X")
                return -1;
        }

        return 0;
    }


}
