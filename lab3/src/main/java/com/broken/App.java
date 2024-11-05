package com.broken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.broken.feed.FeedParser;
import com.broken.namedEntities.NamedEntity;
import com.broken.feed.Article;
import com.broken.utils.Config;
import com.broken.utils.DictionaryData;
import com.broken.utils.FeedsData;
import com.broken.utils.JSONParser;
import com.broken.utils.UserInterface;
import java.net.MalformedURLException;
import com.broken.utils.Stats;
import com.broken.utils.HandleHeuristic;
import java.lang.String;
//prueba 
import java.io.File; 
import java.io.FileWriter;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.SparkSession;

public class App {

    public static void main(String[] args) {
        List<FeedsData> feedsDataArray = new ArrayList<>();
        try { 
            feedsDataArray = JSONParser.parseJsonFeedsData("../lab3/src/main/resources/data/feeds.json");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        UserInterface ui = new UserInterface();
        Config config = ui.handleInput(args, feedsDataArray);
        run(config, feedsDataArray);
    }

    private static void run(Config config, List<FeedsData> feedsDataArray) {
        if (feedsDataArray == null || feedsDataArray.size() == 0) {
            System.out.println("No feeds data found");
            return;
        }
        List<DictionaryData> dataDict = new ArrayList<>();;
        List<NamedEntity> namedEnt = new ArrayList<>();
        try {
            dataDict = JSONParser.parseJsonDictionaryData("../lab3/src/main/resources/data/dictionary.json");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error parsing dictionary data");
        }
        try {
            List<Article> allArticles = new ArrayList<>(); 
            String parser = "";            
            for (FeedsData feed : feedsDataArray){
                parser = FeedParser.fetchFeed(feed.getUrl());
                List<Article> aux = FeedParser.parseXML(parser);
                allArticles.addAll(aux);
                
            }
            
            String name;
            if (config.getBigdata()) {
                name = "../lab3/src/main/resources/data/quijote.txt"; 
            }
            else{
                name = "Feeds.txt";
                String data = Article.dataString(allArticles);
                try {
                    File archivo = new File(name);
                    FileWriter write = new FileWriter(archivo); 
                    write.write(data); 
                    write.close(); 
                    System.out.println("Se ha escrito el contenido en un archivo llamado Feeds");

                } catch (IOException e) {
                    System.err.println("Error al crear o escribir en el archivo: " + e.getMessage());
                }
            }
            

            SparkSession spark = SparkSession.builder()
            .appName("App")
            .getOrCreate();
            
            JavaRDD<String> text = spark.read().textFile(name).javaRDD();
            String lines = text.reduce((s1, s2) -> s1 + " " + s2);

            if (config.getComputeNamedEntities()) {
                String heuristicName = config.getHeuristic();
                System.out.println("Computing named entities using " + heuristicName + " heuristic");
                HandleHeuristic handleHeuristic = new HandleHeuristic();
                // change data for name
                namedEnt = handleHeuristic.handleString(heuristicName, lines, dataDict);
                System.out.println("Named Entities: ");
                for(NamedEntity entity : namedEnt){
                    entity.print(); 
                }
            }
            if(config.getStats() != null){
            // TODO: Print stats
                Stats stats = new Stats(config.getStats()); 
                System.out.println("\nStats: ");
                stats.count(namedEnt);
                stats.printStats(stats.getCount());
            }
            spark.stop();
            System.out.println("\nchauchis");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("Malformed URL");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO Exception");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception");
        }  
    }
}
