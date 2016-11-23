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
					
					sqlHelper.addFastGame(getApplicationContext());
					String game_id = sqlHelper.getLastGameID();
					Intent i = new Intent(getApplicationContext(), SmartTickerActivity.class);
        				i.putExtra("GameID", game_id);
        				startActivity(i);
					
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
}