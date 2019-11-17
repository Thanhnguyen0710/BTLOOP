package thegame.entity.Bullet;

import thegame.GameField;
import thegame.entity.GameEntity;
import thegame.entity.enemy.AbstractEnemy;

public abstract class AbstractBullet implements GameEntity {
    private double posX;
    private double posY;
    private double deltaX;
    private double deltaY;
    private double speed;
    private int strength;
    private boolean destroy = false;

    public double getSpeed() {
        return speed;
    }

    public int getStrength() {
        return strength;
    }

    public AbstractBullet(double posX, double posY, double deltaX, double deltaY, double speed, int strength) {
        this.posX = posX;
        this.posY = posY;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.speed = speed;
        this.strength = strength;
        final double normalize = speed / Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        this.deltaX = deltaX * normalize;
        this.deltaY = deltaY * normalize;
    }

    @Override
    public int getPosX() {
        return (int)posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    @Override
    public int getPosY() {
        return (int)posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public double getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    public double getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(int deltaY) {
        this.deltaY = deltaY;
    }

    public void move() {
        posX += deltaX;
        posY += deltaY;
    }

    public void collision(GameField field) {
        for (int i = 0 ; i < field.getEntities().size() ; i++) {
            if ((posX > field.getEntities().get(i).getPosX() + 10 && posX < field.getEntities().get(i).getPosX() + 40 )
                    && (posY > field.getEntities().get(i).getPosY()  && posY < field.getEntities().get(i).getPosY() + 50 ))
                if (field.getEntities().get(i) instanceof AbstractEnemy && !destroy) {
                    ((AbstractEnemy)field.getEntities().get(i)).setHealth(strength);
                    destroy = true;
                }
        }
    }

    public boolean onDestroy() {
        if (destroy == true) return true;
        else if (posX <= 5 || posX >= 1000 || posY <= 5 || posY >= 700)
            return true;
        else
            return false;
    }
}
