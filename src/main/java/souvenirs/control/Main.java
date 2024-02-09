package souvenirs.control;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler();

        List<Souvenir> souvenirs = fileHandler.readSouvenirsFromFile("src/souvenirs.csv");
        List<Producer> producers = fileHandler.readProducersFromFile("src/producers.csv");

        DataManager dataManager = new DataManager(souvenirs, producers);

        Producer newProducer = new Producer("ОбщадБанк", "Великобританція");
        dataManager.addProducer(newProducer);

        Souvenir newSouvenir = new Souvenir("Фірмова кепка",
                newProducer,
                LocalDate.of(2023, 5, 20),
                25.99);
        dataManager.addSouvenir(newSouvenir);

        System.out.println("Before editing:");
        dataManager.displayAllSouvenirs();
        dataManager.displayAllProducers();

        dataManager.editProducer(newProducer, "МоноБанк", "Україна");
        dataManager.editSouvenir(newSouvenir, "Котик", newProducer,  newSouvenir.getReleaseDate(), 29.99);

        System.out.println("\nAfter editing:");
        dataManager.displayAllSouvenirs();
        dataManager.displayAllProducers();
    }
}