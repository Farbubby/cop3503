// Farhan Mahbub
// COP3503 Spring 2023
// fa203667

public class RunLikeHell
{
    public static int maxGain(int[] blocks)
    {
        int len = blocks.length, prev = 0;

        // If there are no "blocks" or array is empty
        if (len <= 0)
        {
            return 0;
        }

        // If there is only one block 
        if (len == 1) 
        {
            return blocks[0];
        }

        // Algorithm starts
        for (int i = 2; i < blocks.length; i++) 
        {
            // Based off recursive function:
            // return Math.max(blocks[i] + recursion(i-2), blocks[i] + recursion[i-3])
            // prev holds previous value and would effectively be blocks[i-3] at next iteration
            blocks[i] += Math.max(blocks[i-2], prev);
            prev = blocks[i-2];
        }

        // You will eventually land on one of the 2 spots
        return Math.max(blocks[len-1], blocks[len-2]);
    }

    public static double difficultyRating()
    {
        return 2.0;
    }

    public static double hoursSpent()
    {
        return 3.0;
    }
}
