package Kalah;

import javafx.beans.property.*;

/**
 * Created by cfitz on 3/30/17.
 */
public class GameOptions
{
    private IntegerProperty housesPerPlayer = new SimpleIntegerProperty();
    public final int getHousesPerPlayer()
    {
        return housesPerPlayer.get();
    }
    public final void setHousesPerPlayer(int value)
    {
        housesPerPlayer.set(value);
    }
    public IntegerProperty housesPerPlayerProperty()
    {
        return housesPerPlayer;
    }

    private IntegerProperty seedsPerHouse = new SimpleIntegerProperty();
    public final int getSeedsPerHouse()
    {
        return seedsPerHouse.get();
    }
    public final void setSeedsPerHouse(int value)
    {
        seedsPerHouse.set(value);
    }
    public IntegerProperty seedsPerHouseProperty()
    {
        return seedsPerHouse;
    }

    private BooleanProperty randomize = new SimpleBooleanProperty();
    public final boolean getRandomize()
    {
        return randomize.get();
    }
    public final void setRandomize(boolean value)
    {
        randomize.set(value);
    }
    public BooleanProperty randomizeProperty()
    {
        return randomize;
    }

    private StringProperty playerName = new SimpleStringProperty();
    public final String getPlayerName()
    {
        return playerName.get();
    }
    public final void setPlayerName(String value)
    {
        playerName.set(value);
    }
    public StringProperty playerNameProperty()
    {
        return playerName;
    }

    public StringProperty otherPlayerName = new SimpleStringProperty();
    public final String getOtherPlayerName()
    {
        return otherPlayerName.get();
    }
    public final void setOtherPlayerName(String value)
    {
        otherPlayerName.set(value);
    }
    public StringProperty otherPlayerNameProperty()
    {
        return otherPlayerName;
    }

    private BooleanProperty pieRule = new SimpleBooleanProperty();
    public final boolean getPieRule()
    {
        return pieRule.get();
    }
    public final void setPieRule(boolean value)
    {
        pieRule.set(value);
    }
    public BooleanProperty pieRuleProperty()
    {
        return pieRule;
    }

    private LongProperty timeLimit = new SimpleLongProperty();
    public final long getTimeLimit()
    {
        return timeLimit.get();
    }
    public final void setTimeLimit(long value)
    {
        timeLimit.set(value);
    }
    public LongProperty timeLimitProperty()
    {
        return timeLimit;
    }

    private LongProperty timeLeft = new SimpleLongProperty();
    public final long getTimeLeft()
    {
        return timeLeft.get();
    }
    public final void setTimeLeft(long value)
    {
        timeLeft.set(value);
    }
    public LongProperty timeLeftProperty()
    {
        return timeLeft;
    }
}
