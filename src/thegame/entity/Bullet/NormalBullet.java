package thegame.entity.Bullet;

import thegame.entity.Config;

public class NormalBullet extends AbstractBullet {
    public NormalBullet(double posX, double posY, double deltaX, double deltaY) {
        super(posX, posY, deltaX, deltaY, Config.NORMAL_BULLET_SPEED,Config.NORMAL_BULLET_STRENGTH);
    }
}
