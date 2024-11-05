package com.broken.utils;
public class Config {
    private boolean computeNamedEntities = false;
    private boolean bigdata = false;
    private String stats;
    private String heuristic; 

    public Config(boolean bigdata, boolean computeNamedEntities, String heuristic, String stats) {        
        this.bigdata = bigdata; 
        this.computeNamedEntities = computeNamedEntities;
        this.heuristic = heuristic;
        this.stats = stats;
    }
    public boolean getBigdata() {
        return bigdata;
    }
    public boolean getComputeNamedEntities() {
        return computeNamedEntities;
    }
    public String getHeuristic() {
        return heuristic;
    }
    public String getStats() {
        return stats;
    }
}
