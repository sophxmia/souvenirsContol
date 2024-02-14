package souvenirs.control;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    // Singleton pattern usage
     private static FileHandler instance;

     private FileHandler(){}

    public static FileHandler getInstance(){
         if(instance == null){
             instance = new FileHandler();
         }
         return instance;
    }

    public List<Souvenir> readSouvenirsFromFile(String fileName) {
        List<Souvenir> souvenirs = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split((","));
                String name = data[0];
                String producerName = data[1];
                String country = data[2];
                LocalDate releaseDate = LocalDate.parse(data[3], formatter);
                double price = Double.parseDouble(data[4]);

                Producer producer = new Producer(producerName, country);
                Souvenir souvenir = new Souvenir(name, producer, releaseDate, price);
                souvenirs.add(souvenir);
            }
        } catch (IOException e) {
//            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return souvenirs;
    }

    public void writeSouvenirsToFile(List<Souvenir> souvenirs, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            for (Souvenir souvenir : souvenirs) {
                String releaseDateFormatted = souvenir.getReleaseDate().format(formatter);
                writer.write(souvenir.getName() + "," + souvenir.getProducer().getName() + "," +
                        souvenir.getProducer().getCountry() + "," + releaseDateFormatted + "," +
                        souvenir.getPrice() + "\n");
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
