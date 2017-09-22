
package com.eunjin.weatherandcody.model.uv;

import java.util.HashMap;
import java.util.Map;

public class CurrentUV {

    private double lat;
    private double lon;
    private String dateIso;
    private int date;
    private double value;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public CurrentUV withLat(double lat) {
        this.lat = lat;
        return this;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public CurrentUV withLon(double lon) {
        this.lon = lon;
        return this;
    }

    public String getDateIso() {
        return dateIso;
    }

    public void setDateIso(String dateIso) {
        this.dateIso = dateIso;
    }

    public CurrentUV withDateIso(String dateIso) {
        this.dateIso = dateIso;
        return this;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public CurrentUV withDate(int date) {
        this.date = date;
        return this;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public CurrentUV withValue(double value) {
        this.value = value;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public CurrentUV withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
