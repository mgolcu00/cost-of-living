package com.example.cost_of_livng_v01;

import java.util.ArrayList;

public class City {
    private String name;
    private int id;
    private String country;
    private String Location;
    private int countryId;
    private Countries Country = new Countries();
    public City(){

    }
    public City(int id,String name,String country) {
        this.id=id;
        this.name=name;
        this.country=country;
    }

    public String getName() {
        return name;
    }
    public String getName2(int contId,int cityId){
        String name2 = "";
        name2=Country.countries[contId][cityId];
        return name2;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }
    public String getCountry2(int id) {
        String country2="";
        country2 = Country.countries[id][0];
        return country2;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public static ArrayList<City> getData(){
        ArrayList<City> CityList= new ArrayList<City>();
        int idx[] = new int[6];


        for (int i=0 ;i<idx.length;i++ ){
            City temp =new City();
            temp.setName(temp.getName2(i,1));
            temp.setId(i);
            temp.setCountry(temp.getCountry2(i));
            CityList.add(temp);
        }
        return CityList;

    }


}
