//Student  : 20212030
//Name : W.H.Sachini Wewalwala

import java.util.*;

public class Puzzle {

    private final int startPoint;
    private final int finishPoint;
    private final int width;
    private final int C;

    private final char[][] map;
    private final Map<Integer, LinkedList<Integer>> adjacencyList;

    public Puzzle(int columns, int startPoint, int finishPoint, int width, char[][] map) {
        C = columns; //no of columns
        this.startPoint = startPoint;
        this.finishPoint = finishPoint;
        this.width = width;
        this.map = map;

        adjacencyList = new HashMap<>();

        for (int i = 0; i < columns; i++) {
            adjacencyList.put(i, new LinkedList<>());
        }
    }


    //Adds an edge between two nodes in the puzzle's adjacency list
    public void addEdge(int startNode, int endNode) {
        adjacencyList.get(startNode).add(endNode);
    }

    //Retrieves the list of neighbors for a given node
    public List<Integer> getNeighbors(int node) {
        return adjacencyList.get(node);
    }

    public int getC() {
        return C;
    }

    //Calculate the movement or distance between nodes.
    public int getDx(int u, int v) {
        int dx = 0;
        int uX = u / getWidth();
        int vX = v / getWidth();

        if (uX != vX) {
            dx = vX - uX;
        }
        return dx;
    }

    public int getDy(int u, int v) {
        int dy = 0;
        int uY = u % getWidth();
        int vY = v % getWidth();

        if (uY != vY) {
            dy = vY - uY;
        }
        return dy;
    }

    public int getStartPoint() {
        return startPoint;
    }

    public int getFinishPoint() {
        return finishPoint;
    }

    public int getWidth() {
        return width;
    }

    public char[][] getMap() {
        return map;
    }
}
