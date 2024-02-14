package souvenirs.control;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        DataManagerFacade dataManagerFacade = new DataManagerFacade();

        Producer newProducer = new Producer("ОбщадБанк", "Великобританція");
        dataManagerFacade.addProducer(newProducer);

        Souvenir newSouvenir = new Souvenir("Фірмова кепка",
                newProducer,
                LocalDate.of(2023, 5, 20),
                25.99);
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