// Farhan Mahbub
// COP3503 Spring 2023
// fa203667
import java.util.*;

public class SneakyQueens 
{
    public static boolean allTheQueensAreSafe(ArrayList<String> coordinateStrings, int boardSize)
    {
        int i, size = coordinateStrings.size();
        Hashtable<Integer, Integer> xcoords = new Hashtable<>(), ycoords = new Hashtable<>();
        Hashtable<Integer, Integer> rightDown = new Hashtable<>(), rightUp = new Hashtable<>();

        for (i = 0; i < size; i++)
        {   
            // Generates a point [x1, y1]
            int[] point = convertStringPos(coordinateStrings.get(i));

            // Handles cases for a pair of queens that are on the same row or column
            // Checks if any of the x or y coords have been seen already
            if (!(xcoords.containsKey(point[0]) || ycoords.containsKey(point[1])))
            {
                xcoords.put(point[0], point[1]);
                ycoords.put(point[1], point[0]);
            }
            else return false;

            // Handles cases for a pair of queens that are on the +1 slope diagonal "/"
            // Derived from formula: (x1-y1) = a-b given (a, b) and that (x1-a)/(y1-b) = 1
            if (!(rightUp.containsKey(point[0] - xcoords.get(point[0]))))
            {
                rightUp.put(point[0] - xcoords.get(point[0]), i);
            }
            else return false;

            // Handles cases for a pair of queens that are on the -1 slope diagonal "\"
            // Derived from formula: (x1+y1) = a+b given (a, b) and that (x1-a)/(y1-b) = -1
            if (!(rightDown.containsKey(point[0] + xcoords.get(point[0]))))
            {
                rightDown.put(point[0] + xcoords.get(point[0]), i);
            }
            else return false;
        }

        return true;
    }

    // Function that converts the letters and digits of the string into an array point [x1, y1]
    private static int[] convertStringPos(String pos)
    {
        int i, vert, horizz, len = pos.length();

        // Iterates until a digit is reached in the string
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
            // Stops when a digit is reached
            // Iterative approach using the idea of (c * q) + r1: q is quotient and r1 is remainer
            if (Character.isLetter(pos.charAt(i))) sum = sum*26 + (pos.charAt(i) - 'a' + 1);
            else break;
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