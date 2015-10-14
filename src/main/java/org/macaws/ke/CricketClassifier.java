package org.macaws.ke;

import com.datumbox.opensource.classifiers.NaiveBayes;
import com.datumbox.opensource.dataobjects.NaiveBayesKnowledgeBase;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Malintha on 8/25/2015.
 */

public class CricketClassifier {

    public NaiveBayesKnowledgeBase trainCricketClassifier(String[] positiveTrainingExamples, String[] negativeTrainingExamples) throws IOException {
        Map<String, String> trainingFiles = new HashMap<String, String>();
        System.out.println(System.getProperty("user.dir"));
        for (String fileName : positiveTrainingExamples) {
            trainingFiles.put("Cricket", fileName);
        }
        for (String fileName : negativeTrainingExamples) {
            trainingFiles.put("notCricket", fileName);
        }
        Map<String, String[]> trainingExamples = new HashMap();
        for (Map.Entry<String, String> entry : trainingFiles.entrySet()) {
            trainingExamples.put(entry.getKey(), readLines(entry.getValue()));
        }
        NaiveBayes nb = new NaiveBayes();
        nb.setChisquareCriticalValue(10);
        nb.train(trainingExamples);
        NaiveBayesKnowledgeBase knowledgeBase = nb.getKnowledgeBase();
        System.out.println("# Done Training");
        return knowledgeBase;
    }

    public String predict(String webContent, NaiveBayesKnowledgeBase nbkb) {
        NaiveBayes nb = new NaiveBayes(nbkb);
        String output = nb.predict(webContent);
        return output;
    }

    public String[] readLines(String url) throws IOException {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(url);
        List<String> lines;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        lines = new ArrayList<String>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        return lines.toArray(new String[lines.size()]);
    }

    NaiveBayesKnowledgeBase nbkb = null;

    public void trainWithExamples(){
        CricketClassifier cc = new CricketClassifier();
        String[] positiveExamples = {"Cricket_1.txt","Cricket_2.txt","Cricket_3.txt","Cricket_4.txt","Cricket_5.txt"};
        String[] negativeExamples = {"notCricket_1.txt","notCricket_2.txt","notCricket_3.txt","notCricket_4.txt","notCricket_5.txt","notCricket_6.txt"};

        try {
            nbkb = cc.trainCricketClassifier(positiveExamples, negativeExamples);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) throws IOException {
//
//        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("Cricket1.txt");
//        BufferedReader br = new BufferedReader(new InputStreamReader(is));
//        while(br.readLine()!=null)
//            System.out.println(br.readLine());
//        CricketClassifier cc = new CricketClassifier();
//        String[] positiveExamples = {"Cricket_1.txt","Cricket_2.txt","Cricket_3.txt","Cricket_4.txt","Cricket_5.txt"};
//        String[] negativeExamples = {"notCricket_1.txt","notCricket_2.txt","notCricket_3.txt","notCricket_4.txt","notCricket_5.txt","notCricket_6.txt"};
//        try {
//            NaiveBayesKnowledgeBase nbkb = cc.trainCricketClassifier(positiveExamples,negativeExamples);
//
//            String[] evalArray = cc.readLines("Eval_2.txt");
//            StringBuilder sb = new StringBuilder();
//            for(String S : evalArray)
//                sb.append(S);
//            String evalString = sb.toString();
//            String op = cc.predict(evalString, nbkb);
//            System.out.println(op);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
