package org.macaws.cpl;


import org.macaws.ke.Controller;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by Malintha on 11/23/2015.
 */
class PatternMatchRunnable implements Runnable{

    int threadId;
    LinkedList<ContextualPattern> promotedPatternList;
    int length;
    int upperBound;
    ArrayList<String> corpusSentences;
    Controller c;
    int currentIteration;
    static CPLUtils cplUtils;
    static HashMap<String,String> candidate_instances;
    Connection con;
    PreparedStatement ps;
    DateFormat dateFormat;
    Date date;
    String today;

    public PatternMatchRunnable(int threadId, LinkedList<ContextualPattern> promotedpatternList, int currentIteration) throws Exception {
        this.threadId = threadId;
        this.promotedPatternList = promotedpatternList;
        this.length = promotedpatternList.size();
        this.currentIteration = currentIteration;
        c = new Controller();
        cplUtils = new CPLUtils();
//        this.corpusSentences = c.preProcess(1); //cplUtils.readCorpusFromFile(1);
        this.corpusSentences = cplUtils.readCorpusFromFile(1);
        upperBound = threadId==4?((length/5)*(threadId+1)+(length%5)):((length/5)*(threadId+1));
//        cplUtils = new CPLUtils();
        candidate_instances = new HashMap<>();
        con = DBCon.getInstance();
        ps = con.prepareStatement("INSERT INTO candidate_instances(instance,category,collected_on,matching_patterns, collected_iteration) VALUES (?,?,?,?,?)");
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date = new Date();
        today = dateFormat.format(date);
    }

    @Override
    public void run() {

        for(int i=(length/5)*threadId;i<upperBound;i++){
//            System.out.println("ThreadId = "+threadId+" i ="+i+" pattern : "+promotedPatternList.get(i).getText());
            String patternText = promotedPatternList.get(i).getText();
            String patternWords = patternText.replaceAll("argument","").trim();
            String patternCategory = promotedPatternList.get(i).getCategory();
            String patternPos = cplUtils.getPosSentence(patternText);
            String patternTextRegex = null;
            String precedingWordSplitter = "";

            if(patternPos.contains("CD")) {
                int CDIndex = 0;
                for (int j = 0; j < patternPos.split(" ").length; j++) {
                    if (patternPos.split(" ")[j].equals("CD"))
                        CDIndex = j;
                }
                String[] patternWordsSplitArray = patternWords.split(" ");
                patternWordsSplitArray[CDIndex-1] = "[a-zA-Z_0-9]{1,}";
                patternText = "";
                for (int j = 0; j < patternWordsSplitArray.length; j++)
                    patternText += patternWordsSplitArray[j] + " ";

                patternText=patternText.trim();
                patternTextRegex = ".*"+patternText+".*";
                precedingWordSplitter = patternWords.split(" ")[0];
            }
            if(patternTextRegex==null) {
                patternTextRegex = ".*" + patternWords.trim() + ".*";
                precedingWordSplitter = patternWords;
            }

            System.out.println(i+" "+patternTextRegex + " |"+patternWords);

            for(String sentence: corpusSentences){
                //matches proper nouns, not scores
//                String patternRegex = ".*([a-zA-Z]{1,}\\s?){1,3}"+patternText.replaceAll("argument","")+".*";
                if(sentence.matches(patternTextRegex)){
                    String precedingWords = sentence.substring(0, sentence.indexOf(precedingWordSplitter));
//                    System.out.println(precedingWords);
                    String posSentence = cplUtils.getPosSentence(sentence.replace("-","").replaceAll("^(.*)\\.$","$1"));
                    String[] precedingWordsArray = precedingWords.split(" ");
                    String[] posSentenceArray = posSentence.trim().split(" ");
                    String extraction="";
                    String tempExtraction = "";

                    if(precedingWordsArray.length>=3){
                        for(int j=precedingWordsArray.length-3;j<precedingWordsArray.length;j++){
                            if(posSentenceArray[j].matches("NNP.?")) {
                                tempExtraction += precedingWordsArray[j] + " ";
                                //remove and, at, against, vs, versus, , ,
                                //if second word is a conjuction, remove the first word
                            }
                            if(j==precedingWordsArray.length-2 && tempExtraction.contains(",")){
                                tempExtraction="";
                            }
                            if(j==precedingWordsArray.length-2 && posSentenceArray[j].matches("IN||CD.?")){
                                tempExtraction="";
                            }
                            extraction=tempExtraction;
                        }
                    }
                    else{
                        for(int j=0;j<precedingWordsArray.length;j++){
                            if(posSentenceArray[j].matches("NNP"))
                            extraction+=precedingWordsArray[j]+" ";
                        }
                    }
                    if(extraction.trim().matches("^[A-Z_0-9][a-zA-Z_0-9]*$")) {
                        System.out.println(extraction + " | " + patternCategory + " | " + patternText+" | "+today);
                        this.storeInstanceInDB(extraction.trim(),patternCategory,patternWords);
                    }

                }
            }
        }
    }

    public void storeInstanceInDB(String instance, String category,String extractedPattern) {
        //store patterns in db
        try {
            ps.setString(1,instance);
            ps.setString(2,category);
            ps.setString(3,today);
            ps.setString(4,extractedPattern);
            ps.setInt(5,currentIteration);
            ps.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

            }

    }
