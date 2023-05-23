package za.co.wethinkcode.server.world;

/**
 * The Position class represents a two-dimensional position in the game world.
 */
public class Position {
    private final int x;
    private final int y;

    /**
     * Constructs a Position object with the specified x and y coordinates.
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x coordinate of the position.
     *
     * @return The x coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y coordinate of the position.
     *
     * @return The y coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Checks if the current position is within the specified bounding box defined by the top left and bottom right positions.
     *
     * @param topLeft     The top left position of the bounding box.
     * @param bottomRight The bottom right position of the bounding box.
     * @return `true` if the position is within the bounding box, `false` otherwise.
     */
    public boolean isIn(Position topLeft, Position bottomRight) {
        boolean withinTop = this.y <= topLeft.getY();
        boolean withinBottom = this.y >= bottomRight.getY();
        boolean withinLeft = this.x >= topLeft.getX();
        boolean withinRight = this.x <= bottomRight.getX();
        return withinTop && withinBottom && withinLeft && withinRight;
    }

    /**
     * Compares the current position with another object for equality.
     *
     * @param o The object to compare.
     * @return `true` if the positions are equal, `false` otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        return y == position.y;
    }
}
