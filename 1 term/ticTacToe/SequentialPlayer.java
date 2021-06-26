package ticTacToe.Players;

import ticTacToe.Cell;
import ticTacToe.Move;
import ticTacToe.Player;
import ticTacToe.Position;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class SequentialPlayer implements Player {
    int n;
    int m;
    public SequentialPlayer(int n, int m) {
        this.n = n;
        this.m = m;
    }
    @Override
    public Move move(final Position position, final Cell cell) {
//        Board board = (Board) position;
//        board.makeMove()
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                final Move move = new Move(r, c, cell);
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        throw new IllegalStateException("No valid moves");
    }
}