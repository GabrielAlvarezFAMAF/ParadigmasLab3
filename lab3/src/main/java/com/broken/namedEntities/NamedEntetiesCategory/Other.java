package com.broken.namedEntities.NamedEntetiesCategory;
import com.broken.namedEntities.NamedEntity;
import java.util.List;

public class Other extends NamedEntity {
    private String description;
    public Other(List<String> topics, String name) {
        super("Other", topics, name);
    }
    public Other(List<String> topics, String name, String description) {
        super("Other", topics, name);
        this.description = description;
    }
    public String getDescription(){
        return description;
    }
    
}
