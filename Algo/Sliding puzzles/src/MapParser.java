//Student  : 20212030
//Name : W.H.Sachini Wewalwala

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapParser {

    public static Puzzle parseMap(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int width, height;
        int startX = -1, startY = -1, finishX = -1, finishY = -1;

        //Creates an ArrayList to store the lines read from the mapped file.
        List<String> mapLines = new ArrayList<>();
        int y = 0;
        while ((line = reader.readLine()) != null) {
            mapLines.add(line);

            if (line.contains("S")) {
                startX = line.indexOf("S");
                startY = y;
            }
            if (line.contains("F")) {
                finishX = line.indexOf("F");
                finishY = y;
            }
            y++;
        }
        reader.close();

        //Calculate width and height
        width = mapLines.getFirst().length();
        height = mapLines.size();

        //Creates a 2D char array map based on the height and width of the map
        char[][] map = new char[height][];
        for (int i = 0; i < mapLines.size(); i++) {
            map[i] = mapLines.get(i).toCharArray();
        }

        //Calculates a linear index that uniquely represents a cell in a 2D grid when the grid is flattened into a 1D array
        int startPosition = startX * width + startY;
        int finishPosition = finishX * width + finishY;



        //Creating the puzzle
        Puzzle puzzle = new Puzzle(height * width, startPosition, finishPosition, width, map);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (j > 0) {
                    puzzle.addEdge(i * width + j, i * width + j - 1); // left
                }
                if (j < width - 1) {
                    puzzle.addEdge(i * width + j, i * width + j + 1); // right
                }
                if (i > 0) {
                    puzzle.addEdge(i * width + j, (i - 1) * width + j); // up
                }
                if (i < height - 1) {
                    puzzle.addEdge(i * width + j, (i + 1) * width + j); // down
                }

            }
        }

        return puzzle;

    }


}
