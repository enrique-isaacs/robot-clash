package za.co.wethinkcode.server.robotLab;

import za.co.wethinkcode.server.world.WORLD;

public class SniperBot extends AbstractBot{
    public SniperBot(WORLD world, String name) {
        super(world, name);
        setType("sniper");
    }

    @Override
    protected void setType(String make) {
        this.model = make;
    }
}
