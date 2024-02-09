package souvenirs.control;

public class Producer {
    private String name;
    private String country;

    /**
     * Class constructor
     *
     * @param name    producer's name
     * @param country country
     */
    public Producer(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Override the toString() method to have a better view of the object Producer
     *
     * @return Producer
     */
    @Override
    public String toString() {
        return "Producer{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
