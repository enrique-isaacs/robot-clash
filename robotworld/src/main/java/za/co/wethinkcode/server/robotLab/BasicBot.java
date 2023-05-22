package za.co.wethinkcode.server.robotLab;


import za.co.wethinkcode.server.world.WORLD;

public class BasicBot extends AbstractBot{
    public BasicBot(WORLD world, String name) {
        super(world, name);

    }

    @Override
    protected void setType(String make) {
        this.model = make;
    }
}
