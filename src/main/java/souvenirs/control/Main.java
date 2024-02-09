package souvenirs.control;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler();

        List<Souvenir> souvenirs = fileHandler.readSouvenirsFromFile("src/souvenirs.csv");
        for (Souvenir souvenir : souvenirs) {
            System.out.println(souvenir);
        }

        List<Producer> producers = fileHandler.readProducersFromFile("src/producers.csv");
        for (Producer producer : producers) {
            System.out.println(producer);
        }
    }
}