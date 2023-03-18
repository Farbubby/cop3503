// Farhan Mahbub
// COP3503 Spring 2023
// fa203667

import java.util.*;
import java.io.*;

public class TopoPaths 
{
    public static int countTopoPaths(String filename)
    {
        int i, j, k, val, numVertices, numEdges, num, countIncoming[];
        boolean visited[];
        LinkedList<Integer> vertex;
        ArrayDeque<Integer> available = new ArrayDeque<>();
        ArrayList<LinkedList<Integer>> adjacencyList = new ArrayList<>();

        try
        {
            Scanner scan = new Scanner(new File(filename));
            numVertices = scan.nextInt();
            countIncoming = new int[numVertices];
            visited = new boolean[numVertices];

            // Creates an adjacency list from File I/O
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

            // Starts a queue with all of the nodes with 0 incoming edges
            for (i = 0; i < numVertices; i++)
            {
                if (countIncoming[i] == 0)
                {
                    available.add(i);
                }
            }

            for (i = 0; i < numVertices; i++)
            {
                // Idea #1: If at least 2 vertices have 0 incoming edges, a topopath doesn't exist
                // Idea #2: Empty list means that all of the neighbor nodes have some incoming edge
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

                // Find the nodes that aren't visited with no incoming edges
                for (k = 0; k < numVertices; k++)
                {
                    if (countIncoming[k] == 0 && !visited[k])
                    {
                        available.add(k);
                    }
                }
            }

            // Number of topopaths can't be greater than 1
            // To have more than one topological sort, you need to have at least 2 vertices with
            // 0 incoming edges which would me there would be no topopaths.
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