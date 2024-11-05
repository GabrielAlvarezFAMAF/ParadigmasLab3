package com.broken.namedEntities.NamedEntetiesCategory;
import com.broken.namedEntities.NamedEntity;
import java.util.List;

public class Location extends NamedEntity {
    private String longitud; 
    private String latitud;
    public Location(List<String> topics, String name) {
        super("Location", topics, name);
    }
    public Location(List<String> topics, String name, String longitud, String latitud) {
        super("Location", topics, name);
        this.longitud = longitud;
        this.latitud = latitud;
    }
    public String getLongitud(){
        return longitud;
    }
    public String getLatitud(){
        return latitud;
    }
}