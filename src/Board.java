/**
 * Created by codyboppert on 10/25/14.
 */
public class Board {
    private final Tile[][] layout;

    public Board(Tile[][] layout) {
        for (int i = 0; i < layout.length; i++) {
            if (layout[i].length != layout.length) {
                throw new RuntimeException("Non Square board created.");
            }
        }
        this.layout = layout;
    }

    public Tile[][] getLayout() { return layout; }

    //Each match of bottom and top with the same color adds one to the fitness counter
    public int getFitness() {
        int fitness = 0;
        int n = layout.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Tile tile = layout[i][j];

                //Only checking east and south ensures no double counting of matches when moving from left to right
                // and top to bottom through the board
                //Check east edge
                Part eastEdge = tile.getEastEdge();
                if (eastEdge.isTop() && j + 1 < n) {
                    Part partToEast = layout[i][j + 1].getWestEdge();
                    if (!partToEast.isTop() && partToEast.getColor() == eastEdge.getColor()) {
                        fitness++;
                    }
                }

                //Check south edge
                Part southEdge = tile.getSouthEdge();
                if (southEdge.isTop() && i + 1 < n) {
                    Part partToSouth = layout[i + 1][j].getNorthEdge();
                    if (!partToSouth.isTop() && partToSouth.getColor() == southEdge.getColor()) {
                        fitness++;
                    }
                }
            }
        }

        return fitness;
    }
}
