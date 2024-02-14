package souvenirs.control;

import java.time.LocalDate;
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

    public List<Souvenir> readSouvenirsFromFile(String filename) {
        return fileHandler.readSouvenirsFromFile(filename);
    }

    public List<Producer> readProducersFromFile(String filename) {
        return fileHandler.readProducersFromFile(filename);
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

    public void displaySouvenirsByYear() {
        dataManager.displaySouvenirsByYear();
    }

    public void deleteProducerAndSouvenirs(Producer producer){
        dataManager.deleteProducerAndSouvenirs(producer);
    }

    public void addSouvenir(Souvenir souvenir) {
        dataManager.addSouvenir(souvenir);
    }

    public void addProducer(Producer producer) {
        dataManager.addProducer(producer);
    }

    public void editSouvenir(Souvenir souvenir, String newName, Producer newProducer, LocalDate newReleaseDate, double newPrice) {
        dataManager.editSouvenir(souvenir, newName, newProducer, newReleaseDate, newPrice);
    }

    public void editProducer(Producer producer, String newName, String newCountry) {
        dataManager.editProducer(producer, newName, newCountry);
    }

    public void displayAllSouvenirs() {
        dataManager.displayAllSouvenirs();
    }

    public void displayAllProducers() {
        dataManager.displayAllProducers();
    }
}
