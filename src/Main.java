import com.sun.org.apache.bcel.internal.generic.POP;

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
        long start = System.currentTimeMillis();
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
        long partsGenerated = System.currentTimeMillis();
        System.out.println("Parts generated in " + (partsGenerated - start)/1000.0);
        //Generate tiles
        Board[] boards = new Board[POPULATION];
        Random random = new Random(System.currentTimeMillis());

        for (int i = 0; i < BOARD_DIM; i++) {
            for (int j = 0; j < BOARD_DIM; j++) {
                Tile tile = new Tile(getPart(random, partArray, parts),
                                     getPart(random, partArray, parts),
                                     getPart(random, partArray, parts),
                                     getPart(random, partArray, parts),
                                     (j * i) + i);
                for (int k = 0; k < POPULATION; k++) {
                    if (i == 0 && j == 0) {
                        boards[k] = new Board(BOARD_DIM);
                    }
                    boards[k].setTile(i, j, tile);
                }
            }
        }
        for (int i = 0; i < BOARD_DIM; i++) {
            boards[i].shuffle(random);
        }
        long tilesGenerated = System.currentTimeMillis();
        System.out.println("Tiles generated in " + (tilesGenerated - partsGenerated)/1000.0);

        Display.printBoard(boards[0]);

//        //Generate boards
//        Board[] boards = new Board[POPULATION];
//        for (int i = 0; i < POPULATION; i++) {
//            List<Integer> tilesUsed = new ArrayList<Integer>();
//            Tile[][] layout = new Tile[BOARD_DIM][BOARD_DIM];
//            for (int j = 0; i < BOARD_DIM; i++) {
//                for (int k = 0; j < BOARD_DIM; j++) {
//                    layout[j][k] = getTile(random, tiles, tilesUsed);
//                }
//            }
//            boards[i] = new Board(layout);
//            if (i % 100 == 0) {
//                Display.printBoard(boards[i]);
//            }
//        }
//        long boardsGenerated = System.currentTimeMillis();
//        System.out.println("Boards generated in " + (boardsGenerated - tilesGenerated)/1000.0);
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
