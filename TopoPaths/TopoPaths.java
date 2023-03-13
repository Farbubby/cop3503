// Farhan Mahbub
// COP3503 Spring 2023
// fa203667

import java.util.*;
import java.io.*;

public class TopoPaths 
{
    
    public static void main(String[] args)
    {
        countTopoPaths("./input_files/TestCase03-graph.txt");
    }

    public static int countTopoPaths(String filename)
    {
        int i, j, val, numVertices, numEdges, countIncoming[];
        ArrayList<LinkedList<Integer>> adjacencyList = new ArrayList<>();

        try
        {
            Scanner scan = new Scanner(new File(filename));
            numVertices = scan.nextInt();
            countIncoming = new int[numVertices];

            for (i = 0; i < numVertices; i++)
            {
                LinkedList<Integer> list = new LinkedList<>();
                numEdges = scan.nextInt();

                for (j = 0; j < numEdges; j++)
                {
                    val = scan.nextInt();
                    countIncoming[val-1]++;
                    list.add(val);
                }
                    
                adjacencyList.add(list);
            }
        }

        catch (Exception e)
        {
            System.out.println(e);
        }

        return 0;
    }

    public static double difficultyRating()
    {
        return 3.0;
    }

    public static double hoursSpent()
    {
        return 10.0;
    }

}