package de.fivestrikes.fs2;

import android.app.ActionBar;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;

public class SmartClubChoose extends ListActivity {

	HelperFunction fctHelper = null;
	HelperLayout lytHelper = null;
	HelperSQL sqlHelper=null;
	double screenInch = 0;
	float screenDensity;
	Cursor model=null;
	HelperAdapterClub adapter=null;
	
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
		
/* Vereinsliste einrichten */
		
		refreshContent();		
		
	}
	
/* Inhalt neu laden, wenn Activity ernuet aufgerufen wird */
	
	@Override
	public void onResume() {

		super.onResume();
		refreshContent();
	    	
	}
	
	public void refreshContent() {
		    	
		model=sqlHelper.getAllClubCursor();
		startManagingCursor(model);
		adapter=new HelperAdapterClub(SmartClubChoose.this, model);
		setListAdapter(adapter);
		        
	}
	
/* Auswahl eines Vereins */
	
	@Override
	public void onListItemClick(ListView list, View view,
            int position, long id) {
		
		// Clubdaten ermitteln
		String club_id=String.valueOf(id);
		String server_club_id=sqlHelper.getClubServerIDByID(club_id);
		
		// Daten übermitteln
		Intent i=new Intent();
		i.putExtra("ClubID", club_id);
		i.putExtra("ServerClubID", server_club_id);
		setResult(RESULT_OK, i);
		finish();
		
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