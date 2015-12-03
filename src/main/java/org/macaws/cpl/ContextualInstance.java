package org.macaws.cpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Malintha on 12/2/2015.
 */
public class ContextualInstance {

    int id;
    String instanceText;
    String category;
    String promotedOn;
    String promotedIterartion;
    float certainty;
    HashMap<String,String> patternList;
    HashMap<String,Integer> categoryCount;

    public ContextualInstance(int id, String instanceText, String category, String pattern){
        this.instanceText = instanceText;
        this.id = id;
        patternList = new HashMap();
        categoryCount = new HashMap<>();
        patternList.put(category,pattern);
        categoryCount.put(category,1);
    }

    public void addPattern(String pattern, String category){
        patternList.put(category,pattern);
        this.increseCategoryCount(category);
    }

    public void increseCategoryCount(String category){
        Object currentCount = categoryCount.get(category);
        if(currentCount==null){
            categoryCount.put(category,1);
        }
        else{
            categoryCount.replace(category,Integer.valueOf(currentCount.toString()),Integer.valueOf(currentCount.toString())+1);
        }
    }



    public HashMap<String,Float> calculateCategory() {
        Iterator categoryCountIterator = categoryCount.entrySet().iterator();
        int numOfSuggestedCategories = categoryCount.size();
        HashMap<String, Float> returnCategories = new HashMap<>();

        boolean isTeam = false;
        boolean isPlayer = false;
        boolean isStadium = false;
        boolean isRuns = false;
        boolean isWickets = false;
        boolean isUmpire = false;

        if (numOfSuggestedCategories == 1) {
            Map.Entry<String, Integer> pair = (Map.Entry<String, Integer>) categoryCountIterator.next();
            if (pair.getValue() >= 3) {
                this.category = pair.getKey();
                this.certainty = 100;
                returnCategories.put(category, certainty);
            }

        }

        else {

            while (categoryCountIterator.hasNext()) {

                Map.Entry<String, Integer> pair = (Map.Entry<String, Integer>) categoryCountIterator.next();
                //insert in the candidateOntology
                //get the superclass
                //check mutual exclusiveness
                if (pair.getKey().equalsIgnoreCase("team"))
                    isTeam = true;
                if (pair.getKey().equalsIgnoreCase("bowler") || pair.getKey().equalsIgnoreCase("batsman") || pair.getKey().equalsIgnoreCase("captain"))
                    isPlayer = true;
                if (pair.getKey().equalsIgnoreCase("umpire"))
                    isUmpire = true;
                if (pair.getKey().equalsIgnoreCase("runs"))
                    isRuns = true;
                if (pair.getKey().equalsIgnoreCase("wickets"))
                    isWickets = true;
                if (pair.getKey().equalsIgnoreCase("stadium"))
                    isStadium = true;
            }

            boolean[] mutuallyExclusive = {isTeam, isPlayer, isStadium, isRuns, isWickets, isUmpire};
            int count = 0;
            for (boolean b : mutuallyExclusive) {
                if (b)
                    count++;
            }
            System.out.println("ME categories : "+count);
            //if only one ME category
            if (count == 1) {
                ArrayList<String> categories = new ArrayList<>();
                int totalMentions = 0;
                while (categoryCountIterator.hasNext()) {
                    Map.Entry<String, Integer> pair = (Map.Entry<String, Integer>) categoryCountIterator.next();
                    totalMentions+=pair.getValue();
                }

                while (categoryCountIterator.hasNext()) {
                    Map.Entry<String, Integer> pair = (Map.Entry<String, Integer>) categoryCountIterator.next();
                    if(pair.getValue()>=3){
                        float categoryCertainty = (pair.getValue()/totalMentions)*100;
                        returnCategories.put(pair.getKey(),categoryCertainty);
                    }
                }
            }

            //more than one ME categories
            else{
                int totalMentions = 0;
                while (categoryCountIterator.hasNext()) {
                    Map.Entry<String, Integer> pair = (Map.Entry<String, Integer>) categoryCountIterator.next();
                    totalMentions+=pair.getValue();
                }

                while (categoryCountIterator.hasNext()) {
                    Map.Entry<String, Integer> pair = (Map.Entry<String, Integer>) categoryCountIterator.next();
                    if(pair.getValue()>2) {
                        float categoryCertainty = (pair.getValue() / totalMentions) * 100;
                        System.out.println(pair.getKey()+" certainty : "+ categoryCertainty);
                        if (categoryCertainty > 40) {
                            returnCategories.put(pair.getKey(), categoryCertainty);
                        }
                    }
                }
            }


        }
        return returnCategories;
    }

    public ArrayList<String> getCategoricalPatterns(String category){
        ArrayList<String> patternsOfCategory = new ArrayList<>();
        Iterator it = patternList.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String,String> pair = (Map.Entry<String, String>) it.next();
            if(pair.getKey().equalsIgnoreCase(category)){
                patternsOfCategory.add(pair.getValue());
            }
        }
        return patternsOfCategory;
    }



}
