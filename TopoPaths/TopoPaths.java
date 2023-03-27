// Farhan Mahbub
// COP3503 Spring 2023
// fa203667

import java.util.*;
import java.io.*;

public class TopoPaths 
{
    public static int countTopoPaths(String filename)
    {
        int i, j, val, num, numVertices, numEdges, countIncoming[];
        boolean visited[];
        Scanner scan;
        LinkedList<Integer> list, vertex;
        ArrayDeque<Integer> available = new ArrayDeque<>();
        ArrayList<LinkedList<Integer>> adjacencyList = new ArrayList<>();

        try
        {
            scan = new Scanner(new File(filename));
            numVertices = scan.nextInt();
            countIncoming = new int[numVertices];
            visited = new boolean[numVertices];

            // Creates an adjacency list from File I/O
            for (i = 0; i < numVertices; i++)
            {
                list = new LinkedList<>();
                numEdges = scan.nextInt();

                for (j = 0; j < numEdges; j++)
                {
                    val = scan.nextInt();
                    countIncoming[val-1]++;
                    list.add(val);
                }
                    
                adjacencyList.add(list);
            }

            // Main algorithm starts here
            for (i = 0; i < numVertices; i++)
            {
                // Find the nodes that aren't visited with no incoming edges
                for (j = 0; j < numVertices; j++)
                {
                    if (countIncoming[j] == 0 && !visited[j])
                    {
                        available.add(j);
                    }
                }

                // If none or at least 2 vertices have 0 incoming edges, then there's no topopath
                if (available.size() != 1)
                {
                    return 0;
                }

                // Make sure the vertex is marked visited
                num = available.remove();
                visited[num] = true;
                vertex = adjacencyList.get(num);

                // Decrement the incoming edges from the current vertex's neighbors
                for (j = 0; j < vertex.size(); j++)
                {
                    countIncoming[vertex.get(j)-1]--;
                }
            }

            // To get here, there needed to be one node with 0 incoming edges at each vertex step
            return 1;
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