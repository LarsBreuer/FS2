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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class RegisterActivity extends Activity {

	//private final static String REGISTER_API_ENDPOINT_URL = "http://calm-waters-7359.herokuapp.com/api/v1/registrations";
	private SharedPreferences mPreferences;
	private String mUserEmail;
	private String mUserName;
	private String mUserPassword;
	private String mUserPasswordConfirmation;
	HelperSQL sqlHelper = null;
	HelperFunction fctHelper = null;
	double screenInch = 0;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
		
/* Helper generieren */
		
		sqlHelper=new HelperSQL(this); 
		fctHelper=new HelperFunction();
		
/* Bildschirmgröße ermitteln */

		// Inch
		screenInch=fctHelper.getScreenInch(RegisterActivity.this);
		
/* Bildschirm einrichten, abhängig von der Bildschirmgröße */
		
		if(screenInch > 6) {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		} else {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
		
/* Button konfigurieren */
		
		Button btn_cancel = (Button) findViewById(R.id.cancel);
		
		btn_cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				finish();
				
			}
		});
		
		Button btn_terms = (Button) findViewById(R.id.btn_terms);
		
		btn_terms.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				startActivity(new Intent(getApplicationContext(), TermsActivity.class));
				
			}
		});
		
		Button btn_privacy = (Button) findViewById(R.id.btn_privacy);
		
		btn_privacy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				startActivity(new Intent(getApplicationContext(), PrivacyActivity.class));
				
			}
		});
		
/* Action Bar konfigurieren */
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();
		
	}
	
	public void btnSmartRegisterClick(View view){

		EditText userNameField = (EditText) findViewById(R.id.userName);
		mUserName = userNameField.getText().toString();
		EditText userEmailField = (EditText) findViewById(R.id.userEmail);
		mUserEmail = userEmailField.getText().toString();
		// mUserEmail = "info@fivestrikes.de";
		EditText userPasswordField = (EditText) findViewById(R.id.userPassword);
		mUserPassword = userPasswordField.getText().toString();
		EditText userPasswordConfirmationField = (EditText) findViewById(R.id.userPasswordConfirmation);
		mUserPasswordConfirmation = userPasswordConfirmationField.getText().toString();
		
		CheckBox checkbox_terms = (CheckBox) findViewById(R.id.checkbox_terms);
		CheckBox checkbox_privacy = (CheckBox) findViewById(R.id.checkbox_privacy);

		if (mUserEmail.length() == 0 || mUserName.length() == 0 || mUserPassword.length() == 0 || mUserPasswordConfirmation.length() == 0) {
			
			// Eingabefelder sind leer
			// DialogBox einrichten
			final Dialog dialog = new Dialog(RegisterActivity.this);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setContentView(R.layout.custom_dialog); 

			// Texte setzen
			TextView title = (TextView) dialog.findViewById(R.id.title);
			TextView text = (TextView) dialog.findViewById(R.id.text);
			title.setText(R.string.text_register_empty_title);
			text.setText(R.string.text_register_empty);
			
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
			// Ende Nachrichtenbox
			
			return;
			
		} else {
			
			if (!mUserPassword.equals(mUserPasswordConfirmation)) {
				
				// Passwort stimmt nicht mit dem bestätigten Passwort überein
				// Dialogbix einrichten
				final Dialog dialog = new Dialog(RegisterActivity.this);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.custom_dialog);

				// Texte setzen
				TextView title = (TextView) dialog.findViewById(R.id.title);
				TextView text = (TextView) dialog.findViewById(R.id.text);
				title.setText(R.string.password);
				text.setText(R.string.text_password_confirmation);
				
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
				// Ende Nachrichtenbox
				
			} else {
				
				if (!checkbox_terms.isChecked() || !checkbox_privacy.isChecked()) {
					
					// Nutzungsbedingungen bzw. Datenschutzbestimmungen wurden nicht bestätigt
					// Dialogbox einrichten
					final Dialog dialog = new Dialog(RegisterActivity.this);
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setContentView(R.layout.custom_dialog);

					// Texte setzen
					TextView title = (TextView) dialog.findViewById(R.id.title);
					TextView text = (TextView) dialog.findViewById(R.id.text);
					title.setText(R.string.terms);
					text.setText(R.string.text_terms_notice);
					
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
					// Ende Nachrichtenbox
					
				} else {
					
					RegisterTask registerTask = new RegisterTask(RegisterActivity.this);
			            registerTask.setMessageLoading(getString(R.string.text_registering_new_account));
			            String register_url =  getResources().getString(R.string.url) + "api/v1/registrations";
			            registerTask.execute(register_url);
					
				}
			}
		}
	}
		
	private class RegisterTask extends HelperOnlineUrlJsonAsyncTask {
		
		public RegisterTask(Context context) {
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
					
					json.put("success", false);
					json.put("info", getString(R.string.text_register_failed));
					// add the users's info to the post params
					userObj.put("email", mUserEmail);
					userObj.put("name", mUserName);
					userObj.put("password", mUserPassword);
					userObj.put("password_confirmation", mUserPasswordConfirmation);
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
