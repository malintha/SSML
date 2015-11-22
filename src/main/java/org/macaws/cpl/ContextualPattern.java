package org.macaws.cpl;

/**
 * Created by Malintha on 11/22/2015.
 */
public class ContextualPattern {
    String category;
    String text;

    public ContextualPattern(String category,String text){
        this.category=category;
        this.text=text;
    }

    public String getCategory(){
        return this.category;
    }
    public String getText(){
        return this.text;
    }
}
