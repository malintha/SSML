package org.macaws.cpl;

import java.util.ArrayList;

/**
 * Created by Malintha on 12/2/2015.
 */
public class ContextualInstance {

    int id;
    String instanceText;
    String category;
    String promotedOn;
    String promotedIterartion;
    String certainty;
    ArrayList<String> patternList;

    public ContextualInstance(int id, String instanceText, String category){
        this.instanceText = instanceText;
        this.id = id;
        this.category = category;
        patternList = new ArrayList<String>();
    }

    public void addPattern(String pattern){
        for(String pat:patternList){
            if(pattern.trim().equals(pat.trim())){
                return;
            }
        }
        patternList.add(pattern.trim());
    }


}
