package org.macaws.cpl;

import org.macaws.ke.Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Malintha on 11/23/2015.
 */
class PatternMatchRunnable implements Runnable{

    int threadId;
    ArrayList<ContextualPattern> promotedPatternList;
    int length;
    int upperBound;
    ArrayList<String> corpusSentences;
    Controller c;
    int currentIteration;

    public PatternMatchRunnable(int threadId, ArrayList<ContextualPattern> promotedpatternList, int currentIteration) throws IOException {
        this.threadId = threadId;
        this.promotedPatternList = promotedpatternList;
        this.length = promotedpatternList.size();
        this.currentIteration = currentIteration;
        c = new Controller();
        this.corpusSentences = c.preProcess(currentIteration);
        upperBound = threadId==4?((length/5)*(threadId+1)+(length%5)):((length/5)*(threadId+1));
    }

    @Override
    public void run() {

        for(int i=(length/5)*threadId;i<upperBound;i++){
            String patternText = promotedPatternList.get(i).getText();
            String patternCategory = promotedPatternList.get(i).getCategory();
            for(String sentence: corpusSentences){
                String filteredSentence = sentence.replaceAll("\\W","");
                String filteredPatternText = patternText.replaceAll("\\W","");
//                System.out.println(filteredSentence+" | "+filteredPatternText);
                if(filteredSentence.contains(filteredPatternText.replaceAll("argument",""))){
                    System.out.println(sentence+" | "+patternText);
                }
            }



        }
    }
}