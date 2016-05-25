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
/** TODO -2- => Bei Login auch Button registrieren einrichten */
			if (auth_token.equals("")) {
				
				// Dialogbox einrichten
				final Dialog dialog = new Dialog(this);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.custom_login_dialog);
				
				// Button definieren
				Button btn_login = (Button) dialog.findViewById(R.id.btn_login);
				
				btn_login.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
/** TODO -0- => Programm stürzt ab, nachdem man eingeloggt ist */
						if (dialog != null && dialog.isShowing()) {
							dialog.dismiss();
						}

						EditText user_email_field = (EditText) dialog.findViewById(R.id.user_email);
						mUserEmail = user_email_field.getText().toString();
						mUserEmail = "lars-breuer@gmx.de";
						EditText user_password_field = (EditText) dialog.findViewById(R.id.user_password);
						mUserPassword = user_password_field.getText().toString();

						if (mUserEmail.length() == 0 || mUserPassword.length() == 0) {
							
							// Dialogbox einrichten
							final Dialog dialog = new Dialog(MainSmartActivity.this);
							dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
							dialog.setContentView(R.layout.custom_dialog);

							// Texte setzen
							TextView title = (TextView) dialog.findViewById(R.id.title);
							TextView text = (TextView) dialog.findViewById(R.id.text);
							title.setText(R.string.text_login_empty_title);
							text.setText(R.string.text_login_empty);
							
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
							
						} else {
							
							dialog.dismiss();
							
							LoginTask loginTask = new LoginTask(MainSmartActivity.this);
							loginTask.setMessageLoading("Logging in...");
							//loginTask.execute(LOGIN_API_ENDPOINT_URL);
							String login_url =  getResources().getString(R.string.url) + "api/v1/sessions";
							loginTask.execute(login_url);

						}
						
					}
				});
				
				Button btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
				
				btn_cancel.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						
						dialog.dismiss();
						
					}
				});

				dialog.show();
				
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
								getResources().getString(R.string.login)};
						
						HelperAdapterMainSmart adapter = new HelperAdapterMainSmart(MainSmartActivity.this, values);
						setListAdapter(adapter);
						
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
		
/** TODO -3- => Menüpunkt Einstellung einfügen => darin u.a. auch Einstellung, ob man Smartphone oder Tablet haben will, außerdem Sprache einstellen (dies auch schon beim ersten Start) */
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
}