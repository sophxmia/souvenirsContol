package souvenirs.control;

import java.time.LocalDate;
import java.util.ArrayList;
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
        List<Integer> producerIds = new ArrayList<>();
        for (Producer producer : souvenir.getProducers()) {
            if (!producers.contains(producer)) {
                // Додати виробника, якщо він ще не існує у списку виробників
                addProducer(producer);
            }
            producerIds.add(producer.getId());
        }
        souvenir.setProducerIds(producerIds);

        souvenirs.add(souvenir);
    }

    // add new producer
    public void addProducer(Producer producer) {
        producers.add(producer);
    }

    // edit already existing souvenir
    public void editSouvenir(Souvenir souvenir, String newName, List<Producer> newProducers, LocalDate newReleaseDate, double newPrice) {
        souvenir.setName(newName);
        souvenir.setProducers(newProducers);
        souvenir.setReleaseDate(newReleaseDate);
        souvenir.setPrice(newPrice);
    }

    public void deleteSouvenir(Souvenir souvenir) {
        souvenirs.remove(souvenir);
    }

    public void displayAllSouvenirsByProducer(String producerName) {
        System.out.println("Producer: " + producerName);
        for (Souvenir souvenir : souvenirs) {
            if (souvenir.getProducers().stream().anyMatch(producer -> producer.getName().equals(producerName)))
                System.out.println(souvenir);
        }
    }

    public void displayAllSouvenirsByCountry(String countryName) {
        System.out.println("Country: " + countryName);
        for (Souvenir souvenir : souvenirs) {
            if (souvenir.getProducers().stream().anyMatch(producer -> producer.getCountry().equals(countryName)))
                System.out.println(souvenir);
        }
    }

    public void displayProducersWithPriceBelow(double maxPrice) {
        System.out.println("Producers with souvenir prices below " + maxPrice + ":");
        for (Souvenir souvenir : souvenirs) {
            if (souvenir.getPrice() < maxPrice) {
                for (Producer producer : souvenir.getProducers()) {
                    System.out.println(producer);
                }
            }
        }
    }


    public void displayAllProducersWithSouvenirs() {
        for (Producer producer : producers) {
            System.out.println("Producer: " + producer);
            System.out.println("Souvenirs:");
            for (Souvenir souvenir : souvenirs) {
                if (souvenir.getProducers().stream().anyMatch(p -> p.getName().equals(producer.getName()))) {
                    System.out.println(souvenir);
                }
            }
            System.out.println();
        }
    }


    public void displayProducersOfSouvenirInYear(String souvenirName, int year) {
        System.out.println("Producers of souvenir '" + souvenirName + "' produced in " + year + ":");
        for (Souvenir souvenir : souvenirs) {
            if (souvenir.getName().equals(souvenirName) && souvenir.getReleaseDate().getYear() == year) {
                for (Producer producer : souvenir.getProducers()) {
                    System.out.println(producer);
                }
            }
        }
    }

    public void displaySouvenirsByYear(int year) {
        System.out.println("Year: " + year);
        for (Souvenir souvenir : souvenirs) {
            if (souvenir.getReleaseDate().getYear() == year) System.out.println(souvenir);
        }
        System.out.println();

    }

    public void deleteProducerAndSouvenirs(Producer producer) {
        souvenirs.removeIf(souvenir -> souvenir.getProducers().contains(producer));
        producers.remove(producer);
    }

    public List<Souvenir> getAllSouvenirs() {
        return souvenirs;
    }

    public List<Producer> getAllProducers() {
        return producers;
    }
}
