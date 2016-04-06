package de.fivestrikes.fs2;

import java.util.Date;
import android.content.Context;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class HelperFunction {

	public double getScreenInch(Context context) {
		
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width=dm.widthPixels;
		int height=dm.heightPixels;
		int dens=dm.densityDpi;
		double wi=(double)width/(double)dens;
		double hi=(double)height/(double)dens;
		double x = Math.pow(wi,2);
		double y = Math.pow(hi,2);
		double screenInches = Math.sqrt(x+y);
		
		return(screenInches);
		
	}
	
	public float getScreenDensity(Context context) {
		
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		float screenDensity = metrics.density;
		
		return(screenDensity);
	}
	
	public String getScreenSize(Context context) {
		
		String strScreenSize;
		int screenSize = context.getResources().getConfiguration().screenLayout &
			        Configuration.SCREENLAYOUT_SIZE_MASK;
		switch(screenSize) {
		    case Configuration.SCREENLAYOUT_SIZE_LARGE:
			    strScreenSize = "Large screen";
		        break;
		    case Configuration.SCREENLAYOUT_SIZE_NORMAL:
			    strScreenSize = "Normal screen";
		        break;
		    case Configuration.SCREENLAYOUT_SIZE_SMALL:
			    strScreenSize = "Small screen";
		        break;
		    case Configuration.SCREENLAYOUT_SIZE_XLARGE:
			    strScreenSize = "Xlarge screen";
		        break;
		    default:
			    strScreenSize = "Screen size is neither large, normal or small";
		}
		
		return(strScreenSize);
		
	}
	
	public String revise(String strText) {
		
		strText = strText.trim();														// Leerzeichen am Anfang und Ende entfernen
		strText = strText.replace(" ", "_");												// Leerzeichen im Text mit Unterstrich ersetzen
		return(strText);
	}
	
	public static boolean isInteger(String s) {
		    
		try { 	
			Integer.parseInt(s); 
		} catch(NumberFormatException e) { 
			return false; 
		}
		// only got here if we didn't return false
		return true;
		
	}
	
	private int getDayIntFromDateString(String date) {
		
		String[] parts = date.split("\\.");
		int day = Integer.parseInt(parts[0]);
		
		return day;
		
	}
	
	private int getMonthIntFromDateString(String date) {
		
		String[] parts = date.split("\\.");
		int month = Integer.parseInt(parts[1]);
		
		return month - 1;
		
	}

	private int getYearIntFromDateString(String date) {
	
		String[] parts = date.split("\\.");
		int year = Integer.parseInt(parts[0]);
	
		return year;
	
	}
	
	public String updateTimer(float time){
		
		String minutes,seconds;
		long secs,mins;
		
		secs = (long)(time/1000);
		mins = (long)((time/1000)/60);

		secs = secs % 60;
		seconds=String.valueOf(secs);
		if(secs == 0){
			seconds = "00";
		}
		if(secs <10 && secs > 0){
			seconds = "0"+seconds;
		}
	    	
		/* Convert the minutes to String and format the String */
	    	
		minutes=String.valueOf(mins);
		if(mins == 0){
			minutes = "00";
		}
		if(mins <10 && mins > 0){
			minutes = "0"+minutes;
		}
	    	
		return(minutes + ":" + seconds);
		
	}

}
