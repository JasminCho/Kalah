package Kalah;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class IntroScreenController
{
    public Button singlePlayerMode, twoPlayerMode, ruleScreen;
    public TextField playerName;
    public TextField timeLimit;
    public Label timeLeft;
    public ComboBox<Integer> numberOfHouses;
    public ComboBox<Integer> numberOfSeeds;
    public CheckBox randomizeSeeds;
    public CheckBox pieRule;

    GameOptions options;

    @FXML
    public void initialize()
    {
        options = new GameOptions();

        numberOfHouses.getItems().addAll(4, 5, 6, 7, 8, 9);
        numberOfSeeds.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        options.timeLeftProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                timeLeft.setText(newValue.toString());
            }
        });
    }

    public void startSinglePlayer()
    {
        singlePlayerMode.setText("Single Player Button pressed");
        setOptions();

//        Kalah game = new Kalah(playerName.getText(), "Player 2", 4, 4);
        Kalah game = new Kalah(options);
        game.startGame();
    }

    public void startTwoPlayer()
    {
        twoPlayerMode.setText("Two Player Button pressed");
        setOptions();
        System.out.println(playerName.getText());
    }

    public void openRuleScreen()
    {
        ruleScreen.setText("Rule screen button pressed");
        System.out.println(playerName.getText());
    }

    public void setOptions()
    {
        options.setHousesPerPlayer(numberOfHouses.getValue());
        options.setSeedsPerHouse(numberOfSeeds.getValue());
        options.setPlayerName(playerName.getText());
        options.setOtherPlayerName("Player 2");
        options.setRandomize(randomizeSeeds.isSelected());
        options.setTimeLimit(Long.parseLong(timeLimit.getText()));
        options.setPieRule(pieRule.isSelected());
        //options.setTimeLeft(options.getTimeLimit());
    }
}