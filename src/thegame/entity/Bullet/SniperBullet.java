package thegame.entity.Bullet;

import thegame.entity.Config;

public class SniperBullet extends AbstractBullet {
    public SniperBullet(double posX, double posY, double deltaX, double deltaY) {
        super(posX, posY, deltaX, deltaY, Config.SNIPER_BULLET_SPEED,Config.SNIPER_BULLET_STRENGTH);
    }
}
