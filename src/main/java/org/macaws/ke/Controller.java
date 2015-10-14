package org.macaws.ke;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

import java.io.*;

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
            String line;
            String text = "";
            while((line=br.readLine())!=null)
                text+=line;

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

    public void preProcess(int itr) throws FileNotFoundException {
        String[] rawSentences = fixSentences(itr);

    }

}
