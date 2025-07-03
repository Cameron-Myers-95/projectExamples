package programmingProject5;

import java.util.Scanner;
/**
 * @author Cameron Myers
 * @version 7/3/2025
 * 
 * Allows users to manage the game interface, including adding, removing,
 * listing players, and tracking the turn status during gameplay.
 */
public class GamingProgram
{
	
	
    public static void main(String[] args)
    {
    	 Scanner scan = new Scanner(System.in);
         TurnManager manager = new TurnManager();
         boolean running = true;
         
         while (running)
         {
             System.out.println("\n== GAME OPTIONS ==");
             System.out.println("[A] Add player");
             System.out.println("[D] Remove player");
             System.out.println("[L] List players");
             System.out.println("[S] Begin next turn");
             System.out.println("[W] Who's next");
             System.out.println("[E] End");
             System.out.print("Select an option: ");

             String choice = scan.nextLine().trim().toUpperCase();
          
             //handles each case of user input
             switch (choice)
             {
                 case "A":
                     System.out.print("Enter player name: ");
                     String name = scan.nextLine().trim();
                     boolean duplicate = false;
                     for (Player p : manager.listPlayers())
                     {
                         if (p.getPlayerID().equalsIgnoreCase(name))
                         {
                             duplicate = true;
                             break;
                         }
                     }

                     if (duplicate)
                     {
                         System.out.println("Username is taken.");
                     }
                     else
                     {
                         manager.addPlayer(new Player(name));
                         System.out.println(name + " has been added.");
                     }
                     break;

                 case "D":
                     System.out.print("Enter player name to remove: ");
                     String nameToRemove = scan.nextLine().trim();
                     boolean removed = manager.removePlayer(nameToRemove);
                     
                     if (removed)
                     {
                         System.out.println(nameToRemove + " removed.");
                     } 
                     else
                     {
                         System.out.println("Player not found.");
                     }
                     break;

                 case "L":
                     Player[] players = manager.listPlayers();
                     if (players.length == 0) 
                     {
                         System.out.println("No players in the game.");
                     } 
                     else
                     {
                         System.out.println("Current players: ");
                         for (Player p : players) 
                         {
                             System.out.println("- " + p);
                         }
                     }
                     break;

                 case "S":
                     Player current = manager.startNextTurn();
                     if (current == null)
                     {
                         System.out.println("No players to take a turn.");
                     }
                     else
                     {
                         System.out.println("It's " + current + "'s turn.");
                     }
                     break;

                 case "W":
                     Player next = manager.whosNext();
                     if (next == null)
                     {
                         System.out.println("No players in the game.");
                     } 
                     else
                     {
                         System.out.println("Next up: " + next);
                     }
                     break;

                 case "E":
                     System.out.println("GAME OVER");
                     running = false;
                     break;

                 default:
                     System.out.println("Please give a valid input.");
             }
         }

         scan.close();


    }
}
    
    
/**
 * This class constructs our players username and returns it
 * @param playerID the username of the player
 * @return playerID returns the username of the player
 */
class Player 
{
    private String playerID;

    public Player(String playerID)
    {
        this.playerID = playerID;
    }

    public String getPlayerID()
    {
        return playerID;
    }

    public String toString()
    {
        return playerID;
    }
}


class TurnManager
{
    private Player[] participants;
    private int count;
    private int currentTurnIndex;
    
    /**
     * This method keeps track of the turns of each player,
     * adds new players to the rotation, and ensures the game
     * moves forward in the correct order. 
     */
    public TurnManager()
    {
        participants = new Player[100];
        count = 0;
        currentTurnIndex = 0;
    }
    /**
     * This method adds a player to the end of the list and returns false
     * if the list is full.
     * @param p The player added.
     * @return false if list is full, true if a player is added. 
     */
    public boolean addPlayer(Player p)
    {
        if (count >= participants.length)
            return false;

        participants[count] = p;
        count++;
        return true;
    }
    /**
     * This method will remove a player from the game
     * @param playerID The username of the player
     * @return returns true if the username can be found otherwise returns false
     */

    public boolean removePlayer(String playerID)
    {
        for (int i = 0; i < count; i++)
        {
            if (participants[i].getPlayerID().equalsIgnoreCase(playerID))
            {
                for (int j = i; j < count - 1; j++)
                {
                    participants[j] = participants[j + 1];
                }

                count--;

                if (i < currentTurnIndex)
                {
                    currentTurnIndex--;
                }
                if (currentTurnIndex >= count && count > 0)
                {
                    currentTurnIndex = 0;
                }

                return true;
            }
        }
        return false;
    }
    
    /**
     * This method shows which players turn is next
     * 
     * @return The next player in line, if there are no players it will return a null value
     */

    public Player whosNext()
    {
        if (count == 0)
        {
            return null;
        }
        return participants[currentTurnIndex];
    }
    
    /**
     * This method starts the turn of the next player in line
     * @return the name of player whos turn it is and a null value if there are no players
     */
    public Player startNextTurn()
    {
        if (count == 0)
        {
            return null;
        }

        Player current = participants[currentTurnIndex];
        currentTurnIndex = (currentTurnIndex + 1) % count;
        return current;
    }
    /**
     * This method lists all the current players in order of their turn.
     * @return the current order of players using an array.
     */
    public Player[] listPlayers()
    {
        Player[] result = new Player[count];
        for (int i = 0; i < count; i++)
        {
            int index = (currentTurnIndex + i) % count;
            result[i] = participants[index];
        }
        return result;
    }

  }






