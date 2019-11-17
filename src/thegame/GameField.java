package thegame;

import thegame.entity.Bullet.AbstractBullet;
import thegame.entity.Bullet.MachineGunBullet;
import thegame.entity.Bullet.NormalBullet;
import thegame.entity.Bullet.SniperBullet;
import thegame.entity.Config;
import thegame.entity.GameEntity;
import thegame.entity.enemy.*;
import thegame.entity.tile.road.Spawner;
import thegame.entity.tile.tower.AbstractTower;
import thegame.entity.tile.tower.MachineGunTower;
import thegame.entity.tile.tower.NormalTower;
import thegame.entity.tile.tower.SniperTower;

import java.util.ArrayList;
import java.util.List;

public class GameField {
    public static int[][] gameFeild = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };
    private int coin = 100;
    private int health = 20;

    private List<GameEntity> entities = new ArrayList<GameEntity>();

    private Spawner spawner = new Spawner();

    public List<GameEntity> getEntities() {
        return entities;
    }

    public void setEntities(List<GameEntity> entities) {
        this.entities = entities;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public void doSpawnNormalTower(int posX,int posY){
        entities.add(new NormalTower(posX,posY));
    }

    public void doSpawnSniperTower(int posX,int posY){
        entities.add(new SniperTower(posX,posY));
    }

    public void doSpawnMachineGunTower(int posX,int posY){
        entities.add(new MachineGunTower(posX,posY));
    }

    public void doSpawnNormalEnemy() {
        entities.add(new NormalEnemy(spawner.getPosX(),spawner.getPosY()));
    }

    public void doSpawnSmallerEnemy(){
        entities.add(new SmallerEnemy(spawner.getPosX(),spawner.getPosY()));
    }

    public void doSpawnTankerEnemy(){
        entities.add(new TankerEnemy(spawner.getPosX(),spawner.getPosY()));
    }

    public void doSpawnBossEnemy(){
        entities.add(new BossEnemy(spawner.getPosX(),spawner.getPosY()));
    }

    public void doSpawnNormalBullet(double posX,double posY,double deltaX, double deltaY){
        entities.add(new NormalBullet(posX, posY, deltaX, deltaY));
    }

    public void doSpawnSniperBullet(double posX,double posY,double deltaX, double deltaY){
        entities.add(new SniperBullet(posX, posY, deltaX, deltaY));
    }

    public void doSpawnMachineGunBullet(double posX,double posY,double deltaX, double deltaY){
        entities.add(new MachineGunBullet(posX, posY, deltaX, deltaY));
    }

    public void doDestroy(GameRender gameRender){
        for(int i = 0 ; i < entities.size() ; i++){
            if(entities.get(i) instanceof AbstractEnemy ){
                if (((AbstractEnemy) entities.get(i)).onDestroy(this)){
                    coin += ((AbstractEnemy) entities.get(i)).getReward();
                    entities.remove(i);
                    gameRender.getPane().getChildren().remove(gameRender.getImageViewList().get(i));
                    gameRender.getImageViewList().remove(i);
                }
            }
            else if(entities.get(i) instanceof AbstractBullet ){
                if (((AbstractBullet) entities.get(i)).onDestroy()){
                    entities.remove(i);
                    gameRender.getPane().getChildren().remove(gameRender.getImageViewList().get(i));
                    gameRender.getImageViewList().remove(i);
                }
            }
        }
    }

    public boolean gameOver(){
        if (health <= 0) {
            entities.clear();
            return true;
        }
        return false;
    }

    public void moveEnemy(){
        for (int i = 0 ; i < entities.size() ; i++) {
            if (entities.get(i) instanceof AbstractEnemy)
                ((AbstractEnemy) entities.get(i)).move(this);
        }
    }

    public void moveBullet(){
        for (int i = 0 ; i < entities.size() ; i++){
            if (entities.get(i) instanceof AbstractBullet) {
                ((AbstractBullet) entities.get(i)).move();
                ((AbstractBullet) entities.get(i)).collision(this);
            }
        }
    }

    public void sell(int X, int Y,GameRender gameRender) {
        int x = (int)X/50;
        int y = (int)Y/50;
        for (int i = 0 ; i < entities.size() ; i++)
            if(entities.get(i).getPosX() == x && entities.get(i).getPosY() == y) {
                if (entities.get(i) instanceof NormalTower)
                    coin += Config.NORMAL_TOWER_COIN/2;

                if (entities.get(i) instanceof SniperTower)
                    coin += Config.SNIPER_TOWER_COIN/2;

                if (entities.get(i) instanceof MachineGunTower)
                    coin += Config.MACHINEGUN_TOWER_COIN/2;

                if (entities.get(i) instanceof AbstractTower)
                    entities.remove(i);
                    gameRender.getPane().getChildren().remove(gameRender.getImageViewList().get(i));
                    gameRender.getImageViewList().remove(i);
            }
    }

    public void spawnerBullet() {
        for (int i = 0 ; i < entities.size() ; i++) {
            if (entities.get(i) instanceof AbstractTower) {
                AbstractEnemy abstractEnemy = ((AbstractTower)entities.get(i)).getFindEnemy(this);
                if (abstractEnemy != null && entities.get(i) instanceof NormalTower)
                    doSpawnNormalBullet(entities.get(i).getPosX()*50 + 25,entities.get(i).getPosY()*50 + 25,
                                              abstractEnemy.getPosX() - (entities.get(i).getPosX()*50 + 25),
                                                     abstractEnemy.getPosY() - (entities.get(i).getPosY()*50 + 25));

                if (abstractEnemy != null && entities.get(i) instanceof SniperTower)
                    doSpawnSniperBullet(entities.get(i).getPosX()*50 + 25,entities.get(i).getPosY()*50 + 25,
                            abstractEnemy.getPosX() - (entities.get(i).getPosX()*50 + 25),
                            abstractEnemy.getPosY() - (entities.get(i).getPosY()*50 + 25));

                if (abstractEnemy != null && entities.get(i) instanceof MachineGunTower)
                    doSpawnMachineGunBullet(entities.get(i).getPosX()*50 + 25,entities.get(i).getPosY()*50 + 25,
                            abstractEnemy.getPosX() - (entities.get(i).getPosX()*50 + 25),
                            abstractEnemy.getPosY() - (entities.get(i).getPosY()*50 + 25));
            }
        }
    }

    public boolean isVictory() {
        for (int i = 0 ; i < entities.size() ; i++)
            if (entities.get(i) instanceof AbstractEnemy) return false;

        return true;
    }
}
