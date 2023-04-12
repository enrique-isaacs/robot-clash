package za.co.wethinkcode.server.world;

import za.co.wethinkcode.server.world.Position;

public class SquareObstacle implements Obstacle{

    int x,y,size;
    public SquareObstacle(int x, int y){
        this.x = x;
        this.y = y;
        this.size = 5;

    }


    @Override
    public int getBottomLeftX() {
        return this.x;
    }

    @Override
    public int getBottomLeftY() {
        return this.y;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean blocksPosition(Position position) {
        boolean inRangeOfX = this.x <= position.getX() && position.getX() <= this.x+getSize()-1;
        boolean inRangeOfY = this.y <= position.getY() && position.getY() <= this.y+getSize()-1;
        return inRangeOfX && inRangeOfY;

    }

    @Override
    public boolean blocksPath(Position a, Position b) {
        int x1 = a.getX();
        int x2 = b.getX();
        int y1 = a.getY();
        int y2 = b.getY();

        return isPathBlocked(x1, y1, x2, y2);
    }

    public boolean isPathBlocked(int x1, int y1, int x2, int y2) {
        boolean pathBlocked = false;

        if (x1 == x2) {
            for (int i = Math.min(y1, y2) + 1; i < Math.max(y1, y2); i++) {
                if (blocksPosition(new Position(x2, i))) {
                    pathBlocked = true;
                    break;
                }
            }
        } else if (y1 == y2) {
            for (int i = Math.min(x1, x2) + 1; i < Math.max(x1, x2); i++) {
                if (blocksPosition(new Position(i, y2))) {
                    pathBlocked = true;
                    break;
                }
            }
        }

        return pathBlocked;

    }

}

