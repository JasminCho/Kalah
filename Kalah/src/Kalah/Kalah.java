package Kalah;

import java.util.Random;
import java.util.Scanner;

public class Kalah
{
    Player player;
    Player otherPlayer;

    Player playerGoing;
    Player winner;

    int currentTurn;

    GameOptions options;

    public Kalah(GameOptions opts)
    {
        options = opts;
        
        // player 1 settings
        player = new Player(options.getPlayerName(), options.getHousesPerPlayer());

        // player 2 settings
        otherPlayer = new Player(options.getOtherPlayerName(), options.getHousesPerPlayer());

        // player 1 goes first for now
        playerGoing = player;
    }

    void startGame()
    {
        currentTurn = 1;

        fillHouses(options.getSeedsPerHouse());

        displayBoard();

        while(!player.getHousesEmpty() && !otherPlayer.getHousesEmpty())
        {
            boolean skipTurn = false;

            if(options.getPieRule() && currentTurn == 2)
            {
                if(pieRule() == 2)
                {
                    // if they choose to swap, we skip the turn
                    skipTurn = true;
                    displayBoard();
                }
            }

            if(!skipTurn)
            {
                //Take in input for house
                Scanner keyboard = new Scanner(System.in);

                // tell player to go
                System.out.print(playerGoing.getPlayerName() + ", you have " + options.getTimeLimit() / 1000 + " seconds to go. Enter house number 1-" + options.getHousesPerPlayer() + ": ");

                // save current time
                long beforeTime = System.currentTimeMillis();

                int input = keyboard.nextInt() - 1;

                long afterTime = System.currentTimeMillis();

                // compare time now to the time before input to see if the user took too long
                if (afterTime - beforeTime > options.getTimeLimit())
                {
                    System.out.println(playerGoing.getPlayerName() + " took too long to take their turn, so they lost!");
                    return;
                }

                boolean goAgain = moveSeeds(playerGoing, input);
                displayBoard();

                // if it's the other player's turn
                if (!goAgain)
                {
                    // determine the new player
                    if (playerGoing == player)
                    {
                        playerGoing = otherPlayer;
                  }
                    else
                        {
                        playerGoing = player;
                    }
                }

                currentTurn++;
            }
        }

        // determine winner
        // calculate player1 store
        player.addToStore(player.getTotalSeedsInHouses());
        player.fillHouses(0);
        // calculate player2 store
        otherPlayer.addToStore(otherPlayer.getTotalSeedsInHouses());
        otherPlayer.fillHouses(0);

        // show the winning board
        displayBoard();

        // calculate the winner
        if(player.getPlayerStore() > otherPlayer.getPlayerStore())
        {
            winner = player;
        }
        else if(player.getPlayerStore() == otherPlayer.getPlayerStore())
        {
            winner = null;
        }
        else
        {
            winner = otherPlayer;
        }

        if(winner == null)
        {
            System.out.println("Tie");
        }
        else
        {
            System.out.println(winner.getPlayerName());
        }
    }

    void debug(String message)
    {
        System.out.println("DEBUG: " + message);
    }

    public void displayBoard()
    {
        int lineLen = displayHouses(otherPlayer, true);
        displayStore(otherPlayer);

        // create spaces between the stores equal to the length of the filled houses
        for(int i = 0; i < lineLen; i++)
        {
            System.out.print(" ");
        }

        displayStore(player);

        System.out.println();

        displayHouses(player);
    }

    public int displayHouses(Player thisPlayer)
    {
        return displayHouses(thisPlayer, false);
    }

    public int displayHouses(Player thisPlayer, boolean reverse)
    {
        System.out.print("   ");
        String output = "";

        if(reverse)
        {
            for (int i = options.getHousesPerPlayer() - 1; i >= 0; i--)
            {
                output += "[" + thisPlayer.getSeedsInHouse(i) + "]";
            }
        }
        else
        {
            for (int i = 0; i < options.getHousesPerPlayer(); i++)
            {
                output += "[" + thisPlayer.getSeedsInHouse(i) + "]";
            }
        }

        System.out.println(output);

        return output.length();
    }

    public void displayStore(Player thisPlayer)
    {
        System.out.print("|" + thisPlayer.getPlayerStore() + "|");
    }

    public Player getOppositePlayer(Player p)
    {
        if(p == player)
        {
            return otherPlayer;
        }
        else
        {
            return player;
        }
    }

    public boolean moveSeeds(Player chosenPlayer, int chosenHouse) // index determined by button(house) clicked
    {
        Player currentPlayer = chosenPlayer;

        int currentHouse = chosenHouse;
        int seedsInHand = currentPlayer.getSeedsInHouse(currentHouse);

        // set number of seeds in the selected house to 0
        currentPlayer.setSeedsInHouse(currentHouse, 0);
        // go to next house
        currentHouse++;

        String housesMessage = "";
        for(int i = 0; i < options.getHousesPerPlayer(); i++)
        {
            if(i == chosenHouse)
            {
                housesMessage += "[x] ";
            }
            else
            {
                housesMessage += "[ ]" ;
            }
        }

        debug("Player chose: " + housesMessage);

        /*
        If the last seed is placed in an empty house (no other seeds there), then player picks up seeds in the other
        player's house directly across from it and places them in their own store
        */

        // loop until seeds left in hand is 0
        while(seedsInHand > 0)
        {
            // if in the player's current houses, put seed in there
            if (currentHouse < options.getHousesPerPlayer())
            {
                /*if (currentPlayer == playerGoing && currentPlayer.getSeedsInHouse(currentHouse) == 0)
                {
                    // check opposite house for seeds and take the one seed you dropped and all the seeds in the other house and put in store
                    int oppositeHouse = housesPerPlayer - currentHouse - 1;

                    int seedsInOppositeHouse = getOppositePlayer(currentPlayer).getSeedsInHouse(oppositeHouse);
                    currentPlayer.addToStore(seedsInOppositeHouse + 1);
                    seedsInHand--;
                }
                else*/
                {
                    // increment seeds in house by 1
                    currentPlayer.addToHouse(currentHouse, 1);
                    currentHouse++;
                    seedsInHand--;
                }
            }
            // if at the end of the player's houses, put seed in the store
            else if (currentHouse == options.getHousesPerPlayer() && currentPlayer == playerGoing)
            {
                // increment player's store by 1
                currentPlayer.addToStore(1);
                currentHouse++;
                seedsInHand--;

                // Last seed dropped in the current player's store, they go again
                if(seedsInHand == 0)
                {
                    return true;
                }
            }
            // on other player's houses now
            else
            {
                // switch players
                currentPlayer = getOppositePlayer(currentPlayer);

                currentHouse = 0;
            }
        }

        return false;
    }

    void fillHouses(int seedsPerHouse)
    {
        if (options.getRandomize())
        {
            randomizeSeeds();
        }
        else
        {
            player.fillHouses(seedsPerHouse);
            otherPlayer.fillHouses(seedsPerHouse);
        }
    }

    void randomizeSeeds()
    {
        // Get total number of seeds per side
        // Randomly pick a house from 0 to the specified house number - 1
        // Place one seed and subtract it from the total number of seeds per side
        // Randomly pick another house and repeat
        int seedsPerHouse = options.getSeedsPerHouse();
        int totalSeedsPerSide = options.getHousesPerPlayer()*seedsPerHouse;

        int seedsLeft = totalSeedsPerSide;

        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        while(seedsLeft > 0)
        {
            // pick random house
            int house = rand.nextInt(options.getHousesPerPlayer() - 1);

            // add seed to house for player 1
            player.addToHouse(house, 1);
            // add seed to opposite house for player 2
            otherPlayer.addToHouse(house, 1);

            seedsLeft--;
        }
    }

    int pieRule()
    {
        Scanner keyboard = new Scanner(System.in);

        /*
         Second, we’d like to have a “Pie rule” option for the first move. The Pie rule is a way of evening
        out games where one player has an advantage. It comes from the way that you ensure a fair
        split if a piece of pie is cut in two: let one person cut the slice, then the other gets to choose
        which of the two to keep. The way the pie rule works is this:
        a. Player 1 makes a move, as normal.
        b. Player 2 makes a choice:
        i. Option 1: Player 2 lets Player 1’s move stand, and then goes ahead and plays as
        normal
        ii. Option 2: Player 2 chooses to switch positions with Player 1 (i.e. Player 2
        essentially becomes Player 1). So, the person who was Player 1 makes another
        move, this time as Player 2.
        After that first move, the game proceeds as normal – there are no further switches later on
        */
        System.out.println("Option 1: " + otherPlayer.getPlayerName() + "'s lets " + player.getPlayerName() + " move stand, and then goes ahead and plays as normal");
        System.out.println("Option 2: " + otherPlayer.getPlayerName() + "'s chooses to switch positions with " + player.getPlayerName());
        System.out.print("PUT WHAT YOU WANT TO DO PLS (1 or 2): ");
        
        int choice = keyboard.nextInt();
        int[] tempHouses;
        int tempStore;
        Player oppositePlayer = getOppositePlayer(playerGoing);

        if(choice == 2)
        {
            tempStore = playerGoing.getPlayerStore();
            tempHouses = playerGoing.getHouses();

            playerGoing.setHouses(oppositePlayer.getHouses());
            playerGoing.setPlayerStore(oppositePlayer.getPlayerStore());

            oppositePlayer.setHouses(tempHouses);
            oppositePlayer.setPlayerStore(tempStore);

            currentTurn++;
            playerGoing = oppositePlayer;
        }

        return choice;
    }
}
