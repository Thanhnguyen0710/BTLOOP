package thegame.entity.tile.tower;

import thegame.entity.Config;

public class SniperTower extends AbstractTower {
    public SniperTower(int posX, int posY) {
        super(posX, posY, Config.SNIPER_TOWER_RANGE,Config.SNIPER_TOWER_SPEED);
    }
}
