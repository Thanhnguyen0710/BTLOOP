package thegame.entity.enemy;

import thegame.GameField;
import thegame.entity.tile.road.Target;

public abstract class AbstractEnemy implements Enemy {
    private int shield;
    private int posX;
    private int posY;
    private int health;
    private int reward;
    private double moveSpeed;
    private boolean enemyDead;

    public AbstractEnemy(int posX, int posY,int shield, int health, int reward, double moveSpeed, boolean enemyDead) {
        this.shield = shield;
        this.posX = posX;
        this.posY = posY;
        this.health = health;
        this.reward = reward;
        this.moveSpeed = moveSpeed;
        this.enemyDead = enemyDead;
    }

    @Override
    public int getShield() {
        return shield;
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }

    @Override
    public int getHealth() {
        return health;
    }

    public void setHealth(int strength) {
        this.health = health - (strength-shield);
    }

    @Override
    public int getReward() {
        return reward;
    }

    @Override
    public double getMoveSpeed() {
        return moveSpeed;
    }

    @Override
    public boolean isEnemyDead() {
        return enemyDead;
    }

    @Override
    public boolean onDestroy(GameField field) {
        Target target = new Target();
        if(health <= 0 ) return true;
        else if (posX == target.getPosX() && posY == target.getPosY()) {
            field.setHealth(field.getHealth()-1);
            return true;
        }
        else return false;
    }

    public void move(GameField field){
        if((posY == 350 && posX < 150) || (posY == 150 && posX < 350) || (posY == 430 && posX < 650) || (posY == 300 && posX < 1000 && posX >= 650)){
            posX += moveSpeed;
        }
        else if(posX == 150 || posX == 650){
            posY -= moveSpeed;
        }
        else if(posX == 350)
            posY += moveSpeed;
    }
}
