// Farhan Mahbub
// COP3503 Spring 2023
// fa203667
import java.util.ArrayList;
import java.util.Hashtable;

public class SneakyQueens {

    public static void main(String[] args)
    {   
        ArrayList<String> temp = new ArrayList<>();
        temp.add("ad1");
        temp.add("bro2");
        temp.add("br3");
        System.out.println(allTheQueensAreSafe(temp, 10000));
    }

    public static boolean allTheQueensAreSafe(ArrayList<String> coordinateStrings, int boardSize)
    {
        int i;
        int point[];
        int size = coordinateStrings.size();
        Hashtable<Integer, Integer> xcoords = new Hashtable<>();
        Hashtable<Integer, Integer> ycoords = new Hashtable<>();
        for (i = 0; i < size; i++)
        {
            String loc = coordinateStrings.get(i);
            if (!(isValidPlace(loc, boardSize))) continue;
            point = convertStringPos(loc);
            if (!(xcoords.containsKey(point[0]) || ycoords.containsKey(point[1])))
            {
                xcoords.put(point[0], i);
                ycoords.put(point[1], i);
            }
            else return false;
        }
        return true;
    }

    public static boolean isValidPlace(String pos, int boardSize)
    {
        int[] point = convertStringPos(pos);
        return !(point[0] > boardSize || point[1] > boardSize);
    }

    public static int[] convertStringPos(String pos)
    {
        int i;
        int len = pos.length();
        for (i = 0; i < len; i++)
        {
            if (Character.isDigit(pos.charAt(i))) break;
        }
        int vertical = Integer.parseInt(pos.substring(i));
        int horizontal = baseConversion(pos);
        int[] point = {horizontal, vertical}; 
        return point;
    }

    private static int baseConversion(String pos) 
    {
        int i;
        int sum = 0;
        int len = pos.length();
        for (i = 0; i < len; i++)
        {
            if (Character.isLetter(pos.charAt(i))) sum = sum*26 + (pos.charAt(i) - 'a' + 1);
        }
        return sum;
    }

    public static double difficultyRating()
    {
        return 1.0;
    }

    public static double hoursSpent()
    {
        return 1;
    }

}