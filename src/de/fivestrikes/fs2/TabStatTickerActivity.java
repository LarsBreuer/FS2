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


public class TabStatTickerActivity extends FragmentActivity {

	HelperSQL sqlHelper = null;
	Bundle args = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.tab_stat_ticker);
		
/* Datenbank laden */
        
		sqlHelper = new HelperSQL(this);
		
/* Daten aus Activity laden */
		
		args = getIntent().getExtras();
		args.putString("InputString", "TabStatTicker");

/* Action Bar konfigurieren */
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();
	
/* Fragments einrichten */

		if (findViewById(R.id.frag_stat_ticker_list) != null) {
			
			if (savedInstanceState != null) {return;}
			TabFragListWithText firstFragment = new TabFragListWithText();
			firstFragment.setArguments(args);
			getSupportFragmentManager().beginTransaction()
				.add(R.id.frag_stat_ticker_list, firstFragment).commit();
			
		}
        
		if (findViewById(R.id.frag_stat_ticker_content_1) != null) {
			
			args.putString("Activity", "");
			
			if (savedInstanceState != null) {return;}
			TabFragEmpty secondFragment = new TabFragEmpty();
			secondFragment.setArguments(args);
			getSupportFragmentManager().beginTransaction()
            			.add(R.id.frag_stat_ticker_content_1, secondFragment).commit();
			
		}
		
		if (findViewById(R.id.frag_stat_ticker_content_2) != null) {

			args.putString("Activity", "");
			
			if (savedInstanceState != null) {return;}
			TabFragEmpty thirdFragment = new TabFragEmpty();
			thirdFragment.setArguments(args);
			getSupportFragmentManager().beginTransaction()
            			.add(R.id.frag_stat_ticker_content_2, thirdFragment).commit();
			
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
		