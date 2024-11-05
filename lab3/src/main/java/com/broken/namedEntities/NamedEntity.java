package com.broken.namedEntities;
import java.util.Date;
import java.util.List;
import java.lang.String;


public class NamedEntity {
   private String category;
   private List <String> topics;   
   private String name ;
   public NamedEntity (String category, List<String> topics, String name) {
       this.category = category ;
       this.topics = topics ;
       this.name = name;
   }
   public String getCategory(){
       return category;
   }
   public List <String>  getTopics(){ 
        return topics;
   }
   public String getName(){
       return name;
   }
   public void print() {
    System.out.println("Category: " + category);
    System.out.println("Topics: " + topics);
    System.out.println("Name: " + name);
    System.out.println("********************************");
    }
}





