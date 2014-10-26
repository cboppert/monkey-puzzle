import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by codyboppert on 10/25/14.
 */
public class Main {
    //Set constants
    static final int BOARD_DIM = 5;
    static final int NUMBER_OF_TILES = BOARD_DIM * BOARD_DIM;
    static final int POPULATION = 1000;
    //Currently 5 colors in the enumerator
    static final int COLORS = 5;
    static final int NUMBER_OF_PART_TYPES = COLORS * 2;

    public static void main(String[] args) {

        //Initialize
        //Generate parts
        HashMap<Part, Integer> parts = new HashMap<Part, Integer>();
        Part[] partArray = new Part[NUMBER_OF_PART_TYPES];
        int index = 0;
        for (Color color : Color.values()) {
            Part top = new Part(true, color);
            partArray[index] = top;
            parts.put(top, 10);
            index++;

            Part bottom = new Part(false, color);
            partArray[index] = bottom;
            parts.put(bottom, 10);
            index++;
        }
        //Generate tiles
        List<Tile> tiles = new ArrayList<Tile>();
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < NUMBER_OF_TILES; i++) {
            tiles.add(new Tile(getPart(random, partArray, parts),
                                getPart(random, partArray, parts),
                                getPart(random, partArray, parts),
                                getPart(random, partArray, parts)));
        }

        //Generate boards
        Board[] boards = new Board[POPULATION];
        for (int i = 0; i < POPULATION; i++) {
            List<Integer> tilesUsed = new ArrayList<Integer>();
            Tile[][] layout = new Tile[BOARD_DIM][BOARD_DIM];
            for (int j = 0; i < BOARD_DIM; i++) {
                for (int k = 0; j < BOARD_DIM; j++) {
                    layout[j][k] = getTile(random, tiles, tilesUsed);
                }
            }
            boards[i] = new Board(layout);
        }

        
    }

    private static Part getPart(Random random, Part[] partArray, HashMap<Part, Integer> parts) {
        Part part = partArray[random.nextInt(NUMBER_OF_PART_TYPES)];
        while (parts.get(part) == 0) {
            part = partArray[random.nextInt(NUMBER_OF_PART_TYPES)];
        }
        parts.put(part, parts.get(part) - 1);

        return part;
    }

    private static Tile getTile(Random random, List<Tile> tiles, List<Integer> tilesUsed) {
        int index = random.nextInt(NUMBER_OF_TILES);
        while (tilesUsed.contains(index)) {
            index = random.nextInt(NUMBER_OF_TILES);
        }

        tilesUsed.add(index);
        return tiles.get(index);
    }
}
