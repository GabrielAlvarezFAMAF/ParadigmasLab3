package com.broken.namedEntities.heuristics;
import java.util.List;
import java.util.ArrayList;

public abstract class Heuristic {
    private String heuristicName;
    private List <String> candidates = new ArrayList<>();
    public Heuristic(String heuristicName){
        this.heuristicName = heuristicName;
    }
    public List<String> getCandidates(){
        return this.candidates;
    }
    public String getHeuristicName(){
        return this.heuristicName;
    }
    public void setHeuristicName(String heuristicName){
        this.heuristicName = heuristicName;
    }
   public abstract List<String> extractCandidates(String text);
}
