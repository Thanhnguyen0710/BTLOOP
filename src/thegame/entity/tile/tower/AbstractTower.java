package thegame.entity.tile.tower;

import thegame.GameField;
import thegame.entity.enemy.AbstractEnemy;
import thegame.entity.tile.Tower;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTower implements Tower {
    private int posX;
    private int posY;
    private double range;
    private int speed;

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public AbstractTower(int posX, int posY, double range, int speed) {
        this.posX = posX;
        this.posY = posY;
        this.range = range;
        this.speed = speed;
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }

    public AbstractEnemy getFindEnemy(GameField field){
        List<AbstractEnemy> target = new ArrayList<AbstractEnemy>();
        for(int i = 0; i < field.getEntities().size() ; i++) {
            if(field.getEntities().get(i) instanceof AbstractEnemy){
                AbstractEnemy a = (AbstractEnemy)field.getEntities().get(i);
                if (Math.sqrt((a.getPosX()-posX*50)*(a.getPosX()-posX*50) + (a.getPosY()-posY*50)*(a.getPosY()-posY*50)) <= range*50){
                    target.add(a);
                }
            }
        }
        if(target.size() == 0) return null;
        else return target.get(0);
    }
}
