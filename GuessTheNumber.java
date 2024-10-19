import java.util.Scanner;
import java.util.Random;
class Guesser
{
    int guessNum;
    int guessNum()
    {
        Random random = new Random();
        guessNum = random.nextInt(50) + 1;
        System.out.println("Guesser has guessed the number (hidden for players.");
        return guessNum;
    }
}
class Player
{
    int guessNum;
    int playerNumber;

    // Constructor to assign player number
    Player(int playerNumber)
    {
        this.playerNumber = playerNumber;
    }

    int guessNum()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Player " + playerNumber + ", kindly guess the number:");
        guessNum = input.nextInt();
        return guessNum;
    }
}
class Umpire {
    int numFromGuesser;
    int[] numFromPlayers = new int[3];
    boolean[] activePlayers = {true, true, true};

    void collectNumberFromGuesser() {
        Guesser g = new Guesser();
        numFromGuesser = g.guessNum();
    }


    void collectNumberFromPlayers() {
        Player p1 = new Player(1);
        Player p2 = new Player(2);
        Player p3 = new Player(3);
        if (activePlayers[0]) numFromPlayers[0] = p1.guessNum();
        if (activePlayers[1]) numFromPlayers[1] = p2.guessNum();
        if (activePlayers[2]) numFromPlayers[2] = p3.guessNum();
    }

    boolean compare() {
        int winnerPlayer = -1;
        boolean anyPlayerCorrect = false;
        for (int i = 0; i < activePlayers.length; i++) {
            if (activePlayers[i]) {
                if (numFromGuesser == numFromPlayers[i]) {
                    System.out.println("Player " + (i + 1) + " guessed correctly!");
                    anyPlayerCorrect = true;
                    winnerPlayer = i+1;
                } else {
                    activePlayers[i] = false;
                    System.out.println("Player " + (i + 1) + " guessed incorrectly and is eliminated!");
                }
            }
        }


        if (anyPlayerCorrect) {
            int correctCount = 0;
            for (boolean active : activePlayers) {
                if (active) correctCount++;
            }

            if (correctCount == 1) {
                System.out.println("Player " + winnerPlayer + " is the winner! Game over!");
                return false;
            } else {
                System.out.println("Multiple players guessed correctly! Moving to the next round.");
                return true;
            }
        } else {
            System.out.println("No players guessed correctly. Game over!");
            return false;
        }
    }
    void displayGuesserNumber(){
        System.out.println("The Guesser number was: " +numFromGuesser);
    }
}

public class GuessTheNumber {
    public static void main(String[] args) {
        Umpire u = new Umpire();
        int round = 1;
        boolean continueGame = true;

        while (continueGame) {
            System.out.println("\n=== Round " + round + " ===");
            u.collectNumberFromGuesser();
            u.collectNumberFromPlayers();
            continueGame = u.compare();
            if (!continueGame) {
                u.displayGuesserNumber();
                break;
            }
            if (round == 2) {
                System.out.println("Welcome to the Semifinal!");
            } else if (round == 3) {
                System.out.println("Welcome to the final!");
            } else if (round == 4) {
                System.out.println("Welcome to the Super Final!");
            }
            round++;
        }
        System.out.println("Game Over!");
    }
}