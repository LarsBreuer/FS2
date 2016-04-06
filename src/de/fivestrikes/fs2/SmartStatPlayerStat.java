package de.fivestrikes.fs2;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class SmartStatPlayerStat extends ListActivity {
	
	Bundle args;
	HelperLayout lytHelper = null;
	View view;
	SmartStatPlayerStat activity = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.stat_player_stat);
		view = findViewById(android.R.id.content);
		
/* Helper generieren */
		
		lytHelper = new HelperLayout();
		
/* Daten aus Activity laden */ 
	        
		args = getIntent().getExtras();
		
/* Layout festlegen */
		
		lytHelper.lytStatPlayerStat(args, view, null, activity);
		
/* Action Bar konfigurieren */
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();
		
	}
	
/*
 * 
 * Statistikauswahl
 *
*/
	
	@Override
	public void onListItemClick(ListView list, View v, int position, long id) {
	
		TextView labelTextView = (TextView) v.findViewById(R.id.stat_player_label);
		String statLabel = labelTextView.getText().toString();
		args.putString("Stat", statLabel);
		
		Intent i=new Intent(SmartStatPlayerStat.this, SmartStatPlayerTicker.class);
		i.putExtras(args);
		startActivity(i);
		
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