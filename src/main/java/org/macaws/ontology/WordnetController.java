package org.macaws.ontology;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.didion.jwnl.data.IndexWord;
import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.PointerUtils;
import net.sf.extjwnl.data.Synset;
import net.sf.extjwnl.data.Word;
import net.sf.extjwnl.data.list.PointerTargetNodeList;
import net.sf.extjwnl.data.list.PointerTargetTree;


public class WordnetController {
    public Set<String> treeOperation(net.sf.extjwnl.data.IndexWord word) throws JWNLException {
    	// Get all the hyponyms (children) of the first sense of <var>word</var>
        PointerTargetTree hyponyms = PointerUtils.getHyponymTree(word.getSenses().get(0));
        Set<String> wordSet=new LinkedHashSet<String>();
        List<PointerTargetNodeList>	 hyponymsList=hyponyms.toList();
        for (int i = 0; i < hyponymsList.size(); i++) {
        	PointerTargetNodeList pointerTargetNodeList=hyponymsList.get(i);
        	for (int j = 0; j < pointerTargetNodeList.size(); j++) {
        		Synset s=pointerTargetNodeList.get(j).getSynset();
	        	List<Word> words=s.getWords();
	        	for (int k = 0; k < words.size(); k++) {
	        		wordSet.add(words.get(k).getLemma());
				}
			}
		}
        return wordSet;
    }
  
    
    public Set<String> listOperation(net.sf.extjwnl.data.IndexWord word) throws JWNLException {
        // Get all of the hypernyms (parents) of the first sense of <var>word</var>
        PointerTargetNodeList hypernyms = PointerUtils.getDirectHypernyms(word.getSenses().get(0));
        Set<String> wordSet=new LinkedHashSet<String>();
        for (int i = 0; i < hypernyms.size(); i++) {
        	Synset s=hypernyms.get(i).getSynset();
        	List<Word> words=s.getWords();
        	for (int j = 0; j < words.size(); j++) {
        		wordSet.add(words.get(j).getLemma());
			}
		}
        return wordSet;
    }
}
