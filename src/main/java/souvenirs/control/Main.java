package souvenirs.control;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        DataManagerFacade dataManagerFacade = new DataManagerFacade();

        Producer newProducer = new ProducerBuilder()
                .setName("ОбщадБанк")
                .setCountry("Великобританція")
                .build();
        dataManagerFacade.addProducer(newProducer);

        Souvenir newSouvenir = new SouvenirBuilder()
                .setName("Фірмова кепка")
                .setProducer(new ProducerBuilder()
                        .setName("ОбщадБанк")
                        .setCountry("Великобританція")
                        .build())
                .setReleaseDate(LocalDate.of(2023, 5, 20))
                .setPrice(25.99)
                .build();
        dataManagerFacade.addSouvenir(newSouvenir);

        System.out.println("Before editing:");
        dataManagerFacade.displayAllSouvenirs();
        dataManagerFacade.displayAllProducers();

        dataManagerFacade.editProducer(newProducer, "МоноБанк", "Україна");
        dataManagerFacade.editSouvenir(newSouvenir, "Котик", newProducer, newSouvenir.getReleaseDate(), 29.99);

        System.out.println("\nAfter editing:");
        dataManagerFacade.displayAllSouvenirs();
        dataManagerFacade.displayAllProducers();
    }
}