package org.macaws.ke;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class WebCrawlController {

    static CrawlController controller;

    static boolean setPageLimit = true;
    static boolean reVisitUrl = true;

    public static void main(String[] args) throws Exception {
        String crawlStorageFolder = "/data/crawl/root";
        int numberOfCrawlers = 7;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setProxyHost("cache.mrt.ac.lk");
        config.setProxyPort(3128);
//        config.setProxyUsername("114178r");
//        config.setProxyPassword("Malinthak1@yahoo.com");
        config.setMaxDepthOfCrawling(5);
        config.setMaxPagesToFetch(100);
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        controller = new CrawlController(config, pageFetcher, robotstxtServer);

//        controller.addSeed("http://en.wikipedia.org/wiki/Kumar_Sangakkara");
        controller.addSeed("https://en.wikipedia.org/wiki/Ishant_Sharma");
//        controller.addSeed("https://en.wikipedia.org/wiki/Brett_Lee");
//        controller.addSeed("https://en.wikipedia.org/wiki/Ricky_Ponting");
//        controller.addSeed("https://en.wikipedia.org/wiki/Brendon_McCullum");
//        controller.addSeed("https://en.wikipedia.org/wiki/Saeed_Anwar");

        controller.start(MyCrawler.class, numberOfCrawlers);


    }
}
