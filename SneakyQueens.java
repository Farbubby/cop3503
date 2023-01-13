// Farhan Mahbub
// COP3503 Spring 2023
// fa203667
import java.util.ArrayList;

public class SneakyQueens {

    public static void main(String[] args)
    {   
        System.out.print();
    }

    public static boolean allTheQueensAreSafe(ArrayList<String> coordinateStrings, int boardSize)
    {
        return false;
    }

    public static int[] convertStringPos(String pos)
    {
        int i;
        int horizontal = 0;
        int vertical = 0;
        int len = pos.length();
        for (i = 0; i < len; i++)
        {
            if (Character.isDigit(pos.charAt(i))) break;
        }
        vertical = Integer.parseInt(pos.substring(i));
        horizontal = baseConversion(pos);
        int loc[] = {horizontal, vertical};

        return loc;
    }

    public static int baseConversion(String pos) 
    {
        int i;
        int sum = 0;
        for (i = 0; i < pos.length(); i++)
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