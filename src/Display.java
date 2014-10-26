/**
 * Created by codyboppert on 10/26/14.
 */
public class Display {

    public static void printBoard(Board board) {
        Tile[][] layout = board.getLayout();
        int dimension = layout.length;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < dimension; k++) {
                    switch (j) {
                        case 0:
                            builder.append("  ").append(getPartLabel(layout[i][k].getNorthEdge())).append("  ");
                            break;
                        case 1:
                            builder.append(getPartLabel(layout[i][k].getWestEdge()))
                                   .append("  ")
                                   .append(getPartLabel(layout[i][k].getEastEdge()));
                            break;
                        case 2:
                            builder.append("  ").append(getPartLabel(layout[i][k].getSouthEdge())).append("  ");
                            break;
                    }
                }
                builder.append('\n');
            }
        }
        System.out.println(builder.toString());
        System.out.println(board.getFitness());
    }

    public static String getPartLabel(Part part) {
        StringBuilder label = new StringBuilder();
        switch (part.getColor()) {
            case RED:
                label.append('R');
                break;
            case ORANGE:
                label.append('O');
                break;
            case YELLOW:
                label.append('Y');
                break;
            case GREEN:
                label.append('G');
                break;
            case BLUE:
                label.append('B');
                break;
        }
        if (part.isTop()) {
            label.append('T');
        } else {
            label.append('B');
        }

        return label.toString();
    }

}
