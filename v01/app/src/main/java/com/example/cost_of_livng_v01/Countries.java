package com.example.cost_of_livng_v01;


public class Countries {

    public String[][] countries = new String[10][6];



    public Countries(){
        countries[0][0] = "Turkey";
        countries[1][0] = "Germany";
        countries[2][0] = "France";
        countries[3][0] = "Russia";
        countries[4][0] = "USA";
        countries[5][0] = "England";
        setCities();
    }
    public void setCities(){
        countries[0][1]="Istanbul";
        countries[1][1]="Berlin";
        countries[2][1]="Paris";
        countries[3][1]="Moscow";
        countries[4][1]="New York";
        countries[5][1]="London";
    }



}
