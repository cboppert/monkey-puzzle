/**
 * Created by codyboppert on 10/25/14.
 */
public class Tile {
    private final Part northEdge;
    private final Part eastEdge;
    private final Part southEdge;
    private final Part westEdge;
    private final int id;

    public Tile(Part northEdge, Part eastEdge, Part southEdge, Part westEdge, int id) {
        this.northEdge = northEdge;
        this.eastEdge = eastEdge;
        this.southEdge = southEdge;
        this.westEdge = westEdge;
        this.id = id;
    }

    public Part getNorthEdge() { return northEdge; }
    public Part getEastEdge() { return eastEdge; }
    public Part getSouthEdge() { return southEdge; }
    public Part getWestEdge() { return westEdge; }
    public int getId() { return id; }
}
