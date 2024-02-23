package souvenirs.control;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// usage of Facade pattern
public class DataManagerFacade {
    private final FileHandler fileHandler;
    private final DataManager dataManager;

    public DataManagerFacade() {
        this.fileHandler = FileHandler.getInstance();
        this.dataManager = new DataManager(fileHandler.readSouvenirsFromFile("src/souvenirs.csv"),
                fileHandler.readProducersFromFile("src/producers.csv"));
    }

    public void writeSouvenirsToFile(List<Souvenir> souvenirs, String fileName) {
        fileHandler.writeSouvenirsToFile(souvenirs, fileName);
    }

    public void writeProducersToFile(List<Producer> producers, String fileName) {
        fileHandler.writeProducersToFile(producers, fileName);
    }

    public void displaySouvenirsByProducer(String producerName) {
        dataManager.displayAllSouvenirsByProducer(producerName);
    }

    public void deleteSouvenir(Souvenir souvenir) {
        dataManager.deleteSouvenir(souvenir);
        writeSouvenirsToFile(dataManager.getAllSouvenirs(), "src/souvenirs.csv");
    }

    public void displaySouvenirsByCountry(String country) {
        dataManager.displayAllSouvenirsByCountry(country);
    }

    public void displayProducersWithPriceBelow(double maxPrice) {
        dataManager.displayProducersWithPriceBelow(maxPrice);
    }

    public void displayAllProducersWithSouvenirs() {
        dataManager.displayAllProducersWithSouvenirs();
    }

    public void displayProducersOfSouvenirInYear(String souvenirName, int year) {
        dataManager.displayProducersOfSouvenirInYear(souvenirName, year);
    }

    public void displaySouvenirsByYear(int year) {
        dataManager.displaySouvenirsByYear(year);
    }

    public void deleteProducerAndSouvenirs(Producer producer) {
        dataManager.deleteProducerAndSouvenirs(producer);
    }

    public void addSouvenir(Souvenir souvenir) {
        List<Integer> producerIds = new ArrayList<>();
        for (Producer producer : souvenir.getProducers()) {
            producerIds.add(producer.getId());
        }
        souvenir.setProducerIds(producerIds);

        dataManager.addSouvenir(souvenir);
        writeSouvenirsToFile(dataManager.getAllSouvenirs(), "src/souvenirs.csv");
    }


    public void addProducer(Producer producer) {
        dataManager.addProducer(producer);
        writeProducersToFile(dataManager.getAllProducers(), "src/producers.csv");
    }

    public void addProducerToSouvenir(Souvenir souvenir, Producer producer) {
        dataManager.addProducerToSouvenir(souvenir, producer);
    }

    public void removeProducerFromSouvenir(Souvenir souvenir, Producer producer) {
        dataManager.removeProducerFromSouvenir(souvenir, producer);
    }

    public void editSouvenir(Souvenir souvenir, String newName, List<Producer> newProducers, LocalDate newReleaseDate, double newPrice) {
        dataManager.editSouvenir(souvenir, newName, newProducers, newReleaseDate, newPrice);
        writeSouvenirsToFile(dataManager.getAllSouvenirs(), "src/souvenirs.csv");
    }

    public void editProducer(Producer producer, String newName, String newCountry) {
        dataManager.editProducer(producer, newName, newCountry);
    }

    public List<Souvenir> getAllSouvenirs() {
        return dataManager.getAllSouvenirs();
    }

    public List<Producer> getAllProducers() {
        return dataManager.getAllProducers();
    }

}
