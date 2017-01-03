package de.fivestrikes.fs2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ListView;

public class TabFragStatPlayerList extends ListFragment {
	
	Cursor model=null;
	HelperAdapterPlayer adapter=null;
	HelperSQL sqlHelper=null;
	Cursor c=null;
	String team_id, game_id, player_id;
	ArrayList<String> player = new ArrayList<String>();
	Bundle args;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		      
		View view = inflater.inflate(R.layout.list_frag_stat_list, container, false);
		
/* Daten aus Activity laden */ 
	        
		args = getArguments();
		
/* Helper generieren */

		sqlHelper = new HelperSQL(getActivity());

/* Daten aus Activity laden */ 
	        
		team_id = args.getString("TeamID");
		game_id = args.getString("GameID");
		
/* Spieldaten einrichten */
		
		// Spielbezeichnung ermitteln
		String team_home_id = sqlHelper.getGameTeamHomeIDByID(game_id);
		String team_away_id = sqlHelper.getGameTeamAwayIDByID(game_id);

		String team_home_short = sqlHelper.getClubShortByTeamID(team_home_id);
		String team_away_short = sqlHelper.getClubShortByTeamID(team_away_id);
		
		int year = 0;
		int day = 0;
		int month = 0;
		Date game_date = null;
		String str_game_date = sqlHelper.getGameDateByID(game_id);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try
		{
			game_date = sdf.parse(str_game_date);
			day = game_date.getDate();    // getDate => Day of month; getDay => day of week
			month = game_date.getMonth();
			year = game_date.getYear()+1900;
		}
		catch (ParseException e) {}
		
		str_game_date = (new StringBuilder()
			.append(day).append(".")
			.append(month + 1).append(".")
			.append(year))
			.toString();
		
		String game = team_home_short + " - " + team_away_short;
		
		TextView txt_game_date = (TextView) view.findViewById(R.id.game_date);
		TextView txt_game = (TextView) view.findViewById(R.id.game);
		txt_game_date.setText(str_game_date);
		txt_game.setText(game);
		
/* Spielerliste definieren und einrichten */
		
		// Nur Spieler einbinden, die in dem Spiel auch tatsächlich gespielt haben
		c = sqlHelper.getAllPlayerCursorByTeamID(team_id);
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			
			player_id = sqlHelper.getPlayerID(c);
			if (sqlHelper.count_ticker_activity(game_id, null, player_id, null, null, null) > 0) {
				player.add(player_id);	
			}
			
		}
		HelperAdapterStatPlayerList adapter = new HelperAdapterStatPlayerList(getActivity(), player, null);
		setListAdapter(adapter);
		
		return view;
		
	}

    
/*
 * 
 * Mannschaftauswahl 
 *
 */
	
	@Override
	public void onListItemClick(ListView list, View view, int position, long id) {
		// Spieldaten erhalten
		player_id = player.get(position);
		args.putString("PlayerID", player_id);
		
		// Spielerliste aktualisieren
		HelperAdapterStatPlayerList adapter = new HelperAdapterStatPlayerList(getActivity(), player, player_id);
		setListAdapter(adapter);
		
		startTask();
	}
	
	/// Async Task + Progress Dialog
	
	private void startTask() {
		if (getProgressDialog() == null) {
			uiHandler.post(new Runnable() {
				@Override
				public void run() {
					setProgressDialog(ProgressDialog.show(getActivity(), null, TabFragStatPlayerList.this.getString(R.string.in_progress), true));
				}
			});
		}
		new LoadingTask().execute(args);
	}
	
	private ProgressDialog progressDialog;
	private Handler uiHandler = new Handler();
	
	final class LoadingTask extends AsyncTask<Bundle, Void, Void> {
		protected Void doInBackground(final Bundle... args) {
			if (args == null) {
				return null;
			}
			
			// Spielerfenster aktualisieren
			FragmentManager fragmentManager = getFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			
			TabFragStatPlayerOverview fragOverview = new TabFragStatPlayerOverview();
			fragOverview.setArguments(args[0]);
			fragmentTransaction.replace(R.id.frag_stat_player_content_1, fragOverview);
			
			TabFragStatPlayerStat fragStat = new TabFragStatPlayerStat();
			fragStat.setArguments(args[0]);
			fragmentTransaction.replace(R.id.frag_stat_player_content_2, fragStat);
			fragmentTransaction.commit();
			
			uiHandler.post(new Runnable() {
				@Override
				public void run() {
					dismissProgressDialog();
				}
			});
			
			return null;
		}
	};

	public void dismissProgressDialog() {
		if (getProgressDialog() != null && getProgressDialog().isShowing()) {
			getProgressDialog().dismiss();
		}
	}

	public ProgressDialog getProgressDialog() {
		return progressDialog;
	}

	public void setProgressDialog(ProgressDialog progressDialog) {
		this.progressDialog = progressDialog;
	}
	
	///
}
