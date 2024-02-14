package souvenirs.control;

import java.time.LocalDate;
// usage of Builder pattern
public class SouvenirBuilder {
    private String name;
    private Producer producer;
    private LocalDate releaseDate;
    private Double price;
    public SouvenirBuilder setName(String name){
        this.name = name;
        return this;
    }
    public SouvenirBuilder setProducer(Producer producer){
        this.producer = producer;
        return this;
    }
    public SouvenirBuilder setReleaseDate(LocalDate releaseDate){
        this.releaseDate = releaseDate;
        return this;
    }
    public SouvenirBuilder setPrice(double price){
        this.price = price;
        return this;
    }
    public Souvenir build(){
        return new Souvenir(name, producer, releaseDate, price);
    }
}
