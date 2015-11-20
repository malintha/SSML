package org.macaws.cpl;

import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import org.macaws.ke.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Malintha on 8/3/2015.
 */
public class CPL {
    static ClassLoader classLoader = null;
    static InputStream modelIn = null;
    static PerformanceMonitor perfMon = null;
    static POSModel model = null;
    static ObjectStream<String> lineStream;
    static POSTaggerME tagger;
    static Connection con;

    public static void main(String[] args) throws Exception {
        CPL cpl = new CPL();
        cpl.initialize();
        cpl.runCPL();
    }

    /**
     * Initialize the variables for CPL algorithm
     * @throws Exception
     */

    public void initialize() throws Exception {
        classLoader = Thread.currentThread().getContextClassLoader();
        modelIn = classLoader.getResourceAsStream("openNLP/en-pos-maxent.bin");
        perfMon = new PerformanceMonitor(System.err, "sent");
        model = new POSModel(modelIn);
        tagger = new POSTaggerME(model);
        con = DBCon.getInstance();
    }

    /**
     * Adding patterns to the database
     * @param con
     * @param Pattern
     * @param Category
     * @return
     */

    /**
     * CPL algorithm
     * @throws Exception
     */
    public void runCPL() throws Exception {
        ArrayList<String> batsman = new ArrayList<String>();
        ArrayList<String> bowler = new ArrayList<String>();
        ArrayList<String> team = new ArrayList<String>();

        //getting instances from ontology

        batsman.add("Kumar Sangakkara");
        batsman.add("Mahela Jayawardene");
        batsman.add("Sachin Tendulkar");
        batsman.add("Brendon McCullum");

        bowler.add("Ishant Sharma");
        bowler.add("Brett Lee");
        bowler.add("Shoaib Akhtar");

        team.add("Sri Lanka");
        team.add("India");
        team.add("Pakistan");
        team.add("Australia");

        HashMap<String, ArrayList> instances = new HashMap<String, ArrayList>();
        instances.put("batsman", batsman);
        instances.put("bowler", bowler);
        instances.put("team", team);

        Controller c = new Controller();
        ArrayList<String> s = c.preProcess(1);

        HashMap<String, ArrayList<String>> candidatePatterns = new HashMap<>();
        candidatePatterns.put("batsman",new ArrayList<String>());
        candidatePatterns.put("bowler",new ArrayList<String>());
        candidatePatterns.put("team",new ArrayList<String>());

        ArrayList<String> curList;
        ArrayList<String> incomingList;
        Iterator it = instances.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<String, ArrayList> pair = (Map.Entry<String, ArrayList>) it.next();
            String category = pair.getKey();
            ArrayList<String> instancesInCategory = pair.getValue();
            ArrayList<String> instancesInSentences = new ArrayList<>();
            for (String ins : instancesInCategory) {
//                get instance first/second name
                String[] parts = ins.split(" ");
                String fname = "";
                String lname = "";
                if (parts.length > 1) {
                    fname = parts[0];
                    lname = parts[parts.length-1];
                }
                else {
                    fname = lname = parts[0];
                }

                for (String sentence : s) {
                    if (sentence != null) {
                        if (sentence.contains(fname) || sentence.contains(lname)) {
                            instancesInSentences.add(sentence.trim());
                        }
                    } else {
                        continue;
                    }
                }
                //extract patterns
                curList = candidatePatterns.get(category);
                incomingList = extractFollowingPattern(ins,fname,lname,instancesInSentences);

                if(incomingList.size()!=0) {
                    for (int i = 0; i < incomingList.size(); i++) {
                        curList.add(incomingList.get(i));
                    }
                    candidatePatterns.put(category,curList);
                }
            }
        }

        //store patterns in db
        Iterator candidatePatIterator = candidatePatterns.entrySet().iterator();
        PreparedStatement ps = con.prepareStatement("INSERT INTO candidate_patterns(Category,Pattern) VALUES (?,?)");

        while(candidatePatIterator.hasNext()){
            Map.Entry<String, ArrayList> pair = (Map.Entry<String, ArrayList>) candidatePatIterator.next();
            ArrayList<String> candidatePatternList = pair.getValue();
            String category = pair.getKey();

            for(String p : candidatePatternList){
                ps.setString(1,category);
                ps.setString(2,p);
                ps.executeUpdate();
            }
        }
    }

    //    Extract Following pattern
    public static ArrayList<String> extractFollowingPattern(String ins, String fname, String lname, ArrayList<String> sentencesList) {

        ArrayList<String> patternList = new ArrayList<>();
        ArrayList<String[]> taggedArray = new ArrayList<>();
        HashMap<String, String> posVsSent = new HashMap<>();
        Pattern removeBracketcontent = Pattern.compile("\\((.*)\\)\\s");
        Matcher matcher;
//        String pa = "(NN.){1,4}\\s(VB.)\\s(DT|JJ.{0,1}|RB|\\s)*((NN.{0,1}|\\s)*|(IN|\\s){0,1})";
//        String pa = "(NN.{0,1}){1,4}\\s(VB.|NN)\\s(IN|DT|JJ.{0,1}|RB|\\s)*((NN.{0,1}|\\s)*|(IN|\\s){0,1})";
        String pa = "(NN.{0,1}){1,4}\\s(VB.|NN|IN|CC)\\s(IN|DT|JJ.{0,1}|RB|NN.|\\s)*((NN.{0,1}|\\s)*|(IN|\\s){0,1})";
//        String pa = "(NN.{0,1}){1,4}\\s(VB.|NN|IN|CC)\\s(IN|DT|JJ.{0,1}|RB|NN.|\\s)*((NN.{0,1}|\\s)*|(IN|\\s){0,1})";
        Pattern pat = Pattern.compile(pa);
        Matcher m;

        int juncIndex = 0;

        try {
            for (String s : sentencesList) {
                s = s.trim();
                matcher = removeBracketcontent.matcher(s);
                while (matcher.find()) {
                    s = matcher.replaceAll("");
                }
                if (s.contains(fname))
                    juncIndex = s.indexOf(fname);
                else
                    juncIndex = s.indexOf(lname);
                String followingSub;

                if (juncIndex != -1) {
                    followingSub = s.substring(juncIndex);
                    lineStream = new PlainTextByLineStream(new StringReader(followingSub.trim()));

                    String line;
                    while ((line = lineStream.read()) != null) {
                        String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE.tokenize(line);
                        String[] tags = tagger.tag(whitespaceTokenizerLine);
                        taggedArray.add(tags);
                        String tagSentence = "";
                        for (String t : tags) {
                            tagSentence += t + " ";
                        }
//                    System.out.println(followingSub + "\n" + tagSentence + "\n");
                        posVsSent.put(followingSub, tagSentence.trim());
//                    perfMon.incrementCounter();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Iterator posVsSentIterator = posVsSent.entrySet().iterator();

        while (posVsSentIterator.hasNext()) {
            Map.Entry<String, String> pair = (Map.Entry<String, String>) posVsSentIterator.next();
            String p = pair.getValue();
            String s = pair.getKey();
            m = pat.matcher(p);
            String posLen = "";
            while (m.find()) {
                posLen += m.group();
            }
            int len = posLen.split(" ").length;

            String[] sentW = s.split(" ");
            String patternSentence = "";
            for (int i = 0; i < sentW.length; i++) {
                try {
                    if (sentW[i].equals(fname) || sentW[i].equals(lname)) {
                        int cur = i;
                        while (i <= cur + len) {
                            patternSentence += sentW[i] + " ";
                            i++;
                        }
//                    Insert patternSentence in the pattern list
                        patternSentence = patternSentence.trim();
                        String replaceInstancePattern=ins;
//                        if (patternSentence.split(" ").length > ins.split(" ").length) {
                        if(fname!=lname) {
                            if (patternSentence.matches(".*" + fname + ".*" + lname + ".*"))
                                replaceInstancePattern = fname+".*"+lname;
                            else if(patternSentence.contains(fname))
                                replaceInstancePattern = fname;
                            else if(patternSentence.contains(lname))
                                replaceInstancePattern = lname;
                        }
                        else{
                            replaceInstancePattern = fname;
                        }
                            patternSentence = patternSentence.replaceAll(replaceInstancePattern,"argument");

//                        if(patternSentence.replaceAll(",","").matches("(\\w+\\s{0,1})+"))
                            patternList.add(patternSentence);
                        }
                        break;
//                    }
                } catch (ArrayIndexOutOfBoundsException e) {
//                    System.out.println("### " + s + " ### " + fname + " " + lname + " ### " + len);
                }
            }
        }
//        System.out.println(patternList);
        return patternList;
    }
}






