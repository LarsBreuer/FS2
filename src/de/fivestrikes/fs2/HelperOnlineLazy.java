package de.fivestrikes.fs2;

import java.io.PrintWriter;
import java.io.StringWriter;

public class HelperOnlineLazy {
	
	public static class Ex {
		
		public static String getStackTrace(java.lang.Exception e) {
			
			StringWriter sWriter = new StringWriter();
			e.printStackTrace(new PrintWriter(sWriter));
			return sWriter.getBuffer().toString();
			
		}
	}
	
	public static class Str {
		
		public static String urlEncode(String value, boolean trim) {
			return java.net.URLEncoder.encode(value.trim());	
		}
		
		public static String urlEncode(String value) {
			return urlEncode(value, true);
		}
	}
}
