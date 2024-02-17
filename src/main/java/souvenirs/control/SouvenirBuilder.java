package souvenirs.control;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// usage of Builder pattern
public class SouvenirBuilder {
    private String name;
    private List<Producer> producers;
    private LocalDate releaseDate;
    private Double price;

    public SouvenirBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public SouvenirBuilder setProducers(List<Producer> producers) {
        this.producers = producers;
        return this;
    }

    public SouvenirBuilder addProducer(Producer producer) {
        if (this.producers == null) {
            this.producers = new ArrayList<>();
        }
        this.producers.add(producer);
        return this;
    }

    public SouvenirBuilder setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public SouvenirBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public Souvenir build() {
        return new Souvenir(name, producers, releaseDate, price);
    }
}
