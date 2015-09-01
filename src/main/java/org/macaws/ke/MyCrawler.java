package org.macaws.ke;

import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.regex.Pattern;


public class MyCrawler extends WebCrawler {

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
            + "|png|mp3|mp3|zip|gz))$");

    /**
     * This method receives two parameters. The first parameter is the page
     * in which we have discovered this new url and the second parameter is
     * the new url. You should implement this function to specify whether
     * the given url should be crawled or not (based on your crawling logic).
     * In this example, we are instructing the crawler to ignore urls that
     * have css, js, git, ... extensions and to only accept urls that start
     * with "http://www.ics.uci.edu/". In this case, we didn't need the
     * referringPage parameter to make the decision.
     */

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        return !FILTERS.matcher(href).matches();
    }

    /**
     * This function is called when a page is fetched and ready
     * to be processed by your program.
     */
    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String text = htmlParseData.getText();
            String html = htmlParseData.getHtml();
            Set<WebURL> links = htmlParseData.getOutgoingUrls();
//            System.out.println(html);
            try {
                System.out.println(ArticleExtractor.INSTANCE.getText(html));
                System.out.println("##########Extraction Done###########");
            } catch (BoilerpipeProcessingException e) {
                e.printStackTrace();
            }
//            extractArticle(url);
        }
    }

    public boolean isCricketURL(String aUrl) {
        String text = null;
        try {
            URL url = new URL(aUrl);
            text = ArticleExtractor.INSTANCE.getText(url);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (BoilerpipeProcessingException e) {
            e.printStackTrace();
        }
/**
 * implement document classification, return true if it is a cricket article
 */

        return false;
    }

    public void extractArticle(String aUrl) {
        try {
/**
 * instead of passing the url to boilerpipe, try inside
 */
            URL url = new URL(aUrl);
            String text = ArticleExtractor.INSTANCE.getText(url);
            System.out.println(text);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (BoilerpipeProcessingException e) {
            e.printStackTrace();
        }

    }

}

