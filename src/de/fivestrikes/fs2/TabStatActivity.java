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


public class TabStatActivity extends FragmentActivity {

	HelperSQL sqlHelper = null;
	Bundle args = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.tab_stat);
		
/* Datenbank laden */
        
		sqlHelper = new HelperSQL(this);

/* Action Bar konfigurieren */
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();
	
/* Fragments einrichten */

		if (findViewById(R.id.frag_stat_list) != null) {

			if (savedInstanceState != null) {return;}
			TabFragStatList firstFragment = new TabFragStatList();
			getSupportFragmentManager().beginTransaction()
				.add(R.id.frag_stat_list, firstFragment).commit();
			
		}
        
		if (findViewById(R.id.frag_stat_empty) != null) {

			Bundle args = new Bundle();
			args.putString("Activity", "Stat");
			
			if (savedInstanceState != null) {return;}
			TabFragEmpty secondFragment = new TabFragEmpty();
			secondFragment.setArguments(args);
			getSupportFragmentManager().beginTransaction()
            			.add(R.id.frag_stat_empty, secondFragment).commit();
			
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
		