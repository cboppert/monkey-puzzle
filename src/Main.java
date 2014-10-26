import java.util.HashMap;
import java.util.Random;

/**
 * Created by codyboppert on 10/25/14.
 */
public class Main {
    public static void main(String[] args) {
        //Initialize
        //Generate parts
        HashMap<Part, Integer> parts = new HashMap<Part, Integer>();
        Part[] partArray = new Part[10];
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
        Random random = new Random(System.currentTimeMillis());

        //Generate boards



    }
}
