package app;

public class Address {
    private final int house;
    private final String postcode;
    private final String city;
    
    public Address(
        int house,
        String postcode,
        String city
    ) {
        this.house = house;
        this.postcode = postcode;
        this.city = city;
    }

    public int getHouse() {return house;}
    public String getPostcode() {return postcode;}
    public String getCity() {return city;}
}