package thegame.entity.enemy;

import thegame.entity.Config;

public class NormalEnemy extends AbstractEnemy{
    public NormalEnemy(int posX, int posY) {
        super( posX, posY, Config.NORMAL_ENEMY_SHIELD,Config.NORMAL_ENEMY_HEALTH,Config.NORMAL_ENEMY_REWARD, Config.NORMAL_ENEMY_MOVESPEED, true);
    }
}
