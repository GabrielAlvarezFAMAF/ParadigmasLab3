package com.broken.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class UserInterface {
    private HashMap<String, String> optionDict;
    private List<Option> options;
    private void handleNoArguments(String arg, Option option,List<FeedsData> feedsDataArray ) {
        if (arg.equals("-sf")) {
            optionDict.put(option.getName(), "cat");
        }
        else{
            System.out.println("Invalid inputs");
            System.out.println("\nType -h to print help");
            System.exit(1);
        }
    }

    public UserInterface() {
        options = new ArrayList<Option>();
        options.add(new Option("-ne", "--named-entity", 1));
        options.add(new Option("-sf", "--stats-format", 1));
        options.add(new Option("-b", "--bigdata", 0));
        optionDict = new HashMap<String, String>();
    }

    public Config handleInput(String[] args, List<FeedsData> feedsDataArray) {
        for (Integer i = 0; i < args.length; i++) {
            for (Option option : options) {
                if (option.getName().equals(args[i]) || option.getLongName().equals(args[i])) {
                    if (option.getnumValues() == 0) {
                        optionDict.put(option.getName(), null);
                    } else {
                        if (i + 1 < args.length && !args[i + 1].startsWith("-")) { 
                            optionDict.put(option.getName(), args[i + 1]);
                            i++;
                        }
                        else {
                            handleNoArguments(args[i], option, feedsDataArray);
                        }
                    }
                }
            }
        }    
        Boolean bigdata = optionDict.containsKey("-b"); 
        Boolean computeNamedEntities = optionDict.containsKey("-ne");
        String heuristic = optionDict.get("-ne"); 
        String stringStat = optionDict.get("-sf"); 
        return new Config(bigdata, computeNamedEntities, heuristic, stringStat);
    }
}