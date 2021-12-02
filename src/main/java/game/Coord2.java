package logic;

import java.util.Objects;

public class Coord2 {
    int x;
    int y;
    Coord2 previousCord;

    public Coord2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setPreviousCord(Coord2 previousCord) {
        this.previousCord = previousCord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coord2 Coord2 = (Coord2) o;
        return x == Coord2.x &&
                y == Coord2.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
