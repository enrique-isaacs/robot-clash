package za.co.wethinkcode.server.robotLab;

import za.co.wethinkcode.server.world.WORLD;

/**
 * A concrete implementation of the AbstractBot class representing a basic robot.
 * It inherits properties and behaviors from the AbstractBot class.
 */
public class BasicBot extends AbstractBot{

    /**
     * Constructs a BasicBot object.
     *
     * @param world The world in which the robot operates.
     * @param name  The name of the robot.
     */
    public BasicBot(WORLD world, String name) {
        super(world, name);
    }

    /**
     * Sets the type/make of the robot.
     *
     * @param make The type/make of the robot.
     */
    @Override
    protected void setType(String make) {
        this.model = make;
    }
}
