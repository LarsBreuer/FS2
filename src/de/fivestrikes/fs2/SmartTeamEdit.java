package de.fivestrikes.fs2;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
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

public class SmartTeamEdit extends Activity {

	private static final int GET_CODE = 0;
	HelperSQL sqlHelper=null;
	HelperLayout lytHelper = null;
	EditText edit_club_name;
	EditText edit_club_name_short;
	View view = null;
	String club_id=null;
	String server_club_id=null;
	String club_name=null;
	String club_name_short=null;
	String team_id=null;
	Bundle args;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.team_edit);
		view = findViewById(android.R.id.content);
		
/* Helper generieren */
		
		sqlHelper=new HelperSQL(this);
		lytHelper = new HelperLayout();
		
/* Daten aus Activity laden */ 
	        
		args = getIntent().getExtras();
		
/* Layout festlegen */
/** TODO -3- => Teamfarbe auswählen */
		lytHelper.lytTeamEdit(view, null, args);

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
				
				edit_club_name = (EditText) view.findViewById(R.id.team_name);
				edit_club_name_short = (EditText) view.findViewById(R.id.team_short);
				
				club_id=data.getStringExtra("ClubID");
				server_club_id=data.getStringExtra("ServerClubID");
					
				if(club_id!=null) {
					club_name=sqlHelper.getClubNameByID(club_id);
					club_name_short=sqlHelper.getClubShortByID(club_id);
					edit_club_name.setEnabled(false);
					edit_club_name_short.setEnabled(false);
				}
					
				if (club_name!=null) {
					edit_club_name.setText(club_name);
				}
				
				if (club_name_short!=null) {						
					edit_club_name_short.setText(club_name_short);
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