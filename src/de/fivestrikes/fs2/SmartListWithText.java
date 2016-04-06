package de.fivestrikes.fs2;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class SmartListWithText extends ListActivity {
	
	Bundle args;
	HelperLayout lytHelper = null;
	View view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_with_text);
		view = findViewById(android.R.id.content);
		SmartListWithText activity = this;
		
/* Helper generieren */
		
		lytHelper = new HelperLayout();
		
/* Daten aus Activity laden */ 
	        
		args = getIntent().getExtras();
		
/* Layout festlegen */
		
		lytHelper.lytListWithText(args, view, null, activity);
		
/* Action Bar konfigurieren */
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();
		
	}
	
	@Override
	public void onListItemClick(ListView list, View view, int position, long id) {

		lytHelper.lytListWithTextClick(args, view, null, position);

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