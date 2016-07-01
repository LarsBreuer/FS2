package de.fivestrikes.fs2;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;

public class SmartGameList extends ListActivity {

	HelperSQL sqlHelper=null;
	Cursor model=null;
	HelperAdapterGame adapter=null;
	String game_id=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
/* Helper generieren */
		
		sqlHelper=new HelperSQL(this);

/* Action Bar konfigurieren */
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();
		
/* Teamliste einrichten */
		
		refreshContent(game_id);		
		
	}
	
/* Inhalt neu laden, wenn Activity ernuet aufgerufen wird */
	
	@Override
	public void onResume() {

		super.onResume();
		refreshContent(game_id);
	    	
	}
	
	public void refreshContent(String game_id) {
	
		sqlHelper=new HelperSQL(this);
		model=sqlHelper.getAllGameCursor();
		startManagingCursor(model);
		adapter=new HelperAdapterGame(SmartGameList.this, model, game_id);
		setListAdapter(adapter);
		        
	}
	
/* Spielauswahl */
	
	@Override
	public void onListItemClick(ListView list, View view, int position, long id) {
	
		game_id = String.valueOf(id);
		Intent i=new Intent(SmartGameList.this, SmartGameEdit.class);
		i.putExtra("GameID", game_id);
		startActivity(i);
			
	}
	
/* Action Bar einrichten */
/** TODO -3- => Menü-Button bei jedem Bildschirm mit Auswahl der anderen Hauptbereiche und Hilfe */
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
/** TODO -1- => Abfrage, ob kostenpflichtige App, ansonsten nur drei Spiele möglich */
				// Füge ein neues Spiel ein
				sqlHelper.insertGame();
				game_id = sqlHelper.getLastGameID();
				Intent i=new Intent(SmartGameList.this, SmartGameEdit.class);
				i.putExtra("GameID", game_id);
				startActivity(i);
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
	}
}