package org.eauction.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class DateUtility {

	public static Timestamp convertUtilToSqlDate(String strDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
		LocalDateTime dateTime = LocalDateTime.parse(strDate, formatter);
		
		Timestamp ts = Timestamp.valueOf(dateTime);
		
		return ts ;
	}
	
	public static LocalDateTime convertSqlToUtilDate(Timestamp ts) {
		LocalDateTime ldt = ts.toLocalDateTime();
		return ldt ;
	}
	
	public static boolean isBidDateValid(Timestamp ts) {
		boolean isValid = false ;
		try {
			LocalDateTime bidEndDate = convertSqlToUtilDate(ts);
			LocalDateTime curLdt = LocalDateTime.now() ;

			if(curLdt.isBefore(bidEndDate) || curLdt.isEqual(bidEndDate)) {
				isValid = true ;
			}else {
				isValid = false ;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return isValid ;
	}
	
	public static Timestamp getBidDateTimeSql() {
		LocalDateTime date = LocalDateTime.now() ;
		date = date.plusDays(5) ;
		System.out.println(date);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String str = date.format(dtf);
		System.out.println(str);
		date = LocalDateTime.parse(str, dtf);
		Timestamp ts = Timestamp.valueOf(date);
		return ts ;
	}
	
	public static void main(String[] args) {
//		Timestamp ts = Timestamp.valueOf(LocalDateTime.now().plusDays(5));
//		boolean isValid = DateUtility.isBidDateValid(ts) ;
//		System.out.println(" isValid = "+isValid);
		
//		getBidDateTimeSql();
//		
		convertUtilToSqlDate("2022-11-03 09:10:00");
	}
}
