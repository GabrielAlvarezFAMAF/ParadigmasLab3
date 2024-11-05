package com.broken.utils;
import java.util.List;
import java.util.ArrayList;

public class DictionaryData {
    private String label; 
    private String category; 
    private List<String> topic=new ArrayList<>();
    private String [] keyword;
    public DictionaryData(String label, String category, String [] topic, String [] keyword) {
        this.label = label;
        this.category = category;
        for (String top : topic){
            this.topic.add(top);
        }
        this.keyword = keyword;
    }
    public String getLabel(){
        return label;
    }
    public String getCategory(){
        return category;
    }
    public List<String> getTopic(){
        return topic;
    }
    public String [] getKeyword(){
        return keyword;
    }
}
