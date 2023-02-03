// Farhan Mahbub
// COP3503 Spring 2023
// fa203667
import java.util.*;
import java.awt.Point;

public class SneakyKnights
{
    public static boolean allTheKnightsAreSafe(ArrayList<String> coordinateStrings, int boardSize)
    {
        HashSet<Point> seen = new HashSet<>();
        int i, size = coordinateStrings.size();
        for (i = 0; i < size; i++)
        {
            Point coord = convertStringPos(coordinateStrings.get(i));
            seen.add(coord.getLocation());

            // If statement checks 8 possible positions around the knight
            coord.translate(-1, 2);
            if (seen.contains(coord.getLocation())) return false;
            coord.translate(-1, -1);
            if (seen.contains(coord.getLocation())) return false;
            coord.translate(0, -2);
            if (seen.contains(coord.getLocation())) return false;
            coord.translate(1, -1);
            if (seen.contains(coord.getLocation())) return false;
            coord.translate(2, 0);
            if (seen.contains(coord.getLocation())) return false;
            coord.translate(1, 1);
            if (seen.contains(coord.getLocation())) return false;
            coord.translate(0, 2);
            if (seen.contains(coord.getLocation())) return false;
            coord.translate(-1, 1);
            if (seen.contains(coord.getLocation())) return false;
        }
        
        return true;
    }

    // Function that converts the letters and digits of the string into an array point [x1, y1]
    private static Point convertStringPos(String pos)
    {
        int i, charSum = 0, digiSum = 0, len = pos.length();

        // Uses base-26 and base-10 calculations for the letters and digits respectively
        for (i = 0; i < len; i++)
        {
            if (Character.isLetter(pos.charAt(i))) 
            {
                charSum = (charSum * 26) + (pos.charAt(i) - 'a' + 1);
            }
            else if (Character.isDigit(pos.charAt(i)))
            {
                digiSum = (digiSum * 10) + (pos.charAt(i) - '0');
            }
        }

        return new Point(charSum, digiSum);
    }

    public static double difficultyRating()
    {
        return 2.5;
    }

    public static double hoursSpent()
    {
        return 10;
    }
}