package testcases;

public class EmailGeneration {
	    public static String randomEmail() {
	        return "auto" + System.currentTimeMillis() + "@gmail.com";
	    }

	    public static String randomMobile() {
	        return "9" + (long)(Math.random() * 1000000000);
	    }
	}


