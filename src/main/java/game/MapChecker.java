package game;

import logic.GameObject;

import java.util.ArrayList;

public class MapChecker {

    private static int size;

    public static boolean checkMap(GameObject[][] tre, GameObject player) {
        ArrayList<Coord> arrayList = new ArrayList<>();
        Coord coord = new Coord(player.getX(), player.getY());

        size = tre.length;
        arrayList.add(coord);
        for (int i = 0; i < arrayList.size(); i++) {
            Coord cor = arrayList.get(i);
            if (checkEnd(tre, cor.x, cor.y)) {
                return true;
            }

            if (cor.x + 1 <= size - 1 &&
                    (tre[cor.x + 1][cor.y].getType() == GameObject.Type.EMPTY ||
                    tre[cor.x + 1][cor.y].getType() == GameObject.Type.ENEMY)) {
                Coord coord2 = new Coord(cor.x + 1, cor.y);
                if (!arrayList.contains(coord2)) {
                    arrayList.add(coord2);
                }
            }
            if (cor.y + 1 <= size - 1 &&
                    (tre[cor.x][cor.y + 1].getType() == GameObject.Type.EMPTY ||
                    tre[cor.x][cor.y + 1].getType() == GameObject.Type.ENEMY)) {
                Coord coord2 = new Coord(cor.x, cor.y + 1);
                if (!arrayList.contains(coord2)) {
                    arrayList.add(coord2);
                }
            }
            if (cor.x - 1 > 0 &&
                    (tre[cor.x - 1][cor.y].getType() == GameObject.Type.EMPTY ||
                    tre[cor.x - 1][cor.y].getType() == GameObject.Type.ENEMY)) {
                Coord coord2 = new Coord(cor.x - 1, cor.y);
                if (!arrayList.contains(coord2)) {
                    arrayList.add(coord2);
                }
            }
            if (cor.y - 1 > 0 &&
                    (tre[cor.x][cor.y - 1].getType() == GameObject.Type.EMPTY ||
                    tre[cor.x][cor.y - 1].getType() == GameObject.Type.ENEMY)) {
                Coord coord2 = new Coord(cor.x, cor.y - 1);
                if (!arrayList.contains(coord2)) {
                    arrayList.add(coord2);
                }
            }
        }
        return false;
    }

    private static boolean checkEnd(GameObject[][] tre, int x, int y) {
        return (x + 1 <= size - 1 && tre[x + 1][y].getType() == GameObject.Type.GOAL) ||
                (y + 1 <= size - 1 && tre[x][y + 1].getType() == GameObject.Type.GOAL) ||
                (x - 1 >= 0 && tre[x - 1][y].getType() == GameObject.Type.GOAL) ||
                (y - 1 >= 0 && tre[x][y - 1].getType() == GameObject.Type.GOAL);
    }
}
