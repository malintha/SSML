package org.macaws.cpl;

import java.sql.*;
import java.util.ArrayList;

class DBHandler{
	 static ArrayList<String> getQueue=new ArrayList<String>();
     static ArrayList<String> setQueue=new ArrayList<String>();

    public static int setData(Connection con,String query) throws Exception{
		Statement stm=con.createStatement();
		int res = 0;
          //      System.out.println(setQueue.size());
          //      System.out.println(query);
                setQueue.add(query);
                for(int i=0;i<setQueue.size();i++){
                    String s = setQueue.get(i);
                    res=stm.executeUpdate(s);
                    setQueue.remove(i);
                }
		return res;
	}
	
	public static ResultSet getData(Connection con,String query) throws Exception{
		Statement stm=con.createStatement();
                ResultSet rst = null;
             //   System.out.println(query);
                getQueue.add(query);
                for(int i=0;i<getQueue.size();i++){
                    String s = getQueue.get(i);
                    rst=stm.executeQuery(s);
                    getQueue.remove(i);
                }
		
		return rst;
	}
	
}