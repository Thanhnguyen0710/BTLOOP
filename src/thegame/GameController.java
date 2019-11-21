package thegame;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import thegame.entity.Config;

import java.io.File;


public class GameController extends AnimationTimer {
    private Stage primaryStage;

    private Image gameStart = new Image("/image/background/background.png");
    private ImageView gameStartView = new ImageView(gameStart);

    private Image normal_tower = new Image("/image/tower/normal_tower.png");
    private ImageView normal_towerView = new ImageView(normal_tower);

    private Image sniper_tower = new Image("/image/tower/sniper_tower.png");
    private ImageView sniper_towerView = new ImageView(sniper_tower);

    private Image machinegun_tower = new Image("/image/tower/machinegun_tower.png");
    private ImageView machinegun_towerView = new ImageView(machinegun_tower);

    private Image gameOver = new Image("/image/background/gameover.jpg");
    private ImageView gameOverView = new ImageView(gameOver);

    private Image victory = new Image("/image/background/victory.png");
    private ImageView victoryView = new ImageView(victory);
/*
    private Media musicBackground = new Media(new File("/music/background.mp3").toURI().toString());
    private MediaPlayer musicBackgroundPlayer = new MediaPlayer(musicBackground);

    private Media musicGameOver = new Media(new File("/music/gameover.mp3").toURI().toString());
    private MediaPlayer musicGameOverPlayer = new MediaPlayer(musicGameOver);

    private Media musicVictory = new Media(new File("/music/victory.mp3").toURI().toString());
    private MediaPlayer musicVictoryPlayer = new MediaPlayer(musicVictory);
*/
    private Button normal = new Button();
    private Button sniper = new Button();
    private Button machinegun = new Button();
    private Button play = new Button("START");
    private Button sell = new Button("SELL");
    private Button pause = new Button("PAUSE");

    private Pane pane = new Pane();
    private Pane over = new Pane();
    private Scene startGame = new Scene(pane,1200,700);
    private Scene overGame = new Scene(over,1050,700);

    private GameField field = new GameField();

    private int tick = 0;

    private int level = 0;
    private int i = 0;

    private GameRender render = new GameRender(field,pane);

    public GameController (Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    @Override
    public void handle(long l) {
        if (level == 4 && i == 30 && field.isVictory()) {
  //          musicBackgroundPlayer.stop();
    //        musicVictoryPlayer.play();
            pane.getChildren().add(victoryView);
            stop();
        }
        else if (!field.gameOver()) {
            tick += 1;
            field.moveBullet();

            if (level == 1 && i == 7 && field.isVictory()) {
                pane.getChildren().add(machinegun);
                i++;
            }

            if (level == 2 && i == 14 && field.isVictory()) {
                pane.getChildren().add(sniper);
                i++;
            }

            if (tick > 0 && tick % 5 == 0) {
                field.moveEnemy();
                field.doDestroy(render);
            }

            field.spawnerBullet(tick);

            if (tick == 90) {
                if (level == 1) {
                    if ( i < 7) {
                        if (i == 4 || i == 6)
                            field.doSpawnSmallerEnemy();
                        else
                            field.doSpawnNormalEnemy();
                        i++;
                    }
                }

                if (level == 2) {
                    if (i < 14) {
                        if (i == 10) field.doSpawnTankerEnemy();
                        else if (i == 13) field.doSpawnBossEnemy();
                        else if (i % 2 == 0) field.doSpawnNormalEnemy();
                        else field.doSpawnSmallerEnemy();
                        i++;
                    }
                }

                if (level == 3) {
                    if (i < 20) {
                        if (i == 10 || i == 15) field.doSpawnTankerEnemy();
                        else if (i == 13 || i == 19) field.doSpawnBossEnemy();
                        else if (i % 2 == 0) field.doSpawnNormalEnemy();
                        else field.doSpawnSmallerEnemy();
                        i++;
                    }
                }

                if (level == 4) {
                    if (i < 30) {
                        if (i == 10 || i == 15 || i == 23 || i == 27) field.doSpawnTankerEnemy();
                        else if (i == 13 || i == 19 || i == 29) field.doSpawnBossEnemy();
                        else if (i % 2 == 0) field.doSpawnNormalEnemy();
                        else field.doSpawnSmallerEnemy();
                        i++;
                    }
                }

                tick = 0;
            }

            normal.setOnAction(actionEvent -> {
                Image image = new Image("/image/tower/normal_tower.png");
                ImageView imageView = new ImageView(image);
                imageView.setLayoutX(1100);
                imageView.setLayoutY(50);
                if (field.getCoin() >= Config.NORMAL_TOWER_COIN) {
                    pane.getChildren().add(imageView);
                    startGame.setOnMouseMoved(mouseEvent -> {
                        imageView.setLayoutX(mouseEvent.getX() - 25);
                        imageView.setLayoutY(mouseEvent.getY() - 25);
                        startGame.setOnMouseClicked(mouseEvent1 -> {
                            int X = (int) mouseEvent1.getX();
                            int Y = (int) mouseEvent1.getY();
                            if (X < 1050 && field.gameFeild[(int) Y / 50][(int) X / 50] != 0) {
                                field.doSpawnNormalTower((int) X / 50, (int) Y / 50);
                                field.setCoin(field.getCoin() - Config.NORMAL_TOWER_COIN);
                                pane.getChildren().remove(imageView);
                                startGame.setOnMouseClicked(null);
                                startGame.setOnMouseMoved(null);
                            }
                        });
                    });
                }
            });

            sniper.setOnAction(actionEvent -> {
                Image image = new Image("/image/tower/sniper_tower.png");
                ImageView imageView = new ImageView(image);
                imageView.setLayoutX(1100);
                imageView.setLayoutY(250);
                if (field.getCoin() >= Config.SNIPER_TOWER_COIN) {
                    pane.getChildren().add(imageView);
                    startGame.setOnMouseMoved(mouseEvent -> {
                        imageView.setLayoutX(mouseEvent.getX() - 25);
                        imageView.setLayoutY(mouseEvent.getY() - 25);
                        startGame.setOnMouseClicked(mouseEvent1 -> {
                            int X = (int) mouseEvent1.getX();
                            int Y = (int) mouseEvent1.getY();
                            if (X < 1050 && field.gameFeild[(int) Y / 50][(int) X / 50] != 0) {
                                field.doSpawnSniperTower((int) X / 50, (int) Y / 50);
                                field.setCoin(field.getCoin() - Config.SNIPER_TOWER_COIN);
                                pane.getChildren().remove(imageView);
                                startGame.setOnMouseClicked(null);
                                startGame.setOnMouseMoved(null);
                            }
                        });
                    });
                }
            });

            machinegun.setOnAction(actionEvent -> {
                Image image = new Image("/image/tower/machinegun_tower.png");
                ImageView imageView = new ImageView(image);
                imageView.setLayoutX(1100);
                imageView.setLayoutY(150);
                if (field.getCoin() >= Config.MACHINEGUN_TOWER_COIN) {
                    pane.getChildren().add(imageView);
                    startGame.setOnMouseMoved(mouseEvent -> {
                        imageView.setLayoutX(mouseEvent.getX() - 25);
                        imageView.setLayoutY(mouseEvent.getY() - 25);
                        startGame.setOnMouseClicked(mouseEvent1 -> {
                            int X = (int) mouseEvent1.getX();
                            int Y = (int) mouseEvent1.getY();
                            if (X < 1050 && field.gameFeild[(int) Y / 50][(int) X / 50] != 0) {
                                field.doSpawnMachineGunTower((int) X / 50, (int) Y / 50);
                                field.setCoin(field.getCoin() - Config.MACHINEGUN_TOWER_COIN);
                                pane.getChildren().remove(imageView);
                                startGame.setOnMouseClicked(null);
                                startGame.setOnMouseMoved(null);
                            }
                        });
                    });
                }
            });

            play.setOnAction(actionEvent -> {
                if ((level == 0 || (level == 1 && i >= 7) || (level == 2 && i >= 14) || (level == 3 && i >= 20)) && field.isVictory()) {
                    level++;
                    i = 0;
                }
                play.setText("NEXT LEVEL");
            });

            sell.setOnAction(actionEvent -> {
                startGame.setOnMouseClicked(mouseEvent -> {
                    field.sell((int)mouseEvent.getX(),(int)mouseEvent.getY(),render);
                    startGame.setOnMouseClicked(null);
                });
            });

            pause.setOnAction(actionEvent -> {
                if (pause.getText().equals("PAUSE")) {
                    pause.setText("PLAY");
                    stop();
                }
                else {
                    pause.setText("PAUSE");
                    super.start();
                }
            });

            render.render();
        }
        else {
      //      musicBackgroundPlayer.stop();
        //    musicGameOverPlayer.play();
            primaryStage.setScene(overGame);
            stop();
        }
    }

    public void start(){
        //musicBackgroundPlayer.play();

        over.getChildren().add(gameOverView);
        pane.getChildren().addAll(gameStartView,normal,play,sell,pause);

        normal.setGraphic(normal_towerView);
        sniper.setGraphic(sniper_towerView);
        machinegun.setGraphic(machinegun_towerView);

        pause.setLayoutX(1100);
        pause.setLayoutY(530);

        sell.setLayoutX(1100);
        sell.setLayoutY(450);

        play.setLayoutX(1100);
        play.setLayoutY(370);

        normal.setLayoutX(1100);
        normal.setLayoutY(50);

        sniper.setLayoutX(1100);
        sniper.setLayoutY(250);

        machinegun.setLayoutX(1100);
        machinegun.setLayoutY(150);

        primaryStage.setScene(startGame);
        super.start();
    }
}
