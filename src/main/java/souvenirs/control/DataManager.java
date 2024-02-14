package souvenirs.control;

import java.time.LocalDate;
import java.util.List;

public class DataManager {
    private final List<Souvenir> souvenirs;
    private final List<Producer> producers;

    // constructor for a new class
    public DataManager(List<Souvenir> souvenirs, List<Producer> producers) {
        this.souvenirs = souvenirs;
        this.producers = producers;
    }

    // add new souvenir
    public void addSouvenir(Souvenir souvenir) {
        souvenirs.add(souvenir);
    }

    // add new producer
    public void addProducer(Producer producer) {
        producers.add(producer);
    }

    // edit already existing souvenir
    public void editSouvenir(Souvenir souvenir, String newName, Producer newProducer, LocalDate newReleaseDate, double newPrice) {
        souvenir.setName(newName);
        souvenir.setProducer(newProducer);
        souvenir.setReleaseDate(newReleaseDate);
        souvenir.setPrice(newPrice);
    }

    // edit already existing producer
    public void editProducer(Producer producer, String newName, String newCountry) {
        producer.setName(newName);
        producer.setCountry(newCountry);
    }

    //display all souvenirs
    public void displayAllSouvenirs() {
        for (Souvenir souvenir : souvenirs) {
            System.out.println(souvenir);
        }
    }

    //display all producers
    public void displayAllProducers() {
        for (Producer producer : producers) {
            System.out.println(producer);
        }
    }

}
