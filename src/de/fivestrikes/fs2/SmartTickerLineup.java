package de.fivestrikes.fs2;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.database.Cursor;

public class SmartTickerLineup extends ListActivity {

	HelperLayout lytHelper = null;
	HelperSQL sqlHelper=null;
	HelperText txtHelper = null;
	Cursor model=null;
	LineupAdapter adapter = null;
	String team_id, game_id, player_id, player_position_id, realtime, activity_result, teamname, activity_string;
	Integer number_of_players, activity_id, ticker_activity_id, ticker_event_id, home_or_away, time, lineup_id, game_halftime;
	View view;
	Bundle args;
	static boolean[] checkBoxState;
	int count_player;
	Context ctxt;
	Resources res;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
/* Helper generieren */
		
		sqlHelper=new HelperSQL(this);
		lytHelper = new HelperLayout();
		txtHelper=new HelperText();
		
/* Daten aus Activity laden */ 
		
		args = getIntent().getExtras();
		game_id = args.getString("GameID");
		activity_id = args.getInt("ActivityID");
		home_or_away = args.getInt("HomeOrAway");
		time = args.getInt("Time");
		realtime = args.getString("Realtime");
		
		lineup_id = getResources().getInteger(R.integer.lineup_id);
		game_halftime = sqlHelper.getGameDurationHalftimeByID(game_id);
		
/* Grundlayout setzen */
		
		setContentView(R.layout.smart_ticker_lineup);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		view = findViewById(android.R.id.content);
		res = getResources(); 
		ctxt = view.getContext();
		
/* Action Bar konfigurieren */
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();
		
/* Teamliste einrichten */
		
		refreshContent(team_id, game_id, home_or_away);		
		
	}
	
	@Override
	public void onDestroy() {
		
		super.onDestroy();	  

	}
	
/* Inhalt neu laden, wenn Activity erneut aufgerufen wird */
	
	@Override
	public void onResume() {

		super.onResume();
		refreshContent(team_id, game_id, home_or_away);
	    	
	}
	
	public void refreshContent(String team_id, String game_id, Integer home_or_away) {

/* Überschrift setzen */
		
		if (home_or_away.equals(1)) team_id = sqlHelper.getGameTeamHomeIDByID(game_id);
		if (home_or_away.equals(0)) team_id = sqlHelper.getGameTeamAwayIDByID(game_id);
		teamname = sqlHelper.getTeamClubShortByID(team_id);
		activity_string = getResources().getString(R.string.lineup) + " " + teamname;
		TextView headline=(TextView)view.findViewById(R.id.headline);
		headline.setText(activity_string);

/* Spielerliste einrichten */

		count_player = 0;
		sqlHelper = new HelperSQL(this);
		model = sqlHelper.getAllPlayerCursorByTeamID(team_id);
		number_of_players = model.getCount();
		checkBoxState = new boolean[number_of_players];
		startManagingCursor(model);
		adapter = new LineupAdapter(model);
		setListAdapter(adapter);
		        
	}
	
/*
 * 
 * Spielerliste definieren 
 *
 */
	
	class LineupAdapter extends CursorAdapter {
		
		LineupAdapter(Cursor c) {
			super(SmartTickerLineup.this, c);
		}
		
		@Override
		public void bindView(View row, Context ctxt, Cursor c) {
			
			final int position = c.getPosition();
			final CheckBox cbListCheck;
			
			TickerLineupHolder holder=(TickerLineupHolder)row.getTag();
			cbListCheck = (CheckBox)row.findViewById(R.id.rowCheckbox);
			cbListCheck.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					
					if (cbListCheck.isChecked()) {

						checkBoxState[position]=true;
						count_player = count_player + 1;

						if (count_player == 7) {

							transfer();
							
			    			}

					} else if (!cbListCheck.isChecked()) {

						checkBoxState[position]=false;
						count_player = count_player - 1;

					}
				}
			});
			holder.populateFrom(c, sqlHelper, game_id);
			
		}
		
		@Override
		public View newView(Context ctxt, Cursor c, ViewGroup parent) {

			LayoutInflater inflater=(LayoutInflater) ctxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View row=inflater.inflate(R.layout.row_player_checkbox, parent, false);
			TickerLineupHolder holder=new TickerLineupHolder(row, ctxt);
			row.setTag(holder);
			return(row);
			
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View view = super.getView(position, convertView, parent);
			CheckBox cbListCheck = (CheckBox) view.findViewById(R.id.rowCheckbox);
			cbListCheck.setChecked(checkBoxState[position]);

			return view;
			
		}
	}
	
	static class TickerLineupHolder {

		private TextView tv_player_number = null;
		private TextView tv_player_name = null;
		private TextView tv_player_position = null;
		Context ctxt=null;
		Resources res;
	    
		TickerLineupHolder(View row, Context context) {
	      
			tv_player_number = (TextView) row.findViewById(R.id.player_number);
			tv_player_name = (TextView) row.findViewById(R.id.player_name);
			tv_player_position = (TextView) row.findViewById(R.id.player_position);
			ctxt = context;
			res = ctxt.getResources(); 
	    	
		}
	    
		void populateFrom(Cursor c, HelperSQL helper, String game_id) {
			
			String player_id = helper.getPlayerID(c);
			String player_number=helper.getPlayerNumberByID(player_id);
			String player_name=helper.getPlayerForenameByID(player_id)+" "+helper.getPlayerSurenameByID(player_id);
			String player_position_id=helper.getPlayerPositionFirstByID(player_id);
			
			// Texte setzen
			tv_player_number.setText(player_number);
			tv_player_name.setText(player_name);
			tv_player_position.setText(helper.getPlayerPositionNameByID(player_position_id, ctxt));
	  
		}	
	}
	
/*
 * 
 * Spielerauswahl 
 *
 */
		

	@Override
	public void onListItemClick(ListView list, View row, int position, long id) {
		
		final CheckBox cbListCheck;
		cbListCheck = (CheckBox) row.findViewById(R.id.rowCheckbox);

		if (!cbListCheck.isChecked()) {

			cbListCheck.setChecked(true);
			checkBoxState[position]=true;
			count_player = count_player + 1;

			if (count_player == 7) {

				transfer();
						
			}

		} else if (cbListCheck.isChecked()) {

			cbListCheck.setChecked(false);
			checkBoxState[position]=false;
			count_player = count_player - 1;

		}
	}
	
/*
 * 
 * Ausgewählte Spieler eingeben und weiter
 *
 */
	
	public void transfer() {

		sqlHelper.insertTickerEvent(game_id, (int) (long) time, null, null);
		ticker_event_id = sqlHelper.getLastTickerEventID();
				
		int time_lineup;
		Integer sub_in_id = getResources().getInteger(R.integer.sub_in_id);
		String activity_string = getResources().getString(R.string.sub_in);
	    	model.moveToFirst();
	    	
	    	// Falls die Zeit der Aktivität bis zu zwei Minuten nach Spielbeginn oder nach Halbzeitpause, dann setze Zeit auf Halbzeitpause
	    	if (time < 120000) {
			time_lineup = 0;
		} else {
			if (time <= ((game_halftime*60*1000)+120000) && time >= (game_halftime*60*1000)) {
				time_lineup = (game_halftime*60*1000);
			} else {
				time_lineup = time;
			}
		}

	    	for (int i = 0; i < number_of_players; i++) {
	    		
	    		if (checkBoxState[i] == true) {
	    			
	    			player_id = sqlHelper.getPlayerID(model);
	    			player_position_id = sqlHelper.getPlayerPositionFirstByID(player_id);
	    			
	    			sqlHelper.insertTickerActivity(game_id, ticker_event_id, (int) (long) time_lineup, realtime, sub_in_id, home_or_away, player_id, null, null, null, null, activity_string, null);
	    			
	    			if (player_position_id != null) {
	    				if (player_position_id.substring(2,4).equals("01")) {
		    				if (home_or_away.equals(1)) {
		    					sqlHelper.updateGame(game_id, null, null, null, null, null, null, null, null, null, null, null, Integer.parseInt(player_id), null, null, null, null, null, null, null, null, null, null);
		        			}
		    				if (home_or_away.equals(0)) {
		        				sqlHelper.updateGame(game_id, null, null, null, null, null, null, null, null, null, null, null, null, Integer.parseInt(player_id), null, null, null, null, null, null, null, null, null);
		        			}
		        		}
	    			}
	    		}
	    		model.moveToNext();
	    	}
	    	
	    	// Tickermeldung generieren
	    	String ticker_event_note = txtHelper.generateTickerEventNote(ticker_event_id, ctxt, res);
		sqlHelper.updateTickerEvent(String.valueOf(ticker_event_id), null, time_lineup, ticker_event_note, null);
	    	
	    	if (home_or_away.equals(1)) {
			
			home_or_away = 0;
			team_id = sqlHelper.getGameTeamAwayIDByID(game_id);
			refreshContent(team_id, game_id, home_or_away);
			
		} else {
			
			finish();
			
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
				
				// Beende vorzeitig die Auswahl der Spieler
				transfer();
				return true;

			default:
				return super.onOptionsItemSelected(item);
      	  	
		}
        
		return true;
        
	}
	
	private void CreateMenu(Menu menu) {
		
		MenuItem mnu1 = menu.add(0, 0, 0, "Ok"); {

			mnu1.setIcon(R.drawable.done);
			mnu1.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			
		}
		
	}

}