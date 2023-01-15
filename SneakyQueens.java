// Farhan Mahbub
// COP3503 Spring 2023
// fa203667
import java.util.ArrayList;
import java.util.Hashtable;

public class SneakyQueens {

    public static boolean allTheQueensAreSafe(ArrayList<String> coordinateStrings, int boardSize)
    {
        int i, point[], size = coordinateStrings.size();
        String loc;
        Hashtable<Integer, Integer> func = new Hashtable<>(), result = new Hashtable<>();

        for (i = 0; i < size; i++)
        {
            loc = coordinateStrings.get(i);
            if (!(isValidPlace(loc, boardSize))) continue;
            point = convertStringPos(loc);
            if (!(func.containsKey(point[0]) || result.containsKey(point[1])))
            {
                func.put(point[0], point[1]);
                result.put(point[1], point[0]);
            }
            else return false;
        }
        result.clear();
        for (i = 0; i < size; i++)
        {
            loc = coordinateStrings.get(i);
            point = convertStringPos(loc);
            if (!(result.containsKey(point[0] - func.get(point[0]))))
            {
                result.put(point[0] - func.get(point[0]), i);
            }
            else return false;
        }
        result.clear();
        for (i = 0; i < size; i++)
        {
            loc = coordinateStrings.get(i);
            point = convertStringPos(loc);
            if (!(result.containsKey(point[0] + func.get(point[0]))))
            {
                result.put(point[0] + func.get(point[0]), i);
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
        int i, vert, horizz, len = pos.length();
        for (i = 0; i < len; i++)
        {
            if (Character.isDigit(pos.charAt(i))) break;
        }
        vert = Integer.parseInt(pos.substring(i));
        horizz = baseConversion(pos);
        int[] point = {horizz, vert}; 
        return point;
    }

    private static int baseConversion(String pos) 
    {
        int i, sum = 0, len = pos.length();
        for (i = 0; i < len; i++)
        {
            if (Character.isLetter(pos.charAt(i))) sum = sum*26 + (pos.charAt(i) - 'a' + 1);
        }
        return sum;
    }

    public static double difficultyRating()
    {
        return 2.5;
    }

    public static double hoursSpent()
    {
        return 15;
    }

}