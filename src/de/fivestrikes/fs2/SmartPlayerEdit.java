package de.fivestrikes.fs2;

import java.util.ArrayList;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;

public class SmartPlayerEdit extends Activity {

	private static final int GET_CODE = 0;
	private static Context context;
	HelperSQL sqlHelper = null;
	HelperFunction fctHelper = null;
	HelperLayout lytHelper = null;
	String player_id = null;
	String server_player_id = null;
	String team_id = null;
	String player_number = null;
	String player_forename = null;
	String player_surename = null;
	String player_position_first = null;
	String player_position_second = null;
	String player_position_third = null;
	EditText edit_player_forename = null;
	EditText edit_player_surename = null;
	EditText edit_player_number = null;
	Spinner spinner_position_first = null; 
	Spinner spinner_position_second = null; 
	Spinner spinner_position_third = null; 
	TextView player_club = null;
	TextView player_team = null;
	View view;
	Bundle args;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.player_edit);
		view = findViewById(android.R.id.content);
		context = getApplicationContext();
		
/* Helper generieren */
		
		sqlHelper=new HelperSQL(this);
		fctHelper=new HelperFunction();
		lytHelper = new HelperLayout();
		
/* Daten aus Activity laden */ 
	        
		args = getIntent().getExtras();
		
/* Layout festlegen */
		
		lytHelper.lytPlayerEdit(view, null, args);

/* Action Bar konfigurieren */
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();

	}
	
/* Ergebnis der Sub-Activity "Clubauswahl" abfragen */
		
		@Override
		public void onActivityResult(int requestCode, int resultCode, Intent data) {
			super.onActivityResult(requestCode, resultCode, data);
					
			if (requestCode == GET_CODE) {
				if (resultCode == RESULT_OK) {
					
					edit_player_forename = (EditText) view.findViewById(R.id.player_forename);
					edit_player_surename = (EditText) view.findViewById(R.id.player_surename);
					edit_player_number = (EditText) view.findViewById(R.id.player_number);
					player_club = (TextView) view.findViewById(R.id.player_club);
					player_team = (TextView) view.findViewById(R.id.player_team);
					
					if (data.getStringExtra("TeamID") != null) {
						
						team_id=data.getStringExtra("TeamID");
						server_player_id=data.getStringExtra("ServerPlayerID");
						player_club.setText(sqlHelper.getTeamClubNameByID(team_id));
						player_team.setText(sqlHelper.getTeamTypeNameByTeamID(team_id, context));
						
					}
					
					if (data.getStringExtra("ServerPlayerID") != null) {
						
						// Lade Daten aus Activity
						server_player_id = data.getStringExtra("ServerPlayerID");
						player_number = data.getStringExtra("PlayerNumber");
						player_forename = data.getStringExtra("PlayerForename");
						player_surename = data.getStringExtra("PlayerSurename");
						player_position_first = data.getStringExtra("PlayerPositionFirst");
						player_position_second = data.getStringExtra("PlayerPositionSecond");
						player_position_third = data.getStringExtra("PlayerPositionThird");
						
						// Layoutfelder füllen
						edit_player_forename.setText(player_forename);
						edit_player_surename.setText(player_surename);
						edit_player_number.setText(player_number);
						if (fctHelper.isInteger(player_position_first)) {spinner_position_first.setSelection(Integer.parseInt(player_position_first.substring(3,4)));}
						if (fctHelper.isInteger(player_position_second)) {spinner_position_second.setSelection(Integer.parseInt(player_position_second.substring(3,4)));}
						if (fctHelper.isInteger(player_position_third)) {spinner_position_third.setSelection(Integer.parseInt(player_position_third.substring(3,4)));}
						
						// Edit-Felder sperren
						edit_player_forename.setEnabled(false);
						edit_player_surename.setEnabled(false);
						edit_player_number.setEnabled(false);
												
					}
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