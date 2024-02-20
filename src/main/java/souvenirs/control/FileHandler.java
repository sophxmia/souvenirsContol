package souvenirs.control;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    // Singleton pattern usage
    private static FileHandler instance;

    private FileHandler() {
    }

    public static FileHandler getInstance() {
        if (instance == null) {
            instance = new FileHandler();
        }
        return instance;
    }

    public List<Souvenir> readSouvenirsFromFile(String fileName) {
        List<Souvenir> souvenirs = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 0; // Add this line
            while ((line = reader.readLine()) != null) {
                lineNumber++; // Add this line
                String[] data = line.split((","));
                if (data.length < 4) { // Add this condition to handle incomplete lines
                    System.err.println("Incomplete data in line " + lineNumber + ": " + line);
                    continue; // Skip this line
                }
                try {
                    String name = data[0];
                    String[] producersData = data[1].split(",");
                    List<Producer> producers = new ArrayList<>();
                    for (String producerData : producersData) {
                        String[] producerInfo = producerData.split("\\(");
                        String producerName = producerInfo[0];
                        String country = producerInfo[1].substring(0, producerInfo[1].length() - 1);
                        Producer producer = new Producer(producerName, country);
                        producers.add(producer);
                    }
                    LocalDate releaseDate = LocalDate.parse(data[2], formatter);
                    double price = Double.parseDouble(data[3]);
                    Souvenir souvenir = new Souvenir(name, producers, releaseDate, price);
                    souvenirs.add(souvenir);
                } catch (Exception e) {
                    System.err.println("Error parsing line " + lineNumber + ": " + line);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return souvenirs;
    }


    public void writeSouvenirsToFile(List<Souvenir> souvenirs, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            for (Souvenir souvenir : souvenirs) {
                String releaseDateFormatted = souvenir.getReleaseDate().format(formatter);
                StringBuilder producersData = new StringBuilder();
                for (Producer producer : souvenir.getProducers()) {
                    producersData.append(producer.getName()).append("(").append(producer.getCountry()).append(")");
                }
                writer.write(souvenir.getName() + "," + producersData + "," +
                        releaseDateFormatted + "," + souvenir.getPrice() + "\n");
            }
        } catch (IOException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Producer> readProducersFromFile(String fileName) {
        List<Producer> producers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0];
                String country = data[1];

                Producer producer = new Producer(name, country);
                producers.add(producer);
            }
        } catch (IOException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }
        return producers;
    }

    public void writeProducersToFile(List<Producer> producers, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Producer producer : producers) {
                writer.write(producer.getName() + ","
                        + producer.getCountry() + "\n");
            }
        } catch (IOException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
