package logic;

import java.util.ArrayList;

public class ChaseLogic {

    private static int size;

    public static Integer[] findEnemyPath(GameObject[][] tre, GameObject enemy) {
        ArrayList<Coord2> arrayList = new ArrayList<>();
        Coord2 Coord2 = new Coord2(enemy.getX(), enemy.getY());
        ArrayList<Coord2> path = new ArrayList<>();

        size = tre.length;
        arrayList.add(Coord2);
        for (int i = 0; i < arrayList.size(); i++) {
            Coord2 cor = arrayList.get(i);
            if (checkEnd(tre, cor.x, cor.y)) {
                while (!cor.previousCord.equals(Coord2)) {
                    cor = cor.previousCord;
                }
                Integer[] ret = new Integer[2];
                ret[0] = cor.x;
                ret[1] = cor.y;
                return ret;
            }

            if (cor.x + 1 <= size - 1 &&
                    (tre[cor.x + 1][cor.y].getType() == GameObject.Type.EMPTY ||
                    tre[cor.x + 1][cor.y].getType() == GameObject.Type.PLAYER)) {
                Coord2 Coord22 = new Coord2(cor.x + 1, cor.y);
                Coord22.setPreviousCord(cor);
                if (!arrayList.contains(Coord22)) {
                    arrayList.add(Coord22);
                }
            }
            if (cor.y + 1 <= size - 1 &&
                    (tre[cor.x][cor.y + 1].getType() == GameObject.Type.EMPTY ||
                    tre[cor.x][cor.y + 1].getType() == GameObject.Type.PLAYER)) {
                Coord2 Coord22 = new Coord2(cor.x, cor.y + 1);
                Coord22.setPreviousCord(cor);
                if (!arrayList.contains(Coord22)) {
                    arrayList.add(Coord22);
                }
            }
            if (cor.x - 1 >= 0 &&
                    (tre[cor.x - 1][cor.y].getType() == GameObject.Type.EMPTY ||
                    tre[cor.x - 1][cor.y].getType() == GameObject.Type.PLAYER)) {
                Coord2 Coord22 = new Coord2(cor.x - 1, cor.y);
                Coord22.setPreviousCord(cor);
                if (!arrayList.contains(Coord22)) {
                    arrayList.add(Coord22);
                }
            }
            if (cor.y - 1 >= 0 &&
                    (tre[cor.x][cor.y - 1].getType() == GameObject.Type.EMPTY ||
                    tre[cor.x][cor.y - 1].getType() == GameObject.Type.PLAYER)) {
                Coord2 Coord22 = new Coord2(cor.x, cor.y - 1);
                Coord22.setPreviousCord(cor);
                if (!arrayList.contains(Coord22)) {
                    arrayList.add(Coord22);
                }
            }
        }
        return null;
    }

    private static boolean checkEnd(GameObject[][] tre, int x, int y) {
        return tre[x][y].getType() == GameObject.Type.PLAYER;
    }
}
