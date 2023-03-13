// Farhan Mahbub
// COP3503 Spring 2023
// fa203667

import java.util.*;
import java.io.*;

public class TopoPaths 
{
    
    public static void main(String[] args)
    {
        ArrayList<LinkedList<Integer>> thing;
        thing = getAdjacencyList("./input_files/TestCase03-graph.txt");

        for (int i = 0; i < thing.size(); i++)
        {
            System.out.println(thing.get(i));
        }
    }

    public static int countTopoPaths(String filename)
    {
        return 0;
    }

    private static ArrayList<LinkedList<Integer>> getAdjacencyList(String filename)
    {
        int i, val, numVertices, numEdges;
        ArrayList<LinkedList<Integer>> adjacencyList = new ArrayList<>();

        try
        {
            Scanner scan = new Scanner(new File(filename));

            while (scan.hasNextInt())
            {
                numVertices = scan.nextInt();

                for (i = 0; i < numVertices; i++)
                {
                    LinkedList<Integer> list = new LinkedList<>();
                    numEdges = scan.nextInt();

                    for (i = 0; i < numEdges; i++)
                    {
                        val = scan.nextInt();
                        list.add(val);
                    }
                    
                    adjacencyList.add(list);

                    if (!scan.hasNextInt()) break;
                }
            }
        }

        catch (Exception e)
        {
            System.out.println(e);
        }

        return adjacencyList;
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