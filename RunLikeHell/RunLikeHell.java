public class RunLikeHell
{
    public static int maxGain(int[] blocks)
    {
        if (blocks.length <= 0)
        {
            return 0;
        }

        if (blocks.length == 1) 
        {
            return blocks[0];
        }

        if (blocks.length == 2) 
        {
            return Math.max(blocks[0], blocks[1]);
        }
        
        int prev = 0;
        int largest = 0;

        for (int i = 2; i < blocks.length; i++) 
        {
            blocks[i] += Math.max(blocks[i-2], prev);
            prev = blocks[i-2];

            if (blocks[i] > largest) 
            {
                largest = blocks[i];
            }
        }

        return largest; 
    }

    public static double difficultyRating()
    {
        return 1.0;
    }

    public static double hoursSpent()
    {
        return 2.0;
    }
}