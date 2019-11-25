package thegame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        GameController gameController = new GameController(primaryStage);


        primaryStage.setTitle("The Game!!!");
        Pane root = new Pane();
        Scene startGame = new Scene(root,1050,700);

        Image gameStart = new Image("/image/background/start.jpg");
        ImageView gameStartView = new ImageView(gameStart);
        root.getChildren().add(gameStartView);

        Media musicStart = new Media(new File("D:\\BTLOOP\\src\\music\\startgame.mp3").toURI().toString());
        MediaPlayer musicStartPlayer = new MediaPlayer(musicStart);
        musicStartPlayer.play();

        startGame.setOnMouseClicked(mouseEvent -> {
            if((mouseEvent.getX() > 370 && mouseEvent.getX() < 681) &&
            (mouseEvent.getY() > 387 && mouseEvent.getY() < 570) ) {
                gameController.start();
                musicStartPlayer.stop();
            }
            startGame.setOnMouseClicked(null);
        });

        primaryStage.setScene(startGame);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
