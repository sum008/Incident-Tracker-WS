package com.incident.tool.utiliy;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class DateUtility {
	
	public String getCurrentDate (String format) {
		return new SimpleDateFormat(format).format(new Date());
	}

}
