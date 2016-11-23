package de.fivestrikes.fs2;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ListView;

public class TabFragTeamSelect extends ListFragment {
	
	HelperSQL sqlHelper = null;
	Cursor model = null;
	HelperAdapterTeam adapter = null;
	String player_id = null;
	String server_player_id = null;
	String team_id = null;
	String server_team_id = null;
	String player_number = null;
	String player_forename = null;
	String player_surename = null;
	String player_position_first = null;
	String player_position_second = null;
	String player_position_third = null;
	String game_id = null;
	Integer home_or_away = null;
	String home_id = null;
	String away_id = null;
	TabFragGameList fragGameList;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		       
/* Helper generieren */
		
		sqlHelper=new HelperSQL(getActivity());
		
/* Daten aus Activity laden */ 
	        
		Bundle args = getArguments();
		if (args != null) {
			
			player_id = args.getString("PlayerID");
			server_player_id = args.getString("ServerPlayerID");
			team_id = args.getString("TeamID");
			server_team_id = args.getString("ServerTeamID");
			player_number = args.getString("PlayerNumber");
			player_forename = args.getString("PlayerForename");
			player_surename = args.getString("PlayerSurename");
			player_position_first = args.getString("PlayerPositionFirst");
			player_position_second = args.getString("PlayerPositionSecond");
			player_position_third = args.getString("PlayerPositionThird");
			
			game_id = args.getString("GameID");
			home_or_away = args.getInt("HomeOrAway");
			home_id = args.getString("HomeID");
			away_id = args.getString("AwayID");

		}

/* Teamliste einrichten */
		
		model=sqlHelper.getAllTeamCursor();
		getActivity().startManagingCursor(model);
		FragmentManager fragmentManager = getFragmentManager();
		adapter = new HelperAdapterTeam(getActivity(), model, null, fragmentManager);
		setListAdapter(adapter);	
		
		return inflater.inflate(R.layout.list, container, false);
	
	}
    
/*
 * 
 * Mannschaftauswahl 
 *
 */
	
	@Override
	public void onListItemClick(ListView list, View view, int position, long id) {
		
		// Mannschaftsdaten ermitteln
		team_id = String.valueOf(id);
		server_team_id = sqlHelper.getTeamServerTeamID(team_id);
		
		if (game_id != null) {
			
			// Abfragen, ob ausgewählte Heim- und Auswärtsmannschaft identisch sind
			Boolean team_equal = false;
						
			if (home_or_away.equals(1)) {
				if (team_id.equals(away_id) && away_id != null) {
					
					team_equal = true;
					
				}
			}
			
			if (home_or_away.equals(0)) {
				if (team_id.equals(home_id) && home_id != null) {
				
					team_equal = true;
					
				}
			}
			
			if (team_equal == true) {
				
				// DialogBox einrichten
				final Dialog dialog = new Dialog(getActivity());
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.custom_dialog);

				// Texte setzen
				TextView title = (TextView) dialog.findViewById(R.id.title);
				TextView text = (TextView) dialog.findViewById(R.id.text);
				title.setText(R.string.text_selection_not_possible);
				text.setText(R.string.text_opponent_selection);
				
				// Button definieren
				LinearLayout lyt_button2 = (LinearLayout) dialog.findViewById(R.id.lyt_button2);
				lyt_button2.removeAllViews();
				LinearLayout lyt_button3 = (LinearLayout) dialog.findViewById(R.id.lyt_button3);
				lyt_button3.removeAllViews();
				
				Button dialogButton1 = (Button) dialog.findViewById(R.id.button1);
				dialogButton1.setText(R.string.okay);
				
				dialogButton1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});

				dialog.show();
				// Ende Nachrichtenbox
				
			} else {
			
				if (home_or_away.equals(1)) {
				
					sqlHelper.updateGame(game_id, Integer.parseInt(team_id), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
				
				} else{
				
					sqlHelper.updateGame(game_id, null, Integer.parseInt(team_id), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
				
				}
			
				Bundle args = new Bundle();
				args.putString("GameID", game_id);
			
				// Aktualisiere die Spieliste
				fragGameList = (TabFragGameList) getFragmentManager().findFragmentById(R.id.frag_game_list);
				fragGameList.refreshContent(game_id);
	  		
				// Erneuere Fragments
				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();			
				TabFragGameEdit fragment = new TabFragGameEdit();
				fragment.setArguments(args);
				fragmentTransaction.replace(R.id.frag_game_edit, fragment);
				fragmentTransaction.commit();
			
			}
			
		} else {
			
			// Spielerdaten setzen
			Bundle args = new Bundle();
			args.putString("ServerPlayerID", server_player_id);
			args.putString("PlayerID", player_id);
			args.putString("TeamID", team_id);
			args.putString("ServerTeamID", server_team_id);
			args.putString("PlayerNumber", player_number);
			args.putString("PlayerForename", player_forename);
			args.putString("PlayerSurename", player_surename);
			args.putString("PlayerPositionFirst", player_position_first);
			args.putString("PlayerPositionSecond", player_position_second);
			args.putString("PlayerPositionSecond", player_position_third);
			
			FragmentManager fragmentManager = getFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			TabFragPlayerEdit fragment = new TabFragPlayerEdit();
			fragment.setArguments(args);
			fragmentTransaction.replace(R.id.frag_player_edit, fragment);
			fragmentTransaction.commit();
			
		}
	}
}
