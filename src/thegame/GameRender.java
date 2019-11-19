package thegame;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import thegame.entity.Bullet.AbstractBullet;
import thegame.entity.Bullet.MachineGunBullet;
import thegame.entity.Bullet.NormalBullet;
import thegame.entity.Bullet.SniperBullet;
import thegame.entity.enemy.*;
import thegame.entity.tile.tower.MachineGunTower;
import thegame.entity.tile.tower.NormalTower;
import thegame.entity.tile.tower.SniperTower;

import java.util.ArrayList;
import java.util.List;

public class GameRender {
    private GameField field;

    private Pane pane;

    private final String normal_enemy = "/image/enemy/normal_enemy.png";

    private final String smaller_enemy = "/image/enemy/smaller_enemy.png";

    private final String tanker_enemy = "/image/enemy/tanker_enemy.png";

    private final String boss_enemy = "/image/enemy/boss_enemy.png";

    private final String normal_tower = "/image/tower/normal_tower.png";

    private final String sniper_tower = "/image/tower/sniper_tower.png";

    private final String machinegun_tower = "/image/tower/machinegun_tower.png";

    private final String normal_bullet = "/image/bullet/normal_bullet.png";

    private final String sniper_bullet = "/image/bullet/sniper_bullet.png";

    private final String machinegun_bullet = "/image/bullet/machinegun_bullet.png";

    private List<ImageView> imageViewList = new ArrayList<ImageView>();

    private Label labelCoin = new Label();

    private Label labelHealth = new Label();

    public GameRender(GameField field,Pane pane){
        this.pane = pane;
        this.field = field;
        pane.getChildren().addAll(labelCoin,labelHealth);
    }

    public Pane getPane() {
        return pane;
    }

    public List<ImageView> getImageViewList() {
        return imageViewList;
    }

    public void renderNormalEnemy(NormalEnemy normalEnemy){
        Image image = new Image(normal_enemy);
        ImageView imageView = new ImageView(image);
        imageView.setLayoutX(normalEnemy.getPosX());
        imageView.setLayoutY(normalEnemy.getPosY());
        imageViewList.add(imageView);
    }

    public void renderSmallerEnemy(SmallerEnemy smallerEnemy){
        Image image = new Image(smaller_enemy);
        ImageView imageView = new ImageView(image);
        imageView.setLayoutX(smallerEnemy.getPosX());
        imageView.setLayoutY(smallerEnemy.getPosY());
        imageViewList.add(imageView);
    }

    public void renderTankerEnemy(TankerEnemy tankerEnemy){
        Image image = new Image(tanker_enemy);
        ImageView imageView = new ImageView(image);
        imageView.setLayoutX(tankerEnemy.getPosX());
        imageView.setLayoutY(tankerEnemy.getPosY());
        imageViewList.add(imageView);
    }

    public void renderBossEnemy(BossEnemy bossEnemy){
        Image image = new Image(boss_enemy);
        ImageView imageView = new ImageView(image);
        imageView.setLayoutX(bossEnemy.getPosX());
        imageView.setLayoutY(bossEnemy.getPosY());
        imageViewList.add(imageView);
    }

    public void renderNormalTower(NormalTower normalTower){
        Image image = new Image(normal_tower);
        ImageView imageView = new ImageView(image);
        imageView.setLayoutX(normalTower.getPosX()*50);
        imageView.setLayoutY(normalTower.getPosY()*50);
        imageViewList.add(imageView);
    }

    public void renderSniperTower(SniperTower sniperTower){
        Image image = new Image(sniper_tower);
        ImageView imageView = new ImageView(image);
        imageView.setLayoutX(sniperTower.getPosX()*50);
        imageView.setLayoutY(sniperTower.getPosY()*50);
        imageViewList.add(imageView);
    }

    public void renderMachineGunTower(MachineGunTower machineGunTower){
        Image image = new Image(machinegun_tower);
        ImageView imageView = new ImageView(image);
        imageView.setLayoutX(machineGunTower.getPosX()*50);
        imageView.setLayoutY(machineGunTower.getPosY()*50);
        imageViewList.add(imageView);
    }

    public void renderNormalBullet(NormalBullet normalBullet){
        Image image = new Image(normal_bullet);
        ImageView imageView = new ImageView(image);
        imageView.setLayoutX(normalBullet.getPosX());
        imageView.setLayoutY(normalBullet.getPosY());
        imageViewList.add(imageView);
    }

    public void renderSniperBullet(SniperBullet sniperBullet){
        Image image = new Image(sniper_bullet);
        ImageView imageView = new ImageView(image);
        imageView.setLayoutX(sniperBullet.getPosX());
        imageView.setLayoutY(sniperBullet.getPosY());
        imageViewList.add(imageView);
    }

    public void renderMachineGunBullet(MachineGunBullet machineGunBullet){
        Image image = new Image(machinegun_bullet);
        ImageView imageView = new ImageView(image);
        imageView.setLayoutX(machineGunBullet.getPosX());
        imageView.setLayoutY(machineGunBullet.getPosY());
        imageViewList.add(imageView);
    }

    public void render(){
        for (int i = 0 ; i < imageViewList.size() ; i++){
            if(field.getEntities().get(i) instanceof AbstractEnemy) {
                if(field.getEntities().get(i).getPosX() != imageViewList.get(i).getLayoutX()
                 ||field.getEntities().get(i).getPosY() != imageViewList.get(i).getLayoutY())
                    imageViewList.get(i).setLayoutX(field.getEntities().get(i).getPosX());
                    imageViewList.get(i).setLayoutY(field.getEntities().get(i).getPosY());
            }

            if(field.getEntities().get(i) instanceof AbstractBullet) {
                if(field.getEntities().get(i).getPosX() != imageViewList.get(i).getLayoutX()
                        ||field.getEntities().get(i).getPosY() != imageViewList.get(i).getLayoutY())
                    imageViewList.get(i).setLayoutX(field.getEntities().get(i).getPosX());
                imageViewList.get(i).setLayoutY(field.getEntities().get(i).getPosY());
            }
        }

        for(int i = imageViewList.size() ; i < field.getEntities().size(); i++) {
            if (field.getEntities().get(i) instanceof NormalEnemy)
                renderNormalEnemy((NormalEnemy)field.getEntities().get(i));

            if (field.getEntities().get(i) instanceof SmallerEnemy)
                renderSmallerEnemy((SmallerEnemy)field.getEntities().get(i));

            if (field.getEntities().get(i) instanceof TankerEnemy)
                renderTankerEnemy((TankerEnemy) field.getEntities().get(i));

            if (field.getEntities().get(i) instanceof BossEnemy)
                renderBossEnemy((BossEnemy)field.getEntities().get(i));

            if (field.getEntities().get(i) instanceof NormalTower)
                renderNormalTower((NormalTower) field.getEntities().get(i));

            if (field.getEntities().get(i) instanceof SniperTower)
                renderSniperTower((SniperTower) field.getEntities().get(i));

            if (field.getEntities().get(i) instanceof MachineGunTower)
                renderMachineGunTower((MachineGunTower) field.getEntities().get(i));

            if (field.getEntities().get(i) instanceof NormalBullet)
                renderNormalBullet((NormalBullet) field.getEntities().get(i));

            if (field.getEntities().get(i) instanceof SniperBullet)
                renderSniperBullet((SniperBullet) field.getEntities().get(i));

            if (field.getEntities().get(i) instanceof MachineGunBullet)
                renderMachineGunBullet((MachineGunBullet) field.getEntities().get(i));

            pane.getChildren().add(imageViewList.get(i));
        }

        labelHealth.setText("HEALTH: " + field.getHealth());
        labelHealth.setTextFill(Color.RED);
        labelHealth.setLayoutX(1100);
        labelHealth.setLayoutY(600);

        labelCoin.setText("COIN: " + field.getCoin());
        labelCoin.setLayoutX(1100);
        labelCoin.setLayoutY(650);
    }
}
