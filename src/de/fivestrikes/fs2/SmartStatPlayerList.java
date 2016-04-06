package de.fivestrikes.fs2;

import java.util.ArrayList;
import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class SmartStatPlayerList extends ListActivity {
	
	Bundle args;
	HelperLayout lytHelper = null;
	HelperSQL sqlHelper = null;
	View view;
	Cursor c=null;
	String team_id, game_id, player_id;
	ArrayList<String> player = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		view = findViewById(android.R.id.content);
		SmartStatPlayerList activity = this;
		
/* Helper generieren */
		
		lytHelper = new HelperLayout();
		sqlHelper = new HelperSQL(this);
		
/* Daten aus Activity laden */ 
	        
		args = getIntent().getExtras();
		
/* Daten aus Activity laden */ 
	        
		team_id = args.getString("TeamID");
		game_id = args.getString("GameID");
		
/* Spielerliste definieren und einrichten */
		
		// Nur Spieler einbinden, die in dem Spiel auch tatsächlich gespielt haben
		c = sqlHelper.getAllPlayerCursorByTeamID(team_id);
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			
			player_id = sqlHelper.getPlayerID(c);
			if (sqlHelper.count_ticker_activity(game_id, null, player_id, null, null, null) > 0) {
				player.add(player_id);	
			}
			
		}
		HelperAdapterStatPlayerList adapter = new HelperAdapterStatPlayerList(SmartStatPlayerList.this, player, null);
		setListAdapter(adapter);
		
/* Action Bar konfigurieren */
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();
		
	}
	
	@Override
	public void onListItemClick(ListView list, View view, int position, long id) {

		player_id = player.get(position);
		args.putString("PlayerID", player_id);
		
		Intent i=new Intent(SmartStatPlayerList.this, SmartStatPlayerFirst.class);
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