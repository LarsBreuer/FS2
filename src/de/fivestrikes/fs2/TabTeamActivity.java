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


public class TabTeamActivity extends FragmentActivity {

	HelperSQL helper = null;
	TabFragTeamList fragTeamList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.tab_team);
		
/* Datenbank laden */
        
		helper = new HelperSQL(this);

/* Action Bar konfigurieren */
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();
	
/* Fragments einrichten */

		if (findViewById(R.id.frag_team_list) != null) {

			if (savedInstanceState != null) {return;}
			TabFragTeamList firstFragment = new TabFragTeamList();
			getSupportFragmentManager().beginTransaction()
				.add(R.id.frag_team_list, firstFragment).commit();
			
		}
        
		if (findViewById(R.id.frag_team_edit) != null) {

			Bundle args = new Bundle();
			args.putString("Activity", "Team");
			
			if (savedInstanceState != null) {return;}
			TabFragEmpty secondFragment = new TabFragEmpty();
			secondFragment.setArguments(args);
			getSupportFragmentManager().beginTransaction()
            			.add(R.id.frag_team_edit, secondFragment).commit();

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
				
				// Füge neue Mannschaft ein
				// Aktualisiere die Spielerliste
      	  			fragTeamList = (TabFragTeamList) getSupportFragmentManager().findFragmentById(R.id.frag_team_list);
      	  		
      	  			Bundle args = new Bundle();
      	      	  		args.putString("InputString", "TeamAdd");
      	      	  		fragTeamList.refreshContent(null, args);
				// Erneuere Fragments
				FragmentManager fragmentManager = getSupportFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			
				TabFragListWithText fragment = new TabFragListWithText();
				fragment.setArguments(args);
				fragmentTransaction.replace(R.id.frag_team_edit, fragment);
	        
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
		