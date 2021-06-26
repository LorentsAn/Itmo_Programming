package ticTacToe.Players;

import ticTacToe.Cell;
import ticTacToe.Move;
import ticTacToe.Player;
import ticTacToe.Position;

import java.util.Random;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class RandomPlayer implements Player {
    private final Random random;
    int n;
    int m;

    public RandomPlayer(final Random random) {
        this.random = random;
    }

    public RandomPlayer(int n, int m) {
        this(new Random());
        this.n = n;
        this.m = m;
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            int r = random.nextInt(n);
            int c = random.nextInt(m);
            final Move move = new Move(r, c, cell);
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}