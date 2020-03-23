package com.example.internshiptask;

public class CountriesDTO {
    private String name;
    private String capital;
    private String region;
    private String subregion;
    private long population;
    private double area;
    private String nativeName;

    public CountriesDTO(String name, String capital, String region, String subregion, long population, double area, String nativeName, String callingCodes) {
        this.name = name;
        this.capital = capital;
        this.region = region;
        this.subregion = subregion;
        this.population = population;
        this.area = area;
        this.nativeName = nativeName;

    }

    public String getName() {
        return name;
    }


    public String getCapital() {
        return capital;
    }


    public String getRegion() {
        return region;
    }


    public String getSubregion() {
        return subregion;
    }


    public long getPopulation() {
        return population;
    }


    public double getArea() {
        return area;
    }


    public String getNumericCode() {
        return nativeName;
    }



}
