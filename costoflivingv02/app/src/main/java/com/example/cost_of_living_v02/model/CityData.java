package com.example.cost_of_living_v02.model;

public class CityData {
    private String id, name, CountryName,currency;
    private Double salary,milk, bread;
    private float  gasoline, oneWayTicket,   rice, apple, beef, Utilites, internetCost;
    private int population, starbucksCount, LibraryCount;

    public CityData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getBread() {
        return bread;
    }

    public void setBread(Double bread) {
        this.bread = bread;
    }

    public Double getMilk() {
        return milk;
    }

    public void setMilk(Double milk) {
        this.milk = milk;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}

