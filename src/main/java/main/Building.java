package main;

import java.util.Objects;

public class Building {

    private String city;

    private String street;

    private int floor;

    private int houseNumber;

    public Building(String city,
                    String street,
                    int floor,
                    int houseNumber) {
        this.city = city;
        this.street = street;
        this.floor = floor;
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return floor == building.floor
                && Objects.equals(city, building.city)
                && Objects.equals(houseNumber, building.houseNumber)
                && Objects.equals(street, building.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, floor);
    }

    @Override
    public String toString() {
        return "Здание : {" +
                "\n\tгород : " + city +
                "\n\tулица : " + street +
                "\n\tколичество этажей : " + floor +
                "\n\tномер дома : " + houseNumber +
                "\n}";
    }
}
