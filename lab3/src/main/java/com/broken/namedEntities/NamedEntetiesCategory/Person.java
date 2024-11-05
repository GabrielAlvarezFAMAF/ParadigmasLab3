package com.broken.namedEntities.NamedEntetiesCategory;
import com.broken.namedEntities.NamedEntity;
import java.util.Date;
import java.util.List;

public class Person extends NamedEntity {
    private Date birth;
    private int age;
    private int height; 
    public Person( List<String> topics, String name) {
        super("Person", topics, name);
    }
    public Person(List<String> topics, String name, Date birth, int age, int height) {
        super("Person", topics, name);
        this.birth = birth;
        this.age = age;
        this.height = height;
    }
    public Date getBirth(){
        return birth;
    }
    public int getAge(){
        return age;
    }
    public int getHeight(){
        return height;
    }
}
