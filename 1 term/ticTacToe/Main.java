package ticTacToe;

import ticTacToe.Players.HumanPlayer;
import ticTacToe.Players.RandomPlayer;

import java.util.Scanner;

public class Main {

    public static int toDigital(String num, String peremen) {
        Scanner scan = new Scanner(System.in);
        boolean chislo = false;
        while (!chislo) {
            for (int i = 0; i < num.length(); i++) {
                if (!Character.isDigit(num.charAt(i))) {
                    System.out.println("Неверный ввод числа, введите заново" + " " + peremen);
                    num = scan.next();
                    break;
                } else if (i == num.length() - 1) {
                    chislo = true;
                    if (Integer.parseInt(num) < 1) {
                        System.out.println("Число" + " " + peremen + " " + "меньше единицы, введите большее значение");
                        num = scan.next();
                        chislo = false;
                        break;
                    }
                }
            }

        }
        return Integer.parseInt(num);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Введите размерность доски");
        String numN = scan.next();
        int n = toDigital(numN, "n");
        String numM = scan.next();
        int m = toDigital(numM, "m");
        System.out.println("Введите число для победы");
        int k;
        while (true) {
            String num = scan.next();
            k = toDigital(num, "k");
            if (k > Math.max(n, m)) {
                System.out.println("Ошибка, вы переборщили с числом, введите заново");
            } else {
                break;
            }
        }
        System.out.println("Введите число побед для выйгрыша");
        int valWin = scan.nextInt();
        int winOfFirst = 0;
        int winOfSecond = 0;
        int priority = 0;
        Player player1 = new HumanPlayer();
        Player player2 = new RandomPlayer(n, m);

        while (Math.max(winOfFirst, winOfSecond) < valWin) {
            if (priority % 2 == 0) {
                final Game game = new Game(false, player1, player2);
                int result;
                do {
                    result = game.play(new TicTacToeBoard(n, m, k));
                    System.out.println("Game result: " + result);
                    if (result == 1) {
                        winOfFirst += 1;
                    } else if (result == 2){
                        winOfSecond += 1;
                    }
                    break;
                } while (result != 0);
            } else {
                final Game game = new Game(false, player2, player1);
                int result;
                do {
                    result = game.play(new TicTacToeBoard(n, m, k));
                    if (result == 1) {
                        winOfSecond += 1;
                        System.out.println("Game result: 2");
                    } else if (result == 2) {
                        winOfFirst += 1;
                        System.out.println("Game result: 1");
                    }
                    break;
                } while (result != 0);
            }
            priority += 1;

        }
        if (winOfFirst == Math.max(winOfFirst, winOfSecond)) {
            System.out.println("Выйграл первый игрок с счетом" + " " + winOfFirst + ":" + winOfSecond);
        } else {
            System.out.println("Выйграл второй игрок с счетом" + " " + winOfFirst + ":" + winOfSecond);
        }

    }


}
