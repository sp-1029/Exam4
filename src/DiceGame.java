import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
public class DiceGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select the number of sides on your die");
        Die die = new Die(scanner.nextInt());
        System.out.println("How many players are there?");
        int numPlayers = scanner.nextInt();
        Player[] players = new Player[numPlayers + 1];
        for (int i = 0; i < numPlayers; i++){
            System.out.println("What is the name of player " + (i + 1) + "?");
            String name = scanner.next();
            players[i] = new Player(name, die);
            players[i].setDie();
            players[i].setName(name);
        }
        for (int i = 0; i < numPlayers; i++){
            die.roll();
            players[i].setRolledValue(die.getValue());
            //System.out.println(players[i].getName() + " rolled a " + die.getValue() + " on a " + die.getNumSides() + " sided die");
        }

        Player[] highestRollers = new Player[numPlayers + 1];
        int highestRoll = players[0].getRolledValue();
        highestRollers[0] = players[0];
        for (int i = 0; i < numPlayers - 1; i++){
            if (players[i + 1].getRolledValue() > highestRoll){
                Arrays.fill(highestRollers, null);
                highestRollers[i + 1] = players[i + 1];
                highestRoll = players[i + 1].getRolledValue();
            } else if(players[i + 1].getRolledValue() == highestRoll) {
                highestRollers[i + 1] = players[i + 1];
            }
        }

        int numWinners = 0;
        for (int i = 0; i <= numPlayers; i++){
            if (highestRollers[i] != null) {
                numWinners++;
            }
        }

        File output = new File("DiceGameOutput.txt");
        if (numWinners == 1) {
            for(int i = 0; i < numPlayers; i++){
                if (highestRollers[i] != null){
                    try {
                        FileWriter fw = new FileWriter(output);
                        for(int j = 0; j < numPlayers; j++){
                            fw.write(players[j].getName() + " rolled a " + players[j].getRolledValue() + " on a " + die.getNumSides() + " sided die" + '\n');
                        }
                        fw.write(highestRollers[i].getName() + " wins");
                        fw.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
            }
        } else {
            Player[] winners = new Player[numWinners];
            int j = 0;
            for(int i = 0; i < numPlayers; i++){
                if (highestRollers[i] != null){
                    winners[j] = highestRollers[i];
                    j++;
                }
            }
            try {
                FileWriter fw = new FileWriter(output);
                for(int i = 0; i < numPlayers; i++){
                    fw.write(players[i].getName() + " rolled a " + players[i].getRolledValue() + " on a " + die.getNumSides() + " sided die" + '\n');
                }
                fw.write("There is a tie between all of these players:" + '\n');
                for(int i = 0; i < numWinners; i++) {
                    fw.write(winners[i].getName() + '\n');
                }
                fw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}