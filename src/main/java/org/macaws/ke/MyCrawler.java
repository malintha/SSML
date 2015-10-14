package org.macaws.ke;

import com.datumbox.opensource.dataobjects.NaiveBayesKnowledgeBase;
import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;
import de.l3s.boilerpipe.extractors.ArticleSentencesExtractor;
import de.l3s.boilerpipe.extractors.CanolaExtractor;
import de.l3s.boilerpipe.extractors.LargestContentExtractor;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.regex.Pattern;


public class MyCrawler extends WebCrawler {

    private final static boolean setCricketFilter = false;
    ClassLoader classLoader = null;

    String fileName = "itr";
    int iteration=4;
    boolean initializeWriter = false;
    boolean getTheKB = false;
    PrintWriter out;
    NaiveBayesKnowledgeBase nbkb;
    CricketClassifier cc = new CricketClassifier();
    String ccPredictionOP;

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
            + "|png|mp3|mp3|zip|gz))$");

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        if(!FILTERS.matcher(href).matches()) {
            //add url to the ridis db
            return true;
        }
        else
            return false;
    }

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String text = htmlParseData.getText();
            String html = htmlParseData.getHtml();
            Set<WebURL> links = htmlParseData.getOutgoingUrls();
            try {
/**
 * Using canola Extractor
 */
                String content = ArticleExtractor.INSTANCE.getText(html);
                writeToFile(content);

            } catch (BoilerpipeProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeToFile(String content) throws IOException {
        classLoader = Thread.currentThread().getContextClassLoader();

        if(!initializeWriter) {
            File f = new File("src\\main\\resources\\rawTexts\\"+fileName+"_"+iteration+".txt");
            if(f.exists())
                f.delete();
            out = new PrintWriter(new BufferedWriter(new FileWriter("src\\main\\resources\\rawTexts\\"+fileName+"_"+iteration+".txt", true)));
            initializeWriter = true;
        }

        if(setCricketFilter){
            if(!getTheKB){
                cc.trainWithExamples();
                nbkb = cc.nbkb;
                getTheKB = true;
            }

            ccPredictionOP = cc.predict(content,nbkb);
            if(ccPredictionOP.equals("cricket"))
                out.write(content);
        }
        else
            out.write(content+"\n\n\n");

        System.out.println("#DONE");
    }
    public void setIteration(int iteration){
        this.iteration=iteration;
    }
}

