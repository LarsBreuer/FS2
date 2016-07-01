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
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends Activity {
/** TODO -1- => LoginActivity entfernen */
	//private final static String LOGIN_API_ENDPOINT_URL = "http://calm-waters-7359.herokuapp.com/api/v1/sessions";
	private SharedPreferences mPreferences;
	private String mUserEmail;
	private String mUserPassword;
	HelperFunction fctHelper = null;
	double screenInch = 0;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
		
/* Helper generieren */
		
		fctHelper=new HelperFunction();
		
/* Bildschirmgröße ermitteln */

		// Inch
		screenInch=fctHelper.getScreenInch(LoginActivity.this);
		
/* Bildschirm einrichten, abhängig von der Bildschirmgröße */
		
		if(screenInch > 6) {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		} else {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
		
/* Action Bar konfigurieren */
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();
		
	}
	
	public void btnSmartLoginClick (View view){

		EditText user_email_field = (EditText) findViewById(R.id.user_email);
		mUserEmail = user_email_field.getText().toString();
		//mUserEmail = "lars-breuer@gmx.de";
		EditText user_password_field = (EditText) findViewById(R.id.user_password);
		mUserPassword = user_password_field.getText().toString();

		if (mUserEmail.length() == 0 || mUserPassword.length() == 0) {
			
			// Eingabefelder sind leer
			// DialogBox einrichten
			final Dialog dialog = new Dialog(LoginActivity.this);
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
					finish();
				}
			});

			dialog.show();
			// Ende Nachrichtenbox
			
			return;
			
		} else {
			
			LoginTask loginTask = new LoginTask(LoginActivity.this);
			loginTask.setMessageLoading("Logging in...");
			//loginTask.execute(LOGIN_API_ENDPOINT_URL);
			String login_url =  getResources().getString(R.string.url) + "api/v1/sessions";
			loginTask.execute(login_url);
			
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
	
/* Action Bar einrichten */
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		super.onCreateOptionsMenu(menu);
		CreateMenu(menu);
		return true;
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
        
			case android.R.id.home: 
				onBackPressed();
				break;

			default:
				return super.onOptionsItemSelected(item);
      	  	
		}
        
		return true;
        
	}
	
	private void CreateMenu(Menu menu) {
		
	}

}
