package de.fivestrikes.fs2;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class TabGameActivity extends FragmentActivity {

	HelperSQL sqlHelper = null;
	TabFragGameList fragGameList;
	String game_id = null;
	Bundle args = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.tab_game);
		
/* Datenbank laden */
        
		sqlHelper = new HelperSQL(this);

/* Action Bar konfigurieren */
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();
	
/* Fragments einrichten */

		if (findViewById(R.id.frag_game_list) != null) {

			if (savedInstanceState != null) {return;}
			TabFragGameList firstFragment = new TabFragGameList();
			getSupportFragmentManager().beginTransaction()
				.add(R.id.frag_game_list, firstFragment).commit();
			
		}
        
		if (findViewById(R.id.frag_game_edit) != null) {

			Bundle args = new Bundle();
			args.putString("Activity", "Game");
			
			if (savedInstanceState != null) {return;}
			TabFragEmpty secondFragment = new TabFragEmpty();
			secondFragment.setArguments(args);
			getSupportFragmentManager().beginTransaction()
            			.add(R.id.frag_game_edit, secondFragment).commit();
			
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
				
				// Füge neues Spiel ein
				sqlHelper.insertGame();
				game_id = sqlHelper.getLastGameID();
				args = new Bundle();
				args.putString("GameID", game_id);
				
				// Aktualisiere die Spieliste
      	  			fragGameList = (TabFragGameList) getSupportFragmentManager().findFragmentById(R.id.frag_game_list);
      	  			fragGameList.refreshContent(game_id);
      	  		
				// Erneuere Fragments
				FragmentManager fragmentManager = getSupportFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();				
				TabFragGameEdit fragment = new TabFragGameEdit();
				fragment.setArguments(args);
				fragmentTransaction.replace(R.id.frag_game_edit, fragment);
				fragmentTransaction.commit();
				return true;

			default:
				return super.onOptionsItemSelected(item);
				
		}
		return true;
	}
	
	private void CreateMenu(Menu menu) {
	
		MenuItem mnu1 = menu.add(0, 0, 0, "Item 1"); {

			mnu1.setAlphabeticShortcut('a');
			mnu1.setIcon(R.drawable.add);
			mnu1.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			
		}
	}
}
		