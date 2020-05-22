package com.app.springbootrestapi.constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConstantsUtil {

	public static Date typecastToDate(String inputdate) throws ParseException{
		
		 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
		 Date date = formatter.parse(inputdate);
		 System.out.println(formatter.format(date));
		return  date; 
	}
}
