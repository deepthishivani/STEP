package main.java.programming_fundamentals.class_problems;

import java.util.*;

public class RockPaperScissors {

    static String playRound(String playerMove, String computerMove) {
        if (playerMove.equals(computerMove))
            return "Draw";

        if ((playerMove.equals("Rock") && computerMove.equals("Scissors")) ||
            (playerMove.equals("Paper") && computerMove.equals("Rock")) ||
            (playerMove.equals("Scissors") && computerMove.equals("Paper")))
            return "Player Wins";

        return "Computer Wins";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        String[] moves = {"Rock", "Paper", "Scissors"};
        int n = 5;

        String[] player = new String[n];
        String[] computer = new String[n];
        String[] result = new String[n];

        int wins = 0, losses = 0, draws = 0;

        for (int i = 0; i < n; i++) {
            System.out.print("Enter Rock, Paper or Scissors: ");

            String move = sc.next();

            player[i] = move.substring(0, 1).toUpperCase()
                    + move.substring(1).toLowerCase();

            computer[i] = moves[random.nextInt(3)];

            result[i] = playRound(player[i], computer[i]);

            if (result[i].equals("Player Wins"))
                wins++;
            else if (result[i].equals("Computer Wins"))
                losses++;
            else
                draws++;

            System.out.println(result[i]);
        }

        System.out.println("\nRound\tPlayer\t\tComputer\tResult");

        for (int i = 0; i < n; i++) {
            System.out.println(
                    (i + 1) + "\t" +
                    player[i] + "\t\t" +
                    computer[i] + "\t\t" +
                    result[i]
            );
        }

        double winPercentage = wins * 100.0 / n;

        System.out.printf(
                "\nWins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%%n",
                wins, losses, draws, winPercentage
        );
    }
}
