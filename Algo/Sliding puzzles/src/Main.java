//Student  : 20212030
//Name : W.H.Sachini Wewalwala

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("******************************************");
        System.out.println("               Sliding Puzzles            ");
        System.out.println("******************************************\n");
        System.out.println("Enter the file name:");
        Scanner scanner = new Scanner(System.in);

        try {
            Puzzle puzzle = MapParser.parseMap(scanner.nextLine());

            List<String> path = AStar.findShortestPath(puzzle, puzzle.getMap(), puzzle.getStartPoint(), puzzle.getFinishPoint(), puzzle.getWidth());
            if (path.isEmpty()) {
                System.out.println("No Path Found!");
            } else {
                for (String steps : path) {
                    System.out.println(steps);
                }
            }


        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());


        } catch (RuntimeException e) {
            e.printStackTrace();
        }

    }
}
