public class RunLikeHell
{
    public static int maxGain(int[] blocks)
    {
        // If there are no "blocks" or array is empty
        if (blocks.length <= 0)
        {
            return 0;
        }

        // If there is only one block 
        if (blocks.length == 1) 
        {
            return blocks[0];
        }

        // Tf there are 2 blocks (pick one or the other)
        if (blocks.length == 2) 
        {
            return Math.max(blocks[0], blocks[1]);
        }
        
        int prev = 0, largest = 0;

        // Algorithm starts
        for (int i = 2; i < blocks.length; i++) 
        {
            // Based off recursive function:
            // return Math.max(blocks[i] + recursion(i-2), blocks[i] + recursion[i-3])
            // prev holds previous value and would effectively be blocks[i-3] at next iteration
            blocks[i] += Math.max(blocks[i-2], prev);
            prev = blocks[i-2];

            // The final result of the blocks array should contain the list of highest values if
            // you wish to reach block i
            // Largest value is the optimal value
            if (blocks[i] > largest) 
            {
                largest = blocks[i];
            }
        }

        return largest; 
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
