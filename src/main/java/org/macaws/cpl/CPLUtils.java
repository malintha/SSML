package org.macaws.cpl;

import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import org.macaws.ke.Controller;

import java.io.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Malintha on 11/24/2015.
 */
public class CPLUtils {

    static ClassLoader classLoader = null;
    static InputStream modelIn = null;
    static PerformanceMonitor perfMon = null;
    static POSModel model = null;
    static ObjectStream<String> lineStream;
    static POSTaggerME tagger;
    static ArrayList<String> sentCorpus;
    static HashMap<String, ArrayList<String>> mutuallyExclusiveCategories;

    public CPLUtils() throws Exception {
        classLoader = Thread.currentThread().getContextClassLoader();
        modelIn = classLoader.getResourceAsStream("openNLP/en-pos-maxent.bin");
        perfMon = new PerformanceMonitor(System.err, "sent");
        model = new POSModel(modelIn);
        tagger = new POSTaggerME(model);

    }

    public void setSentCorpus(int iteration){
        sentCorpus = this.readCorpusFromFile(iteration);
    }

    public String getPosSentence(String sentence) {
        lineStream = new PlainTextByLineStream(new StringReader(sentence));
        String line;
        String tagSentence = "";
        try {
            while ((line = lineStream.read()) != null) {
                String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE.tokenize(line);
                String[] tags = tagger.tag(whitespaceTokenizerLine);

                for (String t : tags) {
                    tagSentence += t + " ";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tagSentence.trim();
    }

    public void writeCorpusToFile(int currentIteration) {
        //write refined corpus to text
        try {
            Controller controller = new Controller();
            ArrayList<String> al = controller.preProcess(currentIteration);
            File f = new File("src\\main\\resources\\FinedTexts\\" + currentIteration + ".txt");
            if (!f.exists()) {
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src\\main\\resources\\FinedTexts\\" + currentIteration + ".txt", true)));
                for (String s : al)
                    out.write(s+"\n");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    public ArrayList<String> readCorpusFromFile (int currentIteration){
        BufferedReader br = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream("FinedTexts/"+currentIteration+".txt")));
        ArrayList<String> finedTextsArrayList = new ArrayList<>();
        String line;
        try {
            while ((line = br.readLine()) != null) {
                finedTextsArrayList.add(line);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return finedTextsArrayList;
    }

    public int getOccurancesInCorpus(String text){
        int occurrance = 0;
        for(String s:sentCorpus){
            if(s.contains(text))
                occurrance++;
        }
        return occurrance;
    }




}
