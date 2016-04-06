package de.fivestrikes.fs2;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ActionBar;
import android.app.ListActivity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SmartTeamOnline extends ListActivity {
	
	private static Context context;
	String strTeamName=null;
	String team_type_name=null;
	String url=null;
	String server_team_id=null;
	String server_club_id=null;
	String team_type_id=null;
	String club_name=null;
	String club_name_short=null;
	String club_id=null;
	String team_id=null;
	TextView text = null;
	HelperOnlineGetJSON getJsonHelper=null;
	EditText edit_team_search = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.team_online);
		context = this;
		
/* Helper definieren */
		
		getJsonHelper = new HelperOnlineGetJSON();
		
/* Daten aus Activity laden */ 
	        
		team_id=getIntent().getStringExtra("TeamID");
		
/* Layoutfelder definieren und Inhalte setzen */
		
		TextView headline = (TextView) findViewById(R.id.headline);
		text = (TextView) findViewById(R.id.text);
		headline.setText(getResources().getString(R.string.text_team_search)); 
		text.setText(getResources().getString(R.string.text_team_search_explain)); 
		Button btn_team_search=(Button) findViewById(R.id.btn_team_search);
		
		// Suche nach Mannschaftsnamen auf dem Server
		btn_team_search.setOnClickListener(new View.OnClickListener() {
			@Override
	            public void onClick(View view) {
/** TODO -0- => ProgressDialog bzw. Animated Gif mit Ladesymbol einbauen - siehe meine Beschreibung  in der ToDo Handball App */
				edit_team_search = (EditText) findViewById(R.id.team_search);
				strTeamName = edit_team_search.getText().toString();
				getJsonHelper.synchTeam(strTeamName, context, team_id, null, null);
			
			}
		});
		
/* Action Bar konfigurieren */
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();
		
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
