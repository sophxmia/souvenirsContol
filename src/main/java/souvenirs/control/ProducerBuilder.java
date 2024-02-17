package souvenirs.control;

// usage of Builder pattern
public class ProducerBuilder {
    private String name;
    private String country;

    public ProducerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ProducerBuilder setCountry(String country) {
        this.country = country;
        return this;
    }

    public Producer build() {
        return new Producer(name, country);
    }
}
