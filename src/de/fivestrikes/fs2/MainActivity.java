package de.fivestrikes.fs2;

import de.fivestrikes.fs2.inappbilling.util.IabHelper;
import de.fivestrikes.fs2.inappbilling.util.IabResult;
import de.fivestrikes.fs2.inappbilling.util.Purchase;
import de.fivestrikes.fs2.inappbilling.util.Inventory;
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
import android.database.Cursor;

public class MainActivity extends Activity {
	
	int picSize=0;
	HelperSQL sqlHelper = null;
	HelperFunction fctHelper = null;
	HelperLayout lytHelper = null;
	double screenInch = 0;
	float screenDensity;
	String screenSize;
	private final int SPLASH_DISPLAY_LENGTH = 2500;
	IabHelper mHelper;
	public boolean mIsPremium = false;
	public final static String SKU_UNLIMITED_VERSION = "unlimited_version";
	String game_id = null;

	@Override
	public void onCreate(Bundle icicle) {
		
		super.onCreate(icicle);
		
/* Fehlermeldung bei JSON Ladevorgang verhindern */
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		
/* Create ServiceConnection for IInAppBillingService */
/** TODO -1- => PublicKey verstecken. Etwa so:
 * You can split it into pieces like this
 *  String piece1 = "SDFGJKGB4UIH234WE/FRT23RSDF/3DFUISDFVWE";
 *  String piece2 = "SDFGJKGB4UIHUISDFVWE";
 *  String piece3 = "BDYASGBDNAWGRET24IYE23das4saGBENWKD";
 *  String piece4 = "432423SDF23R/+SDDS";
 *  
 *  mHelper = new IabHelper(this, piece1 + piece2 + piece3 + piece4);
 *  
 *  */
		
		
		String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAh99+dNG/SPzRB2WjrzjwiTtAc2l2tpbojMpz7vHmdxR7ahUY0zOfgtSaryxwMGRdL59Y8wNKDhntjOLj7qwj1oT8mUeBAXDrzDo+D2i1w1vzgCRxwffsunRoDidmftzIFce/I+DAAvf05QwGprtKDplmZl1o4rMkVUFHcgoIMstqTE6GdF330XpJyXsGsXAIDK0NXFLSFbFjaK+52PVVbQ6ViqPbvGaeApnTt5nl0GCwheV0oNP397KVqtAKKbGRIMDnyN9zVB0POWO7o8x+ph7ybQwCsGQKiypNgQJnmXgjZ4WJnlQDWjDVAf6/RA/4xhl30H55Z+ICtG+t6fO7KQIDAQAB";
		mHelper = new IabHelper(this, base64EncodedPublicKey);
		
		mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
			
			public void onIabSetupFinished(IabResult result) {
				
			      if (!result.isSuccess()) {

			      		// Oh no, there was a problem.
			      		Log.d("MainActivity", "Problem setting up In-app Billing: " + result);
			      		
			      } else {
			      	
			      		Log.v("MainActivity", "startSetup mHelper war erfolgreich");
				      Log.v("MainActivity mHelper", String.valueOf(mHelper));
				      // Pro-Elemente aus Eingabetiefe entfernen, falls Nutzer nicht die Pro-Variante gekauft hat
				      mHelper.queryInventoryAsync(false, mGotInventoryListener);
			      	
			      }			
			}
		});
		
		
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
		ImageView imgMainImage=(ImageView) findViewById(R.id.main_image);
			
/* Layoutfelder einrichten */
/** TODO -1- => Info über Screengrößen in eigenen Screen einbauen unter "Info" */
		/*
		// Textfelder
		text.setText("Display Density: " + String.valueOf(screenDensity));
		text2.setText(screenSize);
		text3.setText("Inch: " + String.valueOf(screenInch));
		*/
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
		 * und schließt das Startbild nach ein paar Sekunden.*/
		
		new Handler().postDelayed(new Runnable(){
			
			@Override
			public void run() {
				
				/* Create an Intent that will start the Menu-Activity. */
				Intent mainIntent = null;
				
				if(screenInch > 6) {
					mainIntent = new Intent(MainActivity.this, MainTabActivity.class);
				} else {
					Log.v("MainActivity", "App wird weiter geleitet");
					mainIntent = new Intent(MainActivity.this, MainSmartActivity.class);
				}
				
				MainActivity.this.startActivity(mainIntent);
				MainActivity.this.finish();
				
			}
		}, SPLASH_DISPLAY_LENGTH);
	}
	
	// Pro-Elemente aus Eingabetiefe entfernen, falls Nutzer nicht die Pro-Variante gekauft hat
	IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
		
		public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
			
			Log.v("MainActivity", "QueryInventoryFinishedListener aufgerufen");
			
			if (result.isFailure()) {
				
				Log.v("MainActivity", "Bei der Abfrage des Premium-Accounts ist es zu einem Fehler gekommen");
				Log.v("MainActivity result", String.valueOf(result));
				
			} else {
				// does the user have the premium upgrade?
				mIsPremium = inventory.hasPurchase(SKU_UNLIMITED_VERSION);
				
				Log.v("MainActivity", "Abfrage, ob Premium-Account");
				Log.v("MainActivity mIsPremium", String.valueOf(mIsPremium));
				
				// Falls Nutzer keinen Premium-Account hat => Eingabetiefe von Wurfecke, Wurftechnik und Detail zu technischem Fehler aller Spiele auf Null setzen
				if (!mIsPremium){
					
					Log.v("MainActivity", "Nutzer ist nicht Premium");
					
					Cursor c = sqlHelper.getAllGameCursor();
					for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
						
						game_id = sqlHelper.getGameID(c);
						sqlHelper.updateGame(game_id, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, 0, null, null, null, 0, null, null);		
						
					}
				}
			}
		}
	};
	
	@Override
	public void onDestroy() {
		
		super.onDestroy();
		if (mHelper != null) mHelper.dispose();
		mHelper = null;
		
	}
}