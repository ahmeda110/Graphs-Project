import java.io.FileNotFoundException;
import java.io.PrintStream;

public class DijkstrasAlgorithm {

  Main file = new Main();
  //PrintStream out = new PrintStream(file.fileName.replace(".txt",""));

  private static final int NO_PARENT = -1;

  // Function that implements Dijkstra's 
  // single source shortest path 
  // algorithm for a graph represented 
  // using adjacency matrix 
  // representation 
  public static void dijkstra(int[][] adjacencyMatrix,
    int startVertex, String fileName) throws FileNotFoundException {
    int nVertices = adjacencyMatrix[0].length;

    // shortestDistances[i] will hold the 
    // shortest distance from src to i 
    int[] shortestDistances = new int[nVertices];

    // added[i] will true if vertex i is 
    // included / in shortest path tree 
    // or shortest distance from src to 
    // i is finalized 
    boolean[] added = new boolean[nVertices];

    // Initialize all distances as 
    // INFINITE and added[] as false 
    for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
      shortestDistances[vertexIndex] = Integer.MAX_VALUE;
      added[vertexIndex] = false;
    }

    // Distance of source vertex from 
    // itself is always 0 
    shortestDistances[startVertex] = 0;

    // Parent array to store shortest 
    // path tree 
    int[] parents = new int[nVertices];

    // The starting vertex does not 
    // have a parent 
    parents[startVertex] = NO_PARENT;

    // Find shortest path for all 
    // vertices 
    for (int i = 1; i < nVertices; i++) {

      // Pick the minimum distance vertex 
      // from the set of vertices not yet 
      // processed. nearestVertex is 
      // always equal to startNode in 
      // first iteration. 
      int nearestVertex = -1;
      int shortestDistance = Integer.MAX_VALUE;
      for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
        if (!added[vertexIndex] &&
          shortestDistances[vertexIndex] <
          shortestDistance) {
          nearestVertex = vertexIndex;
          shortestDistance = shortestDistances[vertexIndex];
        }
      }

      // Mark the picked vertex as 
      // processed 
      added[nearestVertex] = true;

      // Update dist value of the 
      // adjacent vertices of the 
      // picked vertex. 
      for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
        int edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex];

        if (edgeDistance > 0 &&
          ((shortestDistance + edgeDistance) <
            shortestDistances[vertexIndex])) {
          parents[vertexIndex] = nearestVertex;
          shortestDistances[vertexIndex] = shortestDistance +
            edgeDistance;
        }
      }
    }

    printSolution(startVertex, shortestDistances, parents, fileName);
  }

  // A utility function to print 
  // the constructed distances 
  // array and shortest paths 
  private static void printSolution(int startVertex,
    int[] distances,
    int[] parents, String fileName) throws FileNotFoundException {
    PrintStream out = new PrintStream(fileName.replace(".txt", "") + "_dijkstras_output.txt");
    int nVertices = distances.length;
    out.print("Vertex\t Distance\tPath");

    for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
      if (vertexIndex != startVertex) {
        out.print("\n" + startVertex + " -> ");
        out.print(vertexIndex + " \t\t ");
        out.print(distances[vertexIndex] + "\t\t");
        printPath(vertexIndex, parents, out);
      }
    }
  }

  // Function to print shortest path 
  // from source to currentVertex 
  // using parents array 
  private static void printPath(int currentVertex,
    int[] parents, PrintStream out) {

    // Base case : Source node has 
    // been processed 
    if (currentVertex == NO_PARENT) {
      return;
    }
    printPath(parents[currentVertex], parents, out);
    out.print(currentVertex + " ");
  }

  // Driver Code 
  public static void main(String[] args) throws FileNotFoundException {
    int[][] adjacencyMatrix = {
      {
        0,
        4,
        0,
        0,
        0,
        0,
        0,
        8,
        0
      },
      {
        4,
        0,
        8,
        0,
        0,
        0,
        0,
        11,
        0
      },
      {
        0,
        8,
        0,
        7,
        0,
        4,
        0,
        0,
        2
      },
      {
        0,
        0,
        7,
        0,
        9,
        14,
        0,
        0,
        0
      },
      {
        0,
        0,
        0,
        9,
        0,
        10,
        0,
        0,
        0
      },
      {
        0,
        0,
        4,
        0,
        10,
        0,
        2,
        0,
        0
      },
      {
        0,
        0,
        0,
        14,
        0,
        2,
        0,
        1,
        6
      },
      {
        8,
        11,
        0,
        0,
        0,
        0,
        1,
        0,
        7
      },
      {
        0,
        0,
        2,
        0,
        0,
        0,
        6,
        7,
        0
      }
    };

    dijkstra(adjacencyMatrix, 0, null);
  }
}
