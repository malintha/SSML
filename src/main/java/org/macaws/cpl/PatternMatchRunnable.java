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
    static CPLUtils cplUtils;

    public PatternMatchRunnable(int threadId, ArrayList<ContextualPattern> promotedpatternList, int currentIteration) throws Exception {
        this.threadId = threadId;
        this.promotedPatternList = promotedpatternList;
        this.length = promotedpatternList.size();
        this.currentIteration = currentIteration;
        c = new Controller();
        this.corpusSentences = c.preProcess(1);
        upperBound = threadId==4?((length/5)*(threadId+1)+(length%5)):((length/5)*(threadId+1));
        cplUtils = new CPLUtils();
    }

    @Override
    public void run() {

        for(int i=(length/5)*threadId;i<upperBound;i++){
            String patternText = promotedPatternList.get(i).getText();
            String patternWords = patternText.replaceAll("argument","");
            String patternCategory = promotedPatternList.get(i).getCategory();

            for(String sentence: corpusSentences){
                //matches proper nouns, not scores
//                String patternRegex = ".*([a-zA-Z]{1,}\\s?){1,3}"+patternText.replaceAll("argument","")+".*";
                if(sentence.contains(patternText.replaceAll("argument", ""))){
                    String precedingWords = sentence.substring(0, sentence.indexOf(patternWords));
                    String posSentence = cplUtils.getPosSentence(sentence.replace("-","").replaceAll("^(.*)\\.$","$1"));
                    String[] precedingWordsArray = precedingWords.split(" ");
                    String[] posSentenceArray = posSentence.trim().split(" ");
                    String extraction="";
                    if(precedingWordsArray.length>=3){
                        for(int j=precedingWordsArray.length-3;j<precedingWordsArray.length;j++){
                            if(posSentenceArray[j].matches("NN.{0,2}"))
                            extraction+=precedingWordsArray[j]+" ";
                        }
                    }
                    else{
                        for(int j=0;j<precedingWordsArray.length;j++){
                            if(posSentenceArray[j].matches("NN.{0,2}"))
                            extraction+=precedingWordsArray[j]+" ";
                        }
                    }
                    if(extraction!=""||extraction!=null)
                    System.out.println(sentence+" | "+extraction+" | "+patternWords);
                }
            }



        }
    }
}