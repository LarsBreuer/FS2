package de.fivestrikes.fs2;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;

public class MainActivity extends Activity {
	
	int picSize=0;
	HelperSQL sqlHelper = null;
	HelperFunction fctHelper = null;
	HelperLayout lytHelper = null;
	double screenInch = 0;
	float screenDensity;
	String screenSize;
	private final int SPLASH_DISPLAY_LENGTH = 2500;

	@Override
	public void onCreate(Bundle icicle) {
		
		super.onCreate(icicle);
		
/* Fehlermeldung bei JSON Ladevorgang verhindern */
/** TODO -1- => Kostenpflichtige App einbauen */
/** TODO -0- => AsyncTask einbauen - siehe meine Beschreibung in der ToDo Handball App */
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		
/* Helper generieren */
/** TODO -3- => Erster Start einsetzen, u.a. auch Eingabe, welche Sprache und ob man Tablet oder Smartphone will */
		sqlHelper=new HelperSQL(this);
		fctHelper=new HelperFunction();
		lytHelper=new HelperLayout();
		
/* App-Datenbank einrichten, falls diese noch nicht existiert */

		if (sqlHelper.dbExists() == false) sqlHelper.insertApp();
		sqlHelper.updateStatGameActivities(null, null);
		
/* ID der Spielaktion ändern; am Anfang Zahl für die Sportart anfügen */
		
		sqlHelper.updateTickerActivityID();
		
/* Bildschirmgröße ermitteln */
		
		// Auflösung
		screenDensity=fctHelper.getScreenDensity(MainActivity.this);

		// Größe
		screenSize=fctHelper.getScreenSize(MainActivity.this);
		
		// Inch
		screenInch=fctHelper.getScreenInch(MainActivity.this);

/* Bildschirm einrichten, abhängig von der Bildschirmgröße */
		
		setContentView(R.layout.main);
		
		if(screenInch > 6) {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			sqlHelper.updateSmartOrTab(1);
		} else {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			sqlHelper.updateSmartOrTab(0);
		}

/* Layoutfelder definieren */
		
		TextView text=(TextView)findViewById(R.id.display_density);
		TextView text2=(TextView)findViewById(R.id.display_size);
		TextView text3=(TextView)findViewById(R.id.display_inch);
		ImageView imgMainImage=(ImageView) findViewById(R.id.main_image);
			
/* Layoutfelder einrichten */
		
		// Textfelder
		text.setText("Display Density: " + String.valueOf(screenDensity));
		text2.setText(screenSize);
		text3.setText("Inch: " + String.valueOf(screenInch));
		
		// Bilder
		picSize=350;
		if(screenDensity<2.5){
			picSize=250;
		}
		if(screenDensity<2){
			picSize=140;
		}
		
		imgMainImage.setImageResource(R.drawable.logo512);
		lytHelper.scaleImage(imgMainImage, picSize*2, "LinearLayout");
	
/* Wartezeit vor Start der App setzen */
	
		/* New Handler startet die Main-Activity 
		 * und schließt den Startbiland nach ein paar Sekunden.*/
		
		new Handler().postDelayed(new Runnable(){
			
			@Override
			public void run() {
				
				/* Create an Intent that will start the Menu-Activity. */
				Intent mainIntent = null;
				
				if(screenInch > 6) {
					mainIntent = new Intent(MainActivity.this, MainTabActivity.class);
				} else {
					mainIntent = new Intent(MainActivity.this, MainSmartActivity.class);
				}
				
				MainActivity.this.startActivity(mainIntent);
				MainActivity.this.finish();
				
			}
		}, SPLASH_DISPLAY_LENGTH);
	}   
}