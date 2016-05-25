package de.fivestrikes.fs2;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.Log;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;


public class TabPlayerActivity extends FragmentActivity {

	private SharedPreferences mPreferences;
	HelperSQL sqlHelper = null;
	HelperOnlineGetJSON getJsonHelper=null;
	String team_id = null;
	String server_team_id = null;
	String player_id = null;
	Bundle args = null;
	TabFragPlayerList fragPlayerList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.tab_player);
		
/* Datenbank laden */
        
		sqlHelper = new HelperSQL(this);
		getJsonHelper = new HelperOnlineGetJSON();
		
/* Daten aus Activity laden */
		
		args = getIntent().getExtras();
		team_id = args.getString("TeamID");
		server_team_id = args.getString("ServerTeamID");
		
/* Action Bar konfigurieren */
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();
	
/* Fragments einrichten */
		args.putString("Activity", "Player");

		if (findViewById(R.id.frag_player_list) != null) {

			if (savedInstanceState != null) {return;}
			TabFragPlayerList firstFragment = new TabFragPlayerList();
			firstFragment.setArguments(args);
			getSupportFragmentManager().beginTransaction()
				.add(R.id.frag_player_list, firstFragment).commit();
			
		}
        
		if (findViewById(R.id.frag_player_edit) != null) {
			
			if (savedInstanceState != null) {return;}
			TabFragEmpty secondFragment = new TabFragEmpty();
			secondFragment.setArguments(args);
			getSupportFragmentManager().beginTransaction()
            			.add(R.id.frag_player_edit, secondFragment).commit();
			
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
            
			case 0:
				
				// Füge neuen Spieler ein
				player_id = null;								//player_id auf null setzen, damit ein neuer Spieler angelegt werden kann
				args.putString("PlayerID", player_id);
				
				fragPlayerList = (TabFragPlayerList) getSupportFragmentManager().findFragmentById(R.id.frag_player_list);
				fragPlayerList.refreshContent(team_id, null, args);
				
				FragmentManager fragmentManager = getSupportFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
				TabFragPlayerEdit fragment = new TabFragPlayerEdit();
				fragment.setArguments(args);
				fragmentTransaction.replace(R.id.frag_player_edit, fragment);
				fragmentTransaction.commit();
				return true;

		        case 1:
		      	  	
		      	  	// Abfrage, ob der Nutzer eingeloogt ist
		      	  	mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
				String auth_token = mPreferences.getString("AuthToken", "");
				
				if (auth_token.equals("")) {
					
					// DialogBox einrichten
					final Dialog dialog = new Dialog(TabPlayerActivity.this);
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setContentView(R.layout.custom_dialog);

					// Texte setzen
					TextView title = (TextView) dialog.findViewById(R.id.title);
					TextView text = (TextView) dialog.findViewById(R.id.text);
					title.setText(R.string.synchro);
					text.setText(R.string.text_login_not_possible);
					
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
					
					// Synchronisiere Spieler der Mannschaft
		      	  		if (server_team_id != null) {
		      	  		
		      	  			// Lade Spieler vom Server
		      	  			getJsonHelper.loadPlayerFromServer(team_id, server_team_id, getApplicationContext());
		      	  		
		      	  			// Aktualisiere die Spielerliste
		      	  			fragPlayerList = (TabFragPlayerList) getSupportFragmentManager().findFragmentById(R.id.frag_player_list);
		      	  		
		      	  			// Neutralisiere das Editierfenster
		      	  			args.putString("Activity", "Player");
		      	  			fragPlayerList.refreshContent(team_id, null, args);
		      			
		      	  			TabFragEmpty secondFragment = new TabFragEmpty();
		      	  			secondFragment.setArguments(args);
		      	  			getSupportFragmentManager().beginTransaction()
		                  			.add(R.id.frag_player_edit, secondFragment).commit();
		      	  		
		      	  		} else {
		      	  		
		      	  			// DialogBox einrichten
		      	  			final Dialog dialog = new Dialog(this);
						dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
						dialog.setContentView(R.layout.custom_dialog);

						// Texte setzen
						TextView title = (TextView) dialog.findViewById(R.id.title);
						TextView text = (TextView) dialog.findViewById(R.id.text);
						title.setText(R.string.text_player_load_server_title);
						text.setText(R.string.text_player_synch_not_possible);
						
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
		      	  		
		      	  		}
				}
		      	  	
		      	  	return true;

			default:
				return super.onOptionsItemSelected(item);
				
		}
		return true;
	}
	
	private void CreateMenu(Menu menu) {
	
		MenuItem mnu1 = menu.add(0, 0, 0, "Add"); {

			mnu1.setIcon(R.drawable.add);
			mnu1.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			
		}
		
		MenuItem mnu2 = menu.add(0, 1, 0, "Synch"); {

			mnu2.setIcon(R.drawable.cloud_sync_white);
			mnu2.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			
		}
	}
}
		