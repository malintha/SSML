package org.macaws.ke;

import opennlp.model.Sequence;
import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Malintha on 10/14/2015.
 */
public class Test {
    public static void test() {

//        String s = "plays for Delhi in domestic cricket and has taken 68 wickets in 14 first-class games, [9] [10] including a five-wicket haul";
//        String s2 = "The next Indian tour proved to be disastrous for the team, with Sri Lanka being beaten by India in Test series 2–0 and ODI series 3–1.Sri Lankan team under the captaincy of Sangakkara gained momentum and won the next Tri-series in Bangladesh , Zimbabwe and Sri Lanka , beating India as well.plays for Delhi in domestic cricket and has taken 68 wickets in 14 first-class games";
//
//        String p = "(.*\\.)(\\w+.*)";
//        System.out.println(s2.replaceAll(p,"$1 \n$2"));
//        if(s2.matches(p)){
//            String[] t = s2.split(s2);
//            for(String t1:t)
//                System.out.println(t);
//        }
//        String pat = "(\\w*)(\\^*)((\\[\\d+\\])*)(\\^*)(\\w*)";
//        System.out.println(s.matches(pat));
//        String s1 = s.replaceAll(pat,"$1$6");
//        System.out.println(s1);
//        System.out.println(s1.replaceAll("(\\w*)([\\.,]*)(\\s)(\\s*)([\\.,]*)(\\w*)","$1$2$3$5$6"));

        String sample = "Ishant Sharma is an Indian cricketer who has represented India in Tests, ODIs and T20Is.\n" +
                "Against South Africa in 2013, Ishant Sharma became the fifth quickest Indian to grab 100 ODI wickets.\n" +
                "On the basis of his strong performance in Australia in 2008, Ishant Sharma was bought for a winning bid of $950,000 by Kolkata Knight Riders in the player auction for the Indian Premier League .\n" +
                "Varun Aaron , the Jharkhand fast bowler who has replaced Ishant Sharma in India's ODI squad for the England series, said he will not cut back on his pace as his career progresses because he loves bowling fast.\n" +
                "Indian fast bowlers have a history of dropping their pace dramatically as their careers take off - Irfan Pathan, Munaf Patel and to some extent Ishant Sharma - but Aaron, who regularly clocks over 140kph and impressed for India Emerging Players during the recent tournament in Australia, said he will not go down that road.\"It's\n" +
                "wolf777 on August 22, 2011, 23:20 GMTFetish of picking only four bowlers in the eleven will leave him overworked and pretty soon he will join Ishant Sharma, Zaheer Khan and others in rehabilitation.\n" +
                "Ishant Sharma close to that fate himself.";


        String obamaSample[] = new String[] {"Obama is no Lincoln","Obama is a good president","Obama became the first American President","Obama beats McCain","Obama bought airtime","Obama energizes the base","Obama is the only President","Obama is the new Hitler","Obama is half-white","Obama is an impressive person","Obama is an empty suit","Obama is not President","Obama is leading McCain","Obama is not the right candidate","Obama's Inauguration Day","Obama's Iowa campaign","Obama's Inaugural Address","Obama's historic nomination","Obama's foreign policy team","Obama accepts the Democratic nomination","Obama's campaign today","Obama's Republican colleagues","Obama's New Hampshire campaign","Obama's CIA director","Obama's DOJ","Obama's popular vote lead","Obama's running-mate","Obama's presidential leadership","Obama gets the Democratic nomination","Obama beat Hillary","Obama got more delegates","Obama become the next President","Obama becomes the Democratic nominee","Obama came out swinging at","Obama did not win the nomination","Obama didn't win the nomination","Obama conceded the election","Obama entered Harvard Law School","Obama's economic team","Obama's health care plan","Obama's presidency","Obama's tax cut plan","Obama's proposed economic stimulus plan","Obama's the right person","Obama is a uniter","Obama is a gifted politician","Obama is a great President","Obama is a United States Senator","Obama is the presumptive nominee","Obama picked Joe Biden","Obama lost Tennessee","Obama's judicial nominees","Obama's inauguration today","Obama's new nominee","Obama's military credentials","Obama's incoming chief","Obama's inauguration Tuesday","Obama's executive orders"};


        String[] samplearr = sample.split("\n");
        ArrayList<String[]> taggedArray = new ArrayList<>();
        ArrayList<String> tagsArray = new ArrayList<String>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream modelIn = classLoader.getResourceAsStream("openNLP/en-pos-maxent.bin");
        PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
        POSModel model = null;
        HashMap<String,String> posVsSent = new HashMap<String,String>();
        try {
            model = new POSModel(modelIn);
            POSTaggerME tagger = new POSTaggerME(model);
            ObjectStream<String> lineStream;
            perfMon.start();
            for (String s : samplearr) {
                s = s.trim();
                int juncIndex = s.indexOf("Ishant Sharma");
                String followingSub = s.substring(juncIndex);
//                System.out.println(followingSub.trim());

                lineStream = new PlainTextByLineStream(new StringReader(followingSub.trim()));
                String line;
                while ((line = lineStream.read()) != null) {
                    String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE.tokenize(line);
                    String[] tags = tagger.tag(whitespaceTokenizerLine);
                    taggedArray.add(tags);
                    String tagSentence = "";
                    for (String t : tags) {
                        tagSentence+=t+" ";
                    }
                    System.out.println(followingSub+"\n"+tagSentence+"\n");
                    posVsSent.put(followingSub, tagSentence.trim());
                    perfMon.incrementCounter();
                }
            }
            perfMon.stopAndPrintFinalResult();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Extract following pattern
        String pa = "(NN.){1,2}\\sVB.\\s(DT|JJ.{0,1}|RB|\\s)*((NN.{0,1}|\\s)*|(IN|\\s){0,1})";
        Pattern pat = Pattern.compile(pa);
        Matcher m = null;
        Iterator posVsSentIterator = posVsSent.entrySet().iterator();
        while(posVsSentIterator.hasNext()){
            Map.Entry<String,String> pair = (Map.Entry<String, String>) posVsSentIterator.next();
            String p = pair.getValue();
            String s = pair.getKey();
            m = pat.matcher(p);
            while (m.find()){
                System.out.print("Start index: " + m.start());
                System.out.print(" End index: " + m.end() + " ");
                System.out.println(m.group());
                System.out.println(s);
            }

        }
    }

    public static void main(String[] args) {
//        NN.{0,1}\sVB.\s(DT|JJ|RB|\s)*((NN.{0,1}|\s)*|(IN|\s){0,1})
//        test();

        String pa = "(NN.){1,2}\\sVB.\\s(DT|JJ.{0,1}|RB|\\s)*((NN.{0,1}|\\s)*|(IN|\\s){0,1})";
        Pattern p = Pattern.compile(pa);
        Matcher m = p.matcher("NNP VBD DT JJ JJS NNP TO VB CD CD .");
        String posLen = "";
        String sent = "Against South Africa in 2013, Ishant Sharma became the fifth quickest Indian to grab 100 ODI wickets.";
        while (m.find()){
//            System.out.println("Start index: " + m.start());
//            System.out.println("End index: " + m.end() + " ");
            posLen+=m.group();
        }
        System.out.println(posLen);
        int len = posLen.split(" ").length;

        String[] sentW = sent.split(" ");
        for(int i = 0;i<sentW.length;i++){
            if(sentW[i].equals("Ishant")){
                int cur = i;
                while(i<=cur+len) {
                    System.out.print(sentW[i]+" ");
                    i++;
                }
            }
        }

    }
}
