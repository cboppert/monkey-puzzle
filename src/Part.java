/**
 * Created by codyboppert on 10/25/14.
 */
public class Part {
    private final boolean top;
    private final Color color;

    public Part(boolean top, Color color) {
        this.top = top;
        this.color = color;
    }

    public boolean isTop() { return top; }
    public Color getColor() { return color; }
}
