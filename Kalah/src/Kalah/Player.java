package Kalah;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class Player
{
    /*
    TODO: Pie Rule - Player 1 goes; Player 2 chooses if they want Player 1's place or not (P reported by Player 2)
    TODO: Time limit to how long your move can be (Time limit 0 = no time limit)
    TODO: Game play b/w Two AI, AI vs Player, or Two Players
    TODO: Client-server
          ILLEGAL reported with illegal moves or TIME for time limit up - ends game
          The person who made an illegal move or went overtime is the loser
          OK is returned if everything was legal and within the time limit
          If the client took too long to make a move or made an illegal move, the server
          should send it a TIME or ILLEGAL response instead of an OK.
          If the server took too long to make a move, it should send a TIME statement
          after sending an OK, before sending the move. If the server made an illegal move,
          it should send the move, then an ILLEGAL acknowledgement (followed by a WINNER acknowledgement)
    TODO: Server reports TIME, ILLEGAL, WINNER, LOSER, WELCOME, TIE messages
    TODO: Client reports READY
    TODO: Program chooses to be server or client
    */
    private String playerName;
    private int playerStore; // the player's store
    int finalScore;
    private int numHouses;

    private int[] playerHouses; // seeds on their side

    public Player(String name, int numHouses)
    {
        setPlayerName(name);
        playerStore = 0;
        finalScore = 0;

        setNumHouses(numHouses);
    }

    // Add seeds into a specified house
    public void addToHouse(int houseIndex, int seedNum)
    {
        playerHouses[houseIndex] += seedNum;
    }

    void fillHouses(int seedsPerHouse)
    {
        for (int i = 0; i < numHouses; i++)
        {
            setSeedsInHouse(i, seedsPerHouse);
        }
    }

    // Increment the Player Store
    public void addToStore(int s)
    {
        playerStore += s;
    }

    // Getters
    public String getPlayerName()
    {
        return playerName;
    }

    public int getSeedsInHouse(int selectedHouse)
    {
        return playerHouses[selectedHouse];
    }

    public int getPlayerStore()
    {
        return playerStore;
    }

    public boolean getHousesEmpty()
    {
        return getTotalSeedsInHouses() == 0;
    }

    public int getTotalSeedsInHouses()
    {
        int total = 0;
        for(int i = 0; i < numHouses; i++)
        {
            total += getSeedsInHouse(i);
        }
        return total;
    }

    public int[] getHouses()
    {
        return playerHouses;
    }

    // Setters
    public void setPlayerName(String name)
    {
        playerName = name;
    }

    public void setNumHouses(int h)
    {
        numHouses = h;
        playerHouses = new int[numHouses];
    }

    public void setSeedsInHouse(int selectedHouse, int numSeeds)
    {
        playerHouses[selectedHouse] = numSeeds;
    }

    public void setPlayerStore(int s)
    {
        playerStore = s;
    }

    public void setHouses(int[] newHouses)
    {
        playerHouses = newHouses;
    }
}
