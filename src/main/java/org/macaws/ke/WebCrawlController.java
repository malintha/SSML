package org.macaws.ke;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class WebCrawlController {

    static CrawlController crawlController;

    static boolean setPageLimit = true;
    static boolean reVisitUrl = true;

    public static void main(String[] args) throws Exception {
        String crawlStorageFolder = "/data/crawl/root";
        int numberOfCrawlers = 20;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setProxyHost("cache.mrt.ac.lk");
        config.setProxyPort(3128);
//        config.setProxyUsername("114178r");
//        config.setProxyPassword("Malinthak1@yahoo.com");
        config.setMaxDepthOfCrawling(5);
        config.setMaxPagesToFetch(1000);
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        crawlController = new CrawlController(config, pageFetcher, robotstxtServer);

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

        crawlController.start(MyCrawler.class, numberOfCrawlers);


    }
}
