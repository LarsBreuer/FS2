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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SmartPlayerSynch extends ListActivity {
	
	private static Context context;
	String strPlayerName=null;
	String url=null;
	String player_id = null;
	String server_player_id = null;
	String team_id=null;
	String server_team_id=null;
	String player_number=null;
	String player_forename=null;
	String player_surename=null;
	String player_position_first=null;
	String player_position_second=null;
	String player_position_third=null;
	TextView text = null;
	HelperSQL sqlHelper=null;
	HelperOnlineJSONParser jsonHelper=null;
	HelperOnlineGetJSON getJsonHelper=null;
	HelperFunction fctHelper = null;
	
	// JSON Node names
	private static final String TAG_PLAYERS = "players";
	private static final String TAG_ID = "id";
	private static final String TAG_TEAM_ID = "team_id";
	private static final String TAG_PLAYER_NUMBER = "player_number";
	private static final String TAG_PLAYER_FORENAME = "player_forename";
	private static final String TAG_PLAYER_SURENAME = "player_surename";
	private static final String TAG_PLAYER_NAME = "player_name";
	private static final String TAG_PLAYER_POSITION_FIRST = "player_position_first";
	private static final String TAG_PLAYER_POSITION_SECOND = "player_position_second";
	private static final String TAG_PLAYER_THIRD = "player_position_third";
	private SharedPreferences mPreferences;
	
	// JSONArray
	JSONArray players = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_with_text);
		context = this;
		
/* Helper definieren */
		
		sqlHelper=new HelperSQL(SmartPlayerSynch.this);
		jsonHelper = new HelperOnlineJSONParser();
		fctHelper=new HelperFunction();
		getJsonHelper = new HelperOnlineGetJSON();
		
/* Layoutfelder definieren und Inhalte setzen */
		
		text = (TextView) findViewById(R.id.text);
		text.setText(getResources().getString(R.string.text_player_search_explain));
		LinearLayout lyt_button_all = (LinearLayout) findViewById(R.id.lyt_button_all);
		lyt_button_all.removeAllViews();
		
/* Daten aus Activity laden */ 
	        
		team_id = getIntent().getStringExtra("TeamID");
		server_team_id = getIntent().getStringExtra("ServerTeamID");
		
/* Action Bar konfigurieren */
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();
		
/* Spielerliste einrichten */
		
		getJsonHelper.synchPlayer(server_team_id, context, null, null);

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
