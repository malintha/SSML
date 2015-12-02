package org.macaws.ke;

import de.l3s.boilerpipe.extractors.ArticleExtractor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.macaws.cpl.CPLUtils;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Malintha on 10/13/2015.
 */
public class Client {
    public static void main(String[] args) throws Exception {
//        Controller c = new Controller(10,false,5,1000);
//        c.readFromNet(4);
//        ArrayList<String> al =  c.preProcess(4);
        CPLUtils cplUtils = new CPLUtils();
//        cplUtils.writeCorpusToFile(4);
        ArrayList<String> sentences = cplUtils.readCorpusFromFile(4);
        for(String s:sentences)
            System.out.println(s);
    }

}
