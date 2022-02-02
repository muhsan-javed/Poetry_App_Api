package com.muhsantech.poetryappapi.Model;

public class PoetryModel {

    int id;
    String poetry_data;
    String poet_name;
    String dateTime;

    public PoetryModel(int id, String poetry_data, String poet_name, String dateTime) {
        this.id = id;
        this.poetry_data = poetry_data;
        this.poet_name = poet_name;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoetry_data() {
        return poetry_data;
    }

    public void setPoetry_data(String poetry_data) {
        this.poetry_data = poetry_data;
    }

    public String getPoet_name() {
        return poet_name;
    }

    public void setPoet_name(String poet_name) {
        this.poet_name = poet_name;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
