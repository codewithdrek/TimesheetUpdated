package com.supraits.dao.impl;

public class GetQueryAPI {
	public static String getQuery(String query, String... args)
	  {
	    StringBuffer temp = new StringBuffer(query);
	    int count = 0;
	    while (temp.indexOf("$") != -1)
	    {
	      count++;
	      temp.deleteCharAt(temp.indexOf("$"));
	    }
	    temp = new StringBuffer(query);
	    

	    if (count != args.length) {
	      return "Arguments mismatch";
	    }
	    
	    count = 1;
	    
	    for (String arg : args)
	    {
	      temp.replace(temp.indexOf("$" + count), temp.indexOf("$" + count) + ("$" + count).length(), arg);
	      
	      count++;
	    }
	    query = temp.toString();
	    
	    return query;
	  }

	public static String getQuery(String m1, String primaryEmail, int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}
}
