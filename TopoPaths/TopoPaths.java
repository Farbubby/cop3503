// Farhan Mahbub
// COP3503 Spring 2023
// fa203667

import java.util.*;
import java.io.*;

public class TopoPaths 
{
    
    public static void main(String[] args)
    {
        int k = countTopoPaths("./input_files/TestCase05-graph.txt");
        System.out.println(k);
    }

    public static int countTopoPaths(String filename)
    {
        int i, j, val, numVertices, numEdges, num, countIncoming[];
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

            for (i = 0; i < numVertices; i++)
            {
                if (countIncoming[i] == 0)
                {
                    available.add(i);
                }
            }

            for (i = 0; i < numVertices; i++)
            {
                if (available.size() != 1)
                {
                    return 0;
                }

                num = available.pop();
                visited[num] = true;
                vertex = adjacencyList.get(num);

                for (j = 0; j < vertex.size(); j++)
                {
                    countIncoming[vertex.get(j)-1]--;
                }

                for (i = 0; i < numVertices; i++)
                {
                    if (countIncoming[i] == 0 && !visited[i])
                    {
                        available.add(i);
                    }
                }
            }

            return 1;
        }

        catch (Exception e)
        {
            System.out.println(e);
        }

        return 0;
    }

    // private static int findZero(int[] incoming)
    // {
    //     int i, len = incoming.length;

    //     for (i = 0; i < len; i++)
    //     {
    //         if (incoming[i] == 0)
    //         {
    //             return i;
    //         }
    //     }

    //     return -1;
    // }

    // private static int countZeroes(int[] incoming)
    // {
    //     int i, count = 0, len = incoming.length;

    //     for (i = 0; i < len; i++)
    //     {
    //         if (incoming[i] == 0)
    //         {
    //             return count++;
    //         }
    //     }

    //     return count;
    // }

    public static double difficultyRating()
    {
        return 3.0;
    }

    public static double hoursSpent()
    {
        return 10.0;
    }

}