// Farhan Mahbub
// COP3503 Spring 2023
// fa203667
import java.util.ArrayList;
import java.util.Hashtable;

public class SneakyQueens {

    public static boolean allTheQueensAreSafe(ArrayList<String> coordinateStrings, int boardSize)
    {
        int i, size = coordinateStrings.size();
        Hashtable<Integer, Integer> func = new Hashtable<>(), result = new Hashtable<>();
        ArrayList<int[]> posList = convertList(coordinateStrings);

        // Handles cases for a pair of queens that are on the same row or column
        // Arraylist of arrays which are points ([x1, y1])
        for (i = 0; i < size; i++)
        {   
            // Checks if any of the x or y coords have been seen already
            if (!(func.containsKey(posList.get(i)[0]) || result.containsKey(posList.get(i)[1])))
            {
                func.put(posList.get(i)[0], posList.get(i)[1]);
                result.put(posList.get(i)[1], posList.get(i)[0]);
            }
            else return false;
        }
        result.clear();

        // Handles cases for a pair of queens that are on the +1 slope diagonal "/"
        for (i = 0; i < size; i++)
        {
            // Derived from formula: (x1-y1) = a-b given (a, b) and that (x1-a)/(y1-b) = 1
            if (!(result.containsKey(posList.get(i)[0] - func.get(posList.get(i)[0]))))
            {
                result.put(posList.get(i)[0] - func.get(posList.get(i)[0]), i);
            }
            else return false;
        }
        result.clear();

        // Handles cases for a pair of queens that are on the -1 slope diagonal "\"
        for (i = 0; i < size; i++)
        {
            // // Derived from formula: (x1+y1) = a+b given (a, b) and that (x1-a)/(y1-b) = -1
            if (!(result.containsKey(posList.get(i)[0] + func.get(posList.get(i)[0]))))
            {
                result.put(posList.get(i)[0] + func.get(posList.get(i)[0]), i);
            }
            else return false;
        }
        return true;
    }

    // Coverts all of the position strings into an array of point coordinates ([x1, y1])
    public static ArrayList<int[]> convertList(ArrayList<String> coordinateStrings)
    {   
        int i, size = coordinateStrings.size();
        ArrayList<int[]> posList = new ArrayList<>();
        for (i = 0; i < size; i++)
        {
            posList.add(convertStringPos(coordinateStrings.get(i)));
        }
        return posList;
    }

    // Helper function that converts the letters and digits part of the string into an array point [x1, y1]
    public static int[] convertStringPos(String pos)
    {
        int i, vert, horizz, len = pos.length();
        // Stops until a digit is reached in the string
        for (i = 0; i < len; i++)
        {
            if (Character.isDigit(pos.charAt(i))) break;
        }
        vert = Integer.parseInt(pos.substring(i));
        horizz = baseConversion(pos);
        int[] point = {horizz, vert};
        return point;
    }

    // Helper function that converts the letters into an x-coordinate with base-26 calculations
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