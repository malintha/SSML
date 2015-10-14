package org.macaws.ke;

import de.l3s.boilerpipe.extractors.ArticleExtractor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;

/**
 * Created by Malintha on 10/13/2015.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        try {
//            Controller c = new Controller(5,true,5,200);
//            c.crawlController.start(MyCrawler.class,5);
//            c.readFromNet(1);
            Controller c = new Controller();
            String[] s = c.preProcess(1);
            for(int i=0;i<1000;i++)
                System.out.println(s[i]);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
