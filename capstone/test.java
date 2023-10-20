package capstone;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class test {
    public static void main(String[] args) {
        int numCoordinates = 600000; // 원하는 좌표 개수
        Set<String> coordinatesSet = new HashSet<>();
        Random random = new Random();

        while (coordinatesSet.size() < numCoordinates) {
            int x = random.nextInt(100000) + 1; // 1부터 100000까지의 랜덤 x 좌표
            int y = random.nextInt(100000) + 1; // 1부터 100000까지의 랜덤 y 좌표
            coordinatesSet.add(x + "," + y);
        }

        try {
            FileWriter writer = new FileWriter("random.txt");
            for (String coordinates : coordinatesSet) {
                writer.write(coordinates + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
