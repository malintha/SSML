package org.macaws.cpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 * Created by Malintha on 11/20/2015.
 */
public class BatchInsert {
    public static void main(String[] args) throws Exception {
        Connection con = DBCon.getInstance();
        PreparedStatement ps = con.prepareStatement("INSERT INTO promoted_patterns(Category,Pattern,promotedOn) VALUES (?,?,?)");
        ps.setString(1, "Batsman");
        ArrayList<String> al = new ArrayList<>();
        al.add("argument has 38 centuries");
        al.add("argument scored");
        al.add("argument has scored");
        al.add("argument made 64 not-out");
        al.add("argument became the second highest run scorer in One-Day International");
        al.add("argument passed 14,000 ODI runs");
        al.add("argument averages");
        al.add("argument scored his");
        al.add("argument scored his 23rd century");
        al.add("argument by most international runs");

        for(String s:al) {

            ps.setString(2, s);
            ps.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
            ps.execute();
        }
    }
}
