package souvenirs.control;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataManagerFacade dataManagerFacade = new DataManagerFacade();

        Producer newProducer = new ProducerBuilder()
                .setName("ОбщадБанк")
                .setCountry("Великобританція")
                .build();
        dataManagerFacade.addProducer(newProducer);

        List<Producer> producersForNewSouvenir = new ArrayList<>();
        producersForNewSouvenir.add(newProducer);

        Souvenir newSouvenir = new SouvenirBuilder()
                .setName("Фірмова кепка")
                .setProducers(producersForNewSouvenir)
                .setReleaseDate(LocalDate.of(2023, 5, 20))
                .setPrice(25.99)
                .build();
        dataManagerFacade.addSouvenir(newSouvenir);

        System.out.println("Before editing:");
        dataManagerFacade.displayAllSouvenirs();
        dataManagerFacade.displayAllProducers();

        dataManagerFacade.editProducer(newProducer, "МоноБанк", "Україна");
        dataManagerFacade.editSouvenir(newSouvenir, "Котик", producersForNewSouvenir, newSouvenir.getReleaseDate(), 29.99);

        System.out.println("\nAfter editing:");
        dataManagerFacade.displayAllSouvenirs();
        dataManagerFacade.displayAllProducers();
    }
}