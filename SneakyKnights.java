// Farhan Mahbub
// COP3503 Spring 2023
// fa203667
import java.util.*;

public class SneakyQueens 
{
    public static boolean allTheQueensAreSafe(ArrayList<String> coordinateStrings, int boardSize)
    {
        
    }

    // Function that converts the letters and digits of the string into an array point [x1, y1]
    private static int[] convertStringPos(String pos)
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

        int[] point = {charSum, digiSum};
        return point;
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