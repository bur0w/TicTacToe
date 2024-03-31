import java.util.Scanner;

public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];
    private static String currentPlayer = "X";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        clearBoard();

        while (true) {
            display();
            int rowMove = getRangedInt(scanner, "Enter the row (1-3): ", 1, 3) - 1;
            int colMove = getRangedInt(scanner, "Enter the column (1-3): ", 1, 3) - 1;

            if (!isValidMove(rowMove, colMove)) {
                System.out.println("Invalid move! The cell is already occupied.");
                continue;
            }

            board[rowMove][colMove] = currentPlayer;

            if (isWin(currentPlayer)) {
                display();
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            } else if (isTie()) {
                display();
                System.out.println("It's a tie!");
                break;
            }

            currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
        }

        scanner.close();
    }

    private static void clearBoard() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = " ";
            }
        }
    }

    private static void display() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                System.out.print(board[i][j]);
                if (j < COL - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < ROW - 1) {
                System.out.println("---------");
            }
        }
    }

    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int i = 0; i < ROW; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int i = 0; i < COL; i++) {
            if (board[0][i].equals(player) && board[1][i].equals(player) && board[2][i].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
               (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static boolean isTie() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (board[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
}
