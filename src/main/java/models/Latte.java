package models;

import daos.DTO;

public class Latte implements DTO {

    private Integer id;
    private String size;
    private Integer shots;
    private String flavor;
    private String milk;
    private String temperature;

    public Latte(){}

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getShots() {
        return shots;
    }

    public void setShots(Integer shots) {
        this.shots = shots;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getMilk() {
        return milk;
    }

    public void setMilk(String milk) {
        this.milk = milk;
    }

    public String toString(){
        return "id: " + id + ",  size: " + size + ",  shots: " + shots +
                ",  flavor: " + flavor + ",  milk: " + milk + ",  temperature: " + temperature;
    }
}
