package Kalah;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("Scenes/IntroScreen.fxml"));
        primaryStage.setTitle("Kalah Game");
        primaryStage.setScene(new Scene(root, 900, 500));
        //primaryStage.setFullScreen(true);
        primaryStage.show();
    }
}

