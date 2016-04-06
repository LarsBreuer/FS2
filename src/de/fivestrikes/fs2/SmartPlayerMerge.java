package de.fivestrikes.fs2;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ActionBar;
import android.app.ListActivity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SmartPlayerMerge extends ListActivity {
	
	String player_id = null;
	TextView text = null;
	HelperSQL sqlHelper=null;
	Cursor model=null;
	HelperAdapterPlayerServerID adapter=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_with_text);
		
/* Helper definieren */
		
		sqlHelper=new HelperSQL(SmartPlayerMerge.this);
		
/* Layoutfelder definieren und Inhalte setzen */
		
		text = (TextView) findViewById(R.id.text);
		text.setText(getResources().getString(R.string.text_player_merge_explain)); 
		LinearLayout lyt_button_all = (LinearLayout) findViewById(R.id.lyt_button_all);
		lyt_button_all.removeAllViews();
		
/* Daten aus Activity laden */ 
	        
		player_id = getIntent().getStringExtra("PlayerID");
		
/* Action Bar konfigurieren */
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();
		
/* Spielerliste einrichten */
		
		model=sqlHelper.getAllPlayerCursorServerPlayerID();
		startManagingCursor(model);
		adapter=new HelperAdapterPlayerServerID(SmartPlayerMerge.this, model);
		setListAdapter(adapter);

	}
	
/* Spielerauswahl */
	
	@Override
	public void onListItemClick(ListView list, View view, int position, long id) {
		
		// Daten des ausgewählten Spielers ermitteln
		String connect_player_id = String.valueOf(id);
		
		// Überprüfen, ob die beiden Spieler schon einmal in einem gemeinsamen Spiel eingesetzt wurden
/** TODO -2- => Überprüfen, ob die beiden Spieler schon einmal in einem gemeinsamen Spiel eingesetzt wurden */
		
		// Falls nicht => Alle Spielaktionen mit dem Spieler ändern...
/** TODO -2- => Die Player Server ID auch in den Spielaktionen eintragen */
		
		// ... und den Spieler löschen
/** TODO -2- => sqlHelper.deletePlayer(player_id);	 */	
		
		Toast.makeText(getBaseContext(), getString(R.string.text_player_connect_suceeded), Toast.LENGTH_LONG).show();
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
