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

    public void addProducerToSouvenir(Souvenir souvenir, Producer producer) {
        souvenir.getProducers().add(producer);
    }

    public void removeProducerFromSouvenir(Souvenir souvenir, Producer producer) {
        souvenir.getProducers().remove(producer);
    }

    // edit already existing souvenir
    public void editSouvenir(Souvenir souvenir, String newName, List<Producer> newProducers, LocalDate newReleaseDate, double newPrice) {
        souvenir.setName(newName);
        souvenir.setProducers(newProducers);
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

    public void displayAllSouvenirsByProducer(String producerName) {
        for (Souvenir souvenir : souvenirs) {
            if (souvenir.getProducers().stream().anyMatch(producer -> producer.getName().equals(producerName)))
                System.out.println(souvenir);
        }
    }

    public void displayAllSouvenirsByCountry(String countryName) {
        for (Souvenir souvenir : souvenirs) {
            if (souvenir.getProducers().stream().anyMatch(producer -> producer.getName().equals(countryName)))
                System.out.println(souvenir);
        }
    }

    public void displayProducersWithPriceBelow(double maxPrice) {
        for (Producer producer : producers) {
            boolean hasSouvenirBelowPrice = souvenirs.stream()
                    .anyMatch(souvenir -> souvenir.getProducers().contains(producer) && souvenir.getPrice() < maxPrice);
            if (hasSouvenirBelowPrice) {
                System.out.println(producer);
            }
        }
    }

    public void displayAllProducersWithSouvenirs() {
        for (Producer producer : producers) {
            System.out.println("Producer: " + producer);
            System.out.println("Souvenirs:");
            for (Souvenir souvenir : souvenirs) {
                if (souvenir.getProducers().contains(producer)) System.out.println(souvenir);
            }
            System.out.println();
        }
    }

    public void displayProducersOfSouvenirInYear(String souvenirName, int year) {
        for (Souvenir souvenir : souvenirs) {
            if (souvenir.getName().equals(souvenirName) && souvenir.getReleaseDate().getYear() == year) {
                System.out.println("Producers of souvenir '" + souvenirName + "' produced in " + year + ":");
                for (Producer producer : souvenir.getProducers()) {
                    System.out.println(producer);
                }
            }
        }
    }

    public void displaySouvenirsByYear() {
        for (int year = LocalDate.now().getYear(); year >= 1990; year--) {
            System.out.println("Year: " + year);
            for (Souvenir souvenir : souvenirs) {
                if (souvenir.getReleaseDate().getYear() == year) System.out.println(souvenir);
            }
            System.out.println();
        }

    }

    public void deleteProducerAndSouvenirs(Producer producer) {
        souvenirs.removeIf(souvenir -> souvenir.getProducers().contains(producer));
        producers.remove(producer);
    }
}
