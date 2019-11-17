package thegame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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

        startGame.setOnMouseClicked(mouseEvent -> {
            if((mouseEvent.getX() > 370 && mouseEvent.getX() < 681) &&
            (mouseEvent.getY() > 387 && mouseEvent.getY() < 570) ) {
                gameController.start();
            }
            startGame.setOnMouseClicked(null);
        });

        primaryStage.setScene(startGame);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
