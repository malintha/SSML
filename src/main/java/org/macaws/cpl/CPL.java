package org.macaws.cpl;

import org.macaws.ke.Controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Malintha on 8/3/2015.
 */
public class CPL {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> batsman = new ArrayList<String>();
        ArrayList<String> bowler = new ArrayList<String>();
        ArrayList<String> team = new ArrayList<String>();

        batsman.add("Kumar Sangakkara");
        batsman.add("Mahela Jayawardene");
        batsman.add("Sachin Tendulkar");

        bowler.add("Ishant Sharma");
        bowler.add("Brett Lee");
        bowler.add("Shoaib Akhtar");
        bowler.add("Mitchell Johnson");

        team.add("Sri Lanka");
        team.add("India");
        team.add("Pakistan");
        team.add("Australia");

        HashMap<String,ArrayList> instances = new HashMap<String,ArrayList>();
        instances.put("batsman",batsman);
        instances.put("bowler", bowler);
        instances.put("team", team);

        Controller c = new Controller();
        ArrayList<String> s = c.preProcess(1);

        HashMap<String,String[]> patterns = new HashMap<String,String[]>();

        Iterator it = instances.entrySet().iterator();

        while(it.hasNext()){
            Map.Entry<String, ArrayList> pair = (Map.Entry<String, ArrayList>) it.next();
            String category = pair.getKey();
            ArrayList<String> instancesInCategory = pair.getValue();
            for(String ins:instancesInCategory){
                for(String sentence:s){
                    if(sentence!=null) {
                        if (sentence.contains(ins)) {
                            System.out.println(sentence);


                        }
                    }
                    else{
                        continue;
                    }
                }
            }
        }
    }




}
