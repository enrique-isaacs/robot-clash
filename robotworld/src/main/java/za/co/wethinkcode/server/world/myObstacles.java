package za.co.wethinkcode.server.world;

public class myObstacles implements Obstacle {
    private int bottomLeftX;
    private int bottomLeftY;

    public myObstacles(int x, int y){
        this.bottomLeftY = y;
        this.bottomLeftX = x;
    }

    @Override
    public int getBottomLeftY() {
        return this.bottomLeftY;
    }

    @Override
    public int getBottomLeftX() {
        return this.bottomLeftX;
    }

    @Override
    public int getSize() {
        return 5;
    }

    @Override
    public boolean blocksPosition(Position position) {
        return (position.getX() >= bottomLeftX && position.getX() < bottomLeftX + 5) &&
                (position.getY() == bottomLeftY);
    }

    @Override
    public boolean blocksPath(Position a, Position b) {
        int minX = Math.min(a.getX(), b.getX());
        int maxX = Math.max(a.getX(), b.getX());
        int minY = Math.min(a.getY(), b.getY());
        int maxY = Math.max(a.getY(), b.getY());

        if (a.getX() == b.getX()) {
            for (int y = minY; y <= maxY; y++) {
                if (blocksPosition(new Position(a.getX(), y))) {
                    return true;
                }
            }
        } else if (a.getY() == b.getY()) {
            for (int x = minX; x <= maxX; x++) {
                if (blocksPosition(new Position(x, a.getY()))) {
                    return true;
                }
            }
        }

        return false;
    }
}
