package souvenirs.control;

import java.time.LocalDate;
import java.util.List;

public class Souvenir {
    private String name;
    private List<Producer> producers;
    private LocalDate releaseDate;
    private Double price;

    /**
     * Class constructor
     *
     * @param name        name of the product
     * @param producers   producers
     * @param releaseDate releaseDate
     * @param price       price
     */
    public Souvenir(String name, List<Producer> producers, LocalDate releaseDate, double price) {
        this.name = name;
        this.producers = producers;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Producer> getProducers() {
        return producers;
    }

    public void setProducers(List<Producer> producers) {
        this.producers = producers;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Override the toString() method to have a better view of the object Souvenir
     *
     * @return Souvenir
     */
    @Override
    public String toString() {
        return "Souvenir{" +
                "name='" + name + '\'' +
                ", producer=" + producers +
                ", releaseDate=" + releaseDate +
                ", price=" + price +
                '}';
    }
}
