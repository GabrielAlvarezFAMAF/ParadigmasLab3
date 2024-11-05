package com.broken.utils;
import java.util.List;

import com.broken.namedEntities.NamedEntity;
import com.broken.namedEntities.heuristics.CapitalizedWordHeuristic;
import com.broken.namedEntities.heuristics.FiltredCwh;
import com.broken.namedEntities.heuristics.Heuristic;
import com.broken.namedEntities.heuristics.SemanticNeighborg;
import com.broken.namedEntities.NamedEntetiesCategory.*;
import com.broken.namedEntities.NamedEntity;
import java.util.ArrayList;

public class HandleHeuristic {
    public List<NamedEntity> handleString(String confHeuristic, String fetchedUrl, List<DictionaryData> dataDict) {
        List<NamedEntity> namedEnt = new ArrayList<>();
        List<String> words = new ArrayList<>();

        Heuristic heuristic;
        if (confHeuristic.equals("FiltredCwh")) {
            heuristic = new FiltredCwh(confHeuristic); 
        } else if (confHeuristic.equals("Smnbh")){
            heuristic = new SemanticNeighborg(confHeuristic); 
        }else {
            heuristic = new CapitalizedWordHeuristic(confHeuristic); 
        }
        words = heuristic.extractCandidates(fetchedUrl); 
        for (String word : words) {
            for (DictionaryData data : dataDict) {
                for (String datakey : data.getKeyword()){
                    if (word.equals(datakey.replaceAll("[\\[\\]\"]", ""))) {
                        NamedEntity namedEntiy = new NamedEntity(data.getCategory(), data.getTopic(), data.getLabel());
                        switch (data.getCategory()) {
                            case "Organization":
                                namedEntiy = new Organization(data.getTopic(), data.getLabel());
                                break;
                            case "Person":
                                namedEntiy = new Person(data.getTopic(), data.getLabel());
                                break;
                            case "Other":
                                namedEntiy = new Other(data.getTopic(), data.getLabel());
                                break;
                            case "Location":
                                namedEntiy = new Location(data.getTopic(), data.getLabel());
                                break;
                            
                            default:
                                break;
                        }
                        namedEnt.add(namedEntiy);
                    }
                }
            }
        }
        return namedEnt;
    }
    
}

