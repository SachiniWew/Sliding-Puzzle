//Student  : 20212030
//Name : W.H.Sachini Wewalwala

import java.util.*;

public class AStar {
    static List<String> findShortestPath(Puzzle puzzle, char[][] map, int start, int finish, int width) {
        String startPoint = "(" + ((start / width) + 1) + ", " + ((start % width) + 1) + ")";
        int height = map.length;

        int C = puzzle.getC();
        int[] distances = new int[C];
        int[] prev = new int[C];

        //Store nodes, prioritizing them based on their estimated total distance from the start node
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(C, Comparator.comparingInt(i -> distances[i] + heuristic(i, finish, width)));

        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;
        priorityQueue.add(start);

        while (!priorityQueue.isEmpty()) {
            int currentNode = priorityQueue.poll();

            if(currentNode==finish){
                break;
            }

            for (int neighborNode : puzzle.getNeighbors(currentNode)) {
                int neighborX = neighborNode / width;
                int neighborY = neighborNode % width;

                int[] finalPosition = slideOnIce(neighborX, neighborY, puzzle.getDx(currentNode, neighborNode), puzzle.getDy(currentNode, neighborNode), width, height, map, finish);
                int slideStopX = finalPosition[0];
                int slideStopY = finalPosition[1];

                int slideStopNode = slideStopX * width + slideStopY;

                if (slideStopX >= 0 && slideStopX < width && slideStopY >= 0 && slideStopY < height && map[slideStopY][slideStopX] != '0') {
                    int alternatePathDistance = distances[currentNode] + 1; // Assuming edge weight is 1

                    if (alternatePathDistance < distances[slideStopNode]) {
                        distances[slideStopNode] = alternatePathDistance;
                        prev[slideStopNode] = currentNode;
                        priorityQueue.add(slideStopNode);
                    }
                }
            }
        }

        List<String> path = new ArrayList<>();
        int at = finish;
        while (at != start) {
            path.add(getDirection(prev[at], at, width));
            at = prev[at];
        }

        System.out.println("\n******************************************");
        System.out.println("                 Path                   ");
        System.out.println("******************************************\n");

        path.add("Start at " + startPoint);

        Collections.reverse(path);
        path.add("Done!");

        List<String> numberedPath = new ArrayList<>();
        for (int i = 0; i < path.size(); i++) {
            numberedPath.add((i + 1) + ". " + path.get(i));
        }
        return numberedPath;
    }

    //Calculates the heuristic value
    private static int heuristic(int currentNode, int end, int width) {
        int currentX = currentNode / width;
        int currentY = currentNode % width;

        int endX = end / width;
        int endY = end % width;

        return Math.abs(currentX - endX) + Math.abs(currentY - endY); // Manhattan distance heuristic
    }

    //Handles the sliding behavior of the algorithm on an icy grid.
    private static int[] slideOnIce(int targetX, int targetY, int dx, int dy, int width, int height, char[][] map, int finish) {
        int lastValidX = targetX;
        int lastValidY = targetY;

        while (targetX >= 0 && targetX < width && targetY >= 0 && targetY < height && map[targetY][targetX] != '0') {
            lastValidX = targetX;
            lastValidY = targetY;

            if (targetX * width + targetY == finish) {
                return new int[]{targetX, targetY};
            }

            targetX += dx;
            targetY += dy;
        }

        return new int[]{lastValidX, lastValidY};
    }


    //Determine the direction of movement from one node to another on the grid
    private static String getDirection(int start, int end, int width) {
        int startX = start / width;
        int startY = start % width;

        int endX = end / width;
        int endY = end % width;

        if (endX < startX) return "Move left to (" + (endX + 1) + "," + (endY + 1) + ")";
        if (endX > startX) return "Move right to (" + (endX + 1) + "," + (endY + 1) + ")";
        if (endY < startY) return "Move up to (" + (endX + 1) + "," + (endY + 1) + ")";
        if (endY > startY) return "Move down to (" + (endX + 1) + "," + (endY + 1) + ")";
        return "";
    }
}
