Project made using IntelliJ IDE, Java, and Javafx

To run project simplyopen the Kalah.iml and Run

In the window that pops up simply type in your name and hit single player
(other buttons will be implemented in later sprints)

Inside the console window there is a text version of the game for testing purposes

For the first sprint:
Got main classes Player.java and Kalah.java as well as some GUI functionality working
The original plan to have Board.java and Marble.java was changed as the two classes were combined
into Kalah.java which is the main class for the game logic

Player.java holds Player information such as name, store(score), houses, etc
Kalah.java holds functions and variables for creating the board, filling the houses, and keeping track of stores
    The main game logic will be implemented here as well as display calls for the GUI
    The display calls currently show a text version of the game in the console window
    Score.java was also implemented into Kalah.java to keep things simple
    Calls to other players (as well as AI once that class is implemented) are also done here

No input validation for the text game because it will later be implemented with buttons in the GUI where there will
be no way for there to be an invalid input

Current GUI is without CSS so it's a lil' ugly for now

~~~~~~~~~~~~~~~~~~~~~~~~~
Kalah rules we followed

Seeds = marbles
House = holes in board
Store = player's main score

Rules:
    Player can only access houses on their side
    Player's turn begins and they pick up however many seeds are in a particular house
    Place one seed in a counter-clockwise motion in each house/hole
    If player reaches their dedicated Store on the side of the board, place one in
    If the seed placed in the Store is the last one in the player's hand, you get another turn (go again)
    If the last seed is placed in an empty house (no other seeds there), then player picks up seeds in the other
        player's house directly across from it and places them in their own store
    Turn ends when player places last seed in a house occupied by seeds or an empty house
    The game is over when one player's houses are completely empty and then the other player gets to take all the
        seeds in their houses and place them in their store
    The player with most seeds win