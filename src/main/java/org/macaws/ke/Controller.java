package org.macaws.ke;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Malintha on 10/13/2015.
 */
public class Controller {

    static CrawlController crawlController;
    static CrawlConfig config;

    static String crawlStorageFolder = "/data/crawl/root";
    static int numOfCrawlers;
    static String proxyHost = "cache.mrt.ac.lk";
    static int proxyPort = 3128;
    static String proxyUser = "114178r";
    static String proxyPw = "Malinthak1@yahoo.com";
    static int currentIteration;
    static MyCrawler mc;
    ClassLoader classLoader;
    public Controller(){}

    public Controller(int numOfCrawlers, boolean setProxy,int maxDepth, int maxPages) throws Exception {
        classLoader = Thread.currentThread().getContextClassLoader();

        this.numOfCrawlers = numOfCrawlers;
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        crawlController = new CrawlController(config, pageFetcher, robotstxtServer);

        if(setProxy){
            config.setProxyHost(proxyHost);
            config.setProxyPort(proxyPort);
//            config.setProxyUsername(proxyUser);
//            config.setProxyPassword(proxyPw);
        }
        if(maxDepth!=0)
            config.setMaxDepthOfCrawling(maxDepth);
        if(maxPages!=0)
            config.setMaxPagesToFetch(maxPages);

        crawlController.addSeed("http://en.wikipedia.org/wiki/Kumar_Sangakkara");
        crawlController.addSeed("https://en.wikipedia.org/wiki/Ishant_Sharma");
        crawlController.addSeed("https://en.wikipedia.org/wiki/Brett_Lee");
        crawlController.addSeed("https://en.wikipedia.org/wiki/Ricky_Ponting");
        crawlController.addSeed("https://en.wikipedia.org/wiki/Brendon_McCullum");
        crawlController.addSeed("https://en.wikipedia.org/wiki/Saeed_Anwar");

        System.out.println("#Done Configurations");
    }
    public synchronized void readFromNet(int iteration) {
        this.currentIteration=iteration;
        mc = new MyCrawler();
        mc.setIteration(iteration);
        System.out.println("#Iteration "+iteration);
        crawlController.start(MyCrawler.class,this.numOfCrawlers);
    }

    public String[] fixSentences(int itr) throws FileNotFoundException {
        String[] sentences = null;
        InputStream modelIn = null;
        classLoader = Thread.currentThread().getContextClassLoader();
        try{
            modelIn = classLoader.getResourceAsStream("openNLP/en-sent.bin");

            BufferedReader br = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream("rawTexts/itr_"+itr+".txt")));


            //create 10 threads to process each sentence
//            ExecutorService threadPool = Executors.newFixedThreadPool(10);
//            for(int i=0;i<10;i++){
//                threadPool.submit(new preprocessCorpusRunnable());
//            }

            String text = "";
            String line;

            while((line = br.readLine())!=null) {
                line = line.replaceAll("\\[.*\\]","").replaceAll("\\(.*\\)","");
                if(!this.doesContainStopWords(line))
                    System.out.println("##### "+line);
                else
                    text+=line;
            }

            SentenceModel model = new SentenceModel(modelIn);
            SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
            sentences = sentenceDetector.sentDetect(text);

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (modelIn != null) {
                try {
                    modelIn.close();
                }
                catch (IOException e) {
                }
            }
            return sentences;
        }
    }


    public ArrayList<String> preProcess(int itr) throws FileNotFoundException {
        String[] rawSentences = fixSentences(itr);
        ArrayList<String> finedSentences = new ArrayList<String>();
        String removeSqrBrackets = "(\\w*)(\\^*)((\\[\\.*\\])*)(\\^*)(\\w*)";
        String groupSqrBrackets = "$1$6";
        String removeExtraSpaces = "(\\w*)([\\.,]*)(\\s)(\\s*)([\\.,]*)(\\w*)";
        String addSpaceBetweenSentences = "(.*\\.)(\\w+.*)";
        String groupExtraSpaces = "$1$2$3$5$6";
        String temp,temp1,temp2,temp3;


        for(int i=0;i<rawSentences.length;i++) {
            temp = rawSentences[i].replaceAll(removeSqrBrackets, groupSqrBrackets);
            temp1 = temp.replaceAll(removeExtraSpaces,groupExtraSpaces);
            if(temp1.matches(addSpaceBetweenSentences)){
                temp2=temp1.replaceAll(addSpaceBetweenSentences,"$1");
                temp3=temp1.replaceAll(addSpaceBetweenSentences,"$2");
                finedSentences.add(temp2);
                finedSentences.add(temp3);
            }
            else
                finedSentences.add(temp1);
        }
    return finedSentences;
    }

    public String removeCommas(String s){
        String commaBetweenWords = "(\\w+)(\\s{0,1})(\\,)(\\w+)";
        String commaBetweenScore = "(.*)(\\d+)(\\,)(\\d+)(.*)";
        String commaBetweenBowling = ".*\\d+\\,\\d+\\,\\d+\\,\\d+";

        if(s.matches(commaBetweenWords))
            s = s.replaceAll(commaBetweenWords,"$1$2$4");
        if(s.matches(commaBetweenScore))
            s = s.replaceAll(commaBetweenScore,"$1$2$4$5");
        return s;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Controller c = new Controller();
//        c.fixSentences(0);
        String[] al = c.fixSentences(1);

//        String hin = "On 18 August 2012, Laxman announced his retirement from international cricket";
//        System.out.println(c.isContainsStopWords(hin));

        for(String s:al)
            System.out.println(s);
//            if(!c.doesContainStopWords(s))
//            String s1 = s.replaceAll("\\(.*\\)","");
//                System.out.println(s1.replaceAll("\\[.*\\]",""));


        }

    public boolean doesContainStopWords(String sentence){
        String[] stopWordsList = {"a", "an", "and", "are", "as", "at", "be", "but", "by",
                "for", "if", "in", "into", "is", "it",
                "no", "not", "of", "on", "or", "such",
                "that", "the", "their", "then", "there", "these",
                "they", "this", "to", "was", "will", "with"};
        String[] sentenceWords = sentence.split(" ");
        for (int j=0;j<sentenceWords.length;j++)
            for(int i=0;i<stopWordsList.length;i++){
                if(sentenceWords[j].equals(stopWordsList[i]))
                    return true;
            }

        return false;
    }

}

