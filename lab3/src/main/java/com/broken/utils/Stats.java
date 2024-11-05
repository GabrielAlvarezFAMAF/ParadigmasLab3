
package com.broken.utils;
import java.util.HashMap;
import java.util.List;

import com.broken.namedEntities.NamedEntity;

public class Stats {
    private String format;
    private HashMap<String, Integer> Count;
    public Stats(String format) {
        this.format = format;
        Count= new HashMap<>();
    }
    public HashMap<String,Integer> count( List<NamedEntity> entitys ){
        if (format.equals("cat") || format.equals("category")) {
            for (NamedEntity entity : entitys) {
                if (!Count.containsKey(entity.getCategory())) {
                    Count.put(entity.getCategory(),1);
                }else {
                    Count.put(entity.getCategory(),Count.get(entity.getCategory()) + 1);
                }
            }
        }
        else if (format.equals("topic")) {
            for (NamedEntity entity : entitys) {
                List<String> topics = entity.getTopics();
                for (String topic : topics){
                    topic = topic.replaceAll("[\\[\\]\"]", "");
                    if (!Count.containsKey(topic)) {
                        Count.put(topic,1);
                    }else {
                        Count.put(topic, Count.get(topic) + 1);
                    }   
                }
            }
        }
        return Count;
    }
    public void printStats(HashMap<String,Integer> Count){
        for (String key : Count.keySet()) {
            System.out.println(key + " : " + Count.get(key));
        }
        System.out.println("-".repeat(80));
    }
    public HashMap<String,Integer> getCount(){
        return Count;
    }
    public String getFormat(){
        return format;
    }
}