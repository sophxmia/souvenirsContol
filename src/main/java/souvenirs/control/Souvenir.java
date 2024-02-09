package souvenirs.control;

import java.time.LocalDate;

public class Souvenir {
    private String name;
    private Producer producer;
    private LocalDate releaseDate;
    private Double price;

    /**
     * Class constructor
     * @param name name of the product
     * @param producer producer
     * @param releaseDate releaseDate
     * @param price price
     */
    public Souvenir(String name, Producer producer, LocalDate releaseDate, double price){
        this.name = name;
        this.producer = producer;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
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
     * @return Souvenir
     */
    @Override
    public String toString(){
        return "Souvenir{" +
                "name='" + name + '\'' +
                ", producer=" + producer +
                ", releaseDate=" + releaseDate +
                ", price=" + price +
                '}';
    }
}
