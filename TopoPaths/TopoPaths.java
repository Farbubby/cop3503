// Farhan Mahbub
// COP3503 Spring 2023
// fa203667

import java.util.*;
import java.io.*;

public class TopoPaths 
{
    
    public static void main(String[] args)
    {
        ArrayList<LinkedList<Integer>> thing = getAdjacencyList("TestCase03-graph.txt");

        for (int j = 0; j < thing.size(); j++)
        {
            for (int i = 0; i < thing.get(j).size(); i++)
            {
                System.out.print(thing.get(j).get(i) + " ");
            }
            System.out.println("");
        }
    }

    public static int countTopoPaths(String filename)
    {
        return 0;
    }

    private static ArrayList<LinkedList<Integer>> getAdjacencyList(String filename)
    {
        int i, numVertices, numEdges;
        ArrayList<LinkedList<Integer>> adjacencyList = new ArrayList<>();

        try
        {
            Scanner scan = new Scanner(new File(filename));

            while (scan.hasNext())
            {
                numVertices = scan.nextInt();

                for (i = 0; i < numVertices; i++)
                {
                    LinkedList<Integer> list = new LinkedList<>();
                    numEdges = scan.nextInt();

                    for (i = 0; i < numEdges; i++)
                    {
                        list.add(scan.nextInt());
                    }

                    adjacencyList.add(list);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("No file");
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