package ticTacToe;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;


public class TicTacToeBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private Cell[][] cells;
    private Cell turn;
    private int n;
    private int m;
    private int k;
    public int all = 0;

    public TicTacToeBoard(int n, int m, int k) {
        this.n = n;
        this.m = m;
        this.k = k;
        this.cells = new Cell[n][m];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }


    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        int rowNow = move.getRow();
        int columnNow = move.getColumn();
        Cell value = move.getValue();
        cells[rowNow][columnNow] = value;
        int inDiag1 = 1;
        int inDiag2 = 1;
        int inColumn = 1;
        int inRow = 1;

        all += 1;

        // идем в строке

        for (int i = 1; i < k + 1; i++) {
            if (columnNow + i < m) {
                if (cells[rowNow][columnNow + i] == value) {
                    inColumn += 1;
                } else {
                    break;
                }
            }
            if (columnNow - i >= 0) {
                if (cells[rowNow][columnNow - i] == value) {
                    inColumn += 1;
                } else {
                    break;
                }
            }
        }
        if (inColumn >= k) {

            return Result.WIN;
        }

        // идем по столбцу

        for (int i = 1; i < k + 1; i++) {
            if (rowNow + i < n) {
                if (cells[rowNow + i][columnNow] == value) {
                    inRow += 1;

                } else {
                    break;
                }
            }
            if (rowNow - i >= 0) {
                if (cells[rowNow - i][columnNow] == value) {
                    inRow += 1;
               } else {
                    break;
                }
            }
        }
        if (inRow >= k) {
            return Result.WIN;
        }

        // идем по диагонали налево

        for (int i = 1; i < k + 1; i++) {
            if (columnNow + i < m && rowNow + i < n) {
                if (cells[rowNow + i][columnNow + i] == value) {
                    inDiag1 += 1;
                } else {
                    break;
                }
            }
            if (columnNow - i >= 0 && rowNow - i >= 0) {
                if (cells[rowNow - i][columnNow - i] == value) {
                    inDiag1 += 1;
                } else {
                    break;
                }
            }
        }
        if (inDiag1 >= k) {
            return Result.WIN;
        }

        // идем по диагонали направо


        for (int i = 1; i < k + 1; i++) {
            if (columnNow + i < m && rowNow - i >= 0 && columnNow + i >=0 && rowNow - i < n) {
                if (cells[rowNow - i][columnNow + i] == value) {
                    inDiag2 += 1;
                } else {
                    break;
                }
            }
            if (columnNow - i < m && rowNow + i >= 0 && columnNow - i >= 0 && rowNow + i < n) {
                if (cells[rowNow + i][columnNow - i] == value) {
                    inDiag2 += 1;
                } else {
                    break;
                }
            }
        }
        if (inDiag2 >= k) {
            return Result.WIN;
        }

        if (all == m * n) {
            return Result.DRAW;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;

        return Result.UNKNOWN;

    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < n
                && 0 <= move.getColumn() && move.getColumn() < m
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        for (int r = 0; r < n; r++) {
            sb.append("\n");
            for (int c = 0; c < m; c++) {
                sb.append(SYMBOLS.get(cells[r][c]));
            }
        }
        return sb.toString();
    }

}
