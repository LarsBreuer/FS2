package de.fivestrikes.fs2;

import java.util.Calendar;
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

public class SmartStatList extends ListActivity {

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
	
/* Inhalt neu laden, wenn Activity erneut aufgerufen wird */
	
	@Override
	public void onResume() {

		super.onResume();
		refreshContent(game_id);
	    	
	}
	
	public void refreshContent(String game_id) {
	
		sqlHelper=new HelperSQL(this);
		model=sqlHelper.getAllGameCursor();
		startManagingCursor(model);
		adapter=new HelperAdapterGame(SmartStatList.this, model, game_id);
		setListAdapter(adapter);
		        
	}
	
/* Spielerauswahl */
	
	@Override
	public void onListItemClick(ListView list, View view, int position, long id) {
	
		game_id = String.valueOf(id);
		Intent i=new Intent(SmartStatList.this, SmartListWithText.class);
		Bundle args = new Bundle();
		args.putString("GameID", game_id);
		args.putString("InputString", "StatOverview");
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