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
import de.fivestrikes.fs2.inappbilling.util.Inventory;
import de.fivestrikes.fs2.inappbilling.util.Purchase;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;

public class MainTabActivity extends Activity {
	
	int picSize=0;
	HelperSQL sqlHelper = null;
	HelperFunction fctHelper = null;
	HelperLayout lytHelper = null;
	double screenInch = 0;
	float screenDensity;
	String login_or_logout = null;
	String auth_token = null;
	String screenSize;
	Button btn_login;
	private String mUserEmail;
	private String mUserPassword;
	private SharedPreferences mPreferences;
	IabHelper mHelper;
	public boolean mIsPremium = false;
	public final static String SKU_UNLIMITED_VERSION = "unlimited_version";

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_tab);

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

/* Layoutfelder definieren */
		
		ImageView imgMainImage=(ImageView) findViewById(R.id.main_image);
		ImageButton btnMainTeam=(ImageButton) findViewById(R.id.main_team);
		ImageButton btnMainGame=(ImageButton) findViewById(R.id.main_game);
		ImageButton btnMainGameFast=(ImageButton) findViewById(R.id.main_game_fast);
		ImageButton btnMainInfo=(ImageButton) findViewById(R.id.main_info);
		ImageButton btnMainStatistic=(ImageButton) findViewById(R.id.main_statistic);
		ImageButton btnMainLogin=(ImageButton) findViewById(R.id.main_login);
		btn_login = (Button) findViewById(R.id.btn_login);
		
/* Bildschirmgröße ermitteln */
		
		// Auflösung
		screenDensity=fctHelper.getScreenDensity(MainTabActivity.this);
		
/* Layoutfelder einrichten */
		
		// Bilder
		picSize=200;
		if(screenDensity<2.5){
			picSize=100;
		}
		if(screenDensity<2){
			picSize=50;
		}
		
		imgMainImage.setImageResource(R.drawable.logo48_with_text);
		lytHelper.scaleImage(imgMainImage, picSize*4, "LinearLayout");
		btnMainTeam.setImageResource(R.drawable.team);
		lytHelper.scaleImage(btnMainTeam, picSize, "LinearLayout");
		btnMainGame.setImageResource(R.drawable.game);
		lytHelper.scaleImage(btnMainGame, picSize, "LinearLayout");
		btnMainGameFast.setImageResource(R.drawable.game);
		lytHelper.scaleImage(btnMainGameFast, picSize, "LinearLayout");
		btnMainStatistic.setImageResource(R.drawable.statistic);
		lytHelper.scaleImage(btnMainStatistic, picSize, "LinearLayout");
		btnMainInfo.setImageResource(R.drawable.info);
		lytHelper.scaleImage(btnMainInfo, picSize, "LinearLayout");
		btnMainLogin.setImageResource(R.drawable.login);
		lytHelper.scaleImage(btnMainLogin, picSize, "LinearLayout");
		
		btn_login.setText(login_or_logout);
		
	}
	
/* Links setzen */
	
	public void btnTabTeamClick (View view){

		startActivity(new Intent(getApplicationContext(), TabTeamActivity.class));
		
	}
	
	public void btnTabGameClick (View view){

		startActivity(new Intent(getApplicationContext(), TabGameActivity.class));
		
	}
	
	public void btnTabStatisticClick (View view){

		startActivity(new Intent(getApplicationContext(), TabStatActivity.class));
		
	}
	
	public void btnTabGameFastClick (View view){

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
				
				// DialogBox einrichten
				final Dialog dialog = new Dialog(MainTabActivity.this);
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
					mHelper = new IabHelper(MainTabActivity.this, base64EncodedPublicKey);
					
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
					
					// Füge neues Spiel ein
					sqlHelper.addFastGame(getApplicationContext());
					String game_id = sqlHelper.getLastGameID();
					Intent i = new Intent(getApplicationContext(), TabTickerActivity.class);
	  				i.putExtra("GameID", game_id);
	  				startActivity(i);
					
				}
			}
		});

		dialog.show();
		
	}
	
	public void btnTabInfoClick (View view){
		
		Uri uri = Uri.parse( "http://www.fivesweb.de/hilfe" );
		startActivity( new Intent( Intent.ACTION_VIEW, uri ) );
		
	}
	
	public void btnTabLoginClick (View view){

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

					btn_login.setText(getResources().getString(R.string.login));
					
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
			
		}
		
	}
	
	private class LoginTask extends HelperOnlineUrlJsonAsyncTask {
		
		public LoginTask(Context context) {
			super(context);
		}

		@Override
		protected JSONObject doInBackground(String... urls) {
			
			DefaultHttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(urls[0]);
			JSONObject holder = new JSONObject();
			JSONObject userObj = new JSONObject();
			String response = null;
			JSONObject json = new JSONObject();

			try {
				try {
					
					// setup the returned values in case something goes wrong
					json.put("success", false);
					json.put("info", "Something went wrong. Retry!");
					// add the user email and password to the params
					userObj.put("email", mUserEmail);
					userObj.put("password", mUserPassword);
					holder.put("user", userObj);
					StringEntity se = new StringEntity(holder.toString());
					post.setEntity(se);

					// setup the request headers
					post.setHeader("Accept", "application/json");
					post.setHeader("Content-Type", "application/json");

					ResponseHandler<String> responseHandler = new BasicResponseHandler();
					response = client.execute(post, responseHandler);
					json = new JSONObject(response);

				} catch (HttpResponseException e) {
					
					e.printStackTrace();
					Log.e("ClientProtocol", "" + e);
					json.put("info", "Email and/or password are invalid. Retry!");
					
				} catch (IOException e) {
					
					e.printStackTrace();
					Log.e("IO", "" + e);
					
		            }
				
			} catch (JSONException e) {
				
				e.printStackTrace();
		            Log.e("JSON", "" + e);
		            
			}

			return json;
		}

		@Override
		protected void onPostExecute(JSONObject json) {

			try {
				if (json.getBoolean("success")) {
					
					// everything is ok
					SharedPreferences.Editor editor = mPreferences.edit();
					// save the returned auth_token into the SharedPreferences
					editor.putString("AuthToken", json.getJSONObject("data").getString("auth_token"));
					String id = String.valueOf(json.getJSONObject("data").getString("user_id"));
					String name = String.valueOf(json.getJSONObject("data").getString("user_name"));
					sqlHelper.updateUser(id, name);
					editor.commit();
					finish();
					
				}
		            Toast.makeText(context, json.getString("info"), Toast.LENGTH_LONG).show();
		            
			} catch (Exception e) {
				
				// something went wrong: show a Toast with the exception message
				Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
				
			} finally {
				super.onPostExecute(json);
			}
		}
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
		
/* Buttontext setzen */

		btn_login.setText(login_or_logout);

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
					
					// Füge neues Spiel ein
					sqlHelper.addFastGame(getApplicationContext());
					String game_id = sqlHelper.getLastGameID();
					Intent i = new Intent(getApplicationContext(), TabTickerActivity.class);
	  				i.putExtra("GameID", game_id);
	  				startActivity(i);
					
				} else {
					
					// DialogBox einrichten
					final Dialog dialog = new Dialog(MainTabActivity.this);
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
							mHelper.launchPurchaseFlow(MainTabActivity.this, SKU_UNLIMITED_VERSION, 10001,
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
					
					// Füge neues Spiel ein
					sqlHelper.addFastGame(getApplicationContext());
					String game_id = sqlHelper.getLastGameID();
					Intent i = new Intent(getApplicationContext(), TabTickerActivity.class);
	  				i.putExtra("GameID", game_id);
	  				startActivity(i);
					
				}
			}
		}
	};
	    
}