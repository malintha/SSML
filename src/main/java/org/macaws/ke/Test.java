package org.macaws.ke;

/**
 * Created by Malintha on 10/14/2015.
 */
public class Test {
    public static void main(String[] args) {

        String s = "plays for Delhi in domestic cricket and has taken 68 wickets in 14 first-class games, [9] [10] including a five-wicket haul";

        String pat = "(\\w*)(\\^*)((\\[\\d+\\])*)(\\^*)(\\w*)";
        System.out.println(s.matches(pat));
        String s1 = s.replaceAll(pat,"$1$6");
        System.out.println(s1);
        System.out.println(s1.replaceAll("(\\w*)([\\.,]*)(\\s)(\\s*)([\\.,]*)(\\w*)","$1$2$3$5$6"));
    }

}
