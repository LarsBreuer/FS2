package de.fivestrikes.fs2;

import java.io.IOException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import de.fivestrikes.fs2.inappbilling.util.IabHelper;
import de.fivestrikes.fs2.inappbilling.util.IabResult;
import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import de.fivestrikes.fs2.inappbilling.util.IabHelper;
import de.fivestrikes.fs2.inappbilling.util.IabResult;
import de.fivestrikes.fs2.inappbilling.util.Inventory;
import de.fivestrikes.fs2.inappbilling.util.Purchase;

public class MainSmartActivity extends ListActivity {
	
	int picSize=0;
	HelperSQL sqlHelper = null;
	HelperFunction fctHelper = null;
	HelperLayout lytHelper = null;
	float screenDensity;
	String login_or_logout = null;
	String auth_token = null;
	String[] values = null;
	private String mUserEmail;
	private String mUserPassword;
	private SharedPreferences mPreferences;
	IabHelper mHelper;
	public boolean mIsPremium = false;
	public final static String SKU_UNLIMITED_VERSION = "unlimited_version";

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_smart);
		
/* Helper generieren */
		
		sqlHelper=new HelperSQL(this);
		fctHelper=new HelperFunction();
		lytHelper=new HelperLayout();
		
/* Userdaten abfragen */
		
		mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
		auth_token = mPreferences.getString("AuthToken", "");
		if (auth_token.equals("")) {
			login_or_logout = getResources().getString(R.string.login);
		} else {
			login_or_logout = getResources().getString(R.string.logout) + " " + sqlHelper.getUserName();
		}

/* Bildschirmgröße ermitteln */
		
		// Auflösung
		screenDensity=fctHelper.getScreenDensity(MainSmartActivity.this);
		
/* Layoutfelder definieren */

		ImageView imgMainImage=(ImageView) findViewById(R.id.main_image);
		ImageButton btnMainTeam=(ImageButton) findViewById(R.id.main_team);
		ImageButton btnMainGame=(ImageButton) findViewById(R.id.main_game);
		ImageButton btnMainStatistic=(ImageButton) findViewById(R.id.main_statistic);
		ImageButton btnMainLogin=(ImageButton) findViewById(R.id.main_login);
		
/* Layoutfelder einrichten */
		
		// Bilder
		picSize=300;
		if(screenDensity<2.5){
			picSize=200;
		}
		if(screenDensity<2){
			picSize=100;
		}
		
		imgMainImage.setImageResource(R.drawable.logo48_with_text);
		lytHelper.scaleImage(imgMainImage, picSize*2, "LinearLayout");
	
/* Zelltexte anlegen */

		values = new String[] { getResources().getString(R.string.team), 
								getResources().getString(R.string.game),
								getResources().getString(R.string.game_fast),
								getResources().getString(R.string.statistic),
								getResources().getString(R.string.info),
								login_or_logout};
		
		HelperAdapterMainSmart adapter = new HelperAdapterMainSmart(MainSmartActivity.this, values);
		setListAdapter(adapter);
		
	}
	
	@Override
	public void onListItemClick(ListView list, View view, int position, long id) {

		if (position == 0) startActivity(new Intent(getApplicationContext(), SmartTeamList.class));
		if (position == 1) startActivity(new Intent(getApplicationContext(), SmartGameList.class));
		if (position == 2) {
			
			// DialogBox einrichten
			final Dialog dialog = new Dialog(this);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setContentView(R.layout.custom_dialog);

			// Texte setzen
			TextView title = (TextView) dialog.findViewById(R.id.title);
			TextView text = (TextView) dialog.findViewById(R.id.text);
			title.setText(R.string.game_fast);
			text.setText(R.string.text_game_fast);
			
			// Button definieren
			LinearLayout lyt_button3 = (LinearLayout) dialog.findViewById(R.id.lyt_button3);
			lyt_button3.removeAllViews();

			Button dialogButton1 = (Button) dialog.findViewById(R.id.button1);
			dialogButton1.setText(R.string.Server);
			
			dialogButton1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					
					dialog.dismiss();
/** TODO -2- => Schnelles Spiel vom Server laden (auch bei Main Tab Activity) */
					// DialogBox einrichten
					final Dialog dialog = new Dialog(MainSmartActivity.this);
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setContentView(R.layout.custom_dialog);

					// Texte setzen
					TextView title = (TextView) dialog.findViewById(R.id.title);
					TextView text = (TextView) dialog.findViewById(R.id.text);
					title.setText(R.string.text_patience);
					text.setText(R.string.text_function_not_available);
					
					// Button definieren
					LinearLayout lyt_button2 = (LinearLayout) dialog.findViewById(R.id.lyt_button2);
					lyt_button2.removeAllViews();
					LinearLayout lyt_button3 = (LinearLayout) dialog.findViewById(R.id.lyt_button3);
					lyt_button3.removeAllViews();
					
					Button dialogButton1 = (Button) dialog.findViewById(R.id.button1);
					dialogButton1.setText(R.string.okay);
					
					dialogButton1.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							dialog.dismiss();
						}
					});

					dialog.show();
					
				}
			});
			
			Button dialogButton2 = (Button) dialog.findViewById(R.id.button2);
			dialogButton2.setText(R.string.Local);
			
			dialogButton2.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					
					dialog.dismiss();
					
					// Abfrage, wie viele Spiele der Nutzer bereits angelegt hat
					Cursor c = sqlHelper.getAllGameCursor();			
					c.moveToFirst();
					Integer countGames = c.getCount();
					c.close();
					
					// Falls 3 oder mehr Spiele angelegt wurden, Abfrage, ob der Nutzer einen Premium-Account hat
					if (countGames > 2) {
						
						/** TODO -1- => PublicKey verstecken. */
						String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAh99+dNG/SPzRB2WjrzjwiTtAc2l2tpbojMpz7vHmdxR7ahUY0zOfgtSaryxwMGRdL59Y8wNKDhntjOLj7qwj1oT8mUeBAXDrzDo+D2i1w1vzgCRxwffsunRoDidmftzIFce/I+DAAvf05QwGprtKDplmZl1o4rMkVUFHcgoIMstqTE6GdF330XpJyXsGsXAIDK0NXFLSFbFjaK+52PVVbQ6ViqPbvGaeApnTt5nl0GCwheV0oNP397KVqtAKKbGRIMDnyN9zVB0POWO7o8x+ph7ybQwCsGQKiypNgQJnmXgjZ4WJnlQDWjDVAf6/RA/4xhl30H55Z+ICtG+t6fO7KQIDAQAB";
						mHelper = new IabHelper(MainSmartActivity.this, base64EncodedPublicKey);
						
						mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
							
							public void onIabSetupFinished(IabResult result) {
								
							      if (!result.isSuccess()) {

							      		// Oh no, there was a problem.
							      		Log.d("HelperLayout InputDepth", "Problem setting up In-app Billing: " + result);
							      		
							      } else {
							      	
								      // Prüfen, ob Nutzer den Premium-Account hat
							      		if (mHelper != null) mHelper.flagEndAsync();
									mHelper.queryInventoryAsync(mGotInventoryListener);
								      
							      }			
							}
						});						
					} else {
						
						sqlHelper.addFastGame(getApplicationContext());
						String game_id = sqlHelper.getLastGameID();
						Intent i = new Intent(getApplicationContext(), SmartTickerActivity.class);
	        				i.putExtra("GameID", game_id);
	        				startActivity(i);
						
					}
				}
			});

			dialog.show();
			// Ende Dialogbox einrichten
			
		}
		if (position == 3) startActivity(new Intent(getApplicationContext(), SmartStatList.class));
		if (position == 4) {
			
			Uri uri = Uri.parse( "http://www.fivesweb.de/hilfe" );
			startActivity( new Intent( Intent.ACTION_VIEW, uri ) );
			
		}
		if (position == 5) {
			
			if (auth_token.equals("")) {
				
				startActivity(new Intent(getApplicationContext(), LoginActivity.class));
				
			} else {
				
				// DialogBox einrichten
				final Dialog dialog = new Dialog(this);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.custom_dialog);

				// Texte setzen
				TextView title = (TextView) dialog.findViewById(R.id.title);
				TextView text = (TextView) dialog.findViewById(R.id.text);
				title.setText(R.string.logout);
				text.setText(R.string.text_logout);
				
				// Button definieren
				LinearLayout lyt_button3 = (LinearLayout) dialog.findViewById(R.id.lyt_button3);
				lyt_button3.removeAllViews();

				Button dialogButton1 = (Button) dialog.findViewById(R.id.button1);
				dialogButton1.setText(R.string.yes);
				
				dialogButton1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						
						dialog.dismiss();
						
						SharedPreferences.Editor editor = mPreferences.edit();
						editor.clear();
						editor.commit();
						finish();
						auth_token = "";
						
						Toast.makeText(getApplicationContext(), getResources().getString(R.string.text_logout_success), Toast.LENGTH_LONG).show();
						
						values = new String[] { getResources().getString(R.string.team), 
								getResources().getString(R.string.game),
								getResources().getString(R.string.game_fast),
								getResources().getString(R.string.statistic),
								getResources().getString(R.string.info),
								login_or_logout};
						
						HelperAdapterMainSmart adapter = new HelperAdapterMainSmart(MainSmartActivity.this, values);
						setListAdapter(adapter);
						
					}
				});
				
				Button dialogButton2 = (Button) dialog.findViewById(R.id.button2);
				dialogButton2.setText(R.string.no);
				
				dialogButton2.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
/** TODO -3- => Absturz beim Ausloggen verhindern */
						dialog.dismiss();
						
					}
				});

				dialog.show();
				
			}
		}
		
/** TODO -3- => Menüpunkt Einstellung einfügen => darin u.a. auch Einstellung, ob man Smartphone oder Tablet haben will, außerdem Sprache einstellen (dies auch schon beim ersten Start) */
	}
	
	@Override
	public void onResume() {

		super.onResume();
		
/* Userdaten abfragen */
		
		mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
		auth_token = mPreferences.getString("AuthToken", "");
		if (auth_token.equals("")) {
			login_or_logout = getResources().getString(R.string.login);
		} else {
			login_or_logout = getResources().getString(R.string.logout) + " " + sqlHelper.getUserName();
		}
		
/* Zelltexte anlegen */

		values = new String[] { getResources().getString(R.string.team), 
				getResources().getString(R.string.game),
				getResources().getString(R.string.game_fast),
				getResources().getString(R.string.statistic),
				getResources().getString(R.string.info),
				login_or_logout};
		
		HelperAdapterMainSmart adapter = new HelperAdapterMainSmart(MainSmartActivity.this, values);
		setListAdapter(adapter);

	}
	
IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
		
		public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
			
			if (result.isFailure()) {
				Log.v("HelperLayout QueryInventoryFinishedListener result", String.valueOf(result));
			} else {
				// does the user have the premium upgrade?
				mIsPremium = inventory.hasPurchase(SKU_UNLIMITED_VERSION);
				
				// Falls Nutzer keinen Premium-Account hat => Aufforderung, einen Premium-Account abzuschließen
				if (mIsPremium == true) {
					
					sqlHelper.addFastGame(getApplicationContext());
					String game_id = sqlHelper.getLastGameID();
					Intent i = new Intent(getApplicationContext(), SmartTickerActivity.class);
        				i.putExtra("GameID", game_id);
        				startActivity(i);
					
				} else {
					
					// DialogBox einrichten
					final Dialog dialog = new Dialog(MainSmartActivity.this);
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setContentView(R.layout.custom_dialog);

					// Texte setzen
					TextView title = (TextView) dialog.findViewById(R.id.title);
					TextView text = (TextView) dialog.findViewById(R.id.text);
					title.setText(R.string.pro_version);
					text.setText(R.string.text_pro_version);
						
					// Button definieren
					LinearLayout lyt_button3 = (LinearLayout) dialog.findViewById(R.id.lyt_button3);
					lyt_button3.removeAllViews();
					
					Button dialogButton1 = (Button) dialog.findViewById(R.id.button1);
					dialogButton1.setText(R.string.yes);
					
					dialogButton1.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
/** TODO -0- => Individuellen payload einbauen? */ 
							String payload = "abc";
							mHelper.launchPurchaseFlow(MainSmartActivity.this, SKU_UNLIMITED_VERSION, 10001,
									mPurchaseFinishedListener, payload);
							
							dialog.dismiss();
							
						}
					});
					
					Button dialogButton2 = (Button) dialog.findViewById(R.id.button2);
					dialogButton2.setText(R.string.no);
					
					dialogButton2.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							
							dialog.dismiss();
							
						}
					});

					dialog.show();
					// Ende Dialogbox einrichten
					
				}
			}
		}
	};
	
	// Kontrollieren, ob Kauf erfolgreich war
	IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener
			  = new IabHelper.OnIabPurchaseFinishedListener() {
			
		public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
				
			if (result.isFailure()) {
				Log.d("Helper Layout InputDepth", "Error purchasing: " + result);
			} else {
				
				if (purchase.getSku().equals(SKU_UNLIMITED_VERSION)) {
					
					sqlHelper.addFastGame(getApplicationContext());
					String game_id = sqlHelper.getLastGameID();
					Intent i = new Intent(getApplicationContext(), SmartTickerActivity.class);
        				i.putExtra("GameID", game_id);
        				startActivity(i);
					
				}
			}
		}
	};
}