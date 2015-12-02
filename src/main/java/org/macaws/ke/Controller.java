package org.macaws.ke;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import org.macaws.cpl.CPLUtils;

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
    static CPLUtils cu;

    public Controller() throws Exception {
        classLoader = Thread.currentThread().getContextClassLoader();
        cu = new CPLUtils();
    }

    public Controller(int numOfCrawlers, boolean setProxy,int maxDepth, int maxPages) throws Exception {
        classLoader = Thread.currentThread().getContextClassLoader();
        cu = new CPLUtils();
        this.numOfCrawlers = numOfCrawlers;
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        crawlController = new CrawlController(config, pageFetcher, robotstxtServer);
//
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

        crawlController.addSeed("https://en.wikipedia.org/wiki/Sri_Lanka_national_cricket_team");
        crawlController.addSeed("https://en.wikipedia.org/wiki/India_national_cricket_team");
        crawlController.addSeed("https://en.wikipedia.org/wiki/England_cricket_team");
        crawlController.addSeed("https://en.wikipedia.org/wiki/West_Indies_cricket_team");
        crawlController.addSeed("https://en.wikipedia.org/wiki/New_Zealand_national_cricket_team");
        crawlController.addSeed("https://en.wikipedia.org/wiki/South_Africa_national_cricket_team");
        
        crawlController.addSeed("https://en.wikipedia.org/wiki/Nepal_national_cricket_team");
        crawlController.addSeed("https://en.wikipedia.org/wiki/Women%27s_Cricket_World_Cup");
        crawlController.addSeed("https://en.wikipedia.org/wiki/2015_Cricket_World_Cup");
        crawlController.addSeed("https://en.wikipedia.org/wiki/2011_Cricket_World_Cup");
        crawlController.addSeed("https://en.wikipedia.org/wiki/New_Zealand_national_cricket_team");
        crawlController.addSeed("http://www.espncricinfo.com/icc-cricket-world-cup-2015/content/story/856963.html");

        crawlController.addSeed("https://en.wikipedia.org/wiki/Angelo_Mathews");
        crawlController.addSeed("https://en.wikipedia.org/wiki/Tillakaratne_Dilshan");
        crawlController.addSeed("https://en.wikipedia.org/wiki/Lasith_Malinga");
        crawlController.addSeed("https://en.wikipedia.org/wiki/Lahiru_Thirimanne");
        crawlController.addSeed("https://en.wikipedia.org/wiki/Dinesh_Chandimal");
        crawlController.addSeed("https://en.wikipedia.org/wiki/Rangana_Herath");

        crawlController.addSeed("https://en.wikipedia.org/wiki/Kusal_Perera");
        crawlController.addSeed("https://en.wikipedia.org/wiki/Thisara_Perera");
        crawlController.addSeed("https://en.wikipedia.org/wiki/Nuwan_Kulasekara");
        crawlController.addSeed("https://en.wikipedia.org/wiki/Ajantha_Mendis");
        crawlController.addSeed("https://en.wikipedia.org/wiki/Sachithra_Senanayake");
        crawlController.addSeed("https://en.wikipedia.org/wiki/Suranga_Lakmal");

        crawlController.addSeed("https://en.wikipedia.org/wiki/Dhammika_Prasad");
        crawlController.addSeed("https://en.wikipedia.org/wiki/Tharindu_Kaushal");
        crawlController.addSeed("https://en.wikipedia.org/wiki/Milinda_Siriwardana");
        crawlController.addSeed("https://en.wikipedia.org/wiki/Upul_Tharanga");
        crawlController.addSeed("https://en.wikipedia.org/wiki/List_of_One_Day_International_cricket_records");
        crawlController.addSeed("https://en.wikipedia.org/wiki/List_of_Test_cricket_records");

        crawlController.addSeed("https://en.wikipedia.org/wiki/List_of_Twenty20_International_records");
        crawlController.addSeed("https://en.wikipedia.org/wiki/ICC_ODI_Championship");
        crawlController.addSeed("https://en.wikipedia.org/wiki/2015_Ashes_series");
        crawlController.addSeed("https://en.wikipedia.org/wiki/Asia_Cup");
        crawlController.addSeed("https://en.wikipedia.org/wiki/2014_Asia_Cup");
        crawlController.addSeed("https://en.wikipedia.org/wiki/ICC_Player_Rankings");

        crawlController.addSeed("https://en.wikipedia.org/wiki/European_Cricket_Championship");
        crawlController.addSeed("https://en.wikipedia.org/wiki/AB_de_Villiers");
        crawlController.addSeed("https://en.wikipedia.org/wiki/Shane_Watson");
        crawlController.addSeed("https://en.wikipedia.org/wiki/Mitchell_Johnson_(cricketer)");
        crawlController.addSeed("https://en.wikipedia.org/wiki/David_Warner_(cricketer)");
        crawlController.addSeed("https://en.wikipedia.org/wiki/Mitchell_Starc");

        System.out.println("#Done Configurations");
    }
    public synchronized void readFromNet(int iteration) {
        this.currentIteration=iteration;
        mc = new MyCrawler();
        mc.setIteration(iteration);
        crawlController.start(MyCrawler.class,this.numOfCrawlers);
    }

    public String[] fixSentences(int itr) throws FileNotFoundException {
        String[] sentences = null;
        InputStream modelIn = null;
        classLoader = Thread.currentThread().getContextClassLoader();
        try{
            modelIn = classLoader.getResourceAsStream("openNLP/en-sent.bin");

            BufferedReader br = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream("rawTexts/itr_"+itr+".txt")));

            String text = "";
            String line;

            while((line = br.readLine())!=null) {
                line = line.replaceAll("\\[.*\\]","").replaceAll("\\(.*\\)","");
                String tempLine1 = "", tempLine2 = "";
                if(this.doesContainStopWords(line)) {
                    if (line.matches("(.*[a-zA-Z]{1,3}||\"\\.)(\"||[a-zA-Z]+.*\\.)")) {
                        tempLine1 = line.replaceAll("(.*[a-zA-Z]{1,3}||\"\\.)(\"||[a-zA-Z]+.*\\.)", "$1") + " ";
                        tempLine2 = line.replaceAll("(.*[a-zA-Z]{1,3}||\"\\.)(\"||[a-zA-Z]+.*\\.)", "$2");

                    } else {
                        text += line;
                    }

                    if (tempLine1.matches("(.*\\?)(.*)")) {
                        text += tempLine1.replaceAll("(.*\\?)(.*)", "$1");
                        text += tempLine1.replaceAll("(.*\\?)(.*)", "$2");
                    } else {
                        text += tempLine1;
                    }

                    if (tempLine2.matches("(.*\\?)(.*)")) {
                        text += tempLine2.replaceAll("(.*\\?)(.*)", "$1");
                        text += tempLine2.replaceAll("(.*\\?)(.*)", "$2");
                    } else {
                        text += tempLine2;
                    }
                }
                else{
                    continue;
                }
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


    public ArrayList<String> preProcess(int itr) throws IOException {
        String[] rawSentences = fixSentences(itr);

        //remove copyright sentences
        //remove sentences with garbage characters.
        //remove all capital, too spaced sentences.
        //remove repeating dots dots, !!! and hash tags

        ArrayList<String> finedSentences = new ArrayList<String>();

        for(String sentence:rawSentences){
            String tempSentence = null;
            if(!this.doesContaincopyrightWords(sentence)){
                tempSentence = sentence;
            }
            else{
                continue;
            }

            if(tempSentence!=null){
                String posSentence = cu.getPosSentence(tempSentence);
                    if(posSentence.contains("VB") && posSentence.split(" ").length>2) {
                        if(!tempSentence.matches(".*(.\\s){2}.*")){
                            if(!(tempSentence.matches(".*([01]?[0-9]|2[0-3]):[0-5][0-9].*"))){
                                if(!posSentence.matches(".*(VBN\\sCD\\sNNP\\s\\.)$") && !posSentence.matches(".*(VBN\\sNNP\\sCD\\s\\.)$")){
                                    if(!tempSentence.matches("^\\w+\\s+\\w+\\s+[0-9]{1,2}\\s+||\\-\\w+\\s+||\\-[0-9]{4}.*$") && !posSentence.matches("^.*(VBN\\sIN\\sCD\\sNNP).*$"))
                                          if(!tempSentence.matches("^.*([A-Z]{3,}\\s*)+.*$")) {
                                            if (!tempSentence.matches("^.*(\\.{3,}|!{3,}).*$")) {
                                                tempSentence = tempSentence.replaceAll("^[^A-Z|\"]*(.*)","$1");
                                                if(tempSentence.matches(".*\"(.*)\".*")){
                                                    String tempSentence1 = tempSentence.replaceAll(".*\"(.*)\".*","$1");
                                                    if(2*tempSentence1.split(" ").length>tempSentence.split(" ").length){
                                                        tempSentence = tempSentence1;
                                                    }
                                                }
                                                if(tempSentence!=""||tempSentence!=null)
                                                    finedSentences.add(tempSentence);
//                                                System.out.println(tempSentence.replaceAll("(.*)(\\s+\\.)","$1")+ " | "+posSentence.replaceAll("(.*)(\\s+\\.)","$1"));
                                            }
                                        }
                                    //remove sentences with repeated characters like ... or !!!
                                    //remove sentences which has many capital letters
                                }
                            }
                        }

                    }
                }
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

//    public static void main(String[] args) throws Exception {
//        Controller c = new Controller();
//        cu = new CPLUtils();
//        c.readFromNet(2);

//        c.fixSentences(0);
//        ArrayList<String> al = c.preProcess(1);


//        }

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

    public boolean doesContaincopyrightWords(String sentence){

        String[] copyrightWordsList = {"terms of use","privacy policy","registered","trademark","foundation","inc.","organization",
                "non-profit","®","license","may apply","creative commons","may apply","terms","policy","pages","@","#"};

            for(String copyrightWordsListWord:copyrightWordsList){
                if(sentence.toLowerCase().contains(copyrightWordsListWord))
                    return true;
            }
        return false;
    }

}

