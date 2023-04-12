package za.co.wethinkcode.server.robotLab;

import za.co.wethinkcode.server.world.IWorld;

public class DefaultBot extends AbstractBot{
    public DefaultBot(String name) {
        super(name);
    }

    public DefaultBot(String name, IWorld world) {
        super(name, world);
    }
}
