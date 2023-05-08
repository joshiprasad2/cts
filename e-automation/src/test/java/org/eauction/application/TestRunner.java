package org.eauction.application;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

	 public static void main(String[] args) {
//	      Result result = JUnitCore.runClasses(JunitTestSuite.class);
//
//	      for (Failure failure : result.getFailures()) {
//	         System.out.println(failure.toString());
//	      }
//			
//	      System.out.println(result.wasSuccessful());
	      
	      String str = "Product Id : 3| Seller Id :2" ;
	      String id = str.substring(str.indexOf(":")+1, str.indexOf("|")) ;
	      System.out.println("product id = "+id);
	      
	   }
}
