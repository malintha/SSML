package org.macaws.ke;

import opennlp.model.Sequence;
import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import org.apache.poi.hssf.record.formula.functions.Match;
import org.macaws.cpl.CPLUtils;

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

        String sample = "Mahela Jayawardana was bought for a winning bid of $950,000 by Kolkata Knight Riders in the player auction for the Indian Premier League";


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
                int juncIndex = s.indexOf("Mahela Jayawardana");
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
//            perfMon.stopAndPrintFinalResult();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Extract following pattern
        String pa = "(NN.{0,1}){1,4}\\s(VB.|NN|IN|CC){1,2}\\s(IN|DT|JJ.{0,1}|RB|VB.|\\s)*((NN.{0,1}|\\s)*|(IN|\\s){0,1})";
        Pattern pat = Pattern.compile(pa);
        Matcher m = null;
        Iterator posVsSentIterator = posVsSent.entrySet().iterator();
        while(posVsSentIterator.hasNext()) {
            Map.Entry<String, String> pair = (Map.Entry<String, String>) posVsSentIterator.next();
            String p = pair.getValue();
            String s = pair.getKey();
            m = pat.matcher(p);
            String posLen = "";
            while (m.find()) {
                posLen += m.group();
                System.out.print(m.groupCount());
            }
            int len = posLen.split(" ").length;
            System.out.println("\n"+posLen);
            String[] sentW = s.split(" ");
            for (int i = 0; i < sentW.length; i++) {
                if (sentW[i].equals("Mahela")) {
                    int cur = i;
                    while (i <= cur + len) {
                        System.out.print(sentW[i] + " ");
                        i++;
                    }
                    System.out.println();
                }

            }
        }
    }

    public static void main(String[] args) throws Exception {

        float a;
        a = 3/7;
        System.out.println(a);

        HashMap<String,Integer> hm = new HashMap<>();
        hm.put("Name",1);
        hm.put("hhh",3);
        Object k =  hm.get("hhh");
        if(k==null)
            System.out.println(0);
        else
            System.out.println(Integer.valueOf(k.toString()));
//CPLUtils cu = new CPLUtils();
//        ArrayList<String> al = cu.readCorpusFromFile(1);
//
//for(String s: al)
//            if(s.matches("(.*)\\sis\\sa.*\\s(.*) Cricketer.")) {
//                System.out.println("Sentence : "+ s);
//                String ar1 = s.replaceAll("(.*) is an (.*) Cricketer.","$1");
//                String ar2 = s.replaceAll("(.*) is an (.*) Cricketer.","$2");
//                System.out.println("argument 1 : " + ar1 + " , argument 2 : " + ar2);
//                System.out.println("Predicate : IsAMemberOfTeam("+ ar1+", "+ar2+")");
//                System.out.println();
//
//        }

//        String patternText = "argument took three wickets";
//        System.out.println(patternText.substring(0,patternText.indexOf("took")));
//        Pattern p = Pattern.compile("passed 14,000 [a-zA-Z_0-9]{1,} runs");
//        Matcher m = p.matcher("");
//        CPLUtils cu = new CPLUtils();
//        String patternPos = cu.getPosSentence(patternText);
//
//        if(patternPos.contains("CD")) {
//            int CDIndex = 0;
//            for (int j = 0; j < patternPos.split(" ").length; j++) {
//                if (patternPos.split(" ")[j].equals("CD"))
//                    CDIndex = j;
//            }
//            String[] patternTextSplitArray = patternText.split(" ");
//            patternTextSplitArray[CDIndex] = "[a-zA-Z_0-9]{1,}";
//            patternText = "";
//            for(int j=0;j<patternTextSplitArray.length;j++)
//                patternText+=patternTextSplitArray[j]+" ";
//            System.out.println(patternText);
//            System.out.println("Chaminda Vaas took 8 wickets for 19 runs in 2001/02 against Zimbabwe".matches(".*"+patternText.replaceAll("argument","").trim()+".*"));
//        }
//        ArrayList<String> al = cu.readCorpusFromFile(1);
//        for(String ss:al)
//            System.out.println(ss);



//        test();
//        String s = "Ishant Sharma (born 2 September 1988) is an Indian cricketer who has represented India in Tests, ODIs and T20Is.";
//        Pattern p = Pattern.compile("\\((.*)\\)\\s");
//        Matcher m = p.matcher(s);
//        String r = "";
//        while(m.find()){
//            r=m.replaceAll("");
////            System.out.println(m.group());
//        }
//        System.out.println(r);

//        NN.{0,1}\sVB.\s(DT|JJ|RB|\s)*((NN.{0,1}|\s)*|(IN|\s){0,1})
//        test();

//        String pa = "(NN.){1,2}\\sVB.\\s(DT|JJ.{0,1}|RB|\\s)*((NN.{0,1}|\\s)*|(IN|\\s){0,1})";
//        Pattern p = Pattern.compile(pa);
//        Matcher m = p.matcher("NNP VBD DT JJ JJS NNP TO VB CD CD .");
//        String posLen = "";
//        String sent = "Against South Africa in 2013, Ishant Sharma became the fifth quickest Indian to grab 100 ODI wickets.";
//        while (m.find()){
//            posLen+=m.group();
//        }
//        System.out.println(posLen);
//        int len = posLen.split(" ").length;
//
//        String[] sentW = sent.split(" ");
//        for(int i = 0;i<sentW.length;i++){
//            if(sentW[i].equals("Ishant")){
//                int cur = i;
//                while(i<=cur+len) {
//                    System.out.print(sentW[i]+" ");
//                    i++;
//                }
//            }
//        }
//

//        String fname = "Kumar";
//        String lname = "Sangakkara";
//
//        String s = "Kumar Chokshananda Sangakkara resigned the captaincy after, Sangakkara has 38";
//        String p;
//        if(fname!=lname)
//            p = fname+".*"+lname;
//        else
//            p = fname;
//
//        System.out.println(s.replaceAll(p,"xx"));
//        Pattern p1 = Pattern.compile(p);
//        Matcher m = p1.matcher(s);
//        while(m.find()) {
//            System.out.println(m.group());
//        }


//        System.out.println("Mahela,Sangakkara resigned the captaincy after".matches("(\\w\\s{0,1})+"));

    }
    public static String removeCommas(String s){
        String commaBetweenWords = "(\\w\\s{0,1})*(\\,)(\\s{0,1}\\w\\s{0,1})*";
        String commaBetweenScore = "(.*)(\\d+)(\\,)(\\d+)(.*)";
        String commaBetweenBowling = ".*\\d+\\,\\d+\\,\\d+\\,\\d+";

        if(s.matches(commaBetweenWords))
            s = s.replaceAll(",","");
        System.out.println(s);
        if(s.matches(commaBetweenScore))
            s = s.replaceAll(commaBetweenScore,"$1$2$4$5");
        return s;
    }
}
