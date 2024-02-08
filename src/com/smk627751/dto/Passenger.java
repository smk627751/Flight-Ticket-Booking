package com.smk627751.dto;

import java.io.Serial;
import java.io.Serializable;

public class Passenger implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String name;
    private byte age;
    private String gender;
    private int id;
    private String status;
    public Passenger(String name,byte age,String gender,int id)
    {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.id = id;
        this.status = "CNF";
    }

    public String toString()
    {
        return "Name :"+name+" || Age : "+age+" || gender : "+gender+" || ID :"+id+"|| Status: "+status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }
}
