package de.fivestrikes.fs2;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
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
import android.database.Cursor;

public class SmartPlayerList extends ListActivity {

	private SharedPreferences mPreferences;
	HelperSQL sqlHelper=null;
	HelperOnlineGetJSON getJsonHelper=null;
	Cursor model=null;
	HelperAdapterPlayer adapter=null;
	String team_id=null;
	String player_id=null;
	String server_player_id=null;
	String server_team_id=null;
	Bundle args;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
/* Helper generieren */
		
		sqlHelper=new HelperSQL(this);
		getJsonHelper = new HelperOnlineGetJSON();
		
/* Daten aus Activity laden */ 
	        
		args = getIntent().getExtras();
		team_id = args.getString("TeamID");
		
/* Daten aus Datenbank laden */ 
		
		server_team_id = sqlHelper.getTeamServerTeamID(team_id);
		args.putString("ServerTeamID", server_team_id);

/* Action Bar konfigurieren */
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();
		
/* Teamliste einrichten */
		
		refreshContent(team_id);		
		
	}
	
/* Inhalt neu laden, wenn Activity ernuet aufgerufen wird */
	
	@Override
	public void onResume() {

		super.onResume();
		refreshContent(team_id);
	    	
	}
	
	public void refreshContent(String team_id) {
	
		sqlHelper=new HelperSQL(this);
		model=sqlHelper.getAllPlayerCursorByTeamID(team_id);
		startManagingCursor(model);
		adapter=new HelperAdapterPlayer(SmartPlayerList.this, model, null);
		setListAdapter(adapter);
		        
	}
	
/* Spielerauswahl */
	
	@Override
	public void onListItemClick(ListView list, View view, int position, long id) {
	
		player_id = String.valueOf(id);
		server_player_id = sqlHelper.getPlayerServerIDByID(player_id);
		
		// Werte übermitteln
		if(server_player_id!=null) {
			
			// Benachrichtigen, dass lokale Änderungen nicht auf dem Server abgespeichert werden
			// DialogBox einrichten
			final Dialog dialog = new Dialog(SmartPlayerList.this);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setContentView(R.layout.custom_dialog);

			// Texte setzen
			TextView title = (TextView) dialog.findViewById(R.id.title);
			TextView text = (TextView) dialog.findViewById(R.id.text);
			title.setText(R.string.synchro);
			text.setText(R.string.text_player_edit);
			
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
					Intent i=new Intent(SmartPlayerList.this, SmartPlayerEdit.class);
					i.putExtra("PlayerID", player_id);
					startActivity(i);
				}
			});

			dialog.show();
			// Ende Nachrichtenbox
			
		} else {
			
			Intent i=new Intent(SmartPlayerList.this, SmartPlayerEdit.class);
			i.putExtra("PlayerID", player_id);
			startActivity(i);
			
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
				Intent i=new Intent(SmartPlayerList.this, SmartPlayerEdit.class);
				i.putExtra("TeamID", team_id);
				startActivity(i);
				return true;

		        case 1:
		      	  	
		  		// Synchronisiere Spieler der Mannschaft
		      	  
		      	  	mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
				String auth_token = mPreferences.getString("AuthToken", "");
				
				if (auth_token.equals("")) {
					
					// DialogBox einrichten
					final Dialog dialog = new Dialog(SmartPlayerList.this);
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
					
					if (server_team_id != null) {
		      	  		
		      	  			getJsonHelper.loadPlayerFromServer(team_id, server_team_id, getApplicationContext());
		      	  			refreshContent(team_id);
		      	  		
		      	  		} else {
		      	  		
		      	  			// DialogBox einrichten
		      				final Dialog dialog = new Dialog(SmartPlayerList.this);
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
		      						Intent i=new Intent(SmartPlayerList.this, SmartPlayerEdit.class);
		      						i.putExtra("PlayerID", player_id);
		      						startActivity(i);
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