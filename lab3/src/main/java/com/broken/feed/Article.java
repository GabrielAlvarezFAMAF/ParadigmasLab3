package com.broken.feed; 
import java.util.List;
public class Article {
    private String title ; 
    private String descrpition ; 
    private String pubDate ; 
    private String link ; 
    public Article(){
    }
    public Article(String title, String descrpition, String pubDate, String link){
        this.title = title;
        this.descrpition = descrpition;
        this.pubDate = pubDate;
        this.link = link;
    }
    public String getTitle(){
        return title;
    }   
    public String getDescrpition(){
        return descrpition;
    }   
    public String getPubDate(){
        return pubDate;
    }   
    public String getLink(){
        return link;
    }
    public void toString (String title, String descrpition, String pubDate, String link){
        System.out.println ("Title: " + title + "\n" + 
        "Description: " + descrpition + "\n"+ 
        "Publication Date: " + pubDate + "\n" + 
        "Link: " + link+ "\n "+ "******************"
        );
    }
    public static String dataString(List <Article> allArticles){
        String data = ""; 
        for (Article article : allArticles) {
            data = data + article.getTitle() + " " + article.getDescrpition(); 
        }
        return data; 
    }

}