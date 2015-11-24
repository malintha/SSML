package org.macaws.cpl;

import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * Created by Malintha on 11/24/2015.
 */
public class CPLUtils {

    static ClassLoader classLoader = null;
    static InputStream modelIn = null;
    static PerformanceMonitor perfMon = null;
    static POSModel model = null;
    static ObjectStream<String> lineStream;
    static POSTaggerME tagger;

    public CPLUtils() throws Exception {
        classLoader = Thread.currentThread().getContextClassLoader();
        modelIn = classLoader.getResourceAsStream("openNLP/en-pos-maxent.bin");
        perfMon = new PerformanceMonitor(System.err, "sent");
        model = new POSModel(modelIn);
        tagger = new POSTaggerME(model);
    }


    public String getPosSentence(String sentence) throws IOException {
        lineStream = new PlainTextByLineStream(new StringReader(sentence));
        String line;
        String tagSentence = "";
        while ((line = lineStream.read()) != null) {
            String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE.tokenize(line);
            String[] tags = tagger.tag(whitespaceTokenizerLine);

            for (String t : tags) {
                tagSentence += t + " ";
            }
        }
        return tagSentence.trim();
    }
}
